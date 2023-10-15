package towerdefense;

import java.awt.Image;

public class Units extends Sprite {
    protected int HP;
    protected int attack;
    
    public Units(int HP, int attack, int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
        this.HP = HP;
        this.attack = attack;
    }
}
