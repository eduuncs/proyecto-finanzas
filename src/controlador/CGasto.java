package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Gasto;

public class CGasto {

    public boolean ingresarGasto(Gasto gasto) {
        boolean res = false;
        Connection conn = Conexion.conectar();
        PreparedStatement pst;
        String sql = "INSERT INTO gasto(tipo,descripcion,dinero,fecha_inicio,fecha_fin,codigo_cuenta,codigo_persona) VALUES(?,?,?,?,?,?,?);";
        String actualizar = "UPDATE cuenta SET dinero= ? WHERE codigo=?;";
        String dinero = "SELECT dinero FROM cuenta WHERE codigo=?;";
        ResultSet rs;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, gasto.getTipo());
            pst.setString(2, gasto.getDescripcion());
            pst.setDouble(3, gasto.getDinero());
            pst.setDate(4, Date.valueOf(gasto.getFechaInicio()));
            pst.setDate(5, Date.valueOf(gasto.getFechaFin()));
            pst.setInt(6, gasto.getActivoFinanciero());
            pst.setString(7, gasto.getPersona());
            int num = 0;
            num = pst.executeUpdate();
            if (num != 0) {
                num = 0;
                pst = conn.prepareStatement(dinero);
                double saldoActual = 0;
                pst.setInt(1, gasto.getActivoFinanciero());
                rs = pst.executeQuery();
                if (rs.next()) {
                    saldoActual = rs.getDouble(1);
                    saldoActual -= gasto.getDinero();
                    pst = conn.prepareStatement(actualizar);
                    pst.setDouble(1, saldoActual);
                    pst.setInt(2, gasto.getActivoFinanciero());
                    num = pst.executeUpdate();
                    if (num != 0) {
                        res = true;
                    }
                }
            }
            conn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return res;
    }

    public int claveCuenta(String descripcion) {
        int res = 0;
        Connection conn = Conexion.conectar();
        PreparedStatement pst;
        String sql = "SELECT codigo FROM cuenta WHERE descripcion=?;";
        ResultSet rs;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, descripcion);

            rs = pst.executeQuery();
            if (rs.next()) {
                res = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return res;
    }
}
