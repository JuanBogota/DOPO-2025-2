package domain;

import java.awt.Color;

/**
 * The Hay class represents a static hay bundle placed within the valley.
 * Hay units do not move or consume energy — they simply exist as renewable
 * food resources for herbivores. Their visual appearance alternates cyclically
 * between red and yellow across simulation steps:
 */
public class Hay implements Unit {


    private int row;
    private int column;
    private int colorCycle;

    /**
     * Creates a new Hay bundle at the specified position inside the valley.
     * The hay starts in the red color (cycle 0).
     *
     * @param valley the Valley instance where this hay is placed
     * @param row the row position
     * @param column the column position
     */
    public Hay(Valley valley, int row, int column) {
        this.row = row;
        this.column = column;
        this.colorCycle = 0; // Starts as red
        valley.setUnit(row, column, this);
    }

    /**
     * Returns the geometric shape of the hay bundle.
     *
     * @return Unit#SQUARE, indicating a rectangular or square shape
     */
    @Override
    public int shape() {
        return Unit.SQUARE;
    }

    /**
     * Returns the current color of the hay based on its animation cycle.
     * @return the current Color of the hay
     */
    @Override
    public Color getColor() {
        if (colorCycle == 0 || colorCycle == 2 || colorCycle == 4) {
            return Color.red;
        } else {
            return Color.yellow;
        }
    }

    /**
     * Indicates whether the unit represents a resource.
     * Although hay is food, it is not considered a "resource" in the system.
     * @return false — hay is not a general resource
     */
    @Override
    public boolean isResource() {
        return false;
    }

    /**
     * Indicates whether the unit is an animal.
     * @return false — hay is not an animal
     */
    @Override
    public boolean isAnimal() {
        return false;
    }

    /**
     * Defines the behavior of hay per simulation tick.
     * Each time the simulation advances, the hay progresses one step
     * in its color cycle.
     */
    @Override
    public void act() {
        colorCycle = (colorCycle + 1) % 5;
    }

    /**
     * Returns the current row position of the hay bundle.
     * @return the row index
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the current column position of the hay bundle.
     * @return the column index
     */
    public int getColumn() {
        return column;
    }
}
