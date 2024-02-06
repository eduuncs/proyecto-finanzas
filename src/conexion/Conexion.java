package conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
public class Conexion {
    public static Connection conectar(){
        Connection con=null;
        try{
           con= DriverManager.getConnection("jdbc:mysql://localhost/finanzas","finanza","11111111");
           
        }catch(SQLException e){
            System.err.println("Error en la conexion a la base de datos "+e);
        }
        return con;
    }
    
    
}
