package tests;

import org.junit.Test;
import utils.QAPData;
import utils.QAPDataReader;
import utils.CostCalculator;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CostCalculatorTest {
    @Test
    public void fitnessShouldBeCorrect() throws FileNotFoundException {
        QAPData data = QAPDataReader.readData("dataFiles/had12.dat");

        List<Integer> solution = Arrays.asList(2, 9, 10, 1, 11, 4, 5, 6, 7, 0, 3, 8);
        assertEquals(1652, CostCalculator.calculateCost(data, solution));
    }
}
