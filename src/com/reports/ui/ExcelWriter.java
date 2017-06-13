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
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ASUS
 */
public class ExcelWriter {
    
    String titles[]= new String[]{"AREA", "Empresa", "ID", "NOMBRE DEL EMPLEADO", "TE", "L","M", "M", "J", "V","S", "D",
                                    "PD", "DT", "Vac", "Faltas", "FET", "Comedor",  "Bono", "Observaciones"};
    XSSFWorkbook libroTrabajo;
    public ExcelWriter(){
        
    }
    
    public void createConsolidado(String file, ArrayList<RowDTO> listRows){
        //nombre del archivo de Excel
        if(!file.endsWith(".xlsx")){
            file= file+".xlsx";
        }
        String nombreHoja1 = "Hoja1";//nombre de la hoja1

        libroTrabajo = new XSSFWorkbook();
        XSSFSheet hoja1 = libroTrabajo.createSheet(nombreHoja1) ;
        for (int r=0;r < listRows.size()+3; r++ ) {
            RowDTO dto;
            XSSFRow row = hoja1.createRow(r);
            if(r==0){
                dto= listRows.get(0);
                setDateTOFile(row, hoja1 ,dto.getNameFile());
            }else if(r==2){
                fillTitles(row, libroTrabajo);
            }else if(r>2){
                dto= listRows.get(r-3);
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
            JOptionPane.showMessageDialog(null, "Error al guardar archivo: Error 9:"+e);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al crear archivo: Error 15:"+e);
        }
    }
    
    public void setDateTOFile(XSSFRow row, XSSFSheet hoja, String fecha){
        XSSFCell cell = row.createCell( 5, XSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(fecha);

        hoja.addMergedRegion(new CellRangeAddress(
                0, //first row (0-based)
                1, //last row  (0-based)
                5, //first column (0-based)
                8  //last column  (0-based)
        ));
        
    }
    public void fillTitles(XSSFRow row, XSSFWorkbook hoja1){
        //Headers
        for( int i=0;i < titles.length; i++){
            String header= titles[i];
            XSSFCell cell = row.createCell(i, XSSFCell.CELL_TYPE_STRING);
            
            XSSFCellStyle estiloCelda = hoja1.createCellStyle();
            estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            estiloCelda.setFillBackgroundColor((short)30);
            cell.setCellStyle(estiloCelda);
            cell.setCellValue(header);
        }
    }
    
    public XSSFCellStyle getStyleCenterCell(XSSFWorkbook book){
        XSSFCellStyle estiloCelda = book.createCellStyle();
        estiloCelda.setWrapText(true);
        estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        estiloCelda.setFillBackgroundColor((short)10);
        return estiloCelda;
    }
    public void fillRow(XSSFRow row, RowDTO dto){
        int line=0;
        try{
           line=row.getRowNum()+1;
            //Area
           XSSFCell cell = row.createCell(0, XSSFCell.CELL_TYPE_STRING);
           cell.setCellStyle(getStyleCenterCell(libroTrabajo));
           cell.setCellValue(dto.getArea());

           //Empresa
           cell = row.createCell(1, XSSFCell.CELL_TYPE_STRING);
           cell.setCellStyle(getStyleCenterCell(libroTrabajo));
           cell.setCellValue(dto.getEmpresa());

           //Id
           cell = row.createCell(2, XSSFCell.CELL_TYPE_STRING);
           cell.setCellValue(dto.getNumero());

           //Nombre
           cell = row.createCell(3, XSSFCell.CELL_TYPE_STRING);
           cell.setCellValue(dto.getNombre());

           //TE
           cell = row.createCell(4, XSSFCell.CELL_TYPE_STRING);
           cell.setCellStyle(getStyleCenterCell(libroTrabajo));
           cell.setCellValue(Double.parseDouble(dto.getTiempoExtra().isEmpty()?"0" :dto.getTiempoExtra()));

           //Lunes
           cell = row.createCell(5, XSSFCell.CELL_TYPE_STRING);
           cell.setCellStyle(getStyleCenterCell(libroTrabajo));
           cell.setCellValue(dto.getLunes());

           //Martes
           cell = row.createCell(6, XSSFCell.CELL_TYPE_STRING);
           cell.setCellStyle(getStyleCenterCell(libroTrabajo));
           cell.setCellValue(dto.getMartes());

           //Miercoles
           cell = row.createCell(7, XSSFCell.CELL_TYPE_STRING);
           cell.setCellStyle(getStyleCenterCell(libroTrabajo));
           cell.setCellValue(dto.getMiercoles());

           //Jueves
           cell = row.createCell(8, XSSFCell.CELL_TYPE_STRING);
           cell.setCellStyle(getStyleCenterCell(libroTrabajo));
           cell.setCellValue(dto.getJueves());

           //Viernes
           cell = row.createCell(9, XSSFCell.CELL_TYPE_STRING);
           cell.setCellStyle(getStyleCenterCell(libroTrabajo));
           cell.setCellValue(dto.getViernes());

           //Sabado
           cell = row.createCell(10, XSSFCell.CELL_TYPE_STRING);
           cell.setCellStyle(getStyleCenterCell(libroTrabajo));
           cell.setCellValue(dto.getSabado());

           //Domingo
           cell = row.createCell(11, XSSFCell.CELL_TYPE_STRING);
           cell.setCellStyle(getStyleCenterCell(libroTrabajo));
           cell.setCellValue(dto.getDomingo());

           //PD
           cell = row.createCell(12, XSSFCell.CELL_TYPE_NUMERIC);
           cell.setCellStyle(getStyleCenterCell(libroTrabajo));
           cell.setCellValue(Double.parseDouble(dto.getPD()));
           
           //PD
//           cell = row.createCell(12, XSSFCell.CELL_TYPE_STRING);
//           cell.setCellValue("=CONTAR.SI(L"+line+",\"A\")+CONTAR.SI(L"+line+",\"DT\")");

           //DT
           cell = row.createCell(13, XSSFCell.CELL_TYPE_NUMERIC);
           cell.setCellStyle(getStyleCenterCell(libroTrabajo));
           cell.setCellValue(Double.parseDouble(dto.getDT()));
           //DT
//           cell = row.createCell(13, XSSFCell.CELL_TYPE_STRING);
//           cell.setCellValue("=CONTAR.SI(F"+line+":L"+line+",\"DT\")+CONTAR.SI(L"+line+",\"A\")");

           //Vac
           cell = row.createCell(14, XSSFCell.CELL_TYPE_NUMERIC);
           cell.setCellStyle(getStyleCenterCell(libroTrabajo));
           cell.setCellValue(Double.parseDouble(dto.getVac()));
           //Vac
//           cell = row.createCell(14, XSSFCell.CELL_TYPE_STRING);
//           cell.setCellValue("=CONTAR.SI(F"+line+":L"+line+",\"V\")");

           //Faltas
           cell = row.createCell(15, XSSFCell.CELL_TYPE_NUMERIC);
           cell.setCellStyle(getStyleCenterCell(libroTrabajo));
           cell.setCellValue(dto.getFaltas());
//           cell.setCellValue("=CONTAR.SI(F"+line+":L"+line+",\"F\")");

           //FET
           cell = row.createCell(16, XSSFCell.CELL_TYPE_STRING);
           cell.setCellStyle(getStyleCenterCell(libroTrabajo));
           cell.setCellValue(dto.getFET());
//           cell.setCellValue("=CONTAR.SI(F"+line+":L" +line+",\"FET\")");

           //Comedor
           cell = row.createCell(17, XSSFCell.CELL_TYPE_NUMERIC);
           cell.setCellStyle(getStyleCenterCell(libroTrabajo));
           cell.setCellValue(dto.getComedor());
//           cell.setCellValue("=CONTAR.SI(F"+line+":L"+line+",\"A\")+CONTAR.SI(F"+line+":L"+line+",\"DT\")+CONTAR.SI(F"+line+":L"+line+",\"FET\")");

           //Comedor
//           cell = row.createCell(18, XSSFCell.CELL_TYPE_NUMERIC);
//           cell.setCellValue(Double.parseDouble(dto.getComedor()));

           //Bono
           cell = row.createCell(18, XSSFCell.CELL_TYPE_NUMERIC);
           cell.setCellValue(400);

           //Motivo tiempo extra
           cell = row.createCell(19, XSSFCell.CELL_TYPE_STRING);
           cell.setCellValue(dto.getObservaciones());

//           //Sueldo
//           cell = row.createCell(21, XSSFCell.CELL_TYPE_NUMERIC);
//           cell.setCellValue(100);
//
//           //Jornada laboral(8 horas)
//           cell = row.createCell(22, XSSFCell.CELL_TYPE_NUMERIC);
//           cell.setCellValue(20);
//
//           //Costo TE Extra
//           cell = row.createCell(23, XSSFCell.CELL_TYPE_NUMERIC);
//           cell.setCellValue(40);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error en la linea:"+line+": Crear datos: Error 10: "+ e);
        }
    }
   
}
