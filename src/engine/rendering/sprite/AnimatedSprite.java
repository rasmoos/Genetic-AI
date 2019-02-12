package engine.rendering.sprite;

import engine.rendering.*;
import engine.util.*;
import engine.util.vector.*;

public class AnimatedSprite extends Sprite {

    private Sprite[] sprites;
    private int frame;
    private int numberOfSprites;
    private float speed;

    public AnimatedSprite(String... paths) {
        super(paths[0]);
        this.numberOfSprites = paths.length;

        sprites = new Sprite[numberOfSprites];

        for(int i = 0; i < numberOfSprites; i++) {
            sprites[i] = new Sprite(paths[i]);
        }
    }

    public AnimatedSprite(Vector4f hitBox, String... paths) {
        super(paths[0], hitBox);
        this.numberOfSprites = paths.length;

        sprites = new Sprite[numberOfSprites];

        for(int i = 0; i < numberOfSprites; i++) {
            sprites[i] = new Sprite(paths[i], hitBox);
        }
    }

    public void update() {
        if(Time.passedTime(speed)) {
            frame++;
            if(frame >= numberOfSprites) frame = 0;
        }
    }

    public void render(Screen screen, Vector2f position) {
        screen.renderSprite(sprites[frame], position);
    }

    public void resetFrame() {
        frame = 0;
    }

    public AnimatedSprite setSpeed(float speed) {
        this.speed = speed;
        return this;
    }
}
