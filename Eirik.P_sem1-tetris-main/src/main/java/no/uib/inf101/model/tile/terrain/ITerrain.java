package no.uib.inf101.model.tile.terrain;

import java.awt.Color;
import java.util.ArrayList;

import no.uib.inf101.grid.*;
import no.uib.inf101.model.tile.Meeple;

public interface ITerrain {

    /**
     * 
     * @return Meeple from terrain feature. Leaves meeple for later.
     */
    public Meeple getMeeple();

    /**
     * Overwrites current meeple of the terrain.
     * @param meeple 
     */
    public void setMeeple(Meeple meeple);

    /**
     * 
     * @return String containing the terrain name.
     */
    public String getName();


    /**
     * Returns the color of the terrain. 
     * @return Color object
     */
    public Color getColor();

    /**
     * Returns the character associated with the terrain. 'f' - field, 'r' - road etc.
     * ! Cloister returns X, as c is taken by city.
     * @return char.
     */
    public char getSymbol();

    /**
     * 
     * @return String representation of terrain.
     */
    public String toString();


    /**
     * 
     * @return Truth value of comparing terrains.
     */
    public boolean equals(ITerrain terrain);

}
