/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amaws.controller;

import amaws.model.Persona;
import amaws.model.PersonaIDUS;
import amaws.model.orga;
import java.sql.SQLException;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author BlackSpider
 */

@RestController
@RequestMapping("/ama/persona")
public class ControllerPersona {
      
    @RequestMapping(value="/datos", method = RequestMethod.POST)
    public Persona datospersona(@RequestBody String username) throws PSQLException, SQLException {
        PersonaIDUS pidus = new PersonaIDUS();
        return pidus.consultaDatos(username);
    }
    
    @ResponseStatus(value=HttpStatus.OK, reason="Insercion Correcta")
    @RequestMapping(value="/insertar", method = RequestMethod.POST)
    public void insertar (@RequestBody Persona p) throws SQLException{
       // Persona p = new Persona ("gio","465ad4f6ads4f6asd4f");
        PersonaIDUS pidus = new PersonaIDUS();
        pidus.insertarPersona(p);
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
    
   
   
   /* @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="el parametro enviado es no valido")
    @ExceptionHandler(NestedServletException.class)
    public void NestedServletException(){
        //logger.log(Level.ERROR, "NumberFormatException!!!");
        System.out.println("eroor NestedServletException ");
    }*/
    
    @ResponseStatus(value=HttpStatus.CONFLICT, reason="La consulta echa no existe")
    @ExceptionHandler(PSQLException.class)
    public void PSQLException(){
        //logger.log(Level.ERROR, "NumberFormatException!!!");
        System.out.println("error PSQLException");
    }
    
    
   
}
