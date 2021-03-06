/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amaws.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.text.ParseException;
import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;


/**
 *
 * @author BlackSpider
 */
@JsonPropertyOrder ({"fecha","notificar","centroides","arrayDatos"})
public class Datos {
    
    private Date Fecha;
    private boolean notificar;
    private ArrayList centroides;
    private ArrayList arrayDatos;
    
   
    public Datos(@JsonProperty("fechaCarga")String FechaCarga, @JsonProperty("arrayDatos")ArrayList lista, @JsonProperty("notificar")boolean notificar, @JsonProperty("centroides")ArrayList centroides) throws ParseException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = new Date();
        date = sdf.parse(FechaCarga);
        this.Fecha = date;
        this.arrayDatos = lista;
        this.notificar = notificar;
        this.centroides = centroides;
        
    }
    
    public Datos(Date Fecha, ArrayList lista, boolean notificar, ArrayList centroides) {
        this.Fecha = Fecha;
        this.arrayDatos = lista;
        this.notificar = notificar;
        this.centroides = centroides;
        
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

    public boolean isNotificar() {
        return notificar;
    }

    public ArrayList getCentroides() {
        return centroides;
    }
    
    
    
    
}
