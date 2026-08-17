import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SistemaInstrumentos{
    Map<Integer, Instrumento> instrumentos;
    private Path ruta;

    public SistemaInstrumentos(){
        instrumentos = new HashMap<>();
    }

    public void mostrarPorAutor(String nombreAutor){
        instrumentos.entrySet().stream().filter(i->i.getValue().getAutores().contains(nombreAutor)).forEach(System.out::println);
    }

    public void mostrarPorIdentificacion(int identificacion){
        instrumentos.entrySet().stream()
                .filter(i -> i.getValue().getIdentificacion() == identificacion)
                .forEach(System.out::println);
    }

    public void mostrarPorForma(String forma){
        instrumentos.entrySet()
                .stream()
                .filter(i -> i.getValue().getForma().equals(forma))
                .forEach(System.out::println);
    }

    public void mostrarPorCondicion(String condicion){
        instrumentos.entrySet()
                .stream()
                .filter(i -> i.getValue().getCondicion().equals(condicion))
                .forEach(System.out::println);
    }

    public void mostrarPorValidez(boolean validez){
        instrumentos.entrySet()
                .stream()
                .filter(i -> i.getValue().isTieneValidez() == validez)
                .forEach(System.out::println);
    }
    public void mostrarPorClave(){
        instrumentos.entrySet()
                .stream()
                .forEach(System.out::println);
    }

    public void mostrarOrdenadoPorPrimerAutor(){
        instrumentos.entrySet()
                .stream()
                .sorted((entry1, entry2) -> {
                    String autor1 = entry1.getValue().autores.isEmpty() ? "" : entry1.getValue().autores.get(0);
                    String autor2 = entry2.getValue().autores.isEmpty() ? "" : entry2.getValue().autores.get(0);
                    return autor1.compareToIgnoreCase(autor2);
                })
                .forEach(System.out::println);
    }

    public void mostrarPorCondicionYValidez(String condicion, boolean tieneValidez){
        instrumentos.entrySet()
                .stream()
                .filter(i -> i.getValue().getCondicion().equals(condicion) && i.getValue().isTieneValidez() == tieneValidez)
                .forEach(System.out::println);
    }

    public void eliminarInstrumentos(Integer claveAEliminar){
        instrumentos.remove(claveAEliminar);
        try{
            List<String> archivoConInstrumentoBorrado=Files.lines(ruta).filter(linea->{
                String[] lineaDeTexto=linea.split(",");
                return lineaDeTexto.length>0 && !claveAEliminar.toString().equals(lineaDeTexto[0]);
            }).collect(Collectors.toList());
            Files.write(ruta, archivoConInstrumentoBorrado);
        }catch (IOException aviso){
            System.out.println("Error al leer el archivo: ");
        }
    }




}
