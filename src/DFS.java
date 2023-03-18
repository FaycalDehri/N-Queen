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
    public static int[] search(int n) {

        Stack<Node> stack = new Stack<>();

        //This can be used in case we wanna retrieve all possible solutions
        ArrayList<Node> solutions = new ArrayList<Node>();

        //Counters for generated and developped nodes

        Node root = new Node(n);
        stack.push(root);

        while (!stack.empty()) {
            Node node = stack.pop();

            //we increment each time there is a new node being explored
            Main.generatedNodes++;


            //Once we reach the leaf
            if (node.getLevel() == n) {
                //if it's a valid solution we take it and stop
                if(node.evaluate()){
                    solutions.add(node);
                    break;
                }else{
                    continue;
                }

            }

            ArrayList<Node> children = node.genChildren();
            Main.developedNodes++;

            for (int i = children.size() - 1; i >= 0; i--) {
                stack.push(children.get(i));
            }

        }

        if(solutions.isEmpty()){
            return null;
        }else{

            return solutions.get(0).getState();
        }
    }
}
