package geneticAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Population {
    /**
     * A collection of genotypes
     */
    private List<Individual> individuals;

    /**
     * Creates a population with random genes
     *
     * @param populationSize population size
     */
    public Population(int populationSize, int N) {
        individuals = new ArrayList<>(populationSize);

        for (int i = 0; i < populationSize; i++) {
            individuals.add(new Individual(N));
        }
    }

    /**
     * Creates new, EMPTY population instance
     */
    public Population() {
        individuals = new ArrayList<>();
    }

    /**
     * Clones population
     * @param other other population
     */
    public Population(Population other) {
        individuals = new ArrayList<>();

        for (Individual i : other.individuals) {
            individuals.add(new Individual(i));
        }
    }

    public List<Individual> getIndividuals() {
        return individuals;
    }

    public void add(Individual individual) {
        individuals.add(individual);
    }

    /**
     * Sorts population by each individual's fitness
     */
    public void sort() {
        Collections.sort(individuals, new IndividualComparator());
    }
}
