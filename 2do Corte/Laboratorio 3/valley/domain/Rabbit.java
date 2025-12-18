package domain;
import java.awt.Color;

/**
 * Rabbit class — moves diagonally, eats hay to recover energy, 
 * and dies if it runs out of energy.
 */
public class Rabbit extends Mammal {
    
    private static final int NORTHEAST = 1;  
    private static final int SOUTHWEST = -1; 
    private int direction;
    
    public Rabbit(Valley valley, int row, int column) {
        super(valley, row, column);
        color = new Color(139, 69, 19); // Brown color
        direction = NORTHEAST;
    }
    
    @Override
    public int shape() {
        return Unit.ROUND;
    }
    
    @Override
    public void act() {
        if (getEnergy() == 0) {
            die();
            return;
        }
        
        if (tryToEat()) {
            return;
        }
        int newRow = getRow() + direction;
        int newColumn = getColumn() + direction;
        
        Valley v = getValley();
        if (newRow < 0 || newRow >= v.getSize() || 
            newColumn < 0 || newColumn >= v.getSize()) {
            direction = -direction;
            newRow = getRow() + direction;
            newColumn = getColumn() + direction;
        }
        
        move(newRow, newColumn);
    }
    
    private boolean tryToEat() {
        Valley v = getValley();
        
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                int newRow = getRow() + dr;
                int newCol = getColumn() + dc;
                
                if (newRow >= 0 && newRow < v.getSize() && 
                    newCol >= 0 && newCol < v.getSize()) {
                    
                    Unit unit = v.getUnit(newRow, newCol);
                    
                    if (unit instanceof Hay) {
                        v.setUnit(newRow, newCol, null); 
                        eat();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private Valley getValley() {
        try {
            java.lang.reflect.Field field = Mammal.class.getDeclaredField("valley");
            field.setAccessible(true);
            return (Valley) field.get(this);
        } catch (Exception e) {
            return null;
        }
    }
}
