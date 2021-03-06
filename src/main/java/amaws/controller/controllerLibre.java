/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amaws.controller;

import amaws.model.Datos;
import amaws.model.DatosIDUS;
import amaws.model.PushService;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.NestedServletException;

/**
 *
 * @author BlackSpider
 */

@RestController
@RequestMapping(value = "/libre")
public class controllerLibre {
   
   
    
    @Autowired
    PushService push;
    
    /*
    
    @RequestMapping(value="/ultimodato")
    public Datos Datos() throws SQLException {
        DatosIDUS didus = new DatosIDUS();
        Datos d = didus.UltimoDato();
        didus.cerrarConexion();
        return d;
    }
    
    @RequestMapping(value="/ultimosdiez")
    public ArrayList ultimosDiez() throws SQLException {
        DatosIDUS didus = new DatosIDUS();
        ArrayList<Datos> ultimos = didus.ultimosDiez();
        didus.cerrarConexion();
        return ultimos;
    }
    
    @RequestMapping(value="/consultadia" ,method = RequestMethod.POST)
    public ArrayList Datos2(@RequestBody String fecha) throws ParseException, SQLException {
        DatosIDUS didus = new DatosIDUS();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        date = sdf.parse(fecha);
        ArrayList <String> consultadia = didus.consultaDia(date);
        didus.cerrarConexion();
        return consultadia;       
    }
    
    
    @RequestMapping(value="/consultamomento", method = RequestMethod.POST)
    public @ResponseBody Datos momento(@RequestBody String fecha) throws ParseException, SQLException {
        DatosIDUS didus = new DatosIDUS();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = new Date();
        
        date = sdf.parse(fecha);
       
        Datos d = didus.consultaMomento(date);
        didus.cerrarConexion();
        
        return d;
    }*/
    
    @RequestMapping(value="/ultimodato")
    public Datos Datos() throws SQLException {
        DatosIDUS didus = new DatosIDUS();
        Datos d = didus.UltimoDato();
        didus.cerrarConexion();
        return d;
    }
    
    
    @RequestMapping(value="/prueba")
    public String prueba(){
        return "Soy Libre wiiii";
    } 
    
 
   @RequestMapping(value= "/notification")
   public String notificar(String token) throws ExecutionException, InterruptedException, IOException, SQLException {
       
        push.sendPushs(token);
        System.out.println(push.getPushsend());
     
        return ("enviando push");
    }
    
    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="pasaste mal la fecha")
    @ExceptionHandler(ParseException.class)
    public void numberFormatHandler(){
        //logger.log(Level.ERROR, "NumberFormatException!!!");
        System.out.println("error en el parse del dato");
    }
    
     @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Error de Base de Datos")
    @ExceptionHandler(SQLException.class)
    public void sqlException(){
        System.out.println("Error de base de datos");
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
    
    /*@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="datos nulos")
    @ExceptionHandler(NullPointerException.class)
    public void NullPointerException(){
        //logger.log(Level.ERROR, "NumberFormatException!!!");
        System.out.println("NullPointerException");
    }*/
    
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Problemas en notificacion push")
    @ExceptionHandler(IOException.class)
    public void IOException(){
        //logger.log(Level.ERROR, "NumberFormatException!!!");
        System.out.println("IOException en notificacion push");
    }
}
