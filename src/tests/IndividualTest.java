package tests;

import geneticAlgorithm.Individual;
import geneticAlgorithm.QAPIndividual;
import org.junit.Test;

import static org.junit.Assert.*;

public class IndividualTest {
    @Test
    public void newIndividualShouldHaveNonEmptyListOfGenes() {
        Individual individual = new QAPIndividual(5);

        for (Integer i : ((QAPIndividual) individual).getGenes()) assertNotNull(i);
    }

    @Test
    public void newIndividualShouldHaveZeroFitness() {
        Individual individual = new QAPIndividual(1);

        assertEquals(0, individual.getFitness());
    }

    @Test
    public void individualCloneShouldBeDifferentInstance() {
        Individual individual = new QAPIndividual(12);
        Individual clone = new QAPIndividual((QAPIndividual) individual);

        assertNotSame(individual, clone);
    }

    @Test
    public void individualCloneShouldHaveTheSameProperties() {
        Individual individual = new QAPIndividual(12);
        Individual clone = new QAPIndividual((QAPIndividual) individual);

        assertEquals(individual, clone);
    }
}
