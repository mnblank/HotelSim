package Huespedes;


public class Niño extends Huesped {

    public Niño(int edad, String nombre){
        super(edad,nombre);
    }

    @Override
    public String getDatos() {
        return super.getNombre() + " Niño de " + super.getEdad() + " años.";
    }

}
