/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pong;

import java.awt.event.KeyEvent;
import static pong.Commons.HEIGHT;
import static pong.Commons.INIT_PADDLE_X;
import static pong.Commons.INIT_PADDLE_Y;


public class PaddleW extends Sprite implements Commons {

    private int dy;

    public PaddleW() {
        
        initPaddle();
        
    }
    
    private void initPaddle() {

        resetState();
    }
    
    

    public void move() {

        y += dy;

        if (y <= 0) {
            y = 0;
        }

        if (y >= HEIGHT - (height + 25)) {
            y = HEIGHT - (height + 25);
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            dy = -3;
        }

        if (key == KeyEvent.VK_S) {
            dy = 3;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            dy = 0;
        }

        if (key == KeyEvent.VK_S) {
            dy = 0;
        }
    }

    private void resetState() {

        x = INIT_PADDLE_X;
        y = INIT_PADDLE_Y;
        setWidth(15);
        setHeight(100);
    }
}
