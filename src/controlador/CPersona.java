package controlador;

import java.sql.*;
import conexion.Conexion;
import javax.swing.JOptionPane;
import modelo.Persona;

public class CPersona {
    public Persona loginPersona(Persona p) {
        Persona res=null;
        Connection conn= Conexion.conectar();
        String sql="SELECT * FROM persona WHERE codigo=? and pass=? ;";
        PreparedStatement pst;
        ResultSet rs=null;
        try {
            pst=conn.prepareStatement(sql);
            pst.setString(1,p.getDni());
            pst.setString(2,p.getContrasenia());
            rs=pst.executeQuery();
            if(rs.next()){
                res=new Persona(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error al iniciar sesion "+ex);
            JOptionPane.showMessageDialog(null, "Error al iniciar sesion");
        }
        return res;
    }
    public boolean nuevaPersona(Persona p){
        boolean res=false;
        Connection conn= Conexion.conectar();
        String sql="INSERT INTO persona VALUES(?,?,?,?,?);";
        PreparedStatement pst;
        int numero=0;
        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1, p.getDni());
            pst.setString(2,p.getContrasenia());
            pst.setString(3, p.getNombre());
            pst.setString(4,p.getApellido1());
            pst.setString(5,p.getApellido2());
            numero=pst.executeUpdate();
            if(numero!=0){
                res=true;
            }
            conn.close();
        }catch(SQLException e){
            System.out.println("Error al ingresar un nuevo usuario "+e);
            
        }
        return res;
    }
}
