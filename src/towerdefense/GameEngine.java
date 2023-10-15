/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Shokhjakhon
 */
public class GameEngine extends JPanel {

    protected Tile[][] map;
    protected Player player1;
    protected Player player2;
    protected LinkedList<Tile> shortPath;

    public GameEngine(String playerName1, String playerName2) {
        super();
        setLayout(new GridLayout(10, 10, 5, 5));

        map = new Tile[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = new Tile(i, j, j * 75, i * 75, 70, 70, new ImageIcon("src/assets/images/bg.jpg").getImage());
                map[i][j].setPreferredSize(new Dimension(70, 70));
                map[i][j].setOpaque(false);
                map[i][j].setContentAreaFilled(false);
                map[i][j].setBorderPainted(false);
                map[i][j].setEnabled(false);
                add(map[i][j]);
            }
        }
        shortPath = new LinkedList<>();
        Tile loc1 = map[randInt(0, 1)][randInt(0, 9)];
        Tile loc2 = map[randInt(8, 9)][randInt(0, 9)];
        Castle castle1 = new Castle(loc1.getX(), loc1.getY(), 70, 70, new ImageIcon("src/assets/images/blue.png").getImage(), "Castle");
        Castle castle2 = new Castle(loc2.getX(), loc2.getY(), 70, 70, new ImageIcon("src/assets/images/red.png").getImage(), "Castle");

        loc1.place(castle1);
        loc2.place(castle2);

        player1 = new Player(playerName1, castle1);
        player2 = new Player(playerName2, castle2);
        player1.buildings.add(castle1);
        player2.buildings.add(castle2);

