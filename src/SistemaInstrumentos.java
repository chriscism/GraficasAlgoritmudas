import java.util.HashMap;
import java.util.Map;

public class SistemaInstrumentos{
    Map<Integer, Instrumento> instrumentos;

    public SistemaInstrumentos(){
        instrumentos = new HashMap<>();
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




}
