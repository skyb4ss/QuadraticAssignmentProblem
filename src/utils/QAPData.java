package utils;

public class QAPData {
    private int N;
    private int[][] flowMatrix;
    private int[][] distanceMatrix;

    QAPData(int n, int[][] flowMatrix, int[][] distanceMatrix) {
        N = n;
        this.flowMatrix = flowMatrix;
        this.distanceMatrix = distanceMatrix;
    }

    public int getN() {
        return N;
    }

    public int[][] getFlowMatrix() {
        return flowMatrix;
    }

    public int[][] getDistanceMatrix() {
        return distanceMatrix;
    }
}
