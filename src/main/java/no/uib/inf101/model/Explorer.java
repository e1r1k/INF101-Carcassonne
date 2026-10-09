package no.uib.inf101.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import no.uib.inf101.carcassonne.view.ViewableCarcassonneModel;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.boolGCListMeepleTriple;
import no.uib.inf101.model.tile.Meeple;
import no.uib.inf101.model.tile.Tile;
import no.uib.inf101.model.tile.terrain.City;
import no.uib.inf101.model.tile.terrain.EmptyTerrain;
import no.uib.inf101.model.tile.terrain.ITerrain;
import no.uib.inf101.model.tile.terrain.Monastery;
import no.uib.inf101.model.tile.terrain.Road;

public class Explorer {

    ViewableCarcassonneModel model;
    
    /**
     * Class containing methods for exploring features. All exploration is done in relation to 
     * the current tile in focus of the model, which is passed to the explorer in its constructor.
     * @param model ViewableCarcassonneModel object.
     */
    public Explorer(ViewableCarcassonneModel model) {
        this.model = model;
    }

    /**
     * A depth-first search exploring connected equal tiles. Returns a bool representing if the search found any empty tiles 
     * (true meaning yes - the feature is unfinished, false meaning no - the feature is complete), and a list of alle the 
     * connected cells.
     * @param gc Start-cell
     * @param visited Visited cells. When called outside of the method, will usually contain an empty list.
     * @param startTerrain The terrain we are comparing with. Usually the gc.getValue().
     * @return  A pair containing a boolean and a list of the cells
     *          belonging to the feature.
     */
    public boolGCListMeepleTriple exploreCity(GridCell<ITerrain> gc, ArrayList<GridCell<ITerrain>> visited) {
        boolean emptyTerrainFound = false;
        if (gc.getValue().equals(new EmptyTerrain())) {
            emptyTerrainFound = true;
        }
            if (gc.getValue().equals(new City())) {
                visited.add(gc);
        
                GridCell<ITerrain> left = model.getBoard().getCell(gc.getPos().getRow(), gc.getPos().getCol() - 1);
                GridCell<ITerrain> top = model.getBoard().getCell(gc.getPos().getRow() - 1, gc.getPos().getCol());
                GridCell<ITerrain> right = model.getBoard().getCell(gc.getPos().getRow(), gc.getPos().getCol() + 1);
                GridCell<ITerrain> bottom = model.getBoard().getCell(gc.getPos().getRow() + 1, gc.getPos().getCol());
        
                if (!visited.contains(left)) {
                    emptyTerrainFound |= exploreCity(left, visited).getKey();
                }
                if (!visited.contains(top)) {
                    emptyTerrainFound |= exploreCity(top, visited).getKey();
                }
                if (!visited.contains(right)) {
                    emptyTerrainFound |= exploreCity(right, visited).getKey();
                }
                if (!visited.contains(bottom)) {
                    emptyTerrainFound |= exploreCity(bottom, visited).getKey();
                }
        }

        HashMap<String, Integer> meeples = new HashMap<>();
        meeples.put("P1", 0);
        meeples.put("P2", 0);
        for (GridCell<ITerrain> cell : visited) {
            if (cell.getValue().getMeeple() != null) {
                meeples.merge(cell.getValue().getMeeple().getTeam(), 1, Integer::sum);
            }
        }
        System.out.println(visited);
        return new boolGCListMeepleTriple(emptyTerrainFound, visited, meeples);
    }

