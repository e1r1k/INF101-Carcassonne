package no.uib.inf101.grid;

import java.util.ArrayList;
import java.util.HashMap;

import no.uib.inf101.model.tile.Meeple;
import no.uib.inf101.model.tile.terrain.ITerrain;

public class boolGCListMeepleTriple {
    
    private boolean key;
    private ArrayList<GridCell<ITerrain>> value;
    private HashMap<String, Integer> meeples;

    public boolGCListMeepleTriple(boolean key, ArrayList<GridCell<ITerrain>> value, HashMap<String, Integer> meeples) {
        this.key = key;
        this.value = value;
        this.meeples = meeples;
    }

    public HashMap<String, Integer> getMeeples() {
        return this.meeples;
    }

    public boolean getKey() {
        return this.key;
    }

    public ArrayList<GridCell<ITerrain>> getValue() {
        return this.value;
    }

}
