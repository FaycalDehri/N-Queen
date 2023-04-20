
public class ConflictsHeuristic implements Heuristic {
    public int evaluate(Node node) {
        int[] state = node.getState();
        int n = state.length;
        int conflicts = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (state[i] == state[j]) { // same row
                    conflicts++;
                }
                if (Math.abs(i - j) == Math.abs(state[i] - state[j])) { // same diagonal
                    conflicts++;
                }
            }
        }

        return conflicts;
    }

}
