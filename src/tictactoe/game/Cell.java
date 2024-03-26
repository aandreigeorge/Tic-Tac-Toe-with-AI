package tictactoe.game;

class Cell {

    private boolean isOccupied;
    private char value;


    Cell() {
        this.value = ' ';
        this.isOccupied = false;
    }


    boolean isOccupied() {
        return this.isOccupied;
    }

    char getValue() {
        return this.value;
    }

    void setValue(char value) {
        this.value = value;
        this.isOccupied = value != ' ';
    }

    @Override
    public String toString() {
        return Character.toString(this.value);
    }
}
