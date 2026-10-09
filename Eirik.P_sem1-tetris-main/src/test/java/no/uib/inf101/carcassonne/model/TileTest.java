package no.uib.inf101.carcassonne.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.carcassonne.controller.CarcassonneController;
import no.uib.inf101.carcassonne.view.CarcassonneView;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.model.CarcassonneModel;
import no.uib.inf101.model.board.CarcassonneBoard;
import no.uib.inf101.model.tile.Deck;
import no.uib.inf101.model.tile.Tile;
import no.uib.inf101.model.tile.terrain.City;
import no.uib.inf101.model.tile.terrain.Field;
import no.uib.inf101.model.tile.terrain.ITerrain;
import no.uib.inf101.model.tile.terrain.Road;


public class TileTest {
    
    @Test 
    public void indexTest() {
        CarcassonneBoard board = new CarcassonneBoard(60, 60);
        Deck deck = new Deck();
        deck.testDeck();
        CarcassonneModel model = new CarcassonneModel(board, deck);

        assertTrue(model.getTile().getTerrain(0).equals(new City()));
        assertTrue(model.getTile().getTerrain(3).equals(new City()));
        assertTrue(model.getTile().getTerrain(6).equals(new City()));
        assertTrue(model.getTile().getTerrain(1).equals(new Road()));
        assertTrue(model.getTile().getTerrain(4).equals(new Road()));
        assertTrue(model.getTile().getTerrain(7).equals(new Road()));
        assertTrue(model.getTile().getTerrain(2).equals(new Field()));
        assertTrue(model.getTile().getTerrain(5).equals(new Field()));
        assertTrue(model.getTile().getTerrain(8).equals(new Field()));
    }

    @Test
    public void rotateTest() {
        CarcassonneBoard board = new CarcassonneBoard(60, 60);
        Deck deck = new Deck();
        deck.testDeck();
        CarcassonneModel model = new CarcassonneModel(board, deck);

        model.rotateTile();
        assertEquals(model.getTile().toString(), "ccc\nrrr\n---");

        model.rotateTile();
        assertEquals(model.getTile().toString(), "-rc\n-rc\n-rc");
    }

    @Test
    public void newTileTest() {
        CarcassonneBoard board = new CarcassonneBoard(60, 60);
        Deck deck = new Deck();
        deck.testDeck();
        CarcassonneModel model = new CarcassonneModel(board, deck);

        model.newTile();
        Tile tile1 = Tile.newTile("CRFR");
        assertEquals(model.tile.getPos(), tile1.getPos());
        assertEquals(model.getTile().getCode(), tile1.getCode());
        System.out.println(model.getTile().getFeatures());
        assertTrue(model.getTile().getFeatures().get(0).getValue().equals(new City()));
        assertTrue(model.getTile().getFeatures().get(1).getValue().equals(new Road()));
    }
}
