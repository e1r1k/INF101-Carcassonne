package no.uib.inf101.model.tile;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Deck {

    private ArrayList<Tile> deck = new ArrayList<>();

    
    /** 
     * Constructor, generates a deck comprising of random tiles from the provided codes.
     * Adding a new code to the tileCodes dict may cause problems, make sure to also 
     * add it to the newTile method in the Tile class.
     */
    public Deck() {
        Map<String, Integer> cardDict = new HashMap<>();
        cardDict.put("CRFR", 4);
        cardDict.put("RFRF", 8);
        cardDict.put("RRFF", 9);
        cardDict.put("RFRR", 4);
        cardDict.put("RRRR", 1);
        cardDict.put("RCFR", 3);
        cardDict.put("FCRR", 3);
        cardDict.put("RCRR", 3);
        cardDict.put("FCFF", 5);
        cardDict.put("FCFC", 3);
        cardDict.put("CCFF", 2);
        cardDict.put("CFCF", 3);
        cardDict.put("CCRR", 5);
        cardDict.put("CCCR", 3);
        cardDict.put("CCCF", 4);
        cardDict.put("CCCC", 1);
        cardDict.put("XF", 2);
        cardDict.put("XR", 4);

        for (Map.Entry<String, Integer> card : cardDict.entrySet()) {
            String code = card.getKey();
            int copies = card.getValue();
            for (int i = 0; i < copies; i++) {
                this.deck.add(Tile.newTile(code));
            }
        }
        Collections.shuffle(this.deck, new Random());
    }

    /** Gets the first element of the deck, deletes it from deck and then returns it. */
    public Tile getNext() {
        if (this.deck.size() == 0) {
            return Tile.newTile("EMPTY");
        }
        Tile tile = this.deck.get(0);
        this.deck.remove(0);
        //System.out.println(this.deck);
        System.out.println("New tile drawn from deck");
        return tile;
    }

    /**
     * Only used for testing.
     */
    public ArrayList<Tile> getDeck() {
        return this.deck;
    }

    /** Method for creating a smaller deck of tiles. Useful for testing. */
    public void testDeck() {
        this.deck = new ArrayList<>();
        this.deck.add(Tile.newTile("CRFR"));
        this.deck.add(Tile.newTile("CRFR"));
        this.deck.add(Tile.newTile("CRFR"));
        this.deck.add(Tile.newTile("CRFR"));
    }
}

