package no.uib.inf101.grid;

/**
 *  ! Denne klassen er hentet fra repositoriet vi fikk utdelt til seminaroppgave 1, Tetris.
 */

import no.uib.inf101.model.tile.terrain.ITerrain;

/**
     * A GridCell contains a CellPosition and a value.
     *
     * @param pos  the position of the cell (column, row)
     * @param value the color of the cell (E)
     */

public record GridCell<E>(CellPosition pos, E value){

    public CellPosition getPos()  {
        return this.pos;
    }

    public E getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return ("[ " + this.pos.getCol() + ", " + this.pos.getRow() + ", " + this.value + " ]");
    }

}
