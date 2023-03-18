import java.util.ArrayList;


public class Node {
    private int[] state; // current state
    private int n; // that's why the game is called n-queen...haha..end me
    private int level; // depth of the search space ... get it ? DEPTH first search ... haha
    private Node parent;
    private boolean developped; // to check if a node is developped after being generated or not
    private int g; //g(x)
    private int h;//h(x)

    public Node(int n) {
        this.n = n;
        this.state = new int[n];
        this.level = 0;
        this.g = 0;
        this.h = 0;
    }
    public Node(int[] state, int level, int n) {
        this.n = n;
        this.level = level;
        this.state = new int[n];
        System.arraycopy(state, 0, this.state, 0, n);
        //this.state = state;
        this.developped = false;
        g = 0;
        h = 0;
    }
    public Node(int[] state, int level, int n, Node parent) {
        this.n = n;
        this.level = level;
        this.state = new int[n];
        System.arraycopy(state, 0, this.state, 0, n);
        this.developped = false;
        this.parent = parent;
        g = 0;
        h = 0;
    }

    public Node(){
        this.g = 0;
        this.h = 0;
    }

    public void setState(int[] state) {
        this.state = state;
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

    public void setG(int g){
        this.g=g;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    //This methode is used to generate all the possible next nodes of the search space
    public ArrayList<Node> genChildren() {
        ArrayList<Node> children = new ArrayList<>();

        for (int i = 0; i <n; i++) {
            //first we check if the queen doesnt risk being attacked in the new placement
            if (level < n && checkColumnsOnly(i)) {
                //we copy the exact current state than add a new placement in the following column
                int[] childState  = state.clone();
                // System.arraycopy(state, 0, childState, 0, n);

                childState[level] = i;
                //we create a new child node to add it the children list of the parent node

                Node child = new Node(childState, level + 1, n,this);

                child.setG(g + 1);

                children.add(child);
            }
            this.developped = true;
        }
        return children;
    }

    public ArrayList<Node> genSmartChildren() {
        ArrayList<Node> children = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            //first we check if the queen doesnt risk being attacked in the new placement
            if (checkColumnsAndDiagonals(i)) {
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

    public boolean checkColumnsAndDiagonals(int col) {

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
    private boolean checkColumnsOnly(int col) {
        for (int i = 0; i < level; i++) {
            // If the new placement is in the same column as an existing queen
            // then it is not safe (attacking)
            if (state[i] == col) {
                return false;
            }
        }
        return true;
    }

    public boolean evaluate(){

        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                //checks if any queen is on the same column as the current queen
                if (state[i] == state[j]) {
                    return false;
                }
                //checks if any queen is on the same diagonal as the current queen
                if (Math.abs(i-j) == Math.abs(state[i]-state[j])) {
                    return false;
                }
            }
        }
        return true;
    }


}