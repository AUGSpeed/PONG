
package pong;

import java.awt.event.KeyEvent;


public class Paddle extends Sprite implements Commons {

    private int dy;

    public Paddle() {
        
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

        if (key == KeyEvent.VK_UP) {
            dy = -3;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 3;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    private void resetState() {

        x = INIT_PADDLE2_X;
        y = INIT_PADDLE2_Y;
        setWidth(15);
        setHeight(100);
    }
}