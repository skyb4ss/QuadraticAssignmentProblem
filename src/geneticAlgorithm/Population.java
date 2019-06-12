package geneticAlgorithm;

public interface Population {
    Population evolve();

    Individual getBestIndividual();
}
