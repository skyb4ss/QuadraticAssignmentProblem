package main;

import consts.Constants;
import geneticAlgorithm.GeneticAlgorithm;
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
        //har12 1652, had20 6992
        List<String> dataFilenames = Arrays.asList("had12.dat", "had14.dat", "had16.dat", "had18.dat", "had20.dat");
        String selectionMethod = "tournament";

        List<Double> crossoverProbabilities = Arrays.asList(0.0, 0.7, 0.99);
        List<Double> mutationProbabilities = Arrays.asList(0.0, 0.02, 0.05);
        List<Integer> populationSizes = Arrays.asList(25, 50, 75);

        int takes = 10;

        for (String filename : dataFilenames) {
            loadData(filename);

            for (int i = 1; i <= takes; i++) {
                try {
                    String directory = "logs/" + filename + "/" + selectionMethod + "/cross_" + Constants.CROSSOVER_PROBABILITY + "_mut_" + Constants.MUTATION_PROBABILITY + "_pop_" + Constants.POPULATION_SIZE;
                    File dir = new File(directory);
                    dir.mkdirs();

                    File file = new File(directory + "/take_" + i + ".csv");
                    FileOutputStream logFileStream = new FileOutputStream(file);
                    PrintStream logFilePrintStream = new PrintStream(logFileStream);
                    System.setOut(logFilePrintStream);

                    writeLogFileHeader();
                } catch (FileNotFoundException e) {
                    LOGGER.log(Level.SEVERE, "File not found", e);
                }
                GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(N, flowMatrix, distanceMatrix, Constants.CROSSOVER_PROBABILITY, Constants.MUTATION_PROBABILITY, Constants.POPULATION_SIZE, selectionMethod);

                geneticAlgorithm.run();
                System.setOut(CONSOLE);
                System.out.println(geneticAlgorithm.getBestSolution().printWithIndicesNumberedFrom1());
            }
        }


    /*
        try {
            if (Constants.SHOULD_LOG) {
                File file = new File("logs/" + Constants.LOG_FILENAME);
                FileOutputStream logFileStream = new FileOutputStream(file);
                PrintStream logFilePrintStream = new PrintStream(logFileStream);
                System.setOut(logFilePrintStream);

                writeLogFileHeader();
            }

            loadData();

            GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(N, flowMatrix, distanceMatrix,0.7,0.02,50);

            geneticAlgorithm.run();

            System.setOut(CONSOLE);
            System.out.println(geneticAlgorithm.getBestSolution().printWithIndicesNumberedFrom1());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during running main", e);
        }
        */
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

    /**
     * Loads required data
     */
    private static void loadData() {
        DataReader dataReader = new DataReader();

        dataReader.readData("dataFiles/" + Constants.DATA_FILENAME);

        N = dataReader.getN();
        flowMatrix = dataReader.getFlowMatrix();
        distanceMatrix = dataReader.getDistanceMatrix();
    }

    private static void writeLogFileHeader() {
        System.out.println("sep=,");
        System.out.println("nr_pokolenia, najlepsza_ocena, srednia_ocen, najgorsza_ocena");
    }

}
