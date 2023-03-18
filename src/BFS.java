import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
    public static void printArray(int[] t) {
        for (int i : t) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static int[] search(int n) {
        Queue<Node> queue = new LinkedList<>();
        ArrayList<Node> solutions = new ArrayList<>();
        ArrayList<Node> children = new ArrayList<>();

        Node root = new Node(n);
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            Main.generatedNodes++;

            if (node.getLevel() == n) {

                if (node.evaluate()) {
                    solutions.add(node);
                    return solutions.get(0).getState();
                } else {
                    continue;
                }

            }

            children = node.genChildren();
            Main.developedNodes++;

            queue.addAll(children);
        }


        if (solutions.isEmpty()) {
            return null;
        } else {
            return solutions.get(0).getState();
        }
    }
}