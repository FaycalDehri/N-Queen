public class EuclideanDistance implements Heuristic {
    public int evaluate(int[] state, int n) {
        int dist = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (state[i] == state[j]) {
                    dist += 2;
                }
                if (Math.abs(i - j) == Math.abs(state[i] - state[j])) {
                    dist += 2;
                }
            }
        }
        return (int) Math.sqrt(dist);
    }
}
