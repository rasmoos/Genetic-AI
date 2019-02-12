package engine.entity;

import engine.rendering.*;
import engine.rendering.sprite.*;
import engine.util.vector.*;

public class Entity {

    private Sprite sprite;
    private Vector2f position;

    public Entity(Sprite sprite, Vector2f position) {
        this.sprite = sprite;
        this.position = position;
    }

    public void update() {}

    public void render(Screen screen) {
        screen.renderSprite(sprite, position);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Vector2f getPosition() {
        return position;
    }
}
