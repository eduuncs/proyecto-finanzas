
package modelo;
import java.time.LocalDate;

public class Gasto {
    private int codigo;
    private String tipo;
    private String descripcion;
    private double dinero;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int activoFinanciero;
    private String persona;

    public Gasto( String tipo, String descripcion, double dinero, LocalDate fechaInicio, LocalDate fechaFin, int activoFinanciero, String persona) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.dinero = dinero;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activoFinanciero = activoFinanciero;
        this.persona=persona;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getActivoFinanciero() {
        return activoFinanciero;
    }

    public void setActivoFinanciero(int activoFinanciero) {
        this.activoFinanciero = activoFinanciero;
    }

    @Override
    public String toString() {
        return "Gasto{" + "codigo=" + codigo + ", tipo=" + tipo + ", descripcion=" + descripcion + ", dinero=" + dinero + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", activoFinanciero=" + activoFinanciero +", persona: "+ persona+'}';
    }
    
    
}