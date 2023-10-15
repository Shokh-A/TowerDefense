package towerdefense;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;

public class Menu {
    private JFrame menu = new JFrame();
    
    public Menu() {
        menu.setTitle("Borsch");
        menu.setSize(600, 200);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel P1 = new JLabel();
        P1.setText("Player 1");
        JLabel P2 = new JLabel();
        P2.setText("Player 2");
        
        JTextField player1 = new JTextField(15);
        JTextField player2 = new JTextField(15);
        
        JButton start = new JButton();
        start.setText("NEW GAME");
        start.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                menu.dispose();
                GUI gui = new GUI(player1.getText(), player2.getText());
            }  
        }); 
        
        JButton loadGame = new JButton();
        loadGame.setText("LOAD GAME");
        loadGame.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                System.out.println("game is loaded");
            }  
        }); 
        
        JButton quit = new JButton();
        quit.setText("QUIT");
        quit.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                menu.dispatchEvent(new WindowEvent(menu, WindowEvent.WINDOW_CLOSING));
            }  
        }); 
        
        JPanel top = new JPanel();
        JPanel b1 = new JPanel();
        JPanel b2 = new JPanel();
        JPanel b3 = new JPanel();
        top.add(P1);
        top.add(player1);
        top.add(P2);
        top.add(player2);
        b1.add(start);
        b2.add(loadGame);
        b3.add(quit);
        menu.add(top);
        menu.getContentPane().setLayout(new BorderLayout());
        menu.getContentPane().add(top, BorderLayout.NORTH);
        menu.getContentPane().add(b1, BorderLayout.WEST);
        menu.getContentPane().add(b2, BorderLayout.CENTER);
        menu.getContentPane().add(b3, BorderLayout.EAST);
           
        menu.setVisible(true);
    }
}


