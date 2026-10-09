package no.uib.inf101.carcassonne.view;

import java.awt.Color;

public interface ColorTheme {

    /**
     * Returns a color corresponding to a character defined in inplementing class. Not 
     * necessarily bound to first letter of color name.
     * 
     * @param input the character for converting to color.
     * @return color object.
     */
    public Color getCellColor(char input);

    /**
     * Gets the color of the frame. Should not be null, but could
     * be transparent (new Color(0, 0, 0, 0)) for borderless tiles.
     * 
     * @return color object.
     */
    public Color getFrameColor();

    /**
     * Gets the color of the background. Can not be transparent, but can
     * be null. Then it will return the default background color from java. 
     * 
     * @return color object.
     */
    public Color getBackgroundColor();

    /**
     * Gets the color of the game over screen.
     * 
     * @return color object.
     */
    public Color getGameOverColor();  
    
    /**
     * Gets the text color for menus.
     * 
     * @return Color object.
     */
    public Color getTextColor();
}
