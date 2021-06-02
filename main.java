import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class main {

    private static JFileChooser file = new JFileChooser();
    private static File arch;
    private static String ruta = null;

    /**
     * Juan Andrés Galicia Reyes 20298 Universidad del Valle de Guatemala Hoja de
     * trabajo 10 Metodo principal de ejecucion
     * 
     * @param args
     */
    public static void main(String[] args) {

        ArrayList<String[]> datos = open();
        Floyd fl = new Floyd(datos);

        String op = "";
        Scanner leer = new Scanner(System.in);
        while (!op.equals("3")) {
            fl.algoritmo();

            System.out.println(fl.mostrarM());
            System.out.println(fl.mostrarR());

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
                            boolean existeO2 = false;
                            String cO = "";
                            while (!existeO2) {
                                System.out.println("Ingrese la ciudad de origen en MINUSCULAS por favor");
                                cO = leer.next();
                                if (fl.getCiudades().contains(cO)) {
                                    existeO2 = true;
                                } else {
                                    System.out.println("La ciudad no existe por favor ingrese una válida\n");
                                }
                            }

                            boolean existeO22 = false;
                            String cD = "";
                            while (!existeO22) {
                                System.out.println("Ingrese la ciudad de destino en MINUSCULAS por favor");
                                cD = leer.next();
                                if (fl.getCiudades().contains(cD)) {
                                    existeO22 = true;
                                } else {
                                    System.out.println("La ciudad no existe por favor ingrese una válida\n");
                                }
                            }

                            System.out.println(fl.interrumpir(cO, cD));
                        } else if (opN2.equals("b")) {
                            System.out.println("Ingrese el nombre de la ciudad de origen");
                            String cd = leer.next();
                            if (fl.getCiudades().contains(cd)) {
                                System.out.println("Ingrese el nombre de la ciudad de destino");
                                String co = leer.next();
                                if (fl.getCiudades().contains(co)) {
                                    int distanciaNew = verif("Ingrese la distancia entre las ciudades");
                                    fl.addNew(cd, co, distanciaNew);
                                } else {
                                    boolean seguir2 = true;
                                    String opNew2 = "";
                                    while (!opNew2.equals("1") && !opNew2.equals("2") && seguir2) {
                                        System.out.println(
                                                "Está ingresando una ciudad nueva al registro está seguro?\n1. Si\n2. No");
                                        opNew2 = leer.next();
                                        if (opNew2.equals("1")) {
                                            int distanciaNew = verif("Ingrese la distancia entre las ciudades");
                                            fl.addNewC(cd, co, distanciaNew);
                                        } else if (opNew2.equals("2")) {
                                            seguir2 = false;
                                            continue;
                                        } else {
                                            System.out.println("Ingrese un valor valido");
                                        }
                                    }
                                }
                            } else {
                                boolean seguir = true;
                                String opNew = "";
                                while (!opNew.equals("1") && !opNew.equals("2") && seguir) {
                                    System.out.println(
                                            "Está ingresando una ciudad nueva al registro está seguro?\n1. Si\n2. No");
                                    opNew = leer.next();
                                    if (opNew.equals("1")) {
                                        System.out.println("Ingrese el nombre de la ciudad de destino");
                                        String co = leer.next();
                                        if (fl.getCiudades().contains(co)) {
                                            int distanciaNew = verif("Ingrese la distancia entre las ciudades");
                                            fl.addNewC(cd, co, distanciaNew);
                                        } else {
                                            boolean seguir2 = true;
                                            String opNew2 = "";
                                            while (!opNew2.equals("1") && !opNew2.equals("2") && seguir2) {
                                                System.out.println(
                                                        "Está ingresando una ciudad nueva al registro está seguro?\n1. Si\n2. No");
                                                opNew2 = leer.next();
                                                if (opNew2.equals("1")) {
                                                    int distanciaNew = verif("Ingrese la distancia entre las ciudades");
                                                    fl.addNewC(cd, co, distanciaNew);
                                                } else if (opNew2.equals("2")) {
                                                    seguir2 = false;
                                                    continue;
                                                } else {
                                                    System.out.println("Ingrese un valor valido");
                                                }
                                            }
                                        }
                                    } else if (opNew.equals("2")) {
                                        seguir = false;
                                        continue;
                                    } else {
                                        System.out.println("Ingrese un valor valido");
                                    }
                                }
                            }

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
     * @return ArrayList<String> datos obtenidos
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

    /**
     * metodo que verifica si lo ingresado es un numero
     * 
     * @param cadena cadena a reproducir para solicitar un numero
     * @return int numero obtenido
     */
    public static int verif(String cadena) {
        Scanner l = new Scanner(System.in);
        int m1 = -1;
        do {
            try {
                System.out.println(cadena);
                m1 = l.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Valor no valido, ingrese un valor nuimerico");
            }
            l.nextLine();
        } while (m1 < 0);

        return m1;
    }

}
