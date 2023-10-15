/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Shokhjakhon
 */
public class GUI {

    private JFrame frame;
    private GameEngine engine;
    private int turn = 0;
    private JLabel goldAmount;
    private JLabel playerName;

    public GUI(String playerName1, String playerName2) {
        frame = new JFrame();
        engine = new GameEngine(playerName1, playerName2);

        /*Menu bar*/
        JMenuBar main = new JMenuBar();
        JMenu menu = new JMenu("MENU");
        main.add(menu);
        JMenuItem save = new JMenuItem("SAVE GAME");
        menu.add(save);
        JMenuItem restart = new JMenuItem("RESTART GAME");
        menu.add(restart);
        JMenuItem exit = new JMenuItem("EXIT");
        menu.add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        frame.setJMenuBar(main);

        /*Player info*/
        JLabel gold = new JLabel();
        gold.setFont(new Font("Serif", Font.PLAIN, 20));
        gold.setForeground(Color.yellow);
        gold.setText("GOLD:");

        goldAmount = new JLabel();
        goldAmount.setFont(new Font("Serif", Font.PLAIN, 20));
        goldAmount.setForeground(Color.yellow);
        goldAmount.setText(Integer.toString((turn % 2 == 0 ? engine.player1 : engine.player2).getGold()));

        JLabel player = new JLabel();
        player.setFont(new Font("Serif", Font.PLAIN, 20));
        player.setForeground(Color.white);
        player.setText("PLAYER: ");

        playerName = new JLabel();
        playerName.setFont(new Font("Serif", Font.PLAIN, 20));
        playerName.setForeground(Color.white);
        playerName.setText((turn % 2 == 0 ? engine.player1 : engine.player2).getName());

        // Right and Left panels
        JPanel right = new JPanel();
        right.setBackground(Color.black);
        right.setPreferredSize(new Dimension(160, 0));

        JPanel left = new JPanel();
        left.setBackground(Color.black);
        left.setPreferredSize(new Dimension(160, 0));

        // Unit option buttons
        JButton soldier = new JButton();
        soldier.setText("Soldier");
        soldier.setPreferredSize(new Dimension(130, 50));
        soldier.setFont(new Font("Arial", Font.BOLD, 18));

        JButton tanks = new JButton();
        tanks.setText("Tank");
        tanks.setPreferredSize(new Dimension(130, 50));
        tanks.setFont(new Font("Arial", Font.BOLD, 18));

        left.add(soldier);
        soldier.setVisible(false);
        left.add(tanks);
        tanks.setVisible(false);

        // Tower option buttons
        JButton basic = new JButton();
        basic.setText("Basic");
        basic.setPreferredSize(new Dimension(130, 50));
        basic.setFont(new Font("Arial", Font.BOLD, 18));

        JButton ice = new JButton();
        ice.setText("Ice");
        ice.setPreferredSize(new Dimension(130, 50));
        ice.setFont(new Font("Arial", Font.BOLD, 18));

        JButton poison = new JButton();
        poison.setText("Poison");
        poison.setPreferredSize(new Dimension(130, 50));
        poison.setFont(new Font("Arial", Font.BOLD, 18));

        JButton goldmine = new JButton();
        goldmine.setText("Gold Mine");
        goldmine.setPreferredSize(new Dimension(130, 50));
        goldmine.setFont(new Font("Arial", Font.BOLD, 18));

        left.add(basic);
        basic.setVisible(false);
        left.add(ice);
        ice.setVisible(false);
        left.add(poison);
        poison.setVisible(false);
        left.add(goldmine);
        goldmine.setVisible(false);

        // Upgrade button
        JButton bUpgrade = new JButton();
        bUpgrade.setText("UPGRADE");
        bUpgrade.setPreferredSize(new Dimension(130, 50));
        bUpgrade.setFont(new Font("Arial", Font.BOLD, 18));
        bUpgrade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                basic.setVisible(false);
                ice.setVisible(false);
                poison.setVisible(false);
                goldmine.setVisible(false);
                soldier.setVisible(false);
                tanks.setVisible(false);

                left.validate();

                disableTilesAndRemoveListener();
            }
        });

        // Train button
        JButton bTrain = new JButton();
        bTrain.setText("TRAIN");
        bTrain.setPreferredSize(new Dimension(130, 50));
        bTrain.setFont(new Font("Arial", Font.BOLD, 18));
        bTrain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                basic.setVisible(false);
                ice.setVisible(false);
                poison.setVisible(false);
                goldmine.setVisible(false);
                soldier.setVisible(true);
                tanks.setVisible(true);

                left.validate();

                disableTilesAndRemoveListener();
            }
        });

        JButton bBuild = new JButton();
        bBuild.setText("BUILD");
        bBuild.setPreferredSize(new Dimension(130, 50));
        bBuild.setFont(new Font("Arial", Font.BOLD, 18));
        bBuild.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                basic.setVisible(true);
                ice.setVisible(true);
                poison.setVisible(true);
                goldmine.setVisible(true);
                soldier.setVisible(false);
                tanks.setVisible(false);

                left.validate();

                disableTilesAndRemoveListener();
            }
        });

        /*JButton bTurn = new JButton();
        bTurn.setText("TURN");
        bTurn.setPreferredSize(new Dimension(130, 50));
        bTurn.setFont(new Font("Arial", Font.BOLD, 18));
        bTurn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                

                left.validate();

                
            }
        });*/

        right.add(Box.createRigidArea(new Dimension(100, 100)));
        right.add(gold);
        right.add(Box.createRigidArea(new Dimension(5, 5)));
        right.add(goldAmount);
        right.add(Box.createRigidArea(new Dimension(100, 15)));
        right.add(player);
        right.add(playerName);
        right.add(Box.createRigidArea(new Dimension(100, 20)));
        right.add(bUpgrade);
        right.add(Box.createRigidArea(new Dimension(100, 20)));
        right.add(bBuild);
        right.add(Box.createRigidArea(new Dimension(100, 20)));
        right.add(bTrain);
        right.add(Box.createRigidArea(new Dimension(100, 20)));
        //right.add(bTurn);

        // Tower type buttons ActionListener
        basic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                basic.setVisible(false);
                ice.setVisible(false);
                poison.setVisible(false);
                goldmine.setVisible(false);
                soldier.setVisible(false);
                tanks.setVisible(false);
                BasicTower bTower = new BasicTower(0, 0, 0, 0, new ImageIcon().getImage(), "Basic");
                enableTiles(bTower);
            }
        });

        ice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                basic.setVisible(false);
                ice.setVisible(false);
                poison.setVisible(false);
                goldmine.setVisible(false);
                soldier.setVisible(false);
                tanks.setVisible(false);
                IceTower iTower = new IceTower(0, 0, 0, 0, new ImageIcon().getImage(), "Ice");
                enableTiles(iTower);
            }
        });

        poison.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                basic.setVisible(false);
                ice.setVisible(false);
                poison.setVisible(false);
                goldmine.setVisible(false);
                soldier.setVisible(false);
                tanks.setVisible(false);
                PoisonTower pTower = new PoisonTower(0, 0, 0, 0, new ImageIcon().getImage(), "Poison");
                enableTiles(pTower);
            }
        });

        goldmine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                basic.setVisible(false);
                ice.setVisible(false);
                poison.setVisible(false);
                goldmine.setVisible(false);
                soldier.setVisible(false);
                tanks.setVisible(false);
                GoldMine goldMine = new GoldMine(0, 0, 0, 0, new ImageIcon().getImage(), "GoldMine");
                enableTiles(goldMine);
            }
        });

        // Unit type button ActionListener
        soldier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                basic.setVisible(false);
                ice.setVisible(false);
                poison.setVisible(false);
                goldmine.setVisible(false);
                soldier.setVisible(false);
                tanks.setVisible(false);

                Soldier soldier = new Soldier(0, 0, 0, 0, new ImageIcon().getImage());
                engine.onTurnChange(turn);
                engine.train(turn, soldier);
                turn++;
                playerName.setText((turn % 2 == 0 ? engine.player1 : engine.player2).getName());
                goldAmount.setText(Integer.toString((turn % 2 == 0 ? engine.player1 : engine.player2).getGold()));
                disableTilesAndRemoveListener();
            }
        });

        tanks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                basic.setVisible(false);
                ice.setVisible(false);
                poison.setVisible(false);
                goldmine.setVisible(false);
                soldier.setVisible(false);
                tanks.setVisible(false);

                Tank tank = new Tank(0, 0, 0, 0, new ImageIcon().getImage());
                engine.onTurnChange(turn);
                engine.train(turn, tank);
                turn++;
                playerName.setText((turn % 2 == 0 ? engine.player1 : engine.player2).getName());
                goldAmount.setText(Integer.toString((turn % 2 == 0 ? engine.player1 : engine.player2).getGold()));
                disableTilesAndRemoveListener();
            }
        });

        frame.setVisible(true);
        frame.getContentPane().add(engine);
        frame.getContentPane().add(left, BorderLayout.WEST);
        frame.getContentPane().add(right, BorderLayout.EAST);
        frame.setTitle("Borsch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.repaint();
    }

    public void enableTiles(Tower tower) {
        boolean built = false;
        Player curPlayer = turn % 2 == 0 ? engine.player1 : engine.player2;
        for (Buildings building : curPlayer.buildings) {
            int tileRow = building.y / 75;
            int tileCol = building.x / 75;
            if (tileRow - 1 >= 0 && engine.map[tileRow - 1][tileCol].isEmpty()) {
                removeDuplicateListeners(engine.map[tileRow - 1][tileCol]);
                engine.map[tileRow - 1][tileCol].setEnabled(true);
                engine.map[tileRow - 1][tileCol].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //mine //if(engine.build(tileRow-1, tileCol, curPlayer, tower)) disableTilesAndRemoveListener();
                        //Shok's
                        if (engine.build(tileRow - 1, tileCol, turn, tower)) {
                            engine.onTurnChange(turn);
                            turn++;
                            playerName.setText((turn % 2 == 0 ? engine.player1 : engine.player2).getName());
                            goldAmount.setText(Integer.toString((turn % 2 == 0 ? engine.player1 : engine.player2).getGold()));
                            disableTilesAndRemoveListener();
                        }
                        goldAmount.setText(Integer.toString((turn % 2 == 0 ? engine.player1 : engine.player2).getGold()));
                    }
                });
            }
            if (tileRow + 1 < 10 && engine.map[tileRow + 1][tileCol].isEmpty()) {
                removeDuplicateListeners(engine.map[tileRow + 1][tileCol]);
                engine.map[tileRow + 1][tileCol].setEnabled(true);
                engine.map[tileRow + 1][tileCol].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //mine //  if(engine.build(tileRow+1, tileCol, curPlayer, tower)) disableTilesAndRemoveListener();
                        //Shokh's
                        if (engine.build(tileRow + 1, tileCol, turn, tower)) {
                            engine.onTurnChange(turn);
                            turn++;
                            playerName.setText((turn % 2 == 0 ? engine.player1 : engine.player2).getName());
                            goldAmount.setText(Integer.toString((turn % 2 == 0 ? engine.player1 : engine.player2).getGold()));
                            disableTilesAndRemoveListener();
                        }
                        goldAmount.setText(Integer.toString((turn % 2 == 0 ? engine.player1 : engine.player2).getGold()));
                    }
                });
            }
            if (tileCol - 1 >= 0 && engine.map[tileRow][tileCol - 1].isEmpty()) {
                removeDuplicateListeners(engine.map[tileRow][tileCol - 1]);
                engine.map[tileRow][tileCol - 1].setEnabled(true);
                engine.map[tileRow][tileCol - 1].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //mine   //  if(engine.build(tileRow, tileCol-1, curPlayer, tower)) disableTilesAndRemoveListener();
                        //Shokh's
                        if (engine.build(tileRow, tileCol - 1, turn, tower)) {
                            engine.onTurnChange(turn);
                            turn++;
                            playerName.setText((turn % 2 == 0 ? engine.player1 : engine.player2).getName());
                            goldAmount.setText(Integer.toString((turn % 2 == 0 ? engine.player1 : engine.player2).getGold()));
                            disableTilesAndRemoveListener();
                        }
                        goldAmount.setText(Integer.toString((turn % 2 == 0 ? engine.player1 : engine.player2).getGold()));
                    }
                });
            }
            if (tileCol + 1 < 10 && engine.map[tileRow][tileCol + 1].isEmpty()) {
                removeDuplicateListeners(engine.map[tileRow][tileCol + 1]);
                engine.map[tileRow][tileCol + 1].setEnabled(true);
                engine.map[tileRow][tileCol + 1].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //-mine      // if(engine.build(tileRow, tileCol+1, curPlayer, tower)) disableTilesAndRemoveListener();
                        //-Shokh's       
                        if (engine.build(tileRow, tileCol + 1, turn, tower)) {
                            engine.onTurnChange(turn);
                            turn++;
                            playerName.setText((turn % 2 == 0 ? engine.player1 : engine.player2).getName());
                            goldAmount.setText(Integer.toString((turn % 2 == 0 ? engine.player1 : engine.player2).getGold()));
                            disableTilesAndRemoveListener();
                        }
                        goldAmount.setText(Integer.toString((turn % 2 == 0 ? engine.player1 : engine.player2).getGold()));
                    }
                });
            }
            
        }
        
    }

    public void disableTilesAndRemoveListener() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (ActionListener al : engine.map[j][i].getActionListeners()) {
                    engine.map[j][i].removeActionListener(al);
                }
            }
        }
    }

    public void removeDuplicateListeners(Tile tile) {
        for (ActionListener al : tile.getActionListeners()) {
            tile.removeActionListener(al);
        }
    }
}
