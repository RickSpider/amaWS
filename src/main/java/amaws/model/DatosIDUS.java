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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import javax.sql.DataSource;
import org.postgresql.util.PSQLException;

/**
 *
 * @author BlackSpider
 */
public class DatosIDUS {
    
   private Connection conSQL;

    public DatosIDUS() throws SQLException {
        DataSource dataSource = (DataSource) AppContext.getContext().getBean("dataSource");
        
        this.conSQL = dataSource.getConnection();
    }
    
    private ArrayList conversion(String txt){
        
        ArrayList lista = new ArrayList();
        
        String expresion = "[^0-9.;:-]";
        
        for(String txt2 : txt.split(expresion)){
            //System.out.println(txt2);
            if (!txt2.equals("")){
                lista.add(txt2);
            }
        }
        
        return lista;
    }
    
    //compresion de datos
    private ArrayList compresionDatos (ArrayList <String> lista){
        
        ArrayList <Valores> lista2 = new ArrayList();
        
        for (int i = 0 ; i<lista.size(); i++){
          String [] dato = lista.get(i).split("\\;");
          if (lista2.size() > 0){
             boolean ban = false;
              for (int j=0 ; j<lista2.size();j++){
                  if (lista2.get(j).getIntensidad() == Double.parseDouble(dato[0])){
                       lista2.get(j).setCoordenadas(dato[1]);
                       ban = true;
                       j = lista2.size();
                  }
              }
              
              if (!ban){
                  Valores va = new Valores(Double.parseDouble(dato[0]));
                  va.setCoordenadas(dato[1]);
                  lista2.add(va);
              }
              
          }else{
              Valores va = new Valores(Double.parseDouble(dato[0]));
              va.setCoordenadas(dato[1]);
              lista2.add(va);
          }
          
        }
        
        Collections.sort(lista2, new Comparator <Valores>() {
            
            @Override
            public int compare(Valores v1, Valores v2) {
                    return new Double(v1.getIntensidad()).compareTo(v2.getIntensidad());
            }
          
        });
          
        return lista2;
    }
    
    public Datos UltimoDato() throws PSQLException,SQLException{
        String sql = "SELECT fecha, data FROM datos ORDER BY fecha DESC LIMIT 1;";
        Object [] co = new Object [2];
        ResultSet rs;
        ArrayList <String> lista = new ArrayList();
        Datos dt;
       
        PreparedStatement psConsulta = this.conSQL.prepareStatement(sql);
        rs = psConsulta.executeQuery();
        rs.next();
               
        co[0] = rs.getTimestamp(1);
        co[1] = rs.getString(2);
 
        lista = conversion(String.valueOf(co[1].toString()));
        
        dt = new Datos ((Date) co[0],compresionDatos(lista));
        
        this.conSQL.close();
        
        return dt;
        
    }
    
    public ArrayList consultaDia(Date Fecha) throws PSQLException,SQLException{
        String sql = "SELECT fecha FROM datos WHERE date(fecha) = ? ORDER BY fecha ASC;";
        ResultSet rs;
        ArrayList <String>  lista = new ArrayList();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
        
        PreparedStatement psConsulta = this.conSQL.prepareStatement(sql);
        psConsulta.setDate(1, new java.sql.Date(Fecha.getTime()));
        rs = psConsulta.executeQuery();
                             
        while(rs.next()){
            lista.add(sdf.format(rs.getTimestamp(1)));
        }
        
        this.conSQL.close();
        return lista;
    }
    
    public Datos consultaMomento(Date Fecha) throws PSQLException,SQLException{
        String sql = "SELECT fecha, data FROM datos WHERE fecha = ?";
        Object [] co = new Object [2];
        ResultSet rs;
        ArrayList <String> lista = new ArrayList();
        Datos dt;
        //try{
        PreparedStatement psConsulta = this.conSQL.prepareStatement(sql);
        psConsulta.setTimestamp(1, new java.sql.Timestamp(Fecha.getTime()));
        rs = psConsulta.executeQuery();
        rs.next();
               
        co[0] = rs.getTimestamp(1);
        co[1] = rs.getString(2);
               
        lista = conversion(String.valueOf(co[1].toString()));
       
        dt = new Datos ((Date) co[0],compresionDatos(lista));
        
        this.conSQL.close();
        return dt;
    }

    public void insertarDato (Datos d) throws SQLException {
        
        ArrayList <String> lista = d.getArrayDatos();
        StringBuffer carga = new StringBuffer("");
        
        for (String x : lista){
            carga.append("["+x+"]");
        }
        
        String sql = "INSERT INTO datos(fecha, data) VALUES (?, ?);";
        PreparedStatement psInsertar = this.conSQL.prepareStatement(sql);
        psInsertar.setTimestamp(1, new java.sql.Timestamp(d.pasarDate().getTime()));
        psInsertar.setString(2,carga.toString());
        psInsertar.execute();
        
        this.conSQL.close();
        
    }
  
}
