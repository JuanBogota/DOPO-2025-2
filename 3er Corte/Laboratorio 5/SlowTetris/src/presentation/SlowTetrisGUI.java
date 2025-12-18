package presentation;

import domain.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
 
public class SlowTetrisGUI extends JFrame{
   
    //paneles
    private JPanel boardPanel;
    private JPanel infoPanel;
    private JPanel titlePanel;
    private JPanel nextPiecePanel;
    private JPanel scorePanel;
    private JPanel highScorePanel;
    
    //etiquetas
    private JLabel scoreLabel;
    private JLabel highScoreLabel;

    //colores
    private Color backgroundColor = new Color(20, 20, 40);
    private Color gridColor = new Color(60, 60, 80);
    private Color colorI = Color.CYAN;      // Pieza l (línea)
    private Color colorO = Color.YELLOW;    // Pieza O (cuadrado)
    private Color colorT = Color.MAGENTA;   // Pieza T
    private Color colorS = Color.GREEN;     // Pieza S
    private Color colorZ = Color.RED;       // Pieza Z
    private Color colorJ = Color.BLUE;      // Pieza J
    private Color colorL = Color.ORANGE;    // Pieza L

    private SlowTetris game;
    private int selectedColumn = -1;
    private int pieceColumn = 4;

    private JPanel previewTopPanel;
 
    public SlowTetrisGUI(){
        setTitle("SlowTetris");
        prepareElements();
        prepareActions();
        prepareElementsMenu();
        prepareActionMenu();
    }
 
    public static void main(String[] args){
        SlowTetrisGUI gui = new SlowTetrisGUI();
        gui.setVisible(true);
    }
 
    private void prepareElements(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int height = size.height;
        int width = size.width;
        setSize(height/2, width/2);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(backgroundColor);

        
        JPanel boardContainer = new JPanel(new BorderLayout(0, 5));
        boardContainer.setBackground(backgroundColor);
        
        prepareTopPreviewPanel();
        prepareElementsBoard();

        boardContainer.add(previewTopPanel, BorderLayout.NORTH);
        boardContainer.add(boardPanel, BorderLayout.CENTER);
        
        add(boardContainer, BorderLayout.WEST);
        
        prepareElementsInfo();
    }

    private void prepareActions(){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
           @Override 
            public void windowClosing(WindowEvent e){
                exit();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });
        
