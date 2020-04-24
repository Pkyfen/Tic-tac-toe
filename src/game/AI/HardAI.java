package game.AI;

import game.Field;
import game.Symbol;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class HardAI implements Player {
    final List<Coordinate> iterationOrder;

    public HardAI() {
        this.iterationOrder = List.of(
                new Coordinate(2, 2),

                new Coordinate(1, 3),
                new Coordinate(3, 1),
                new Coordinate(1, 1),
                new Coordinate(3, 3),

                new Coordinate(2, 3),
                new Coordinate(3, 2),
                new Coordinate(2, 1),
                new Coordinate(1, 2)
        );
    }

    @Override
    public void move(Field field) {
        System.out.println("Making move level \"hard\"");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Symbol symbol = field.nextSymbol();

        Coordinate next = getBestMove(field, symbol);

        field.set(next.x, next.y);
    }

    private Coordinate getBestMove(Field field, Symbol symbol) {
        Stream<Coordinate> stream = iterationOrder.stream()
                .filter((coordinate) -> field.isFree(coordinate.x, coordinate.y));

        Comparator<Coordinate> comparator = Comparator.comparing(point -> getScore(field, point, symbol));

        return (symbol == Symbol.X ? stream.max(comparator) : stream.min(comparator)).orElseThrow();
    }


    private int getScore(Field field, Coordinate coordinate, Symbol symbol) {
        return getScore(field, coordinate.x, coordinate.y, symbol);
    }


    private int getScore(Field field, int x, int y, Symbol symbol) {
        field.set(x, y);

        if (!field.continues()) {
            Symbol winner = field.getWinner();
            field.unset(x, y);

            if (winner == Symbol.EMPTY) {
                return 0;
            }
            return winner == Symbol.X ? 1 : -1;
        }else {
            Symbol opposite = symbol.opposite();

            IntStream stream = iterationOrder.stream()
                    .filter((coordinate) -> field.isFree(coordinate.x, coordinate.y))
                    .mapToInt(Coordinate -> getScore(field, Coordinate, opposite));

            int score = (opposite == Symbol.X ? stream.max() : stream.min()).orElseThrow();

            field.unset(x, y);
            return score;
        }
    }

    private static class Coordinate {
        int x;
        int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
