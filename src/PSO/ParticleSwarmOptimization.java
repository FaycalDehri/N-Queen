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



    public static int evaluateState(int[] state){
        int n = state.length;
        int conflicts = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (state[i] == state[j]) {
                    // same row
                    conflicts++;
                }
                if (Math.abs(i - j) == Math.abs(state[i] - state[j])) {
                    // same diagonal
                    conflicts++;
                }
            }
        }

        return conflicts;
    }

    //This is some stinky code, it works tho don't touch.
    //it initializes the swarm with random values and sets the Gbest Values
    public static Swarm initSwarm(int swarmSize,int n){

        ArrayList<Particle> tempSwarm = new ArrayList<>();
        Swarm initialSwarm = new Swarm();
        int[] tempGbestState = new int[n];
        int tempGbestScore = Integer.MAX_VALUE;

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
            tempParticle.setpBest(temp);
            tempSwarm.add(tempParticle);
        }

        initialSwarm.setPopulation(tempSwarm);

        for(Particle guy : tempSwarm){
            if(guy.getScore()<tempGbestScore){
               tempGbestScore = guy.getScore();
               tempGbestState = guy.getState();
            }
        }

        initialSwarm.setgBest(tempGbestState);

        return initialSwarm;

    }

    //C1 is the prob of being influenced by the global best
    //C2 is the prob of being influenced by the personal best
    //rand<C1 :
    public static void cross(Swarm swarm, double C1, double C2){
        Random random = new Random();
        ArrayList<Particle> tempPopulation = new ArrayList<>();


        for (Particle guy : swarm.population){
            Particle tempParticle = new Particle();
            tempParticle.setState(new int[swarm.population.get(0).state.length]);
            //we set the pBest as the current pBest to not lose it
            tempParticle.setpBest(guy.pBest);

            double rand = random.nextDouble();
            if(rand < C1){

                System.arraycopy(guy.state,0,tempParticle.state,0,guy.state.length/2);
                System.arraycopy(swarm.gBest,0,tempParticle.state,guy.state.length/2,guy.state.length/2);

                tempParticle.setScore();

                //Updates the pBest values
                if (tempParticle.getScore() < evaluateState(guy.pBest)) {
                    tempParticle.setpBest(guy.getState());
                }
                //Updates the Gbest Values
                if(tempParticle.getScore()<evaluateState(swarm.gBest)){
                    swarm.setgBest(tempParticle.getState());
                }

            }else if(rand >C1 && rand <C2){
                System.arraycopy(guy.pBest,0,tempParticle.state,guy.state.length/2,guy.state.length/2);
                System.arraycopy(guy.state,0,tempParticle.state,0,guy.state.length/2);

                tempParticle.setScore();

                //Updates the pBest values
                if (tempParticle.getScore() < evaluateState(guy.pBest)) {
                    guy.setpBest(guy.getState());
                }
                //Updates the gBest Values
                if(tempParticle.getScore()<evaluateState(swarm.gBest)){
                    swarm.setgBest(tempParticle.getState());
                }

            }else{
                int[] tempRandomValues = new int[guy.state.length];

                for(int i=0;i<guy.state.length;i++){
                    tempRandomValues[i]=i;
                }
                shuffle(tempRandomValues);

                System.arraycopy(tempRandomValues,0,tempParticle.state,guy.state.length/2,guy.state.length/2);
                System.arraycopy(guy.state,0,tempParticle.state,0,guy.state.length/2);

                tempParticle.setScore();

                //Updates the pBest values
                if (tempParticle.getScore() < evaluateState(guy.pBest)) {
                    guy.setpBest(guy.getState());
                }
                //Updates the gBest Values
                if(tempParticle.getScore()<evaluateState(swarm.gBest)){
                    swarm.setgBest(tempParticle.getState());
                }
            }
            tempPopulation.add(tempParticle);

        }


        swarm.setPopulation(tempPopulation);

    }

    public static Particle search(int n,int nbrIterations,int swarmSize, double C1,double C2){
        Swarm swarm = new Swarm();
        int iterationCounter = 0;
        swarm= initSwarm(swarmSize,n);
        swarm.population.sort(new ParticleComparator());
        Particle bestParticle = new Particle();
        bestParticle.setState(swarm.population.get(0).getState());
        bestParticle.setScore();
        System.out.println("score is : "+bestParticle.score);

        while(iterationCounter<nbrIterations && bestParticle.score !=0){
            cross(swarm,C1,C2);
            swarm.population.sort(new ParticleComparator());
            System.out.println("Iteration number "+iterationCounter);
            iterationCounter++;

            if(bestParticle.score<swarm.population.get(0).score){
                bestParticle = swarm.population.get(0);
            }
        }

        return bestParticle;
    }

}
