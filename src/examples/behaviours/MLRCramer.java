package examples.behaviours;

import java.util.*;

public class MLRCramer {

    private double Y[] = {251.3, 248.3, 267.5};
    private double X[][] = {
        {1, 43.4, 29.3},
        {1, 43.9, 29.5},
        {1, 44.5, 29.7}
    };
    public double B_0;
    public double B_1;
    public double B_2;

    public MLRCramer() {
        
        double B[] = cramer(X, Y);

        B_0 = B[0];
        B_1 = B[1];
        B_2 = B[2];
    }

    public double getY(double x_1, double x_2) {
        return B_0 + B_1 * x_1 + B_2 * x_2;
    }

    private static double[] cramer(double X[][], double Y[]) {

        double results[] = new double[3];

        double Ds = systemDeterminant3x3(X);
        double Dx_1 = systemDeterminant3x3(replace(copyMatrix(X), Y, 0));
        double Dx_2 = systemDeterminant3x3(replace(copyMatrix(X), Y, 1));
        double Dx_3 = systemDeterminant3x3(replace(copyMatrix(X), Y, 2));

        double x_1 = Dx_1/Ds;
        double x_2 = Dx_2/Ds;
        double x_3 = Dx_3/Ds;

        results[0] = x_1;
        results[1] = x_2;
        results[2] = x_3;

        return results;
    }

    private static double systemDeterminant3x3(double matrix[][]) {
        
        double res1 = 0, res2 = 0;

        for(int i=0; i<matrix.length; i++) {
            
            double temp1, temp2;
            
            temp1 = matrix[i][0] * matrix[(i+1)%3][1] * matrix[(i+2)%3][2];
            temp2 = matrix[i][2] * matrix[(i+1)%3][1] * matrix[(i+2)%3][0];

            res1 += temp1;
            res2 += temp2;
        }

        return res1 - res2;
    }

    private static double[][] replace(double matrix[][], double array[], int index) {

        for(int i=0; i<matrix.length; i++) {
            matrix[i][index] = array[i];
        }

        return matrix;
    }

    private static double[][] copyMatrix(double matrix[][]) {

        int n = matrix.length;

        double newMatrix[][] = new double[n][n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }

        return newMatrix;
    }

    private static void printMatrix(double matrix[][]) {

        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix.length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println("");
        }

    }
}