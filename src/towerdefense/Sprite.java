package towerdefense;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Sprite {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;
    
    public Sprite(int x, int y, int width, int height, Image image) {
       this.x = x;
       this.y = y;
       this.width = width;
       this.height = height;
       this.image = image;
    }
    
    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
    
}
