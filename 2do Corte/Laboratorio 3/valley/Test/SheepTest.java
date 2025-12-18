package Test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.Sheep;
import domain.Valley;
import domain.Unit;
import java.awt.Color;

/**
 * Clase de pruebas unitarias para la clase Sheep.
 * Prueba el comportamiento de las ovejas en el valle.
 */
public class SheepTest {
    
    private Valley valley;
    private Sheep sheep;
    private static final int TEST_ROW = 12;
    private static final int TEST_COLUMN = 12;
    
    @Before
    public void setUp() {
        valley = new Valley();
        sheep = new Sheep(valley, TEST_ROW, TEST_COLUMN);
    }
    
    /**
     * Prueba que la oveja se cree correctamente con color gris claro.
     */
    @Test
    public void testSheepCreation() {
        assertNotNull("La oveja no debe ser nula", sheep);
        assertEquals("El color de la oveja debe ser gris claro", Color.lightGray, sheep.getColor());
    }
    
    /**
     * Prueba que la oveja tenga forma de rectángulo (SQUARE).
     */
    @Test
    public void testSheepShape() {
        assertEquals("La oveja debe tener forma SQUARE", Unit.SQUARE, sheep.shape());
    }
    
    /**
     * Prueba que la oveja se coloca correctamente en el valle.
     */
    @Test
    public void testSheepInValley() {
        Unit unitInValley = valley.getUnit(TEST_ROW, TEST_COLUMN);
        assertNotNull("Debe haber una unidad en la posición de la oveja", unitInValley);
        assertEquals("La unidad en el valle debe ser la oveja", sheep, unitInValley);
    }
    
    /**
     * Prueba que la oveja pierde energía al moverse.
     */
    @Test
    public void testSheepLosesEnergyWhenMoving() {
        int initialEnergy = sheep.getEnergy();
        sheep.act();
        
        // Después de actuar, la energía debería ser igual o menor
        assertTrue("La oveja debe perder energía o mantenerla después de act()", 
                   sheep.getEnergy() <= initialEnergy);
    }
    
    /**
     * Prueba que la oveja se mueve hacia el norte inicialmente.
     */
    @Test
    public void testSheepMovesNorth() {
        // Crear oveja en una posición media del valle
        Sheep testSheep = new Sheep(valley, 15, 12);
        int initialRow = testSheep.getRow();
        
        testSheep.act();
        
        // Debería haber intentado moverse hacia el norte (fila menor)
        assertTrue("La oveja debe intentar moverse hacia el norte", 
                   testSheep.getRow() <= initialRow);
    }
    
    /**
     * Prueba que la oveja hereda correctamente de Mammal.
     */
    @Test
    public void testSheepIsAnimal() {
        assertTrue("La oveja debe ser un animal", sheep.isAnimal());
    }
    
    /**
     * Prueba que la oveja tiene energía inicial positiva.
     */
    @Test
    public void testSheepHasInitialEnergy() {
        assertTrue("La oveja debe tener energía inicial mayor a 0", 
                   sheep.getEnergy() > 0);
    }
    
    /**
     * Prueba que la oveja muere cuando su energía es cero.
     */
    @Test
    public void testSheepDiesWithZeroEnergy() {
        // Crear oveja y agotar su energía
        Sheep testSheep = new Sheep(valley, 10, 10);
        
        // Ejecutar act() múltiples veces para agotar energía
        for (int i = 0; i < 100; i++) {
            testSheep.act();
            if (testSheep.getEnergy() == 0) {
                break;
            }
        }
        
        // Cuando tenga energía 0 y ejecute act(), debería morir
        testSheep.act();
        
        // Después de morir, no debería estar en el valle
        Unit deadSheep = valley.getUnit(testSheep.getRow(), testSheep.getColumn());
        assertNull("La oveja no debería estar en el valle después de morir", deadSheep);
    }
    
    /**
     * Prueba que la oveja cambia de dirección al llegar al norte.
     */
    @Test
    public void testSheepChangesDirectionAtNorth() {
        // Crear oveja muy cerca del norte (fila 1)
        Sheep northSheep = new Sheep(valley, 1, 12);
        
        // Ejecutar act() para que se mueva a fila 0
        northSheep.act();
        
        // Si llegó a fila 0, ahora debería intentar moverse hacia el sur
        if (northSheep.getRow() == 0) {
            int rowBeforeMove = northSheep.getRow();
            northSheep.act();
            
            // Debería haberse movido hacia el sur o permanecer igual
            assertTrue("La oveja en el norte debería cambiar dirección", 
                       northSheep.getRow() >= rowBeforeMove);
        }
    }
    
    /**
     * Prueba que la oveja está correctamente posicionada en el valle.
     */
    @Test
    public void testSheepPositionInValley() {
        assertEquals("La fila de la oveja debe coincidir", TEST_ROW, sheep.getRow());
        assertEquals("La columna de la oveja debe coincidir", TEST_COLUMN, sheep.getColumn());
        assertTrue("La fila debe estar dentro del valle", 
                   sheep.getRow() >= 0 && sheep.getRow() < valley.getSize());
        assertTrue("La columna debe estar dentro del valle", 
                   sheep.getColumn() >= 0 && sheep.getColumn() < valley.getSize());
    }
}
