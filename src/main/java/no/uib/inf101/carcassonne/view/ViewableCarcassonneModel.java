package no.uib.inf101.carcassonne.view;

import java.util.ArrayList;

import no.uib.inf101.grid.*;
import no.uib.inf101.model.GameState;
import no.uib.inf101.model.board.CarcassonneBoard;
import no.uib.inf101.model.tile.Tile;
import no.uib.inf101.model.tile.terrain.ITerrain;

public interface ViewableCarcassonneModel {


    ArrayList<GridCell<ITerrain>> spentRoads = new ArrayList<>();

    /**
     * @return The dimension of the grid.
     */
    GridDimension getDimension();

    /**
     * @return Et objekt som inneholder alle posisjonene på brettet med tilhørende symbol.
     */
    Iterable<GridCell<ITerrain>> getTilesOnBoard();

    /**
     * @return Et objekt som inneholder alle terrains i nåværende tile.
     */
    Iterable<GridCell<ITerrain>> getTileTerrains();

    /*
     * @return the gamestate, kan være ACTIVE_GAME, eller GAME_OVER.
     */
    public GameState getGameState();

    /**
     * @return Board stored in model.
     */
    public CarcassonneBoard getBoard();

    /**
     * Setter for model gamestate.
     */
    public void setGameState(GameState state);


    /**
     * 
     * @return Current tile in focus.
     */
    public Tile getTile();

    /**
     * 
     * @return Score of player one. Stored in model.
     */
    public Integer getPlayerOneScore();

    /**
     * Returns a boolean indicating wether the models movement lock has been triggered. This will most likely 
     * happen when a tile has been placed, and can be used to tell if the end turn-button is ready to be used.
     * @return boolean
     */
    public boolean getMovementLock();

    /**
     * 
     * @returnScore of player two. Stored in model.
     */
    public Integer getPlayerTwoScore();

    /** 
     * method for handling new focus tiles.
     */
    public void newTile();

    /**
     * Scans terrains surrounding the current tile for features to explore. 
     * Also handles scoring of the features.
     */
    void scanFeatures();

    void subtractMeeples(String string, int i);

    int countMeeples(String string);

    int getP1Meeples();

    int getP2Meeples();
}
