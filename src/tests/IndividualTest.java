package tests;

import geneticAlgorithm.Individual;
import org.junit.Test;

import static org.junit.Assert.*;

public class IndividualTest {
    @Test
    public void newIndividualShouldHaveNonEmptyListOfGenes() {
        Individual individual = new Individual(5);

        for (Integer i : individual.getGenes()) {
            assertNotNull(i);
        }
    }

    @Test
    public void newIndividualShouldHaveZeroFitness() {
        Individual individual = new Individual(1);

        assertEquals(0, individual.getFitness());
    }

    @Test
    public void individualCloneShouldBeDifferentInstance() {
        Individual individual = new Individual(12);
        Individual clone = new Individual(individual);

        assertNotSame(individual, clone);
    }

    @Test
    public void individualCloneShouldHaveTheSameProperties() {
        Individual individual = new Individual(12);
        Individual clone = new Individual(individual);

        assertEquals(individual, clone);
    }
}
