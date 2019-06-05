/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pong;
import java.awt.Rectangle;
/**
 *
 * @author Student
 */
public class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    
    protected void setX(int x) {
        this.x = x;
    }

    protected int getX() {
        return x;
    }

    protected void setY(int y) {
        this.y = y;
    }

    protected int getY() {
        return y;
    }
    
    protected void setWidth(int width) {
        this.width = width;
    }

    protected int getWidth() {
        return width;
    }
    
    protected void setHeight(int height) {
        this.height = height;
    }

    protected int getHeight() {
        return height;
    }

    protected Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

}
