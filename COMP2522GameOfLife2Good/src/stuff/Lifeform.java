package stuff;

import java.awt.Color;
import java.util.Random;

public abstract class Lifeform{
    Color color;
    
    int posx;
    
    int posy;
    
    int health;
    
    int minSelf;
    
    int minFood;
    
    int minEmpty;
    
    int self;
    
    int food;
    
    int empty;
    
    Random r;
    
    boolean hasTurned = false;
    
    Lifeform surroundings[][];

    abstract void eat();
    
    abstract void moveCheck(int inEdible);
    
    abstract void move();
    
    abstract void reproduceCheck();
    
    abstract void reproduce(int countSelf, int countFood, int countEmpty);
        
    abstract void setSurroundings(Lifeform a, int b, int c);
    
    abstract Lifeform[][] getSurroundings();
        
    abstract Color getColor();
}
