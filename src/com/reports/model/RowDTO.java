/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reports.model;

/**
 *
 * @author Cesar
 */
public class RowDTO {
    private String numero;
    private String nombre;
    private String puesto;
    private String tiempoExtra;
    private String lunes;
    private String martes;
    private String miercoles;
    private String jueves;
    private String viernes;
    private String sabado;
    private String domingo;
    private String PD;
    private String DT;
    private String vac;
    private String faltas;
    private String FET;
    private String comedor;
    private String observaciones;
    private String observaciones2;
    private String area;
    private String empresa;
    private boolean nominaQuincenal;
    private String nameFile;

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the puesto
     */
    public String getPuesto() {
        return puesto;
    }

    /**
     * @param puesto the puesto to set
     */
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    /**
     * @return the tiempoExtra
     */
    public String getTiempoExtra() {
        return tiempoExtra;
    }

    /**
     * @param tiempoExtra the tiempoExtra to set
     */
    public void setTiempoExtra(String tiempoExtra) {
        if(tiempoExtra.equalsIgnoreCase("")){
            this.tiempoExtra = "0";
        }else{
            this.tiempoExtra = tiempoExtra;
        }
        
    }

    /**
     * @return the lunes
     */
    public String getLunes() {
        return lunes;
    }

    /**
     * @param lunes the lunes to set
     */
    public void setLunes(String lunes) {
        this.lunes = lunes;
    }

    /**
     * @return the martes
     */
    public String getMartes() {
        return martes;
    }

    /**
     * @param martes the martes to set
     */
    public void setMartes(String martes) {
        this.martes = martes;
    }

    /**
     * @return the miercoles
     */
    public String getMiercoles() {
        return miercoles;
    }

    /**
     * @param miercoles the miercoles to set
     */
    public void setMiercoles(String miercoles) {
        this.miercoles = miercoles;
    }

    /**
     * @return the jueves
     */
    public String getJueves() {
        return jueves;
    }

    /**
     * @param jueves the jueves to set
     */
    public void setJueves(String jueves) {
        this.jueves = jueves;
    }

    /**
     * @return the viernes
     */
    public String getViernes() {
        return viernes;
    }

    /**
     * @param viernes the viernes to set
     */
    public void setViernes(String viernes) {
        this.viernes = viernes;
    }

    /**
     * @return the sabado
     */
    public String getSabado() {
        return sabado;
    }

    /**
     * @param sabado the sabado to set
     */
    public void setSabado(String sabado) {
        this.sabado = sabado;
    }

    /**
     * @return the domingo
     */
    public String getDomingo() {
        return domingo;
    }

    /**
     * @param domingo the domingo to set
     */
    public void setDomingo(String domingo) {
        this.domingo = domingo;
    }

    /**
     * @return the PD
     */
    public String getPD() {
        return PD;
    }

    /**
     * @param PD the PD to set
     */
    public void setPD(String PD) {
        this.PD = PD;
    }

    /**
     * @return the DT
     */
    public String getDT() {
        return DT;
    }

    /**
     * @param DT the DT to set
     */
    public void setDT(String DT) {
        this.DT = DT;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the vac
     */
    public String getVac() {
        return vac;
    }

    /**
     * @param vac the vac to set
     */
    public void setVac(String vac) {
        this.vac = vac;
    }

    /**
     * @return the faltas
     */
    public String getFaltas() {
        return faltas;
    }

    /**
     * @param faltas the faltas to set
     */
    public void setFaltas(String faltas) {
        this.faltas = faltas;
    }

    /**
     * @return the FET
     */
    public String getFET() {
        return FET;
    }

    /**
     * @param FET the FET to set
     */
    public void setFET(String FET) {
        this.FET = FET;
    }

    /**
     * @return the comedor
     */
    public String getComedor() {
        return comedor;
    }

    /**
     * @param comedor the comedor to set
     */
    public void setComedor(String comedor) {
        this.comedor = comedor;
    }

    /**
     * @return the observaciones2
     */
    public String getObservaciones2() {
        return observaciones2;
    }

    /**
     * @param observaciones2 the observaciones2 to set
     */
    public void setObservaciones2(String observaciones2) {
        this.observaciones2 = observaciones2;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return the empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the nominaQuincenal
     */
    public boolean isNominaQuincenal() {
        return nominaQuincenal;
    }

    /**
     * @param nominaQuincenal the nominaQuincenal to set
     */
    public void setNominaQuincenal(boolean nominaQuincenal) {
        this.nominaQuincenal = nominaQuincenal;
    }

    /**
     * @return the nameFile
     */
    public String getNameFile() {
        return nameFile;
    }

    /**
     * @param nameFile the nameFile to set
     */
    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }
    
}
