/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amaws.model;

import amaws.model.config.AppContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.postgresql.util.PSQLException;

/**
 *
 * @author BlackSpider
 */

public class PersonaIDUS {
    
    private Connection conSQL;
    
    public PersonaIDUS() throws SQLException{
        
        //tomamos el bean de spring (el que configuramos en el AppConfig.java) 
        DataSource dataSource = (DataSource) AppContext.getContext().getBean("dataSource");
        
        this.conSQL = dataSource.getConnection();
    }
          
    public Persona consultaDatos(String username) throws PSQLException, SQLException {
        
        String sql =  "SELECT username, nombre, apellido, tkn, alertar FROM personas WHERE username = ?;";
        ResultSet rs = null;
        Object dato [] = new Object[5];
        Persona pers = null;
         
        PreparedStatement psConsulta = this.conSQL.prepareStatement(sql);
        psConsulta.setString(1,username);
        rs = psConsulta.executeQuery();
        rs.next();
        
        dato[0] = rs.getString(1);
        dato[1] = rs.getString(2);
        dato[2] = rs.getString(3);
        dato[3] = rs.getString(4);  
        dato[4] = rs.getBoolean(5);
        pers = new Persona(String.valueOf(dato[0]),String.valueOf(dato[1]),String.valueOf(dato[2]),String.valueOf(dato[3]), (Boolean) dato[4]);
        
        this.conSQL.close();
       
        return pers;   
    }
    
    public void insertarPersona (Persona p) throws SQLException {
        
        String sql = "INSERT INTO personas(username, nombre, apellido, tkn, alertar)VALUES (?, ?, ?, ?,?);";
        PreparedStatement psInsertar = this.conSQL.prepareStatement(sql);
        psInsertar.setString(1,p.getUsername());
        psInsertar.setString(2,p.getNombre());
        psInsertar.setString(3,p.getApellido());
        psInsertar.setString(4,p.getToken());
        psInsertar.setBoolean(5,p.isAlertar());
        psInsertar.execute();
        
        this.conSQL.close();
        
    }
    
    public void updatePersona (Persona p) throws SQLException{
        
        String sql = "UPDATE personas SET nombre= ?, apellido= ?, tkn = ?, alertar = ? WHERE username = ?;";
        PreparedStatement psUpdate = this.conSQL.prepareStatement(sql);
        psUpdate.setString(1,p.getNombre());
        psUpdate.setString(2,p.getApellido());
        psUpdate.setString(3,p.getToken());
        psUpdate.setBoolean(4,p.isAlertar());
        psUpdate.setString(5,p.getUsername());
        psUpdate.execute();
        
        this.conSQL.close();
        
    }
    

}
