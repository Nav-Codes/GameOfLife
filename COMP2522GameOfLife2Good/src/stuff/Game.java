package stuff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Creates first rendering of the GUI for the game 
 * and handles mouse click logic.
 * 
 * @author Navjot
 * @version 1.0
 */
public class Game extends JFrame {
    
    /**
     * Represents the world object 
     */
    private final World w;
    private Cell[][] cells;

    /**
     * Determines the size of the grid.
     */
    private int SIZE_OF_GRID = 25;
    
    int sizeOfCell;

    /**
     * Constructor for game. Handles rendering of GUI and handles mouse clicks. 
     * 
     * @param size initializes the grid size.
     */
    public Game() {
        setTitle("GameOfLife");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(514, 516);
        setLocationRelativeTo(null);
        sizeOfCell = Math.min(getWidth() / SIZE_OF_GRID, getHeight() / SIZE_OF_GRID);
        w = new World(SIZE_OF_GRID, sizeOfCell);
        cells = w.getCells();
        JPanel jpanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponents(g);
                drawGrid(g);
                paintGrid(g);
                
            }
        };

        
        
        jpanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                w.turn();
                paintGrid(jpanel.getGraphics());
            }
        });
        add(jpanel);
    }


    /**
     * Draws the basic grid where the cells will be placed.
     * 
     * @param g represents the grid the user can see. 
     */
    private void drawGrid(Graphics g) {
        int sizeOfCell = Math.min(getWidth() / SIZE_OF_GRID, getHeight() / SIZE_OF_GRID);

        g.setColor(Color.BLACK);
        
        for (int j = 0; j <= SIZE_OF_GRID; j++) {
            int lx = j * sizeOfCell;
            g.drawLine(lx, 0, lx, getHeight());
        }

        for (int i = 0; i <= SIZE_OF_GRID; i++) {
            int ly = i * sizeOfCell;
            g.drawLine(0, ly, getWidth(), ly);
        }

    }

    /**Initializes the cells colors and displays them on the grid.
     * 
     * @param g represents the grid the user can see. 
     */
    private void paintGrid(Graphics g) {        
        Cell[][] arr = cells;
        int rows = arr.length;
        int columns = arr[0].length;
        int width = getWidth() / columns;
        int height = getHeight() / rows;
        
        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < columns; j++) {
                g.setColor(arr[i][j].getLifeform().getColor());
                g.fillRect(j * width, i * height, width, height);
            }
        }
        
        drawGrid(g);

    }

}
