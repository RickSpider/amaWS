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
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 *
 * @author BlackSpider
 */
public class AlertaIDUS {
    private Connection conSQL;

    public  AlertaIDUS () throws SQLException {
        DataSource dataSource = (DataSource) AppContext.getContext().getBean("dataSource");
        
        this.conSQL = dataSource.getConnection();
    }
    
    public Alerta consultaAlertas (String username) throws SQLException{
    
        String sql = "SELECT coordenada, radio, avisar, enabled FROM alertas WHERE username = ?;";
        Object [] co = new Object [3];
        
        PreparedStatement psConsulta = this.conSQL.prepareStatement(sql);
        psConsulta.setString(1, username);
        ResultSet rs = psConsulta.executeQuery();
        Alerta al = new Alerta(username);
        
        while (rs.next()){
            co[0] = rs.getString(1) ; //coordenada
            co[1] = rs.getInt(2); //radio 
            co[2] = rs.getBoolean(3);//avisar
            Pin p =  new Pin (co[0].toString(),Integer.parseInt(co[1].toString()),(boolean) co[2],true);
            al.addPin(p);
        }
        this.conSQL.close();
        return al;
    }
    
    
    public void insertarAlerta (Alerta al) throws SQLException{
        String sql = "insert into alertas (username,coordenada, radio, avisar, enabled) values (?,?,?,?,?);";
        
        PreparedStatement ps = this.conSQL.prepareStatement(sql);
        
        for (int i = 0 ; i < al.getPins().size(); i++){
            ps.setString(1,al.getUsername());
            ps.setString(2,al.getPins().get(i).getCoordenada() );
            ps.setInt(3,al.getPins().get(i).getRadio());
            ps.setBoolean(4,al.getPins().get(i).isAvisar());
            ps.setBoolean(5,al.getPins().get(i).isEnabled());
            ps.execute();
        }
        this.conSQL.close();
    }
    
    public void updateAlerta(Alerta al) throws SQLException{
    
        String sql = "UPDATE alertas SET enabled=? WHERE username =? AND coordenada =?;";
        
        PreparedStatement ps = this.conSQL.prepareStatement(sql);
        
        for (int i = 0 ; i < al.getPins().size(); i++){
            ps.setBoolean(1,al.getPins().get(i).isEnabled());
            ps.setString(2,al.getUsername());
            ps.setString(3,al.getPins().get(i).getCoordenada() );
            ps.execute();
        }
        this.conSQL.close();
    }
    
    public void eliminarAlerta (Alerta al) throws SQLException{
        
        String sql = "DELETE FROM alertas WHERE username = ? AND coordenada = ?;";
        
        PreparedStatement ps = this.conSQL.prepareStatement(sql);
        
        for (int i = 0 ; i < al.getPins().size(); i++){
            ps.setString(1,al.getUsername());
            ps.setString(2,al.getPins().get(i).getCoordenada() );
            ps.execute();
        }
        
        this.conSQL.close();
    }
    
}
