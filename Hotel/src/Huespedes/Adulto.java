package Huespedes;

public class Adulto extends Huesped {
    private String CURP;
    public Adulto(int edad, String nombre,String CURP){
        super(edad,nombre);
        this.CURP = CURP;
    }
    public String getINE(){
        return this.CURP;
    }

    @Override
    public String getDatos() {
        return super.getNombre() + ", Adulto de " + super.getEdad() + " años.";
    }
}
