package Test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.Valley;
import domain.Unit;
import domain.Wolf;
import java.awt.Color;

/**
 * Clase de pruebas unitarias para la clase Valley.
 * Prueba la funcionalidad del valle como contenedor de unidades.
 */
public class ValleyTest {
    
    private Valley valley;
    private Wolf testWolf;
    private static final int TEST_ROW = 12;
    private static final int TEST_COLUMN = 12;
    
    @Before
    public void setUp() {
        valley = new Valley();
        testWolf = new Wolf(valley, TEST_ROW, TEST_COLUMN);
    }
    
    /**
     * Prueba que el valle se cree con el tamaño correcto.
     */
    @Test
    public void testValleySize() {
        assertEquals("El tamaño del valle debe ser 25", 25, valley.getSize());
    }
    
    /**
     * Prueba que el valle se inicializa con espacios vacíos.
     */
    @Test
    public void testValleyHasEmptySpaces() {
        Valley emptyValley = new Valley();
        boolean hasEmpty = false;
        
        for (int r = 0; r < emptyValley.getSize(); r++) {
            for (int c = 0; c < emptyValley.getSize(); c++) {
                if (emptyValley.isEmpty(r, c)) {
                    hasEmpty = true;
                    break;
                }
            }
            if (hasEmpty) break;
        }
        
        assertTrue("El valle debería tener al menos algunos espacios vacíos", hasEmpty);
    }
    
    /**
     * Prueba obtener una unidad del valle.
     */
    @Test
    public void testGetUnit() {
        Unit retrievedUnit = valley.getUnit(TEST_ROW, TEST_COLUMN);
        assertNotNull("La unidad no debe ser nula", retrievedUnit);
        assertEquals("La unidad obtenida debe ser el lobo", testWolf, retrievedUnit);
    }
    
    /**
     * Prueba que isEmpty funciona correctamente para posiciones vacías.
     */
    @Test
    public void testIsEmptyWithEmptyPosition() {
        Valley emptyValley = new Valley();
        assertTrue("La posición (0, 0) debe estar vacía", emptyValley.isEmpty(0, 0));
    }
    
    /**
     * Prueba que isEmpty funciona correctamente para posiciones ocupadas.
     */
    @Test
    public void testIsEmptyWithOccupiedPosition() {
        assertFalse("La posición ocupada por el lobo no debe estar vacía", 
                    valley.isEmpty(TEST_ROW, TEST_COLUMN));
    }
    
    /**
     * Prueba que isEmpty retorna false para posiciones fuera del valle.
     */
    @Test
    public void testIsEmptyOutOfBounds() {
        assertFalse("Las posiciones fuera del valle no deben considerarse vacías", 
                    valley.isEmpty(-1, 0));
        assertFalse("Las posiciones fuera del valle no deben considerarse vacías", 
                    valley.isEmpty(0, 25));
        assertFalse("Las posiciones fuera del valle no deben considerarse vacías", 
                    valley.isEmpty(25, 25));
    }
    
    /**
     * Prueba el método neighborsEquals con vecinos de la misma clase.
     */
    @Test
    public void testNeighborsEqualsWithSameClass() {
        Wolf wolf1 = new Wolf(valley, 10, 10);
        Wolf wolf2 = new Wolf(valley, 10, 11);
        Wolf wolf3 = new Wolf(valley, 11, 10);
        
        int neighbors = valley.neighborsEquals(10, 10);
        assertEquals("Wolf en (10,10) debe tener 2 vecinos lobo", 2, neighbors);
    }
    
    /**
     * Prueba que neighborsEquals retorna 0 cuando no hay vecinos de la misma clase.
     */
    @Test
    public void testNeighborsEqualsWithoutSameClass() {
        Valley emptyValley = new Valley();
        Wolf wolf = new Wolf(emptyValley, 15, 15);
        
        int neighbors = emptyValley.neighborsEquals(15, 15);
        assertEquals("Wolf sin vecinos debe tener 0 vecinos", 0, neighbors);
    }
    
    /**
     * Prueba que neighborsEquals retorna 0 para posiciones vacías.
     */
    @Test
    public void testNeighborsEqualsOnEmptyPosition() {
        Valley emptyValley = new Valley();
        int neighbors = emptyValley.neighborsEquals(5, 5);
        assertEquals("Posición vacía no debe tener vecinos", 0, neighbors);
    }
    
    /**
     * Prueba que neighborsEquals retorna 0 para posiciones fuera del valle.
     */
    @Test
    public void testNeighborsEqualsOutOfBounds() {
        int neighbors = valley.neighborsEquals(-1, -1);
        assertEquals("Posición fuera del valle debe retornar 0", 0, neighbors);
    }
    
    /**
     * Prueba máximo de vecinos posibles (8 vecinos).
     */
    @Test
    public void testNeighborsEqualsMaximum() {
        Valley newValley = new Valley();
        Wolf centerWolf = new Wolf(newValley, 12, 12);
        
        // Agregar wolves en todas las 8 posiciones adyacentes
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr != 0 || dc != 0) {
                    Wolf neighbor = new Wolf(newValley, 12 + dr, 12 + dc);
                }
            }
        }
        
        int neighbors = newValley.neighborsEquals(12, 12);
        assertEquals("Wolf debe tener 8 vecinos wolf", 8, neighbors);
    }
    
    /**
     * Prueba que se pueden obtener múltiples unidades.
     */
    @Test
    public void testMultipleUnitsRetrieval() {
        Valley newValley = new Valley();
        Wolf wolf1 = new Wolf(newValley, 1, 1);
        Wolf wolf2 = new Wolf(newValley, 2, 2);
        Wolf wolf3 = new Wolf(newValley, 3, 3);
        
        assertEquals("Primera unidad debe ser wolf1", wolf1, newValley.getUnit(1, 1));
        assertEquals("Segunda unidad debe ser wolf2", wolf2, newValley.getUnit(2, 2));
        assertEquals("Tercera unidad debe ser wolf3", wolf3, newValley.getUnit(3, 3));
    }
    
    @Test
    public void testTicTacExecutesAllUnits() {
        Valley valley = new Valley();
        Wolf wolf = new Wolf(valley, 10, 10);
        int initialEnergy = wolf.getEnergy();
        
        valley.ticTac();
        
        assertTrue("Después de ticTac, el lobo debe tener menos energía", 
                   wolf.getEnergy() < initialEnergy);
    }
}
