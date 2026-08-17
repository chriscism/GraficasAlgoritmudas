import java.util.Scanner;

public class UITexto{
    private SistemaInstrumentos sistemaInstrumentos=new SistemaInstrumentos();
    public UITexto(){
        Scanner scn=new Scanner(System.in);
        int opcion=0;
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
                    "9.- Salir");
            boolean esNumero=true;
            int consulta=0;
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
                    System.out.println("Gracias por usar el sistema, regrese pronto");
                    break;
                default:
                    System.out.println("Esa no es una opcion valida");
            }
        }while(opcion!=9);
    }
}
