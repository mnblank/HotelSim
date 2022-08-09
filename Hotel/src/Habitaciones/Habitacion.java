package Habitaciones;

public abstract class Habitacion {
    private int costo;
    private int numCamas;
    private String nombre;
    private boolean reservada;

    public Habitacion(int costo, int numCamas, String nombre){
        this.costo = costo;
        this.numCamas = numCamas;
        this.nombre = nombre;
        this.reservada = false;
    }
    public int getCosto(){
        return this.costo;
    }
    public int getNumHabitaciones(){
        return this.numCamas;
    }
    public String getNombre(){
        return this.nombre;
    }
    public boolean isReservada(){
        return this.reservada;
    }
    @Override
    public String toString(){
        return "Habitacion " + this.nombre +" con " + this.numCamas + " camas y con un costo de " +
                this.costo + " pesos por dia.";
    }

}
