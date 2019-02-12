package AI;

import engine.core.*;

import java.util.Random;

public class Start {

    static Random random = new Random();

    public static void main(String[] args) {
        Main.createRenderer(new Ai(), Ai.WIDTH, Ai.HEIGHT, "AI");
    }

}
