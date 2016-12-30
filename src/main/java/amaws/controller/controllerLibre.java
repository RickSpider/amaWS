/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amaws.controller;

import amaws.model.Datos;
import amaws.model.DatosIDUS;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author BlackSpider
 */

@SpringBootApplication
@RestController
@RequestMapping("/app")
public class controllerLibre {
    
    @Autowired
    DataSource dataSource;
    
    @RequestMapping(value="/ultimodato")
    public Datos Datos() {
        Datos dt;
        DatosIDUS didus = new DatosIDUS(dataSource);
        dt = didus.UltimoDato();
        return (dt);
    }
    
}
