package com.reports.utils;

import org.apache.poi.xssf.usermodel.XSSFCell;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cesar
 */
public class ColumnReader {
    
    
    public ColumnReader(){
        
        
    }
    
    public String getColumnDefaultValue(XSSFCell cell){
        String value="";
        if(cell!=null){
            value=getValieByTypeCell(cell);
        }
        return value;
    }
    
    public String getColumnB(XSSFCell cell){
        String value="";
        if(cell!=null){
            value=getValieByTypeCell(cell);
        }
        return value;
    }
    
    public String getValieByTypeCell(XSSFCell cell) {
        String value = "";
        switch (cell.getCellType()) {
            case 0:
                value= String.valueOf(cell.getNumericCellValue());
                break;
            case 1:
                value= String.valueOf( cell.getStringCellValue());
                break;
            case 3:
                break;
            case 4:
                 value= String.valueOf( cell.getDateCellValue());
                break;
        }
        return value;
    }
}
