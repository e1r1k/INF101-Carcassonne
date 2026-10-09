package no.uib.inf101.carcassonne.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import no.uib.inf101.carcassonne.midi.CarcassonneMusic;
import no.uib.inf101.carcassonne.view.CarcassonneView;
import no.uib.inf101.model.GameState;

public class CarcassonneController implements java.awt.event.KeyListener {

    ControllableCarcassonneModel model;
    CarcassonneView view;
    Timer timer;

    /**  Konstruktør, oppretter timer og keyListener.*/
    public CarcassonneController(ControllableCarcassonneModel model, CarcassonneView view) {
        this.model = model;
        this.view = view;

        CarcassonneMusic music = new CarcassonneMusic();
        music.run();

        view.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (model.getGameState() == GameState.FINISHED) {

        }
        else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.model.moveTile(0, -3);
        }
        else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.model.moveTile(0, 3);
        }
        else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.model.moveTile(3, 0);
        }
        else if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
            this.model.moveTile(-3, 0);
        }
        else if (e.getKeyCode() == KeyEvent.VK_R || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            this.model.rotateTile();
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.model.placeTileOnBoard();
        }  
        this.view.repaint();
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // Trengs ikke for øyeblikket.
        
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // Trengs ikke for øyeblikket.

    }

}

