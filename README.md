# Tic Tac Toe
Tic Tac Toe implementation using a two-dimensional array of Cell objects.

The game starts with an empty board and creates two players based on the user input. 
The players can be of the same type or different types(HUMAN and AI).
X is always the first player.

The AI player has three distinct levels of difficulty: EASY/MEDIUM/HARD. 1.EASY - Performs a random move. 
2.MEDIUM - Performs a winning move or blocks the opponent's winning move. 3.HARD - Uses minimax algorithm to pick the best move.

The game also includes validation for: 1. Bad input from the user upon entering game configuration details. 2. Bad input from user
regarding cell coordinates. 3. User entering coordinates for a cell that is already occupied. 

To exit the user can type at any point "EXIT" command.
