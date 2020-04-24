package game;

import game.AI.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player1 = new User();
        Player player2 = new User();
        do {
            System.out.print("Input command: ");
            String command = scanner.nextLine();
            if (command.equals("exit")) break;
            String[] input = command.split("\\s+");
            if (!input[0].equals("start") || input.length != 3) {
                System.out.println("Bad parameters!");
                continue;
            }

            switch (input[1]) {
                case "user":
                    player1 = new User();
                    break;
                case "easy":
                    player1 = new EasyAI();
                    break;
                case "medium":
                    player1 = new MediumAI();
                    break;
                case "hard":
                    player1 = new HardAI();
                    break;
            }
            switch (input[2]) {
                case "user":
                    player2 = new User();
                    break;
                case "easy":
                    player2 = new EasyAI();
                    break;
                case "medium":
                    player2 = new MediumAI();
                    break;
                case "hard":
                    player2 = new HardAI();
                    break;
            }

            playGame(player1, player2);
        } while (true);

    }

    private static void playGame(Player player1, Player player2) {
        Field field = new Field();

        while (field.continues()) {
            if (field.nextSymbol() == Symbol.X) {
                System.out.println(field);
                player1.move(field);
            } else {
                System.out.println(field);
                player2.move(field);
            }
        }
        System.out.println(field);
        Symbol winner = field.getWinner();

        switch (winner) {
            case X:
            case O:
                System.out.println(winner.getSymbol() + " wins");
                break;
            case EMPTY:
                System.out.println("Draw");
                break;
            default:
                throw new IllegalStateException();
        }
    }

}