package PSO;

import java.util.ArrayList;

public class Swarm {

    ArrayList<Particle> population;
    Particle gBest;


    public ArrayList<Particle> getPopulation() {
        return population;
    }

    public Particle getgBest() {
        return gBest;
    }

    public void setgBest(Particle gBest) {
        if(gBest.score<this.gBest.score){
            this.gBest = gBest;
        }
    }

    public void setPopulation(ArrayList<Particle> population) {
        this.population = population;
    }

}
