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