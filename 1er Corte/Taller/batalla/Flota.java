import java.util.ArrayList;

/**
 * Clase Flota: representa una flota dentro del juego.
 * Contiene barcos, aviones, portaaviones y marinos.
 */
public class Flota {
    private String nombre;
    private ArrayList<Avion> aviones;
    private ArrayList<Barco> barcos;
    private ArrayList<PortaAviones> portaAviones;
    private ArrayList<Marino> marinos;

    
    /** Nuevos atributos */
    
    /** Código único de identificación de la flota (no modificable
    pero puede ser consultado por todos. */
    private final String codigo;

    /** Puntajes asignados a los diferentes elementos de guerra. */
    private int puntajePortaavion;
    private int puntajeBarco;
    private int puntajeAvion;

    /** Tripulantes mínimos requeridos (constantes, no modificables). */
    private final int minMarinosPortaavion = 100;
    private final int minMarinosBarco = 40;
    private final int minPilotosAvion = 20;


	  /** Constructor */
    public Flota(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.aviones = new ArrayList<>();
        this.barcos = new ArrayList<>();
        this.portaAviones = new ArrayList<>();
        this.marinos = new ArrayList<>();

        /** Valores iniciales de puntaje */
        this.puntajePortaavion = 50;
        this.puntajeBarco = 30;
        this.puntajeAvion = 20;
    }

    
    /** Consulta */
    public String getCodigo() {
        return codigo;
    }

    public int getMinMarinosPortaavion() { 
	    return minMarinosPortaavion; 
	    }
	    
    public int getMinMarinosBarco() { 
	    return minMarinosBarco; 
	    }
	    
    public int getMinMarinosAvion() { 
	    return minPilotosAvion; 
	    }

}
