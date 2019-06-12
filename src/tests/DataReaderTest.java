package tests;

import org.junit.Test;
import utils.QAPData;
import utils.QAPDataReader;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Krzysztof on 2018-03-02.
 */
public class DataReaderTest {

    @Test
    public void NShouldBe12WhenReadingHad12() throws FileNotFoundException {
        QAPData data = QAPDataReader.readData("dataFiles/had12.dat");
        assertEquals(12, data.getN());
    }

    @Test
    public void flowMatrixShouldNotBeNull() throws FileNotFoundException {
        QAPData data = QAPDataReader.readData("dataFiles/had12.dat");
        assertNotNull(data.getFlowMatrix());
    }

    @Test
    public void distanceMatrixShouldNotBeNull() throws FileNotFoundException {
        QAPData data = QAPDataReader.readData("dataFiles/had12.dat");
        assertNotNull(data.getDistanceMatrix());
    }
}
