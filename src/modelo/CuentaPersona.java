package modelo;
public class CuentaPersona {
    private Cuenta activoFinanciero;
    private Persona persona;

    public CuentaPersona(Cuenta activoFinanciero, Persona persona) {
        this.activoFinanciero = activoFinanciero;
        this.persona = persona;
    }

    public Cuenta getActivoFinanciero() {
        return activoFinanciero;
    }

    public void setActivoFinanciero(Cuenta activoFinanciero) {
        this.activoFinanciero = activoFinanciero;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "ActivoFinancieroPersona{" + "activoFinanciero=" + activoFinanciero + ", persona=" + persona + '}';
    }
    
    
}
