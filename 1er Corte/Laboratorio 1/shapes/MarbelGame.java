import java.util.HashMap;

/**
 * Clase que representa el juego de canicas donde cada canica debe llegar 
 * a su hoyo del mismo color
 * 
 * @author Juan Daniel Bogotá
 * @author Nicolas Felipe Bernal
 * @version 1.0 (30 Ago 2025)
 */
public class MarbelGame {
    private Cell[][] board;
    private int size;
    private static final String[] COLORS = {"red", "blue", "green"};
    private HashMap<String, Integer> marblesInHoles;
    
    /**
     * Constructor del juego
     * @param size tamaño del tablero (size x size)
     */
    public MarbelGame(int size) {
        this.size = size;
        board = new Cell[size][size];
        marblesInHoles = new HashMap<>();
        for (String color : COLORS) {
            marblesInHoles.put(color, 0);
        }
    }
    
    /**
     * Mueve una canica en la dirección especificada
     * @param direction dirección del movimiento ("N", "S", "E", "W")
     */
    public void moveTo(String direction) {
        return String direction;
    }
    
    /**
     * Reinicia el juego a su estado inicial
     */
    public void restart() {
        marblesInHoles.clear();
        for (String color : COLORS) {
            marblesInHoles.put(color, 0);
        }
    }
    
    /**
     * Verifica si el estado actual del juego es correcto
     * @return true si todas las canicas están en sus hoyos correctos
     */
    public boolean isOk() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].hasMarble() && !board[i][j].isOk()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Verifica si el juego ha sido ganado
     * @return mensaje indicando si el juego ha sido ganado
     */
    public String winGame() {
        if (isOk()) {
            return "¡Felicitaciones! Has completado el juego.";
        }
        return "El juego aún no ha sido completado.";
    }
    
    /**
     * Dibuja el tablero y sus componentes
     */
    private void draw() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                    board[i][j].makeVisible();
                }
            }
        }
    }
    
    /**
     * Borra el tablero y sus componentes
     */
    private void erase() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                    board[i][j].makeInvisible();
                }
            }
        }
    }
    
}