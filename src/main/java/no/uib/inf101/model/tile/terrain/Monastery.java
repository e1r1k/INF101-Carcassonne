package no.uib.inf101.model.tile.terrain;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.model.tile.Meeple;

public class Monastery implements ITerrain {

    private ArrayList<GridCell<ITerrain>> connected = new ArrayList<>();
    private Meeple meeple = null;
    private final String name = "Monastery";

    public String getName() {
        return this.name;
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }


    @Override
    public char getSymbol() {
        return 'X';
    }

    @Override
    public String toString() {
        return "X";
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
