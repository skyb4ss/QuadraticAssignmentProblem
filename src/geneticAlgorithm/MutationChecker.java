package geneticAlgorithm;

import java.util.Random;

public class MutationChecker {
    private static Random random = new Random();

    public static boolean shouldMutate(GeneticAlgorithmParams geneticAlgorithmParams) {
        return random.nextDouble() <= geneticAlgorithmParams.getMutationProbability();
    }
}