        setFocusable(true);
        requestFocus();
    }

    private void handleKeyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {
                if (pieceColumn > 0) {
                    pieceColumn--;
                    previewTopPanel.repaint();
                }
            }
                
            case KeyEvent.VK_RIGHT -> {
                Piece currentPiece = game.getCurrentPiece();
                if (currentPiece != null && pieceColumn + currentPiece.getWidth() < game.getCols()) {
                    pieceColumn++;
                    previewTopPanel.repaint();
                }
            }
                
            case KeyEvent.VK_SPACE, KeyEvent.VK_ENTER -> {
                if (!game.isGameOver() && game.isValidColumn(pieceColumn)) {
                    game.placePiece(pieceColumn);
                    pieceColumn = 4; 
                    updateGameDisplay();
                }
            }
            case KeyEvent.VK_R -> {
                if (game.getCurrentPiece() != null) {
                    game.getCurrentPiece().rotate();
                    
                    Piece piece = game.getCurrentPiece();
                    if (pieceColumn + piece.getWidth() > game.getCols()) {
                        pieceColumn = game.getCols() - piece.getWidth();
                    }
                    
                    previewTopPanel.repaint();
                }
            }
            case KeyEvent.VK_N -> newGame();
        }
    }

    private void newGame() {
        game.reset();
        selectedColumn = -1;
        pieceColumn = 4;
        refresh();
        requestFocus();
    }

    private void updateGameDisplay() {
        refresh();
    }
    
    
    private void exit(){
        int result = JOptionPane.showConfirmDialog(this, 
                    "Are you sure you want to exit?", "Exit SlowTetris", 
                    JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

    public void prepareElementsMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenu optionsMenu = new JMenu("Options");

        JMenuItem newGameItem = new JMenuItem("New Game");
        JMenuItem openGameItem = new JMenuItem("Open Game");
        JMenuItem saveGameItem = new JMenuItem("Save Game");
        JMenuItem exitItem = new JMenuItem("Exit Game");

        JMenuItem modifyColorItem = new JMenuItem("Modify Colors");
        JMenuItem modifySizeItem = new JMenuItem("Modify Size");

        gameMenu.add(newGameItem);
        gameMenu.add(openGameItem);
        gameMenu.add(saveGameItem);
        gameMenu.add(exitItem);

        optionsMenu.add(modifyColorItem);
        optionsMenu.add(modifySizeItem);

        menuBar.add(gameMenu);
        menuBar.add(optionsMenu);
        setJMenuBar(menuBar);
    }

    private void prepareActionMenu(){
        JMenuBar menu = getJMenuBar();
        JMenu gameMenu = menu.getMenu(0);
        JMenu optionsMenu = menu.getMenu(1);
        
        JMenuItem exitItem = gameMenu.getItem(3);
        JMenuItem saveItem = gameMenu.getItem(2);
        JMenuItem openItem = gameMenu.getItem(1);
        JMenuItem modifyColorItem = optionsMenu.getItem(0);

        exitItem.addActionListener(e -> exit());
        saveItem.addActionListener(e -> Save());
        openItem.addActionListener(e -> Open());
        modifyColorItem.addActionListener(e -> prepareModifyColors());
    }

    private void Open(){
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if(returnValue == JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, 
                "En construcción: " + selectedFile.getName(), "File Opened", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void Save(){
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(this);
        if(returnValue == JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, 
                "En construcción: " + selectedFile.getName(), "File Saved", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void prepareModifyColors(){
        JDialog dialog = new JDialog(this, "Modify Piece Colors", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(400, 500);
        dialog.setLocationRelativeTo(this);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel instructionLabel = new JLabel("Select a piece to change its color:");
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        dialog.add(instructionLabel, BorderLayout.NORTH);
        
        createPieceColorButton(mainPanel, "I - Line Piece", colorI, "I");
        createPieceColorButton(mainPanel, "O - Square Piece", colorO, "O");
        createPieceColorButton(mainPanel, "T - T Piece", colorT, "T");
        createPieceColorButton(mainPanel, "S - S Piece", colorS, "S");
        createPieceColorButton(mainPanel, "Z - Z Piece", colorZ, "Z");
        createPieceColorButton(mainPanel, "J - J Piece", colorJ, "J");
        createPieceColorButton(mainPanel, "L - L Piece", colorL, "L");
        
        dialog.add(mainPanel, BorderLayout.CENTER);
        
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dialog.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.setVisible(true);
    }

    private void createPieceColorButton(JPanel panel, String text, Color currentColor, String pieceType){
        JPanel piecePanel = new JPanel(new BorderLayout(10, 0));
        
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JPanel colorPreview = new JPanel();
        colorPreview.setBackground(currentColor);
        colorPreview.setPreferredSize(new Dimension(50, 30));
        colorPreview.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    
        JButton changeButton = new JButton("Change Color");
        changeButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(this, 
                "Choose color for " + text, currentColor);
            if(newColor != null){
                updatePieceColor(pieceType, newColor);
                colorPreview.setBackground(newColor);
                refresh();
            }
        });
        
        piecePanel.add(label, BorderLayout.WEST);
        piecePanel.add(colorPreview, BorderLayout.CENTER);
        piecePanel.add(changeButton, BorderLayout.EAST);
        
        panel.add(piecePanel);
    }

    private void updatePieceColor(String pieceType, Color newColor){
        switch(pieceType){
            case "I" -> colorI = newColor;
            case "O" -> colorO = newColor;
            case "T" -> colorT = newColor;
            case "S" -> colorS = newColor;
            case "Z" -> colorZ = newColor;
            case "J" -> colorJ = newColor;
            case "L" -> colorL = newColor;
        }
    }

    private void prepareTopPreviewPanel() {
        previewTopPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawTopPreview(g);
            }
        };
        previewTopPanel.setPreferredSize(new Dimension(300, 100));
        previewTopPanel.setBackground(new Color(40, 40, 60));
        previewTopPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(gridColor, 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }

        private void drawTopPreview(Graphics g) {
        if (game.getCurrentPiece() == null) {
            return;
        }
        
        Piece piece = game.getCurrentPiece();
        int[][] shape = piece.getShape();
        int blockSize = 30;
        int startX = pieceColumn * blockSize;
        int startY = 10;
        Color pieceColor = getPieceColor(piece.getType());
        
        for (int r = 0; r < shape.length; r++) {
            for (int c = 0; c < shape[r].length; c++) {
                if (shape[r][c] == 1) {
                    g.setColor(pieceColor);
                    g.fillRect(startX + c * blockSize, startY + r * blockSize, 
                              blockSize, blockSize);
                    g.setColor(Color.WHITE);
                    g.drawRect(startX + c * blockSize, startY + r * blockSize, 
                              blockSize, blockSize);
                }
            }
        }
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.drawString("← → : Mover  |  R: Rotar  |  SPACE: Soltar", 10, 95);
    }


    private Color getPieceColor(int type) {
        return switch (type) {
            case Piece.TYPE_I -> colorI;
            case Piece.TYPE_O -> colorO;
            case Piece.TYPE_T -> colorT;
            case Piece.TYPE_S -> colorS;
            case Piece.TYPE_Z -> colorZ;
            case Piece.TYPE_J -> colorJ;
            case Piece.TYPE_L -> colorL;
            default -> Color.WHITE;
        };
    }

    private void prepareElementsBoard(){
        boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBoard(g);
            }
        };
        boardPanel.setPreferredSize(new Dimension(300, 600));
        boardPanel.setBackground(backgroundColor);
        boardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(gridColor, 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        add(boardPanel, BorderLayout.WEST);
    }

    private void drawBoard(Graphics g){
        int cellSize = 30;
        int cols = 10;
        int rows = 20;
        
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                g.setColor(gridColor);
                g.fillRect(c * cellSize, r * cellSize, cellSize, cellSize);
                g.setColor(backgroundColor);
                g.drawRect(c * cellSize, r * cellSize, cellSize, cellSize);
            }
        }
    }

    private void prepareElementsInfo() {
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(4, 1, 10, 10));
        infoPanel.setBackground(backgroundColor);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        

        prepareTitlePanel();
        prepareNextPiecePanel();
        prepareScorePanel();
        prepareHighScorePanel();
        
        infoPanel.add(titlePanel);
        infoPanel.add(nextPiecePanel);
        infoPanel.add(scorePanel);
        infoPanel.add(highScorePanel);
        
        add(infoPanel, BorderLayout.CENTER);
    }
    
    private void prepareTitlePanel() {
        titlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawTetrisTitle(g);
            }
        };
        titlePanel.setPreferredSize(new Dimension(300, 120));
        titlePanel.setBackground(Color.BLACK);
        titlePanel.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 255), 3));
    }
    
    private void drawTetrisTitle(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        String[] letters = {"T", "E", "T", "R", "I", "S"};
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.CYAN};
        
        Font font = new Font("Arial", Font.BOLD, 40);
        g2d.setFont(font);
        
        int x = 20;
        int y = 60;
        
        for (int i = 0; i < letters.length; i++) {
            g2d.setColor(colors[i]);
            g2d.drawString(letters[i], x, y);
            x += 45;
        }
        
        int pieceX = 180;
        int pieceY = 70;
        int blockSize = 20;
        
        g2d.setColor(Color.MAGENTA);
        g2d.fillRect(pieceX, pieceY, blockSize, blockSize);
        g2d.fillRect(pieceX + blockSize, pieceY, blockSize, blockSize);
        g2d.fillRect(pieceX + blockSize * 2, pieceY, blockSize, blockSize);
        g2d.fillRect(pieceX + blockSize, pieceY + blockSize, blockSize, blockSize);
        
        g2d.setColor(Color.BLACK);
        g2d.drawRect(pieceX, pieceY, blockSize, blockSize);
        g2d.drawRect(pieceX + blockSize, pieceY, blockSize, blockSize);
        g2d.drawRect(pieceX + blockSize * 2, pieceY, blockSize, blockSize);
        g2d.drawRect(pieceX + blockSize, pieceY + blockSize, blockSize, blockSize);
    }
    
    private void prepareNextPiecePanel() {
        nextPiecePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawNextPiece(g);
            }
        };
        nextPiecePanel.setBackground(Color.BLACK);
        nextPiecePanel.setBorder(BorderFactory.createLineBorder(gridColor, 2));
        nextPiecePanel.setPreferredSize(new Dimension(300, 120));
    }
    
    private void drawNextPiece(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.drawString("NEXT", 20, 40);
    
        int blockSize = 20;
        int startX = 180;
        int startY = 50;
        
        g2d.setColor(Color.CYAN);
        g2d.fillRect(startX, startY, blockSize, blockSize);
        g2d.fillRect(startX + blockSize, startY, blockSize, blockSize);
        g2d.fillRect(startX, startY + blockSize, blockSize, blockSize);
        g2d.fillRect(startX + blockSize, startY + blockSize, blockSize, blockSize);
        
        g2d.setColor(Color.WHITE);
        g2d.drawRect(startX, startY, blockSize, blockSize);
        g2d.drawRect(startX + blockSize, startY, blockSize, blockSize);
        g2d.drawRect(startX, startY + blockSize, blockSize, blockSize);
        g2d.drawRect(startX + blockSize, startY + blockSize, blockSize, blockSize);
    }
    
    private void prepareScorePanel() {
        scorePanel = new JPanel();
        scorePanel.setLayout(new BorderLayout());
        scorePanel.setBackground(Color.BLACK);
        scorePanel.setBorder(BorderFactory.createLineBorder(gridColor, 2));
        
        JPanel iconPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawScoreIcon(g);
            }
        };
        iconPanel.setPreferredSize(new Dimension(100, 100));
        iconPanel.setBackground(Color.BLACK);
        
        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setBackground(Color.BLACK);
        
        JLabel scoreTitle = new JLabel("SCORE");
        scoreTitle.setForeground(Color.WHITE);
        scoreTitle.setFont(new Font("Arial", Font.BOLD, 25));
        scoreTitle.setHorizontalAlignment(SwingConstants.CENTER);
        
        scoreLabel = new JLabel("367");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 50));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        textPanel.add(scoreTitle);
        textPanel.add(scoreLabel);
        
        scorePanel.add(iconPanel, BorderLayout.WEST);
        scorePanel.add(textPanel, BorderLayout.CENTER);
    }
    
    private void drawScoreIcon(Graphics g) {
        int blockSize = 15;
        Color[] colors = {Color.ORANGE, Color.CYAN, Color.GREEN, Color.RED, Color.MAGENTA, Color.YELLOW};
        
        int[][] positions = {
            {10, 20}, {25, 20}, {10, 35}, {25, 35},
            {40, 35}, {55, 20}, {40, 50}, {55, 35}
        };
        
        for (int i = 0; i < positions.length && i < colors.length; i++) {
            g.setColor(colors[i % colors.length]);
            g.fillRect(positions[i][0], positions[i][1], blockSize, blockSize);
            g.setColor(Color.BLACK);
            g.drawRect(positions[i][0], positions[i][1], blockSize, blockSize);
        }
    }
    
    private void prepareHighScorePanel() {
        highScorePanel = new JPanel(new GridLayout(2, 1));
        highScorePanel.setBackground(Color.BLACK);
        highScorePanel.setBorder(BorderFactory.createLineBorder(gridColor, 2));
        
        JLabel highScoreTitle = new JLabel("HIGH SCORE");
        highScoreTitle.setForeground(Color.WHITE);
        highScoreTitle.setFont(new Font("Arial", Font.BOLD, 25));
        highScoreTitle.setHorizontalAlignment(SwingConstants.CENTER);
        
        highScoreLabel = new JLabel("367");
        highScoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setFont(new Font("Arial", Font.BOLD, 50));
        highScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        highScorePanel.add(highScoreTitle);
        highScorePanel.add(highScoreLabel);
    }
    
    public void refresh() {
    
        scoreLabel.setText(String.valueOf(game.getScore()));
        highScoreLabel.setText(String.valueOf(game.getHighScore()));
        
        boardPanel.repaint();
        nextPiecePanel.repaint();
        titlePanel.repaint();
        scorePanel.repaint();
        
        setTitle("SlowTetris - Score: " + game.getScore());
    }
}
