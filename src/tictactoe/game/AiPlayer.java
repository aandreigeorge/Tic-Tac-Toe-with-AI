package tictactoe.game;

import java.util.Random;

class AiPlayer extends PlayerFactory {


    AiPlayer(String playerLevel, Board ticTacToeBoard, boolean isMaximizingPlayer) {
        super(playerLevel, ticTacToeBoard, isMaximizingPlayer);
    }


    @Override
    String[] getPlayerMove() {

        Cell[][] board = TIC_TAC_TOE_BOARD.getBOARD();
        int[] move = new int[]{-1, -1};

        switch (PLAYER_LEVEL) {
            case "EASY" -> move = getRandomMove(board);
            case "MEDIUM" -> move = getMediumLevelMove(board);
            case "HARD" -> move = getBestMove(board, IS_MAXIMIZING_PLAYER);
        }

        System.out.println("Making move level \"" + PLAYER_LEVEL + "\"");
        return new String[]{String.valueOf(move[0]), String.valueOf(move[1])};
    }

    /*
        EASY LEVEL:
        Perform a random move for easy level
    */
    private int[] getRandomMove(Cell[][] board) {

        int row, column;
        Random random = new Random();

        do {
            row = random.nextInt(board.length);
            column = random.nextInt(board.length);

        } while (board[row][column].isOccupied());

        return new int[]{row, column};
    }

    /*
       MEDIUM LEVEL:
       IF -> there is a potential winning move PICK that
       ELSE IF -> the opponent has a potential winning move BLOCK that
       ELSE ->  perform a random move
    */
    private int[] getMediumLevelMove(Cell[][] board) {

        int[] move = checkRows(board);

        if (move == null) {
            move = checkColumns(board);
            if (move == null) {
                move = checkDiagonals(board);
            }
        }

        if (move == null) {
            move = getRandomMove(board);
        }

        return move;
    }

    private int[] checkRows(Cell[][] board) {

        for (int row = 0; row < board.length; row++) {

            int sum = 0;
            int opponentSum = 0;
            int emptyIndex = -1;

            for (int column = 0; column < board.length; column++) {

                if (board[row][column].getValue() == PLAYER_SYMBOL) {
                    sum++;
                } else if (board[row][column].getValue() == OPPONENT_SYMBOL) {
                    opponentSum++;
                } else if (!board[row][column].isOccupied()) {
                    emptyIndex = column;
                }

                if (sum == 2 && emptyIndex != -1 || opponentSum == 2 && emptyIndex != -1) {
                    return new int[]{row, emptyIndex};
                }
            }
        }
        return null;
    }

    private int[] checkColumns(Cell[][] board) {

        for (int column = 0; column < board.length; column++) {
            int sum = 0;
            int opponentSum = 0;
            int emptyIndex = -1;

            for (int row = 0; row < board.length; row++) {

                if (board[row][column].getValue() == PLAYER_SYMBOL) {
                    sum++;
                } else if (board[row][column].getValue() == OPPONENT_SYMBOL) {
                    opponentSum++;
                } else if (!board[row][column].isOccupied()) {
                    emptyIndex = row;
                }

                if (sum == 2 && emptyIndex != -1 || opponentSum == 2 && emptyIndex != -1) {
                    return new int[]{emptyIndex, column};
                }
            }
        }
        return null;
    }

    private int[] checkDiagonals(Cell[][] board) {

        int playerSum = 0;
        int opponentSum = 0;
        int emptyRowIndex = -1;
        int emptyColumnIndex = -1;

        for (int i = 0; i < board.length; i++) {

            if (board[i][i].getValue() == PLAYER_SYMBOL) {
                playerSum++;
            } else if (board[i][i].getValue() == OPPONENT_SYMBOL) {
                opponentSum++;
            } else if (!board[i][i].isOccupied()) {
                emptyRowIndex = i;
                emptyColumnIndex = i;
            }
        }

        if (playerSum == 2 && emptyRowIndex != -1 || opponentSum == 2 && emptyRowIndex != -1) {
            return new int[]{emptyRowIndex, emptyColumnIndex};
        }


        playerSum = 0;
        opponentSum = 0;
        emptyRowIndex = -1;
        emptyColumnIndex = -1;

        for (int i = board.length - 1; i >= 0; i--) {

            if (board[i][board.length - 1 - i].getValue() == PLAYER_SYMBOL) {
                playerSum++;
            } else if (board[i][board.length - 1 - i].getValue() == OPPONENT_SYMBOL) {
                opponentSum++;
            } else if (!board[i][board.length - 1 - i].isOccupied()) {
                emptyRowIndex = i;
                emptyColumnIndex = board.length - 1 - i;
            }
        }

        if (playerSum == 2 && emptyRowIndex != -1 || opponentSum == 2 && emptyRowIndex != -1) {
            return new int[]{emptyRowIndex, emptyColumnIndex};
        }

        return null;
    }

    /*
        HARD LEVEL:
        Minimax Algorithm
     */
    private int[] getBestMove(Cell[][] board, boolean isMaximizingPlayer) {

        int[] bestMove = new int[2];
        int bestScore = isMaximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (!board[i][j].isOccupied()) {
                    board[i][j].setValue(isMaximizingPlayer ? 'X' : 'O');
                    int score = minimax(board, !isMaximizingPlayer);
                    board[i][j].setValue(' ');

                    if ((isMaximizingPlayer && score > bestScore) || (!isMaximizingPlayer && score < bestScore)) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(Cell[][] board, boolean isMaximizingPlayer) {


        if (TIC_TAC_TOE_BOARD.gameDraw()) {
            return 0;
        } else if (TIC_TAC_TOE_BOARD.gameWon()) {
            if (TIC_TAC_TOE_BOARD.getWinningPlayer() == 'X') {
                return +10;
            } else if (TIC_TAC_TOE_BOARD.getWinningPlayer() == 'O') {
                return -10;
            }
        }

        int bestScore = isMaximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (Cell[] rowOfCells : board) {
            for (int column = 0; column < board.length; column++) {
                if (!rowOfCells[column].isOccupied()) {

                    char currentPlayer = isMaximizingPlayer ? 'X' : 'O';
                    rowOfCells[column].setValue(currentPlayer);
                    int currentScore = minimax(board, !isMaximizingPlayer);
                    rowOfCells[column].setValue(' ');

                    if (isMaximizingPlayer) {
                        bestScore = Math.max(currentScore, bestScore);
                    } else {
                        bestScore = Math.min(currentScore, bestScore);
                    }
                }
            }
        }
        return bestScore;
    }

}

