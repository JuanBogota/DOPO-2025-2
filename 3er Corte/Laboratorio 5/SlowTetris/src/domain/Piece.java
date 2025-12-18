package domain;
import java.util.Random;

/**
 * Clase que representa una pieza de Tetris
 */
public class Piece {
    private int[][] shape;
    private int type;
    private static Random random = new Random();

    public static final int TYPE_I = 1;
    public static final int TYPE_O = 2;
    public static final int TYPE_T = 3;
    public static final int TYPE_S = 4;
    public static final int TYPE_Z = 5;
    public static final int TYPE_J = 6;
    public static final int TYPE_L = 7;
    

    private static final int[][][] SHAPES = {
        {
            {1, 1, 1, 1}
        },
        {
            {1, 1},
            {1, 1}
        },
        {
            {0, 1, 0},
            {1, 1, 1}
        },
        {
            {0, 1, 1},
            {1, 1, 0}
        },
        {
            {1, 1, 0},
            {0, 1, 1}
        },
        {
            {1, 0, 0},
            {1, 1, 1}
        },
        {
            {0, 0, 1},
            {1, 1, 1}
        }
    };
    
    /**
     * Constructor que crea una pieza aleatoria
     */
    public Piece() {
        this.type = random.nextInt(7) + 1;
        this.shape = copyShape(SHAPES[type - 1]);
    }
    
    /**
     * Constructor que crea una pieza de un tipo específico
     * @param type El tipo de pieza (1-7)
     * @throws IllegalArgumentException Si el tipo es inválido
     */
    public Piece(int type) {
        if (type < 1 || type > 7) {
            throw new IllegalArgumentException("Tipo de pieza inválido: " + type);
        }
        this.type = type;
        this.shape = copyShape(SHAPES[type - 1]);
    }
    
    /**
     * Copia una forma de pieza
     * @param original La forma original
     * @return Una copia de la forma
     */
    private int[][] copyShape(int[][] original) {
        int[][] copy = new int[original.length][original[0].length];
        for (int r = 0; r < original.length; r++) {
            for (int c = 0; c < original[r].length; c++) {
                copy[r][c] = original[r][c];
            }
        }
        return copy;
    }
    
    /**
     * Rota la pieza 90 grados en sentido horario
     */
    public void rotate() {
        int rows = shape.length;
        int cols = shape[0].length;
        int[][] rotated = new int[cols][rows];
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                rotated[c][rows - 1 - r] = shape[r][c];
            }
        }
        
        shape = rotated;
    }
    
    /**
     * Rota la pieza 90 grados en sentido antihorario
     */
    public void rotateCounterClockwise() {
        int rows = shape.length;
        int cols = shape[0].length;
        int[][] rotated = new int[cols][rows];
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                rotated[cols - 1 - c][r] = shape[r][c];
            }
        }
        
        shape = rotated;
    }
    
    /**
     * Obtiene el ancho de la pieza
     */
    public int getWidth() {
        return shape[0].length;
    }
    
    /**
     * Obtiene la altura de la pieza
     */
    public int getHeight() {
        return shape.length;
    }
    
    public int[][] getShape() {
        return shape;
    }
    
    public int getType() {
        return type;
    }
    
    /**
     * Obtiene el nombre de la pieza
     */
    public String getName() {
        return switch (type) {
            case TYPE_I -> "I";
            case TYPE_O -> "O";
            case TYPE_T -> "T";
            case TYPE_S -> "S";
            case TYPE_Z -> "Z";
            case TYPE_J -> "J";
            case TYPE_L -> "L";
            default -> "Unknown";
        };
    }
    
}