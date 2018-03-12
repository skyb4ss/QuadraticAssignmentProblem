package greedy;

import geneticAlgorithm.Individual;
import tools.FitnessCalculator;
import tools.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GreedyAlgorithm {
    private static Random random = new Random();
    private List<Integer> visitedLocations;

    private int N;
    private int[][] flowMatrix, distanceMatrix;


    public GreedyAlgorithm(int n, int[][] flowMatrix, int[][] distanceMatrix) {
        N = n;
        this.flowMatrix = flowMatrix;
        this.distanceMatrix = distanceMatrix;
    }

    public Individual run() {
        visitedLocations = new ArrayList<>();
        Integer currentLocation = random.nextInt(N);
        visitedLocations.add(currentLocation);

        //dla kazdej kolejnej fabryki
        for (int i = 0; i < N - 1; i++) {
            List<Pair<Integer, Integer>> location_cost = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                if (!visitedLocations.contains(j)) {
                    int cost = flowMatrix[currentLocation][j] * distanceMatrix[currentLocation][j] + flowMatrix[j][currentLocation] * distanceMatrix[j][currentLocation];
                    location_cost.add(new Pair<>(j, cost));
                }
            }
            int minIndex = 0;

            //szukam lokalizacji fabryki do ktorej koszt jest najmniejszy
            for (int ind = 0; ind < location_cost.size(); ind++) {
                int currentCost = location_cost.get(ind).b;
                if (currentCost < location_cost.get(minIndex).b) {
                    minIndex = ind;
                }
            }
            //dodaje go do odwiedzionych lokalizacji
            visitedLocations.add(location_cost.get(minIndex).a);
            //ustawiam lokazliacje jako akutalna
            currentLocation = location_cost.get(minIndex).a;
        }

        Individual solution = new Individual(visitedLocations);
        solution.setFitness(FitnessCalculator.calculateFitness(N, flowMatrix, distanceMatrix, visitedLocations));

        return solution;

        /*
        int cost =0;
        cost += flowMatrix[2][9] * distanceMatrix[2][9] + flowMatrix[9][2] * distanceMatrix[9][2];
        cost += flowMatrix[9][10] * distanceMatrix[9][10] + flowMatrix[10][9] * distanceMatrix[10][9];
        cost += flowMatrix[10][1] * distanceMatrix[10][1] + flowMatrix[1][10] * distanceMatrix[1][10];
        cost += flowMatrix[1][11] * distanceMatrix[1][11] + flowMatrix[11][1] * distanceMatrix[11][1];
        cost += flowMatrix[11][4] * distanceMatrix[11][4] + flowMatrix[4][11] * distanceMatrix[4][11];
        cost += flowMatrix[4][5] * distanceMatrix[4][5] + flowMatrix[5][4] * distanceMatrix[5][4];
        cost += flowMatrix[5][6] * distanceMatrix[5][6] + flowMatrix[6][5] * distanceMatrix[6][5];
        cost += flowMatrix[6][7] * distanceMatrix[6][7] + flowMatrix[7][6] * distanceMatrix[7][6];
        cost += flowMatrix[7][0] * distanceMatrix[7][0] + flowMatrix[0][7] * distanceMatrix[0][7];
        cost += flowMatrix[0][3] * distanceMatrix[0][3] + flowMatrix[3][0] * distanceMatrix[3][0];
        cost += flowMatrix[3][8] * distanceMatrix[3][8] + flowMatrix[8][3] * distanceMatrix[8][3];
        System.out.println(cost);
        */
    }
}
