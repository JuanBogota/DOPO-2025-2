package Test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.Hay;
import domain.Valley;
import domain.Unit;
import java.awt.Color;

/**
 * Pruebas unitarias para la clase Hay.
 */
public class HayTest {
    
    private Valley valley;
    private Hay hay;
    private static final int TEST_ROW = 2;
    private static final int TEST_COLUMN = 2;
    
    @Before
    public void setUp() {
        valley = new Valley();
        hay = new Hay(valley, TEST_ROW, TEST_COLUMN);
    }
    
    /**
     * Prueba 1: El heno se crea correctamente en color rojo inicial.
     */
    @Test
    public void testHayCreationInitialColor() {
        assertNotNull("El heno no debe ser nulo", hay);
        assertEquals("El heno debe iniciar en color rojo", Color.red, hay.getColor());
    }
    
    /**
     * Prueba 2: El heno tiene forma de rectángulo (SQUARE).
     */
    @Test
    public void testHayShape() {
        assertEquals("El heno debe tener forma SQUARE", Unit.SQUARE, hay.shape());
    }
    
    /**
     * Prueba 3: El heno cambia de color en el ciclo correcto.
     */
    @Test
    public void testHayColorCycle() {
        // Ciclo: 0=rojo, 1=amarillo, 2=rojo, 3=amarillo, 4=rojo
        
        // Inicial: rojo
        assertEquals("Ciclo 0: debe ser rojo", Color.red, hay.getColor());
        
        // Después de 1 act(): amarillo
        hay.act();
        assertEquals("Ciclo 1: debe ser amarillo", Color.yellow, hay.getColor());
        
        // Después de 2 act(): rojo
        hay.act();
        assertEquals("Ciclo 2: debe ser rojo", Color.red, hay.getColor());
        
        // Después de 3 act(): amarillo
        hay.act();
        assertEquals("Ciclo 3: debe ser amarillo", Color.yellow, hay.getColor());
        
        // Después de 4 act(): rojo
        hay.act();
        assertEquals("Ciclo 4: debe ser rojo", Color.red, hay.getColor());
        
        // Después de 5 act(): vuelve a rojo (cicla de nuevo)
        hay.act();
        assertEquals("Ciclo 5 (reinicia): debe ser rojo", Color.red, hay.getColor());
    }
    
    /**
     * Prueba 4: El heno se coloca correctamente en el valle.
     */
    @Test
    public void testHayInValley() {
        Unit unitInValley = valley.getUnit(TEST_ROW, TEST_COLUMN);
        assertNotNull("Debe haber una unidad en la posición del heno", unitInValley);
        assertEquals("La unidad debe ser el heno", hay, unitInValley);
    }
    
    /**
     * Prueba 5: El heno NO es un recurso.
     */
    @Test
    public void testHayIsNotResource() {
        assertFalse("El heno NO debe ser considerado un recurso", hay.isResource());
    }
    
    /**
     * Prueba 6: El heno NO es un animal.
     */
    @Test
    public void testHayIsNotAnimal() {
        assertFalse("El heno NO debe ser considerado un animal", hay.isAnimal());
    }
    
    /**
     * Prueba 7: La posición del heno es correcta.
     */
    @Test
    public void testHayPosition() {
        assertEquals("La fila del heno debe coincidir", TEST_ROW, hay.getRow());
        assertEquals("La columna del heno debe coincidir", TEST_COLUMN, hay.getColumn());
    }
}
