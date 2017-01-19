/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amaws.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

/**
 *
 * @author BlackSpider
 */
public class Alerta {
    private String username;
    private ArrayList <Pin> pins;

    public Alerta(String username) {
        this.username = username;
        this.pins = new ArrayList();
    }
    
    public Alerta(@JsonProperty("username")String username, @JsonProperty("pins")ArrayList <Pin> pins) {
        this.username = username;
        this.pins = pins;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Pin> getPins() {
        return pins;
    }

    public void setPins(ArrayList<Pin> lista) {
        this.pins = lista;
    }
    
    public void addPin(Pin p){
        this.pins.add(p);
    }
    
}
