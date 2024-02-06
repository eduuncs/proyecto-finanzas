
package modelo;

public class Cuenta {
    private int codigo;
    private String banco;
    private String descripcion;
    private double dinero;
    
    public Cuenta(String banco,String descripcion,double dinero){
        this.banco=banco;
        this.dinero=dinero;
        this.descripcion=descripcion;
    }
    public Cuenta(int codigo, String banco,String descripcion,double dinero){
        this.codigo=codigo;
        this.banco=banco;
        this.dinero=dinero;
        this.descripcion=descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public double getDinero() {
        return dinero;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    @Override
    public String toString() {
        return "ActivoFinanciero{" + "codigo=" + codigo + ", banco=" + banco + ", descripcion=" + descripcion + ", dinero=" + dinero + '}';
    }

    
}
