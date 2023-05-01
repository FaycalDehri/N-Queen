package PSO;

import java.util.ArrayList;
import java.util.Arrays;

public class Swarm {

    ArrayList<Particle> population;
    int[] gBest;

    int[] truegBest;


    public int[] getTruegBest() {
        return truegBest;
    }

    public void setTruegBest(int[] truegBest) {
        this.truegBest = truegBest;
    }

    public ArrayList<Particle> getPopulation() {
        return population;
    }

    public int[] getgBest() {
        return gBest;
    }

    public int getgBestScore() {
        return this.calculategBestFitness();
    }

    public int calculategBestFitness(){
        int n = this.gBest.length;
        int conflicts = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (this.gBest[i] == this.gBest[j]) {
                    // same row
                    conflicts++;
                }
                if (Math.abs(i - j) == Math.abs(this.gBest[i] - this.gBest[j])) {
                    // same diagonal
                    conflicts++;
                }
            }
        }

        return conflicts;
    }
    public static void copyArray(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = arr1[i];
        }
    }

    public void setgBest(Particle position) {

        if(position.score < this. calculategBestFitness()){

            copyArray(position.state, this.gBest);


        }

//        System.out.println("after  setgBest gbest state "+ Arrays.toString(gBest.getState()));
    }

    public void setPopulation(ArrayList<Particle> population) {
        this.population = population;
    }

}