package AI;

import engine.rendering.Screen;
import engine.util.vector.Vector2f;
import engine.util.vector.Vector2i;
import engine.util.vector.Vector3f;

import java.awt.*;

public class Obstacle {

    private Vector2f position;
    private Vector2i size;

    private Color color;

    public Obstacle(Vector2f position, Vector2i size) {
        this.position = position;
        this.size = size;
        color = new Color(34, 33, 33);
    }

    public void render(Screen screen) {
        screen.renderSquare(position.toVec2i(), size, color);
    }

    public boolean collision(Vector2f pos) {

        return pos.getX() >= position.getX() - Dot.SIZE.getX() && pos.getX() <= position.getX() + size.getX() && pos.getY() + Dot.SIZE.getY() >= position.getY() && pos.getY() <= position.getY() + size.getY();
    }

    public Vector2f getPosition() {
        return position;
    }

    public Vector2i getSize() {
        return size;
    }

    public Obstacle setColor(Color color) {
        this.color = color;
        return this;
    }
}
