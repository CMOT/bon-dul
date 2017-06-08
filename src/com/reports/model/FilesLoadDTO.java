/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reports.model;

import java.io.File;

/**
 *
 * @author Cesar
 */
public class FilesLoadDTO {
    
    private File[] files;
    private String[] types;
    private String[] fechas;

    
    public FilesLoadDTO(int size){
        files= new File[size];
        types= new String[size];
        fechas= new String[size];
    }
    /**
     * @return the files
     */
    public File[] getFiles() {
        return files;
    }

    /**
     * @param files the files to set
     */
    public void setFiles(File[] files) {
        this.files = files;
    }

    /**
     * @return the types
     */
    public String[] getTypes() {
        return types;
    }

    /**
     * @param types the types to set
     */
    public void setTypes(String[] types) {
        this.types = types;
    }

    /**
     * @return the fechas
     */
    public String[] getFechas() {
        return fechas;
    }

    /**
     * @param fechas the fechas to set
     */
    public void setFechas(String[] fechas) {
        this.fechas = fechas;
    }
    
    
}
