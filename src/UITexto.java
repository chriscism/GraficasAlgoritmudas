import java.util.Scanner;

public class UITexto{
    private SistemaInstrumentos sistemaInstrumentos=new SistemaInstrumentos();
    public UITexto(){
        Scanner scn=new Scanner(System.in);
        int consulta=0;
        do{
            System.out.println("Ingrese el tipo de consulta\n" +
                    "1.- Por autor\n" +
                    "2.- Por tipo\n" +
                    "3.- Por forma de instrumento\n" +
                    "4.- Por condición\n" +
                    "5.- Por evaluación de validez y confiabilidad\n" +
                    "6.- Ordenarlos por clave\n" +
                    "7.- Ordenar por primer autor\n" +
                    "8.- Mostrar por condición y validez\n" +
                    "9.- Registrar instrumento\n" +
                    "10.- Eliminar Instrumento\n" +
                    "11.- Salir");
            boolean esNumero=true;
            do{
                if((scn.hasNextInt())){
                    consulta=scn.nextInt();
                    scn.nextLine();
                    esNumero=true;
                }else {
                    System.out.println("Ese no es un numero");
                    esNumero=false;
                    scn.nextLine();
                }
            }while(!esNumero);
            switch(consulta){
                case 1:
                    System.out.println("Ingrese el nombre del autor:\n");
                    String autor= scn.nextLine();
                    sistemaInstrumentos.mostrarPorAutor(autor);
                    break;
                case 2:
                    System.out.print("Ingrese el tipo\n" +
                            "1.- Identificar\n" +
                            "2.- Manejar\n");
                    if(scn.hasNextInt()){
                        int id=scn.nextInt();
                        scn.nextLine();
                        sistemaInstrumentos.mostrarPorIdentificacion(id);
                    }else{
                        System.out.println("Identificación no válida.");
                        scn.nextLine();
                    }
                    break;
                case 3:
                    System.out.print("Ingrese la forma del instrumento: ");
                    String forma=scn.nextLine();
                    sistemaInstrumentos.mostrarPorForma(forma);
                    break;
                case 4:
                    System.out.print("Ingrese la condición: ");
                    String condicion=scn.nextLine();
                    sistemaInstrumentos.mostrarPorCondicion(condicion);
                    break;
                case 5:
                    System.out.print("¿Evaluar validez? (1: Con validez / 2: Sin validez): ");
                    boolean validez;
                    if(scn.hasNextInt()){
                        int op=scn.nextInt();
                        scn.nextLine();
                        validez=op==1;
                    }else{
                        String resp=scn.nextLine().trim().toLowerCase();
                        validez=resp.equals("si") || resp.equals("sí");
                    }
                    sistemaInstrumentos.mostrarPorValidez(validez);
                    break;
                case 6:
                    sistemaInstrumentos.mostrarPorClave();
                    break;
                case 7:
                    sistemaInstrumentos.mostrarOrdenadoPorPrimerAutor();
                    break;
                case 8:
                    System.out.print("Ingrese la condición: ");
                    String condVal=scn.nextLine();
                    System.out.print("¿Evaluar validez? (1: Con validez / 2: Sin validez): ");
                    boolean tieneValidez;
                    if(scn.hasNextInt()){
                        int op=scn.nextInt();
                        scn.nextLine();
                        tieneValidez=op==1;
                    }else{
                        String resp=scn.nextLine().trim().toLowerCase();
                        tieneValidez=resp.equals("si") || resp.equals("sí");
                    }
                    sistemaInstrumentos.mostrarPorCondicionYValidez(condVal, tieneValidez);
                    break;
                case 9:
                    System.out.print("Nombre del instrumento: ");
                    String nombre=scn.nextLine();

                    System.out.print("Identificación (1.- identificar, 2.- manejar: ");
                    int id=0;
                    boolean puedeSeguir=false;
                    while(!puedeSeguir){
                        if(scn.hasNextInt()){
                            id=scn.nextInt();
                            scn.nextLine();
                            puedeSeguir=true;
                        }else{
                            scn.nextLine();
                            System.out.println("Valor inválido");
                            puedeSeguir=false;
                        }
                    }
                    System.out.print("Condición: ");
                    String cond=scn.nextLine();

                    System.out.print("¿Tiene validez? (1.- Sí /2.- No): ");
                    boolean esValido;
                    if (scn.hasNextInt()) {
                        int op=scn.nextInt();
                        scn.nextLine();
                        esValido=op==1;
                    }else{
                        String resp=scn.nextLine().trim().toLowerCase();
                        esValido=resp.equals("si") || resp.equals("sí") || resp.equals("1");
                    }

                    System.out.print("Cita bibliográfica: ");
                    String cita = scn.nextLine();

                    System.out.print("Forma del instrumento: ");
                    String formaInst=scn.nextLine();

                    Instrumento nuevo=new Instrumento(nombre, id, cond, esValido, cita, formaInst);

                    System.out.print("Ingrese los autores separados por coma (ej. Autor 1;Autor 2): ");
                    String[] listaAutores = scn.nextLine().split(";");
                    for(String a:listaAutores){
                        if(!a.trim().isEmpty()){
                            nuevo.anadirAutor(a.trim());
                        }
                    }
                    sistemaInstrumentos.guardarEnArchivo(nuevo);
                    System.out.println("Instrumento registrado");
                    break;
                case 10:
                    System.out.print("Ingrese la clave del instrumento a eliminar: ");
                    if(scn.hasNextInt()){
                        int claveBorrar = scn.nextInt();
                        scn.nextLine();
                        sistemaInstrumentos.eliminarInstrumentos(claveBorrar);
                        System.out.println("Instrumento eliminado");
                    }else{
                        System.out.println("Clave no válida.");
                        scn.nextLine();
                    }
                    break;
                case 11:
                    System.out.println("Gracias por usar el sistema, regrese pronto");
                    break;
                default:
                    System.out.println("Esa no es una opcion valida");
            }
        }while(consulta!=11);
    }
}
