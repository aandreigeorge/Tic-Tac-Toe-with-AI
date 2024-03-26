package tictactoe.game;

import java.util.Scanner;

public class TicTacToeGame {

    private static final Scanner scanner = new Scanner(System.in);
    private PlayerFactory player1, player2;
    private Board ticTacToeBoard;


    public TicTacToeGame() {
        configureGameAndPlay();
    }


    private void configureGameAndPlay() {

        do {
            System.out.print("Input command: ");
            String[] commands = scanner.nextLine().toUpperCase().split(" ");

            try {

                if (commands.length == 1 && commands[0].equals("EXIT")) {
                    System.exit(0);
                    scanner.close();

                } else if (commands.length == 3 && commands[0].equals("START")) {
                    ticTacToeBoard = new Board();
                    player1 = PlayerFactory.createPlayer(commands[1], ticTacToeBoard, true);
                    player2 = PlayerFactory.createPlayer(commands[2], ticTacToeBoard, false);
                    playGame();

                } else {
                    System.out.println("Bad parameters!");
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Bad parameters!");
            }

        } while (true);
    }

    private void playGame() {

        ConsoleCleaner.cleanConsole();
        ticTacToeBoard.printBoard();

        do {

            ticTacToeBoard.makeGameMove(player1);
            if (ticTacToeBoard.gameWon() || ticTacToeBoard.gameDraw()) {
                break;
            }

            ticTacToeBoard.makeGameMove(player2);
            if (ticTacToeBoard.gameWon() || ticTacToeBoard.gameDraw()) {
                break;
            }

        } while (true);

        if (ticTacToeBoard.gameWon()) {
            System.out.println(ticTacToeBoard.getWinningPlayer() + " wins\n");
        } else {
            System.out.println("Draw\n");
        }

        configureGameAndPlay();
    }

}
