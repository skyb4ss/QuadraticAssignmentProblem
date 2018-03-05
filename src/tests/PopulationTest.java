package tests;

import geneticAlgorithm.Individual;
import geneticAlgorithm.Population;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PopulationTest {

    @Test
    public void defaultConstructorShouldCreateEmptyPopulation() {
        Population population = new Population();
        assertEquals(0, population.getIndividuals().size());
    }

    @Test
    public void PopulationWithNSizeShouldHaveNIndividuals() {
        Population population = new Population(10, 12);
        for (Individual individual : population.getIndividuals()) {
            assertNotNull(individual);
        }
    }
}
