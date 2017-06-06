/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reports.ui;

import com.reports.model.RowDTO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ASUS
 */
public class ExcelWriter {
    
    String titles[]= new String[]{"AREA", "Empresa", "ID", "NOMBRE DEL EMPLEADO", "TE", "L","M", "M", "J", "V","S", "D",
                                    "PD", "DT", "Vac", "Faltas", "FET", "Comedor", "Comedor", "Bono", "Motivos tiempo Extra",
                                    "SUELDO", "Jornada Laboral(8 horas)", "Costo TE Extra"};
    public ExcelWriter(){
        
    }
    
    public void createConsolidado(String file, ArrayList<RowDTO> listRows){
        //nombre del archivo de Excel
        if(!file.endsWith(".xlsx")){
            file= file+".xlsx";
        }
        String nombreHoja1 = "Hoja1";//nombre de la hoja1

        XSSFWorkbook libroTrabajo = new XSSFWorkbook();
        XSSFSheet hoja1 = libroTrabajo.createSheet(nombreHoja1) ;
        for (int r=0;r < listRows.size(); r++ ) {
            RowDTO dto= listRows.get(r);
            XSSFRow row = hoja1.createRow(r);
            if(r==0){
                fillTitles(row);
            }else{
                fillRow(row, dto );
            }
        }
        try(FileOutputStream fileOut = new FileOutputStream(file)){
            //escribir este libro en un OutputStream.
            libroTrabajo.write(fileOut);
            fileOut.flush();
            fileOut.close();
            JOptionPane.showMessageDialog(null, "Archivo guardado con exito en la ruta: \n\r"+file);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error al guardar archivo: Error 9");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al crear archivo: Error 15..");
        }
    }
    public void fillTitles(XSSFRow row){
        //Headers
        for( int i=0;i < titles.length; i++){
            String header= titles[i];
            XSSFCell cell = row.createCell(i, XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(header);
        }
    }
    
    public void fillRow(XSSFRow row, RowDTO dto){
        try{
           int line=row.getRowNum()+1;
            //Area
           XSSFCell cell = row.createCell(0, XSSFCell.CELL_TYPE_STRING);
           cell.setCellValue("Area");

           //Empresa
           cell = row.createCell(1, XSSFCell.CELL_TYPE_STRING);
           cell.setCellValue("Empresa");

           //Id
           cell = row.createCell(2, XSSFCell.CELL_TYPE_STRING);
           cell.setCellValue(dto.getNumero());

           //Nombre
           cell = row.createCell(3, XSSFCell.CELL_TYPE_STRING);
           cell.setCellValue(dto.getNombre());

           //TE
           cell = row.createCell(4, XSSFCell.CELL_TYPE_STRING);
           cell.setCellValue(Double.parseDouble(dto.getTiempoExtra()));

           //Lunes
           cell = row.createCell(5, XSSFCell.CELL_TYPE_STRING);
           cell.setCellValue(dto.getLunes());

           //Martes
           cell = row.createCell(6, XSSFCell.CELL_TYPE_STRING);
           cell.setCellValue(dto.getMartes());

           //Miercoles
           cell = row.createCell(7, XSSFCell.CELL_TYPE_STRING);
           cell.setCellValue(dto.getMiercoles());

           //Jueves
           cell = row.createCell(8, XSSFCell.CELL_TYPE_STRING);
           cell.setCellValue(dto.getJueves());

           //Viernes
           cell = row.createCell(9, XSSFCell.CELL_TYPE_STRING);
           cell.setCellValue(dto.getViernes());

           //Sabado
           cell = row.createCell(10, XSSFCell.CELL_TYPE_STRING);
           cell.setCellValue(dto.getSabado());

           //Domingo
           cell = row.createCell(11, XSSFCell.CELL_TYPE_STRING);
           cell.setCellValue(dto.getDomingo());

           //PD
//           cell = row.createCell(12, XSSFCell.CELL_TYPE_NUMERIC);
//           cell.setCellValue(Double.parseDouble(dto.getPD()));
           
           //PD
           cell = row.createCell(12, XSSFCell.CELL_TYPE_STRING);
           cell.setCellValue("=CONTAR.SI(L"+line+",\"A\")+CONTAR.SI(L"+line+",\"DT\")");

           //DT
//           cell = row.createCell(13, XSSFCell.CELL_TYPE_NUMERIC);
//           cell.setCellValue(Double.parseDouble(dto.getDT()));
           //DT
           cell = row.createCell(13, XSSFCell.CELL_TYPE_FORMULA);
           cell.setCellValue("CONTAR.SI(F"+line+":L"+line+",\"DT\")+CONTAR.SI(L"+line+",\"A\")");

           //Vac
           cell = row.createCell(14, XSSFCell.CELL_TYPE_NUMERIC);
           cell.setCellValue(Double.parseDouble(dto.getVac()));

           //Faltas
           cell = row.createCell(15, XSSFCell.CELL_TYPE_NUMERIC);
           cell.setCellValue(Double.parseDouble(dto.getFaltas()));

           //FET
           cell = row.createCell(16, XSSFCell.CELL_TYPE_NUMERIC);
           cell.setCellValue(Double.parseDouble(dto.getFET()));

           //Comedor
           cell = row.createCell(17, XSSFCell.CELL_TYPE_NUMERIC);
           cell.setCellValue(Double.parseDouble(dto.getComedor()));

           //Comedor
           cell = row.createCell(18, XSSFCell.CELL_TYPE_NUMERIC);
           cell.setCellValue(Double.parseDouble(dto.getComedor()));

           //Bono
           cell = row.createCell(19, XSSFCell.CELL_TYPE_NUMERIC);
           cell.setCellValue(400);

           //Motivo tiempo extra
           cell = row.createCell(20, XSSFCell.CELL_TYPE_STRING);
           cell.setCellValue(dto.getObservaciones());

           //Sueldo
           cell = row.createCell(21, XSSFCell.CELL_TYPE_NUMERIC);
           cell.setCellValue(100);

           //Jornada laboral(8 horas)
           cell = row.createCell(22, XSSFCell.CELL_TYPE_NUMERIC);
           cell.setCellValue(20);

           //Costo TE Extra
           cell = row.createCell(23, XSSFCell.CELL_TYPE_NUMERIC);
           cell.setCellValue(40);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al Crear datos: Error 10: "+ e);
        }
    }
   
}
