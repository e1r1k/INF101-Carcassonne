package no.uib.inf101.carcassonne.controller;

import no.uib.inf101.model.GameState;

public interface ControllableCarcassonneModel {

    /**
     * Flytter en tile rundt på brettet.
     * @return boolean, forteller om brikken ble flyttet.
     */
    public boolean moveTile(Integer deltaRow, Integer deltaCol);
    
    /**
     * Roterer en tile 90 grader, med klokken.
     */
    public void rotateTile();

    /**
     * @return Gamestate, kan være ACTIVE_GAME, eller GAME_OVER.
     */
    public GameState getGameState();

    /**
     * Plasserer modellens tilhørende nåværende tile på brettet så lenge
     * posisjonen er innenfor brettets rammer, det ikke er noen terreng under den, 
     * og kantene passer med kantene rundt.
     */
    public boolean placeTileOnBoard();

}

