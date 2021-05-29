import java.util.*;

public class MLR {

    public static void main(String[] args) {

        double x_1 = Double.parseDouble(args[0]);
        double x_2 = Double.parseDouble(args[1]);

        double Y[][] = {{251.3}, {251.3}, {248.3}, {267.5}, {273.0}, {276.5}, {270.3}, {274.9}, {285.0}, {290.0}, {297.0}, {302.5}, {304.5}, {309.3}, {321.7}, {330.7}, {349.0}};
        double X[][] = {
            {1, 41.9, 29.1},
            {1, 43.4, 29.3},
            {1, 43.9, 29.5},
            {1, 44.5, 29.7},
            {1, 47.3, 29.9},
            {1, 47.5, 30.3},
            {1, 47.9, 30.5},
            {1, 50.2, 30.7},
            {1, 52.8, 30.8},
            {1, 53.2, 30.9},
            {1, 56.7, 31.5},
            {1, 57.0, 31.7},
            {1, 63.5, 31.9},
            {1, 65.3, 32.0},
            {1, 71.1, 32.1},
            {1, 77.0, 32.5},
            {1, 77.8, 32.9}            
        };

        /*
        double Y[][] = {{250}, {220}, {200}, {350}, {210}, {205}, {285}, {190}};
        double X[][] = {
            {1, 76, 80, 13.5},
            {1, 61, 72, 12.1},
            {1, 50, 70, 11.6},
            {1, 94, 122, 12.5},
            {1, 55, 75, 13.5},
            {1, 61, 95, 14},
            {1, 80, 120, 12.5},
            {1, 52, 68, 14.5}
        };
        */

        double X_t[][] = transpose(X);
        double B[][] = matrixMultiplication(invertible(matrixMultiplication(X_t, X)),matrixMultiplication(X_t, Y));

        double B_0 = B[0][0];
        double B_1 = B[1][0];
        double B_2 = B[2][0];

        double y = B_0 + B_1 * x_1 + B_2 * x_2;

        System.out.println("Input x_1: " + x_1);
        System.out.println("Input x_2: " + x_2);
        System.out.println("MLR function: y = " + B_0 + " + " + B_1 + "x_1 + " + B_2 + "x_2");
        System.out.println("Output y: " + y);
    }

    private static double[][] transpose(double matrix[][]) {
        
        int rows = matrix.length;
        int cols = matrix[0].length;

        double newMatrix[][] = new double[cols][rows];

        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                newMatrix[j][i] = matrix[i][j];
            }
        }

        return newMatrix;
    }

    private static double[][] matrixMultiplication(double A[][], double B[][]) {

        int A_rows = A.length;
        int A_cols = A[0].length;
        int B_rows = B.length;
        int B_cols = B[0].length;

        double newMatrix[][] = new double[A_rows][B_cols];

        for(int i=0; i<A_rows; i++) {
            for(int k=0; k<B_cols; k++) {
                double sum = 0;
                for(int j=0; j<A_cols; j++) {
                    sum += (A[i][j] * B[j][k]);
                }
                newMatrix[i][k] = sum;
            }
        }

        return newMatrix;
    }

    private static double[][] invertible(double matrix[][]) {

        int n = matrix.length;
        double identity_matrix[][] = getIdentityMatrix(n);

        for(int k=0; k<n; k++) {
            double inverse = 1/matrix[k][k];
            for(int j=0; j<n; j++) {
                matrix[k][j] *= inverse;
                identity_matrix[k][j] *= inverse;
            }

            for(int i=0; i<n; i++) {
                if(i != k) {
                    double negative = matrix[i][k] * -1;
                    for(int j=0; j<n; j++) {
                        matrix[i][j] += (negative * matrix[k][j]);
                        identity_matrix[i][j] += (negative * identity_matrix[k][j]);
                    }
                }
            }
        }

        return identity_matrix;
    }

    private static double[][] getIdentityMatrix(int n) {

        double identity_matrix[][] = new double[n][n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(i == j) {
                    identity_matrix[i][j] = 1;
                }
                else {
                    identity_matrix[i][j] = 0;
                }
            }
        }

        return identity_matrix;
    }

    private static void printMatrix(double matrix[][]) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println("");
        }

    }
}