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
public class Valores {
    
    private int intensidad;
    private ArrayList <String> coordenadas;

    public Valores(int intensidad) {
        this.intensidad = intensidad;
        this.coordenadas = new ArrayList();
    }

    public int getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(int intensidad) {
        this.intensidad = intensidad;
    }

    public ArrayList<String> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenada) {
        this.coordenadas.add(coordenada);
    }
    
    
    
    
}
