package com.reports.utils;

import com.reports.model.RowDTO;
import org.apache.poi.ss.usermodel.Cell;
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
    
    private int cantidadA;
    private int cantidadDT;
    private int cantidadV;
    private int cantidadF;
    private int cantidadFET;
    
    public ColumnReader(){
        cantidadA=0;
        cantidadDT=0;
        cantidadV=0;
        cantidadF=0;
        cantidadFET=0;
    }
    
    public String getColumnDefaultValue(XSSFCell cell){
        String value="";
        if(cell!=null){
            value=getValieByTypeCell(cell);
        }
        return value;
    }
    public String getColumnPD(XSSFCell cell, RowDTO row){
        String value="0";
        if(row.getDomingo().compareToIgnoreCase("A")==0 || row.getDomingo().compareToIgnoreCase("DT")==0){
                value="1";
        }
        return value;
    }
    
    public String getColumnDT(XSSFCell cell, RowDTO row){
        String value="";
        int suma=cantidadDT;
        if(row.getDomingo().compareToIgnoreCase("A")==0 ){
            suma=cantidadDT+ 1;
        }
        value=String.valueOf(suma);
        return value;
    }
    
    public String getColumnAndSumLetter(XSSFCell cell){
        String value="";
        if(cell!=null){
            value=getValieByTypeCell(cell);
        }
        sumLetter(value);
        return value;
    }
    
    public void sumLetter(String letter){
        switch(letter){
            case "A":
                cantidadA++;
                break;
            case "DT":
                cantidadDT++;
                break;
            case "V":
                cantidadV++;
                break;
            case "FET":
                cantidadFET++;
                break;
            case "F":
                cantidadF++;
                break;
        }
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
        if(cell!=null){
            switch (cell.getCellType()) {
                case 0:
                    value= String.valueOf((int)cell.getNumericCellValue());
                    break;
                case 1:
                    value= String.valueOf( cell.getStringCellValue());
                    break;
                case 3:
                    break;
                case 4:
                     value= String.valueOf( cell.getDateCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    value= String.valueOf( cell.getNumericCellValue());
                    break;
            }
        }
        return value;
    }

    /**
     * @return the cantidadA
     */
    public int getCantidadA() {
        return cantidadA;
    }

    /**
     * @param cantidadA the cantidadA to set
     */
    public void setCantidadA(int cantidadA) {
        this.cantidadA = cantidadA;
    }

    /**
     * @return the cantidadDT
     */
    public int getCantidadDT() {
        return cantidadDT;
    }

    /**
     * @param cantidadDT the cantidadDT to set
     */
    public void setCantidadDT(int cantidadDT) {
        this.cantidadDT = cantidadDT;
    }

    /**
     * @return the cantidadV
     */
    public int getCantidadV() {
        return cantidadV;
    }

    /**
     * @param cantidadV the cantidadV to set
     */
    public void setCantidadV(int cantidadV) {
        this.cantidadV = cantidadV;
    }

    /**
     * @return the cantidadF
     */
    public int getCantidadF() {
        return cantidadF;
    }

    /**
     * @param cantidadF the cantidadF to set
     */
    public void setCantidadF(int cantidadF) {
        this.cantidadF = cantidadF;
    }

    /**
     * @return the cantidadFET
     */
    public int getCantidadFET() {
        return cantidadFET;
    }

    /**
     * @param cantidadFET the cantidadFET to set
     */
    public void setCantidadFET(int cantidadFET) {
        this.cantidadFET = cantidadFET;
    }
    
    
}
