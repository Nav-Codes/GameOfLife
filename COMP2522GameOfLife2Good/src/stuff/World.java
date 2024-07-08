package stuff;

//world would tell cell what its surroundings are 
public class World {

    private Cell[][] cells;

    /** initializes the world with cells */
    public World(int gridSize, int cellSize) {
        cells = new Cell[gridSize][gridSize];

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    /**would loop through each cell in array, tell it what its surroundings are,
     * move it, and update the cell array with the new positions*/
    public void turn() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                setSurroundings(cells[i][j], i, j);
                cells[i][j].turn();
                updateWorld(cells[i][j].getSurroundings(), i, j);
            }
        }
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j].getLifeform().hasTurned = false;
            }
        }
    }

    /** set the surroundings of a given cell */ 
    public void setSurroundings(Cell temp, int i, int j) {
        Cell [][] surroundings = new Cell[3][3];

        surroundings[0][0] = assignCell(i - 1, j - 1);
        surroundings[0][1] = assignCell(i - 1, j);
        surroundings[0][2] = assignCell(i - 1, j + 1);

        surroundings[1][0] = assignCell(i, j - 1);
        surroundings[1][1] = temp;
        surroundings[1][2] = assignCell(i, j + 1);

        surroundings[2][0] = assignCell(i + 1, j - 1);
        surroundings[2][1] = assignCell(i + 1, j);
        surroundings[2][2] = assignCell(i + 1, j + 1);

        cells[i][j].setSurroundings(surroundings);
    }

    /** updates the cell array after everything takes a turn */ 
    public void updateWorld(Cell [][] updated, int i, int j) {
        if(i-1 >= 0 && j + 1 < 25 && j - 1 >= 0 && i+1 < 25) {
            cells[i - 1][j - 1] = assignWorld(0, 0, updated);
            cells[i-1][j] = assignWorld(0, 1, updated);
            cells[i-1][j+1] = assignWorld(0,2,updated);
        }

        if(i-1 >= 0 && j + 1 < 25 && j - 1 >= 0 && i+1 < 25) {
            cells[i][j-1] = assignWorld(1,0,updated);
        }

        cells[i][j] = assignWorld(1,1,updated);

        if(i-1 >= 0 && j + 1 < 25 && j - 1 >= 0 && i+1 < 25) {
            cells[i][j+1] = assignWorld(1,2,updated);
        }

        if(i-1 >= 0 && j + 1 < 25 && j - 1 >= 0 && i+1 < 25) {
            cells[i+1][j-1] = assignWorld(2,0,updated);
        }
        if(i-1 >= 0 && j + 1 < 25 && j - 1 >= 0 && i+1 < 25) {
            cells[i+1][j] = assignWorld(2,1,updated);
            cells[i+1][j+1] = assignWorld(2,2,updated);
        }
    }

    /**make method that takes in i and j for cell array, the method then 
    tries to access that position, if it is exception, catch it and assign null.
    checks if cell at that position is a cell or if that position does not exist*/ 
    public Cell assignCell(int i, int j) {
        Cell temp;
        try {
            temp = cells[i][j];
        } catch (Exception e) {
            return null;
        }
        return temp;
    }

    /** updates cell array with updated positions of cells after they move. */
    public Cell assignWorld(int i, int j, Cell[][] a) {
        Cell temp;
        try {
            temp = a[i][j];
        } catch (Exception e) {
            return null;
        }
        return temp;
    }

}
