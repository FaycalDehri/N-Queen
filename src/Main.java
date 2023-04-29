import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import PSO.Particle;
import PSO.ParticleSwarmOptimization;
import PSO.Swarm;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends javafx.application.Application  {

    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/hello-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();


    }
    public static int generatedNodes=0;
    public static int developedNodes=0;
    public static void printArray(int[] t){
        for(int i : t){
            System.out.print(i+" ");
        }
        System.out.println();
    }

    public static void visualizeBoard(int[] solution){
        StringBuilder board = new StringBuilder();

        for (int k : solution) {
            for (int j = 0; j < solution.length; j++) {
                if (j == k) {
                    board.append("Q ");
                } else {
                    board.append("- ");
                }
            }
            board.append("\n");
        }

        System.out.println(board.toString());
    }
    public static void main(String[] args) throws IOException {


//        int n = 8;
//        int[] startState = new int[n];
//        int[] solutionArr ;
//
//        Node sol = new Node(startState,0,n);
//        ConflictsHeuristic conflictsHeuristic = new ConflictsHeuristic();
//        Astar.EuclideanDistance euclideanDistance = new Astar.EuclideanDistance();
//
//
//    long startTime = System.nanoTime();
//
////        solutionArr = Blind.DFS.search(n);
////    solutionArr = Blind.BFS.search(n);
//        sol.setState(Astar.Astar.search(sol,conflictsHeuristic));
////        sol.setState(Astar.Astar.search(sol,euclideanDistance));
//
//
//
//    long endTime = System.nanoTime();
//    double durationMS=(endTime - startTime)/1000000;
//    double durationS= durationMS/1000;
//        System.out.println("n =" + n);
//    System.out.println(  durationS);
//    System.out.println(generatedNodes);
//    System.out.println( developedNodes);
//
//        System.out.println(Arrays.toString(sol.getState()));
////    printArray(solutionArr);

       // launch();
//        ArrayList<Individual> pop;
//        ArrayList<Individual> elite;
//        ArrayList<Individual> children;
//        ArrayList<Individual> newpop;
//        pop = GeneticAlgorithm.initPopulation(8,10);
//        Individual[] popArr= pop.toArray(new Individual[0]);
//
//
//       for(Individual i : popArr){
//           System.out.println(Arrays.toString(i.getChromosome()) + " Score : " +i.getFitnessScore());
//       }
//
//       elite = GeneticAlgorithm.eliteSelection(pop,4);
//        System.out.println("The elite is :");
//        elite.sort(new IndividualComparator());
//        for(Individual i : elite){
//            System.out.println(Arrays.toString(i.getChromosome()) + " Score : " +i.getFitnessScore());
//        }
//
//        children = GeneticAlgorithm.multiPointCrossover(elite,3);
//        System.out.println("The children are :");
//        for(Individual i : children){
//            System.out.println(Arrays.toString(i.getChromosome()) + " Score : " +i.getFitnessScore());
//        }
//
//        GeneticAlgorithm.mutation(children,0.7);
//        System.out.println("The mutated children are :");
//        for(Individual i : children){
//            System.out.println(Arrays.toString(i.getChromosome()) + " Score : " +i.getFitnessScore());
//        }
//
//
//        System.out.println("The new population is : ");
//        newpop = GeneticAlgorithm.replaceRandom(pop,children);
//        newpop.sort(new IndividualComparator());
//        for(Individual i : newpop){
//            System.out.println(Arrays.toString(i.getChromosome()) + " Score : " +i.getFitnessScore());
//        }



//        for(int i=0;i<1000;i++){
//            System.out.println(i);
//            solution = GeneticAlgorithm.search(12,20,3,10,2,11,0.8,2,100);
//            System.out.println("score = "+solution.getFitnessScore());
//            if(solution.getFitnessScore()==0){
//                counter++;
//            }
//        }
//        System.out.println(counter);
       //GeneticAlgorithm.tuning(1000,"C:\\Users\\Fayçal\\OneDrive\\Bureau\\SII\\S2\\META\\TP\\N-Queen\\src\\GA\\bestSol.csv","C:\\Users\\Fayçal\\OneDrive\\Bureau\\SII\\S2\\META\\TP\\N-Queen\\src\\GA\\allSol.csv");

        ArrayList<Particle> pop = new ArrayList<>();

//        pop = ParticleSwarmOptimization.genInitialPopulation(10,8);
//
//        for(Particle p : pop){
//            System.out.println(Arrays.toString(p.getState())+" Personal Best : "+p.getScore() +" Group Best : "+p.getgBest()+ " And the group best state is : "+ Arrays.toString(p.getBestGroupState()));
//        }
//
//        System.out.println("After Diversification :");
//        ParticleSwarmOptimization.diversify(pop);
//        for(Particle p : pop){
//            System.out.println(Arrays.toString(p.getState())+" Personal Best : "+p.getScore() +" Group Best : "+p.getgBest()+ " And the group best state is : "+ Arrays.toString(p.getBestGroupState()));
//        }
        Swarm swarm = new Swarm();
        swarm = ParticleSwarmOptimization.initSwarm(10,8);

       for(Particle guy : swarm.getPopulation()){
           System.out.println(Arrays.toString(guy.getState()) +" Score : "+ guy.getScore()+ " The personal Best is "+ Arrays.toString(guy.getpBest().getState()) +" Score : "+guy.getpBest().getScore());
       }
        System.out.println("the global best is : "+Arrays.toString(swarm.getgBest().getState())+ " Score : "+swarm.getgBest().getScore());

        swarm = ParticleSwarmOptimization.crossAndMove(swarm);
        System.out.println("After crossing : ");
        for(Particle guy : swarm.getPopulation()){
            System.out.println(Arrays.toString(guy.getState()) +" Score : "+ guy.getScore() +" And the personal best : "+ Arrays.toString(guy.getpBest().getState())+" Score : "+guy.getpBest().getScore());
        }
        System.out.println("the global best is : "+Arrays.toString(swarm.getgBest().getState())+ " Score : "+swarm.getgBest().getScore()+"h"+swarm.getgBest().calculateFitness());


    }



}


