package utils;

import java.util.List;

public class CostCalculator {

    public static int calculateCost(QAPData qapData, List<Integer> permutation) {
        int cost = 0;

        int N = qapData.getN();
        int[][] flowMatrix = qapData.getFlowMatrix();
        int[][] distanceMatrix = qapData.getDistanceMatrix();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cost += flowMatrix[i][j] * distanceMatrix[permutation.get(i)][permutation.get(j)];
            }
        }

        return cost;
    }


}

