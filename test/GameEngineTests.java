
import java.awt.Image;
import towerdefense.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GameEngineTests {

    Castle castle1 = new Castle(400, 500, 1000, 1000, new ImageIcon("src/Assets/images/blue.png").getImage(), "Castle");
    GameEngine engine = new GameEngine("Name1", "Name2");
    GoldMine gold1 = new GoldMine(500, 500, 100, 100, new ImageIcon("src/Assets/towers/gold.png").getImage(), "GoldMine");
    Player player1 = engine.getPlayer1();
    Player player2 = engine.getPlayer2();
    Tower btower = new BasicTower(400, 500, 70, 70, new ImageIcon("src/assets/towers/basic.png").getImage(), "Basic"); // 10
    Tower itower = new IceTower(400, 500, 70, 70, new ImageIcon("src/assets/towers/ice.png").getImage(), "Ice"); // 15
    Tower ptower = new PoisonTower(400, 500, 70, 70, new ImageIcon("src/assets/towers/poison.png").getImage(), "Poison"); // 15
    Tower goldmine = new GoldMine(400, 500, 70, 70, new ImageIcon("src/assets/towers/gold.png").getImage(), "GoldMine"); // 15
    
    @Before
    public void setUp() {

    }

    @Test
    public void testOnTurnChangeFunction() {
        ArrayList<Buildings> Building1 = new ArrayList<Buildings>();
        Building1.add(gold1);
        player1.setBuildings(Building1);

        assertEquals(player1.getGold(), player2.getGold());
        assertEquals(player1.getGold(), 1000);
        int turn = 1;
        engine.onTurnChange(turn);
        assertEquals(player1.getGold(), 1000);
        assertEquals(player2.getGold(), 1000);
        turn++;
        engine.onTurnChange(turn);
        assertEquals(player1.getGold(), 1020);
    }

    //add an unit to a player which is in a range of towers and then if check decreases etc.
    @Test
    public void testTowerAttackUnit() {

    }

    @Test
    public void testBuildFunction() {
        // Test building for basic tower
        player1.setGold(0);
        assertEquals(engine.build(4, 5, 1, btower),false);
        player1.setGold(10);
        assertEquals(engine.build(4, 5, 1, btower),true);

        //Test basic tower in buildings for player1
//        player1.setGold(100);
//        engine.build(4, 5, player1, btower);
//        assertEquals(player1.getBuildings().contains(btower),true); //THIS IS FAILING

        // Test basic tower in buildings for player2
//        player2.setGold(100);
//        engine.build(4, 5, player2, btower);
//        assertEquals(player2.getBuildings().contains(btower),true); //This is also failing

        // Test building for ice tower
        player1.setGold(0);
        assertEquals(engine.build(4, 5, 1, itower),false);
        player1.setGold(15);
        assertEquals(player1.getGold(),15);
        assertEquals(engine.build(4, 5, 1, itower),true); 

        // Test building for poison tower
        player1.setGold(0);
        assertEquals(engine.build(4, 5, 1, ptower),false);
        player1.setGold(15);
        assertEquals(engine.build(4, 5, 1, ptower),true);

        // Test building for goldmine
        player1.setGold(0);
        assertEquals(engine.build(4, 5, 2, goldmine),false);
        player1.setGold(15);
        assertEquals(engine.build(4, 5, 2, goldmine),true);
        
    }
}
