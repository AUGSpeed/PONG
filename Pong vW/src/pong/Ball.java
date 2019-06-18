

package pong;

/**
 *
 * @author AUGSpeed, Theo, Shem
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
        	ydir = -ydir;
                y += ydir;
        }

        if (y >= HEIGHT - height-25) {
        	ydir = -ydir;
        }

    }

    private void resetState() {

        x = Commons.INIT_BALL_X;
        y = Commons.INIT_BALL_Y;
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
