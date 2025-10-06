import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * The test class DesicionTreeCalculatorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DesicionTreeCalculatorTest{
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
    }

    // Pruebas para create(String name)
    @Test
    public void shouldCreateNewDecisionTree() {
        assertTrue(DecisionTreeCalculator.create("arbol1"));
        assertTrue(DecisionTreeCalculator.create("arbol2"));
    }

    @Test
    public void shouldNotCreateDuplicateTree() {
        assertTrue(DecisionTreeCalculator.create("arbol1"));
        assertFalse(DecisionTreeCalculator.create("arbol1"));
    }

    @Test
    public void shouldNotCreateTreeWithNullName() {
        assertFalse(DecisionTreeCalculator.create(null));
    }

    // Pruebas para assign(String a, String root)
    @Test
    public void shouldAssignRootToTree() {
        DecisionTreeCalculator.create("arbol1");
        assertTrue(DecisionTreeCalculator.assign("arbol1", "¿Tienes hambre?"));
    }

    @Test
    public void shouldNotAssignToNonexistentTree() {
        assertFalse(DecisionTreeCalculator.assign("arbolInexistente", "¿Tienes hambre?"));
    }

    @Test
    public void shouldNotAssignNullRoot() {
        DecisionTreeCalculator.create("arbol1");
        assertFalse(DecisionTreeCalculator.assign("arbol1", null));
    }

    // Pruebas para toString(String decisionTree)
    @Test
    public void shouldReturnTreeString() {
        DecisionTreeCalculator.create("arbol1");
        DecisionTreeCalculator.assign("arbol1", "¿Tienes hambre?");
        String expected = "(¿Tienes hambre?)";
        assertEquals(expected, DecisionTreeCalculator.toString("arbol1"));
    }

    @Test
    public void shouldReturnEmptyForNonexistentTree() {
        assertEquals("", DecisionTreeCalculator.toString("arbolInexistente"));
    }

   
    @Test
    public void shouldReturnEmptyForNullTreeName() {
        assertEquals("", DecisionTreeCalculator.toString(null));
    }


    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */
    @After
    public void tearDown() 
    {
    }
}

