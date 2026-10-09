package no.uib.inf101.grid;

/**
 *  ! Denne klassen er hentet fra repositoriet vi fikk utdelt til seminaroppgave 1, Tetris.
 */


 
/**
 * A CellPosition consists of a row and a column.
 *
 * @param row  the row of the cell
 * @param col  the column of the cell
 */
public record CellPosition(int row, int col) {

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public String getString() {
        return ("X: " + Integer.toString(this.col) + ", Y: " + Integer.toString(this.row));
    }

    public boolean equals(CellPosition pos) {
        return (this.row == pos.getRow() && this.col == pos.getCol());
    }
    
}
