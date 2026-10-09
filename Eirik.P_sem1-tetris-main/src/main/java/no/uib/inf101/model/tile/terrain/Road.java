package no.uib.inf101.model.tile.terrain;

import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayList;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.model.tile.Meeple;

public class Road implements ITerrain {

    private ArrayList<GridCell<ITerrain>> connected = new ArrayList<>();
    private GridCell<ITerrain> next = null;
    private Meeple meeple = null;
    private final String name = "Road";


    public String getName() {
        return this.name;
    }

    @Override
    public Color getColor() {
        return Color.GRAY;
    }

    @Override
    public char getSymbol() {
        return 'r';
    }

    @Override
    public String toString() {
        return "r";
    }

    public Meeple getMeeple() {
        return this.meeple;
    }

    public void setMeeple(Meeple meeple) {
        this.meeple = meeple;
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
    
}
