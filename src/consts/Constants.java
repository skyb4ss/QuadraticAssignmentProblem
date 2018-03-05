package consts;

public final class Constants {
    public static String DATA_FILENAME = "had12.dat";
    public static String LOG_FILENAME = "ruletkamutacja002.csv";

    public static boolean ROULETTE_SELECTION_METHOD = false; // if false, default selection method is a tournament
    public static boolean SHOULD_LOG = true;

    public static int POPULATION_SIZE = 50;
    public static int TOUR = 7;
    public static double CROSSOVER_PROBABILITY = 0.75;
    public static double MUTATION_PROBABILITY = 0.03;
    public static int GENERATIONS = 100;
}
