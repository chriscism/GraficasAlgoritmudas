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
            opcion = vista.pedirNumero("Seleccione una opción: ");
            switch (opcion){
                case 1:
            }
        }while(opcion != 4);
    }

    public Instrumento formarInstrumento(){
        String nombre = vista.pedirCadena("Nombre del instrumento: ");
    }


}
