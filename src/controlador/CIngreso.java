
package controlador;
import conexion.Conexion;
import java.sql.*;
import modelo.*;


public class CIngreso {
    public boolean nuevoIngreso(Ingreso i,String dni){
        boolean res=false;
        Connection conn= Conexion.conectar();
        String query="INSERT INTO ingreso(tipo,descripcion,dinero,fecha_inicio,fecha_fin,codigo_persona,codigo_cuenta) VALUES(?,?,?,?,?,?,?);";
        String actualizar="UPDATE cuenta SET dinero=? WHERE codigo=?;";
        String dinero="SELECT dinero FROM cuenta WHERE codigo=?;";
        PreparedStatement pst;
        ResultSet rs;
        int mod=0;
        try{
            pst=conn.prepareStatement(query);
            pst.setString(1,i.getTipo());
            pst.setString(2, i.getDescripcion());
            pst.setDouble(3, i.getDinero());
            pst.setDate(4, Date.valueOf(i.getFecha_inicio()));
            pst.setDate(5, Date.valueOf(i.getFecha_fin()));
            pst.setString(6, i.getPersona());
            pst.setInt(7,i.getActivoFinanciero());
            mod=pst.executeUpdate();
            if(mod!=0){
                mod=0;
                pst=conn.prepareStatement(dinero);
                pst.setInt(1, i.getActivoFinanciero());
                rs=pst.executeQuery();
                if(rs.next()){
                    pst=conn.prepareStatement(actualizar);
                    double saldoCuenta=rs.getDouble(1);
                    saldoCuenta+=i.getDinero();
                    pst.setDouble(1, saldoCuenta);
                    pst.setInt(2,i.getActivoFinanciero());
                    mod=pst.executeUpdate();
                    if(mod!=0){
                        res=true;
                    }
                }
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return res;
    }
    public int claveActivo(String descripcion){
        int res=0;
        Connection conn=Conexion.conectar();
        PreparedStatement pst;
        String sql="SELECT codigo FROM cuenta WHERE descripcion=?;";
        ResultSet rs;
        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1, descripcion);
            
            rs=pst.executeQuery();
            if(rs.next()){
                res=rs.getInt(1);
            }
        }catch(SQLException e){
            System.err.println(e);
        }
        return res;
    }
    
}
