import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Sistema principal de gestión del TransMilenio.
 * 
 * @author Nicolas Felipe Bernal Gallo  
 * @author Juan Daniel Bogota Fuentes
 * @version 1.0
 */
public class Transmilenio {

    private final HashMap<String, Estacion> estaciones;
    private final HashMap<String, Troncal> troncales;
    private final HashMap<String, Ruta> rutas;

    /**
     * Construye un nuevo sistema TransMilenio vacío.
     */
    public Transmilenio() {
        this.estaciones = new HashMap<>();
        this.troncales = new HashMap<>();
        this.rutas = new HashMap<>();
    }
    
    /**
     * Agrega una estación al sistema.
     * @param estacion estación a agregar
     * @throws TransmilenioException si el nombre está vacío o la estación ya existe
     */
    public void agregarEstacion(Estacion estacion) throws TransmilenioException {
        if (estacion.getNombre() == null || estacion.getNombre().isEmpty()) {
            throw new TransmilenioException(TransmilenioException.SIN_NOMBRE);
        }
        estaciones.put(estacion.getNombre(), estacion);
    }
    
    /**
     * Agrega una troncal al sistema.
     * @param troncal troncal a agregar
     * @throws TransmilenioException si el nombre está vacío
     */
    public void agregarTroncal(Troncal troncal) throws TransmilenioException {
        if (troncal.getNombreTroncal() == null || troncal.getNombreTroncal().isEmpty()) {
            throw new TransmilenioException(TransmilenioException.SIN_NOMBRE);
        }
        troncales.put(troncal.getNombreTroncal(), troncal);
    }
    
    /**
     * Agrega una ruta al sistema.
     * @param ruta ruta a agregar
     * @throws TransmilenioException si el nombre está vacío o tiene menos de 2 estaciones
     */
    public void agregarRuta(Ruta ruta) throws TransmilenioException {
        if (ruta.getNombre() == null || ruta.getNombre().isEmpty()) {
            throw new TransmilenioException(TransmilenioException.SIN_NOMBRE);
        }
        if (ruta.getEstaciones().size() < 2) {
            throw new TransmilenioException(TransmilenioException.RUTA_DEBE_TENER_ESTACIONES);
        }
        rutas.put(ruta.getNombre(), ruta);
    }
    
    /**
     * SERVICIO 1: El tiempo de espera de una estación (Dado su nombre)
     * @param nombreEstacion nombre de la estación
     * @return tiempo de espera en minutos
     * @throws TransmilenioException si la estación no existe
     */
    public int getTiempoEspera(String nombreEstacion) throws TransmilenioException {
        if (!estaciones.containsKey(nombreEstacion)) {
            throw new TransmilenioException(TransmilenioException.ESTACION_NO_ENCONTRADA);
        }
        return estaciones.get(nombreEstacion).getTiempoEspera();
    }
    
    /**
     * SERVICIO 2: El nombre de las rutas del sistema ordenadas alfabéticamente.
     * @return lista de nombres de rutas ordenadas alfabéticamente
     */
    public ArrayList<String> getRutasOrdenadas() {
        TreeSet<String> nombresOrdenados = new TreeSet<>(rutas.keySet());
        return new ArrayList<>(nombresOrdenados);
    }
    
    /**
     * SERVICIO 3: El número de paradas para ir de una estación a otra tomando una ruta dada.
     * @param nombreRuta nombre de la ruta
     * @param nombreOrigen nombre de la estación de origen
     * @param nombreDestino nombre de la estación de destino
     * @return número de paradas
     * @throws TransmilenioException si la ruta no existe o no conecta las estaciones
     */
    public int getNumeroParadas(String nombreRuta, String nombreOrigen, String nombreDestino) throws TransmilenioException {
        if (!rutas.containsKey(nombreRuta)) {
            throw new TransmilenioException(TransmilenioException.RUTA_NO_ENCONTRADA);
        }
        
        Ruta ruta = rutas.get(nombreRuta);
        return ruta.getNumeroParadas(nombreOrigen, nombreDestino);
    }
    
    /**
     * SERVICIO 4: El nombre de las rutas que permiten ir de una estación a otra sin 
     * hacer transbordos ordenadas de menor a mayor por número de paradas y 
     * alfabéticamente por nombre de la ruta.
     * 
     * @param nombreOrigen nombre de la estación de origen
     * @param nombreDestino nombre de la estación de destino
     * @return lista de nombres de rutas ordenadas
     * @throws TransmilenioException si las estaciones no existen o son iguales
     */
    public ArrayList<String> getRutasDirectas(String nombreOrigen, String nombreDestino) throws TransmilenioException {
        
        if (!estaciones.containsKey(nombreOrigen)) {
            throw new TransmilenioException(TransmilenioException.ESTACION_NO_ENCONTRADA);
        }
        if (!estaciones.containsKey(nombreDestino)) {
            throw new TransmilenioException(TransmilenioException.ESTACION_NO_ENCONTRADA);
        }
        if (nombreOrigen.equals(nombreDestino)) {
            throw new TransmilenioException(TransmilenioException.ESTACIONES_IGUALES);
        }

        TreeMap<Integer, TreeSet<String>> rutasPorParadas = new TreeMap<>();
        
        for (Ruta ruta : rutas.values()) {
            if (ruta.conectaEstaciones(nombreOrigen, nombreDestino)) {
                try {
                    int numParadas = ruta.getNumeroParadas(nombreOrigen, nombreDestino);
                    
                    if (!rutasPorParadas.containsKey(numParadas)) {
                        rutasPorParadas.put(numParadas, new TreeSet<>());
                    }
                    
                    rutasPorParadas.get(numParadas).add(ruta.getNombre());
                    
                } catch (TransmilenioException e) {
                }
            }
        }
        
        ArrayList<String> resultado = new ArrayList<>();
        for (TreeSet<String> nombresRutas : rutasPorParadas.values()) {
            resultado.addAll(nombresRutas);
        }
        
        if (resultado.isEmpty()) {
            throw new TransmilenioException(TransmilenioException.SIN_CAMINO_DISPONIBLE);
        }
        
        return resultado;
    }
    