    /**
     * Works a lot like the city-exploration search, only with backtracking.
     * @param gc Starting cell
     * @param parent Parent cell
     * @param visited Every visited cell
     * @param path Current visited path.
     * @return Triple containing a bool, list of gridcells and list of meeples.
     */
    public boolGCListMeepleTriple exploreRoad(GridCell<ITerrain> gc, GridCell<ITerrain> parent, ArrayList<GridCell<ITerrain>> visited, ArrayList<GridCell<ITerrain>> path) {
        visited.add(gc);
        path.add(gc);

        HashMap<String, Integer> meeples = new HashMap<>();
                        meeples.put("P1", 0);
                        meeples.put("P2", 0);
    
        GridCell<ITerrain> left = model.getBoard().getCell(gc.getPos().getRow(), gc.getPos().getCol() - 1);
        GridCell<ITerrain> top = model.getBoard().getCell(gc.getPos().getRow() - 1, gc.getPos().getCol());
        GridCell<ITerrain> right = model.getBoard().getCell(gc.getPos().getRow(), gc.getPos().getCol() + 1);
        GridCell<ITerrain> bottom = model.getBoard().getCell(gc.getPos().getRow() + 1, gc.getPos().getCol());
    
        for (GridCell<ITerrain> neighbour : new GridCell[]{left, top, right, bottom}) {
            if (neighbour != null && neighbour.getValue().equals(new Road()) && !(model.spentRoads).contains(neighbour)) {
                if (!visited.contains(neighbour)) {
                    if (exploreRoad(neighbour, gc, visited, path).getKey()) {
                        for (GridCell<ITerrain> cell : visited) {
                            if (cell.getValue().getMeeple() != null) {
                                meeples.merge(cell.getValue().getMeeple().getTeam(), 1, Integer::sum);
                            }
                        }
                        (model.spentRoads).addAll(path);
                        return new boolGCListMeepleTriple(true, path, meeples);
                    }
                } else if (!neighbour.equals(parent) && path.contains(neighbour)) {
                    for (GridCell<ITerrain> cell : visited) {
                        if (cell.getValue().getMeeple() != null) {
                            if (cell.getValue().getMeeple() != null) {
                                meeples.merge(cell.getValue().getMeeple().getTeam(), 1, Integer::sum);
                            }
                        }
                    }    
                    (model.spentRoads).addAll(path);
                    return new boolGCListMeepleTriple(true, path, meeples); 
                }
            }
        }
        path.remove(gc);
        return new boolGCListMeepleTriple(false, path, meeples);
    }

    private ArrayList<GridCell<ITerrain>> getSurroundingTileMiddles(GridCell gc) {
        ArrayList<GridCell<ITerrain>> surroundingTileMiddles = new ArrayList<>();

        GridCell<ITerrain> L = model.getBoard().getCell(gc.getPos().getRow()+1, gc.getPos().getCol()-2);
        GridCell<ITerrain> TL = model.getBoard().getCell(gc.getPos().getRow()-2, gc.getPos().getCol()-2);
        GridCell<ITerrain> T = model.getBoard().getCell(gc.getPos().getRow()-2, gc.getPos().getCol()+1);
        GridCell<ITerrain> TR = model.getBoard().getCell(gc.getPos().getRow()-2, gc.getPos().getCol()+4);
        GridCell<ITerrain> R = model.getBoard().getCell(gc.getPos().getRow()+1, gc.getPos().getCol()+4);
        GridCell<ITerrain> BR = model.getBoard().getCell(gc.getPos().getRow()+4, gc.getPos().getCol()+4);
        GridCell<ITerrain> B = model.getBoard().getCell(gc.getPos().getRow()+4, gc.getPos().getCol()+1);
        GridCell<ITerrain> BL = model.getBoard().getCell(gc.getPos().getRow()+4, gc.getPos().getCol()-2);

        surroundingTileMiddles.add(L);
        surroundingTileMiddles.add(TL);
        surroundingTileMiddles.add(T);
        surroundingTileMiddles.add(TR);
        surroundingTileMiddles.add(R);
        surroundingTileMiddles.add(BR);
        surroundingTileMiddles.add(B);
        surroundingTileMiddles.add(BL);

        return surroundingTileMiddles;
    }

    public boolGCListMeepleTriple exploreMonastery() {
        ArrayList<GridCell<ITerrain>> surroundingTileMiddles = getSurroundingTileMiddles(model.getTile().getCell(0));
        HashMap<String, Integer> meeples = new HashMap<>();
        meeples.put("P1", 0);
        meeples.put("P2", 0);

        for (GridCell<ITerrain> gc : surroundingTileMiddles) {
            if (gc.getValue().equals(new Monastery())) {
                if (gc.getValue().getMeeple() != null) {
                    meeples.merge(gc.getValue().getMeeple().getTeam(), 1, Integer::sum);
                }
                    ArrayList<GridCell<ITerrain>> tileMiddlesSurroundingMonastery  = getSurroundingTileMiddles(gc);

                    for (GridCell<ITerrain> terrain : tileMiddlesSurroundingMonastery) {
                        if (terrain.getValue().getSymbol() == ' ') {
                            return new boolGCListMeepleTriple(false, tileMiddlesSurroundingMonastery, meeples);
                        }
                    }
                    if (tileMiddlesSurroundingMonastery.size() == 8) {
                        return new boolGCListMeepleTriple(true, 
                                                            tileMiddlesSurroundingMonastery, 
                                                            meeples);}

                return new boolGCListMeepleTriple(false, tileMiddlesSurroundingMonastery, meeples);
            }
        }
        return new boolGCListMeepleTriple(false, surroundingTileMiddles, meeples);
    }
}
