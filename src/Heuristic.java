
// This is an interface that will be used to implement different heursitics
//it containe one abstract methode that will evaluate "f" for each Node that it receives
//depending on the heuristic it uses
public interface Heuristic {
    public  int evaluate(int[] state, int n);


}
