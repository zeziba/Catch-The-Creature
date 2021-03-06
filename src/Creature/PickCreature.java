package Creature;

import java.util.Random;

/**
 * Created by cengen on 11/21/16.
 */
public final class PickCreature {

    public static Creature PickCreature(int x, int y) {
        Random random = new Random();

        Creature rCreature;

        switch (random.nextInt(3)) {
            case 0:
                rCreature = new Hawk(x, y);
                break;
            case 1:
                rCreature = new Ghost(x, y);
                break;
            case 2:
                rCreature = new Fox(x, y);
                break;
            default:
                rCreature = new Hawk(x, y);
        }

        return rCreature;
    }
}
