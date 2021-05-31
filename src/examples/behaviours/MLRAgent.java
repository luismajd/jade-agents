package examples.behaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class MLRAgent extends Agent {

    private MLRAgentGui myGui;

    private MLRCramer cramer;
    private MLRMatricialAlgebra matricialAlgebra;

    private double B_0c;
    private double B_1c;
    private double B_2c;

    private double B_0ma;
    private double B_1ma;
    private double B_2ma;

    protected void setup() {

        myGui = new MLRAgentGui(this);
        myGui.showGui();

        System.out.println("Agent "+getLocalName()+" started.");

        addBehaviour(new MyOneShotBehaviour());

    }

    public void getInput(double x_1, double x_2, String method) {

        double y;

        if(method.equals("cramer")) {
            y = B_0c + B_1c * x_1 + B_2c * x_2;
            System.out.println("MLR function: y = " + B_0c + " + " + B_1c + "x_1 + " + B_2c + "x_2");
        }
        else if(method.equals("matricial algebra")) {
            y = B_0ma + B_1ma * x_1 + B_2ma * x_2;
            System.out.println("MLR function: y = " + B_0ma + " + " + B_1ma + "x_1 + " + B_2ma + "x_2");
        }
        else {
            System.out.println("Error: Invalid method parameter.");
            return;
        }

        System.out.println("Method: " + method);
        System.out.println("Input x_1: " + x_1);
        System.out.println("Input x_2: " + x_2);
        System.out.println("Output y: " + y);
    }

    public class MyOneShotBehaviour extends OneShotBehaviour {

        public void calculateMLR() {

            cramer = new MLRCramer();
            B_0c = cramer.B_0;
            B_1c = cramer.B_1;
            B_2c = cramer.B_2;

            matricialAlgebra = new MLRMatricialAlgebra();
            B_0ma = matricialAlgebra.B_0;
            B_1ma = matricialAlgebra.B_1;
            B_2ma = matricialAlgebra.B_2;
        }

        public void action() {
            calculateMLR();
        } 
        
        public int onEnd() {
            myAgent.doDelete();  
            //myGui.dispose();
            System.out.println("MLR Agent "+getLocalName()+" terminating.");
            return super.onEnd();
        }

    }

}