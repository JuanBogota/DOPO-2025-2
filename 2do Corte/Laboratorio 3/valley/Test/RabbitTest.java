package Test;
import domain.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Rabbit.
 * Checks the behavior of the new mammal Rabbit.
 */
public class RabbitTest {
    
    private Valley valley;
    
    @BeforeEach
    public void setUp() {
        valley = new Valley();
    }
    
    /**
     * Checks that the rabbit is created correctly
     * with its initial attributes
     */
    @Test
    public void testRabbitCreation() {
        // Arrange & Act
        Rabbit rabbit = new Rabbit(valley, 10, 10);
        
        // Assert
        assertNotNull(rabbit, "The rabbit should be created");
        assertEquals(10, rabbit.getRow(), "Initial row should be 10");
        assertEquals(10, rabbit.getColumn(), "Initial column should be 10");
        assertEquals(100, rabbit.getEnergy(), "Initial energy should be 100");
        assertTrue(rabbit.isAnimal(), "The rabbit should be an animal");
        assertEquals(Unit.ROUND, rabbit.shape(), "The rabbit should have a circular shape");
        assertNotNull(rabbit.getColor(), "The rabbit should have an assigned color");
    }
    
    /**
     * Checks that the rabbit changes direction when it reaches the valley boundaries
     */
    @Test
    public void testRabbitChangesDirectionAtBoundary() {
        Rabbit rabbit = new Rabbit(valley, 1, 1);
        
        for (int i = 0; i < 5; i++) {
            rabbit.act();
        }
        
        assertTrue(rabbit.getRow() >= 0 && rabbit.getRow() < valley.getSize(),
            "The rabbit should stay within the valley boundaries (rows)");
        assertTrue(rabbit.getColumn() >= 0 && rabbit.getColumn() < valley.getSize(),
            "The rabbit should stay within the valley boundaries (columns)");
    }
}
