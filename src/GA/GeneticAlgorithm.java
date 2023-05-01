package GA;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class GeneticAlgorithm {


    public static void shuffle(int[] array){
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = rand.nextInt(array.length);
            int temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
    }
    public static ArrayList<Individual> initPopulation(int n,int populationSize){

        ArrayList<Individual>population = new ArrayList<>();
        for(int i=0;i<populationSize;i++){
            //Just a guy
            Individual guy = new Individual();
            int[] chromosome = new int[n];
            for(int j=0;j<n;j++){
                chromosome[j] = j;
            }
            shuffle(chromosome);
            guy.setChromosome(chromosome);
            guy.updateFitnessScore();
            population.add(guy);

        }

        return population;

    }

    //Elite Selection
    public static ArrayList<Individual> eliteSelection(ArrayList<Individual> population, int sampleSize){
        population.sort(new IndividualComparator());
        return new ArrayList<>(population.subList(0, sampleSize));
    }

    //Tournament Selection : this implementation can only go to sampleSize/2 and not any further
    //Keep that in mind when running tests
    public static ArrayList<Individual> tournamentSelection(ArrayList<Individual> population, int sampleSize){
        Collections.shuffle(population);
        ArrayList<ArrayList<Individual>> teams = new ArrayList<>();
        ArrayList<Individual> selectedPopulation = new ArrayList<>();
        int chunkSize = (int) Math.ceil(population.size() / (double) sampleSize);

        for (int i = 0; i < population.size(); i += chunkSize) {
            int endIndex = Math.min(population.size(), i + chunkSize);
            teams.add(new ArrayList<>(population.subList(i, endIndex)));
        }

        for(ArrayList<Individual> team : teams){
            team.sort(new IndividualComparator());
            selectedPopulation.add(team.get(0));
        }

        return selectedPopulation;
    }

    //Roulette Selection
    public static ArrayList<Individual> rouletteSelection(ArrayList<Individual> population, int sampleSize) {

        // Initialize variables to calculate adjusted fitness scores and total fitness
        double totalFitness = 0.0;
        double maxScore = Double.MIN_VALUE;

        // Calculate adjusted fitness scores and total fitness
        for (Individual i : population) {
            double adjustedScore = maxScore - i.getFitnessScore(); // Adjust score to make lower scores have better chance
            totalFitness += adjustedScore;
            if (i.getFitnessScore() > maxScore) {
                maxScore = i.getFitnessScore();
            }
        }

        // Initialize selected population array
        ArrayList<Individual> selectedPopulation = new ArrayList<>();

        // Select individuals until desired sample size is reached
        while (selectedPopulation.size() < sampleSize) {
            // Generate random number within the range of total fitness
            double r = Math.random() * totalFitness;
            double sum = 0.0;
            // Iterate through population and select individuals based on adjusted scores and random number
            for (Individual i : population) {
                double adjustedScore = maxScore - i.getFitnessScore(); // Adjust score to make lower scores have better chance
                sum += adjustedScore;
                if (sum > r) {
                    // Check if selected individual has already been selected
                    if (!selectedPopulation.contains(i)) {
                        // Add individual to selected population if not already selected
                        selectedPopulation.add(i);
                        break;
                    }
                    else {
                        // Subtract adjusted score if individual has already been selected
                        sum -= adjustedScore;
                    }
                }
            }
        }

        // Return selected population
        return selectedPopulation;
    }

    //MonoPoint CrossOver
    public static ArrayList<Individual> monoPointCrossover(ArrayList<Individual> selectedPopulation){
        ArrayList<Individual> children = new ArrayList<>();

        // Loop through each pair of individuals in the selected population
        for(int i=0; i<selectedPopulation.size(); i++) {
            for(int j=i+1; j<selectedPopulation.size(); j++) {

                // Create two new individuals as children
                Individual child1 = new Individual();
                Individual child2 = new Individual();

                // Generate a random crossover point
                int crossoverPoint = (int) (Math.random() * selectedPopulation.get(0).chromosome.length);

                // Get the parent chromosomes for crossover
                int[] parent1Chromosome = selectedPopulation.get(i).getChromosome();
                int[] parent2Chromosome = selectedPopulation.get(j).getChromosome();

                // Create child chromosomes by crossing over parent chromosomes at the randomly generated point
                int[] child1Chromosome = new int[selectedPopulation.get(0).chromosome.length];
                int[] child2Chromosome = new int[selectedPopulation.get(0).chromosome.length];
                for(int k=0; k<selectedPopulation.get(0).chromosome.length; k++) {
                    if(k < crossoverPoint) {
                        child1Chromosome[k] = parent1Chromosome[k];
                        child2Chromosome[k] = parent2Chromosome[k];
                    } else {
                        child1Chromosome[k] = parent2Chromosome[k];
                        child2Chromosome[k] = parent1Chromosome[k];
                    }
                }

                // Set the child chromosomes and their fitness scores
                child1.setChromosome(child1Chromosome);
                child1.setFitnessScore(child1.calculateFitnessScore());
                child2.setChromosome(child2Chromosome);
                child2.setFitnessScore(child2.calculateFitnessScore());

                // Add the children to the list of new individuals
                children.add(child1);
                children.add(child2);
            }
        }

        // Return the list of new individuals
        return children;
    }


    //MultiPoint CrossOver, takes how many crossoverPoints need to be created as an argument
    public static ArrayList<Individual> multiPointCrossover(ArrayList<Individual> selectedPopulation, int numberOfCrossoverPoints) {
        ArrayList<Individual> children = new ArrayList<>();

        // Loop through each pair of individuals in the selected population
        for (int i = 0; i < 1; i++) {
            for (int j = i + 1; j < selectedPopulation.size(); j++) {

                // Create two new individuals as children
                Individual child1 = new Individual();
                Individual child2 = new Individual();
                // Generate crossover points
                int[] crossoverPoints = new int[numberOfCrossoverPoints];
                for (int k = 0; k < numberOfCrossoverPoints; k++) {
                    crossoverPoints[k] = (int) (Math.random() * selectedPopulation.get(0).chromosome.length);
                }
                Arrays.sort(crossoverPoints);

                // Get the parent chromosomes for crossover
                int[] parent1Chromosome = selectedPopulation.get(i).getChromosome();
                int[] parent2Chromosome = selectedPopulation.get(j).getChromosome();

                // Create child chromosomes by crossing over parent chromosomes at the randomly generated points
                int[] child1Chromosome = new int[selectedPopulation.get(0).chromosome.length];
                int[] child2Chromosome = new int[selectedPopulation.get(0).chromosome.length];
                int currentParent = 1;
                int currentCrossoverPoint = 0;
                for (int k = 0; k < selectedPopulation.get(0).chromosome.length; k++) {
                    if (currentParent == 1) {
                        child1Chromosome[k] = parent1Chromosome[k];
                        child2Chromosome[k] = parent2Chromosome[k];
                    } else {
                        child1Chromosome[k] = parent2Chromosome[k];
                        child2Chromosome[k] = parent1Chromosome[k];
                    }
                    if (currentCrossoverPoint < numberOfCrossoverPoints && k == crossoverPoints[currentCrossoverPoint]) {
                        currentParent = 1 - currentParent;
                        currentCrossoverPoint++;
                    }
                }

                // Set the child chromosomes and their fitness scores
                child1.setChromosome(child1Chromosome);
                child1.setFitnessScore(child1.calculateFitnessScore());
                child2.setChromosome(child2Chromosome);
                child2.setFitnessScore(child2.calculateFitnessScore());

                // Add the children to the list of new individuals
                children.add(child1);
                children.add(child2);
            }
        }

        // Return the list of new individuals
        return children;
    }

    //Mutation with rate
    public static void mutation(ArrayList<Individual> children, double mutationRate){
        Random random = new Random();
        int gene1;
        int gene2;
        int temp;

        for (Individual individual : children){
            for(int i=0; i<individual.getChromosome().length; i++){
                //The mutation process consists of swapping two genes from the same chromosome
                if(random.nextDouble() < mutationRate){
                    individual.counter++;


//                    individual.setMutation(individual.getMutation()+1);
                    gene1 = random.nextInt(individual.getChromosome().length);
                    gene2 = random.nextInt(individual.getChromosome().length);
                    temp = individual.getChromosome()[gene1];
                    individual.getChromosome()[gene1] = individual.getChromosome()[gene2];
                    individual.getChromosome()[gene2] = temp;
                    individual.updateFitnessScore();

                }
            }
        }

    }

    //rebuilds population including the best individuals
    public static ArrayList<Individual> replaceElite(ArrayList<Individual>innitPopulation, ArrayList<Individual>children){
        ArrayList<Individual> newPopulation = new ArrayList<>(innitPopulation);
        int populationSize = innitPopulation.size();
        newPopulation.addAll(children);
        newPopulation.sort(new IndividualComparator());

        if (newPopulation.size()>populationSize){
            newPopulation.subList(populationSize,newPopulation.size()).clear();
        }

        return newPopulation;
    }

    //rebuilds population Randomly
    public static ArrayList<Individual> replaceRandom(ArrayList<Individual>innitPopulation, ArrayList<Individual>children){
        ArrayList<Individual> newPopulation = new ArrayList<>(innitPopulation);
        int populationSize = innitPopulation.size();
        newPopulation.addAll(children);
        Collections.shuffle(newPopulation);

        if (newPopulation.size()>populationSize){
            newPopulation.subList(populationSize,newPopulation.size()).clear();
        }

        return newPopulation;
    }


    //USAGE GUIDE :
    //Selection : 1 = elite, 2 = tournament, 3 = roulette
    //Crossover : 1 = mono-point, 2 = multi-point
    //Replacement : 1 = elite, 2 = random
    public static Individual search(int n,int populationSize,int selectionType, int numberOfParents, int crossOverType,int crossOverPoints,
                                    double mutationRate, int replaceType,int maxGeneration){

        ArrayList<Individual> population = initPopulation(n,populationSize);
        population.sort(new IndividualComparator());
        ArrayList<Individual> selected;
        ArrayList<Individual> children;
        ArrayList<Individual> newPopulation;
        Individual bestIndividual= new Individual();
        bestIndividual.setFitnessScore(9999999);
        int generationCounter = 0;

        while(generationCounter<maxGeneration && bestIndividual.getFitnessScore()!=0){
            if(selectionType==1){
                selected = eliteSelection(population,numberOfParents);
            }else if(selectionType==2){
                selected = tournamentSelection(population,numberOfParents);
            }else if(selectionType==3) {
                selected = rouletteSelection(population,numberOfParents);
            }else{
                System.out.println("Invalid argument for selection type !");
                return null;
            }

            if(crossOverType==1){
                children = monoPointCrossover(selected);
            }else if(crossOverType==2){
                children = multiPointCrossover(selected,crossOverPoints);
            }else {
                System.out.println("Invalid argument for crossover type !");
                return null;
            }

            mutation(children,mutationRate);

            if(replaceType==1){
                newPopulation = replaceElite(population,children);
            }else if(replaceType==2){
                newPopulation = replaceRandom(population,children);
            }else{
                System.out.println("Invalid argument for replacement type !");
                return null;
            }

            population=newPopulation;

            population.sort(new IndividualComparator());

            if(population.get(0).getFitnessScore()<bestIndividual.getFitnessScore()){
                bestIndividual.age = generationCounter;
                bestIndividual = population.get(0);
            }
          //  System.out.println(Arrays.toString(population.get(0).getChromosome()) + "Score : "+ population.get(0).getFitnessScore());
            //System.out.println("Generation number " + generationCounter);
            generationCounter++;
        }

       // System.out.println("Generation Number "+ generationCounter);
        return bestIndividual;

    }


    public static void tuning(int n, int maxGenerations,String bestSolutions,String allSolutions) throws IOException {
        int[] bestParam;
        int[] params;
        int minGen=99999;
        double bestRate=0;
        double successRate=0;

        FileWriter writer1 = new FileWriter(bestSolutions);
//        FileWriter writer2 = new FileWriter(allSolutions);
        BufferedWriter bestSolWriter = new BufferedWriter(writer1);
//        BufferedWriter allSolWriter = new BufferedWriter(writer2);

//        allSolWriter.write("n,Population Size,Crossover Type,Selection Type,Replacement Type,Mutation Rate,Number of Parents,Crossover Points,Success Rate\n");
        bestSolWriter.write("n,Population Size,Crossover Type,Selection Type,Replacement Type,Mutation Rate,Number of Parents,Crossover Points,Success Rate\n");
        bestSolWriter.flush();
//        allSolWriter.flush();

        Individual guy;
        for(int populationSize=4;populationSize<=n-2;populationSize++){
            System.out.println("population size is "+populationSize);
            for(int crossoverType=1;crossoverType<=2;crossoverType++){
                for(int selectionType=1;selectionType<=3;selectionType++){
                    for(int replacementType=1;replacementType<=2;replacementType++){
                        for(double mutationRate=0;mutationRate<=1;mutationRate=mutationRate+0.1){
                            for (int numberOfParents=2;numberOfParents<populationSize;numberOfParents++){
                                if(crossoverType==1){
                                    params= new int[]{populationSize,crossoverType,selectionType,replacementType, (int) (mutationRate*100),numberOfParents};
                                    for (int i=0;i<50;i++){
                                        guy = search(n,populationSize,selectionType,numberOfParents,crossoverType,1,mutationRate,replacementType,maxGenerations);
                                        if(guy.getFitnessScore()==0){
                                            successRate++;
                                        }

                                    }
                                    if(bestRate<successRate){
                                        bestParam=params;
                                        bestRate=successRate;
                                        bestSolWriter.write(n+","+populationSize+","+crossoverType+","+selectionType+","+replacementType+","+mutationRate+","
                                                +numberOfParents+","+"1"+","+(successRate/50)*100+"\n");
                                        bestSolWriter.flush();

                                    }
//                                    allSolWriter.write(n+","+populationSize+","+crossoverType+","+selectionType+","+replacementType+","+mutationRate+","
//                                            +numberOfParents+","+"1"+","+(successRate/50)*100+"\n");
//                                    allSolWriter.flush();
                                    successRate=0;
                                }else{
                                    for (int crossOverPoints=1;crossOverPoints<n;crossOverPoints++){
                                        params= new int[]{populationSize,crossoverType,selectionType,replacementType, (int) (mutationRate*100),numberOfParents,crossOverPoints};

                                        for(int i=0;i<50;i++){
                                            guy = search(n,populationSize,selectionType,numberOfParents,crossoverType,crossOverPoints,mutationRate,replacementType,maxGenerations);
                                            if(guy.getFitnessScore()==0){
                                                successRate++;
                                            }
                                            if(bestRate<successRate){
                                                bestParam=params;
                                                bestRate=successRate;
                                                bestSolWriter.write(n+","+populationSize+","+crossoverType+","+selectionType+","+replacementType+","+mutationRate+","
                                                        +numberOfParents+","+"1"+","+(successRate/50)*100+"\n");
                                                bestSolWriter.flush();


                                            }
//                                            allSolWriter.write(n+","+populationSize+","+crossoverType+","+selectionType+","+replacementType+","+mutationRate+","
//                                                    +numberOfParents+","+"1"+","+(successRate/50)*100+"\n");
//                                            allSolWriter.flush();
                                            successRate=0;

                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
//        allSolWriter.close();
        bestSolWriter.close();

    }


}
