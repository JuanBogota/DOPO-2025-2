import java.awt.geom.*;
import java.lang.Math;
import java.awt.Polygon;

/**
 * A cell of the marble game that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Juan Daniel Bogotá
 * @author  Nicolas Felipe Bernal
 * @version 1.0.  (30 Ago 2025) 
 */

public class Cell{
    private String color;
    private boolean hole;
    private boolean isVisible;
    private int xPosition;
    private int yPosition;
    private int size;
    private Circle marble;

    /**
     * Constructor for objects of class Cell
     * @param size the size of the cell
     * @param xPosition the x position of the cell
     * @param yPosition the y position of the cell
     * @param color the color of the cell
     * @param hole if the cell has a hole or not
     * @param marble the marble inside the cell (if any)
     */
    public Cell(String color, boolean hole) {
        this.size = 200;
        this.xPosition = 0;
        this.yPosition = 0;
        this.color = color;
        this.hole = hole;
        this.isVisible = false;
        this.marble = null;
    }

    /**
     * Verify if the cell has a hole
     * @return true if the cell has a hole, false otherwise
     */
    public boolean hasHole() {
        return hole;
    }

    // Termina 1er miniciclo

    /**
     * Try to insert a marble of the given color into the cell
     * @param marbleColor color of the marble to insert
     * @return true if the marble was inserted, false otherwise
     */
    public boolean in(String marbleColor) {
        if (marble != null) {
            return false;
        }
        if (hole && !color.equals(marbleColor)) {
            return false;
        }
        int marbleSize = (int)(size * 0.2);
        marble = new Circle();
        marble.changeSize(marbleSize);
        marble.changeColor(marbleColor);
        marble.moveHorizontal(xPosition + (size - marbleSize)/2);
        marble.moveVertical(yPosition + (size - marbleSize)/2);
        if (isVisible) {
            marble.makeVisible();
        }
        return true;
    }

    /**
     * Remove the marble from the cell
     * @return color of the marble that was removed, or an empty string if there was no marble
     */
    public String out() {
        if (marble != null) {
            String marbleColor = marble.getColor();
            marble.makeInvisible();
            marble = null;
            return marbleColor;
        }
        return "";
    }

    /**
     * Verify if the cell has a marble
     * @return true if the cell has a marble, false otherwise
     */
    public boolean hasMarble() {
        return marble != null;
    }

    /**
     * Verify if the cell is in a correct state
     * @return true if the cell is in a correct state, false otherwise*/
    public boolean isOk() {
        if (!hole || marble == null) {
            return true;
        }
        return color.equals(marble.getColor());
    }

    // Termina 2do miniciclo
    
    /**
     * Draw the cell on the canvas
     */
    private void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            // Dibuja el cuadrado de la celda
            int[] xpoints = {xPosition, xPosition + size, xPosition + size, xPosition};
            int[] ypoints = {yPosition, yPosition, yPosition + size, yPosition + size};
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 4));
            
            // Si tiene hoyo, dibuja un círculo negro
            if (hole) {
                int holeSize = (int)(size * 0.6);
                int holeX = xPosition + (size - holeSize)/2;
                int holeY = yPosition + (size - holeSize)/2;
                int[] xpointsHole = {holeX, holeX + holeSize, holeX + holeSize, holeX};
                int[] ypointsHole = {holeY, holeY, holeY + holeSize, holeY + holeSize};
                canvas.draw(this, "black", new Polygon(xpointsHole, ypointsHole, 4));
            }
            
            // Si hay una canica, la hace visible
            if (marble != null) {
                marble.makeVisible();
            }
        }
    }
    
    /**
     * Erase the cell from the canvas
     */
    private void erase() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }

    /**
     * move the cell a few pixels to the right.
     * @param x nueva posición en x
     * @param y nueva posición en y
     */
    public void moveTo(int x, int y) {
        erase();
        xPosition = x;
        yPosition = y;
        if (marble != null) {
            int marbleSize = (int)(size * 0.8);
            marble.moveHorizontal(x + (size - marbleSize)/2);
            marble.moveVertical(y + (size - marbleSize)/2);
        }
        draw();
    }

    /**
     * Make the cell and its content visible
     */
    public void makeVisible() {
        isVisible = true;
        draw();
    }
    
    /**
     * Make the cell and its content invisible
     */
    public void makeInvisible() {
        erase();
        if (marble != null) {
            marble.makeInvisible();
        }
        isVisible = false;
    }

}