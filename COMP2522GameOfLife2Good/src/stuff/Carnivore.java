package stuff;

import java.awt.Color;
import java.util.Random;

public class Carnivore extends Lifeform implements OmnivoreEdible, HerbInedible, CarnivoreInedible{
    
    public Carnivore() {
        color = Color.RED;
        surroundings = new Lifeform[3][3];
        r = new Random();
        health = 5;
        minSelf = 1;
        minFood = 2;
        minEmpty = 3;
    }

    public void move() {
        int inEdible = 0;

        for (int i = 0; i < surroundings.length; i++) {
            for (int j = 0; j < surroundings[0].length; j++) {
                if (i == 1 && j == 1) {
                    continue;
                }
                if (surroundings[i][j] instanceof CarnivoreInedible || surroundings[i][j] == null) {
                    inEdible++;
                }
            }
        }
        
        moveCheck(inEdible);
        eat();
        hasTurned = true;
    }
    
    @Override
    void moveCheck(int inEdible) {
        posx = r.nextInt(3);
        posy = r.nextInt(3);
        
        if (inEdible != 8) {
            while (true) {
                if (surroundings[posx][posy] instanceof CarnivoreInedible) {
                    posx = r.nextInt(3);
                    posy = r.nextInt(3);
                } else if (surroundings[posx][posy] == null) {
                    posx = r.nextInt(3);
                    posy = r.nextInt(3);
                } else if (posx == 1 && posy == 1) {
                    posx = r.nextInt(3);
                    posy = r.nextInt(3);
                } else if (surroundings[posx][posy] instanceof CarnivoreEdible){
                    break;
                } else if (surroundings[posx][posy] instanceof Empty) {
                    break;
                } else {
                    posx = r.nextInt(3);
                    posy = r.nextInt(3);
                    break;
                }
            }
        }
    }

    public void eat() {
        if (surroundings[posx][posy] instanceof CarnivoreEdible) {
            health = 5;
        } else {
            health--;
        }

        if (health != 0) {
            surroundings[posx][posy] = this;
            surroundings[1][1] = new Empty();
        }
        else {
            surroundings[1][1] = new Empty();
        }

    }

    public void reproduceCheck() {
        self = 0;
        empty = 0;
        food = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (surroundings[i][j] instanceof Carnivore) {
                    self++;
                } else if (surroundings[i][j] instanceof Empty) {
                    empty++;
                } else if (surroundings[i][j] instanceof CarnivoreEdible) {
                    food++;
                }
            }
        }

        reproduce(self, food, empty);
        move();
    }
    
    @Override
    void reproduce(int countSelf, int countFood, int countEmpty) {
        if (countSelf >= minSelf && countEmpty >= minEmpty && countFood == minFood) {
            posx = r.nextInt(3);
            posy = r.nextInt(3);

            while (true) {
                if (surroundings[posx][posy] instanceof Carnivore) {
                    posx = r.nextInt(3);
                    posy = r.nextInt(3);
                } else if (surroundings[posx][posy] == null) {
                    posx = r.nextInt(3);
                    posy = r.nextInt(3);
                } else if (posx == 1 && posy == 1) {
                    posx = r.nextInt(3);
                    posy = r.nextInt(3);
                } else if (surroundings[posx][posy] instanceof Empty) {
                    surroundings[posx][posy] = new Carnivore();
                    surroundings[posx][posy].hasTurned = true;
                    break;
                } else {
                    posx = r.nextInt(3);
                    posy = r.nextInt(3);
                }
            }
        }
    }

    public void setSurroundings(Lifeform a, int b, int c) {
        surroundings[b][c] = a;
    }

    public Lifeform[][] getSurroundings() {
        return surroundings;
    }
    
    public Color getColor() {
        return color;
    }

}
