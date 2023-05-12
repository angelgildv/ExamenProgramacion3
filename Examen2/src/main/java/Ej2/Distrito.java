package Ej2;
import java.util.HashMap;
import java.util.Map;
class Distrito {
    private String nombre;
    private int totalAparcamientos;
    private Map<String, Integer> aparcamientosPorTipo;

    public Distrito(String nombre) {
        this.nombre = nombre;
        this.totalAparcamientos = 0;
        this.aparcamientosPorTipo = new HashMap<>();
    }

    public void incrementarAparcamientosPorTipo(String tipo) {
        int cantidad = aparcamientosPorTipo.getOrDefault(tipo, 0);
        aparcamientosPorTipo.put(tipo, cantidad + 1);
        totalAparcamientos++;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTotalAparcamientos() {
        return totalAparcamientos;
    }

    public Map<String, Integer> getAparcamientosPorTipo() {
        return aparcamientosPorTipo;
    }
}