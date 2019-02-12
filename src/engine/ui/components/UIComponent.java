package engine.ui.components;

import engine.rendering.*;
import engine.util.vector.*;

public class UIComponent {

    private Vector2i position;
    private Vector2i offset;

    public UIComponent(Vector2i position) {
        this.position = position;
        offset = new Vector2i();
    }

    public void init() {

    }

    public void update() {

    }

    public void render(Screen screen) {

    }

    public void setOffset(Vector2i offset) {
        this.offset = offset;
    }

    public Vector2i getPosition() {
        return position;
    }

    public Vector2i getOffset() {
        return offset;
    }

    public Vector2i getScreenPos() {
        return Vector2i.add(position, offset, null);
    }
}
