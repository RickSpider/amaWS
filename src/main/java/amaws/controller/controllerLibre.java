/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amaws.controller;

import amaws.model.Datos;
import amaws.model.DatosIDUS;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.NestedServletException;

/**
 *
 * @author BlackSpider
 */

@RestController
@RequestMapping(value = "/ama")
public class controllerLibre {
    
    
    @RequestMapping(value="/ultimodato")
    public Datos Datos() throws SQLException {
        DatosIDUS didus = new DatosIDUS();
        return didus.UltimoDato();
    }
    
    @RequestMapping(value="/consultadia" ,method = RequestMethod.POST)
    public ArrayList Datos2(@RequestBody String fecha) throws ParseException, SQLException {
        DatosIDUS didus = new DatosIDUS();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        
        date = sdf.parse(fecha);
        
        return didus.consultaDia(date);
       
    }
    
    @RequestMapping(value="/consultamomento", method = RequestMethod.POST)
    public @ResponseBody Datos momento(@RequestBody String fecha) throws ParseException, SQLException {
        DatosIDUS didus = new DatosIDUS();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = new Date();
        
        date = sdf.parse(fecha);
       
        return didus.consultaMomento(date);
    }
    
    @RequestMapping(value="/insertar", method = RequestMethod.POST)
    public void insertar(@RequestBody Datos d) throws ParseException, SQLException {
        DatosIDUS didus = new DatosIDUS();
        didus.insertarDato(d);
    }
    
    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="pasaste mal la fecha")
    @ExceptionHandler(ParseException.class)
    public void numberFormatHandler(){
        //logger.log(Level.ERROR, "NumberFormatException!!!");
        System.out.println("error de parse loco");
    }
    
     @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Error de Conexion con DB")
    @ExceptionHandler(SQLException.class)
    public void sqlException(){
        //logger.log(Level.ERROR, "NumberFormatException!!!");
        System.out.println("Error de Conexion a la DB");
    }
    
    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="el parametro enviado es no valido")
    @ExceptionHandler( HttpMessageNotReadableException.class)
    public void  HttpMessageNotReadableException(){
        //logger.log(Level.ERROR, "NumberFormatException!!!");
        System.out.println(" error HttpMessageNotReadableException");
    }

   
    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="el parametro enviado es no valido")
    @ExceptionHandler(NestedServletException.class)
    public void NestedServletException(){
        //logger.log(Level.ERROR, "NumberFormatException!!!");
        System.out.println("eroor NestedServletException ");
    }
    
       
}
