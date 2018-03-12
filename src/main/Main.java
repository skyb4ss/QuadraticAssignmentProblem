package main;

import consts.Constants;
import geneticAlgorithm.GeneticAlgorithm;
import geneticAlgorithm.Individual;
import greedy.GreedyAlgorithm;
import tools.DataReader;
import tools.FitnessCalculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final PrintStream CONSOLE = System.out;

    private static int N;
    private static int[][] flowMatrix;
    private static int[][] distanceMatrix;

    public static void main(String[] args) {
        List<String> dataFilenames = Arrays.asList("had12.dat", "had14.dat", "had16.dat", "had18.dat", "had20.dat");
        String selectionMethod = "tournament";

        int takes = 10;

        for (String filename : dataFilenames) {
            loadData(filename);

            System.out.println("Plik " + filename);

            System.out.println("Genetyczny: ");
            for (int take = 1; take <= takes; take++) {
                if (Constants.SHOULD_LOG_CHART_DATA) {
                    try {
                        runGeneticAlgorithmAndLogToFile(filename, selectionMethod, take);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(N, flowMatrix, distanceMatrix, Constants.CROSSOVER_PROBABILITY, Constants.MUTATION_PROBABILITY, Constants.POPULATION_SIZE, selectionMethod);
                    geneticAlgorithm.run();
                    System.out.println(geneticAlgorithm.getBestSolution().getFitness());
                }
            }

            System.out.println("Zachłanny: ");
            for (int take = 1; take <= takes; take++) {
                GreedyAlgorithm greedyAlgorithm = new GreedyAlgorithm(N, flowMatrix, distanceMatrix);
                System.out.println(greedyAlgorithm.run().getFitness());
            }

            System.out.println("Random: ");
            for (int take = 1; take <= takes; take++) {
                Individual individual = new Individual(N);
                System.out.println(FitnessCalculator.calculateFitness(N, flowMatrix, distanceMatrix, individual.getGenes()));
            }
            System.out.println();
        }
    }

    private static void runGeneticAlgorithmAndLogToFile(String filename, String selectionMethod, int take) throws FileNotFoundException {
        String directory = "logs/" + filename + "/" + selectionMethod + "/cross_" + Constants.CROSSOVER_PROBABILITY + "_mut_" + Constants.MUTATION_PROBABILITY + "_pop_" + Constants.POPULATION_SIZE + "_tour_" + Constants.TOUR;
        File dir = new File(directory);
        dir.mkdirs();

        File file = new File(directory + "/take_" + take + ".csv");
        FileOutputStream logFileStream = new FileOutputStream(file);
        PrintStream logFilePrintStream = new PrintStream(logFileStream);
        System.setOut(logFilePrintStream);

        writeLogFileHeader();

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(N, flowMatrix, distanceMatrix, Constants.CROSSOVER_PROBABILITY, Constants.MUTATION_PROBABILITY, Constants.POPULATION_SIZE, selectionMethod);
        geneticAlgorithm.run();

        System.setOut(CONSOLE);
        System.out.println(geneticAlgorithm.getBestSolution().getFitness());
    }


    /**
     * Loads required data
     */
    private static void loadData(String filename) {
        DataReader dataReader = new DataReader();

        dataReader.readData("dataFiles/" + filename);

        N = dataReader.getN();
        flowMatrix = dataReader.getFlowMatrix();
        distanceMatrix = dataReader.getDistanceMatrix();
    }

    private static void writeLogFileHeader() {
        System.out.println("sep=,");
        System.out.println("nr_pokolenia, Najlepsza ocena, Średnia ocena, Najgorsza ocena");
    }

}
