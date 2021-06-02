import java.util.ArrayList;

public class Floyd {

    private ArrayList<String> ciudades;
    ArrayList<String[]> datos;

    private int[][] km;
    private int[][] rutas;
    private int infinite = (int) Double.POSITIVE_INFINITY;

    public Floyd(ArrayList<String[]> datos) {
        ciudades = new ArrayList<>();
        this.datos = datos;

        for (int i = 0; i < datos.size(); i++) {
            if (!ciudades.contains(datos.get(i)[0])) {
                ciudades.add(datos.get(i)[0]);
            }
            if (!ciudades.contains(datos.get(i)[1])) {
                ciudades.add(datos.get(i)[1]);
            }
        }
        km = new int[ciudades.size()][ciudades.size()];
        rutas = new int[ciudades.size()][ciudades.size()];

        for (int i = 0; i < ciudades.size(); i++) {
            for (int j = 0; j < ciudades.size(); j++) {
                if (i == j) {
                    km[i][j] = 0;
                } else {
                    km[i][j] = infinite;
                }

                rutas[i][j] = infinite;
            }
        }

        for (int i = 0; i < datos.size(); i++) {
            int m = ciudades.indexOf(datos.get(i)[0]);
            int n = ciudades.indexOf(datos.get(i)[1]);

            km[m][n] = Integer.parseInt(datos.get(i)[2]);
            rutas[m][n] = n;
        }
    }

    /**
     * Metodo de ejecucion algoritmo Floyd
     */
    public void algoritmo() {
        for (int k = 0; k < ciudades.size(); k++) {
            for (int i = 0; i < ciudades.size(); i++) {
                for (int j = 0; j < ciudades.size(); j++) {
                    if (km[i][k] == infinite || km[k][j] == infinite) {
                        continue;
                    }
                    if (km[i][k] + km[k][j] < km[i][j]) {
                        km[i][j] = km[i][k] + km[k][j];
                        rutas[i][j] = rutas[i][k];
                    }

                }
            }
        }
    }

    /**
     * Metodo que devuelve el resultado del calculo de la ruta mas corta entre dos
     * ciudades
     * 
     * @param c1 ciudad de origen
     * @param c2 ciudad de destino
     * @return String cadena de resultado
     */
    public String resultadoFloyd(String c1, String c2) {
        int o = ciudades.indexOf(c1);
        int d = ciudades.indexOf(c2);
        int distancia = km[ciudades.indexOf(c1)][ciudades.indexOf(c2)];

        String resultado = "La distancia más corta entre estas ciudades es: " + distancia + "\n" + ciudades.get(o);
        if (km[o][d] == infinite) {
            resultado = "No se puede calcular el camino más corto ya que este es infinito";
        } else if (km[o][d] == 0) {
            resultado = "Este camino es 0 debido a que ya se está en esa ciudad";
        } else {
            while (o != d) {
                o = rutas[o][d];
                resultado += "----" + ciudades.get(o);
            }
        }
        return resultado + "\n\n";
    }

    /**
     * metodo que interrumpe la ruta entre dos ciudades
     * 
     * @param c1 ciudad de origen
     * @param c2 ciudad de destino
     * @return String cadena de resultado
     */
    public String interrumpir(String c1, String c2) {
        int o = ciudades.indexOf(c1);
        int d = ciudades.indexOf(c2);

        km[o][d] = infinite;
        rutas[o][d] = infinite;
        return "Se ha interrumpido la ruta entre " + c1 + " y " + c2;
    }

    /**
     * metodo que inserta una nueva ciudad en el vector y crea su conexion entre las
     * nuevas ciudades
     * 
     * @param co           ciudad de origen
     * @param cd           ciudad de destino
     * @param distanciaNew distancia entre las ciudades
     */
    public void addNewC(String co, String cd, int distanciaNew) {
        if (!ciudades.contains(co)) {
            ciudades.add(co);
        }
        if (!ciudades.contains(cd)) {
            ciudades.add(cd);
        }

        int km2[][] = new int[ciudades.size()][ciudades.size()];
        for (int i = 0; i < km.length; i++)
            for (int j = 0; j < km[i].length; j++)
                km2[i][j] = km[i][j];
        int rutas2[][] = new int[ciudades.size()][ciudades.size()];
        for (int i = 0; i < km.length; i++) {
            for (int j = 0; j < km[i].length; j++) {
                rutas2[i][j] = rutas[i][j];
            }
        }

        for (int i = 0; i < ciudades.size(); i++) {

            rutas2[ciudades.size() - 1][i] = infinite;
            if (i == ciudades.size() - 1) {
                km2[ciudades.size() - 1][i] = 0;
            } else {
                km2[ciudades.size() - 1][i] = infinite;
            }
            rutas2[ciudades.size() - 1][i] = infinite;

            if (i != ciudades.size() - 1) {
                km2[i][ciudades.size() - 1] = infinite;
                rutas2[i][ciudades.size() - 1] = infinite;
            }
        }

        int o = ciudades.indexOf(co);
        int d = ciudades.indexOf(cd);
        rutas2[o][d] = d;
        this.km = km2;
        this.rutas = rutas2;

        km[o][d] = distanciaNew;
        rutas[o][d] = d;
    }

    /**
     * metodo que inserta la conexion entre dos ciudades existentes
     * 
     * @param co           ciudad de origen
     * @param cd           ciudad de destino
     * @param distanciaNew distancia entre las ciudades
     */
    public void addNew(String co, String cd, int distanciaNew) {
        int o = ciudades.indexOf(co);
        int d = ciudades.indexOf(cd);

        km[o][d] = distanciaNew;
        rutas[o][d] = d;
    }

    /**
     * metodo que muestra la matriz de distancias
     * 
     * @return String resultado
     */
    public String mostrarM() {
        String resultKm = "";
        System.out.println("\n\nMatriz de distancia km (Adyacencia)\n");
        for (int i = 0; i < ciudades.size(); i++) {
            for (int k = 0; k < ciudades.size(); k++) {
                resultKm += ("" + km[i][k] + " ");
            }
            resultKm += "\n";
        }
        return resultKm;
    }

    /**
     * metodo que muestra la matriz de rutas
     * 
     * @return String resultado
     */
    public String mostrarR() {
        String resultR = "";
        System.out.println("\n\nMatriz de numero de rutas\n");
        for (int i = 0; i < ciudades.size(); i++) {
            for (int k = 0; k < ciudades.size(); k++) {
                resultR += ("" + rutas[i][k] + " ");
            }
            resultR += "\n";
        }
        return resultR;
    }

    /**
     * @return ArrayList<String>
     */
    public ArrayList<String> getCiudades() {
        return ciudades;
    }

    /**
     * @param ciudades
     */
    public void setCiudades(ArrayList<String> ciudades) {
        this.ciudades = ciudades;
    }

    /**
     * @return int[][]
     */
    public int[][] getKm() {
        return km;
    }

    /**
     * @param km
     */
    public void setKm(int[][] km) {
        this.km = km;
    }

    /**
     * @return int[][]
     */
    public int[][] getRutas() {
        return rutas;
    }

    /**
     * @param rutas
     */
    public void setRutas(int[][] rutas) {
        this.rutas = rutas;
    }

    /**
     * @return int
     */
    public int getInfinite() {
        return infinite;
    }

    /**
     * @param infinite
     */
    public void setInfinite(int infinite) {
        this.infinite = infinite;
    }

}