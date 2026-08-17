import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SistemaInstrumentos{
    Map<Integer, Instrumento> instrumentos;
    String archivo = "src/instrumentos.txt";
    public SistemaInstrumentos(){
        instrumentos = new HashMap<>();
        obtenerInstrumentosArchivo();
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

    /*
    El proposito de este metodo es guardar en el archivo de texto el nuevo instrumento agregado
     generando una clave aleatoria que no se repita (para esto se ocupa otro metodo)
     Se guardara el contenido en el siguiente orden: nombre, identificacion, condicion, tieneValidez, cita, forma, autores
     */
    public void guardarEnArchivo(Instrumento instrumento){
        File file = new File(archivo);
        crearArchivo(file);
        int clave = generarClave();
        try {
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter escribir = new BufferedWriter(fw);

            //Aqui es donde se agrega toda la informacion
            // falta generar la clave para identificar cada uno y poder borrar
            escribir.write(
                    clave + "," +
                    instrumento.getName() + "," +
                            instrumento.getIdentificacion() + "," +
                            instrumento.getCondicion() + "," +
                            instrumento.isTieneValidez() + "," +
                            instrumento.getCita() + "," +
                            instrumento.getForma() + "," +
                            String.join(";", instrumento.getAutores()) //Separa por ; los autores para que sean uno solo al regresar por ,
            );
            escribir.close();
            instrumentos.put(clave,instrumento);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int generarClave(){
        Random random = new Random();
        int clave = 0;
        do{
            clave = random.nextInt(676767);
        }while(instrumentos.containsKey(clave));
        return clave;
    }

    private void crearArchivo(File file){
        //Verificar que exista el archivo o crearlo
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Este metodo se usa para agregar todos los instrumentos ya creados en el archivo con anterioridad al hashmap
    private void obtenerInstrumentosArchivo(){
        instrumentos.clear();
        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader leer = new BufferedReader(fr);
            String linea;

            while ((linea = leer.readLine()) != null) {
                String[] datosInstrumento = linea.split(",");
                int clave = Integer.parseInt(datosInstrumento[0]);

                //Agregar todos los datos del archivo al instrumento
                Instrumento instrumento = new Instrumento(
                        datosInstrumento[1],
                        Integer.parseInt(datosInstrumento[2]),
                        datosInstrumento[3],
                        Boolean.parseBoolean(datosInstrumento[4]),
                        datosInstrumento[5],
                        datosInstrumento[6]
                );

                //Descomponer los autores con ; para agregarlos al instrumento
                String[] autores = datosInstrumento[7].split(";");
                for (String autor : autores){
                    instrumento.anadirAutor(autor);
                }

                instrumentos.put(clave,instrumento); //Agregar instrumento al hashmap
            }

            leer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
