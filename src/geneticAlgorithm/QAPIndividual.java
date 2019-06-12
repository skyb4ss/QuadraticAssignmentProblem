package geneticAlgorithm;

import utils.Pair;

import java.util.*;

public class QAPIndividual implements Individual {
    private List<Integer> genes;
    private int fitness;

    public QAPIndividual(int numberOfGenes) {
        createRandomGenesOrder(numberOfGenes);

        fitness = 0;
    }

    private void createRandomGenesOrder(int numberOfGenes) {
        genes = new ArrayList<>(numberOfGenes);

        for (int i = 0; i < numberOfGenes; i++)
            genes.add(i, i);

        Collections.shuffle(genes);
    }

    private QAPIndividual(List<Integer> genes) {
        this.genes = genes;
    }

    public QAPIndividual(QAPIndividual other) {
        genes = new ArrayList<>(other.genes);
        fitness = other.fitness;
    }

    public List<Integer> getGenes() {
        return genes;
    }

    public int getFitness() {
        return fitness;
    }

    void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public Pair<Individual, Individual> crossover(Individual other) {
        Random random = new Random();
        int splitPoint = random.nextInt(this.genes.size());

        List<Integer> parentAFirstPart, parentASecondPart, parentBFirstPart, parentBSecondPart;

        QAPIndividual qapOther = (QAPIndividual) other;

        // split first parent
        parentAFirstPart = this.genes.subList(0, splitPoint);
        parentASecondPart = this.genes.subList(splitPoint, this.genes.size());

        // split second parent
        parentBFirstPart = qapOther.genes.subList(0, splitPoint);
        parentBSecondPart = qapOther.genes.subList(splitPoint, this.genes.size());

        // create child from first part of parent A and second part of B
        List<Integer> childAGenes = new ArrayList<>(parentAFirstPart);
        childAGenes.addAll(parentBSecondPart);

        /// create child from second part of parent A and first part of B
        List<Integer> childBGenes = new ArrayList<>(parentBFirstPart);
        childBGenes.addAll(parentASecondPart);

        repairGenes(childAGenes);
        repairGenes(childBGenes);

        return new Pair<>(new QAPIndividual(childAGenes), new QAPIndividual(childBGenes));
    }

    private void repairGenes(List<Integer> genes) {
        Set<Integer> genesValues = new HashSet<>();
        Set<Integer> genesIndices = new HashSet<>();

        //loop over genes and save them to the set. if specific gene value is alredy in set, save
        // index of that gene
        for (int i = 0; i < genes.size(); i++) {
            int gene = genes.get(i);

            if (!genesValues.contains(gene)) genesValues.add(gene);
            else genesIndices.add(i);
        }

        //loop over indices - get random gene value and check if it is already in our set
        // if it is, we have to generate new random value.
        // if it is not in the set, add it to the set and save as a repaired gene
        for (Integer index : genesIndices) {
            Random random = new Random();
            int randomGeneFromRange = random.nextInt(genes.size());

            while (genesValues.contains(randomGeneFromRange))
                randomGeneFromRange = random.nextInt(genes.size());

            genesValues.add(randomGeneFromRange);
            genes.set(index, randomGeneFromRange);
        }
    }

    public void mutate() {
        Random random = new Random();

        for (int i = 0; i < genes.size(); i++)
            Collections.swap(genes, i, random.nextInt(genes.size()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QAPIndividual that = (QAPIndividual) o;
        return fitness == that.fitness &&
                Objects.equals(genes, that.genes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genes, fitness);
    }
}
