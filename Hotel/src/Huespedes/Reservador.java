package Huespedes;


public class Reservador extends Huesped{
    private String CURP;
    private double numTarjeta;
    private int codigoTarjeta;


    public Reservador(int edad, String nombre,String CURP,double numTarjeta,int codigoTarjeta){
        super(edad,nombre);
        this.CURP = CURP;
        this.numTarjeta = numTarjeta;
        this.codigoTarjeta = codigoTarjeta;
    }

    @Override
    public String getDatos() {
        return super.getNombre() + " Adulto de " + super.getEdad() + " años.";
    }
}