    /**
     * Obtiene una estación por su nombre.
     * @param nombre nombre de la estación
     * @return la estación
     * @throws TransmilenioException si no existe
     */
    public Estacion getEstacion(String nombre) throws TransmilenioException {
        if (!estaciones.containsKey(nombre)) {
            throw new TransmilenioException(TransmilenioException.ESTACION_NO_ENCONTRADA);
        }
        return estaciones.get(nombre);
    }


    /**
     * PERSISTENCIA 1: Importar una nueva ruta desde un archivo de texto.
     * 
     * @param archivo nombre del archivo a leer
     * @throws TransmilenioException si hay errores en el formato o estaciones no existen
     * @throws IOException si hay problemas al leer el archivo
     */
    public void importarRuta(String archivo) throws TransmilenioException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        
        String linea = br.readLine();
        if (linea == null || linea.trim().isEmpty()) {
            br.close();
            throw new TransmilenioException(TransmilenioException.SIN_NOMBRE);
        }
        br.close();

        String[] partes = linea.split(",");
        
        if (partes.length < 3) {
            throw new TransmilenioException(TransmilenioException.RUTA_DEBE_TENER_ESTACIONES);
        }
        
        String nombreRuta = partes[0].trim();
        if (nombreRuta.isEmpty()) {
            throw new TransmilenioException(TransmilenioException.SIN_NOMBRE);
        }
        
        ArrayList<Estacion> estacionesRuta = new ArrayList<>();
        
        for (int i = 1; i < partes.length; i++) {
            String nombreEstacion = partes[i].trim();
            if (!estaciones.containsKey(nombreEstacion)) {
                throw new TransmilenioException(TransmilenioException.ESTACION_NO_ENCONTRADA);
            }
            estacionesRuta.add(estaciones.get(nombreEstacion));
        }
        
        Ruta nuevaRuta = new Ruta(nombreRuta, estacionesRuta);
        agregarRuta(nuevaRuta);
    }
    
    /**
     * PERSISTENCIA 2: Exportar a un archivo texto las rutas que permiten ir 
     * de una estación a otra sin transbordos.
     * 
     * @param nombreOrigen nombre de la estación de origen
     * @param nombreDestino nombre de la estación de destino
     * @param archivo nombre del archivo donde exportar
     * @throws TransmilenioException si las estaciones no existen o no hay rutas
     * @throws IOException si hay problemas al escribir el archivo
     */
    public void exportarRutasDirectas(String nombreOrigen, String nombreDestino, String archivo) throws TransmilenioException, IOException {
        
        ArrayList<String> rutasDirectas = getRutasDirectas(nombreOrigen, nombreDestino);
        
        PrintWriter pw = new PrintWriter(new FileOutputStream(archivo));
        
        for (int i = 0; i < rutasDirectas.size(); i++) {
            pw.print(rutasDirectas.get(i));
            if (i < rutasDirectas.size() - 1) {
                pw.print(",");
            }
        }
        pw.println();
        
        pw.close();
    }
    
    /**
     * PERSISTENCIA 3: Salvar la información actual de una troncal de transmilenio.
     * 
     * @param nombreTroncal nombre de la troncal a salvar
     * @param archivo nombre del archivo donde guardar
     * @throws TransmilenioException si la troncal no existe
     * @throws IOException si hay problemas al escribir el archivo
     */
    public void salvarTroncal(String nombreTroncal, String archivo) throws TransmilenioException, IOException {
        
        if (!troncales.containsKey(nombreTroncal)) {
            throw new TransmilenioException(TransmilenioException.TRONCAL_NO_ENCONTRADA);
        }
        
        Troncal troncal = troncales.get(nombreTroncal);
        PrintWriter pw = new PrintWriter(new FileOutputStream(archivo));
        
        pw.print(troncal.getNombreTroncal());
        pw.print(",");
        
        pw.print(troncal.getVelocidadPromedio());
        
        ArrayList<Estacion> estacionesTroncal = troncal.getEstaciones();
        for (int i = 0; i < estacionesTroncal.size(); i++) {
            pw.print(",");
            pw.print(estacionesTroncal.get(i).getNombre());
        }
        pw.println();
        
        pw.close();
    }
}