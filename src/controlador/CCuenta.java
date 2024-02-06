
package controlador;

import conexion.Conexion;
import java.util.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.*;


public class CCuenta {
    public List<Cuenta> encontrarCuentas(String dni){
        List<Cuenta> res=new ArrayList<>();
        Connection conn=Conexion.conectar();
        PreparedStatement pst;
        String sql="SELECT * FROM cuenta WHERE codigo NOT IN(Select cod_cuenta FROM cuenta_persona WHERE cod_persona=?);";
        ResultSet rs;
        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1,dni);
            rs=pst.executeQuery();
            while(rs.next()){
                Cuenta c=new Cuenta(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4));
                res.add(c);
            }
            conn.close();
        }catch(SQLException e){
            System.err.println(e);
        }
        return res;
    }
    public boolean relacionarCuenta(Cuenta cuenta,String dni){
        boolean res=false;
        int num=0;
        Connection conn=Conexion.conectar();
        PreparedStatement pst;
        String sql="INSERT INTO cuenta_persona(cod_cuenta,cod_persona) VALUES(?,?);";
        try{
            pst=conn.prepareStatement(sql);
            pst.setInt(1, cuenta.getCodigo());
            pst.setString(2,dni);
            num=pst.executeUpdate();
            if(num!=0){
                res=true;
            }
            conn.close();
        }catch(SQLException e){
            System.err.println(e);
        }
        return res;
    }
    public boolean ingresarCuenta(Cuenta activo,String dni){
        boolean res=false;
        Connection conn=Conexion.conectar();
        PreparedStatement pst;
        String sql="INSERT INTO cuenta(banco,descripcion,dinero) VALUES(?,?,?);";
        String sql1="INSERT INTO cuenta_persona(cod_cuenta,cod_persona) VALUES(?,?);";
        String aux="SELECT codigo FROM cuenta WHERE banco=? AND descripcion=? AND dinero=? ;";
        ResultSet rs;
        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1, activo.getBanco());
            pst.setString(2, activo.getDescripcion());
            pst.setDouble(3, activo.getDinero());
            int num=0;
            num=pst.executeUpdate();
            
            if(num!=0){
                pst=conn.prepareStatement(aux);
                pst.setString(1, activo.getBanco());
                pst.setString(2, activo.getDescripcion());
                pst.setDouble(3, activo.getDinero());
                rs=pst.executeQuery();
                if(rs.next()){
                   pst=conn.prepareStatement(sql1);
                   pst.setInt(1, rs.getInt(1));
                   pst.setString(2, dni);
                   num=0;
                   num=pst.executeUpdate();
                   if(num!=0){
                       res=true;
                   }
                }
                
            }
            
            conn.close();
        }catch(SQLException e){
            System.err.println(e);
        }
        return res;
    }
}
