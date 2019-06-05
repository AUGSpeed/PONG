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
public class Ball extends Sprite implements Commons {

    private double xdir;
    private double ydir;

    public Ball() {

        initBall();
    }

    private void initBall() {
        
        xdir = 1;
        ydir = -1.5;

        width = getWidth();
        height = getHeight();
        resetState();
    }

    public void move() {
        
        
        x += xdir;
        y += ydir;

        if (y <= 0) {
            setYDir(-1.0*(getYDir()));
            y += ydir;
        }

        if (y >= HEIGHT - height-25) {
            setYDir(-1.0*(getYDir()));
        }

    }

    private void resetState() {

        x = (Commons.WIDTH/2) - 5;
        y = (Commons.HEIGHT/2) - 5;
        setWidth(20);
        setHeight(20);
    }

    public void setXDir(double x) {
        xdir = x;
    }

    public void setYDir(double y) {
        ydir = y;
    }

    public double getYDir() {
        return ydir;
    }
    
    public double getXDir() {
        return xdir;
    }
}
