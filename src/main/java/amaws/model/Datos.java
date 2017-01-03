/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amaws.model;

import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;


/**
 *
 * @author BlackSpider
 */
public class Datos {
    
    private Date Fecha;
    private ArrayList arrayDatos;

    public Datos(Date Fecha, ArrayList lista) {
        this.Fecha = Fecha;
        this.arrayDatos = lista;
        
    }

    public String getFecha() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(Fecha);
        
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

   public void addArrayDatos(ArrayList lista){
       this.arrayDatos = lista;
   }

    public ArrayList getArrayDatos() {
        return arrayDatos;
    }
       
    
}
