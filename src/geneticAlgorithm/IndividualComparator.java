package geneticAlgorithm;

import java.util.Comparator;

/**
 * Compares two individuals by its fitness
 */
public class IndividualComparator implements Comparator<Individual> {
    @Override
    public int compare(Individual o1, Individual o2) {
        return o1.getFitness() - o2.getFitness();
    }
}
