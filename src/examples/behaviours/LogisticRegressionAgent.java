package examples.behaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class LogisticRegressionAgent extends Agent {

  private LogisticRegressionGui myGui;

  double W[];

  protected void setup() {

    myGui = new LogisticRegressionGui(this);
    myGui.showGui();

    System.out.println("Agent "+getLocalName()+" started.");

    addBehaviour(new MyOneShotBehaviour());

  }

  private void printArray(double arr[]) {
      for(int i=0; i<arr.length; i++) {
          System.out.print(arr[i] + " ");
      }
      System.out.println();
  }

  private double[] getX_new(String inputs[]) {
      int n = inputs.length;
      double X_new[] = new double[n];

      for(int i=0; i<n; i++) {
          X_new[i] = Double.parseDouble(inputs[i]);
      }

      return X_new;
  }

  private double[] gradientDescent(double X[][], double Y[], double a, int M) {
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

  private double f(double X[][], double W[], int i) {
      double sum = W[0];
      int d = X.length;
      
      for(int j=1; j<d; j++) {
          sum += W[j] * X[j][i];
      }

      double p = 1/(1+Math.pow(Math.E, -sum));

      return p;
  }

  private double f(double X[], double W[]) {
      double sum = W[0];
      int d = X.length;
      
      for(int j=1; j<d; j++) {
          sum += W[j] * X[j];
      }

      double p = 1/(1+Math.pow(Math.E, -sum));

      return p;
  }

  private double[] initializeW(int d) {
      double W[] = new double[d];

      for(int i=0; i<d; i++) {
          W[i] = 0;
      }

      return W;
  }

  public void getInputs(String inputs[]) {
      int n = inputs.length;
      double X_new[] = new double[n];

      for(int i=0; i<n; i++) {
        X_new[i] = Double.parseDouble(inputs[i]);
      }

      calculateClass(X_new, W);
  }

  private void calculateClass(double X_new[], double W[]) {
      double p = f(X_new, W);
      Boolean Y_new = p > 0.5;

      System.out.println("p: " + p + "; Y: " + Y_new);
  }

  protected void calculateWeights() {

      double X[][] = {
          {1, 1, 1},
          {1, 4, 2},
          {1, 2, 4}
      };
      double Y[] = {0, 1, 1};
      double a = 0.1;
      int M = 100;

      W = gradientDescent(X,Y,a,M);
  }

    

  public class MyOneShotBehaviour extends OneShotBehaviour {

    public void action() {
        calculateWeights();
    } 
    
    public int onEnd() {
      myAgent.doDelete();   
      return super.onEnd();
    } 
  }    // END of inner class ...Behaviour
}
