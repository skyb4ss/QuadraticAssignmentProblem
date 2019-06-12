package geneticAlgorithm;

import java.util.Random;

class CrossoverChecker {
    private static Random random = new Random();

    static boolean shouldCrossover(GeneticAlgorithmParams params) {
        return random.nextDouble() <= params.getCrossoverProbability();
    }
}
