/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reports.ui;

import com.reports.model.FilesLoadDTO;
import com.reports.model.Incidencia;
import com.reports.utils.ColumnReader;
import com.reports.model.RowDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Cesar
 */
public class ExcelReader {

    ArrayList<RowDTO> listRowsOAM;
    ArrayList<RowDTO> listRowsLiquimex;
    ColumnReader columnReader;
    Incidencia incidencia;

    public ExcelReader() {
        listRowsOAM = new ArrayList<>();
        listRowsLiquimex = new ArrayList<>();
        columnReader= new ColumnReader();
    }
    
    public int countTotalLines(FilesLoadDTO fileDTO){
        int counter=0;
        for (int k=0; k< fileDTO.getFiles().length; k++) {
            File actual= fileDTO.getFiles()[k];
            XSSFWorkbook hssfWorkbook = null;
            XSSFSheet hssfSheet;
             try (InputStream is = new FileInputStream(actual)) {
                hssfWorkbook = new XSSFWorkbook(is);
                is.close();
            }catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al leer archivo: Error 7.."+e);
            }
            if (hssfWorkbook != null) {
                incidencia= new Incidencia(fileDTO.getTypes()[k], fileDTO.getFiles()[k].getName());
//                incidencia= new Incidencia(types[k]);
                if(incidencia.getColumns()!=null){
                    for (int i = 0; i < incidencia.getSheetCount(); i++) {
                        hssfSheet = hssfWorkbook.getSheetAt(i);
                        for (int j = 0; j < hssfSheet.getLastRowNum(); j++) {
                           XSSFRow row = hssfSheet.getRow(j);
                           if (row != null) {
                                XSSFCell cell= row.getCell(0);
                                if(cell==null || columnReader.getValieByTypeCell(cell).isEmpty() || Character.isLetter(columnReader.getValieByTypeCell(cell).charAt(0))){
                                }else{
                                    counter++;
                                }
                           }
                        }
                    }
                }
            }
         }
        return counter;
    }
    
    public ArrayList<RowDTO> readAllFiles(FilesLoadDTO fileDTO) {
        
        for (int k=0; k< fileDTO.getFiles().length; k++) {
            File actual= fileDTO.getFiles()[k];
            XSSFWorkbook hssfWorkbook = null;
            XSSFSheet hssfSheet;
            try (InputStream is = new FileInputStream(actual)) {
                hssfWorkbook = new XSSFWorkbook(is);
                is.close();
            }catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al leer archivo: Error 7..:"+e);
            }
            if (hssfWorkbook != null) {
                incidencia= new Incidencia(fileDTO.getTypes()[k], fileDTO.getFiles()[k].getName());
                for (int i = 0; i < incidencia.getSheetCount(); i++) {
                    hssfSheet = hssfWorkbook.getSheetAt(incidencia.getSheets()[i]);
                    System.out.println("nombre de hoja: "+ hssfSheet.getSheetName());
                    NominaPanel.progressBar.setMaximum(hssfSheet.getLastRowNum());
                    NominaPanel.progressBar.setValue(0);
                    readNotEmptyRows(hssfSheet, incidencia.getSheets()[i], fileDTO.getFechas()[k]);
                }
            }
        }
        return listRowsOAM;
    }

    public void readNotEmptyRows(XSSFSheet sheet, int sheetNum, String fecha) {
        RowDTO dto;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            NominaPanel.progressBar.setValue(i);
            columnReader= new ColumnReader();
            XSSFRow row = sheet.getRow(i);
            if (row != null) {
                dto = new RowDTO();
                if(sheetNum==12 || sheetNum==13){
                    dto = readNotEmptyCellsColumnsTwo(row, dto);
                }else{
                    dto = readNotEmptyCells(row, dto);
                }
                if(dto!=null){
                    dto.setNameFile(fecha);
                    if( (incidencia.getType().equalsIgnoreCase("PRODUCCION") && (sheetNum != 11 && sheetNum != 12 && sheetNum != 13))   
                            || incidencia.getType().equalsIgnoreCase("MP")
                            || incidencia.getType().equalsIgnoreCase("PT")){
                        dto.setEmpresa("LIQUIMEX");
                        listRowsLiquimex.add(dto);
                        addRowToTable(dto);
                    }else{
                        dto.setEmpresa("OAM");
                        listRowsOAM.add(dto);
                        addRowToTable(dto);
                    }
                    
                }
            }
        }
    }

    public void addRowToTable(RowDTO dto){
        DefaultTableModel model= (DefaultTableModel)NominaPanel.tableResults.getModel();
        model.addRow(new String[]{dto.getArea(), dto.getEmpresa(), dto.getNumero(), dto.getNombre(), dto.getTiempoExtra()});
    }
    public RowDTO readNotEmptyCells(XSSFRow row, RowDTO dto) {
        XSSFCell cell= row.getCell(incidencia.getColumns()[0]);
        if(invalidId(cell)){
           return null;
        }else{
            dto.setArea(incidencia.getType());
            dto.setNumero(columnReader.getColumnDefaultValue(row.getCell(incidencia.getColumns()[0])));
            dto.setNombre(columnReader.getColumnDefaultValue(row.getCell(incidencia.getColumns()[1])));
            dto.setTiempoExtra(columnReader.getColumnDefaultValue(row.getCell(incidencia.getColumns()[2])));
            dto.setLunes(columnReader.getColumnAndSumLetter(row.getCell(incidencia.getColumns()[3])));
            dto.setMartes(columnReader.getColumnAndSumLetter(row.getCell(incidencia.getColumns()[4])));
            dto.setMiercoles(columnReader.getColumnAndSumLetter(row.getCell(incidencia.getColumns()[5])));
            dto.setJueves(columnReader.getColumnAndSumLetter(row.getCell(incidencia.getColumns()[6])));
            dto.setViernes(columnReader.getColumnAndSumLetter(row.getCell(incidencia.getColumns()[7])));
            dto.setSabado(columnReader.getColumnAndSumLetter(row.getCell(incidencia.getColumns()[8])));
            dto.setDomingo(columnReader.getColumnAndSumLetter(row.getCell(incidencia.getColumns()[9])));
            dto.setPD(columnReader.getColumnPD(cell,dto));
            dto.setDT(columnReader.getColumnDT(cell,dto));
            dto.setVac(String.valueOf(columnReader.getCantidadV()));
            dto.setFaltas(String.valueOf(columnReader.getCantidadF()));
            dto.setFET(String.valueOf(columnReader.getCantidadFET()));
            dto.setComedor(String.valueOf(columnReader.getCantidadA()+columnReader.getCantidadDT()+columnReader.getCantidadFET()));
            dto.setObservaciones(columnReader.getColumnDefaultValue(row.getCell(incidencia.getColumns()[10])));
        }
        return dto;
    }
    
    public RowDTO readNotEmptyCellsColumnsTwo(XSSFRow row, RowDTO dto) {
        XSSFCell cell= row.getCell(incidencia.getColumnsTwo()[0]);
        if(invalidId(cell)){
           return null;
        }else{
            dto.setArea(incidencia.getType());
            dto.setNumero(columnReader.getColumnDefaultValue(row.getCell(incidencia.getColumnsTwo()[0])));
            dto.setNombre(columnReader.getColumnDefaultValue(row.getCell(incidencia.getColumnsTwo()[1])));
            dto.setTiempoExtra(columnReader.getColumnDefaultValue(row.getCell(incidencia.getColumnsTwo()[2])));
            dto.setLunes(columnReader.getColumnAndSumLetter(row.getCell(incidencia.getColumnsTwo()[3])));
            dto.setMartes(columnReader.getColumnAndSumLetter(row.getCell(incidencia.getColumnsTwo()[4])));
            dto.setMiercoles(columnReader.getColumnAndSumLetter(row.getCell(incidencia.getColumnsTwo()[5])));
            dto.setJueves(columnReader.getColumnAndSumLetter(row.getCell(incidencia.getColumnsTwo()[6])));
            dto.setViernes(columnReader.getColumnAndSumLetter(row.getCell(incidencia.getColumnsTwo()[7])));
            dto.setSabado(columnReader.getColumnAndSumLetter(row.getCell(incidencia.getColumnsTwo()[8])));
            dto.setDomingo(columnReader.getColumnAndSumLetter(row.getCell(incidencia.getColumnsTwo()[9])));
            dto.setPD(columnReader.getColumnPD(cell,dto));
            dto.setDT(columnReader.getColumnDT(cell,dto));
            dto.setVac(String.valueOf(columnReader.getCantidadV()));
            dto.setFaltas(String.valueOf(columnReader.getCantidadF()));
            dto.setFET(String.valueOf(columnReader.getCantidadFET()));
            dto.setComedor(String.valueOf(columnReader.getCantidadA()+columnReader.getCantidadDT()+columnReader.getCantidadFET()));
            dto.setObservaciones(columnReader.getColumnDefaultValue(row.getCell(incidencia.getColumnsTwo()[10])));
        }
        return dto;
    }
    
    public boolean invalidId(XSSFCell cell){
        String value=columnReader.getValieByTypeCell(cell);
        if(cell==null || value.isEmpty()){
            return true;
        }else if(!Character.isDigit(value.charAt(0)) && !value.equalsIgnoreCase("OAM")){
            return true;
        }else{
            return false;
        }
    }
    
    public File getFile(String path) {
        return new File(path);
    }

    public File[] getFiles(String path) {
        File folder = new File(path);
        File finalfolder;
        File[] listOfFiles = folder.listFiles();
        return listOfFiles;
    }
}
