package no.uib.inf101.model.board;

import no.uib.inf101.grid.*;
import no.uib.inf101.model.tile.terrain.*;

import java.util.ArrayList;
import java.util.Iterator;

public class CarcassonneBoard extends Grid<ITerrain>{
    private final int rows;
    private final int cols;

    /**  Konstruktør, setter feltvariabler. 
     * @param rows Antall rader på brettet.
     * @param cols Antall kolonner på brettet.
    */
    public CarcassonneBoard(int rows, int cols) {
        super(rows, cols, new EmptyTerrain());
        this.rows = rows;
        this.cols = cols;

        int startingTileX = (int) Math.floor(this.cols / 2);
        int startingTileY = (int) Math.floor(this.rows / 2);

        this.set(new CellPosition(startingTileY, startingTileX), new City());
        this.set(new CellPosition(startingTileY, startingTileX+1), new City());
        this.set(new CellPosition(startingTileY, startingTileX+2), new City());

        this.set(new CellPosition(startingTileY+1, startingTileX), new Road());
        this.set(new CellPosition(startingTileY+1, startingTileX+1), new Road());
        this.set(new CellPosition(startingTileY+1, startingTileX+2), new Road());

        this.set(new CellPosition(startingTileY+2, startingTileX), new Field());
        this.set(new CellPosition(startingTileY+2, startingTileX+1), new Field());
        this.set(new CellPosition(startingTileY+2, startingTileX+2), new Field());

    }

}
