package geneticAlgorithm;

import utils.QAPData;

public class GeneticAlgorithmParams {
    private double crossoverProbability, mutationProbability;
    private int populationSize;

    public GeneticAlgorithmParams(
            double crossoverProbability, double mutationProbability,
            int populationSize) {
        this.crossoverProbability = crossoverProbability;
        this.mutationProbability = mutationProbability;
        this.populationSize = populationSize;
    }

    double getCrossoverProbability() {
        return crossoverProbability;
    }

    double getMutationProbability() {
        return mutationProbability;
    }

    int getPopulationSize() {
        return populationSize;
    }
}
