package no.uib.inf101.carcassonne.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme {

    public DefaultColorTheme() {

    }

    @Override
    public Color getCellColor(char input) {
        Color color = switch (input) {
            case ' ' -> new Color(209, 132, 73);
            default -> throw new IllegalArgumentException(
                    "No available color for '" + input + "'");
        };
        return color;
    }

    public Color getWindowBackground() {
        return new Color(240, 209, 139);
    }

    public Color getScoreHighlight() {
        return new Color(128, 219, 72);
    }

    @Override
    public Color getFrameColor() {
        return new Color(255, 198, 138);
    }

    @Override
    public Color getBackgroundColor() {
        return new Color(107, 62, 27);
    }

    @Override
    public Color getGameOverColor() {
        return new Color(0, 0, 0, 128);
    }

    @Override
    public Color getTextColor() {
        return new Color(255, 255, 255);
    }

}
