package stuff;

import java.awt.Color;
import java.util.Random;

public class Plant extends Lifeform implements HerbEdible, OmnivoreEdible, CarnivoreInedible{

    public Plant() {
        color = Color.GREEN;
        surroundings = new Lifeform[3][3];
        r = new Random();
        minSelf = 2;
        minFood = 0;
        minEmpty = 3;
    }

    @Override
    /** plants do not eat (unless they are Venus fly traps) */
    public void eat() {

    }

    @Override
    /** plants cannot move, so this does nothing */
    public void move() {

    }

    @Override
    /** places plants at the appropriate surroundings based off the given rules */
    public void reproduceCheck() {        
        self = 0;
        empty = 0;
        food = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (surroundings[i][j] == null) {
                    continue;
                }
                if (i == 1 && j == 1) {
                    continue;
                } 
                if (surroundings[i][j] instanceof Plant) {
                    self++;
                }
                if (surroundings[i][j] instanceof Empty) {
                    empty++;
                }

            }
        }

        reproduce(self, food, empty);

    }

    @Override
    void reproduce(int countSelf, int countFood, int countEmpty) {
        if (countSelf >= minSelf && countEmpty >= minEmpty && countFood == minFood) {
            posx = r.nextInt(3);
            posy = r.nextInt(3);
            while (true) {
                posx = r.nextInt(3);
                posy = r.nextInt(3);
                if (surroundings[posx][posy] instanceof Plant) {
                    continue;
                }
                if (surroundings[posx][posy] == null) {
                    continue;
                }
                if (posx == 1 && posy == 1) {
                    continue;
                } 
                if (surroundings[posx][posy] instanceof Empty) {
                    surroundings[posx][posy] = new Plant();
                    surroundings[posx][posy].hasTurned = true;
                    break;
                }
            }
        }
    }

    @Override
    /** sets the surroundings of the current life form  */
    public void setSurroundings(Lifeform a, int i, int j) {
        surroundings[i][j] = a;
    }

    @Override
    public Lifeform[][] getSurroundings() {
        return surroundings;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    void moveCheck(int inEdible) {
        
    }

}
