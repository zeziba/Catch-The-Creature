package Creature;

/**
 * Created by cengen on 11/21/16.
 */
public class Fox extends Creature{

    public Fox(int x, int y) {
        setX(x);
        setY(y);
        setState(true);
        setImg("src/Images/Fox.png");
        setDead("src/Images/Fox_dead.png");
        setDuration(500 + random.nextInt(1000));
    }
}
