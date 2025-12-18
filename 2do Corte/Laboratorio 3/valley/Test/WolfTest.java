package Test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.Wolf;
import domain.Valley;
import domain.Unit;
import java.awt.Color;

/**
 * Clase de pruebas unitarias para la clase Wolf.
 * Prueba el comportamiento del lobo en el valle.
 */
public class WolfTest {
    
    private Valley valley;
    private Wolf wolf;
    private static final int TEST_ROW = 5;
    private static final int TEST_COLUMN = 5;
    
    @Before
    public void setUp() {
        valley = new Valley();
        wolf = new Wolf(valley, TEST_ROW, TEST_COLUMN);
    }
    
    /**
     * Prueba que el lobo se cree correctamente con posición y color.
     */
    @Test
    public void testWolfCreation() {
        assertNotNull("El lobo no debe ser nulo", wolf);
        assertEquals("El color del lobo debe ser negro", Color.black, wolf.getColor());
    }
    
    /**
     * Prueba que el lobo tenga la forma correcta (ROUND).
     */
    @Test
    public void testWolfShape() {
        assertEquals("El lobo debe tener forma ROUND", Unit.ROUND, wolf.getShape());
    }
    
    /**
     * Prueba que el lobo está correctamente ubicado en el valle.
     */
    @Test
    public void testWolfPositionInValley() {
        assertEquals("La fila del lobo debe ser la especificada", TEST_ROW, wolf.getRow());
        assertEquals("La columna del lobo debe ser la especificada", TEST_COLUMN, wolf.getColumn());
        assertTrue("La fila del lobo debe estar dentro del valle", 
                   wolf.getRow() >= 0 && wolf.getRow() < valley.getSize());
        assertTrue("La columna del lobo debe estar dentro del valle", 
                   wolf.getColumn() >= 0 && wolf.getColumn() < valley.getSize());
    }
    
    /**
     * Prueba que el lobo tiene energía inicial.
     */
    @Test
    public void testWolfInitialEnergy() {
        assertTrue("El lobo debe tener energía inicial mayor a 0", wolf.getEnergy() > 0);
    }
    
    /**
     * Prueba que el lobo hereda correctamente de Animal.
     */
    @Test
    public void testWolfIsAnimal() {
        assertTrue("Wolf debe ser un animal", wolf.isAnimal());
    }
    
    /**
     * Prueba que el lobo se coloca en el valle correctamente.
     */
    @Test
    public void testWolfInValley() {
        Unit unitInValley = valley.getUnit(TEST_ROW, TEST_COLUMN);
        assertNotNull("Debe haber una unidad en la posición del lobo", unitInValley);
        assertEquals("La unidad en el valle debe ser el lobo", wolf, unitInValley);
    }
    
    /**
     * Prueba que act() disminuye la energía o mueve al lobo.
     */
    @Test
    public void testWolfActDecreaseEnergy() {
        int initialEnergy = wolf.getEnergy();
        wolf.act();
        int finalEnergy = wolf.getEnergy();
        
        // Después de act(), la energía debería ser igual o menor
        assertTrue("La energía no debe aumentar después de act()", 
                   finalEnergy <= initialEnergy);
    }
}
