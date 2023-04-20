

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


//This is a second implementation of Blind.DFS that doesnt generate nodes if we are sure they wont lead to a solution
//i.e the number of generated nodes = the number of developped nodes - 1 (The leaf node)
public class DFS2 {
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
        int developedNodes = 0;
        int generatedNodes = 0;

        Node root = new Node(n);
        stack.push(root);

        while (!stack.empty()) {
            Node node = stack.pop();

            generatedNodes ++;

            if (node.getLevel() == n) {

                printArray(node.getState());
                if(node.evaluate()){
                    solutions.add(node);
                    break;
                }else{
                    continue;
                }

            }


            ArrayList<Node> children = node.genSmartChildren();
            developedNodes++;

            for (int i = children.size() - 1; i >= 0; i--) {
                stack.push(children.get(i));
            }

        }


        System.out.println("Generated nodes : " + generatedNodes +" Developped nodes : " + developedNodes);

        if(solutions.isEmpty()){
            return null;
        }else{

            return solutions.get(0).getState();
        }
    }



}