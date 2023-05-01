package PSO;

public class Particle {

    int[] state ;
    int[] pBest ;
    int score;

    int pBestScore;


    public Particle(){

    }

    public int[] getpBest() {
        return pBest;
    }

    public int[] getState() {
        return state;
    }

    public int getScore() {
        return score;
    }

    public int getpBestScore() {
        return this.calculatepBestFitness();
    }

    public void initpBest() {
        System.arraycopy(this.state, 0, this.pBest, 0, this.state.length);
    }

    public static void copyArray(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = arr1[i];
        }
    }
    public void initParticle (int [] temp)
    {
        this.state = new int[temp.length];
        this.pBest = new int[temp.length];

        // copy elements from temp into this.state and this.pBest
        copyArray(temp, this.state);
        copyArray(temp, this.pBest);

        this.score = this.calculateFitness();
        this.pBestScore = score;

    }


    public void setScore() {
        this.score = this.calculateFitness();
    }

    public void initpBestScore(){
        this.pBestScore= this.score;
    }

    public void setpBestScore(Particle position) {
        if (this.calculatepBestFitness() < position.score){
            System.arraycopy(position.state, 0, this.pBest, 0, position.state.length);
        }

    }

    public void setState(int[] state) {
        this.state = state;
    }

    /*public void setpBest(Particle pBest) {
        if(pBest.score<this.pBest.score){
            this.pBest = pBest;
        }
    }*/


    public int calculatepBestFitness(){
        int n = this.pBest.length;
        int conflicts = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (this.pBest[i] == this.pBest[j]) {
                    // same row
                    conflicts++;
                }
                if (Math.abs(i - j) == Math.abs(this.pBest[i] - this.pBest[j])) {
                    // same diagonal
                    conflicts++;
                }
            }
        }

        return conflicts;
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