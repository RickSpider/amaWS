/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amaws.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author BlackSpider
 */
public class Pin {
    private String coordenada;
    private int radio;
    private boolean avisar;
    private boolean enabled;
    //private String lugar;

    public Pin(@JsonProperty("coordenada")String coordenada, @JsonProperty("radio")int radio, @JsonProperty("avisar")boolean avisar, @JsonProperty("enabled")boolean enabled) {
        this.coordenada = coordenada;
        this.radio = radio;
        this.avisar = avisar;
        this.enabled = enabled;
    }

    public String getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(String coordenada) {
        this.coordenada = coordenada;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    public boolean isAvisar() {
        return avisar;
    }

    public void setAvisar(boolean avisar) {
        this.avisar = avisar;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

   
}
