package Creature;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by cengen on 11/15/16.
 */

public class Hawk extends Creature {

    public Hawk(int x, int y)
    {
        Random random = new Random();
        setX(x);
        setY(y);
        setState(true);
        setImg("src/Images/hawk.png");
        setDead("src/Images/hawk_dead.png");
        setDuration(750 + random.nextInt(1000));
    }

    @Override
    public void drawSelf(Graphics g, JPanel panel)
    {
        if (this.isState())
            g.drawImage(getImg(), getX(), getY(), panel);
        else
            g.drawImage(getDead(), getX(), getY(), panel);
    }
}
