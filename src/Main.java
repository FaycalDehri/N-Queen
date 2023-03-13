import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void printArray(int[] t){
        for(int i : t){
            System.out.print(i+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int n = 4;

;
        ManhattanDistance h = new ManhattanDistance();
        ArrayList<int[]> temp = new ArrayList<int []>();
        ArrayList<int[]> temp2 = new ArrayList<int []>();
      // AStar a = new AStar(h);
//       int[] solution = a.search(n);

        int[] solution = BFS.search(n, temp);
        //int[] solution2 = BFS.search(n, temp2);

        System.out.println(Arrays.toString(solution));


        if (solution == null) {
            System.out.println("No solution found.");
        } else {
            System.out.println("Solution found:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (solution[i] == j) {
                        System.out.print("Q ");
                    } else {
                        System.out.print("* ");
                    }
                }
                System.out.println();
            }
        }

//        for(int []i : temp){
//            printArray(i);
//        }

//        System.out.println(temp.size());
//        System.out.println(temp2.size());

    }


}
