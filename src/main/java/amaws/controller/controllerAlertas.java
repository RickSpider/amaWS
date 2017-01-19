/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amaws.controller;

import amaws.model.Alerta;
import amaws.model.AlertaIDUS;
import amaws.model.Pin;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author BlackSpider
 */
@RestController
@RequestMapping("/ama/alerta")
public class controllerAlertas {
    
    @RequestMapping(value="/consulta", method = RequestMethod.POST)
    public Alerta consultaAlertas(@RequestBody String username) throws SQLException{
        AlertaIDUS aidus = new AlertaIDUS();
        return aidus.consultaAlertas("rick");
    }
    
    @RequestMapping(value="/insertar", method = RequestMethod.POST)
    public void InsertarAlertas(@RequestBody Alerta a) throws SQLException{
        AlertaIDUS aidus = new AlertaIDUS();
        //Pin p = new Pin("-57,66666:-25,66666",5,true,true);
        //Alerta a = new Alerta ("alk");
        //a.addPin(p);
        aidus.insertarAlerta(a);
    }
    
}
