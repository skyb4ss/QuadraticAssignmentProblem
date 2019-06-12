package geneticAlgorithm;

import java.util.Random;

public class CrossoverChecker {
    private static Random random = new Random();

    public static boolean shouldCrossover(GeneticAlgorithmParams geneticAlgorithmParams) {
        return random.nextDouble() <= geneticAlgorithmParams.getCrossoverProbability();
    }
}
