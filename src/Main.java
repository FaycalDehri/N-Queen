import java.io.IOException;
import java.io.ObjectStreamClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

import GA.GeneticAlgorithm;
import GA.Individual;
import GA.IndividualComparator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

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
    public static void main(String[] args) {


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

        Individual solution = new Individual();
        solution = GeneticAlgorithm.search(10,40,2,6,2,4,0.8,2,5000);

        System.out.println("Best solution GA found "+ Arrays.toString(solution.getChromosome())+" Score : "+solution.getFitnessScore());

        visualizeBoard(solution.getChromosome());
    }

}


