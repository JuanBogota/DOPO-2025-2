package Test;
import domain.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Tree.
 * Checks the behavior of the Tree obstacle.
 */
public class TreeTest {

    private Valley valley;

    @BeforeEach
    public void setUp() {
        valley = new Valley();
    }

    /**
     * Checks that the tree is created correctly with its initial attributes
     */
    @Test
    public void testTreeCreation() {
        // Arrange & Act
        Tree tree = new Tree(valley, 5, 5);

        // Assert
        assertNotNull(tree, "The tree should be created");
        assertEquals(5, tree.getRow(), "Initial row should be 5");
        assertEquals(5, tree.getColumn(), "Initial column should be 5");
        assertEquals(Unit.SQUARE, tree.shape(), "The tree should have a square shape");
        assertFalse(tree.isAnimal(), "The tree should not be an animal");
        assertFalse(tree.isResource(), "The tree should not be a resource");
        assertNotNull(tree.getColor(), "The tree should have an assigned color");
    }

    /**
     * Checks that the tree does not perform any action
     */
    @Test
    public void testTreeDoesNotAct() {
        Tree tree = new Tree(valley, 3, 3);

        // Capture the initial position
        int initialRow = tree.getRow();
        int initialColumn = tree.getColumn();

        // Act
        tree.act();

        // Assert
        assertEquals(initialRow, tree.getRow(), "The tree should not move vertically");
        assertEquals(initialColumn, tree.getColumn(), "The tree should not move horizontally");
    }
    
     /**
     * Verifies that the rabbit cannot move into a cell occupied by a tree
     */
    @Test
    public void testTreeExistsAsObstacle() {
    Tree tree = new Tree(valley, 5, 5);
    assertEquals(5, tree.getRow());
    assertEquals(5, tree.getColumn());
    assertFalse(tree.isAnimal());
    assertFalse(tree.isResource());
    }

}
