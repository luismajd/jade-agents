import java.util.*;

public class SimpleLinearRegression {

    public static void main(String[] args) {

        double x = Double.parseDouble(args[0]);

        double X[] = {23, 26, 30, 34, 43, 48, 52, 57, 58};
        double Y[] = {651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518};

        int n = X.length;
        double x_summ = summation(X);
        double y_summ = summation(Y);
        double xy_summ = xySummation(X,Y);
        double x_squared_summ = xySummation(X,X);

        double B_1 = (n*xy_summ - x_summ * y_summ)/(n*x_squared_summ - x_summ * x_summ);

        double B_0 = (y_summ - B_1 * x_summ)/n;

        double y = B_0 + B_1 * x;

        System.out.println("Input x: " + x);
        System.out.println("SLR function: y = " + B_0 + " + " + B_1 + "x");
        System.out.println("Output y: " + y);
    }

    private static double summation(double values[]) {

        double sum = 0;

        for(int i=0; i<values.length; i++) {
            sum += values[i];
        }

        return sum;
    }

    private static double xySummation(double X[], double Y[]) {

        double sum = 0;

        for(int i=0; i<X.length; i++) {
            sum += X[i]*Y[i];
        }

        return sum;
    }
}