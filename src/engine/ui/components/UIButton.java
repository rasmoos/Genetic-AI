package engine.ui.components;

import engine.rendering.*;
import engine.rendering.sprite.*;
import engine.ui.*;
import engine.util.*;
import engine.util.Input.*;
import engine.util.vector.*;

import java.awt.*;

public class UIButton extends UIComponent {

    private UIRectangle rectangle;
    private UIString message;
    private Sprite sprite;

    private Vector2i center;

    private boolean hasText;

    private boolean hovered;

    public UIButton(String message, Text text, Vector2i position, Vector2i size, Color color) {
        super(position);
        rectangle = new UIRectangle(getPosition(), size, color);

        this.message = new UIString(message, text, position);

        int x = (size.getX() - text.getWidth(message)) / 2;
        int y = (size.getY() / 2 + text.getHeight(message) / 2);

        center = new Vector2i(x, y);

        hasText = true;
    }

    public UIButton(Vector2i position, Vector2i size, int color) {
        super(position);

        rectangle = new UIRectangle(getPosition(), size, color);

        hasText = false;
    }

    public void update() {
        Vector2i os = new Vector2i(getOffset().getX() + center.getX(), getOffset().getY() + center.getY());

        rectangle.setOffset(getOffset());
        message.setOffset(os);

        if(hovering()) {
            onHover();
            hovered = true;
        }

        if(hovering() && Mouse.leftClickOnce)
            onClick();

        if(hovered && !hovering()) {
            hovered = false;
            onNotHover();
        }
    }

    /** OVERRIDE IN OWN BUTTON CLASS**/

    public void onHover() {}
    private void onNotHover() {}
    public void onClick() {}

    protected boolean hovering() {
        return Mouse.getX() >= getScreenPos().getX() && Mouse.getY() >= getScreenPos().getY()
                && Mouse.getX() <= getScreenPos().getX() + rectangle.getSize().getX() && Mouse.getY() <= getScreenPos().getY() + rectangle.getSize().getY();
    }

    public void render(Screen screen) {
        rectangle.render(screen);

        if(sprite != null) screen.renderSprite(sprite, rectangle.getPosition());

        if(hasText)
            message.render(screen);
    }

    // BE CAREFUL
    public boolean isClicked() {
        return hovering() && Mouse.leftClickOnce;
    }

    public UIButton setSprite(Sprite sprite) {
        this.sprite = sprite;
        return this;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
