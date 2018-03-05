package tools;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataReader {
    /**
     * Logger
     */
    private static final Logger LOGGER = Logger.getLogger(DataReader.class.getName());

    /**
     * Number of factories read from file
     */
    private int N;

    /**
     * Flow and distance matrices read from file
     */
    private int[][] flowMatrix, distanceMatrix;

    /**
     * Creates data reader instance
     */
    public DataReader() {
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

    /**
     * Reads number of factories and 2 matrices (and stores them as 2d arrays) from a .dat file
     * @param fileName file's name
     */
    public void readData(String fileName) {
        try {
            // Create scanner
            Scanner scanner = new Scanner(new FileInputStream(fileName));

            // Read N
            N = scanner.nextInt();

            // Delcare number of elements stored in the matrix
            int numberOfMatrixElements = N * N;

            // Create the flow matrix
            flowMatrix = new int[N][N];

            // Read first matrix from the file and store it as a flow matrix
            // We count number of elements to be able to detect where we should stop
            int counter = 0;
            while (counter < numberOfMatrixElements) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        flowMatrix[i][j] = scanner.nextInt();
                        counter++;
                    }
                }
            }

            // Create the distance matrix
            distanceMatrix = new int[N][N];

            // Read second matrix from the file and store it as a distance matrix
            counter = 0;
            while (counter < numberOfMatrixElements) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        distanceMatrix[i][j] = scanner.nextInt();
                        counter++;
                    }
                }
            }

            // Close scanner
            scanner.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during reading data", e);
        }
    }

}