package MultipleLinearRegression;

import java.util.*;

public class MLR {

    public static void main(String[] args) {

        double x_1 = Double.parseDouble(args[0]);
        double x_2 = Double.parseDouble(args[1]);
        String method = args[2];
        System.out.println("Method: " + method);
        if(args.length == 3) {
            if(method.equals("cramer")) {
                MLRCramer mlrCramer = new MLRCramer(x_1, x_2);
            }
            else {
                System.out.println("else");
            }
        }
    }
}