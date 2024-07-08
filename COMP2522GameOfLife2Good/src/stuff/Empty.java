package stuff;

import java.awt.Color;

public class Empty extends Lifeform{
    public Empty() {
        color = Color.WHITE;
        surroundings = new Lifeform[3][3];
    }
    
    @Override
    public void eat() {
        
    }

    @Override
    public void move() {
        
    }

    @Override
    public void reproduceCheck() {
        
    }

    @Override
    public void setSurroundings(Lifeform a, int b, int c) {
        surroundings[b][c] = a;
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
    void reproduce(int countSelf, int countFood, int countEmpty) {
        
    }

    @Override
    void moveCheck(int inEdible) {
        
    }

}
