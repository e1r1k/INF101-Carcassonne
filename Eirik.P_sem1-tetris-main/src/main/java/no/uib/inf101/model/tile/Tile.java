package no.uib.inf101.model.tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.model.tile.*;
import no.uib.inf101.model.tile.terrain.*;

public class Tile implements Iterable<GridCell<ITerrain>>{
    private final String code;
    private ITerrain[][] shape;
    private CellPosition pos;
    private ArrayList<GridCell<ITerrain>> features;
    private static final CellPosition STARTPOSITION = new CellPosition(3, 3);
    

    /**
     * Kontruktør, setter feltvariabler
     * @param code String-kode som representerer kantene på tile.
     * @param shape Selve arrayet som inneholder terrengene.
     * @param pos   Posisjonen til terrenget øverst til venstre i relasjon til brettet.
     */
    public Tile(String code, ITerrain[][] shape, CellPosition pos) {
        this.code = code;
        this.shape = shape;
        this.pos = pos;
        this.features = new ArrayList<>();
    }

    /** Genererer en ny tile basert på kode, og plasserer den øverst til venstre på brettet.  
     * Legger også til ulike feature-plasser alt etter hva slags tile det er.
     * @param code Hvilken form og symbol tilen skal ha.
     */
    public static Tile newTile(String code) {
        if (code == "RFRF") {
            Tile tile = new Tile("RFRF",
                    new ITerrain[][] { {new Field(), new Field(), new Field()},
                                       {new Road(), new Road(), new Road()},
                                       {new Field(), new Field(), new Field()}},
                    STARTPOSITION);
            tile.features.add(tile.getCell(4));
            return tile;
        }
        else if (code == "CRFR") {
            Tile tile = new Tile("CRFR",
                    new ITerrain[][] { {new City(), new Road(), new Field()},
                                        {new City(), new Road(), new Field()},
                                        {new City(), new Road(), new Field()}},
                                        STARTPOSITION);
            tile.features.add(tile.getCell(3));
            tile.features.add(tile.getCell(4));
            return tile;
        }
        else if (code == "RRFF") {
            Tile tile = new Tile("RRFF",
                    new ITerrain[][] { {new Field(), new Field(), new Field()},
                                        {new Road(), new Road(), new Field()},
                                        {new Field(), new Road(), new Field()}},
                                        STARTPOSITION);
            tile.features.add(tile.getCell(4));
            return tile;
        }
        else if (code == "RFRR") {
            Tile tile = new Tile("RFRR",
                    new ITerrain[][] { {new Field(), new Field(), new Field()},
                                        {new Road(), new Road(), new Road()},
                                        {new Field(), new Road(), new Field()}},
                                        STARTPOSITION);
            tile.features.add(tile.getCell(4));
            return tile;
        }
        else if (code == "RRRR") {
            Tile tile = new Tile("RRRR",
                    new ITerrain[][] { {new Field(), new Road(), new Field()},
                                        {new Road(), new Road(), new Road()},
                                        {new Field(), new Road(), new Field()}},
                                        STARTPOSITION);
            tile.features.add(tile.getCell(4));
            return tile;
        }
        else if (code == "RCFR") {
            Tile tile = new Tile("RCFR",
                    new ITerrain[][] { {new City(), new City(), new City()},
                                        {new Road(), new Road(), new Field()},
                                        {new Field(), new Road(), new Field()}},
                                        STARTPOSITION);
            tile.features.add(tile.getCell(1));
            tile.features.add(tile.getCell(4));
            return tile;
        }
        else if (code == "FCRR") {
            Tile tile = new Tile("FCRR",
                    new ITerrain[][] { {new City(), new City(), new City()},
                                        {new Field(), new Road(), new Road()},
                                        {new Field(), new Road(), new Field()}},
                                        STARTPOSITION);
            tile.features.add(tile.getCell(1));
            tile.features.add(tile.getCell(4));
            return tile;
        }
        else if (code == "RCRR") {
            Tile tile = new Tile("RCRR",
                    new ITerrain[][] { {new City(), new City(), new City()},
                                        {new Road(), new Road(), new Road()},
                                        {new Field(), new Road(), new Field()}},
                                        STARTPOSITION);
            tile.features.add(tile.getCell(1));
            tile.features.add(tile.getCell(4));
            return tile;
        }
        else if (code == "FCFF") {
            Tile tile = new Tile("FCFF",
                    new ITerrain[][] { {new City(), new City(), new City()},
                                        {new Field(), new Field(), new Field()},
                                        {new Field(), new Field(), new Field()}},
                                        STARTPOSITION);
            tile.features.add(tile.getCell(1));
            return tile;
        }
        else if (code == "FCFC") {
            Tile tile = new Tile("FCFC",
                    new ITerrain[][] { {new City(), new City(), new City()},
                                        {new Field(), new Field(), new Field()},
                                        {new City(), new City(), new City()}},
                                        STARTPOSITION);
            tile.features.add(tile.getCell(1));
            tile.features.add(tile.getCell(7));
            return tile;
        }
        else if (code == "CCFF") {
            Tile tile = new Tile("CCFF",
                    new ITerrain[][] { {new City(), new City(), new City()},
                                        {new City(), new City(), new Field()},
                                        {new City(), new Field(), new Field()}},
                                        STARTPOSITION);
            tile.features.add(tile.getCell(4));
            return tile;
        }
        else if (code == "CFCF") {
            Tile tile = new Tile("CFCF",
                    new ITerrain[][] { {new City(), new Field(), new Field()},
                                        {new City(), new City(), new City()},
                                        {new Field(), new Field(), new City()}},
                                        STARTPOSITION);
            tile.features.add(tile.getCell(4));
            return tile;
        }
        else if (code == "CCRR") {
            Tile tile = new Tile("CRFR",
                    new ITerrain[][] { {new City(), new City(), new City()},
                                        {new City(), new Road(), new Road()},
                                        {new City(), new Road(), new Field()}},
                                        STARTPOSITION);
            tile.features.add(tile.getCell(1));
            tile.features.add(tile.getCell(4));
            return tile;
        }
        else if (code == "CCCR") {
            Tile tile = new Tile("CCCR",
                    new ITerrain[][] { {new City(), new City(), new City()},
                                        {new City(), new Road(), new City()},
                                        {new Field(), new Road(), new Field()}},
                                        STARTPOSITION);
            tile.features.add(tile.getCell(1));
            tile.features.add(tile.getCell(4));
            return tile;
        }
        else if (code == "CCCF") {
            Tile tile = new Tile("CCCF",
                    new ITerrain[][] { {new City(), new City(), new City()},
                                        {new City(), new Field(), new City()},
                                        {new Field(), new Field(), new Field()}},
                                        STARTPOSITION);
            tile.features.add(tile.getCell(1));
            return tile;
        }
        else if (code == "CCCC") {
            Tile tile = new Tile("CCCC",
                    new ITerrain[][] { {new City(), new City(), new City()},
                                        {new City(), new City(), new City()},
                                        {new City(), new City(), new City()}},
                                        STARTPOSITION);
            tile.features.add(tile.getCell(4));
            return tile;
        }
        else if (code == "XF") {
            Tile tile = new Tile("XF",
                new ITerrain[][] { {new Field(), new Field(), new Field()},
                                    {new Field(), new Monastery(), new Field()},
                                    {new Field(), new Field(), new Field()}},
                                    STARTPOSITION);
            tile.features.add(tile.getCell(4));
            return tile;
        }
        else if (code == "XR") {
            Tile tile = new Tile("XR",
                    new ITerrain[][] { {new Field(), new Field(), new Field()},
                                        {new Field(), new Monastery(), new Field()},
                                        {new Field(), new Road(), new Field()}},
                                        STARTPOSITION);
            tile.features.add(tile.getCell(4));
            tile.features.add(tile.getCell(7));
            return tile;
        }
        else if (code == "EMPTY") {
            return new Tile("EMPTY",
                    new ITerrain[][] { {new EmptyTerrain(), new EmptyTerrain(), new EmptyTerrain()},
                                       {new EmptyTerrain(), new EmptyTerrain(), new EmptyTerrain()},
                                       {new EmptyTerrain(), new EmptyTerrain(), new EmptyTerrain()}},
                                       STARTPOSITION);
        }

        else {
            throw new IllegalArgumentException(code + " is not a valid tile.");
        }
    }

