package engine.rendering.sprite;

import engine.util.vector.*;

import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

public class Sprite {

    private BufferedImage sprite;
    private Vector2i size;

    //First Vector Start x & y, Second Vector Width & Height
    private Vector4f hitBox;

    public Sprite(String path) {
        try {
            sprite = ImageIO.read(Sprite.class.getResourceAsStream("/textures/" + path + ".png"));

            size = new Vector2i(sprite.getWidth(), sprite.getHeight());
            hitBox = new Vector4f(0, 0, size.getX(), size.getY());
        } catch(IOException e) {
            System.err.println("[ERROR] Could not load sprite: " + path);
            e.printStackTrace();
        }
    }

    public Sprite(String path, Vector4f hitBox) {
        try {
            sprite = ImageIO.read(Sprite.class.getResourceAsStream("/textures/" + path + ".png"));
            size = new Vector2i(sprite.getWidth(), sprite.getHeight());
            this.hitBox = hitBox;
        } catch(IOException e) {
            System.err.println("[ERROR] Could not load sprite: " + path);
            e.printStackTrace();
        }
    }

    public Vector2i getSize() {
        return size;
    }

    public Vector4f getHitBox() {
        return hitBox;
    }

    public BufferedImage getSprite() {
        return sprite;
    }
}
