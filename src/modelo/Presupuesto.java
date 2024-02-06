
package modelo;
import java.time.LocalDate;

public class Presupuesto {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double presupuestoDinero;

    public Presupuesto(LocalDate fechaInicio, LocalDate fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.presupuestoDinero = 0;
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

    public double getPresupuestoDinero() {
        return presupuestoDinero;
    }

    public void setPresupuestoDinero(double presupuestoDinero) {
        this.presupuestoDinero = presupuestoDinero;
    }

    @Override
    public String toString() {
        return "Presupuesto{" + "fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", presupuestoDinero=" + presupuestoDinero + '}';
    }
    
    
}