    /** Lager en ny tile av samme familie og verdi, bare forskjøvet med deltaRow og deltaCol
     * @param deltaRow Hvor mange rader tile forskyves
     * @param deltaCol Hvor mange kolonner tile forskyves
     * @return Tile med ny posisjon.
     */
    public Tile shiftedBy(int deltaRow, int deltaCol) {
        // Create a new Tile with the shifted position
        Tile candidate = new Tile(this.code, this.shape, new CellPosition((this.pos.getRow() + deltaRow), (this.pos.getCol() + deltaCol)));
    
        // Update the positions of the features
        ArrayList<GridCell<ITerrain>> shiftedFeatures = new ArrayList<>();
        for (GridCell<ITerrain> feature : this.features) {
            // Get the row and column of the feature
            int row = feature.getPos().getRow();
            int col = feature.getPos().getCol();
    
            // Calculate the new position of the feature after shifting
            int shiftedRow = row + deltaRow;
            int shiftedCol = col + deltaCol;
    
            // Create a new GridCell with the shifted position and the same terrain
            GridCell<ITerrain> shiftedFeature = new GridCell<>(new CellPosition(shiftedRow, shiftedCol), feature.getValue());
    
            // Add the shifted feature to the list
            shiftedFeatures.add(shiftedFeature);
        }
    
        // Update the features ArrayList of the shifted tile
        candidate.features = shiftedFeatures;
    
        return candidate;
    }

    

