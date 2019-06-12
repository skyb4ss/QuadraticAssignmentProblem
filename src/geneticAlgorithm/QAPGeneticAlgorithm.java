package geneticAlgorithm;

import consts.Params;
import utils.QAPData;

public class QAPGeneticAlgorithm implements GeneticAlgorithm {
    private QAPData qapData;
    private GeneticAlgorithmParams params;

    public QAPGeneticAlgorithm(QAPData qapData, GeneticAlgorithmParams params) {
        this.qapData = qapData;
        this.params = params;
    }

    @Override
    public Individual findSolution() {
        Population population = new QAPPopulation(qapData, params);

        Individual bestSolution = population.getBestIndividual();

        for (int i = 0; i < Params.GENERATIONS; i++) {
            population = population.evolve();
            Individual generationBestSolution = population.getBestIndividual();

            bestSolution = getBetterSolution(generationBestSolution, bestSolution);
        }

        return bestSolution;
    }

    private Individual getBetterSolution(Individual solution1, Individual solution2) {
        return solution1.getFitness() < solution2.getFitness() ? solution1 : solution2;
    }
}

