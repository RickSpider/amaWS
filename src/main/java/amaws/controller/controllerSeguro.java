/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amaws.controller;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author BlackSpider
 */


@RestController
@EnableResourceServer
public class controllerSeguro {
    
    @Autowired
    DataSource dataSource;
    
    @PreAuthorize("hasAuthority('AMA_CLIENT')")
    @RequestMapping(value="/hello")
    public String greeting() {
        return ("hello fucking mundo");
    }
   
}
