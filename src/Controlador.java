public class Controlador {
    private  SistemaInstrumentos sistema;
    private Vista vista;
    private  GestorArchivo archivo;

    public Controlador(){
        sistema = new SistemaInstrumentos();
        vista = new Vista();
        archivo = new GestorArchivo();
    }

    public void comenzar(){
        int opcion;
        do{
            vista.mostrarMenu();
            opcion = Integer.parseInt(vista.pedirCadena("Seleccione una opción: "));
            switch (opcion){
                case 1:
                    sistema.actualizarInstrumentos(archivo.guardarInstrumentos(sistema.getInstrumentos(),formarInstrumento()));
                    break;
            }
        }while(opcion != 4);
    }

    public Instrumento formarInstrumento(){
        String nombre = vista.pedirCadena("Nombre del instrumento: ");
        int id;
        do{
            id = Integer.parseInt(vista.pedirCadena("Identificación (1.- identificar, 2.- manejar:)"));
            if(!(id == 1 || id == 2))
                System.out.println("Opción inválida");
        }while(!(id == 1 || id == 2));
        String condicion = vista.pedirCadena("Condición (estrés, ansiedad, ambos)");
        boolean tieneValidez;
        int validez = Integer.parseInt(vista.pedirCadena("¿Tiene validez? (1 - Sí, 2 - No)"));
        tieneValidez = id == 1;
        String cita = null;
        if(tieneValidez)
            cita = vista.pedirCadena("Cita bibliográfica: ");
        String forma = vista.pedirCadena("Forma del instrumento");
        Instrumento instrumento = new Instrumento(nombre, id, condicion, tieneValidez, cita, forma);
        int numAutores = Integer.parseInt(vista.pedirCadena("¿Cuántos autores tiene?"));
        for(int i = 1; i <= numAutores; i++){
            String autor = vista.pedirCadena("Inserte nombre del autor " + i);
            instrumento.anadirAutor(autor);
        }
      //  System.out.println(instrumento);
        return instrumento;
    }


}
