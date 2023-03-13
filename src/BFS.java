import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
    public static void printArray(int[] t){
        for(int i : t){
            System.out.print(i+" ");
        }
        System.out.println();
    }

    public static int[] search(int n, ArrayList<int[]> searchSpace) {
        Queue<Node> queue = new LinkedList<>();
        //ArrayList<int[]> solutions = new ArrayList<int[]>();

        Node root = new Node(n);
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.getLevel() == n) {

                //searchSpace.add(node.getState());
                //prints the solution
                System.out.println("Solution found: " );
                //solutions.add(node.getState());
                printArray(node.getState());

                //this parts takes of asking the user if they want another solution
                Scanner scanner = new Scanner(System.in);
                System.out.print("Do you want another solution? (Y/N): ");
                String response = scanner.nextLine();

                //We need to make sure that each generated solution is unique
                if (response.equalsIgnoreCase("Y")) {
                    continue;
                } else {
                    return node.getState();
                }
            }

            ArrayList<Node> children = new ArrayList<>();

            children = node.genChildren();

            for(Node i :children){
                searchSpace.add(i.getState());
            }

            for (int i = 0; i < children.size(); i++) {
                queue.offer(children.get(i));
            }
        }

        return null;
    }
}
