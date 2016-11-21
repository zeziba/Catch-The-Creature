package Creature;

import Sounds.SoundFX;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

/**
 * Created by cengen on 11/15/16.
 */

public abstract class Creature {

    private int x, y, height, width;
    private boolean isState;
    private BufferedImage alive, dead;
    private Instant created;
    private SoundFX kill = new SoundFX("Gun+1.wav");

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPoint(int x, int y)
    {
        setX(x);
        setY(y);
    }

    public BufferedImage getImg() {
        return alive;
    }

    public void setImg(String img) {
        try {
            alive = ImageIO.read(new File(img));
        }
        catch (IOException e) {

        }

        setHeight(alive.getHeight());
        setWidth(alive.getWidth());
    }

    public BufferedImage getDead() {
        return dead;
    }

    public void setDead(String d) {
        try {
            dead = ImageIO.read(new File(d));
        }
        catch (IOException e) {

        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isState() {
        return isState;
    }

    public void setState(boolean state) {
        isState = state;
    }

    public boolean contains(MouseEvent event)
    {
        Rectangle bounds = new Rectangle(x, y, width, height);
        return bounds.contains(event.getPoint());
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public double timeBetween(Instant instant)
    {
        try {
            return Duration.between(getCreated(), instant).toMillis() / 10000.0;
        } catch (NullPointerException ex) {
            return 1.0;
        }
    }

    public void death() {
        kill.makeSound();
    }

    public abstract void drawSelf(Graphics g, JPanel panel);
}
