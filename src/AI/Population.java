package AI;

import engine.rendering.Screen;
import engine.util.vector.Vector2f;
import engine.util.vector.Vector2i;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population {

    private final Random random = new Random();

    private Dot[] dots;
    private List<Obstacle> obstacles = new ArrayList<>();
    private float fitnessSum;

    private int bestDot;
    private int minStep = Dot.MAX_STEPS;

    private int generation = 1;

    public Population(int size) {
        dots = new Dot[size];

        for(int i = 0; i < size; i++) {
            dots[i] = new Dot(this);
        }

        obstacles.add(new Obstacle(new Vector2f(-360, -200), new Vector2i(5, 400)));
        obstacles.add(new Obstacle(new Vector2f(360, -200), new Vector2i(5, 400)));
        obstacles.add(new Obstacle(new Vector2f(-360, 0), new Vector2i(720, 5)));

        obstacles.add(new Obstacle(new Vector2f(0, -400), new Vector2i(5, 300)));
        obstacles.add(new Obstacle(new Vector2f(0, 100), new Vector2i(5, 300)));

    }

    public void update() {
        for(int i = 0; i < dots.length; i++) {
            if(dots[i].getBrain().step > minStep)
                dots[i].kill();
            else
                dots[i].update();
        }
    }

    public void render(Screen screen) {
        for(Obstacle ob : obstacles)
            ob.render(screen);

        if(Ai.renderAll) {
            for(int i = 1; i < dots.length; i++)
                dots[i].render(screen);
        }
        dots[0].render(screen);
    }

    public void calculateFitness() {
        for(Dot dot : dots) {
            dot.calculateFitness();
        }
    }

    public boolean allDotsDead() {
        for(int i = 0; i < dots.length; i++) {
            if(!dots[i].isDead() && !dots[i].hasReachedGoal()) return false;
        }

        return true;
    }

    public void naturalSelection() {
        Dot[] newDots = new Dot[dots.length];
        selectBestDot();
        calculateFitnessSum();

        newDots[0] = dots[bestDot].getBaby();
        newDots[0].isBest();
        for(int i = 1; i < newDots.length; i++) {
            Dot parent = selectParent();
            Dot partner = selectParent();

            if(parent == null) parent = dots[bestDot];
            if(partner == null) partner = dots[bestDot];

            newDots[i] = parent.getBaby(partner);
        }

        dots = newDots.clone();
        generation++;
    }

    public void mutate() {
        for(int i = 1; i < dots.length; i++) {
            dots[i].getBrain().mutate();
        }
    }

    private void calculateFitnessSum() {
        fitnessSum = 0;

        for(int i = 0; i < dots.length; i++)
            fitnessSum += dots[i].getFitness();

//        System.out.println("SUM");
//        System.out.println(fitnessSum);
//        System.out.println("///////");
    }

    private Dot selectParent() {
        float rand = random.nextInt((int) fitnessSum);
        float runningSum = 0;

        RandomizeArray();

        for(int i = 0; i < dots.length; i++) {
            runningSum += dots[i].getFitness();

            if(runningSum > rand) return dots[i];
        }

        return null;
    }

    public void RandomizeArray(){
        Random rgen = new Random();  // Random number generator

        for (int i=0; i<dots.length; i++) {
            int randomPosition = rgen.nextInt(dots.length);
            Dot temp = dots[i];
            dots[i] = dots[randomPosition];
            dots[randomPosition] = temp;
        }
    }

    private void selectBestDot() {
        float max = 0;
        int maxIndex = 0;

        for(int i = 0; i < dots.length; i++) {
            if(dots[i].getFitness() > max) {
                max = dots[i].getFitness();
                maxIndex = i;
            }
        }

        bestDot = maxIndex;

        if(dots[bestDot].hasReachedGoal())
            minStep = dots[bestDot].getBrain().step;

        System.out.println("generation: " + generation + ", step: " + minStep);
    }

    public boolean collision(Vector2f pos) {
        for(Obstacle ob : obstacles)
            if(ob.collision(pos)) return true;

        return false;
    }

    public int getGeneration() {
        return generation;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public Dot getBestDot() {
        return dots[0];
    }

    public void dotFromFile(String fileName) {
        if(dots.length > 1) return;

        dots[0] = new Dot(fileName, this);
    }
}
