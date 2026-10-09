package no.uib.inf101.model;

import java.util.ArrayList;

import no.uib.inf101.carcassonne.controller.ControllableCarcassonneModel;
import no.uib.inf101.carcassonne.view.ViewableCarcassonneModel;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.grid.boolGCListMeepleTriple;
import no.uib.inf101.model.board.CarcassonneBoard;
import no.uib.inf101.model.tile.AdjacencyChecker;
import no.uib.inf101.model.tile.Deck;
import no.uib.inf101.model.tile.Tile;
import no.uib.inf101.model.tile.terrain.*;


public class CarcassonneModel implements ViewableCarcassonneModel, ControllableCarcassonneModel {

    private CarcassonneBoard board;
    public Tile tile;
    private GameState gamestate = GameState.PLAYER_ONE;
    private Deck deck;
    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private int playerOneMeeples = 7;
    private int playerTwoMeeples = 7;
    private boolean movementLock = false;
    public ArrayList<GridCell<ITerrain>> spentRoads = new ArrayList<>();

    /** Konstruktør, setter feltvariabler og henter en ny tetromino 
     * fra fabrikken som den setter sentrert øverst på brettet. 
    */
    public CarcassonneModel(CarcassonneBoard board, Deck deck) {
        this.board = board;
        this.deck = deck;

        this.tile = deck.getNext();
    }
 
    @Override
    public boolean moveTile(Integer deltaRow, Integer deltaCol) {

        Tile candidate = this.tile.shiftedBy(deltaRow, deltaCol);

        if (checkForLegalPosition(candidate) && !movementLock) {
            this.tile = candidate;
            return true;
        }
        return false;
    }

    public void subtractMeeples(String player, int amount) {
        if (player == "P1") {
            this.playerOneMeeples -= amount;
        }
        else if (player == "P2") {
            this.playerTwoMeeples -= amount;
        }
    }

    public void addMeeples(String player, int amount) {
        if (player == "P1") {
            this.playerOneMeeples += amount;
        }
        else if (player == "P2") {
            this.playerTwoMeeples += amount;
        }
    }

    public int countMeeples(String player) {
        if (player == "P1") {
            return this.playerOneMeeples;
        }
        else if (player == "P2") {
            return this.playerTwoMeeples;
        }
        return 0;
    }


    @Override
    public void rotateTile() {
        Tile candidate = this.tile.rotate();
        this.tile = candidate;
    }

    /** Draws a new tile from the deck, and places it in the model. */
    @Override
    public void newTile() {
        this.tile = deck.getNext();
        this.movementLock = false;

        if (this.gamestate == GameState.PLAYER_TWO) {
            moveTile(0, this.board.cols() - 9);
        }

        if (this.tile.isEmpty()) {
            this.gamestate = GameState.FINISHED;
            }
        
    }

    /** Overwrites every terrain under the tile on the grid to be the terrains of the tile, 
     * then draws a new tile from the deck. Will fail if the tile is not placed according to 
     * adjacency rules.
     */
    @Override
    public boolean placeTileOnBoard() {
        // Stop placing of tiles when deck is empty (nexTile gives empty tile when deck is empty)
        if (this.tile.isEmpty()) {return false;}
        else {
            // CheckAdjacency checks the middle top, left, right, and bottom terrains to make sure they match the corresponding
            //  terrains of the tile
            if (AdjacencyChecker.checkAdjacency(this.tile, this)) {
                // Makes sure the space under the tile is unoccupied
                if (checkForUnoccupiedPosition(this.tile)) {

                    // Iterates through the tile, overwriting board terrains underneath
                    for (GridCell<ITerrain> gc : this.tile) {
                        this.board.set(gc.getPos(), gc.getValue());
                    }
                    this.movementLock = true;
                    return true;

                }
            }
        }
        return false;
    }

    @Override
    public void scanFeatures() {
        Explorer explorer = new Explorer(this);
                    System.out.println("Checking tile features");
                    // Scans terrains surrounding tile to find features. 
                    ArrayList<GridCell<ITerrain>> visitedAdjacent = new ArrayList<>();

                    // First scans the 8 surrounding tiles for monasteries as they are not detected in adjacentTerrains.
                    boolGCListMeepleTriple monasteryExploration = explorer.exploreMonastery();
                    if (monasteryExploration.getKey()) {
                        scoreExploration(monasteryExploration, 1.5);
                        }

                    // Then scans adjacent terrains for other features to be explored.
                    for (GridCell<ITerrain> gc : this.getAdjacentTerrains()) {
                        if (visitedAdjacent.contains(gc)) {
                            System.out.println("Cell already visited: " + gc.getPos());
                        }
                        else {
                            if (gc.getValue().equals(new City())) {
                                boolGCListMeepleTriple exploreResult = explorer.exploreCity(gc, new ArrayList<>());
                                for (GridCell<ITerrain> cell : exploreResult.getValue()) {
                                    visitedAdjacent.add(cell);
                                }
                                if (!(exploreResult.getKey())) {
                                    scoreExploration(exploreResult, 2);
                                }
                            }

                            else if (gc.getValue().equals(new Road())) {
                                boolGCListMeepleTriple exploreResult = explorer.exploreRoad(gc, null, new ArrayList<>(), new ArrayList<>());
                                visitedAdjacent.addAll(exploreResult.getValue());
                                System.out.println(exploreResult.getValue());
                                System.out.println("Loop found: " + exploreResult.getKey());
                                System.out.println("Loop length: " + exploreResult.getValue().size());
                                System.out.println("Meeples on road: " + exploreResult.getMeeples());

                                scoreExploration(exploreResult, 1);

                                }
                            
                    }
            }
        }

