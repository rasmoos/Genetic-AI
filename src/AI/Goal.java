package AI;

import engine.rendering.Screen;
import engine.util.vector.Vector2f;
import engine.util.vector.Vector2i;

import java.awt.*;

public class Goal {
    private static final Vector2i size = new Vector2i(20, 20);

    private Vector2f position;

    public Goal(Vector2f position) {
        this.position = position;
    }

    public void render(Screen screen) {
        Vector2f rpos = new Vector2f(position.getX() - 10, position.getY() - 10);
        screen.renderCircle(rpos.toVec2i(), size, Color.RED);
    }

    public Vector2f getPosition() {
        return position;
    }
}
