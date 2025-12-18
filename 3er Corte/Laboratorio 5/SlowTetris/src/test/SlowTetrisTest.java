import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Clase de pruebas para SlowTetris
 * Prueba los métodos básicos del juego
 */
public class SlowTetrisTest {
    
    private SlowTetris game;
    
    @Before
    public void setUp() {
        game = new SlowTetris();
    }
    
    /**
     * Verificar que el juego se inicializa correctamente
     */
    @Test
    public void testInicializacion() {
        assertNotNull("El juego debe inicializarse", game);
        assertEquals("El tablero debe tener 20 filas", 20, game.getRows());
        assertEquals("El tablero debe tener 10 columnas", 10, game.getCols());
        assertEquals("El score inicial debe ser 0", 0, game.getScore());
        assertFalse("El juego no debe estar terminado al inicio", game.isGameOver());
    }
    
    /**
     * Verificar que el tablero está vacío al inicio
     */
    @Test
    public void testTableroVacio() {
        int[][] board = game.getBoard();
        for (int r = 0; r < game.getRows(); r++) {
            for (int c = 0; c < game.getCols(); c++) {
                assertEquals("La celda [" + r + "][" + c + "] debe estar vacía", 
                            SlowTetris.EMPTY, board[r][c]);
            }
        }
    }
    
    /**
     * Verificar que se genera una pieza actual y una siguiente
     */
    @Test
    public void testGeneracionPiezas() {
        assertNotNull("Debe existir una pieza actual", game.getCurrentPiece());
        assertNotNull("Debe existir una pieza siguiente", game.getNextPiece());
        
        int tipoActual = game.getCurrentPiece().getType();
        assertTrue("El tipo de pieza actual debe estar entre 1 y 7", 
                   tipoActual >= 1 && tipoActual <= 7);
        
        int tipoSiguiente = game.getNextPiece().getType();
        assertTrue("El tipo de pieza siguiente debe estar entre 1 y 7", 
                   tipoSiguiente >= 1 && tipoSiguiente <= 7);
    }
    
    /**
     * Verificar que se puede colocar una pieza en una columna válida
     */
    @Test
    public void testColocarPieza() {
        boolean resultado = game.placePiece(4);
        assertTrue("Debe poder colocar una pieza en la columna 4", resultado);
        
        boolean hayPieza = false;
        int[][] board = game.getBoard();
        for (int r = 0; r < game.getRows(); r++) {
            for (int c = 0; c < game.getCols(); c++) {
                if (board[r][c] != SlowTetris.EMPTY) {
                    hayPieza = true;
                    break;
                }
            }
        }
        assertTrue("Debe haber al menos una pieza en el tablero", hayPieza);
    }
    
    /**
     * Verificar que el reset funciona correctamente
     */
    @Test
    public void testReset() {
      
        game.placePiece(3);
        game.placePiece(4);
        game.placePiece(5);
        
    
        game.reset();
        
        assertEquals("El score debe volver a 0", 0, game.getScore());
        assertFalse("El juego no debe estar terminado", game.isGameOver());
    
        int[][] board = game.getBoard();
        boolean tableroVacio = true;
        for (int r = 0; r < game.getRows(); r++) {
            for (int c = 0; c < game.getCols(); c++) {
                if (board[r][c] != SlowTetris.EMPTY) {
                    tableroVacio = false;
                    break;
                }
            }
        }
        assertTrue("El tablero debe estar vacío después del reset", tableroVacio);
    }
    
    /**
     * Verificar que se puede colocar múltiples piezas
     */
    @Test
    public void testColocarMultiplesPiezas() {
        for (int i = 0; i < 5; i++) {
            boolean resultado = game.placePiece(i % game.getCols());
            assertTrue("Debe poder colocar la pieza " + i, resultado);
        }
    }
    
    /**
     * Verificar getCellValue
     */
    @Test
    public void testGetCellValue() {
 
        assertEquals("Celda (0,0) debe estar vacía", 
                     SlowTetris.EMPTY, game.getCellValue(0, 0));
        assertEquals("Celda (10,5) debe estar vacía", 
                     SlowTetris.EMPTY, game.getCellValue(10, 5));
        
 
        assertEquals("Celda fuera de rango debe retornar EMPTY", 
                     SlowTetris.EMPTY, game.getCellValue(-1, 0));
        assertEquals("Celda fuera de rango debe retornar EMPTY", 
                     SlowTetris.EMPTY, game.getCellValue(0, -1));
        assertEquals("Celda fuera de rango debe retornar EMPTY", 
                     SlowTetris.EMPTY, game.getCellValue(100, 100));
    }
    
    /**
     * Verificar que el high score se actualiza
     */
    @Test
    public void testHighScore() {
        int highScoreInicial = game.getHighScore();
        
      
        for (int i = 0; i < 10; i++) {
            game.placePiece(i % game.getCols());
        }
        
        int scoreFinal = game.getScore();
        int highScoreFinal = game.getHighScore();
        
        assertTrue("El high score debe ser mayor o igual al score actual", 
                   highScoreFinal >= scoreFinal);
    }
    
    /**
     * Verificar constructor con dimensiones personalizadas
     */
    @Test
    public void testConstructorConDimensiones() {
        SlowTetris juegoPersonalizado = new SlowTetris(15, 8);
        
        assertEquals("Las filas deben ser 15", 15, juegoPersonalizado.getRows());
        assertEquals("Las columnas deben ser 8", 8, juegoPersonalizado.getCols());
        assertNotNull("Debe tener una pieza actual", juegoPersonalizado.getCurrentPiece());
        assertNotNull("Debe tener una pieza siguiente", juegoPersonalizado.getNextPiece());
    }
    
    /**
     * Verificar que las piezas cambian después de colocarlas
     */
    @Test
    public void testCambioDePiezas() {
        Piece piezaActual = game.getCurrentPiece();
        Piece piezaSiguiente = game.getNextPiece();
        
        game.placePiece(4);
        
        Piece nuevaPiezaActual = game.getCurrentPiece();
  
        assertEquals("La pieza siguiente debe convertirse en la actual", 
                     piezaSiguiente.getType(), nuevaPiezaActual.getType());
        

        assertNotNull("Debe haber una nueva pieza siguiente", game.getNextPiece());
    }
    
    /**
     * Verificar que no se puede colocar pieza en columna inválida
     */
    @Test
    public void testNoColocarEnColumnaInvalida() {
        assertFalse("No debe poder colocar en columna -1", game.placePiece(-1));
        assertFalse("No debe poder colocar en columna 10", game.placePiece(10));
        assertFalse("No debe poder colocar en columna 50", game.placePiece(50));
    }
    
    /**
     * Verificar que getBoard retorna el tablero correcto
     */
    @Test
    public void testGetBoard() {
        int[][] board = game.getBoard();
        
        assertNotNull("El tablero no debe ser null", board);
        assertEquals("El tablero debe tener 20 filas", 20, board.length);
        assertEquals("El tablero debe tener 10 columnas", 10, board[0].length);
    }
}