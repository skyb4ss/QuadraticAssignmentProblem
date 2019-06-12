package main;

import consts.Params;
import geneticAlgorithm.GeneticAlgorithm;
import geneticAlgorithm.GeneticAlgorithmParams;
import utils.QAPData;
import utils.QAPDataReader;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String[] dataFileNames = Params.FILE_NAMES;

        for (String dataFileName : dataFileNames) {
            QAPData qapData = readQAPData(dataFileName);

            runGeneticAlgorithm(qapData);
        }
    }

    private static void runGeneticAlgorithm(QAPData qapData) {
        GeneticAlgorithmParams geneticAlgorithmParams = new GeneticAlgorithmParams(qapData,
                Params.CROSSOVER_PROBABILITY,
                Params.MUTATION_PROBABILITY, Params.POPULATION_SIZE);


        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(geneticAlgorithmParams);

        geneticAlgorithm.run();
        System.out.println(geneticAlgorithm.getBestSolution().getFitness());
    }

    private static QAPData readQAPData(String filename) throws FileNotFoundException {
        return QAPDataReader.readData("dataFiles/" + filename);
    }

}
