package Huespedes;

public abstract class Huesped {
    private int edad;
    private String nombre;

    public Huesped(int edad, String nombre){
        this.edad = edad;
        this.nombre = nombre;
    }
    public abstract String getDatos();

    public String getNombre(){
        return this.nombre;
    }
    public int getEdad(){
        return this.edad;
    }
}
