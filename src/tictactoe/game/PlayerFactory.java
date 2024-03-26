package tictactoe.game;

abstract class PlayerFactory {

    final String PLAYER_LEVEL;
    final Board TIC_TAC_TOE_BOARD;
    final char PLAYER_SYMBOL, OPPONENT_SYMBOL;
    final Boolean IS_MAXIMIZING_PLAYER, IS_HUMAN;


    PlayerFactory(String playerLever, Board ticTacToeBoard, boolean isMaximizingPlayer) {
        this.PLAYER_LEVEL = playerLever;
        this.TIC_TAC_TOE_BOARD = ticTacToeBoard;
        this.IS_HUMAN = playerLever.equals("USER");
        this.IS_MAXIMIZING_PLAYER = isMaximizingPlayer;
        this.PLAYER_SYMBOL = isMaximizingPlayer ? 'X' : 'O';
        this.OPPONENT_SYMBOL = isMaximizingPlayer ? 'O' : 'X';
    }


    static PlayerFactory createPlayer(String playerLevel, Board ticTacToeBoard, boolean isMaximizingPlayer) {

        if (playerLevel.equals("USER")) {
            return new HumanPlayer(playerLevel, ticTacToeBoard, isMaximizingPlayer);

        } else if (playerLevel.equals("EASY") || playerLevel.equals("MEDIUM") || playerLevel.equals("HARD")) {
            return new AiPlayer(playerLevel, ticTacToeBoard, isMaximizingPlayer);

        } else {
            System.out.print("""
                    Player type not supported! Please select from:
                    USER - Human Player
                    EASY/MEDIUM/HARD - AI Player with corresponding difficultly level.
                    """);
            new TicTacToeGame();
        }
        return null;
    }

    abstract String[] getPlayerMove();


}
