package PSO;

import java.util.ArrayList;
import java.util.Random;

public class ParticleSwarmOptimization {

    public static void shuffle(int[] array){
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = rand.nextInt(array.length);
            int temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
    }

    //This is some stinky code, it works tho don't touch.
    //it initializes the swarm with random values and sets the Gbest Values
    public static Swarm initSwarm(int swarmSize,int n){

        ArrayList<Particle> tempSwarm = new ArrayList<>();
        Swarm initialSwarm = new Swarm();
        Particle tempGbest = new Particle();
        tempGbest.score = Integer.MAX_VALUE;

        for(int i=0;i<swarmSize;i++){
            int [] temp = new int[n];
            Particle tempParticle = new Particle();
            for (int j=0;j<n;j++){
                temp[j] = j;
            }
            shuffle(temp);

            //we set the info of the newly generated particle
            tempParticle.setState(temp);
            tempParticle.setScore();
            tempParticle.pBest = tempParticle;
            tempSwarm.add(tempParticle);
        }
        initialSwarm.setPopulation(tempSwarm);

        for(Particle guy : initialSwarm.population){
            if(guy.score<tempGbest.score){
               tempGbest = guy;
            }
        }

        initialSwarm.gBest = tempGbest;

        return initialSwarm;

    }
    public static Swarm crossAndMove(Swarm swarm){
        Random random = new Random();
        Swarm newSwarm = new Swarm();
        newSwarm = swarm;

        for (Particle guy : newSwarm.population){
            if(random.nextBoolean()){
                System.arraycopy(newSwarm.gBest.state,0,guy.state,guy.state.length/2,guy.state.length/2);
                guy.score = guy.calculateFitness();
                if (guy.score < guy.getpBest().score) {
                    guy.setpBest(guy);
                }
            } else {
                System.arraycopy(guy.pBest.state,guy.state.length/2,guy.state,guy.getState().length/2,guy.getState().length/2);
                guy.score = guy.calculateFitness();
                if (guy.score < guy.getpBest().score) {
                    guy.setpBest(guy);
                }
            }

            //This part updates the gBest value
            newSwarm.setgBest(guy);
        }
        return newSwarm;
    }

}
