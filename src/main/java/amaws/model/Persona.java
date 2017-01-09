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
public class Persona {
    private String username;
    private String nombre;
    private String apellido;
    private String token;
    //@JsonProperty("actividadid")
    public Persona(@JsonProperty("username")String username, @JsonProperty("nombre")String nombre, @JsonProperty("apellido")String apellido, @JsonProperty("token")String token) {
        this.username = username;
        this.nombre = nombre;
        this.apellido = apellido;
        this.token = token;
    }
    
   /* public Persona(@JsonProperty("username")String username,@JsonProperty("token")String token){
        this.username = username;
        this.token = token;
        this.nombre = null;
        this.apellido = null;
    }*/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
       
}
