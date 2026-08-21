import java.lang.reflect.Array;
import java.nio.file.Path;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class SistemaInstrumentos{
    private Map<Integer, Instrumento> instrumentos;
    private Path ruta = Path.of("src/instrumentos.txt");
    public SistemaInstrumentos(){
        instrumentos = new HashMap<>();
    }

    public HashMap<Integer,Instrumento> getInstrumentos(){
        return (HashMap<Integer, Instrumento>) instrumentos;
    }
    public void actualizarInstrumentos(HashMap<Integer, Instrumento> instrumentos){
        this.instrumentos = instrumentos;
    }

    public void eliminarInstrumento(Integer clave){
        instrumentos.remove(clave);
    }


    public ArrayList<Instrumento> mostrarPorAutor(String nombreAutor){
        return instrumentos.values().stream().filter(i->i.getAutores().contains(nombreAutor)).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Instrumento> mostrarPorIdentificacion(int identificacion){
       return instrumentos.values().stream()
                .filter(i -> i.getIdentificacion() == identificacion)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Instrumento> mostrarPorForma(String forma){
       return instrumentos.values()
                .stream()
                .filter(i -> i.getForma().equals(forma))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Instrumento> mostrarPorCondicion(String condicion){
       return instrumentos.values()
                .stream()
                .filter(i -> i.getCondicion().equals(condicion))
               .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Instrumento> mostrarPorValidez(){
       return instrumentos.values()
                .stream()
                .filter(Instrumento::isTieneValidez)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    // este no le se
    public void mostrarPorClave(){
        instrumentos.forEach((clave, instrumento)-> System.out.println("Clave: "+clave+"\n"+instrumento));
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

    public ArrayList<Instrumento> mostrarPorCondicionYValidez(String condicion){
       return instrumentos.values()
                .stream()
                .filter(i -> i.getCondicion().equals(condicion) && i.isTieneValidez())
                .collect(Collectors.toCollection(ArrayList::new));
    }

//    public void eliminarInstrumentos(Integer claveAEliminar){
//        instrumentos.remove(claveAEliminar);
//        try{
//            List<String> archivoConInstrumentoBorrado=Files.lines(ruta).filter(linea->{
//                String[] lineaDeTexto=linea.split(",");
//                return lineaDeTexto.length>0 && !claveAEliminar.toString().equals(lineaDeTexto[0]);
//            }).collect(Collectors.toList());
//            Files.write(ruta, archivoConInstrumentoBorrado);
//        }catch (IOException aviso){
//            System.out.println("Error al leer el archivo: ");
//        }
//    }

    /*
    El proposito de este metodo es guardar en el archivo de texto el nuevo instrumento agregado
     generando una clave aleatoria que no se repita (para esto se ocupa otro metodo)
     Se guardara el contenido en el siguiente orden: nombre, identificacion, condicion, tieneValidez, cita, forma, autores
     */
//    public void guardarEnArchivo(Instrumento instrumento){
//        File file = new File(archivo);
//        crearArchivo(file);
//        int clave = generarClave();
//        try {
//            FileWriter fw = new FileWriter(file,true);
//            BufferedWriter escribir = new BufferedWriter(fw);
//
//            //Aqui es donde se agrega toda la informacion
//            // falta generar la clave para identificar cada uno y poder borrar
//            escribir.write(
//                    clave + "," +
//                    instrumento.getName() + "," +
//                    instrumento.getIdentificacion() + "," +
//                    instrumento.getCondicion() + "," +
//                    instrumento.isTieneValidez() + "," +
//                    instrumento.getCita() + "," +
//                    instrumento.getForma() + "," +
//                    String.join(";", instrumento.getAutores()) //Separa por ; los autores para que sean uno solo al regresar por ,
//            );
//            escribir.close();
//            instrumentos.put(clave,instrumento);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public int generarClave(){
//        Random random = new Random();
//        int clave = 0;
//        do{
//            clave = random.nextInt(676767);
//        }while(instrumentos.containsKey(clave));
//        return clave;
//    }

//    private void crearArchivo(File file){
//        //Verificar que exista el archivo o crearlo
//        try {
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    //Este metodo se usa para agregar todos los instrumentos ya creados en el archivo con anterioridad al hashmap
//    private void obtenerInstrumentosArchivo(){
//        instrumentos.clear();
//        try {
//            FileReader fr = new FileReader(archivo);
//            BufferedReader leer = new BufferedReader(fr);
//            String linea;
//
//            while ((linea = leer.readLine()) != null) {
//                String[] datosInstrumento = linea.split(",");
//                int clave = Integer.parseInt(datosInstrumento[0]);
//
//                //Agregar todos los datos del archivo al instrumento
//                Instrumento instrumento = new Instrumento(
//                        datosInstrumento[1],
//                        Integer.parseInt(datosInstrumento[2]),
//                        datosInstrumento[3],
//                        Boolean.parseBoolean(datosInstrumento[4]),
//                        datosInstrumento[5],
//                        datosInstrumento[6]
//                );
//
//                //Descomponer los autores con ; para agregarlos al instrumento
//                String[] autores = datosInstrumento[7].split(";");
//                for (String autor : autores){
//                    instrumento.anadirAutor(autor);
//                }
//
//                instrumentos.put(clave,instrumento); //Agregar instrumento al hashmap
//            }
//
//            leer.close();
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }


}
