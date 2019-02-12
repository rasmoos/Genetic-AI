package engine.ui.components;

import engine.rendering.*;
import engine.util.vector.*;

import java.awt.*;

public class UIRectangle extends UIComponent {

    private Color color;
    private Vector2i size;

    public UIRectangle(Vector2i position, Vector2i size, Color color) {
        super(position);
        this.size = size;
        this.color = color;
    }

    public UIRectangle(Vector2i position, Vector2i size, int color) {
        super(position);
        this.size = size;
        this.color = new Color(color);
    }

    public void render(Screen screen) {
        screen.renderSquare(getScreenPos(), size, color);
    }

    public Vector2i getSize() {
        return size;
    }
}
