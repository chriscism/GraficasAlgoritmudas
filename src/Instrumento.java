import java.util.ArrayList;
import java.util.List;

public class Instrumento{
    private String nombre;
    private int identificacion; // 1 identificar, 2 manejar
    private String condicion;
    private boolean tieneValidez;
    private  String cita;
    private  String forma;
    List<String> autores;

    public Instrumento(String name, int identificacion, String condicion, boolean tieneValidez, String cita, String forma) {
        this.nombre = name;
        this.identificacion = identificacion;
        this.condicion = condicion;
        this.tieneValidez = tieneValidez;
        this.cita = cita;
        this.forma = forma;
        autores = new ArrayList<>();
    }

    public void anadirAutor(String autor){
        autores.add(autor);
    }

    public String getName() {
        return nombre;
    }

    public void setName(String name) {
        this.nombre = name;
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
        String validez = tieneValidez ? "Sí" : "No";
        String autor = "";
        StringBuilder autoresStringBuilder = new StringBuilder(autor);
        for(String a:autores)
            autoresStringBuilder.append(a + ", ");

        return  "Nombre: "  + nombre + "\n"  + "Forma: " + forma + "\n"
                + "Identificación: " + identificacion + "\n" +
                "Condición: " + condicion + "\n" + "Validez: " + validez + "\n"
                + "Cita: " + cita + "\n" + "Autores: " + autoresStringBuilder.substring(0, autoresStringBuilder.length() - 2).toString();
    }

    public void ayuda(){
        System.out.println("Me banearon");
    }
}