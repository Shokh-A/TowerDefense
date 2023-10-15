package towerdefense;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JButton;

public class Tile extends JButton {
    private int row, col, x, y, width, height;
    protected int dist = Integer.MAX_VALUE;
    private Image image;
    private boolean empty = true;
    private Sprite obj;
    protected Tile prev = null;

    public Tile(int row, int col, int x, int y, int width, int height, Image image) {
        super();
        this.row = row;
        this.col = col;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }
    
    public void place(Sprite obj) {
        this.obj = obj;
        empty = false;
    }
    
    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
    
    @Override
    public String toString() {
    return "(" + row + "," + col + ')';
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public Sprite getObj() {
        return obj;
    }

    public void setObj(Sprite obj) {
        this.obj = obj;
    }
    
}
