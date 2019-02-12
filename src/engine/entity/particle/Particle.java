package engine.entity.particle;

import engine.entity.*;
import engine.rendering.sprite.*;
import engine.util.vector.*;

public class Particle extends Entity {

    private float life;
    private float speed;

    private Vector2f velocity;

    public Particle(Sprite sprite, Vector2f position, float life, float speed) {
        super(sprite, position);
        this.life = life;
        this.speed = speed;
        velocity = new Vector2f();
    }

    public void move() {
        Vector2f.add(getPosition(), velocity, getPosition());
    }

    public float getLife() {
        return life;
    }

    public float getSpeed() {
        return speed;
    }

    public Vector2f getVelocity() {
        return velocity;
    }
}
