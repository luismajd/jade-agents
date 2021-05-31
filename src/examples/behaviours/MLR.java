package src.examples.behaviours;

import java.util.*;

public class MLR {

    public static void main(String[] args) {

        String method;
        double B_0;
        double B_1;
        double B_2;
        double x_1 = Double.parseDouble(args[0]);
        double x_2 = Double.parseDouble(args[1]);
        double y;
          
        if(args.length >= 3) {
            if(args[2].equals("-c")) {
                method = "cramer";
                MLRCramer cramer = new MLRCramer();
                y = cramer.getY(x_1, x_2);
                B_0 = cramer.B_0;
                B_1 = cramer.B_1;
                B_2 = cramer.B_2;
            }
            else if(args[2].equals("-m")) {
                method = "matricial algebra";
                MLRMatricialAlgebra matricialAlgebra = new MLRMatricialAlgebra();
                y = matricialAlgebra.getY(x_1, x_2);
                B_0 = matricialAlgebra.B_0;
                B_1 = matricialAlgebra.B_1;
                B_2 = matricialAlgebra.B_2;
            }
            else {
                System.out.println("Error: Invalid method parameter.");
                return;
            }
        }
        else {
            System.out.println("Error: No method parameter has been provided.");
            return;
        }
        System.out.println("Method: " + method);
        System.out.println("Input x_1: " + x_1);
        System.out.println("Input x_2: " + x_2);
        System.out.println("MLR function: y = " + B_0 + " + " + B_1 + "x_1 + " + B_2 + "x_2");
        System.out.println("Output y: " + y);
    }
}