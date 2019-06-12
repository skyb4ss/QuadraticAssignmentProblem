package tests;

import geneticAlgorithm.Individual;
import geneticAlgorithm.Population;
import geneticAlgorithm.QAPPopulation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PopulationTest {

    @Test
    public void defaultConstructorShouldCreateEmptyPopulation() {
        Population population = new QAPPopulation();
        assertEquals(0, ((QAPPopulation) population).getIndividuals().size());
    }
}
