package no.uib.inf101.carcassonne;

import javax.swing.JFrame;

import no.uib.inf101.carcassonne.controller.CarcassonneController;
import no.uib.inf101.carcassonne.view.CarcassonneView;
import no.uib.inf101.model.*;
import no.uib.inf101.model.board.CarcassonneBoard;
import no.uib.inf101.model.tile.Deck;

public class CarcassonneMain {
    private static final String WINDOW_TITLE = "INF101 Carcassonne";
  
  public static void main(String[] args) {
    CarcassonneBoard board = new CarcassonneBoard(60, 60);

    Deck deck = new Deck(); 

    CarcassonneModel model = new CarcassonneModel(board, deck);
    CarcassonneView view = new CarcassonneView(model);
    CarcassonneController controller = new CarcassonneController(model, view);

    // The JFrame is the "root" application window.
    // We here set som properties of the main window, 
    // and tell it to display our tetrisView
    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // Here we set which component to view in our window
    frame.setContentPane(view);
    
    // Call these methods to actually display the window
    frame.pack();
    frame.setVisible(true);



  }
}
