
package modelo;



public class GastoPersona {
    private Gasto gasto;
    private Persona persona;

    public GastoPersona(Gasto gasto, Persona persona) {
        this.gasto = gasto;
        this.persona = persona;
    }

    public Gasto getGasto() {
        return gasto;
    }

    public void setGasto(Gasto gasto) {
        this.gasto = gasto;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "GastoPersona{" + "gasto=" + gasto + ", persona=" + persona + '}';
    }
    
}
