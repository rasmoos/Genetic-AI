package engine.ui;

import engine.rendering.*;
import engine.ui.components.*;

import java.util.*;

public class UIHandler {

    public static boolean mouseOnUI;

    private List<UIComponent> components = new ArrayList<>();

    public UIHandler() {

    }

    public void update() {
        for(UIComponent c : components)
            c.update();
    }

    public void renderToScreen(Screen screen) {
        for(UIComponent c : components)
            c.render(screen);
    }

    public void addComponent(UIComponent component) {
        components.add(component);
    }

    public void removeCommponent(UIComponent component) {
        components.remove(component);
    }

    public static boolean isMouseOnUI() {
        return mouseOnUI;
    }
}
