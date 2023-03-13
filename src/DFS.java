import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class DFS {
    public static void printArray(int[] t){
        for(int i : t){
            System.out.print(i+" ");
        }
        System.out.println();
    }
    public static int[] search(int n, ArrayList<int[]> searchSpace) {

        Stack<Node> stack = new Stack<>();
        //ArrayList<int[]> solutions = new ArrayList<int[]>();

        Node root = new Node(n);
        stack.push(root);

        while (!stack.empty()) {
            Node node = stack.pop();

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


            for(Node i : children){
                searchSpace.add(i.getState());
            }

            for (int i = children.size() - 1; i >= 0; i--) {
                stack.push(children.get(i));
            }
        }

        return null;
    }

}
