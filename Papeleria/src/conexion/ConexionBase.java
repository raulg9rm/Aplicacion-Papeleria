/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import vista.Datos;
import vista.Login;

/**
 *
 * @author Raul Garcia
 */
public class ConexionBase {
    Datos dat;
    private static String nombreBD;
    private static String usuario;
    private static String passw;
    private static String url;
    private Connection conn;
    private ResultSet cdr;
    private Statement statement;
    private CallableStatement sentencia;
    private static ConexionBase instance;
    
    
    public ConexionBase() {
        nombreBD = "papeleria";
        usuario = "root";
        passw =null;
        url = "jdbc:mysql://localhost/"+nombreBD;
        conectarBD();
    }
    
    public void conectarBD(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn=DriverManager.getConnection(url, usuario, passw);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No encontro la clase Driver" + ex, "ERROR", JOptionPane.ERROR_MESSAGE );
        } catch (InstantiationException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo crear la instancia", "ERROR", JOptionPane.ERROR_MESSAGE );
        } catch (IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, "Acceso Denegado", "ERROR", JOptionPane.ERROR_MESSAGE );
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se encontro la BD", "ERROR", JOptionPane.ERROR_MESSAGE );
        }
    }
    
    public static Connection getConexion(){
            Connection cn=null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                cn = DriverManager.getConnection(url,usuario,passw);
            }
            catch (Exception e){
                System.out.println(String.valueOf(e));
            }
            return cn;
    }
    
    
    public void ejecutarABC(ArrayList instruccionBD){
        try {
            sentencia = conn.prepareCall(instruccionBD.get(0).toString());
            for (int i=1; i<instruccionBD.size(); i++){
                sentencia.setString(i, instruccionBD.get(i).toString());
            }
            sentencia.execute();
        } catch (SQLException ex) {
            
        }
    }
    
    public ResultSet ejecutarConsula (ArrayList instruccionBD){
        try {
            sentencia=conn.prepareCall(instruccionBD.get(0).toString());
            for (int i=1; i<instruccionBD.size(); i++){
                sentencia.setString(i,instruccionBD.get(i).toString());
            }
            cdr=sentencia.executeQuery();
        } catch (SQLException ex) {
            
        }
        return cdr;
    }
    
    public static ConexionBase getInstance(){
        
        if(instance == null){
            instance = new ConexionBase();
        }
        return instance;
    }
    
    public ResultSet consultar(String select){
        ResultSet datos;
        try{
            
            statement=conn.createStatement();
            datos=statement.executeQuery(select);
            //close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getErrorCode()+"-"+ex.getMessage(),"Error de conexion",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return datos;
    }
    
    public static ResultSet getTabla(String consulta){
        Connection cn=getConexion();
        Statement st;
        ResultSet datos=null;
        try {
            st=cn.createStatement();
            datos=st.executeQuery(consulta);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return datos;
    }
}
