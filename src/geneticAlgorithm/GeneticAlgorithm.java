package geneticAlgorithm;

import consts.Params;

public class GeneticAlgorithm {
    private GeneticAlgorithmParams params;
    private Individual bestSolution;

    public GeneticAlgorithm(GeneticAlgorithmParams params) {
        this.params = params;
    }

    public void run() {
        Population population = new QAPPopulation(params);

        bestSolution = population.getBestIndividual();

        for (int i = 0; i < Params.GENERATIONS; i++) {
            population = population.evolve();
            Individual generationBestSolution = population.getBestIndividual();

            if (hasFoundBetterSolution(generationBestSolution))
                bestSolution = generationBestSolution;

            System.out.println(bestSolution.getFitness());
        }
    }

    private boolean hasFoundBetterSolution(Individual individual) {
        return individual.getFitness() < bestSolution.getFitness();
    }

    public Individual getBestSolution() {
        return bestSolution;
    }
}

