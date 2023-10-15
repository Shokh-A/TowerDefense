package towerdefense;

import java.awt.Image;

public class Buildings extends Sprite {
    protected int HP;
    protected int cost;
    protected String type;
    protected int lvl;
    
    public Buildings(int cost, int x, int y, int width, int height, Image image, String type) {
        super(x, y, width, height, image);
        this.HP = 100;
        this.cost = cost;
         this.type=type;
         this.lvl=1;
    }

    public int getCost() {
        return cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}