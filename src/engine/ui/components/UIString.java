package engine.ui.components;

import engine.rendering.*;
import engine.util.*;
import engine.util.vector.*;

public class UIString extends UIComponent {

    private String string;
    private Text text;

    public UIString(String string, Text text, Vector2i position) {
        super(position);
        this.string = string;
        this.text = text;
    }

    public void render(Screen screen) {
        screen.renderText(string, getScreenPos(), text.getFont(), text.getColor());
    }
}
