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
public class User {
   
    private String username;
    private String password;
    private boolean enabled;
    private boolean alertar;

    public User(@JsonProperty("username")String username, @JsonProperty("password")String password,@JsonProperty("alertar")boolean alertar ) {
        this.username = username;
        this.password = password;
        this.alertar = alertar;
        this.enabled = true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public boolean isAlertar() {
        return alertar;
    }

    public void setAlertar(boolean alertar) {
        this.alertar = alertar;
    }

    public boolean isEnabled() {
        return enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    
    
}
