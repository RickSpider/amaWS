/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amaws.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.ParseException;
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

    
    
    public Datos(@JsonProperty("fechaCarga")String FechaCarga, @JsonProperty("arrayDatos")ArrayList lista) throws ParseException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = new Date();
        date = sdf.parse(FechaCarga);
        this.Fecha = date;
        this.arrayDatos = lista;
        
    }
    
    public Datos(Date Fecha, ArrayList lista) {
        this.Fecha = Fecha;
        this.arrayDatos = lista;
        
    }

    public void setArrayDatos(ArrayList arrayDatos) {
        this.arrayDatos = arrayDatos;
    }

    public String getFecha() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return sdf.format(Fecha);
        
    }
    
    public Date pasarDate(){
        return this.Fecha;
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
