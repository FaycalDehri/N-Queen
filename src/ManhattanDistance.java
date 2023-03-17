
//The way manhattan distance works is basically it will calculate the vertical distance separating 2 points
//then calculate the vertical distance and add them up, it doesn't take to consideration to diagonal distance
// which will be useful in case there is a direct attack between our queens but not a diagonal one
public class ManhattanDistance implements Heuristic {
    public int evaluate(int[] state, int n) {

        //dist is the total number of attacking queens
        int dist = 0;
        for (int i = 0; i < n; i++) {
            //this will loop through the rows of the chess board
            for (int j = i + 1; j < n; j++) {
                //first we check if i and j are on the same column if so we increment dist
                if (state[i] == state[j]) {
                    dist++;
                }
                //then we check if the 2 queens are on the same diagonal
                if (Math.abs(i - j) == Math.abs(state[i] - state[j])) {
                    dist++;
                }
            }
        }
        return dist;
    }
}

