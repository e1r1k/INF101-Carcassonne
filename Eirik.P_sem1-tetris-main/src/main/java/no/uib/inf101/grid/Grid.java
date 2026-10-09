package no.uib.inf101.grid;

/**
 *  ! Denne klassen er hentet fra repositoriet vi fikk utdelt til seminaroppgave 1, Tetris.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grid<E> implements IGrid {
  private final int rows;
  private final int cols;
  private final E defaultValue;
  private final ArrayList<ArrayList<GridCell<E>>> grid = new ArrayList<>();

  /**  Konstruktør for to argumenter, default verdi er "null". */
  public Grid(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    this.defaultValue = null;

    for (int i = 0; i < rows + 1; i++) {
      ArrayList<GridCell<E>> row = new ArrayList<>();
      for (int j = 0; j < cols + 1; j++) {
        row.add(new GridCell(new CellPosition(i, j), defaultValue));
      }
      this.grid.add(row);
    }
  }

  /** Konstruktør for tre argumenter, default verdi spesifisert. */
  public Grid(int rows, int cols, E defaultValue) {
    this.rows = rows;
    this.cols = cols;
    this.defaultValue = defaultValue;

    for (int i = 0; i < rows; i++) {
      ArrayList<GridCell<E>> row = new ArrayList<>();
      for (int j = 0; j < cols; j++) {
        row.add(new GridCell(new CellPosition(i, j), defaultValue));
      }
      this.grid.add(row);
    }
  }

  @Override
  public void set(CellPosition pos, Object value) {
    grid.get((pos.getRow())).set((pos.getCol()), new GridCell(pos, value));
  }

  @Override
  public E get(CellPosition pos) {
    return grid.get(pos.getRow()).get(pos.getCol()).getValue();
  }

  public GridCell<E> getCell(int y, int x) {
    return grid.get(y).get(x);
  }

  @Override
  public boolean positionIsOnGrid(CellPosition pos) {
    return (0 <= pos.getRow() && pos.getRow() < this.rows && 0 <= pos.getCol() && pos.getCol() < this.cols);
  }

  @Override
  public int rows() {
    return this.rows;
  }

  @Override
  public int cols() {
    return this.cols;
  }

  @Override
  public Iterator<GridCell<E>> iterator() {
    ArrayList<GridCell<E>> allCells = new ArrayList<>();
    for (ArrayList<GridCell<E>> column : this.grid) {
      allCells.addAll(column);
    }
    return allCells.iterator();
  }
}
