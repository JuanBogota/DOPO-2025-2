import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class DecisionTreeTest{

    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
    }

    /**
     * Tests the creation of the smallest possible decision tree
     * (only the root).
     */
     @Test
    public void shouldCreateSmallestDecisionTree(){
        DecisionTree dt=new DecisionTree("Be hungry");
        assertEquals(1, dt.nodes());
        assertEquals(1, dt.height());      
    }    
   
   /**
     * Tests the creation of a tree with multiple levels
     * and verifies the correct number of nodes and height.
     */
    @Test
    public void shouldCreateOtherDecisionTree(){
        DecisionTree dt=new DecisionTree("Be hungry");
        assertTrue(dt.add("Be hungry","Have 25", "Go to sleep"));
        assertTrue(dt.add("Have 25", "Go to restaurant", "Buy a hamburger"));
        assertEquals(5, dt.nodes());
        assertEquals(3, dt.height());
    }    
    
    /**
     * Tests that questions and decisions are correctly differentiated.
     */
    @Test
    public void shoulDifferentiateQuestionsDecisions(){
        DecisionTree dt=new DecisionTree("Be hungry");
        dt.add("Be hungry","Have 25", "Go to sleep");
        dt.add("Have 25", "Go to restaurant", "Buy a hamburger");
        assertTrue(dt.isDecision("Buy a hamburger"));        
        assertFalse(dt.isQuestion("Buy a hamburger"));
        assertTrue(dt.isQuestion("Have 25")); 
        assertFalse(dt.isDecision("Have 25"));    
    }   

    /**
     * Tests that duplicate nodes are not allowed.
     */ 
    @Test
    public void shouldNotHaveDuplicateNodes(){
        DecisionTree dt=new DecisionTree("Be hungry");
        assertTrue(dt.add("Be hungry","Have 25", "Go to sleep"));
        assertFalse(dt.add("Have 25","Be hungry", "Go to shop"));
        assertEquals(3, dt.nodes());
        assertEquals(2, dt.height());
    }    

    /**
     * Tests that a node cannot have more than two children.
     */
    @Test
    public void shouldNotHaveMoreThanTwoChildrens(){
        DecisionTree dt=new DecisionTree("Be hungry");
        assertTrue(dt.add("Be hungry","Have 25", "Go to sleep"));
        assertFalse(dt.add("Be hungry","Be to restaurant", "Go to shop"));
        assertEquals(3, dt.nodes());
        assertEquals(2, dt.height());
    }    
    
    /**
     * Tests that the tree is not case-sensitive.
     */
    @Test
    public void shouldNotBeCaseSensitive(){     
        DecisionTree dt=new DecisionTree("Be Hungry");
        assertTrue(dt.add("Be hungry","HAVE 25", "Go to sleep"));
        assertTrue(dt.add("Have 25", "Go to restaurant", "BUY a hamburger"));
        assertEquals(5, dt.nodes());
        assertEquals(3, dt.height());
    }
    
    /**
     * Tests the correct string representation of the decision tree.
     */
    @Test
    public void shouldConvertToString(){
        DecisionTree dt=new DecisionTree("Be hungry");
        assertTrue(dt.add("Be hungry","Have 25", "Go to sleep"));
        assertTrue(dt.add("Have 25", "Go to restaurant", "Buy a hamburger"));
        String data="(be hungry yes (have 25 yes (go to restaurant) no (buy a hamburger)) no (go to sleep))";
        assertEquals(data, dt.toString());
    }

    /**
     * Tests equality between two equivalent trees.
     */
    @Test
    public void shouldValityEquality(){
        DecisionTree dt=new DecisionTree("Be hungry");
        assertTrue(dt.add("Be hungry","Have 25", "Go to sleep"));
        assertTrue(dt.add("Have 25", "Go to restaurant", "Buy a hamburger"));
        DecisionTree dto=new DecisionTree("Be Hungry");
        assertTrue(dto.add("Be hungry","HAVE 25", "Go to sleep"));
        assertTrue(dto.add("Have 25", "Go to restaurant", "Buy a hamburger"));
        assertEquals(dt,dto);
    }    

    /**
     * Test case that should pass successfully.
     * 
     * This test verifies that two equal values are correctly 
     * recognized as equal by the assertion.
     */
    @Test
    public void shouldPass() {
        int a = 5;
        int b = 5;
        // Esto es verdadero, entonces la prueba pasa
        assertEquals(a, b);
    }
    
    /**
     * Test case that should fail.
     * 
     * This test deliberately compares two different values, 
     * so the assertion will fail.
     */
    @Test
    public void shouldFail() {
        int a = 5;
        int b = 10;
        // Esto es falso, entonces la prueba falla
        assertEquals(a, b);
    }
    
    /**
     * Test case that should cause an error.
     * 
     * This test triggers a runtime exception (NullPointerException), 
     * which results in a test error.
     */
    @Test
    public void shouldErr() {
        String str = null;
        str.length();
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
