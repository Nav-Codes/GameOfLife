package stuff;

import java.awt.Color;
import java.util.Random;

public class Cell {
    private Lifeform life;
    private Cell [][] surroundings;

    /**
     * Constructor for cell that initializes the life forms in each cell. 
     * 
     * @param x is x coordinate of cell. 
     * @param y is y coordinate of cell. 
     * @param size is size of cell on the grid. 
     */
    public Cell() {
        Random r = new Random();
        int choice = r.nextInt(100);
        if (choice >= 80) {
            life = new Herbivore();
        } else if (choice >= 60) {
            life = new Plant();
        } else if (choice >= 50) {
            life = new Carnivore();
        } else if(choice >= 45) {
            life = new Omnivore();
        } else {
            life = new Empty();
        }
    }

    /** Getter method for cell's color.
     * @return color as Color object. */
    public Color getColor() {
        return life.color;
    }

    /** Getter method for cell's life form type.
     * @return Life form object.*/
    public Lifeform getLifeform() {
        return life;
    }

    /** sets a new life form object
     * @newLife represents the new life */
    public void setLifeform(Lifeform newLife) {
        life = newLife;
    }

    /**Getter method for cell's surroundings.
     * @return surroundings 2d array, where surroundings[1][1] is location of this cell.*/
    public void setSurroundings(Cell [][]temp) {
        surroundings = temp;
    }

    /**
     * Getter method for cell's surroundings. 
     * If something in surroundings array is null, catch Exception and do nothing.  
     * 
     * @return surrounding 2d array, where surroundings[1][1] is location of this cell. 
     */
    public Cell[][] getSurroundings() {
        return surroundings;
    }

    /** updates the surroundings of the cell array with the new life form */
    public void updateSurroundings(Lifeform newLife) {        
        Lifeform[][] temp = newLife.getSurroundings();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if (temp == null) {
                    continue;
                }
                if(temp[i][j] == null) {
                    continue;
                }
                if(surroundings[i][j] == null) {
                    continue;
                }
                surroundings[i][j].setLifeform(temp[i][j]);
            }
        }
    }

    /** updates the surroundings of the cell and moves the life form */
    public void turn() {
        Lifeform temp = this.life;

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(i == 1 && j == 1) {
                    continue;
                }
                if (surroundings[i][j] == null) {
                    temp.setSurroundings(null, i, j);
                } else {
                    temp.setSurroundings(surroundings[i][j].getLifeform(), i, j);
                }

            } 
        }
        if (!life.hasTurned) {
            life.reproduceCheck();
            updateSurroundings(life);
        }
    }
}
