package game.AI;

import game.Field;

import java.util.Random;

public interface Player {
    void move(Field field);

    default void moveRandomly(Field field) {
        Random random = new Random();
        int x, y;

        do {
            x = random.nextInt(3) + 1;
            y = random.nextInt(3) + 1;
        } while (!field.isFree(x, y));

        field.set(x, y);
    }
}