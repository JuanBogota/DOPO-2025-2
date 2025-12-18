package domain;
import java.awt.Color;

/**
 * Tree class that is an obstacle which does not move in the valley,
 * it does nothing, just acts as an obstacle
 */
public class Tree implements Unit {
    
    private Valley valley;
    private int row;
    private int column;
    private Color color;
    
    public Tree(Valley valley, int row, int column) {
        this.valley = valley;
        this.row = row;
        this.column = column;
        this.color = new Color(34, 139, 34); // Forest green color for a tree
        valley.setUnit(row, column, this);
    }
    
    @Override
    public int shape() {
        return Unit.SQUARE;
    }
    
    @Override
    public Color getColor() {
        return color;
    }
    
    @Override
    public boolean isResource() {
        return false;
    }
    
    @Override
    public boolean isAnimal() {
        return false;
    }
    
    @Override
    public void act() {
    }
    
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }
}
