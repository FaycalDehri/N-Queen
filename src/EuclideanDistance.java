public class EuclideanDistance implements Heuristic {
    public int evaluate(Node node) {
        int dist = 0;
        for (int i = 0; i < node.getN(); i++) {
            for (int j = i + 1; j < node.getN(); j++) {
                if (node.getState()[i] == node.getState()[j]) {
                    dist += 2;
                }
                if (Math.abs(i - j) == Math.abs(node.getState()[i] - node.getState()[j])) {
                    dist += 2;
                }
            }
        }
        return (int) Math.sqrt(dist);
    }
}
