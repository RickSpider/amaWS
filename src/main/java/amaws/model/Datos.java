/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amaws.model;

import java.util.ArrayList;

/**
 *
 * @author BlackSpider
 */
public class Datos {
    
    private String Fecha;
    private ArrayList arrayDatos;

    public Datos(String Fecha, ArrayList lista) {
        this.Fecha = Fecha;
        this.arrayDatos = lista;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

   public void addArrayDatos(ArrayList lista){
       this.arrayDatos = lista;
   }

    public ArrayList getArrayDatos() {
        return arrayDatos;
    }
       
    
}