    /**
     * 
     * @return 12 terrains surrounding the tile currently in the model.
     */
    private ArrayList<GridCell<ITerrain>> getAdjacentTerrains() {
        ArrayList<GridCell<ITerrain>> adjacentTerrains = new ArrayList<>();

        adjacentTerrains.add(board.getCell(this.tile.getPos().getRow()-1, this.tile.getPos().getCol()));
        adjacentTerrains.add(board.getCell(this.tile.getPos().getRow()-1, this.tile.getPos().getCol()+1));
        adjacentTerrains.add(board.getCell(this.tile.getPos().getRow()-1, this.tile.getPos().getCol()+2));

        adjacentTerrains.add(board.getCell(this.tile.getPos().getRow(), this.tile.getPos().getCol()-1));
        adjacentTerrains.add(board.getCell(this.tile.getPos().getRow()+1, this.tile.getPos().getCol()-1));
        adjacentTerrains.add(board.getCell(this.tile.getPos().getRow()+2, this.tile.getPos().getCol()-1));

        adjacentTerrains.add(board.getCell(this.tile.getPos().getRow(), this.tile.getPos().getCol()+3));
        adjacentTerrains.add(board.getCell(this.tile.getPos().getRow()+1, this.tile.getPos().getCol()+3));
        adjacentTerrains.add(board.getCell(this.tile.getPos().getRow()+2, this.tile.getPos().getCol()+3));

        adjacentTerrains.add(board.getCell(this.tile.getPos().getRow()+3, this.tile.getPos().getCol()));
        adjacentTerrains.add(board.getCell(this.tile.getPos().getRow()+3, this.tile.getPos().getCol()+1));
        adjacentTerrains.add(board.getCell(this.tile.getPos().getRow()+3, this.tile.getPos().getCol()+2));

        return adjacentTerrains;
    }

    /**
     * Handles scoring based on meeples/player turns and feature size. The player with the most meeples on the feature
     * should get the points for that feature. If the players have an equal amount of meeples on the feature, 
     * the player in turn gets the points. Returns the meeples when feature is completed.
     * @param exploreResult boolGCListMeepleTriple, triple containing a bool, list of gridcells and list of meeples.
     */
    private void scoreExploration(boolGCListMeepleTriple exploreResult, double multiplier) {
        if (exploreResult.getMeeples().get("P1") == exploreResult.getMeeples().get("P2")) {
            if (gamestate == GameState.PLAYER_ONE) {
                playerOneScore += (exploreResult.getValue().size() * multiplier);
            }
            else {
                playerTwoScore += (exploreResult.getValue().size() * multiplier);
            }
        }
        else if (exploreResult.getMeeples().get("P1") > exploreResult.getMeeples().get("P2")) {
            playerOneScore += (exploreResult.getValue().size() * multiplier);
        }
        else {
            playerTwoScore += (exploreResult.getValue().size() * multiplier);
        }

        this.playerOneMeeples += exploreResult.getMeeples().get("P1");
        this.playerTwoMeeples += exploreResult.getMeeples().get("P2");
    }
    
    /** Checks if every terrain of the tile is within the bounds of the board. */
    private boolean checkForLegalPosition (Tile candidate) {
        for (GridCell<ITerrain> gc : candidate) {
            int row = gc.getPos().getRow();
            int col = gc.getPos().getCol();

            if (((col < 0) || (this.board.cols() < col))
                ||
                ((row < 0) || (this.board.rows() < row))) {
                    return false;
                }
        }
        return true;
    }

    /** Checks if there are any occupied tiles under the tile. Returns true if no, false if yes. */
    private boolean checkForUnoccupiedPosition (Tile candidate) {
        for (GridCell<ITerrain> gc : candidate) {
            CellPosition pos = gc.getPos();
            
            if (!(board.get(pos).getSymbol() == ' ')) {
                return false;
            }
            }
        return true;
    }

    @Override
    public GridDimension getDimension() {
        return this.board;
    }

    @Override
    public boolean getMovementLock() {
        return this.movementLock;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Iterable<GridCell<ITerrain>> getTilesOnBoard() {
        return this.board;
    }

    @Override
    public Iterable<GridCell<ITerrain>> getTileTerrains() {
        return this.tile;
    }

    @Override
    public CarcassonneBoard getBoard() {
        return this.board;
    }

    @Override
    public GameState getGameState() {
        return this.gamestate;
    }

    @Override
    public void setGameState(GameState state) {
        this.gamestate = state;
    }

    @Override
    public Tile getTile() {
        return this.tile;
    }

    @Override
    public Integer getPlayerOneScore() {
        return this.playerOneScore;
    }

    @Override
    public Integer getPlayerTwoScore() {
        return this.playerTwoScore;
    }

    @Override
    public int getP2Meeples() {
        return this.playerTwoMeeples;
    }

    @Override
    public int getP1Meeples() {
        return this.playerOneMeeples;
    }
    
}
