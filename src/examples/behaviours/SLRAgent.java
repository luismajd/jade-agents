package examples.behaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class SLRAgent extends Agent {

  private SLRAgentGui myGui;

  private double B_0;
  private double B_1;

  protected void setup() {

    myGui = new SLRAgentGui(this);
    myGui.showGui();

    System.out.println("Agent "+getLocalName()+" started.");

    addBehaviour(new MyOneShotBehaviour());

  } 

  public void getInput(double x) {

        double y = B_0 + B_1 * x;

        System.out.println("Input x: " + x);
        System.out.println("Output y: " + y);
    }

  public class MyOneShotBehaviour extends OneShotBehaviour {

    private double summation(double values[]) {

        double sum = 0;

        for(int i=0; i<values.length; i++) {
            sum += values[i];
        }

        return sum;
    }

    private double xySummation(double X[], double Y[]) {

        double sum = 0;

        for(int i=0; i<X.length; i++) {
            sum += X[i]*Y[i];
        }

        return sum;
    }

    public void calculateSLR() {
        double X[] = {23, 26, 30, 34, 43, 48, 52, 57, 58};
        double Y[] = {651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518};

        int n = X.length;
        double x_summ = summation(X);
        double y_summ = summation(Y);
        double xy_summ = xySummation(X,Y);
        double x_squared_summ = xySummation(X,X);

        B_1 = (n*xy_summ - x_summ * y_summ)/(n*x_squared_summ - x_summ * x_summ);

        B_0 = (y_summ - B_1 * x_summ)/n;

        System.out.println("SLR function: y = " + B_0 + " + " + B_1 + "x");
    }

    public void action() {
        calculateSLR();
    } 
    
    public int onEnd() {
      myAgent.doDelete();   
      return super.onEnd();
    } 
  }    // END of inner class ...Behaviour
}
