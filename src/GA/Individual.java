package GA;

public class Individual{

    int mutation =0;
    public static int  counter = 0;

    public static int  age = 0;

    int[] chromosome;

    int fitnessScore;

    public Individual(){
       // this.fitnessScore = this.calculateFitnessScore();
    }


    public int getMutation() {
        return mutation;
    }

    public void setMutation(int mutation) {
        this.mutation = mutation;
    }



    public int[] getChromosome() {
        return chromosome;
    }

    public int getAge() {
        return age;
    }

    public int getFitnessScore() {
        return fitnessScore;
    }

    public void setChromosome(int[] chromosome) {
        this.chromosome = chromosome;
    }

//    public void setAge(int age) {
//        this.age = age;
//    }

    public void setFitnessScore(int fitnessScore) {

        this.fitnessScore = fitnessScore;
    }
    public void updateFitnessScore(){
        int newScore = this.calculateFitnessScore();
        this.setFitnessScore(newScore);
    }

    public int calculateFitnessScore(){

        int n = this.chromosome.length;
        int conflicts = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (this.chromosome[i] == this.chromosome[j]) {
                    // same row
                    conflicts++;
                }
                if (Math.abs(i - j) == Math.abs(this.chromosome[i] - this.chromosome[j])) {
                    // same diagonal
                    conflicts++;
                }
            }
        }

        return conflicts;
    }



}
