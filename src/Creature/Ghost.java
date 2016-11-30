package Creature;

/**
 * Created by cengen on 11/21/16.
 */
public class Ghost extends Creature {

    public Ghost(int x, int y) {
        setX(x);
        setY(y);
        setState(true);
        setImg("src/Images/Ghost.png");
        setDead("src/Images/Ghost_dead.png");
        setDuration(500 + random.nextInt(1000));
    }
}
