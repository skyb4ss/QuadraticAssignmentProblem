package main;

import geneticAlgorithm.GeneticAlgorithm;
import geneticAlgorithm.QAPGeneticAlgorithm;
import geneticAlgorithm.GeneticAlgorithmParams;
import geneticAlgorithm.Individual;
import utils.QAPData;
import utils.QAPDataReader;

import java.io.FileNotFoundException;

import static consts.Params.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String[] dataFileNames = FILE_NAMES;
        GeneticAlgorithmParams params = getGeneticAlgorithmParams();

        for (String dataFileName : dataFileNames) {
            QAPData qapData = readQAPData(dataFileName);

            Individual solution = runGeneticAlgorithm(qapData, params);
            System.out.println(solution.getFitness());
        }
    }

    private static Individual runGeneticAlgorithm(QAPData qapData, GeneticAlgorithmParams params) {
        GeneticAlgorithm geneticAlgorithm = new QAPGeneticAlgorithm(qapData, params);

        return geneticAlgorithm.findSolution();
    }

    private static QAPData readQAPData(String filename) throws FileNotFoundException {
        return QAPDataReader.readData("dataFiles/" + filename);
    }

    private static GeneticAlgorithmParams getGeneticAlgorithmParams() {
        return new GeneticAlgorithmParams(CROSSOVER_PROBABILITY, MUTATION_PROBABILITY,
                POPULATION_SIZE);
    }

}
