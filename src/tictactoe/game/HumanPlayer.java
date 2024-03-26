package tictactoe.game;

import java.util.Scanner;

public class HumanPlayer extends PlayerFactory {

    private final static Scanner scanner = new Scanner(System.in);


    HumanPlayer(String playerLevel, Board ticTacToeBoard, boolean isMaximizingPlayer) {
        super(playerLevel, ticTacToeBoard, isMaximizingPlayer);
    }


    @Override
    String[] getPlayerMove() {

        System.out.print("Enter the coordinates: ");
        String[] userMove = scanner.nextLine().toUpperCase().split(" ");

        if (userMove.length == 1 && userMove[0].contains("EXIT")) {
            System.exit(0);
        }
        return userMove;
    }

}
