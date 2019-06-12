package utils;

import java.util.List;

public class FitnessCalculator {

    public static int calculateFitness(QAPData qapData, List<Integer> genes) {
        int cost = 0;

        int N = qapData.getN();
        int[][] flowMatrix = qapData.getFlowMatrix();
        int[][] distanceMatrix = qapData.getDistanceMatrix();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cost += flowMatrix[i][j] * distanceMatrix[genes.get(i)][genes.get(j)];
            }
        }

        return cost;
    }


}

