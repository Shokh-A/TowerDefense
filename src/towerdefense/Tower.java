package towerdefense;

import java.awt.Image;

public abstract class Tower extends Buildings {
    private int damage;
    private int range;
    private int level;

    public Tower(int damage, int range, int level, int cost, int x, int y, int width, int height, Image image, String type) {
        super(cost, x, y, width, height, image, type);
        this.level = 1;
        this.damage=damage;
        this.range=range;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}