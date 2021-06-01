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

    public ArrayList<String> getCiudades() {
        return ciudades;
    }

    public void setCiudades(ArrayList<String> ciudades) {
        this.ciudades = ciudades;
    }

    public int[][] getKm() {
        return km;
    }

    public void setKm(int[][] km) {
        this.km = km;
    }

    public int[][] getRutas() {
        return rutas;
    }

    public void setRutas(int[][] rutas) {
        this.rutas = rutas;
    }

    public int getInfinite() {
        return infinite;
    }

    public void setInfinite(int infinite) {
        this.infinite = infinite;
    }

}