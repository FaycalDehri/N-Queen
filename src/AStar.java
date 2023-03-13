import java.util.*;

public class AStar {
    private final Heuristic heuristic ;

    public AStar(Heuristic h) {
        heuristic = h;
    }

    public int[] search(int n) {
        PriorityQueue<Node> openList = new PriorityQueue<>(new NodeComparator());
        HashSet<String> closedList = new HashSet<>();

        Node root = new Node(n);
        openList.add(root);

        while (!openList.isEmpty()) {
            Node curr = openList.poll();
            closedList.add(Arrays.toString(curr.getState()));

            if (curr.getLevel() == n) {

                return curr.getState();
            }

            ArrayList<Node> currChildren = new ArrayList<>();
            currChildren = curr.genChildren();
            for (Node child : currChildren) {
                System.out.println(Arrays.toString(child.getState()));
                if (!closedList.contains(Arrays.toString(child.getState()))) {
                    child.setG(curr.getG() + 1);
                    child.setH(heuristic.evaluate(child.getState(), n));
                    openList.add(child);
                }
            }
        }

        return null;
    }

    private static class NodeComparator implements Comparator<Node> {
        public int compare(Node a, Node b) {
            int aCost = a.getG() + a.getH();
            int bCost = b.getG() + b.getH();
            return aCost - bCost;
        }
    }
}
