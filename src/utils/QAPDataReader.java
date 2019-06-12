package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QAPDataReader {
    private static Scanner scanner;

    /**
     * Reads number of factories, flow matrix and distance matrix from a .dat file
     *
     * @param fileName file's name
     */
    public static QAPData readData(String fileName) throws FileNotFoundException {
        scanner = new Scanner(new FileInputStream(fileName));

        int N = readMatrixSize();
        int[][] flowMatrix = readMatrix(N);
        int[][] distanceMatrix = readMatrix(N);

        scanner.close();

        return new QAPData(N, flowMatrix, distanceMatrix);
    }

    private static int readMatrixSize() {
        return scanner.nextInt();
    }

    private static int[][] readMatrix(int N) {
        int[][] matrix = new int[N][N];
        int allElements = N * N;

        int readElements = 0;
        while (readElements < allElements) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = scanner.nextInt();
                    readElements++;
                }
            }
        }

        return matrix;
    }
}