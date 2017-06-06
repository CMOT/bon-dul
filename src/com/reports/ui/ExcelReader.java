/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reports.ui;

import com.reports.utils.ColumnReader;
import com.reports.model.RowDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Cesar
 */
public class ExcelReader {

    ArrayList<RowDTO> listRows;
    ColumnReader columnReader;

    public ExcelReader() {
        listRows = new ArrayList<RowDTO>();
        columnReader= new ColumnReader();
    }
    
    public int countTotalLines(File[] files){
        int counter=0;
        for (int k=0; k< files.length; k++) {
            File actual= files[k];
            XSSFWorkbook hssfWorkbook = null;
            XSSFSheet hssfSheet;
             try (InputStream is = new FileInputStream(actual)) {
                hssfWorkbook = new XSSFWorkbook(is);
                is.close();
            }catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al leer archivo: Error 7..");
            }
            if (hssfWorkbook != null) {
                for (int i = 0; i < 1; i++) {
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
        return counter;
    }
    
    public ArrayList<RowDTO> readAllFiles(File[] files) {
        
        for (int k=0; k< files.length; k++) {
            File actual= files[k];
            XSSFWorkbook hssfWorkbook = null;
            XSSFSheet hssfSheet;
            try (InputStream is = new FileInputStream(actual)) {
                hssfWorkbook = new XSSFWorkbook(is);
                is.close();
            }catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al leer archivo: Error 7..");
            }
            if (hssfWorkbook != null) {
                for (int i = 0; i < 1; i++) {
                    hssfSheet = hssfWorkbook.getSheetAt(i);
                    readNotEmptyRows(hssfSheet);
                }
            }
        }
        return listRows;
    }

    public void readNotEmptyRows(XSSFSheet sheet) {
        RowDTO dto;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            columnReader= new ColumnReader();
            XSSFRow row = sheet.getRow(i);
            if (row != null) {
                dto = new RowDTO();
                dto = readNotEmptyCells(row, dto);
                if(dto!=null){  
                    listRows.add(dto);
                    NominaPanel.progressBar.setValue(listRows.size());
                    NominaPanel.txtAreaLog.append(dto.getNumero()+"\t"+dto.getNombre()+"\n\r");
                }
            }
        }
    }

    public RowDTO readNotEmptyCells(XSSFRow row, RowDTO dto) {
        XSSFCell cell= row.getCell(0);
        if(cell==null || columnReader.getValieByTypeCell(cell).isEmpty() || Character.isLetter(columnReader.getValieByTypeCell(cell).charAt(0)) ){
           return null;
        }else{
            dto.setNumero(columnReader.getColumnDefaultValue(row.getCell(0)));
            dto.setNombre(columnReader.getColumnDefaultValue(row.getCell(1)));
            dto.setLinea(columnReader.getColumnDefaultValue(row.getCell(2)));
            dto.setTiempoExtra(columnReader.getColumnDefaultValue(row.getCell(4)));
            dto.setLunes(columnReader.getColumnAndSumLetter(row.getCell(5)));
            dto.setMartes(columnReader.getColumnAndSumLetter(row.getCell(6)));
            dto.setMiercoles(columnReader.getColumnAndSumLetter(row.getCell(7)));
            dto.setJueves(columnReader.getColumnAndSumLetter(row.getCell(8)));
            dto.setViernes(columnReader.getColumnAndSumLetter(row.getCell(9)));
            dto.setSabado(columnReader.getColumnAndSumLetter(row.getCell(10)));
            dto.setDomingo(columnReader.getColumnAndSumLetter(row.getCell(11)));
            dto.setPD(columnReader.getColumnPD(cell,dto));
            dto.setDT(columnReader.getColumnDT(cell,dto));
            dto.setVac(String.valueOf(columnReader.getCantidadV()));
            dto.setFaltas(String.valueOf(columnReader.getCantidadF()));
            dto.setFET(String.valueOf(columnReader.getCantidadFET()));
            dto.setComedor(String.valueOf(columnReader.getCantidadA()+columnReader.getCantidadDT()+columnReader.getCantidadFET()));
            dto.setObservaciones(columnReader.getColumnDefaultValue(row.getCell(21)));
        }
        return dto;
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
