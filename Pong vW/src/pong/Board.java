

package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import java.util.Random;

public class Board extends JPanel implements Commons {

    private Timer timer;
    private static final Random random = new Random();
    private String message;
    private Ball ball;
    private Paddle paddle;
    private PaddleW paddleW;
    private boolean inGame = true;
    private int score = 0;
    private int scoreW = 0;
    public int win = 7;
    public double mod = 0;
    public boolean deathMode;
    

    public Board(boolean DM) {
        deathMode = DM;
        initBoard();
    }
    
    public void setDM(boolean DM)
    {
        deathMode = DM;
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);

        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), DELAY, PERIOD);
        
        if(deathMode)
            mod = 3;
    }

    @Override
    public void addNotify() {
        super.addNotify();
        gameInit();
    }

    private void gameInit() {
        ball = new Ball();
        paddle = new Paddle();
        paddleW = new PaddleW();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        this.setBackground(Color.BLACK);
        
        for (int i = 0; i < 750; i += 40)
        {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(Commons.WIDTH/2,i,10,15);
        }
        
        Graphics2D g2d = (Graphics2D) g;
        
        Font font = new Font("Dialog", Font.BOLD, 45);

        g2d.setColor(Color.WHITE);
        g2d.setFont(font);
        g2d.drawString(Integer.toString(score),Commons.WIDTH/4,100);

        g2d.setColor(Color.WHITE);
        g2d.setFont(font);
        g2d.drawString(Integer.toString(scoreW),Commons.WIDTH - Commons.WIDTH/4,100);
        
        if (inGame) {
            
            drawObjects(g2d);
        } else {

            gameFinished(g2d);
        }
        
        

        Toolkit.getDefaultToolkit().sync();
    }
    
    private void drawObjects(Graphics2D g2d) {
        Random rand = new Random();
        if (deathMode == true) {
            g2d.setColor(new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
        g2d.fillRect( ball.getX(), ball.getY(),
                ball.getWidth(), ball.getHeight());
        g2d.setColor(new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
        g2d.fillRect( paddle.getX(), paddle.getY(),
                paddle.getWidth(), paddle.getHeight());
        g2d.setColor(new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
        g2d.fillRect( paddleW.getX(), paddleW.getY(),
                paddleW.getWidth(), paddleW.getHeight());
        }
        else {
            g2d.setColor(Color.WHITE);
        g2d.fillRect( ball.getX(), ball.getY(),
                ball.getWidth(), ball.getHeight());
        g2d.fillRect( paddle.getX(), paddle.getY(),
                paddle.getWidth(), paddle.getHeight());
        g2d.fillRect( paddleW.getX(), paddleW.getY(),
                paddleW.getWidth(), paddleW.getHeight());
        }
    }
    
    private void gameFinished(Graphics2D g2d) {

        Font font = new Font("Dialog", Font.BOLD, 48);
        FontMetrics metr = this.getFontMetrics(font);

        g2d.setColor(Color.WHITE);
        g2d.setFont(font);
        g2d.drawString(message,
                (Commons.WIDTH - metr.stringWidth(message)) / 2,
                Commons.WIDTH / 2);
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
            paddleW.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
            paddleW.keyPressed(e);
        }
        
    }

    private class ScheduleTask extends TimerTask {

        @Override
        public void run() {

            ball.move();
            paddle.move();
            paddleW.move();
            checkCollision();
            repaint();
        }
    }
    
    private void score() {
        
        if(deathMode)
            mod = 3;
        else
            mod = 0;
        
        score++;
        
        ball.setX(Commons.INIT_BALL_X);
        ball.setY(Commons.INIT_BALL_Y);
        ball.setXDir(2);
        ball.setYDir(0);
        
        timer.cancel();
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), DELAY, PERIOD);
        
        if (score == win){
            inGame = false;
            timer.cancel();
            message = "Player 1 Wins!";
        }
        
    }
    private void scoreW() {
        
        if(deathMode)
            mod = 3;
        else
            mod = 0;
        
        scoreW++;
        
        ball.setX(Commons.INIT_BALL_X);
        ball.setY(Commons.INIT_BALL_Y);
        ball.setXDir(-2);
        ball.setYDir(0);
        
        timer.cancel();
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), DELAY, PERIOD);
        
         if (scoreW == win){
            inGame = false;
            timer.cancel();
            message = "Player 2 Wins!";
        }
        
    }
    
    private void stopGame() {

        inGame = false;
        timer.cancel();
    }

    private void checkCollision() {

        if (ball.getRect().getMaxX() > Commons.RIGHT_EDGE) {
            score();
        }
        if (ball.getRect().getMinX() < Commons.LEFT_EDGE) {
            scoreW();
        }

        if ((ball.getRect()).intersects(paddle.getRect())) {
            
            mod += 0.1;
            
            int paddleLPos = (int) paddle.getRect().getMinY();
            int ballLPos = (int) ball.getRect().getMinY() + 10;

            int first = paddleLPos + 20;
            int second = paddleLPos + 45;
            int third = paddleLPos + 55;
            int fourth = paddleLPos + 80;

            if (ballLPos < first) {
                ball.setXDir(-2 - mod);
                ball.setYDir(-2 - mod);
            }

            if (ballLPos >= first && ballLPos < second) {
                ball.setXDir(-3 - mod);
                ball.setYDir(-1 - mod);
            }

            if (ballLPos >= second && ballLPos <= third) {
                ball.setXDir(-6 - (mod*2));
                ball.setYDir(0);
            }

            if (ballLPos > third && ballLPos <= fourth) {
                ball.setXDir(-3 - mod);
                ball.setYDir(1 + mod);
            }

            if (ballLPos > fourth) {
                ball.setXDir(-2 - mod);
                ball.setYDir(2 + mod);
            }
        }
        
        if ((ball.getRect()).intersects(paddleW.getRect())) {

            mod += 0.1;
            
            int paddleLPos = (int) paddleW.getRect().getMinY();
            int ballLPos = (int) ball.getRect().getMinY() + 10;

            int first = paddleLPos + 20;
            int second = paddleLPos + 45;
            int third = paddleLPos + 55;
            int fourth = paddleLPos + 80;

            if (ballLPos < first) {
                ball.setXDir(2 + mod);
                ball.setYDir(-2 - mod);
            }

            if (ballLPos >= first && ballLPos < second) {
                ball.setXDir(3 + mod);
                ball.setYDir(-1 - mod);
            }

            if (ballLPos >= second && ballLPos <= third) {
                ball.setXDir(6 + (mod*2));
                ball.setYDir(0);
            }

            if (ballLPos > third && ballLPos <= fourth) {
                ball.setXDir(3 + mod);
                ball.setYDir(1 + mod);
            }

            if (ballLPos > fourth) {
                ball.setXDir(2 + mod);
                ball.setYDir(2 + mod);
            }
        }
        
    }
}
