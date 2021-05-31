package examples.behaviours;

import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MLRAgentGui extends JFrame {

    private MLRAgent myAgent;

    private JTextField x_1Field;
    private JTextField x_2Field;
    private JTextField methodField;

    MLRAgentGui(MLRAgent a) {

        super(a.getLocalName());

        myAgent = a;

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(6, 1));
        p.add(new JLabel("Enter x_1:"));
        x_1Field = new JTextField(15);
        p.add(x_1Field);

        p.add(new JLabel("Enter x_2:"));
        x_2Field = new JTextField(15);
        p.add(x_2Field);

        p.add(new JLabel("Enter method:"));
        methodField = new JTextField(25);
        p.add(methodField);

        getContentPane().add(p, BorderLayout.CENTER);

        JButton addButton = new JButton("Send");
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
                    String x_1 = x_1Field.getText().trim();
                    String x_2 = x_2Field.getText().trim();
                    String method = methodField.getText().trim();
                    myAgent.getInput(Double.parseDouble(x_1), Double.parseDouble(x_2), method);
                    methodField.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(MLRAgentGui.this, "Invalid value. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );

        p = new JPanel();
		p.add(addButton);
		getContentPane().add(p, BorderLayout.SOUTH);

        // Make the agent terminate when the user closes 
		// the GUI using the button on the upper right corner	
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAgent.doDelete();
			}
		} );
		
		setResizable(false);
    }

    public void showGui() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		super.setVisible(true);
	}

}