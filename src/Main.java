import java.util.ArrayList;

public class Main {

    public static void printArray(int[] t){
        for(int i : t){
            System.out.print(i+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int n = 4
                ;
        ArrayList<int[]> temp = new ArrayList<int []>();
        int[] solution = BFS.search(n, temp);

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

        for(int []i : temp){
            printArray(i);
        }
    }

}
