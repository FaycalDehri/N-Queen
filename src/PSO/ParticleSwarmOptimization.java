package PSO;

import java.util.*;

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
    public static void copyArray(int[] arr1, int[] arr2) {

        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = arr1[i];
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
//            tempParticle.setState(temp);
//            tempParticle.setScore();
//            tempParticle.initpBest();
//            tempParticle.initpBestScore();
            tempParticle.initParticle(temp);
            tempSwarm.add(tempParticle);
        }
        initialSwarm.setPopulation(tempSwarm);

        for(Particle guy : initialSwarm.population){
            if(guy.score < tempGbest.score){
                tempGbest = guy;
            }
        }
        initialSwarm.gBest = new int[tempGbest.state.length];

        copyArray(tempGbest.state, initialSwarm.gBest);


        return initialSwarm;

    }

//make crossover between two arrays
    public static void mutateArray(int[] array1, int[] array2) {

        Random random = new Random();
        Set<Integer> usedIndices = new HashSet<Integer>();
        int numCrossover = array1.length / 2; // Number of elements to crossover
        for (int i = 0; i < numCrossover; i++) {
            int index = random.nextInt(array1.length);
            while (usedIndices.contains(index)) {

                index = random.nextInt(array1.length);
            }
            array2[index] = array1[index];
            usedIndices.add(index);
        }

    }


    public static Swarm crossAndMove(int n ,double c1,double c2,int iteration ,Swarm swarm){
        Random random = new Random();
        Swarm newSwarm = new Swarm();
        newSwarm = swarm;




        for (Particle guy : newSwarm.population){

            for (int i=0; i<iteration;i++){
                double probability = random.nextDouble();
                if(probability < c1){

                   mutateArray( newSwarm.gBest, guy.state);


                    guy.score = guy.calculateFitness();

                    guy.setpBestScore(guy);


                } else  if( probability >= c1 && probability < c2) {

                   mutateArray( guy.pBest, guy.state);

                    guy.score = guy.calculateFitness();

                    guy.setpBestScore(guy);



                } else{
                    int [] temporary = new int[n];
                    for (int j=0;j<n;j++){
                        temporary[j] = j;
                    }
                    shuffle(temporary);

                    mutateArray( temporary ,guy.state);

                    guy.score = guy.calculateFitness();



                    guy.setpBestScore(guy);
                }
            }

            newSwarm.setgBest(guy);

        }
        return newSwarm;

    }

}