package Creature;

import java.util.Random;

/**
 * Created by cengen on 11/21/16.
 */
public final class PickCreature {

    public static Creature PickCreature(int x, int y) {
        Random random = new Random();
        int cLength = Creatures.values().length;

        Creature rCreature;

        switch (random.nextInt(cLength)) {
            case 0:
                rCreature = new Hawk(x, y);
                break;
            case 1:
                rCreature = new Ghost(x, y);
                break;
            default:
                rCreature = new Hawk(x, y);
        }

        return rCreature;
    }
}
