package hotel;

import Habitaciones.Habitacion;
import Habitaciones.HabitacionDoble;
import Habitaciones.HabitacionMultiple;
import Habitaciones.HabitacionSencilla;

import java.awt.image.LookupOp;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Hotel {
    private HashMap<String, ArrayList<Habitacion>> catalogoHabitaciones;
    private HashMap<String, ArrayList<Habitacion>> habitacionesReservadas;
    private ArrayList<Reservacion> reservaciones;
    private final String SENCILLAS = "sencillas";
    private final String DOBLES = "dobles";
    private final String MULTIPLES = "multiples";

    public Hotel(){
        this.catalogoHabitaciones = new HashMap<>();
        this.catalogoHabitaciones.put(SENCILLAS,new ArrayList<Habitacion>());
        this.catalogoHabitaciones.put(DOBLES,new ArrayList<Habitacion>());
        this.catalogoHabitaciones.put(MULTIPLES, new ArrayList<Habitacion>());
        this.habitacionesReservadas = new HashMap<>();
        this.habitacionesReservadas.put(SENCILLAS, new ArrayList<>());
        this.habitacionesReservadas.put(DOBLES,new ArrayList<>());
        this.habitacionesReservadas.put(MULTIPLES,new ArrayList<>());
        this.reservaciones = new ArrayList<>();
        this.ingresarHabitaciones();


    }
    
    public void MostrarSencillas(){
        for(int i = 0; i < this.catalogoHabitaciones.get(SENCILLAS).size(); i++){
            System.out.println((i+1) + ". " + catalogoHabitaciones.get(SENCILLAS).get(i));
        }
    }
    public void MostrarDobles(){
        for(int i = 0; i < this.catalogoHabitaciones.get(DOBLES).size(); i++){
            System.out.println((i+1) + ". " + catalogoHabitaciones.get(DOBLES).get(i));
        }
    }
    public void MostrarMultiples(){
        for(int i = 0; i < this.catalogoHabitaciones.get(MULTIPLES).size(); i++){
            System.out.println((i+1) + ". " + catalogoHabitaciones.get(MULTIPLES).get(i));
        }
    }
    public Habitacion getSencilla(int indice){
        return this.catalogoHabitaciones.get(SENCILLAS).get(indice);
    }
    public Habitacion getDoble(int indice){
        return this.catalogoHabitaciones.get(DOBLES).get(indice);
    }
    public Habitacion getMultiple(int indice){
        return this.catalogoHabitaciones.get(MULTIPLES).get(indice);
    }

    public void iniciarReservaciones(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nBienvenido al sistema de reservas.\n");
        boolean loop = true;
        while(loop){
            System.out.println("\nIngrese el tipo de habitacion que desea reservar:\n1. Sencilla.\n2. Doble\n3. Multiple." +
                    "\nx. Salir.");
            String comando = scanner.nextLine().replace("\\s","");
            switch(comando) {
                case "1": // Sencilla
                    System.out.println("Seleccione la habitacion que desea reservar:");
                    this.MostrarSencillas();
                    int indice = -1;
                    while (!(indice > -1 && indice <= this.catalogoHabitaciones.get(SENCILLAS).size())) {
                        try {
                            indice = Integer.parseInt(scanner.nextLine().replace("\\s", ""))-1;
                            if (!(indice > -1 && indice <= this.catalogoHabitaciones.get(SENCILLAS).size())) {
                                System.out.print("Ingrese correctamente el comando.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.print("Ingrese correctamente el comando: ");
                        }
                    }
                    Habitacion habRentada = this.catalogoHabitaciones.get(SENCILLAS).get(indice);
                    this.reservaciones.add(new Reservacion(this.catalogoHabitaciones.get(SENCILLAS).get(indice)));
                    this.habitacionesReservadas.get(SENCILLAS).add(habRentada);
                    this.catalogoHabitaciones.get(SENCILLAS).remove(habRentada);
                    break;
                case "2": // Doble
                    System.out.println("Selecciona la habitacion que desea reservar:");
                    this.MostrarDobles();
                    int indice2 = -1;
                    while (!(indice2 > -1 && indice2 <= this.catalogoHabitaciones.get(DOBLES).size())) {
                        try {
                            indice2 = Integer.parseInt(scanner.nextLine().replace("\\s", ""))-1;
                            if (!(indice2 > -1 && indice2 <= this.catalogoHabitaciones.get(DOBLES).size())) {
                                System.out.print("Ingrese correctamente el comando.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.print("Ingrese correctamente el comando: ");
                        }
                    }
                    Habitacion habRentada2 = this.catalogoHabitaciones.get(DOBLES).get(indice2);
                    this.reservaciones.add(new Reservacion(this.catalogoHabitaciones.get(DOBLES).get(indice2)));
                    this.habitacionesReservadas.get(DOBLES).add(habRentada2);
                    this.catalogoHabitaciones.get(DOBLES).remove(habRentada2);
                    break;
                case "3": //Multiple
                    System.out.println("Seleccione la habitacion que desea reservar:");
                    this.MostrarMultiples();
                    int indice3 = -1;
                    while (!(indice3 > -1 && indice3 <= this.catalogoHabitaciones.get(MULTIPLES).size())) {
                        try {
                            indice3 = Integer.parseInt(scanner.nextLine().replace("\\s", ""))-1;
                            if (!(indice3 > -1 && indice3 <= this.catalogoHabitaciones.get(MULTIPLES).size())) {
                                System.out.print("Ingrese correctamente el comando.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.print("Ingrese correctamente el comando: ");
                        }
                    }
                    Habitacion habRentada3 = this.catalogoHabitaciones.get(MULTIPLES).get(indice3);
                    this.reservaciones.add(new Reservacion(this.catalogoHabitaciones.get(MULTIPLES).get(indice3)));
                    this.habitacionesReservadas.get(MULTIPLES).add(habRentada3);
                    this.catalogoHabitaciones.get(MULTIPLES).remove(habRentada3);
                    break;
                case "x":
                    loop = false;
                    break;
                default:
                    System.out.print("El comando ingresado no es valido.");
                    break;
            }

        }
    }
    private void ingresarHabitaciones(){
        // habitaciones sencillas
        this.catalogoHabitaciones.get(SENCILLAS).add(new HabitacionSencilla(500,"Aventuras Pasionales" ));
        this.catalogoHabitaciones.get(SENCILLAS).add(new HabitacionSencilla(500,"Aventuras Pasionales" ));
        this.catalogoHabitaciones.get(SENCILLAS).add(new HabitacionSencilla(500,"Aventuras Pasionales" ));
        this.catalogoHabitaciones.get(SENCILLAS).add(new HabitacionSencilla(1000,"Pasion Duradera" ));
        this.catalogoHabitaciones.get(SENCILLAS).add(new HabitacionSencilla(1000,"Pasion Duradera" ));
        this.catalogoHabitaciones.get(SENCILLAS).add(new HabitacionSencilla(750,"Negocios" ));
        this.catalogoHabitaciones.get(SENCILLAS).add(new HabitacionSencilla(750,"Negocios" ));
        this.catalogoHabitaciones.get(SENCILLAS).add(new HabitacionSencilla(750,"Negocios" ));
        // habitaciones dobles
        this.catalogoHabitaciones.get(DOBLES).add(new HabitacionDoble(1500,"Agradable compañia" ));
        this.catalogoHabitaciones.get(DOBLES).add(new HabitacionDoble(1500, "Agradable compañia"));
        this.catalogoHabitaciones.get(DOBLES).add(new HabitacionDoble(2000,"Pequeñas Familias"));
        this.catalogoHabitaciones.get(DOBLES).add(new HabitacionDoble(2000,"Pequeñas Familias"));
        this.catalogoHabitaciones.get(DOBLES).add(new HabitacionDoble(2000,"Pequeñas Familias"));
        this.catalogoHabitaciones.get(DOBLES).add(new HabitacionDoble(2000,"Pequeñas Familias"));
        this.catalogoHabitaciones.get(DOBLES).add(new HabitacionDoble(1200,"Negocios" ));
        this.catalogoHabitaciones.get(DOBLES).add(new HabitacionDoble(1200,"Negocios" ));
        this.catalogoHabitaciones.get(DOBLES).add(new HabitacionDoble(1200,"Negocios" ));
        this.catalogoHabitaciones.get(DOBLES).add(new HabitacionDoble(1200,"Negocios" ));
        // habitaciones multiples
        this.catalogoHabitaciones.get(MULTIPLES).add(new HabitacionMultiple(2000,4,"Fiestas traviesas"));
        this.catalogoHabitaciones.get(MULTIPLES).add(new HabitacionMultiple(1500,3,"Negocios"));
        this.catalogoHabitaciones.get(MULTIPLES).add(new HabitacionMultiple(1500,3,"Negocios"));
        this.catalogoHabitaciones.get(MULTIPLES).add(new HabitacionMultiple(1500,3,"Negocios"));
        this.catalogoHabitaciones.get(MULTIPLES).add(new HabitacionMultiple(3000,4,"Vacaciones en Familia"));
        this.catalogoHabitaciones.get(MULTIPLES).add(new HabitacionMultiple(3000,4,"Vacaciones en Familia"));
        this.catalogoHabitaciones.get(MULTIPLES).add(new HabitacionMultiple(3000,4,"Vacaciones en Familia"));
        this.catalogoHabitaciones.get(MULTIPLES).add(new HabitacionMultiple(3000,4,"Vacaciones en Familia"));









    }
}