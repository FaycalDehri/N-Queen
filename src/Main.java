import java.util.ArrayList;
import java.util.Arrays;



public class Main {

    public static int generatedNodes=0;
    public static int developedNodes=0;
    public static void printArray(int[] t){
        for(int i : t){
            System.out.print(i+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int n = 10;
        int[] startState = new int[n];

        Node sol = new Node(startState,0,n);
        ConflictsHeuristic h = new ConflictsHeuristic();



        long startTime = System.nanoTime();

//        sol.setState(DFS.search(n));

        sol.setState(Astar.search(sol,h));
        //[1, 9, 2, 6, 8, 3, 0, 4, 7, 5]


        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;


        System.out.println("Excution time : " + duration +" ms");
        System.out.println("Generated nodes : " + generatedNodes +" Developped nodes : " + developedNodes);
        System.out.println(Arrays.toString(sol.getState()));


//        if (sol.getState() == null) {
//            System.out.println("No solution found.");
//        } else {
//            System.out.println("Solution found:");
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    if (sol.getState()[i] == j) {
//                        System.out.print("Q ");
//                    } else {
//                        System.out.print("* ");
//                    }
//                }
//                System.out.println();
//            }
//        }

    }








}


