package tools;

import java.util.List;

public class FitnessCalculator {
    public static int calculateFitness(int N, int[][] flowMatrix, int[][] distanceMatrix, List<Integer> genes) {
        int cost = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cost += flowMatrix[i][j] * distanceMatrix[genes.get(i)][genes.get(j)];
            }
        }
        return cost;
    }
}

