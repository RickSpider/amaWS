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

/**
 *
 * @author BlackSpider
 */
public class UserIDUS {
    
    private Connection conSQL = null;

    public UserIDUS() throws SQLException {
        
        DataSource dataSource = (DataSource) AppContext.getContext().getBean("dataSource");
        this.conSQL = dataSource.getConnection();
        
    }
      
    public boolean existeUser(String username) throws SQLException {
        //sadf
        String sql = "SELECT EXISTS (SELECT 1 FROM users WHERE username = ?);";
        ResultSet rs;
        boolean existe;
      
        PreparedStatement psConsulta = this.conSQL.prepareStatement(sql);
        psConsulta.setString(1,username);
        rs = psConsulta.executeQuery();
        rs.next();
        existe = rs.getBoolean(1);
        conSQL.close();
         
        return existe;
    }
    
    public void insertarUser(User u) throws SQLException{
        
        String sql = "INSERT INTO users (username, password, enabled, alertar) VALUES (?, ?, ?, ?);";
        PreparedStatement psInsertar = this.conSQL.prepareStatement(sql);
        psInsertar.setString(1,u.getUsername());
        psInsertar.setString(2,u.getPassword());
        psInsertar.setBoolean(3, true);
        psInsertar.setBoolean(4, u.isAlertar());
        psInsertar.execute();
        
               
        sql = "INSERT INTO authorities(username, authority)VALUES (?, ?);";
        psInsertar = this.conSQL.prepareStatement(sql);
        psInsertar.setString(1,u.getUsername());
        psInsertar.setString(2,"user");
        psInsertar.execute();
        
        this.conSQL.close();
    }
    
    public void updateUser(User u) throws SQLException{
        
        String sql = "UPDATE users SET alertar = ? WHERE  username=?;";
        PreparedStatement psUpdate = this.conSQL.prepareStatement(sql);
        psUpdate.setBoolean(1, u.isAlertar());
        psUpdate.setString(2,u.getUsername());
        psUpdate.execute();
        
    }
}
