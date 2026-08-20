import java.util.Scanner;

public class Vista {
    private Scanner scanner;

    public Vista(){
        scanner = new Scanner(System.in);
    }

    public String pedirCadena(String mensaje){
        System.out.println(mensaje);
        return scanner.nextLine();
    }

    public int pedirNumero(String mensaje){
        System.out.println(mensaje);
        return scanner.nextInt();
    }

    public void mostrarMenu(){
        System.out.println("SISTEMA APA\n1. Registrar instrumento\n2. Eliminar instrumento\n3. Realizar consultas");
    }

    public void mostrarSubMenu(){
        System.out.println("Ingrese el tipo de consulta\n" +
                "1.- Por autor\n" +
                "2.- Por tipo\n" +
                "3.- Por forma de instrumento\n" +
                "4.- Por condición\n" +
                "5.- Por evaluación de validez y confiabilidad\n" +
                "6.- Ordenarlos por clave\n" +
                "7.- Ordenar por primer autor\n" +
                "8.- Mostrar por condición y validez\n");
    }


}
