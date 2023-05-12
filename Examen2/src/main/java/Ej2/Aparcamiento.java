package Ej2;
class Aparcamiento {
    private String nombre, tipo, distrito;
    public Aparcamiento(String nombre, String tipo, String distrito) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.distrito = distrito;
    }
    public String getTipo() {
        return tipo;
    }
    public String getDistrito() {
        return distrito;
    }
}