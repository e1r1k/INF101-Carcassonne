package no.uib.inf101.carcassonne.view;

import java.awt.geom.*;
import no.uib.inf101.grid.*;

public class CellPositionToPixelConverter {

    Rectangle2D box;
    GridDimension gd;
    double margin;

    /** Konstruktør, setter feltvariabler. */
    public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
        this.box = box;
        this.gd = gd;
        this.margin = margin;
    }

    /** Regner ut hvor hver celle skal opprettes i UI ut ifra størrelsen på vindu og dimensjonen på grid. */
    public Rectangle2D getBoundsForCell(CellPosition cp) {
        double width = this.box.getWidth();
        double height = this.box.getHeight();
        double rows = this.gd.rows();
        double cols = this.gd.cols();
        double rowPos = cp.getRow();
        double colPos = cp.getCol();
        double boxX = this.box.getX();
        double boxY = this.box.getY();
    
        double cellWidth = (width - (this.margin * (cols + 1))) / cols;
        double cellHeight = (height - (this.margin * (rows + 1))) / rows;
    
        double cellX = boxX + this.margin * (colPos+1) + cellWidth * (colPos);
        double cellY = boxY + this.margin * (rowPos+1) + cellHeight * (rowPos);
    
        Rectangle2D cellBounds = new Rectangle2D.Double(cellX, cellY, cellWidth, cellHeight);
        return cellBounds;
    }
}
