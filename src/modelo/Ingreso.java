
package modelo;

import java.time.LocalDate;
import java.sql.Date;

public class Ingreso {
    private int codigo;
    private String tipo;
    private String descripcion;
    private double dinero;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private String  persona;
    private int  activoFinanciero;

    public Ingreso(int codigo, String tipo, String descripcion, double dinero, LocalDate fecha_inicio, LocalDate fecha_fin, String persona, int activoFinanciero) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.dinero = dinero;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.persona = persona;
        this.activoFinanciero = activoFinanciero;
    }
    public Ingreso(String tipo, String descripcion, double dinero, LocalDate fecha_inicio, LocalDate fecha_fin, String persona, int activoFinanciero) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.dinero = dinero;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.persona = persona;
        this.activoFinanciero = activoFinanciero;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getDinero() {
        return dinero;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public int getActivoFinanciero() {
        return activoFinanciero;
    }

    public void setActivoFinanciero(int activoFinanciero) {
        this.activoFinanciero = activoFinanciero;
    }

    @Override
    public String toString() {
        return "Ingreso{" + "codigo=" + codigo + ", tipo=" + tipo + ", descripcion=" + descripcion + ", dinero=" + dinero + ", fecha_inicio=" + fecha_inicio + ", fecha_fin=" + fecha_fin + ", persona=" + persona + ", activoFinanciero=" + activoFinanciero + '}';
    }
    
    
}
