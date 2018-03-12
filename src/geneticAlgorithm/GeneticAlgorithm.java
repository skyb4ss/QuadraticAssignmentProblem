package geneticAlgorithm;

import consts.Constants;
import tools.FitnessCalculator;
import tools.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneticAlgorithm {
    private static final Logger LOGGER = Logger.getLogger(GeneticAlgorithm.class.getName());

    private static Random random = new Random();


    private int N;
    private int[][] flowMatrix, distanceMatrix;

    private double crossoverProbability, mutationProbability;
    private int populationSize;
    private String selectionMethod;

    private Population population;
    private Individual bestSolution;


    public GeneticAlgorithm(int N, int[][] flowMatrix, int[][] distanceMatrix) {
        this.N = N;
        this.flowMatrix = flowMatrix;
        this.distanceMatrix = distanceMatrix;
    }

    public GeneticAlgorithm(int N, int[][] flowMatrix, int[][] distanceMatrix, double crossoverProbability, double mutationProbability, int populationSize, String selectionMethod) {
        this.N = N;
        this.flowMatrix = flowMatrix;
        this.distanceMatrix = distanceMatrix;
        this.crossoverProbability = crossoverProbability;
        this.mutationProbability = mutationProbability;
        this.populationSize = populationSize;
        this.selectionMethod = selectionMethod;
    }

    /**
     * Runs genetic alghorithm
     */
    public void run() {
        initializePopulation();

        calculateFitness();

        bestSolution = copyAndSortPopulation(population).getIndividuals().get(0);

        for (int i = 0; i < Constants.GENERATIONS; i++) {
            Population newPopulation = new Population();

            // population divided by 2, because each time we add 2 new individuals
            for (int j = 0; j < populationSize / 2; j++) {
                Individual individualA, individualB;

                if (selectionMethod.equals("tournament")) {
                    individualA = performTournamentSelection();
                    individualB = performTournamentSelection();
                } else {
                    individualA = performRouletteWheelSpinSelection();
                    individualB = performRouletteWheelSpinSelection();
                }

                if (random.nextDouble() <= crossoverProbability) {
                    Pair<Individual, Individual> children = individualA.crossover(individualB);

                    Individual childA = children.a;
                    Individual childB = children.b;

                    childA.mutate(mutationProbability);
                    childB.mutate(mutationProbability);
                    newPopulation.add(childA);
                    newPopulation.add(childB);
                } else {
                    individualA.mutate(mutationProbability);
                    individualB.mutate(mutationProbability);

                    newPopulation.add(individualA);
                    newPopulation.add(individualB);
                }
            }

            population = newPopulation;

            calculateFitness();

            // Check if current generation best solution is also best globally
            List<Individual> sortedIndividuals = copyAndSortPopulation(population).getIndividuals();
            if (sortedIndividuals.get(0).getFitness() < bestSolution.getFitness()) {
                //If it is, store it
                bestSolution = new Individual(sortedIndividuals.get(0));
            }

            if (Constants.SHOULD_LOG_CHART_DATA) {
                log(i, sortedIndividuals);
            }
        }
    }

    public Individual getBestSolution() {
        return bestSolution;
    }

    /**
     * Creates a population
     */
    private void initializePopulation() {
        population = new Population(populationSize, N);
    }

    /**
     * Calculates each genotype's fitness
     */
    private void calculateFitness() {
        for (Individual individual : population.getIndividuals()) {
            int fitness = FitnessCalculator.calculateFitness(N, flowMatrix, distanceMatrix, individual.getGenes());
            individual.setFitness(fitness);
        }
    }

    /**
     * Performs tournament method and returns the best individual
     *
     * @return the best individual
     */
    private Individual performTournamentSelection() {
        List<Individual> individuals = population.getIndividuals();
        // Shuffle individuals in order to easily get random entities
        Collections.shuffle(individuals);

        List<Individual> chosenIndividuals = individuals.subList(0, Constants.TOUR);

        // Sort individuals taken from tournament by its fitness
        Collections.sort(chosenIndividuals, new IndividualComparator());

        // First individual in the list is the best
        return individuals.get(0);
    }

    /**
     * Performs roulette wheel spin method
     *
     * @return the best individual
     */
    private Individual performRouletteWheelSpinSelection() {
        List<Individual> individuals = population.getIndividuals();

        for (Individual individual : individuals) {
            double scaledFitness = (double)1 / individual.getFitness() * 1000000;
            individual.setFitness((int) scaledFitness);
        }
        int totalFitness = 0;

        for (Individual individual : individuals) {
            totalFitness += individual.getFitness();
        }

        int r = random.nextInt(totalFitness);

        int currentCount = 0;

        for (Individual individual : individuals) {
            currentCount += individual.getFitness();
            if (currentCount >= r) {
                return individual;
            }
        }

        return null;
    }

    /**
     * Prints out generation summary based on previously sorted individuals
     *
     * @param generationNumber  generation number
     * @param sortedIndividuals sorted individuals
     */
    private void log(int generationNumber, List<Individual> sortedIndividuals) {
        int length = sortedIndividuals.size();

        int sum = 0;

        for (Individual individual : sortedIndividuals) {
            sum += individual.getFitness();
        }

        int avg = (int) ((double) sum / (double) length);

        System.out.println(generationNumber + "," + sortedIndividuals.get(0).getFitness() + "," + avg + "," + sortedIndividuals.get(length - 1).getFitness());
    }

    /**
     * Copies current population and sorts it
     *
     * @return new instance of a sorted population
     */
    private Population copyAndSortPopulation(Population population) {
        Population populationCopy = new Population(population);
        populationCopy.sort();
        return populationCopy;
    }
}

