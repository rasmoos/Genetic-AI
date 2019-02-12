package engine.util;

import java.awt.*;
import java.awt.font.*;

public class Text {

    private Font font;
    private int color;

    private FontRenderContext frc;

    public Text(Font font, int color) {
        this.font = font;
        this.color = color;

        frc = new FontRenderContext(font.getTransform(), true, true);
    }

    public int getWidth(String text) {
        return (int) font.getStringBounds(text, frc).getWidth();
    }

    public int getHeight(String text) {
        return (int) (font.getLineMetrics(text, frc).getAscent() - font.getLineMetrics(text, frc).getDescent());
    }

    public Font getFont() {
        return font;
    }

    public int getColor() {
        return color;
    }
}
