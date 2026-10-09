package no.uib.inf101.carcassonne.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.testng.internal.annotations.ITest;

import no.uib.inf101.carcassonne.controller.CarcassonneController;
import no.uib.inf101.carcassonne.view.CarcassonneView;
import no.uib.inf101.model.CarcassonneModel;
import no.uib.inf101.model.board.CarcassonneBoard;
import no.uib.inf101.model.tile.Deck;

public class DeckTest {
    @Test 
    public void deckTest() {
        CarcassonneBoard board = new CarcassonneBoard(60, 60);
        Deck deck = new Deck();
        deck.testDeck();
        CarcassonneModel model = new CarcassonneModel(board, deck);
        CarcassonneView view = new CarcassonneView(model);
        CarcassonneController controller = new CarcassonneController(model, view);

        model.newTile();
        model.newTile();
        model.newTile();
        assertFalse(model.tile.isEmpty());
        model.newTile();
        assertTrue(model.tile.isEmpty());
    }

    @Test 
    public void getCodeTest() {
        CarcassonneBoard board = new CarcassonneBoard(60, 60);
        Deck deck = new Deck();
        deck.testDeck();
        CarcassonneModel model = new CarcassonneModel(board, deck);
        CarcassonneView view = new CarcassonneView(model);
        CarcassonneController controller = new CarcassonneController(model, view);

        assertTrue(model.getTile().getCode() == "CRFR");
        model.newTile();
        model.newTile();
        model.newTile();
        model.newTile();
        assertTrue(model.getTile().getCode() == "EMPTY");
    }

    @Test 
    public void newTileTest() {
        CarcassonneBoard board = new CarcassonneBoard(60, 60);
        Deck deck = new Deck();
        assertEquals(deck.getDeck().size(), 67);
        deck.testDeck();
        assertEquals(deck.getNext().getCode(), "CRFR");
    }
}
