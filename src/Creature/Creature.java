package Creature;

import Sounds.SoundFX;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by cengen on 11/15/16.
 */

public abstract class Creature {

    Random random = new Random();

    private int x, y, height, width;
    private boolean isState;
    private BufferedImage alive, dead;
    private long created;
    private SoundFX kill = new SoundFX("Gun+1.wav");
    private double duration;

    int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }

    public void setPoint(int x, int y)
    {
        setX(x);
        setY(y);
    }

    BufferedImage getImg() {
        return alive;
    }

    void setImg(String img) {
        try {
            alive = ImageIO.read(new File(img));
        }
        catch (IOException e) {
            System.out.println(e);
        }

        setHeight(alive.getHeight());
        setWidth(alive.getWidth());
    }

    BufferedImage getDead() {
        return dead;
    }

    void setDead(String d) {
        try {
            dead = ImageIO.read(new File(d));
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public int getHeight() {
        return height;
    }

    private void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    private void setWidth(int width) {
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

    private long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public double timeBetween(long instant)
    {
        try {
            return (instant - this.getCreated()) / 1000.0;
        } catch (NullPointerException ex) {
            System.out.println(ex);
            return 1.0;
        }
    }

    public double getDuration() {
        return duration;
    }

    void setDuration(double duration) {
        this.duration = duration / 1000;
    }

    public void death() {
        kill.makeSound();
    }

    public void drawSelf(Graphics g, JPanel panel) {
        if (this.isState())
            g.drawImage(getImg(), getX(), getY(), panel);
        else
            g.drawImage(getDead(), getX(), getY(), panel);
    }
}
