/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amaws.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author BlackSpider
 */
public class DatosIDUS {
    
    private final DataSource dataSouce;

    public DatosIDUS(DataSource dataSouce) {
        this.dataSouce = dataSouce;
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
                  if (lista2.get(j).getIntensidad() == Integer.parseInt(dato[0])){
                       lista2.get(j).setCoordenadas(dato[1]);
                       ban = true;
                       j = lista2.size();
                  }
              }
              
              if (!ban){
                  Valores va = new Valores(Integer.parseInt(dato[0]));
                  va.setCoordenadas(dato[1]);
                  lista2.add(va);
              }
              
          }else{
              Valores va = new Valores(Integer.parseInt(dato[0]));
              va.setCoordenadas(dato[1]);
              lista2.add(va);
          }
          
        }
        
        return lista2;
    }
    
    public Datos UltimoDato(){
        String sql = "SELECT fecha, data FROM datos ORDER BY fecha DESC LIMIT 1;";
        Object [] co = new Object [2];
        ResultSet rs;
        ArrayList <String> lista = new ArrayList();
        Datos dt;
        try{
               PreparedStatement psConsulta = this.dataSouce.getConnection().prepareStatement(sql);
               rs = psConsulta.executeQuery();
               rs.next();
               
               co[0] = rs.getTimestamp(1);
               co[1] = rs.getString(2);
               
        } catch (SQLException ex) {
            Logger.getLogger(DatosIDUS.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error en el try del preparedstatement loco!!!!!!!!!!!!!!!");
        }
        
        lista = conversion(String.valueOf(co[1].toString()));
        
        dt = new Datos ((Date) co[0],compresionDatos(lista));
        return dt;
        
    }
    
    public ArrayList consultaDia(Date Fecha){
        String sql = "SELECT fecha FROM datos WHERE date(fecha) = ? ORDER BY fecha ASC;";
        ResultSet rs;
        ArrayList <String>  lista = new ArrayList();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
        try{
               PreparedStatement psConsulta = this.dataSouce.getConnection().prepareStatement(sql);
               psConsulta.setDate(1, new java.sql.Date(Fecha.getTime()));
               rs = psConsulta.executeQuery();
                             
               while(rs.next()){
                   lista.add(sdf.format(rs.getTimestamp(1)));
               }
               
        } catch (SQLException ex) {
            Logger.getLogger(DatosIDUS.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error en el try del preparedstatement loco!!!!!!!!!!!!!!!");
        }
        
        return lista;
    }
    
    public Datos consultaMomento(Date Fecha){
        String sql = "SELECT fecha, data FROM datos WHERE fecha = ?";
        Object [] co = new Object [2];
        ResultSet rs;
        ArrayList <String> lista = new ArrayList();
        Datos dt;
        try{
               PreparedStatement psConsulta = this.dataSouce.getConnection().prepareStatement(sql);
               psConsulta.setTimestamp(1, new java.sql.Timestamp(Fecha.getTime()));
               rs = psConsulta.executeQuery();
               rs.next();
               
               co[0] = rs.getTimestamp(1);
               co[1] = rs.getString(2);
               
        } catch (SQLException ex) {
            Logger.getLogger(DatosIDUS.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error en el try del preparedstatement loco!!!!!!!!!!!!!!!");
        }
        
        lista = conversion(String.valueOf(co[1].toString()));
       
        dt = new Datos ((Date) co[0],compresionDatos(lista));
        return dt;
    }
    
}
