/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pong;

/**
 *
 * @author Student
 */

import javax.swing.JFrame;

public class Pong extends JFrame {

    public Pong() {
        
        initUI();
    }
    
    private void initUI() {
       
        add(new Board());
        setTitle("Pong");
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Commons.WIDTH, Commons.HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
    }

}
