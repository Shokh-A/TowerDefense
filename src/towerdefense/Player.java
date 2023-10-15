package towerdefense;

import java.util.ArrayList;

public class Player {
    protected String name;
    protected int HP;
    protected int gold;
    protected Castle castle;
    protected ArrayList<Buildings> buildings;
    protected ArrayList<Units> units;
    protected ArrayList<Tower> towers;
    
    public Player(String name, Castle castle) {
        this.name = name;
        this.castle = castle;
        this.HP = 100;
        this.gold = 1000;
        this.buildings = new ArrayList<>();
        this.units = new ArrayList<>();
        this.towers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public void setTowers(ArrayList<Tower> towers) {
        this.towers = towers;
    }

    public int getGold() {
        return gold;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public Castle getCastle() {
        return castle;
    }

    public void setCastle(Castle castle) {
        this.castle = castle;
    }

    public ArrayList<Buildings> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Buildings> buildings) {
        this.buildings = buildings;
    }

    public ArrayList<Units> getUnits() {
        return units;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setUnits(ArrayList<Units> units) {
        this.units = units;
    }
    
}
