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
public class orga {
    private int tarjeta;
    private String username;

    public orga(@JsonProperty("tarjeta")int tarjeta,@JsonProperty("username") String username) {
        this.tarjeta = tarjeta;
        this.username = username;
    }

    public int getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(int tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
