import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class Aetoile {


    public static int[] search(Node start, Heuristic heuristic) {

        //We set the priority queue with a coparator that caluclaates the function f = g + h
        //this ensures that the head of the queue is always the node with the minimum f cost
        PriorityQueue<Node> openSet = new PriorityQueue<>((n1, n2) -> {
            int f1 = n1.getG() + heuristic.evaluate(n1);
            int f2 = n2.getG() + heuristic.evaluate(n2);
            return f1 - f2;
        });

        //to store the nodes that have already been explored to avoid re-exploring them
        HashSet<Node> closedSet = new HashSet<>();

        openSet.add(start);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            //we enter this condition only if we reach a leaf node that is a valid solution
            if (current.evaluate()) {
                return current.getState();
            }


            closedSet.add(current);

            for (Node neighbor : current.genChildren()) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                int tentativeG = current.getG() + 1;

                if (!openSet.contains(neighbor) || tentativeG < neighbor.getG()) {
                    neighbor.setParent(current);
                    neighbor.setG(tentativeG);

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return null;
    }

    private static ArrayList<Node> reconstructPath(Node current) {
        ArrayList<Node> path = new ArrayList<>();
        path.add(current);

        while (current.getParent() != null) {
            current = current.getParent();
            path.add(0, current);
        }

        return path;
    }
}