        repaint();
    }

    public boolean build(int row, int col, int turn, Tower tower) {
        Player curPlayer = turn % 2 == 0 ? player1 : player2;
        Player enemyPlayer = turn % 2 == 0 ? player2 : player1;
        if (curPlayer.gold < tower.cost) {
            return false;
        }
        if (tower instanceof BasicTower) {
            tower = new BasicTower(col * 75, row * 75, 70, 70, new ImageIcon("src/assets/towers/basic.png").getImage(), "Basic");
        } else if (tower instanceof IceTower) {
            tower = new IceTower(col * 75, row * 75, 70, 70, new ImageIcon("src/assets/towers/ice.png").getImage(), "Ice");
        } else if (tower instanceof PoisonTower) {
            tower = new PoisonTower(col * 75, row * 75, 70, 70, new ImageIcon("src/assets/towers/poison.png").getImage(), "Poison");
        } else if (tower instanceof GoldMine) {
            tower = new GoldMine(col * 75, row * 75, 70, 70, new ImageIcon("src/assets/towers/gold.png").getImage(), "GoldMine");
        }
        map[row][col].setEmpty(false);
        if (!shortestPath(map, curPlayer.castle.y / 75, curPlayer.castle.x / 75, enemyPlayer.castle.y / 75, enemyPlayer.castle.x / 75)) {
            map[row][col].setEmpty(true);
            return false;
        }
        map[row][col].place(tower);
        curPlayer.buildings.add(tower);
        curPlayer.gold -= tower.cost;
        curPlayer.towers.add(tower);
        disableBuilding();

        repaint();
        return true;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void disableBuilding() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[j][i].setEnabled(false);
            }
        }
    }

    public void onTurnChange(int turn) {
        Player curPlayer = turn % 2 == 0 ? player1 : player2;
        Player enemyPlayer = turn % 2 == 0 ? player2 : player1;
        for (Units unit : curPlayer.units) {
            shortestPath(map, unit.y / 75, unit.x / 75, enemyPlayer.castle.y / 75, enemyPlayer.castle.x / 75);
            int row = shortPath.get(1).getRow();
            int col = shortPath.get(1).getCol();
            if (row == enemyPlayer.castle.y / 75 && col == enemyPlayer.castle.x / 75) {
                curPlayer.units.remove(unit);
                unit = null;
                enemyPlayer.castle.HP -= 10;
                repaint();
                break;
            }
            map[row][col].place(unit);
            map[unit.y / 75][unit.x / 75].setObj(null);
            map[unit.y / 75][unit.x / 75].setEmpty(true);
            unit.x = col * 75;
            unit.y = row * 75;
        }

        for (Buildings building : curPlayer.buildings) {
            if (building.type == "GoldMine") {
                curPlayer.gold += 20;
            }
        }
        TowerAttackUnit(turn);
        repaint();
    }

    public boolean train(int turn, Units unit) {
        Player curPlayer = turn % 2 == 0 ? player1 : player2;
        Player enemyPlayer = turn % 2 == 0 ? player2 : player1;
        int tileRow = curPlayer.castle.y / 75;
        int tileCol = curPlayer.castle.x / 75;
        shortestPath(map, curPlayer.castle.y / 75, curPlayer.castle.x / 75, enemyPlayer.castle.y / 75, enemyPlayer.castle.x / 75);
        Tile spawnTile = shortPath.get(1);

        if (unit instanceof Tank) {
            unit = new Tank(spawnTile.getX(), spawnTile.getY(), 70, 70, new ImageIcon("src/assets/images/tank.png").getImage());
        } else if (unit instanceof Soldier) {
            unit = new Soldier(spawnTile.getX(), spawnTile.getY(), 70, 70, new ImageIcon("src/assets/images/soldier.png").getImage());
        }
        spawnTile.place(unit);
        curPlayer.units.add(unit);
        repaint();
        return false;
    }

    public boolean notOutOfBound(int row, int col) {
        if (row >= 0 && col >= 0 && row < 10 && col < 10 && map[row][col].isEmpty()) {
            return true;
        }
        return false;
    }

    //BFS, Time O(n^2), Space O(n^2)
    public boolean shortestPath(Tile[][] matrix, int startX, int startY, int endX, int endY) {
        shortPath.clear();
        int sx = startX, sy = startY;
        int dx = endX, dy = endY;
//            //if start or end value is 0, return
//            if (matrix[sx][sy] == 0 || matrix[dx][dy] == 0) {
//		System.out.println("There is no path.");
//		return;  
//            }
        //initialize the cells 
        int m = matrix.length;
        int n = matrix[0].length;
        Tile[][] tiles = new Tile[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j].isEmpty() || matrix[i][j].getObj() instanceof Castle || matrix[i][j].getObj() instanceof Units) {
                    tiles[i][j] = matrix[i][j];
                    tiles[i][j].prev = null;
                    tiles[i][j].dist = Integer.MAX_VALUE;
                } else {
                    tiles[i][j] = null;
                }
            }
        }
        //breadth first search
        LinkedList<Tile> queue = new LinkedList<>();
        Tile src = tiles[sx][sy];
        src.dist = 0;
        queue.add(src);
        Tile dest = null;
        Tile p;
        while ((p = queue.poll()) != null) {
            //find destination 
            if (p.getRow() == dx && p.getCol() == dy) {
                dest = p;
                break;
            }
            // moving up
            visit(tiles, queue, p.getRow() - 1, p.getCol(), p);
            // moving down
            visit(tiles, queue, p.getRow() + 1, p.getCol(), p);
            // moving left
            visit(tiles, queue, p.getRow(), p.getCol() - 1, p);
            //moving right
            visit(tiles, queue, p.getRow(), p.getCol() + 1, p);
        }

        //compose the path if path exists
        if (dest == null) {
            System.out.println("there is no path.");
            return false;
        } else {
            LinkedList<Tile> path = new LinkedList<>();
            p = dest;
            do {
                path.addFirst(p);
                shortPath.addFirst(p);
            } while ((p = p.prev) != null);
            System.out.println(path);
            return true;
        }
    }

    //function to update cell visiting status, Time O(1), Space O(1)
    private static void visit(Tile[][] tiles, LinkedList<Tile> queue, int x, int y, Tile parent)  {
        //out of boundary
        if (x < 0 || x >= tiles.length || y < 0 || y >= tiles[0].length || tiles[x][y] == null) {
            return;
        }
        //update distance, and previous node
        int dist = parent.dist + 1;
        Tile p = tiles[x][y];
        if (dist < p.dist) {
            p.dist = dist;
            p.prev = parent;
            queue.add(p);
        }
    }

    public void TowerAttackUnit(int turn) {
        ArrayList<Units> deadUnits = new ArrayList<Units>();
        Player curPlayer;
        Player otherPlayer;
        if (turn % 2 == 0) {
            curPlayer = player1;
            otherPlayer = player2;
        } else {
            curPlayer = player2;
            otherPlayer = player1;
        }

        for (Tower tow : curPlayer.towers) {
            for (Units unit : otherPlayer.units) {
                if (Math.abs(unit.x - tow.x) / 75 <= tow.getRange() && Math.abs(unit.y - tow.y) / 75 <= tow.getRange()) {
                    unit.HP -= tow.getDamage();
                    if (unit.HP <= 0) {
                        deadUnits.add(unit);
                    }
                }
            }
        }

        for (Units i : deadUnits) {
            otherPlayer.units.remove(i);
        }

    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j].draw(grphcs);
            }
        }

        for (Buildings building : player1.buildings) {
            building.draw(grphcs);
        }

        for (Buildings building : player2.buildings) {
            building.draw(grphcs);
        }

        for (Units unit : player1.units) {
            unit.draw(grphcs);
        }

        for (Units unit : player2.units) {
            unit.draw(grphcs);
        }

        //healthbar and level for buildings player1
        for (Buildings building : player1.buildings) {
            double width = 0.7 * building.HP;
            int height = 4;
            int x = building.x;
            int y = building.y;
            grphcs.setColor(Color.black);
            grphcs.drawRect(x, y - 1, (int) width, 5);
            if (building.HP <= 50) {
                grphcs.setColor(Color.red);
            } else {
                grphcs.setColor(Color.green);
            }
            grphcs.fillRect(x, y, (int) width, height);
            grphcs.setColor(Color.white);
            grphcs.setFont(new Font("TimesRoman", Font.BOLD, 15));
            if (building instanceof Tower) {
                grphcs.drawString("LVL", x, y + 20);
                grphcs.setColor(Color.white);
                grphcs.drawString(Integer.toString(building.lvl), x, y + 32);
            }
        }
        //healthbar and level for buildings player2
        for (Buildings building : player2.buildings) {
            double width = 0.7 * building.HP;
            int height = 4;
            int x = building.x;
            int y = building.y;
            grphcs.setColor(Color.black);
            grphcs.drawRect(x, y - 1, (int) width, 5);
            if (building.HP <= 50) {
                grphcs.setColor(Color.red);
            } else {
                grphcs.setColor(Color.green);
            }
            grphcs.fillRect(x, y, (int) width, height);
            grphcs.setColor(Color.white);
            grphcs.setFont(new Font("TimesRoman", Font.BOLD, 15));
            if (building instanceof Tower) {
                grphcs.drawString("LVL", x, y + 20);
                grphcs.setColor(Color.white);
                grphcs.drawString(Integer.toString(building.lvl), x, y + 32);

            }
        }

        //healthbar and level for units player1
        for (Units unit : player1.units) {
            double width = 0.7 * unit.HP;
            int height = 4;
            int x = unit.x;
            int y = unit.y;
            grphcs.setColor(Color.black);
            grphcs.drawRect(x, y - 1, (int) width, 5);
            if (unit.HP <= 50) {
                grphcs.setColor(Color.red);
            } else {
                grphcs.setColor(Color.green);
            }
            grphcs.fillRect(x, y, (int) width, height);
        }

        //healthbar and levels for units player2
        for (Units unit : player2.units) {
            double width = 0.7 * unit.HP;
            int height = 4;
            int x = unit.x;
            int y = unit.y;
            grphcs.setColor(Color.black);
            grphcs.drawRect(x, y - 1, (int) width, 5);
            if (unit.HP <= 50) {
                grphcs.setColor(Color.red);
            } else {
                grphcs.setColor(Color.green);
            }
            grphcs.fillRect(x, y, (int) width, height);
        }

    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
