package geneticAlgorithm;

import consts.Constants;
import tools.Pair;

import java.util.*;

public class Individual {
    private List<Integer> genes;
    private int fitness;

    /**
     * @param N number of genes
     */
    public Individual(int N) {
        // Create a list of genes
        genes = new ArrayList<>(N);

        // Fill the list with values from 0 to N (numbers of locations)
        for (int i = 0; i < N; i++) {
            genes.add(i, i);
        }

        // In order to create random solution, we shuffle the list
        Collections.shuffle(genes);

        fitness = 0;
    }

    public Individual(List<Integer> genes) {
        this.genes = genes;
    }

    public Individual(Individual other) {
        this.fitness = other.fitness;

        this.genes = new ArrayList<>();

        for (Integer i : other.genes) {
            this.genes.add(new Integer(i));
        }
    }

    public List<Integer> getGenes() {
        return genes;
    }

    public void setGenes(List<Integer> genes) {
        this.genes = genes;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public Pair<Individual, Individual> crossover(Individual other) {
        Random random = new Random();
        int splitPoint = random.nextInt(this.genes.size());

        List<Integer> parentAFirstPart, parentASecondPart, parentBFirstPart, parentBSecondPart;

        // split first parent
        parentAFirstPart = this.genes.subList(0, splitPoint);
        parentASecondPart = this.genes.subList(splitPoint, this.genes.size());

        // split second parent
        parentBFirstPart = other.genes.subList(0, splitPoint);
        parentBSecondPart = other.genes.subList(splitPoint, this.genes.size());

        // create child from first part of parent A and second part of B
        List<Integer> childAGenes = new ArrayList<>(parentAFirstPart);
        childAGenes.addAll(parentBSecondPart);

        /// create child from second part of parent A and first part of B
        List<Integer> childBGenes = new ArrayList<>(parentBFirstPart);
        childBGenes.addAll(parentASecondPart);

        repairGenes(childAGenes);
        repairGenes(childBGenes);

        return new Pair<>(new Individual(childAGenes), new Individual(childBGenes));
    }

    public void mutate(double mutationProbability) {
        Random random = new Random();
        for (int i = 0; i < genes.size(); i++) {
            if (random.nextDouble() <= mutationProbability) {
                Collections.swap(genes, i, random.nextInt(genes.size()));
            }
        }
    }

    public String printWithIndicesNumberedFrom1() {
        StringBuilder builder = new StringBuilder("Fitness: ");
        builder.append(fitness);
        builder.append(", Permutation: [");
        for (Integer gene : genes) {
            builder.append(gene + 1 + ", ");
        }
        builder.replace(builder.length() - 2, builder.length(), "]");
        return builder.toString();
    }

    public boolean equals(Object other) {
        Individual otherIndividual = (Individual) other;
        if (this.genes.equals(otherIndividual.genes) && this.fitness == otherIndividual.fitness) {
            return true;
        }
        return false;
    }

    private void repairGenes(List<Integer> genes) {
        //set of gene values
        Set<Integer> genesSet = new HashSet<>();
        //set of indices of duplicated genes
        Set<Integer> indicesOfDuplicatedGenesSet = new HashSet<>();

        //loop over genes and save them to the set. if specific gene value is alredy in set, save index of that gene
        for (int i = 0; i < genes.size(); i++) {
            int gene = genes.get(i);
            if (!genesSet.contains(gene)) {
                genesSet.add(gene);
            } else {
                indicesOfDuplicatedGenesSet.add(i);
            }
        }

        //loop over indices - get random gene value and check if it is already in our set
        // if it is, we have to generate new random value.
        // if it is not in the set, add it to the set and save as a repaired gene
        for (Integer index : indicesOfDuplicatedGenesSet) {
            Random random = new Random();

            int randomGeneFromRange = random.nextInt(genes.size());

            while (genesSet.contains(randomGeneFromRange)) {
                randomGeneFromRange = random.nextInt(genes.size());
            }

            genesSet.add(randomGeneFromRange);
            genes.set(index, randomGeneFromRange);
        }
    }

}
