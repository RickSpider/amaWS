/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amaws.controller;

import amaws.model.User;
import amaws.model.UserIDUS;
import java.sql.SQLException;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/app/user")
public class ControllerUser {
    
    @RequestMapping(value="/existe", method = RequestMethod.POST)
    public boolean existeUser(@RequestBody String username) throws SQLException{
        UserIDUS uidus = new UserIDUS();
        return uidus.existeUser(username);
    }
    
    //@ResponseStatus(value=HttpStatus.OK, reason="Insercion Correcta")
    @RequestMapping(value="/insertar", method = RequestMethod.POST)
    public void insertarUser(@RequestBody User u) throws SQLException{
        UserIDUS uidus =  new UserIDUS();
        uidus.insertarUser(u);
    } 
    
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Error de Conexion con DB")
    @ExceptionHandler(SQLException.class)
    public void sqlException(){
        //logger.log(Level.ERROR, "NumberFormatException!!!");
        System.out.println("Error de Conexion a la DB");
    }
    
    
}
