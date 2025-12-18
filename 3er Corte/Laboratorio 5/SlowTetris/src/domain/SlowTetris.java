package domain;
import java.util.*;

/**
 * Clase principal del juego SlowTetris
 * Gestiona el tablero y la lógica del juego
 */
public class SlowTetris {
    private int[][] board;
    private int rows;
    private int cols;
    private Piece currentPiece;
    private Piece nextPiece;
    private int score;
    private int highScore;
    private boolean gameOver;
    
    public static final int EMPTY = 0;
    
    /**
     * Constructor del juego
     * Por defecto crea un tablero de 20x10
     */
    public SlowTetris() {
        this(20, 10);
    }
    
    /**
     * Constructor con dimensiones personalizadas
     */
    public SlowTetris(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new int[rows][cols];
        this.score = 0;
        this.highScore = 0;
        this.gameOver = false;
        initializeBoard();
        generateNextPiece();
        generateNextPiece();
    }
    
    /**
     * Inicializa el tablero con valores vacíos
     */
    private void initializeBoard() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board[r][c] = EMPTY;
            }
        }
    }
    
    /**
     * Genera una nueva pieza aleatoria
     */
    private void generateNextPiece() {
        if (currentPiece == null) {
            currentPiece = new Piece();
        } else {
            currentPiece = nextPiece;
        }
        nextPiece = new Piece();
    }
    
    /**
     * Coloca la pieza actual en la columna especificada
     * @param col Columna donde se colocará la pieza
     * @return true si se pudo colocar, false si el juego terminó
     */
    public boolean placePiece(int col) {
        if (gameOver || col < 0 || col >= cols) {
            return false;
        }
        
        int row = findLowestRow(col);
        
        if (row < 0) {
            gameOver = true;
            return false;
        }
        
        int[][] shape = currentPiece.getShape();
        int pieceType = currentPiece.getType();
        
        for (int r = 0; r < shape.length; r++) {
            for (int c = 0; c < shape[r].length; c++) {
                if (shape[r][c] == 1) {
                    int boardRow = row + r;
                    int boardCol = col + c;
                    
                    if (boardRow >= 0 && boardRow < rows && boardCol >= 0 && boardCol < cols) {
                        board[boardRow][boardCol] = pieceType;
                    } else if (boardRow < 0) {
                        gameOver = true;
                        return false;
                    }
                }
            }
        }
  
        int linesCleared = clearLines();
        updateScore(linesCleared);

        generateNextPiece();
        
        return true;
    }
    
    /**
     * Encuentra la fila más baja donde la pieza puede ser colocada
     */
    private int findLowestRow(int col) {
        int[][] shape = currentPiece.getShape();
        int lowestRow = -1;
        
        for (int c = 0; c < shape[0].length; c++) {
            int pieceCol = col + c;
            
            if (pieceCol < 0 || pieceCol >= cols) {
                continue;
            }
    
            int pieceBottom = -1;
            for (int r = shape.length - 1; r >= 0; r--) {
                if (shape[r][c] == 1) {
                    pieceBottom = r;
                    break;
                }
            }
            
            if (pieceBottom == -1) continue;
            
            int targetRow = rows - 1;
            for (int r = rows - 1; r >= 0; r--) {
                if (board[r][pieceCol] != EMPTY) {
                    targetRow = r - 1;
                    break;
                }
            }
            
            int possibleRow = targetRow - pieceBottom;
            
            if (lowestRow == -1 || possibleRow < lowestRow) {
                lowestRow = possibleRow;
            }
        }
        
        return lowestRow;
    }
    
    /**
     * Verifica y elimina las líneas completas
     * @return Número de líneas eliminadas
     */
    private int clearLines() {
        int linesCleared = 0;
        
        for (int r = rows - 1; r >= 0; r--) {
            if (isLineComplete(r)) {
                removeLine(r);
                linesCleared++;
                r++;
            }
        }
        
        return linesCleared;
    }
    
    /**
     * Verifica si una línea está completa
     */
    private boolean isLineComplete(int row) {
        for (int c = 0; c < cols; c++) {
            if (board[row][c] == EMPTY) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Elimina una línea y baja las superiores
     */
    private void removeLine(int row) {
        for (int r = row; r > 0; r--) {
            for (int c = 0; c < cols; c++) {
                board[r][c] = board[r - 1][c];
            }
        }
        for (int c = 0; c < cols; c++) {
            board[0][c] = EMPTY;
        }
    }
    
    /**
     * Actualiza el puntaje según las líneas eliminadas
     */
    private void updateScore(int linesCleared) {
        int points = switch (linesCleared) {
            case 1 -> 100;
            case 2 -> 300;
            case 3 -> 500;
            case 4 -> 800;
            default -> linesCleared * 100;
        };
        
        score += points;
        
        if (score > highScore) {
            highScore = score;
        }
    }
    
    /**
     * Verifica si una columna es válida para colocar la pieza
     */
    public boolean isValidColumn(int col) {
        if (col < 0 || col >= cols) {
            return false;
        }
        
        int[][] shape = currentPiece.getShape();

        return col + shape[0].length <= cols;
    }
    
    /**
     * Reinicia el juego
     */
    public void reset() {
        initializeBoard();
        score = 0;
        gameOver = false;
        generateNextPiece();
        generateNextPiece();
    }
    
    /**
     * Obtiene el estado actual del tablero
     * @return El tablero del juego
     */
    public int[][] getBoard() {
        return board;
    }
    
    /**
     * Obtiene el número de filas del tablero
     * @return Número de filas
     */
    public int getRows() {
        return rows;
    }
    
    /**
     * Obtiene el número de columnas del tablero
     * @return Número de columnas
     */
    public int getCols() {
        return cols;
    }
    
    /**
     * Obtiene la pieza actual
     * @return La pieza actual
     */
    public Piece getCurrentPiece() {
        return currentPiece;
    }

    /**
     * Obtiene la siguiente pieza
     * @return La siguiente pieza
     */
    public Piece getNextPiece() {
        return nextPiece;
    }
    
    /**
     * Obtiene el puntaje actual
     * @return El puntaje actual
     */
    public int getScore() {
        return score;
    }
    
    /**
     * Obtiene el puntaje más alto
     * @return El puntaje más alto
     */
    public int getHighScore() {
        return highScore;
    }
    
    /**
     * Verifica si el juego ha terminado
     * @return true si el juego terminó, false en caso contrario
     */
    public boolean isGameOver() {
        return gameOver;
    }
    
    /**
     * Obtiene el valor de una celda específica del tablero
     * @param row Fila de la celda
     * @param col Columna de la celda
     * @return El valor de la celda (EMPTY si está vacía)
     */    
    public int getCellValue(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return board[row][col];
        }
        return EMPTY;
    }
}
