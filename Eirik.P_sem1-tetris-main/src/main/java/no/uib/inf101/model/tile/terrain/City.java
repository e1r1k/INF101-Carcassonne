package no.uib.inf101.model.tile.terrain;

import java.awt.Color;
import java.util.ArrayList;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.model.tile.Meeple;

public class City implements ITerrain {

    private ArrayList<GridCell<ITerrain>> connected = new ArrayList<>();
    private Meeple meeple = null;
    private final String name = "City";

    public String getName() {
        return this.name;
    }

    @Override
    public Color getColor() {
        return Color.orange;
    }

    @Override
    public char getSymbol() {
        return 'c';
    }

    @Override
    public String toString() {
        return "c";
    }

    @Override
    public boolean equals(ITerrain terrain) {
        if (terrain.getSymbol() == this.getSymbol()) {
            return true;
        }
        else {
            return false;
        }
    }

    public Meeple getMeeple() {
        return this.meeple;
    }

    public void setMeeple(Meeple meeple) {
        this.meeple = meeple;
    }
}