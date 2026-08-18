import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GestorArchivo {
    private Path ruta = Path.of("src/instrumentos.txt");
    private String archivo;

    public GestorArchivo(){
        archivo = "src/instrumentos.txt";
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

    /*
    El proposito de este metodo es guardar en el archivo de texto el nuevo instrumento agregado
     generando una clave aleatoria que no se repita (para esto se ocupa otro metodo)
     Se guardara el contenido en el siguiente orden: nombre, identificacion, condicion, tieneValidez, cita, forma, autores
     */
    public HashMap<Integer,Instrumento> guardarInstrumentos(HashMap<Integer,Instrumento> instrumentos, Instrumento instrumento){
        File file = new File(archivo);
        crearArchivo(file);
        int clave = generarClave(instrumentos);
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
        return instrumentos;
    }

    public int generarClave(HashMap<Integer,Instrumento> instrumentos){
        Random random = new Random();
        int clave = 0;
        do{
            clave = random.nextInt(676767);
        }while(instrumentos.containsKey(clave));
        return clave;
    }


    //Este metodo se usa para agregar todos los instrumentos ya creados en el archivo con anterioridad al hashmap
    public HashMap<Integer, Instrumento> obtenerInstrumentos(){

        HashMap<Integer,Instrumento> instrumentos = new HashMap<>();

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

        return instrumentos;
    }


    public void eliminarInstrumentos(Integer claveAEliminar){
        try{
            List<String> archivoConInstrumentoBorrado= Files.lines(ruta).filter(linea->{
                String[] lineaDeTexto=linea.split(",");
                return lineaDeTexto.length>0 && !claveAEliminar.toString().equals(lineaDeTexto[0]);
            }).collect(Collectors.toList());
            Files.write(ruta, archivoConInstrumentoBorrado);
        }catch (IOException aviso){
            System.out.println("Error al leer el archivo: ");
        }
    }


}
