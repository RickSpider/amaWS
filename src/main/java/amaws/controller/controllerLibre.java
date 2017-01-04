/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amaws.controller;

import amaws.model.Datos;
import amaws.model.DatosIDUS;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
        DatosIDUS didus = new DatosIDUS(dataSource);
        return didus.UltimoDato();
    }
    
    @RequestMapping(value="/consultadia" ,method = RequestMethod.POST)
    public ArrayList Datos2(@RequestBody String fecha) throws ParseException {
        DatosIDUS didus = new DatosIDUS(dataSource);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        
        date = sdf.parse(fecha);
        
        return didus.consultaDia(date);
       
    }
    
    @RequestMapping(value="/consultamomento", method = RequestMethod.POST)
    public @ResponseBody Datos momento(@RequestBody String fecha) throws ParseException {
        DatosIDUS didus = new DatosIDUS(dataSource);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        
        date = sdf.parse(fecha);
       
        return didus.consultaMomento(date);
    }
    
    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="pasaste mal la fecha")
    @ExceptionHandler(ParseException.class)
    public void numberFormatHandler(){
        //logger.log(Level.ERROR, "NumberFormatException!!!");
        System.out.println("error de parse loco");
    }
    
}
