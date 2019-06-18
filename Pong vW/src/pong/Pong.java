/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pong;

/**
 *
 * @author AUGSpeed, Theo, Shem
 */

import javax.swing.JFrame;

public class Pong extends JFrame {
boolean deathMode;
    public Pong(boolean DM) {
        deathMode = DM;
        initUI();
    }
    
    private void initUI() {
       
        add(new Board(deathMode));
        setTitle("Pong");
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Commons.WIDTH, Commons.HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
    }

}
