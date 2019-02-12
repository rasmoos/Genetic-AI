package AI;

import engine.core.Main;
import engine.core.Renderer;
import engine.rendering.Camera;
import engine.rendering.Screen;
import engine.util.Input.Keyboard;
import engine.util.vector.Vector2f;
import engine.util.vector.Vector2i;
import java.awt.Color;
import java.awt.Font;

public class Ai extends Renderer {
    public static final int WIDTH = 1440;
    public static final int HEIGHT = 800;
    private static Goal goal;
    private GameCamera camera;
    private Population population;

    public static boolean renderAll = true;

    public void init() {
        this.camera = new GameCamera();

        if(Settings.BRAIN_FROM_FILE) {
            population = new Population(1);

            population.dotFromFile("res/brain.txt");

        } else {
            this.population = new Population(Settings.POPULATION_SIZE);
        }

        goal = new Goal(new Vector2f(Settings.GOAL.x, Settings.GOAL.y));
    }

    public void update() {
        this.camera.update();

        if(Keyboard.space_once)
            renderAll = !renderAll;

        if (Keyboard.one) {
            Main.setSpeed(1.0F);
        }

        if (Keyboard.two) {
            Main.setSpeed(2.0F);
        }

        if (Keyboard.three) {
            Main.setSpeed(3.0F);
        }

        if (Keyboard.four) {
            Main.setSpeed(4.0F);
        }

        if (Keyboard.five) {
            Main.setSpeed(5.0F);
        }

        if (Keyboard.six) {
            Main.setSpeed(6.0F);
        }

        if (Keyboard.seven) {
            Main.setSpeed(7.0F);
        }

        if (Keyboard.eight) {
            Main.setSpeed(8.0F);
        }

        if (Keyboard.nine) {
            Main.setSpeed(20);
        }

        if(Keyboard.esc_once) {
            population.getBestDot().getBrain().printBrain();
            System.exit(0);
        }

        if (this.population.allDotsDead()) {
            this.population.calculateFitness();
            this.population.naturalSelection();
            this.population.mutate();
        } else {
            this.population.update();
        }

    }

    public void renderToWorld(Screen screen) {
        screen.renderSquare(new Vector2i(-720, -400), new Vector2i(1440, 800), new Color(239, 239, 239));
        goal.render(screen);
        if (!this.population.allDotsDead()) {
            this.population.render(screen);
        }

    }

    public void renderToScreen(Screen screen) {
        screen.renderText(String.valueOf(this.population.getGeneration()), new Vector2i(20, 35), new Font("Airal", 1, 32), 0);
    }

    public Camera getCamera() {
        return this.camera;
    }

    public static Goal getGoal() {
        return goal;
    }
}
