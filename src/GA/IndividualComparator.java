package GA;

import java.util.Comparator;

public class IndividualComparator implements Comparator<Individual> {
    public int compare(Individual a, Individual b) {
        int fitnessA = a.calculateFitnessScore();
        int fitnessB = b.calculateFitnessScore();
        if (fitnessA > fitnessB) {
            return 1;
        } else if (fitnessA < fitnessB) {
            return -1;
        } else {
            return 0;
        }
    }
}

