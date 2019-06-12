package geneticAlgorithm;

import utils.Pair;

import java.util.*;

public class QAPIndividual implements Individual {
    private List<Integer> permutation;
    private int cost;

    public QAPIndividual(int numberOfGenes) {
        createRandomPermutation(numberOfGenes);

        cost = 0;
    }

    QAPIndividual(List<Integer> permutation) {
        this.permutation = permutation;
    }

    private void createRandomPermutation(int numberOfGenes) {
        permutation = new ArrayList<>(numberOfGenes);

        for (int i = 0; i < numberOfGenes; i++)
            permutation.add(i, i);

        Collections.shuffle(permutation);
    }

    public QAPIndividual(QAPIndividual other) {
        permutation = new ArrayList<>(other.permutation);
        cost = other.cost;
    }

    public Pair<Individual, Individual> crossover(Individual other) {
        return QAPIndividualCrossover.crossover(this, (QAPIndividual) other);
    }

    public void mutate() {
        QAPIndividualMutation.mutate(this);
    }

    public List<Integer> getPermutation() {
        return permutation;
    }

    public int getFitness() {
        return cost;
    }

    void setFitness(int fitness) {
        this.cost = fitness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QAPIndividual that = (QAPIndividual) o;
        return cost == that.cost &&
                Objects.equals(permutation, that.permutation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permutation, cost);
    }
}