    @Override
    public Iterator<GridCell<ITerrain>> iterator() {
        ArrayList<GridCell<ITerrain>> list = new ArrayList<>();
        int row = this.pos.getRow() - 1;
        int col = this.pos.getCol() - 1;
        for (ITerrain[] i : this.shape) {
            row += 1;
            col = this.pos.getCol() - 1;
            for (ITerrain j : i) {
                col += 1;
                list.add(new GridCell<ITerrain>(new CellPosition(row, col), j));
                }
        }
        return list.iterator();
    }

    /**
     *  _________________________
     *  |   0   |   1   |   2   |
     *  |   3   |   4   |   5   |   
     *  |   6   |   7   |   8   |
     * @param index
     * @return Terrain in index position
     */
    public ITerrain getTerrain(int index) {
        try {
            int counter = 0;
            for (GridCell<ITerrain> i : this) {
                if (counter == index) {
                    return i.getValue();
                }
                counter += 1;
            }
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Index does not exist. Tiles have an index of 0 - 8.");
        }
        return null;
    }
    /**
     * Same as getTerrain, but returns a gridCell containing the terrain and a cellposition.
     * @param index
     * @return GridCell-object
     */
    public GridCell<ITerrain> getCell(int index) {
        try {
            int counter = 0;
            for (GridCell<ITerrain> i : this) {
                if (counter == index) {
                    return i;
                }
                counter += 1;
            }
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Index does not exist. Tiles have an index of 0 - 8.");
        }
        return null;
    }
    
    /**
     * 
     * @return GridCells that can be used for adding meeples when placing a tile.
     */
    public ArrayList<GridCell<ITerrain>> getFeatures() {
        return this.features;
    }

    @Override 
    public String toString() {
        String tileString = "";
        for (ITerrain[] i : this.shape) {
            for (ITerrain j : i) {
                tileString += j.getSymbol();
            }
            tileString += "\n";
        }
        return tileString.strip();
    }

    // Inspirert av kode fra https://www.baeldung.com/java-equals-hashcode-contracts, hentet 12/03/2024.
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;}
        if (!(o instanceof ITerrain) || (o == null)) {
            return false;}
        
        Tile other = (Tile) o;

        boolean nameEqual = this.code == other.getCode();
        boolean shapeEqual = (this.shape == null && other.shape == null) 
            ||  (this.shape != null && Arrays.deepEquals(this.shape, other.shape));
        boolean posEqual = (this.pos == null && other.pos == null) 
            ||  (this.pos != null && this.pos.equals(other.pos));

        return nameEqual && shapeEqual && posEqual;
    }
    
    @Override
    public final int hashCode() {
        int result = 17;
        return 31 * result + Objects.hash(this.code, Arrays.deepHashCode(this.shape), this.pos);
    }

    /** Lager en ny tile i samme posisjon, med shape-arrayet rotert 90 grader med klokken.
     * @return Tile.
     */
    public Tile rotate() {
        ITerrain[][] rotatedShape = rotateShape();
        Tile rotated = new Tile(this.code, rotatedShape, this.pos);
    
        ArrayList<GridCell<ITerrain>> rotatedFeatures = new ArrayList<>();
        for (GridCell<ITerrain> feature : this.features) {
            int row = feature.getPos().getRow() - this.pos.getRow();
            int col = feature.getPos().getCol() - this.pos.getCol();
            int rotatedRow = col + this.pos.getRow();
            int rotatedCol = rotatedShape.length - 1 - row + this.pos.getCol();
    
            GridCell<ITerrain> rotatedFeature = new GridCell<>(new CellPosition(rotatedRow, rotatedCol), feature.getValue());

            rotatedFeatures.add(rotatedFeature);
        }
        rotated.features = rotatedFeatures;
    
        return rotated;
    }

    /** Roterer et kvadratisk array av terrain-elementer 90 grader med klokken. */
    private ITerrain[][] rotateShape() {
        int dim = this.shape.length;
        ITerrain[][] rotated = new ITerrain[dim][dim];

        for (int i = 0; i < dim; i++){
            for (int j = 0; j < dim; j++) {
                rotated[j][dim - i- 1] = this.shape[i][j];
            }
        }
        return rotated;
    }

    /** Returnerer posisjonen til en tile. */
    public CellPosition getPos() {
        return this.pos;
    }

    /** Returnerer formen til en tile. */
    public ITerrain[][] getShape() {
        return this.shape;
    }

    /**
     * Return the string representation of the tile. Code is made up of four letters, symbolizing
     * the four edges of a tile and the features they contain. For example a straight road through
     * a field would be "RFRF". The string is the same no matter orientation.
     * @return Four-letter string representing tile.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Returns false if any of the terrains in a tile are empty, true if not.
     * @return boolean
     */
    public boolean isEmpty() {
        for (GridCell<ITerrain> i : this) {
            if (!(i.getValue().getSymbol() == ' ')) {
                return false;
            }
        }
        return true;
    }

}
