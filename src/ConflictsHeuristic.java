public class ConflictsHeuristic implements Heuristic {
    public int evaluate(int[] state, int n) {
        int conflicts = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (state[i] == state[j] || Math.abs(i - j) == Math.abs(state[i] - state[j])) {
                    conflicts++;
                }
            }
        }

        return conflicts;
    }
}
