package geneticAlgorithm;

import java.util.Collections;
import java.util.List;
import java.util.Random;

class QAPIndividualMutation {
    private static Random random = new Random();

    static void mutate(QAPIndividual individual) {
        List<Integer> permutation = individual.getPermutation();

        for (int i = 0; i < permutation.size(); i++)
            Collections.swap(permutation, i, random.nextInt(permutation.size()));
    }
}
