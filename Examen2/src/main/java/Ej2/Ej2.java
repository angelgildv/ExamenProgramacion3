package Ej2;
/*
El ayuntamiento de madrid nos ofrece los datos de los aparcamientos del municipio en el archivo adjunto "aparcamientos.csv".
Solo podemos leer una vez los datos del archivo y almacenarlos en la jerarquia de clases necesaria,
que permita en un futuro realizar calculos con los datos de aparcamientos.
Por ahora, solo queremos calcular el numero de aparcamientos por cada tipo y el total de aparcamientos por distrito.
*/
import java.util.Map;

public class Ej2 {
    public static void main(String[] args) {
        try {
            DatosAparcamientos aparcamientos = new DatosAparcamientos();
            aparcamientos.cargarDatos("aparcamientos.csv");

            Map<String, Integer> aparcamientosTipo = aparcamientos.getAparcamientosPorTipo();
            System.out.println("Aparcamientos por tipo:");
            for (Map.Entry<String, Integer> entry : aparcamientosTipo.entrySet()) {
                String tipo = entry.getKey();
                int cantidad = entry.getValue();
                System.out.println(tipo + ": " + cantidad);
            }

            Map<String, Integer> aparcamientosDistrito = aparcamientos.getAparcamientosPorDistrito();
            System.out.println("Aparcamientos por distrito:");
            for (Map.Entry<String, Integer> entry : aparcamientosDistrito.entrySet()) {
                String distrito = entry.getKey();
                int cantidad = entry.getValue();
                System.out.println(distrito + ": " + cantidad);
            }
        } catch (Exception e) {
            System.out.println("Escepcion: " + e);
        }
    }
}