/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amaws.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;

/**
 *
 * @author BlackSpider
 */

@JsonPropertyOrder({"dBZ","coordenadas"})
public class Valores {
    
    private double dbz;
    private ArrayList <String> coordenadas;

    public Valores(double intensidad) {
        this.dbz = intensidad;
        this.coordenadas = new ArrayList();
    }

    public double getdBZ() {
        return dbz;
    }

    public void setdBZ(double dbz) {
        this.dbz = dbz;
    }

    public ArrayList<String> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenada) {
        this.coordenadas.add(coordenada);
    }
    
    
    
    
}
