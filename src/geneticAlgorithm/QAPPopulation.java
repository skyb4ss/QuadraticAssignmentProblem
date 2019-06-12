package geneticAlgorithm;

import consts.Params;
import utils.FitnessCalculator;
import utils.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QAPPopulation implements Population {
    private List<QAPIndividual> individuals;
    private GeneticAlgorithmParams params;

    QAPPopulation(GeneticAlgorithmParams params) {
        individuals = new ArrayList<>(params.getPopulationSize());
        this.params = params;

        for (int i = 0; i < params.getPopulationSize(); i++)
            individuals.add(new QAPIndividual(params.getN()));
    }

    public QAPPopulation() {
        individuals = new ArrayList<>();
    }

    public List<QAPIndividual> getIndividuals() {
        return individuals;
    }

    public Individual getBestIndividual() {
        calculateFitness();

        Individual bestIndividual = individuals.get(0);

        for (Individual individual : individuals)
            if (individual.getFitness() < bestIndividual.getFitness())
                bestIndividual = individual;

        return bestIndividual;
    }

    @Override
    public Population evolve() {
        QAPPopulation newPopulation = new QAPPopulation();
        newPopulation.params = params;

        // population divided by 2, because each time we add 2 new individuals
        for (int j = 0; j < individuals.size() / 2; j++) {
            QAPIndividual individualA, individualB;

            individualA = performTournamentSelection();
            individualB = performTournamentSelection();

            if (shouldCrossover()) {
                Pair<Individual, Individual> children = individualA.crossover(individualB);

                QAPIndividual childA = (QAPIndividual) children.a;
                QAPIndividual childB = (QAPIndividual) children.b;

                if (shouldMutate()) childA.mutate();
                if (shouldMutate()) childB.mutate();

                newPopulation.add(childA);
                newPopulation.add(childB);
            } else {
                if (shouldMutate()) individualA.mutate();
                if (shouldMutate()) individualB.mutate();

                newPopulation.add(individualA);
                newPopulation.add(individualB);
            }
        }

        return newPopulation;
    }

    private void add(QAPIndividual individual) {
        individuals.add(individual);
    }

    private QAPIndividual performTournamentSelection() {
        Collections.shuffle(individuals);

        List<QAPIndividual> chosenIndividuals = individuals.subList(0, Params.TOUR);

        chosenIndividuals.sort(new IndividualComparator());

        return individuals.get(0);
    }

    private boolean shouldCrossover() {
        return CrossoverChecker.shouldCrossover(params);
    }

    private boolean shouldMutate() {
        return MutationChecker.shouldMutate(params);
    }

    private void calculateFitness() {
        for (QAPIndividual individual : individuals) {
            int fitness = FitnessCalculator.calculateFitness(params.getN(),
                    params.getFlowMatrix(), params.getDistanceMatrix(), individual.getGenes());

            individual.setFitness(fitness);
        }
    }
}
