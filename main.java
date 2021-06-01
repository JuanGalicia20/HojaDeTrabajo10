import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import org.ietf.jgss.Oid;

public class main {

    private static JFileChooser file = new JFileChooser();
    private static File arch;
    private static String ruta = null;

    public static void main(String[] args) {

        ArrayList<String[]> datos = open();
        Floyd fl = new Floyd(datos);

        String op = "";
        Scanner leer = new Scanner(System.in);
        while (!op.equals("3")) {
            fl.algoritmo();
            System.out.println("Bienvenido al programa de Centro de Respuesta Covid-19\n" + "Seleccione una opcion:\n"
                    + "1. Calcular ruta más corta de una ciudad a otra\n" + "2. Modificar ciudades\n"
                    + "3. Salir del programa");
            op = leer.next();

            String opN2 = "";
            switch (op) {
                case "1": {
                    boolean existe = false;
                    String cO = "";
                    while (!existe) {
                        System.out.println("Ingrese la ciudad de origen en MINUSCULAS por favor");
                        cO = leer.next();
                        if (fl.getCiudades().contains(cO)) {
                            existe = true;
                        } else {
                            System.out.println("La ciudad no existe por favor ingrese una válida\n");
                        }
                    }

                    boolean existe2 = false;
                    String cD = "";
                    while (!existe2) {
                        System.out.println("Ingrese la ciudad de destino en MINUSCULAS por favor");
                        cD = leer.next();
                        if (fl.getCiudades().contains(cD)) {
                            existe2 = true;
                        } else {
                            System.out.println("La ciudad no existe por favor ingrese una válida\n");
                        }
                    }

                    System.out.println(fl.resultadoFloyd(cO, cD));
                    break;
                }
                case "2": {
                    while (!opN2.equals("a") && !opN2.equals("b")) {
                        System.out.println(
                                "\n\nSeleccione una opcion\n" + "a. Hay interrupción de tráfico entre dos ciudades\n"
                                        + "b. Establecer una nueva conexión entre dos ciudades");
                        opN2 = leer.next();
                        if (opN2.equals("a")) {
                            System.out.println("aaaaa");
                        } else if (opN2.equals("b")) {
                            System.out.println("bbbbbbbbb");
                        } else {
                            System.out.println("Ingrese un valor valido");
                        }
                    }

                    break;
                }
                case "3": {
                    System.out.println("Saliendo del programa...");
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println("Ingrese una opcion valida");
                    break;
                }
            }
        }
    }

    /**
     * metodo que abre el seleccionador de archivos
     * 
     * @return ArrayList<String> los datos obtenidos
     */
    public static ArrayList<String[]> open() {
        int r = file.showOpenDialog(null);
        ArrayList<String> data = new ArrayList<>();
        String line = "";
        ArrayList<String[]> datosF = new ArrayList<String[]>();

        if (r == JFileChooser.APPROVE_OPTION) {
            arch = file.getSelectedFile();
            ruta = arch.getAbsolutePath();
            System.out.println("\nArchivo a utilizar: " + arch.getAbsolutePath());
            try {
                FileReader read = new FileReader(ruta);
                BufferedReader read1 = new BufferedReader(read);
                data.add(read1.readLine());
                while ((line = read1.readLine()) != null) {
                    line = line.toLowerCase();
                    data.add(line);
                }
            } catch (ArithmeticException | IOException | NumberFormatException e) {
                System.out.println(e.toString());
            }
        }

        for (int i = 0; i < data.size(); i++) {
            String[] l = data.get(i).toLowerCase().split(" ");
            datosF.add(l);
        }
        return datosF;
    }

}
