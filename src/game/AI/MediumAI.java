package game.AI;

import game.Field;
import game.Symbol;

public class MediumAI implements Player {

    @Override
    public void move(Field field) {
        System.out.println("Making move level \"medium\"");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (field.willWin(i, j, Symbol.X) || field.willWin(i, j, Symbol.O)) {
                    field.set(i, j);
                    return;
                }
            }
        }

        moveRandomly(field);
    }
}
