package geneticAlgorithm;

import utils.QAPData;

public class GeneticAlgorithmParams {
    private int N;
    private int[][] flowMatrix, distanceMatrix;

    private double crossoverProbability, mutationProbability;
    private int populationSize;

    public GeneticAlgorithmParams(QAPData qapData,
                                  double crossoverProbability, double mutationProbability,
                                  int populationSize) {
        N = qapData.getN();
        flowMatrix = qapData.getFlowMatrix();
        distanceMatrix = qapData.getDistanceMatrix();
        this.crossoverProbability = crossoverProbability;
        this.mutationProbability = mutationProbability;
        this.populationSize = populationSize;
    }

    public int getN() {
        return N;
    }

    public int[][] getFlowMatrix() {
        return flowMatrix;
    }

    public int[][] getDistanceMatrix() {
        return distanceMatrix;
    }

    public double getCrossoverProbability() {
        return crossoverProbability;
    }

    public double getMutationProbability() {
        return mutationProbability;
    }

    public int getPopulationSize() {
        return populationSize;
    }
}
