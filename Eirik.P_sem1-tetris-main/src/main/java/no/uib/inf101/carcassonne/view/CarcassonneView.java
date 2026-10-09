package no.uib.inf101.carcassonne.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JPanel;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.model.GameState;
import no.uib.inf101.model.tile.terrain.ITerrain;

public class CarcassonneView extends JPanel implements ActionListener {
    
  private ViewableCarcassonneModel model;
  private static DefaultColorTheme defaultTheme = new DefaultColorTheme();
  private static final double OUTERMARGIN = 10;
  private static final double INNERMARGIN = 1;
  private static final double BOTTOMMARGIN = 120;
  private final JButton meepleButton;
  private final JButton nextTurnButton;

  // Constructor
  public CarcassonneView(ViewableCarcassonneModel model) {
    super();
    this.model = model;
    this.setFocusable(true);
    this.setPreferredSize(new Dimension(1080, 840));
    this.setBackground(new Color(173, 133, 81));

    meepleButton = new JButton("Add meeple");
    nextTurnButton = new JButton("End turn");

    meepleButton.setBounds(120, 720, 300, 100);
    meepleButton.setBackground(new Color(240, 209, 139));
    nextTurnButton.setBounds(1080 - 300 - 120, 720, 300, 100);
    nextTurnButton.setBackground(defaultTheme.getWindowBackground());

    this.setLayout(null);


    meepleButton.addActionListener(this);
    nextTurnButton.addActionListener(this);
    this.add(meepleButton);
    this.add(nextTurnButton);
  }

