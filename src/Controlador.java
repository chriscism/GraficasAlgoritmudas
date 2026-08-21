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
                    System.out.println("El instrumento se registró con éxito");
                    break;
                case 2:
                    int clave = Integer.parseInt(vista.pedirCadena("Inserte la clave a eliminar"));
                    archivo.eliminarInstrumentos(clave);
                    sistema.eliminarInstrumento(clave);
                    System.out.println("El instrumento se eliminó con éxito");
                    break;
                case 3:
                    vista.mostrarSubMenu();
                    int consulta = Integer.parseInt(vista.pedirCadena("Seleccione una consulta"));
                    switch (consulta){
                        case 1:
                            sistema.mostrarPorAutor(vista.pedirCadena("Inserte al autor: ")).forEach(System.out::println);
                            break;
                        case 2:
                            sistema.mostrarPorIdentificacion(Integer.parseInt(vista.pedirCadena("Inserte identificación (1 - identificar, 2 - manejar)"))).forEach(System.out::println);
                            break;
                        case 3:
                            sistema.mostrarPorForma(vista.pedirCadena("Inserte forma: ")).forEach(System.out::println);
                            break;
                        case 4:
                            sistema.mostrarPorCondicion(vista.pedirCadena("Inserte condición (estrés, ansiedad, ambos)")).forEach(System.out::println);
                            break;
                        case 5:
                            sistema.mostrarPorValidez().forEach(System.out::println);
                            break;
                        case  6:
                            sistema.mostrarPorClave();
                            break;
                        case  7:
                            sistema.mostrarOrdenadoPorPrimerAutor().forEach(System.out::println);
                            break;
                        case 8:
                            sistema.mostrarPorCondicionYValidez(vista.pedirCadena("Inserte condición (estrés, ansiedad, ambos")).forEach(System.out::println);
                            break;
                        default:
                            System.out.println("Valor inválido");
                            break;
                    }
                    break;
                case 4:
                    System.out.println("Saliendo. . .");
                    break;
                default:
                    System.out.println("Valor inválido");
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
