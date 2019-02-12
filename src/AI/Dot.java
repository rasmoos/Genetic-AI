package AI;

import engine.rendering.Screen;
import engine.rendering.sprite.Sprite;
import engine.util.vector.Vector2f;
import engine.util.vector.Vector2i;

import java.awt.*;
import java.util.Random;

public class Dot {

    private static final Sprite sprite = new Sprite("pig");
    private static final Sprite vinner = new Sprite("guld");

    public static final Vector2i SIZE = new Vector2i(5, 5);
    public static final int MAX_STEPS = 2000;

    private Vector2f position;
    private Vector2f velocity;
    private Vector2f acceleration;

    private float distanceToGoal;

    private boolean dead;
    private boolean reachedGoal;
    private boolean isBest;
    private boolean hitWall;

    private float fitness;

    private Brain brain;

    private Population pop;

    public Dot(Population pop) {
        position = new Vector2f(Settings.START.x, Settings.START.y);
        velocity = new Vector2f();
        acceleration = new Vector2f();

        brain = new Brain(MAX_STEPS);

        this.pop = pop;
    }

    public Dot(String fileName, Population pop) {
        position = new Vector2f(Settings.START.x, Settings.START.y);
        velocity = new Vector2f();
        acceleration = new Vector2f();

        brain = new Brain(fileName);
        isBest = true;

        this.pop = pop;
    }

    public void update() {
        Vector2f xColl = new Vector2f(position.getX() + velocity.x, position.getY());
        Vector2f yColl = new Vector2f(position.getX(), position.getY() + velocity.getY());

        if(pop.collision(xColl)) {
            velocity.x *= -1;
        }

        if(pop.collision(yColl))
            velocity.y *= -1;

        if(!dead && !reachedGoal)
            move();

        if(position.getY() + velocity.y < -Ai.HEIGHT / 2 || position.getY() + velocity.y > Ai.HEIGHT / 2 - 44) {
            velocity.y *= -1;
            hitWall = true;
        }

        if(position.getX() + velocity.x < -Ai.WIDTH / 2 || position.getX() + velocity.x > Ai.WIDTH / 2 - 22) {
            velocity.x *= -1;
            hitWall = true;
        }

        if(Vector2f.distance(position, Ai.getGoal().getPosition()) < 10) {
            reachedGoal = true;
            dead = true;
        }
    }

    private void move() {
        if(brain.directions.length > brain.step) {
            acceleration = brain.directions[brain.step];
            brain.step++;
        } else
            dead = true;


        Vector2f.add(position, velocity, position);
        Vector2f.add(velocity, acceleration, velocity);

        if(velocity.getX() > 5) velocity.x = 5;
        if(velocity.getX() < -5) velocity.x = -5;
        if(velocity.getY() > 5) velocity.y = 5;
        if(velocity.getY() < -5) velocity.y = -5;

    }

    public void render(Screen screen) {
        Color color = Color.BLACK;
        if(isBest)
            color = Color.RED;

        screen.renderSquare(position.toVec2i(), SIZE, color);
//
//        if(isBest) {
//            screen.renderSprite(vinner, position.toVec2i());
//        } else
//            screen.renderSprite(sprite, position.toVec2i());
    }

    public void calculateFitness() {

        if(reachedGoal) {
          fitness = 100000000000f / (brain.step);
        } else {
            distanceToGoal = Vector2f.distance(position, Ai.getGoal().getPosition());

            fitness = (float) (1000000 / (distanceToGoal * distanceToGoal));
        }
    }

    public Dot getBaby() {
        Dot baby = new Dot(pop);
        baby.setBrain(brain.clone());

        return baby;
    }

    public Dot getBaby(Dot partner) {
        Dot baby = new Dot(pop);

        for(int i = 0; i < brain.directions.length; i++) {
            if(i % 2 == 0) baby.brain.directions[i] = brain.directions[i];
            else baby.brain.directions[i] = partner.brain.directions[i];
        }

        return baby;
    }

    public boolean isDead() {
        return dead;
    }

    public boolean hasReachedGoal() {
        return reachedGoal;
    }

    public float getFitness() {
        return fitness;
    }

    public Dot setPosition(Vector2f position) {
        this.position = position;

        return this;
    }

    public Brain getBrain() {
        return brain;
    }

    public void setBrain(Brain brain) {
        this.brain = brain;
    }

    public void isBest() {
        isBest = true;
    }

    public void kill() {
        dead = true;
    }

    public Vector2f getPosition() {
        return position;
    }
}
