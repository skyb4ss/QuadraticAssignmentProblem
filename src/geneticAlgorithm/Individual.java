package geneticAlgorithm;

import utils.Pair;

public interface Individual {
    int getFitness();

    void mutate();

    Pair<Individual, Individual> crossover(Individual other);
}
