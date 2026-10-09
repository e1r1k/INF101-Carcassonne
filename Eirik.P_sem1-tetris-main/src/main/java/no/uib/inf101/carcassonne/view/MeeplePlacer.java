package no.uib.inf101.carcassonne.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.testng.internal.collections.Pair;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.model.GameState;
import no.uib.inf101.model.tile.Meeple;
import no.uib.inf101.model.tile.Tile;
import no.uib.inf101.model.tile.terrain.ITerrain;

public class MeeplePlacer {

    public static void showMeeplePlacer(Tile tile, ViewableCarcassonneModel model) {
        ArrayList<GridCell<ITerrain>> features = tile.getFeatures();
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(features.size(), 1));

        // Radiobutton-system hentet fra chatGPT, tilpasset egen kode.
        ButtonGroup group = new ButtonGroup();
        for (GridCell<ITerrain> cell : features) {
            Integer counter = 0;
            JRadioButton radioButton = new JRadioButton(cell.getValue().getName());
            radioButton.setActionCommand(cell.getValue().getName() + " " + counter.toString());
            group.add(radioButton);
            panel.add(radioButton);
            counter += 1;
        }

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {

            if (group.getSelection() == null) {
                System.out.println("No feature selected.");
                frame.dispose();
            }
            else {
                String[] selectedFeature = group.getSelection().getActionCommand().split(" ");
                Integer selectedFeatureIndex = Integer.valueOf(selectedFeature[1]);
                String selectedFeatureName = selectedFeature[0];

                if (model.getGameState() == GameState.PLAYER_ONE) {
                    if (model.countMeeples("P1") > 0) {
                    model.getTile().getFeatures().get(selectedFeatureIndex).getValue().setMeeple(new Meeple("P1"));
                    model.subtractMeeples("P1", 1);
                    }
                }
                else if (model.getGameState() == GameState.PLAYER_TWO) {
                    if (model.countMeeples("P2") > 0) {
                    model.getTile().getFeatures().get(selectedFeatureIndex).getValue().setMeeple(new Meeple("P2"));
                    model.subtractMeeples("P2", 1);
                    }
                }
                System.out.println("Meeple placed on " + selectedFeatureName);
                frame.dispose(); 
            }
        });
        
        frame.add(panel, BorderLayout.CENTER);
        frame.add(confirmButton, BorderLayout.SOUTH);

        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

