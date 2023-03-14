//import java.util.*;
//
//public class Astar {
//
//    public static int[] search(int n,Heuristic h) {
//        PriorityQueue<Node> openList = new PriorityQueue<>(new NodeComparator());
//        ArrayList<String> closedList = new ArrayList<>();
//
//        ArrayList<int[]> solutions = new ArrayList<>();
//
//        Node root = new Node(n);
//        openList.add(root);
//
//        while (!openList.isEmpty()) {
//            Node curr = openList.poll();
//            closedList.add(Arrays.toString(curr.getState()));
//
//            if (curr.getLevel() == n) {
//                System.out.println(Arrays.toString(curr.getState()));
//                //solutions.add(curr.getState());
//               return curr.getState();
//            }else{
//                ArrayList<Node> currChildren = curr.genChildren();
//                if(!currChildren.isEmpty()) {
//                   // System.out.println("Curr Children: " + currChildren);
//                    for (Node child : currChildren) {
//                        if (!closedList.contains(Arrays.toString(child.getState()))) {
//                            child.setG(curr.getG() + 1);
//                            child.setH(h.evaluate(child.getState(), n));
//                            System.out.println(child.getLevel());
//                            openList.add(child);
//                        }
//
//                    }
//                }
//            }
//
//
//        }
//        return null;
//    }
//
//    private static class NodeComparator implements Comparator<Node> {
//        public int compare(Node a, Node b) {
//            int aCost = a.getG() + a.getH();
//            int bCost = b.getG() + b.getH();
//
//            return aCost - bCost;
//        }
//    }
//}
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class Astar {


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
            Main.developedNodes++;

            //we enter this condition only if we reach a leaf node that is a valid solution
            if (current.evaluate()) {
                return current.getState();
            }


            closedSet.add(current);

            //We check the visited nodes to not have duplicates
            for (Node neighbor : current.genChildren()) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                //This will be used to calculate the g of potential nodes
                int tentativeG = current.getG() + 1;

                if (!openSet.contains(neighbor) || tentativeG < neighbor.getG()) {
                    neighbor.setParent(current);
                    neighbor.setG(tentativeG);

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                        Main.generatedNodes++;

                    }
                }
            }
        }

        return null;
    }

}

