

package AI;

import engine.util.vector.Vector2f;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Brain {
    private static final Random random = new Random();
    public Vector2f[] directions;
    public int step = 0;

    public Brain(int size) {
        this.directions = new Vector2f[size];
        this.randomize();
    }

    public Brain(String fileName) {
        String current = null;
        List<String> data = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);

            while((current = reader.readLine()) != null) {
                data.add(current);
            }

        }  catch(IOException e) {
            e.printStackTrace();
        }

        directions = new Vector2f[data.size()];

        for(int i = 0; i < data.size(); i++) {
            String vector = data.get(i);
            vector = vector.replace("Vector2f", "");
            vector = vector.replace("[", "");
            vector = vector.replace("]", "");

            String[] composites = vector.split(",");

            float x = Float.parseFloat(composites[0]);
            float y = Float.parseFloat(composites[1]);

            directions[i] = new Vector2f(x, y);
        }
    }

    private void randomize() {
        for(int i = 0; i < this.directions.length; ++i) {
            double angle = Math.toRadians((double)random.nextInt(360));
            float ax = (float)Math.cos(angle);
            float ay = (float)Math.sin(angle);
            this.directions[i] = new Vector2f(ax, ay);
        }

    }

    public Brain clone() {
        Brain clone = new Brain(this.directions.length);

        for(int i = 0; i < this.directions.length; ++i) {
            clone.directions[i] = this.directions[i];
        }

        return clone;
    }

    public void mutate() {
        float mutationRate = Settings.MUTATION_RATE;

        for(int i = 0; i < this.directions.length; ++i) {
            float rand = random.nextFloat();
            if (rand < mutationRate) {
                double angle = Math.toRadians((double)random.nextInt(360));
                float ax = (float)Math.cos(angle);
                float ay = (float)Math.sin(angle);
                this.directions[i] = new Vector2f(ax, ay);
            }
        }
    }

    public void printBrain() {
        for(int i = 0; i < directions.length - 1; i++) {
            System.out.println(directions[i]);
        }
    }
}
