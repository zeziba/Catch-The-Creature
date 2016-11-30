package Creature;

/**
 * Created by cengen on 11/15/16.
 */

public class Hawk extends Creature {

    public Hawk(int x, int y)
    {
        setX(x);
        setY(y);
        setState(true);
        setImg("src/Images/hawk.png");
        setDead("src/Images/hawk_dead.png");
        setDuration(500 + random.nextInt(1000));
    }
}
