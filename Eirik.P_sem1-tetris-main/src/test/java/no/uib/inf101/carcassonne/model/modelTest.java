package no.uib.inf101.carcassonne.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import no.uib.inf101.carcassonne.controller.CarcassonneController;
import no.uib.inf101.carcassonne.view.*;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.boolGCListMeepleTriple;
import no.uib.inf101.model.*;
import no.uib.inf101.model.board.*;
import no.uib.inf101.model.tile.Deck;
import no.uib.inf101.model.tile.Meeple;
import no.uib.inf101.model.tile.Tile;
import no.uib.inf101.model.tile.terrain.City;
import no.uib.inf101.model.tile.terrain.Field;
import no.uib.inf101.model.tile.terrain.Road;

public class modelTest {

    @Test 
    public void moveTileTest() {
        CarcassonneBoard board = new CarcassonneBoard(60, 60);
        Deck deck = new Deck();
        deck.testDeck();
        CarcassonneModel model = new CarcassonneModel(board, deck);
        CarcassonneView view = new CarcassonneView(model);
        CarcassonneController controller = new CarcassonneController(model, view);

        assertTrue(model.moveTile(3, 3));
        assertFalse(model.moveTile(-9, -9));
        assertFalse(model.moveTile(board.rows(), board.cols()));
        }
    
    @Test
    // Tester også checkAdjacency og checkForUnoccupiedPosition indirekte.
    public void placeTileOnBoardTest() {
        CarcassonneBoard board = new CarcassonneBoard(60, 60);
        Deck deck = new Deck();
        deck.testDeck();
        CarcassonneModel model = new CarcassonneModel(board, deck);
        CarcassonneView view = new CarcassonneView(model);
        CarcassonneController controller = new CarcassonneController(model, view);

        model.moveTile((board.rows() / 2)-3, (board.cols() / 2));
        model.rotateTile();
        assertTrue(model.placeTileOnBoard());
        assertFalse(model.placeTileOnBoard());
        model.moveTile(12, 12);
        assertFalse(model.placeTileOnBoard());
    }

    // Tester også scanfeatures, og dermed scoreExploration indirekte.
    @Test
    public void explorationTest() {
        CarcassonneBoard board = new CarcassonneBoard(60, 60);
        Deck deck = new Deck();
        CarcassonneModel model = new CarcassonneModel(board, deck);
        CarcassonneView view = new CarcassonneView(model);
        CarcassonneController controller = new CarcassonneController(model, view);

        board.set(new CellPosition(9, 9), new Road());
        board.set(new CellPosition(9, 10), new Road());
        board.set(new CellPosition(9, 11), new Road());
        board.set(new CellPosition(10, 11), new Road());
        board.set(new CellPosition(11, 11), new Road());
        board.set(new CellPosition(11, 10), new Road());
        board.set(new CellPosition(11, 9), new Road());
        board.set(new CellPosition(10, 9), new Road());

        board.set(new CellPosition(18, 6), new City());
        board.set(new CellPosition(18, 7), new City());
        board.set(new CellPosition(19, 6), new City());
        board.set(new CellPosition(19, 7), new City());
        board.set(new CellPosition(20, 6), new City());
        board.set(new CellPosition(20, 7), new City());

        board.set(new CellPosition(18, 5), new Field());
        board.set(new CellPosition(18, 8), new Field());
        board.set(new CellPosition(19, 5), new Field());
        board.set(new CellPosition(19, 8), new Field());
        board.set(new CellPosition(20, 5), new Field());
        board.set(new CellPosition(20, 8), new Field());
        board.set(new CellPosition(17, 6), new Field());
        board.set(new CellPosition(17, 7), new Field());
        board.set(new CellPosition(21, 6), new Field());
        board.set(new CellPosition(21, 7), new Field());


        board.get(new CellPosition(9, 9)).setMeeple(new Meeple("P1"));
        board.get(new CellPosition(18, 6)).setMeeple(new Meeple("P2"));
        board.get(new CellPosition(18, 7)).setMeeple(new Meeple("P2"));

        Explorer explorer = new Explorer(model);

        boolGCListMeepleTriple exploreCityResult = explorer.exploreCity(board.getCell(18, 6), new ArrayList<>());
        boolGCListMeepleTriple exploreResult = explorer.exploreRoad(board.getCell(9, 9), null, new ArrayList<>(), new ArrayList<>());
        boolGCListMeepleTriple exploreEmptyRoad = explorer.exploreRoad(board.getCell(1, 1), null, new ArrayList<>(), new ArrayList<>());
        boolGCListMeepleTriple exploreEmptyCity = explorer.exploreCity(board.getCell(1, 1), new ArrayList<>());

        assertTrue(exploreResult.getKey());
        assertEquals(exploreResult.getMeeples().get("P1"), 1 );
        assertEquals(exploreCityResult.getValue().size(), 6);

        assertFalse(exploreCityResult.getKey());
        assertEquals(exploreCityResult.getMeeples().get("P2"), 2 );
        assertEquals(exploreCityResult.getValue().size(), 6);

        assertFalse(exploreEmptyRoad.getKey());
        assertEquals(exploreEmptyRoad.getMeeples().get("P1"), 0);
        assertEquals(exploreEmptyRoad.getMeeples().get("P2"), 0);
        assertEquals(exploreEmptyRoad.getValue().size(), 0);

        assertTrue(exploreEmptyCity.getKey());
        assertEquals(exploreEmptyCity.getMeeples().get("P1"), 0);
        assertEquals(exploreEmptyCity.getMeeples().get("P1"), 0);
        assertEquals(exploreEmptyCity.getValue().size(), 0);

        model.moveTile(12, 3);
        model.scanFeatures();
        assertEquals(model.getPlayerTwoScore(), 12);
    }





}
