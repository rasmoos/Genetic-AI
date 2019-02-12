package engine.ui;

import engine.rendering.*;
import engine.ui.components.*;
import engine.util.Input.*;
import engine.util.vector.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public class UIPanel extends UIComponent {

    private List<UIComponent> components = new ArrayList<>();

    private Vector2i size;
    private Color color;

    private boolean visible;
    private boolean background;

    public UIPanel(Vector2i position, Vector2i size) {
        super(position);
        this.size = size;
    }

    public void update() {
        if(!visible) return;

        if(hovering()) UIHandler.mouseOnUI = true;
        else UIHandler.mouseOnUI = false;

        for(UIComponent c : components) {
            c.update();
        }
    }

    public void render(Screen screen) {
        if(!visible) return;

        if(background)
            screen.renderSquare(getPosition(), size, color);

        for(UIComponent c : components)
            c.render(screen);
    }

    private boolean hovering() {
        return Mouse.getX() >= getScreenPos().getX() && Mouse.getY() >= getScreenPos().getY()
                && Mouse.getX() <= getScreenPos().getX() + size.getX() && Mouse.getY() <= getScreenPos().getY() + size.getY();
    }

    public UIPanel background(Color color) {
        this.color = color;
        background = true;

        return this;
    }

    public UIPanel setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public void addComponent(UIComponent component) {
        components.add(component);
        component.setOffset(getPosition());
        component.init();
    }

    public void renmoveComponent(UIComponent component) {
        components.remove(component);
    }

    public void toggleVisibility() {
        visible = !visible;
    }

    public boolean isVisible() {
        return visible;
    }
}
