/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reports.model;

import javax.swing.JOptionPane;

/**
 *
 * @author Cesar
 */
public class Incidencia {
    
    private String type;
    private int columns[];
    private int columnsTwo[];
    private int sheets[];
    private int sheetCount;
    
    public Incidencia(String type, String file){
        this.type= type;
        switch(type){
            case "PRODUCCION": 
                sheetCount=7;
                sheets= new int[]{0,1, 2, 3, 11, 12, 13};
                columns= new int[]{ 0, 1, 4, 5, 6, 7, 8, 9, 10, 11, 21};
                columnsTwo= new int[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8,9, 19};
                break;
            case "PROCESOS":
                sheetCount=1;
                sheets= new int[]{0};;
                columns= new int[]{0,1, 3, 4, 5, 6, 7, 8, 9, 10, 21};
                break;
            case "ENERGIAS":
                sheetCount=1;
                sheets= new int[]{0};;
                columns= new int[]{0,1,  4, 5, 6, 7, 8, 9, 10, 11, 21};
                break;
            case "MANTENIMIENTO":
                sheetCount=1;
                sheets= new int[]{0};;
                columns= new int[]{0,1, 4, 5, 6, 7, 8, 9, 10, 11, 21};
                break;    
            case "MP SUPERS":
                sheetCount=1;
                sheets= new int[]{0};;
                columns= new int[]{0,1, 3, 4, 5, 6, 7, 8, 9, 10, 20};
                break;
            case "MP":
                sheetCount=1;
                sheets= new int[]{0};;
                columns= new int[]{ 0, 1, 4, 5, 6, 7, 8, 9, 10, 11, 21};
                break;
            case "SOPLADO":
                sheetCount=1;
                sheets= new int[]{0};;
                columns= new int[]{0,1, 3, 4, 5, 6, 7, 8, 9, 10, 21};
                break;
            case "CALIDAD":
                sheetCount=1;
                sheets= new int[]{0};;
                columns= new int[]{0,1, 3, 4, 5, 6, 7, 8, 9, 10, 21};
                break;
            case "PT":
                sheetCount=1;
                sheets= new int[]{0};;
                columns= new int[]{4,6, 9, 10, 11, 12, 13, 14, 15, 16, 27};
                break;
            default :
                JOptionPane.showMessageDialog(null, "Seleccione un area especifica para el archivo: "+file);
                columns=null;
        }
    }
    
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the columns
     */
    public int[] getColumns() {
        return columns;
    }

    /**
     * @param columns the columns to set
     */
    public void setColumns(int[] columns) {
        this.columns = columns;
    }

    /**
     * @return the sheetCount
     */
    public int getSheetCount() {
        return sheetCount;
    }

    /**
     * @param sheetCount the sheetCount to set
     */
    public void setSheetCount(int sheetCount) {
        this.sheetCount = sheetCount;
    }

    /**
     * @return the sheets
     */
    public int[] getSheets() {
        return sheets;
    }

    /**
     * @param sheets the sheets to set
     */
    public void setSheets(int[] sheets) {
        this.sheets = sheets;
    }

    /**
     * @return the columnsTwo
     */
    public int[] getColumnsTwo() {
        return columnsTwo;
    }

    /**
     * @param columnsTwo the columnsTwo to set
     */
    public void setColumnsTwo(int[] columnsTwo) {
        this.columnsTwo = columnsTwo;
    }
    
    
}
