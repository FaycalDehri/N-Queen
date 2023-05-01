package PSO;

import GA.Individual;

import java.util.Comparator;

public class ParticleComparator implements Comparator<Particle> {
    public int compare(Particle a, Particle b) {
        int fitnessA = a.calculateFitness();
        int fitnessB = b.calculateFitness();
        return Integer.compare(fitnessA, fitnessB);
    }
}