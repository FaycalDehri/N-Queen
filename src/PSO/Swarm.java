package PSO;

import java.util.ArrayList;

public class Swarm {

    ArrayList<Particle> population;
    int[] gBest;


    public ArrayList<Particle> getPopulation() {
        return population;
    }

    public int[] getgBest() {
        return gBest;
    }

    public void setgBest(int[] gBest) {

            this.gBest = gBest;

    }

    public void setPopulation(ArrayList<Particle> population) {
        this.population = population;
    }

}
