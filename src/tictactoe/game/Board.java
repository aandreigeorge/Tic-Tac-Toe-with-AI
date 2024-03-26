package tictactoe.game;

class Board {

    private final Cell[][] BOARD;
    private final int NUMBER_OF_CELLS;
    private boolean moveIsValid;
    private char winningPlayer;


    Board() {
        this.BOARD = new Cell[3][3];
        this.NUMBER_OF_CELLS = 9;
        initializeBoard();
    }


    private void initializeBoard() {
        for (int i = 0; i < BOARD.length; i++) {
            for (int j = 0; j < BOARD.length; j++) {
                BOARD[i][j] = new Cell();
            }
        }
    }

    Cell[][] getBOARD() {
        return this.BOARD;
    }

    char getWinningPlayer() {
        return this.winningPlayer;
    }

    void printBoard() {

        System.out.print("-".repeat(NUMBER_OF_CELLS) + "\n");
        for (Cell[] rowOfCells : BOARD) {
            System.out.print("| ");
            for (Cell individualCell : rowOfCells) {
                System.out.print(individualCell + " ");
            }
            System.out.print("|\n");
        }
        System.out.print("-".repeat(NUMBER_OF_CELLS) + "\n");
    }

    void makeGameMove(PlayerFactory currentPlayer) {

        String[] playerMove;

        if (currentPlayer.IS_HUMAN) {
            moveIsValid = false;

            do {
                playerMove = currentPlayer.getPlayerMove();
                validateHumanUserMove(playerMove, currentPlayer.PLAYER_SYMBOL);

            } while (!moveIsValid);

        } else {
            playerMove = currentPlayer.getPlayerMove();
            updateGrid(Integer.parseInt(playerMove[0]), Integer.parseInt(playerMove[1]), currentPlayer.PLAYER_SYMBOL);
        }

    }

    private void validateHumanUserMove(String[] playerMove, char playerSymbol) {

        try {

            int row = Integer.parseInt(playerMove[0]);
            int column = Integer.parseInt(playerMove[1]);

            if (row < 1 || row > BOARD.length || column < 1 || column > BOARD.length) {
                System.out.println("Coordinates should be from 1 to 3");

            } else if (BOARD[row - 1][column - 1].isOccupied()) {
                System.out.println("This cell is occupied! Choose another one!");

            } else {
                moveIsValid = true;
                updateGrid(row - 1, column - 1, playerSymbol);
            }

        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You should enter two numbers separated by a space!");
        }
    }

    private void updateGrid(int row, int column, char playerSymbol) {

        BOARD[row][column].setValue(playerSymbol);
        printBoard();
    }

    boolean gameWon() {

        char playerSymbol;

        playerSymbol = BOARD[0][0].getValue();
        if (playerSymbol != ' ' && BOARD[1][1].getValue() == playerSymbol && BOARD[2][2].getValue() == playerSymbol) {
            this.winningPlayer = playerSymbol;
            return true; // 1st Diagonal
        }

        playerSymbol = BOARD[0][2].getValue();
        if (playerSymbol != ' ' && BOARD[1][1].getValue() == playerSymbol && BOARD[2][0].getValue() == playerSymbol) {
            this.winningPlayer = playerSymbol;
            return true; // 2nd diagonal
        }

        for (int i = 0; i < BOARD.length; i++) {

            playerSymbol = BOARD[i][0].getValue();
            if (playerSymbol != ' ' && BOARD[i][1].getValue() == playerSymbol && BOARD[i][2].getValue() == playerSymbol) {
                this.winningPlayer = playerSymbol;
                return true; //Row
            }

            playerSymbol = BOARD[0][i].getValue();
            if (playerSymbol != ' ' && BOARD[1][i].getValue() == playerSymbol && BOARD[2][i].getValue() == playerSymbol) {
                this.winningPlayer = playerSymbol;
                return true; // Column
            }
        }

        return false;
    }

    boolean gameDraw() {

        for (Cell[] rowOfCells : BOARD) {
            for (Cell individualCell : rowOfCells) {
                if (!individualCell.isOccupied()) {
                    return false;
                }
            }
        }
        return true;
    }

}
