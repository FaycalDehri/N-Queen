package PSO;

public class Particle {

    int[] state;
    Particle pBest;
    int score;


    public Particle(){

    }

    public int[] getState() {
        return state;
    }

    public int getScore() {
        return score;
    }

    public Particle getpBest() {
        return pBest;
    }

    public void setScore() {
        this.score = this.calculateFitness();
    }

    public void setState(int[] state) {
        this.state = state;
    }

    public void setpBest(Particle pBest) {
        if(pBest.score<this.pBest.score){
            this.pBest = pBest;
        }
    }

    public int calculateFitness(){

        int n = this.state.length;
        int conflicts = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (this.state[i] == this.state[j]) {
                    // same row
                    conflicts++;
                }
                if (Math.abs(i - j) == Math.abs(this.state[i] - this.state[j])) {
                    // same diagonal
                    conflicts++;
                }
            }
        }

        return conflicts;
    }

}
