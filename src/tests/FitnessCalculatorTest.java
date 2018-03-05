package tests;

import org.junit.Test;
import tools.DataReader;
import tools.FitnessCalculator;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FitnessCalculatorTest {
    DataReader dataReader = new DataReader();

    @Test
    public void fitnessShouldBeCorrect() {
        dataReader.readData("dataFiles/had12.dat");

        int N = dataReader.getN();
        int[][] flowMatrix = dataReader.getFlowMatrix();
        int[][] distanceMatrix = dataReader.getDistanceMatrix();

        List<Integer> solution = Arrays.asList(2, 9, 10, 1, 11, 4, 5, 6, 7, 0, 3, 8);
        assertEquals(1652, FitnessCalculator.calculateFitness(N, flowMatrix, distanceMatrix, solution));
    }
}
