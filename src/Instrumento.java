import java.util.ArrayList;
import java.util.List;

public class Instrumento{
    private String name;
    private int identificacion; // 1 identificar, 2 manejar
    private String condicion;
    private boolean tieneValidez;
    private  String cita;
    private  String forma;
    List<String> autores;

    public Instrumento(String name, int identificacion, String condicion, boolean tieneValidez, String cita, String forma) {
        this.name = name;
        this.identificacion = identificacion;
        this.condicion = condicion;
        this.tieneValidez = tieneValidez;
        this.cita = cita;
        this.forma = forma;
        autores = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public boolean isTieneValidez() {
        return tieneValidez;
    }

    public void setTieneValidez(boolean tieneValidez) {
        this.tieneValidez = tieneValidez;
    }

    public String getCita() {
        return cita;
    }

    public void setCita(String cita) {
        this.cita = cita;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public String toString(){
        return  null; // no se en q formato ponerlo
    }
}