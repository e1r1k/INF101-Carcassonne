package no.uib.inf101.model.tile;

import no.uib.inf101.carcassonne.view.ViewableCarcassonneModel;
import no.uib.inf101.grid.CellPosition;

public class AdjacencyChecker {

    /**
     * Checks the adjacent terrains of the tile to make sure it can be placed in a position.
     * @param candidate Tile to investigate
     * @return  Boolean - yes: tile can be placed according to rules, no: tile can not be placed according to rules.
     */
    public static boolean checkAdjacency(Tile candidate, ViewableCarcassonneModel model) {
        int x = candidate.getPos().getCol();
        int y = candidate.getPos().getRow();
        char top;
        char left;
        char right;
        char bottom;
        char tileTop = candidate.getTerrain(1).getSymbol();
        char tileLeft = candidate.getTerrain(3).getSymbol();
        char tileRight = candidate.getTerrain(5).getSymbol();
        char tileBottom = candidate.getTerrain(7).getSymbol();

        boolean topClear;
        boolean leftClear;
        boolean rightClear;
        boolean bottomClear;

        // Checks if tile is on the edge of the board. Edges shuld count as empty terrains and be allowed.
        if (x == 0) {
            left = ' ';
            leftClear = true;
        }
        else {
            left = model.getBoard().get(new CellPosition(y+1, x-1)).getSymbol();
            if ((left == ' ') || (left == tileLeft)) {
                leftClear = true;
            }
            else {leftClear = false;}}

        if (y == 0) {
            top = ' ';
            topClear = true;
        }
        else {
            top = model.getBoard().get(new CellPosition(y-1, x+1)).getSymbol();
            if ((top == ' ') || (top == tileTop)) {
                topClear = true;
            }
            else {topClear = false;}}

        if (y == model.getBoard().rows() - 3) {
            bottom = ' ';
            bottomClear = true;}
        else {
            bottom = model.getBoard().get(new CellPosition(y+3, x+1)).getSymbol();}
            if ((bottom == ' ') || (bottom == tileBottom)) {
                bottomClear = true;
            }
            else {bottomClear = false;}

        if (x == model.getBoard().rows() - 3) {
            right = ' ';
            rightClear = true;}
        else {
            right = model.getBoard().get(new CellPosition(y+1, x+3)).getSymbol();
            if ((right == ' ') || (right == tileRight)) {
                rightClear = true;}
            else {rightClear = false;}}
        // You're not supposed to place unconnected tiles.
        if (left == ' ' && right == ' ' && top == ' ' && bottom == ' ') {
            return false;
        }
        return (topClear && leftClear && rightClear && bottomClear);
    }
}
