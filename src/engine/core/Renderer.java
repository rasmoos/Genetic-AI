package engine.core;

import engine.rendering.*;

public abstract class Renderer {

    protected Camera camera;

    public abstract void init();
    public abstract void update();
    public abstract void renderToWorld(Screen screen);
    public abstract void renderToScreen(Screen screen);

    public abstract Camera getCamera();
}
