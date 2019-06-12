package tests;

import org.junit.Test;
import utils.QAPData;
import utils.QAPDataReader;
import utils.FitnessCalculator;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FitnessCalculatorTest {
    @Test
    public void fitnessShouldBeCorrect() throws FileNotFoundException {
         QAPData data =  QAPDataReader.readData("dataFiles/had12.dat");

        int N = data.getN();
        int[][] flowMatrix = data.getFlowMatrix();
        int[][] distanceMatrix = data.getDistanceMatrix();

        List<Integer> solution = Arrays.asList(2, 9, 10, 1, 11, 4, 5, 6, 7, 0, 3, 8);
        assertEquals(1652, FitnessCalculator.calculateFitness(N, flowMatrix, distanceMatrix, solution));
    }
}