  @Override 
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == nextTurnButton) {

      if (model.getMovementLock()) {
        model.scanFeatures();

        if (model.getGameState() == GameState.PLAYER_ONE) {
          model.setGameState(GameState.PLAYER_TWO);
          }
        else if (model.getGameState() == GameState.PLAYER_TWO) {
            model.setGameState(GameState.PLAYER_ONE);
          }

        model.newTile();
        this.repaint();
      }
      this.requestFocusInWindow();
    }

    else if (e.getSource() == meepleButton) {
      if (model.getMovementLock()) {
        MeeplePlacer.showMeeplePlacer(model.getTile(), model);
      }
      this.requestFocusInWindow();
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D canvas = (Graphics2D) g;

    drawGame(canvas);
  }

  /** Tegner spillet inn i UI.  */
  public void drawGame(Graphics2D canvas) {

    // Regner ut dimensjonene til spillbrettet utfra vinduet, til bruk senere.
    double width = this.getWidth()-2*OUTERMARGIN;
    double height = this.getHeight()-6*OUTERMARGIN-BOTTOMMARGIN;

    // Tegner bakgrunnen til spillbrettet. Ganger OUTERMARGIN med 4 for å gi plass til score senere.
    Rectangle2D box = new Rectangle2D.Double(OUTERMARGIN, OUTERMARGIN * 4, width, height); 
    canvas.setColor(defaultTheme.getBackgroundColor());
    canvas.fill(box);

    // Tegner boksen der score vises.
    Rectangle2D scoreBox = new Rectangle2D.Double(OUTERMARGIN, OUTERMARGIN, width, OUTERMARGIN * 4);
    canvas.fill(scoreBox);
    Rectangle2D scoreBoxBottomBorder = new Rectangle2D.Double(OUTERMARGIN, (OUTERMARGIN*4) - 3, width, 3);
    canvas.setColor(defaultTheme.getWindowBackground());
    canvas.fill(scoreBoxBottomBorder);

    // Highlights the score of the player in turn.
    if (this.model.getGameState() == GameState.PLAYER_ONE) {
      Rectangle2D playerOneBox = new Rectangle2D.Double(OUTERMARGIN+2, OUTERMARGIN+2, 290, 23);
      canvas.setColor(defaultTheme.getScoreHighlight());
      canvas.fill(playerOneBox);
    }
    else if (this.model.getGameState() == GameState.PLAYER_TWO) {
      Rectangle2D playerTwoBox = new Rectangle2D.Double(this.getWidth()-OUTERMARGIN-292, OUTERMARGIN+2, 290, 23);
      canvas.setColor(defaultTheme.getScoreHighlight());
      canvas.fill(playerTwoBox);
    }
    
    // Player one score
    canvas.setColor(defaultTheme.getTextColor());
    canvas.setFont(new Font("Arial", Font.BOLD, (int) OUTERMARGIN * 3 - 8));
    String playerOneScoreText = "Player one: " + this.model.getPlayerOneScore().toString() + " | " + model.getP1Meeples() + " meeples";
    int scoreX = (20);
    int scoreY = (int) OUTERMARGIN + 20;
    canvas.drawString(playerOneScoreText, scoreX, scoreY);

    // PLayer two score
    String playerTwoScoreText = "Player two: " + this.model.getPlayerTwoScore().toString() + " | " + model.getP2Meeples() + " meeples";
    int scoreTextWidthP2 = canvas.getFontMetrics().stringWidth(playerTwoScoreText); 
    int scoreXP2 = (int) (this.getWidth() - OUTERMARGIN * 2 - scoreTextWidthP2);
    int scoreYP2 = (int) OUTERMARGIN + 20;
    canvas.drawString(playerTwoScoreText, scoreXP2, scoreYP2);
    

    // Tegner grid, tegner tile etterpå.
    CellPositionToPixelConverter converter = new CellPositionToPixelConverter(box, this.model.getDimension(), INNERMARGIN);
    drawCells(canvas, this.model.getTilesOnBoard(), converter, defaultTheme);
    drawCells(canvas, this.model.getTileTerrains(), converter, defaultTheme);

    // Tegner knappene for meeples og end turn.


    // Denne blokken tegner game finished - displayet over hele skjermen når kortstokken er tom. 
    // Game finished- displayet består av en svart, gjennomsiktig boks med "Game finished" og score under.
    if (this.model.getGameState() == GameState.FINISHED) {
      Rectangle2D gameFinishedBox = new Rectangle2D.Double(0, 0, this.getWidth(), this.getHeight());
      canvas.setColor(defaultTheme.getGameOverColor());
      canvas.fill(gameFinishedBox);

      canvas.setColor(defaultTheme.getTextColor());
      canvas.setFont(new Font("Arial", Font.BOLD, this.getWidth()/10));
      String gameFinishedText = "GAME FINISHED";

      int textWidth = canvas.getFontMetrics().stringWidth(gameFinishedText);
      int textHeight = canvas.getFontMetrics().getHeight();
      int goX = ((this.getWidth() - textWidth) / 2);
      int goY = ((this.getHeight() + textHeight) / 2) - 100;
      canvas.drawString(gameFinishedText, goX, goY);

      canvas.setFont(new Font("Arial", Font.BOLD, this.getWidth()/30));
      String endGameScoreText = "Player one: " + this.model.getPlayerOneScore().toString() + "pts                  " +
                                "Player two: " + this.model.getPlayerTwoScore().toString() + "pts";
      System.out.println(endGameScoreText);

      int endGameScoreTextWidth = canvas.getFontMetrics().stringWidth(endGameScoreText);
      int endGameScoreTextHeight = canvas.getFontMetrics().getHeight();
      int endGameScoreX = ((this.getWidth() - endGameScoreTextWidth) / 2);
      int endGameScoreY = ((this.getHeight() + endGameScoreTextHeight) / 2) + textHeight - 100;
      canvas.drawString(endGameScoreText, endGameScoreX, endGameScoreY);
      // Game finished slutter her
    }
  }

  /** Tegner alle cellene i grid på UI ved hjelp av cellPositionToPixelConverter. */
  private static void drawCells(
      Graphics2D canvas,
      Iterable<GridCell<ITerrain>> grid,
      CellPositionToPixelConverter converter,
      ColorTheme theme) {

        for (GridCell<ITerrain> i : grid) {
          CellPosition pos = i.getPos();
          Color color = i.getValue().getColor();
          Rectangle2D cellGraphics = converter.getBoundsForCell(pos);
          
          canvas.setColor(color);
          canvas.fill(cellGraphics);
          }
        }
}
