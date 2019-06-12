package geneticAlgorithm;

import utils.Pair;

import java.util.*;

class QAPIndividualCrossover {
    static Pair<Individual, Individual> crossover(QAPIndividual individual1,
                                                  QAPIndividual individual2) {
        int splitPoint = getSplitPoint(individual1);

        QAPIndividualSplit parent1Split = split(individual1, splitPoint);
        QAPIndividualSplit parent2Split = split(individual2, splitPoint);

        return mergeAndRepair(parent1Split, parent2Split);
    }

    private static Pair<Individual, Individual> mergeAndRepair(QAPIndividualSplit split1,
                                                               QAPIndividualSplit split2) {
        List<Integer> permutation1 = mergeParts(split1.part1, split2.part2);
        List<Integer> permutation2 = mergeParts(split2.part1, split1.part2);

        repairPermutation(permutation1);
        repairPermutation(permutation2);

        Individual child1 = new QAPIndividual(permutation1);
        Individual child2 = new QAPIndividual(permutation2);

        return new Pair<>(child1, child2);
    }

    private static List<Integer> mergeParts(List<Integer> part1, List<Integer> part2) {
        List<Integer> permutation = new ArrayList<>(part1);
        permutation.addAll(part2);

        return permutation;
    }

    private static QAPIndividualSplit split(QAPIndividual individual,
                                            int splitPoint) {
        List<Integer> permutation, part1, part2;
        permutation = individual.getPermutation();

        part1 = permutation.subList(0, splitPoint);
        part2 = permutation.subList(splitPoint, permutation.size());

        return new QAPIndividualSplit(part1, part2);
    }

    private static int getSplitPoint(QAPIndividual individual) {
        Random random = new Random();
        return random.nextInt(individual.getPermutation().size());
    }

    private static void repairPermutation(List<Integer> permutation) {
        Set<Integer> genesValues = new HashSet<>();
        Set<Integer> genesIndices = new HashSet<>();

        //loop over permutation and save them to the set. if specific gene value is alredy in
        // set, save
        // index of that gene
        for (int i = 0; i < permutation.size(); i++) {
            int gene = permutation.get(i);

            if (!genesValues.contains(gene)) genesValues.add(gene);
            else genesIndices.add(i);
        }

        //loop over indices - get random gene value and check if it is already in our set
        // if it is, we have to generate new random value.
        // if it is not in the set, add it to the set and save as a repaired gene
        for (Integer index : genesIndices) {
            Random random = new Random();
            int randomGeneFromRange = random.nextInt(permutation.size());

            while (genesValues.contains(randomGeneFromRange))
                randomGeneFromRange = random.nextInt(permutation.size());

            genesValues.add(randomGeneFromRange);
            permutation.set(index, randomGeneFromRange);
        }
    }

    static class QAPIndividualSplit {
        List<Integer> part1, part2;

        QAPIndividualSplit(List<Integer> part1, List<Integer> part2) {
            this.part1 = part1;
            this.part2 = part2;
        }
    }
}
