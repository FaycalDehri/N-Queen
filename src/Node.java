import java.util.ArrayList;
import java.util.List;

public class Node {
    private int[] state; // current state
    private int n; // that's why the game is called n-queen...haha..end me
    private int level; // depth of the search space ... get it ? DEPTH first search ... haha

    private int g; //g(x)
    private int h;//h(x)

    public Node(int n) {
        this.n = n;
        state = new int[n];
        level = 0;

        g = 0;
        h = 0;

    }
    public Node(int[] state, int level, int n) {
        this.n = n;
        this.level = level;
        this.state = new int[n];
        System.arraycopy(state, 0, this.state, 0, n);

        g = 0;
        h = 0;
    }
    public int[] getState() {
        return state;
    }
    public int getLevel() {
        return level;
    }

    public int getN(){
        return n;
    }

        public int getG(){
            return g;
        }
        public int getH(){
        return h;
        }

        public void setG(int g){
            this.g=g;
        }
        public void setH(int h) {
        this.h = h;
    }


    //This methode is used to generate all the possible next nodes of the search space
    public ArrayList<Node> genChildren() {
        ArrayList<Node> children = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            //first we check if the queen doesnt risk being attacked in the new placement
            if (isValid(i)) {
                //we copy the exact current state than add a new placement in the following column
                int[] childState = new int[n];
                System.arraycopy(state, 0, childState, 0, n);
                childState[level] = i;
                //we create a new child node to add it the children list of the parent node
                Node child = new Node(childState, level + 1, n);
                children.add(child);
            }
        }
        return children;
    }



    public boolean isValid(int col) {

        for (int i = 0; i < level; i++) {
            //checks if any queen is on the same column as the current queen
            if (state[i] == col) {
                return false;
            }
            //checks if any queen is on the same diagonal as the current queen
            if (level - i == Math.abs(col - state[i])) {
                return false;
            }
        }
        return true;
    }


}
