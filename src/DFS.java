import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class DFS {
    static int generatedNodes= 0;
    public static void printArray(int[] t){
        for(int i : t){
            System.out.print(i+" ");
        }
        System.out.println();
    }

    public static ArrayList<int[]> search(int n, ArrayList<int[]> searchSpace) {

        Stack<Node> stack = new Stack<>();
        ArrayList<int[]> ArraySolutions = new ArrayList<int[]>(n);
        //ArrayList<int[]> solutions = new ArrayList<int[]>();

        Node root = new Node(n); generatedNodes++;
        stack.push(root);


        while (!stack.empty()) {
            Node node = stack.pop();
            if (node.getLevel() == n) {

                //searchSpace.add(node.getState());
                //prints the solution
                //System.out.println("Solution found: ");
                //solutions.add(node.getState());
                if (ArraySolutions.isEmpty()) {
                    System.out.println("first solution found" + generatedNodes);
                }
                    ArraySolutions.add(node.getState());
                    //printArray(node.getState());

               //printArray(node.getState());

                //this parts takes of asking the user if they want another solution
//                Scanner scanner = new Scanner(System.in);
//                System.out.print("Do you want another solution? (Y/N): ");
//                String response = scanner.nextLine();
//
//                //We need to make sure that each generated solution is unique
//                if (response.equalsIgnoreCase("Y")) {
//                    continue;
//                } else {
//                    return node.getState();
//                }


            }

            node.genChildren(); generatedNodes++; generatedNodes++;
//            System.out.println("node number " + generatedNodes);


            for (Node i : node.getChildren()) {
                searchSpace.add(i.getState());
            }

            for (int i = node.getChildren().size() - 1; i >= 0; i--) {
                stack.push(node.getChildren().get(i));
            }
        }

        return ArraySolutions;
    }
}
