package Ej2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class DatosAparcamientos {
    private Map<String, Integer> aparcamientosPorTipo;
    private Map<String, Integer> aparcamientosPorDistrito;

    public DatosAparcamientos() {
        aparcamientosPorTipo = new HashMap<>();
        aparcamientosPorDistrito = new HashMap<>();
    }

    public void cargarDatos(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            /*
            pruebas sin buffer (sin terminar)
            File f = new File(archivo);
            Scanner lector = new Scanner(f);
            boolean primeraLinea = true;
            while ((lector.hasNext())) {
                lector.nextLine();
                String[] datos = lector.nextLine().split(",");
                String nombre = datos[1];
                String tipo = datos[2];
                String distrito = datos[6];
                Aparcamiento aparcamiento = new Aparcamiento(nombre, tipo, distrito);
                contarAparcamientoTipo(tipo);
                contarAparcamientoDistrito(distrito);
            }
            */
            String line;
            boolean primeraLinea = true;
            while ((line = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                String[] datos = line.split(",");
                String nombre = datos[1];
                String tipo = datos[2];
                String distrito = datos[6];
                Aparcamiento aparcamiento = new Aparcamiento(nombre, tipo, distrito);
                contarAparcamientoTipo(tipo);
                contarAparcamientoDistrito(distrito);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void contarAparcamientoTipo(String tipo) {
        aparcamientosPorTipo.put(tipo, aparcamientosPorTipo.getOrDefault(tipo, 0) + 1);
    }

    private void contarAparcamientoDistrito(String distrito) {
        aparcamientosPorDistrito.put(distrito, aparcamientosPorDistrito.getOrDefault(distrito, 0) + 1);
    }

    public Map<String, Integer> getAparcamientosPorTipo() {
        return aparcamientosPorTipo;
    }

    public Map<String, Integer> getAparcamientosPorDistrito() {
        return aparcamientosPorDistrito;
    }
}