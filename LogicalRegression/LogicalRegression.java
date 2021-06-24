import java.util.*;
import java.lang.Math;

public class LogicalRegression {
    
    public static void main(String[] args) {
        double X[][] = {
            {1, 1, 1},
            {1, 4, 2},
            {1, 2, 4}
        };
        double Y[] = {0, 1, 1};
        double a = 0.1;
        int M = 100;

        double W[];
        double X_new[];
        double p;
        Boolean Y_new;

        X_new = new double[]{1, 3.5, 4};
        W = gradientDescent(X,Y,a,M);
        p = f(X_new, W);
        Y_new = getBooleanResult(p);

        System.out.println("p: " + p + "; Y: " + Y_new);
    }
    
    private static double[] gradientDescent(double X[][], double Y[], double a, int M) {
        int d = X.length;
        int n = Y.length;
        double W[] = initializeW(d);

        for(int m=0; m<=M; m++) {
            System.out.print(m + ": ");
            printArray(W);
            for(int j=0; j<d; j++) {
                double sum = 0;
                for(int i=0; i<n; i++) {
                    sum += (f(X, W, i) - Y[i])*X[j][i];
                }
                W[j] = W[j] - a * sum;
            }
        }

        return W;
    }

    private static double f(double X[][], double W[], int i) {
        double sum = W[0];
        int d = X.length;
        
        for(int j=1; j<d; j++) {
            sum += W[j] * X[j][i];
        }

        double p = 1/(1+Math.pow(Math.E, -sum));

        return p;
    }

    private static double f(double X[], double W[]) {
        double sum = W[0];
        int d = X.length;
        
        for(int j=1; j<d; j++) {
            sum += W[j] * X[j];
        }

        double p = 1/(1+Math.pow(Math.E, -sum));

        return p;
    }

    private static double[] initializeW(int d) {
        double W[] = new double[d];

        for(int i=0; i<d; i++) {
            W[i] = 0;
        }

        return W;
    }

    private static Boolean getBooleanResult(double p) {
        return p > 0.5;
    }

    private static void printArray(double arr[]) {
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}