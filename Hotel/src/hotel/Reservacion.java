package hotel;

import Habitaciones.Habitacion;
import Habitaciones.HabitacionDoble;
import Habitaciones.HabitacionSencilla;
import Huespedes.Adulto;
import Huespedes.Huesped;
import Huespedes.Niño;
import Huespedes.Reservador;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Reservacion {
    private Huesped[] huespedes;
    private Habitacion habitacionReservada;
    private Reservador reservador;
    private Date fechaEntrada;
    private Date fechaSalida;
    private int diasReserva;


    public Reservacion(Habitacion habitacionReservada){
        this.habitacionReservada = habitacionReservada;
        this.insertarReservador();
        this.registrarFecha();
        this.agregarHuespedes();
        this.hacerComprobante();
        
        // Falta agregar huespedes

    }

    public Huesped[] getHuespedes() {
        return huespedes;
    }

    public Habitacion getHabitacionReservada() {
        return habitacionReservada;
    }

    public Reservador getReservador() {
        return reservador;
    }
    private void insertarReservador() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese los datos de la persona que hara la reserva:\nNombre: ");
        String nombre = scanner.nextLine().replace("\\s", "");
        System.out.print("Edad: ");
        int edad = -1;
        while (edad < 0) {
            try {
                edad = Integer.parseInt(scanner.nextLine().replace("\\s", ""));
                if(edad < 0){
                    System.out.print("Ingrese correctamente la edad: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Ingrese correctamente la edad: ");
            }
        }
        System.out.print("Ingrese el CURP: ");
        String CURP = scanner.nextLine().replace("\\s","");
        System.out.print("Ingrese el numero de tarjeta: ");
        double tarjeta = -1;
        while (tarjeta < 0) {
            try {
                tarjeta = Double.parseDouble(scanner.nextLine().replace("\\s", ""));
                if(tarjeta < 0){
                    System.out.print("Ingrese correctamente el numero de tarjeta: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Ingrese correctamente el numero de tarjeta: ");
            }
        }
        System.out.print("Codigo de seguridad de tarjeta: ");
        int codigo = -1;
        while (codigo < 0) {
            try {
                codigo = Integer.parseInt(scanner.nextLine().replace("\\s", ""));
                if(codigo < 0){
                    System.out.print("Ingrese correctamente la edad: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Inserte correctamente el codigo: ");
            }
        }
        this.reservador = new Reservador(edad,nombre,CURP,tarjeta,codigo);
    }
    private void registrarFecha(){
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println("Inserte la fecha de entrada (yyyy/MM/dd HH:mm:ss): ");
        Date fechaEntrada = null;
        while(fechaEntrada == null){
            try{
                String fechaString = scanner.nextLine();
                fechaEntrada = dateFormat.parse(fechaString);
            } catch (ParseException e){
                System.out.print("Ingrese correctamente la fecha: ");
            }
        }
        this.fechaEntrada = fechaEntrada;
        System.out.println("Inserte la fecha de salida (yyyy/MM/dd HH:mm:ss): ");
        Date fechaSalida = null;
        while(fechaSalida == null){
            try{
                String fechaString = scanner.nextLine();
                fechaSalida = dateFormat.parse(fechaString);
            } catch (ParseException e){
                System.out.print("Ingrese correctamente la fecha: ");
            }
        }
        while(fechaSalida.before(fechaEntrada)){
            System.out.println("Introduzca una fecha posterior a la de entrada.");
            System.out.println("Inserte la fecha de salida (yyyy/MM/dd HH:mm:ss): ");
            fechaSalida = null;
            while(fechaSalida == null){
                try{
                    String fechaString = scanner.nextLine();
                    fechaSalida = dateFormat.parse(fechaString);
                } catch (ParseException e){
                     System.out.print("Ingrese correctamente la fecha: ");
                }
            }
        }
        this.fechaSalida = fechaSalida;
        this.diasReserva = this.getDifferenceDays(fechaEntrada, fechaSalida) + 1;

    }
    private void agregarHuespedes(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Cuantos huespedes se hospedaran: ");
        int cantHuespedes = 0;
        while(cantHuespedes < 1){
            try{
                cantHuespedes = Integer.parseInt(scanner.nextLine().replace("\\s",""));
                if(cantHuespedes < 1){
                    System.out.print("Ingrese correctamente la cantidad de huespedes que se hospedaran: ");
                }
            } catch (NumberFormatException e){
                System.out.print("Ingrese correctamente la cantidad de huespedes que se hospedaran: ");

            }
        }
        this.huespedes = new Huesped[cantHuespedes];
        for(int i = 0; i < cantHuespedes; i++){
            System.out.print("Ingrese los datos de la persona que se hospedara:\nNombre: ");
            String nombre = scanner.nextLine().replace("\\s", "");
            System.out.print("Edad: ");
            int edad = -1;
            while (edad < 0) {
                try {
                    edad = Integer.parseInt(scanner.nextLine().replace("\\s", ""));
                    if(edad < 0){
                        System.out.print("Ingrese correctamente la edad: ");
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Ingrese correctamente la edad: ");
                }
            }
            if(edad > 16) {
                System.out.print("Ingrese el CURP: ");
                String INE = scanner.nextLine().replace("\\s", "");
                this.huespedes[i] = new Adulto(edad,nombre,INE);
            } else {
                this.huespedes[i] = new Niño(edad,nombre);
            }
        }
    }
    private void hacerComprobante(){
        try {
            FileWriter escritor = new FileWriter(this.reservador.getNombre().replace("\\s","") + ".txt");
            PrintWriter salida = new PrintWriter(escritor);
            String tipoHabitacion = "";
            if(this.habitacionReservada instanceof HabitacionSencilla){
                tipoHabitacion = "Habitacion Sencilla";
            }else if(this.habitacionReservada instanceof HabitacionDoble){
                tipoHabitacion = "Habitacion Doble";
            }else {
                tipoHabitacion = "Habitacion Multiple";
            }
            salida.println("Comprobante de reservacion.\n" + tipoHabitacion + ". " +
                    this.habitacionReservada.getNombre());
            salida.println("Reservada por: " + this.reservador.getDatos());
            salida.println("Costo total: " + this.habitacionReservada.getCosto()*this.diasReserva +" pesos.");
            salida.println("Huespedes de la habitacion: ");
            for(Huesped h : this.huespedes){
                salida.println(h.getDatos());
            }
            salida.println("Fecha de entrada: " + this.fechaEntrada);
            salida.println("Fecha de salida: " + this.fechaSalida);
            salida.flush();
            System.out.println("Su comprobante ha sido guardado correctamente.");
        } catch (Exception ex){
            System.out.println("Tuvimos un problema al generar su comprobante.");
        }
    }
    private int getDifferenceDays(Date d1, Date d2) {
    long diff = d2.getTime() - d1.getTime();
    return (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
}

}
