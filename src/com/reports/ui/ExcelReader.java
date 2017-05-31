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
import java.util.logging.Level;
import java.util.logging.Logger;
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
        for (File file : files) {
            XSSFWorkbook hssfWorkbook = null;
            XSSFSheet hssfSheet;
             try (final InputStream is = new FileInputStream(file)) {
                hssfWorkbook = new XSSFWorkbook(is);
                is.close();
            }catch (IOException e) {
                Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            }
             for (int i = 0; i < 4; i++) {
                 XSSFSheet sheet = hssfWorkbook.getSheetAt(i);
                  for (int j = 0; j < sheet.getLastRowNum(); j++) {
                       XSSFRow row = sheet.getRow(j);
                       if (row != null) {
                            XSSFCell cell= row.getCell(0);
                            if(cell!=null || columnReader.getValieByTypeCell(cell).isEmpty()){
                                counter++;
                            }
                       }
                  }
             }
         }
        return counter;
    }
    
    public void readAllFiles(File[] files) {
//        System.out.println("Path: " + path);
//        File[] files = getFiles(path);
        for (File file : files) {
            XSSFWorkbook hssfWorkbook = null;
            XSSFSheet hssfSheet;
            try (final InputStream is = new FileInputStream(file)) {
                hssfWorkbook = new XSSFWorkbook(is);
                is.close();
            }catch (IOException e) {
                Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            }
            if (hssfWorkbook != null) {
                for (int i = 0; i < 4; i++) {
                    System.out.println("hoja numero: " + i);
                    hssfSheet = hssfWorkbook.getSheetAt(i);
                    readNotEmptyRows(hssfSheet);
                    System.out.println("\n************************************************\n"
                            + "************************************************\n");
                }
            }
        }
        System.out.println("Cantidad de registros: "+ listRows.size());
    }

    public void readNotEmptyRows(XSSFSheet sheet) {
        RowDTO dto;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            if (row != null) {
                dto = new RowDTO();
                dto = readNotEmptyCells(row, dto, i);
                if(dto!=null){  
                    listRows.add(dto);
                }
            }
        }
    }

    public RowDTO readNotEmptyCells(XSSFRow row, RowDTO dto, int indexColumn) {
        XSSFCell cell= row.getCell(0);
        if(cell==null || columnReader.getValieByTypeCell(cell).isEmpty() || Character.isLetter(columnReader.getValieByTypeCell(cell).charAt(0)) ){
//            System.out.println( "Valor de la celda: "+ columnReader.getValieByTypeCell(cell)  +" Color de la celda: "  +cell.getCellStyle().getFillBackgroundColor()+ " Patron: "+cell.getCellStyle().getFillPattern());
           return null;
        }else{
            dto.setNumero(columnReader.getColumnDefaultValue(row.getCell(0)));
            dto.setNombre(columnReader.getColumnDefaultValue(row.getCell(1)));
            dto.setLinea(columnReader.getColumnDefaultValue(row.getCell(2)));
            dto.setTiempoExtra(columnReader.getColumnDefaultValue(row.getCell(3)));
            dto.setLunes(columnReader.getColumnDefaultValue(row.getCell(4)));
            dto.setMartes(columnReader.getColumnDefaultValue(row.getCell(5)));
            dto.setMiercoles(columnReader.getColumnDefaultValue(row.getCell(6)));
            dto.setJueves(columnReader.getColumnDefaultValue(row.getCell(7)));
            dto.setViernes(columnReader.getColumnDefaultValue(row.getCell(8)));
            dto.setSabado(columnReader.getColumnDefaultValue(row.getCell(9)));
            dto.setDomingo(columnReader.getColumnDefaultValue(row.getCell(10)));
            dto.setLunesTE(columnReader.getColumnDefaultValue(row.getCell(11)));
            dto.setMartesTE(columnReader.getColumnDefaultValue(row.getCell(12)));
            dto.setMiercolesTE(columnReader.getColumnDefaultValue(row.getCell(13)));
            dto.setJuevesTE(columnReader.getColumnDefaultValue(row.getCell(14)));
            dto.setViernesTE(columnReader.getColumnDefaultValue(row.getCell(15)));
            dto.setSabadoTE(columnReader.getColumnDefaultValue(row.getCell(16)));
            dto.setDomingoTE(columnReader.getColumnDefaultValue(row.getCell(17)));
            dto.setPD(columnReader.getColumnDefaultValue(row.getCell(18)));
            dto.setDT(columnReader.getColumnDefaultValue(row.getCell(19)));
            dto.setObservaciones(columnReader.getColumnDefaultValue(row.getCell(20)));
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
