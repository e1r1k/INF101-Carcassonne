package no.uib.inf101.carcassonne.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.carcassonne.controller.CarcassonneController;
import no.uib.inf101.carcassonne.view.CarcassonneView;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.model.CarcassonneModel;
import no.uib.inf101.model.board.CarcassonneBoard;
import no.uib.inf101.model.tile.Deck;
import no.uib.inf101.model.tile.terrain.*;

public class BoardTest {
    
    @Test
    public void startingTileTest() {
        CarcassonneBoard board = new CarcassonneBoard(60, 60);
        Deck deck = new Deck();
        deck.testDeck();
        CarcassonneModel model = new CarcassonneModel(board, deck);
        CarcassonneView view = new CarcassonneView(model);
        CarcassonneController controller = new CarcassonneController(model, view);

        assertTrue(board.get(new CellPosition(30, 30)).equals(new City()));
        assertTrue(board.get(new CellPosition(31, 31)).equals(new Road()));
        assertTrue(board.get(new CellPosition(32, 32)).equals(new Field()));
        assertTrue(board.get(new CellPosition(9, 9)).equals(new EmptyTerrain()));

    
    }


}
