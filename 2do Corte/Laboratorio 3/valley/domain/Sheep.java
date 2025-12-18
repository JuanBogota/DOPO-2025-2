package domain;

import java.awt.Color;

/**
 * The Sheep class represents a prey (herbivore) within the 
 * Lotka-Volterra ecosystem simulation.
 * Sheep move vertically between the north and south boundaries of the valley, 
 * losing one energy unit per step. They gain energy when near other sheep, 
 * and die when they are close to wolves.
 */
public class Sheep extends Mammal {


    private static final int NORTH = -1;
    private static final int SOUTH = 1;
    private int direction;

    /**
     * Constructs a new Sheep at the specified position within the valley.
     * The sheep starts with 5 energy points and moves initially toward the north.
     *
     * @param valley the Valley instance where this sheep exists
     * @param row the initial row position
     * @param column the initial column position
     */
    public Sheep(Valley valley, int row, int column) {
        super(valley, row, column);
        color = Color.LIGHT_GRAY;
        direction = NORTH;
    }

    /**
     * Returns the visual shape used to represent the sheep in the simulation.
     * Overrides theUnit#shape() method.
     *
     * @return Unit#SQUARE, indicating the sheep is drawn as a rectangle
     */
    @Override
    public int shape() {
        return Unit.SQUARE;
    }

    /**
     * Defines the behavior of the sheep during each simulation cycle.
     * Overrides the Unit#act() method.
     *   If energy reaches zero, the sheep dies.</li>
     *   Sheep bounce vertically between the north and south borders.</li>
     *   Each successful move reduces energy by one.</li>
     */
    @Override
    public void act() {
        if (getEnergy() == 0) {
            die();
            return;
        }

        int maxRow = valley.getSize() - 1;

        
        if (getRow() == 0) {
            direction = SOUTH;
        } else if (getRow() == maxRow) {
            direction = NORTH;
        }

        
        int newRow = getRow() + direction;
        if (move(newRow, getColumn())) {
            step();
        }
    }
}
