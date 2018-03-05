package tests;

import org.junit.Test;
import tools.DataReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Krzysztof on 2018-03-02.
 */
public class DataReaderTest {
    DataReader dataReader = new DataReader();

    @Test
    public void NShouldBe12WhenReadingHad12() {
        dataReader.readData("dataFiles/had12.dat");

        assertEquals(12, dataReader.getN());
    }

    @Test
    public void flowMatrixShouldNotBeNull() {
        dataReader.readData("dataFiles/had12.dat");

        assertNotNull(dataReader.getFlowMatrix());
    }

    @Test
    public void distanceMatrixShouldNotBeNull() {
        dataReader.readData("dataFiles/had12.dat");

        assertNotNull(dataReader.getDistanceMatrix());
    }
}
