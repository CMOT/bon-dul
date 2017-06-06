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
    private int sheetCount;
    
    public Incidencia(String type){
        this.type= type;
        switch(type){
            case "PRODUCCION": 
                sheetCount=4;
                columns= new int[]{ 0, 1, 4, 5, 6, 7, 8, 9, 10, 11, 21};
                break;
            case "PROCESOS":
                sheetCount=4;
                columns= new int[]{0,1, 2, 3};
                break;
            case "ENERGIAS":
                sheetCount=4;
                columns= new int[]{0,1, 2, 3};
                break;
            case "MANTENIMIENTO":
                sheetCount=4;
                columns= new int[]{0,1, 2, 3};
                break;    
            case "MP SUPPERS":
                sheetCount=4;
                columns= new int[]{0,1, 2, 3};
                break;
            case "MP":
                sheetCount=4;
                columns= new int[]{0,1, 2, 3};
                break;
            case "SOPLADO":
                sheetCount=4;
                columns= new int[]{0,1, 2, 3};
                break;
            case "CALIDAD":
                sheetCount=4;
                columns= new int[]{0,1, 2, 3};
                break;
            case "PT":
                sheetCount=4;
                columns= new int[]{0,1, 2, 3};
                break;
            default :
                JOptionPane.showMessageDialog(null, "No se encontro el tipo de area: "+type);
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
    
}