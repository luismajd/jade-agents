package examples.behaviours;

import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class SLRAgentGui extends JFrame {
    
    private SLRAgent myAgent;

    private JTextField xField;

    SLRAgentGui(SLRAgent a) {
        
        super(a.getLocalName());
		
		myAgent = a;

        JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		p.add(new JLabel("Input x:"));
		xField = new JTextField(15);
		p.add(xField);
		getContentPane().add(p, BorderLayout.CENTER);

        JButton addButton = new JButton("Send");
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
                    String x = xField.getText().trim();
                    myAgent.getInput(Double.parseDouble(x));
					xField.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(SLRAgentGui.this, "Invalid value. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		p = new JPanel();
		p.add(addButton);
		getContentPane().add(p, BorderLayout.SOUTH);

        // Make the agent terminate when the user closes 
		// the GUI using the button on the upper right corner	
		addWindowListener(new	WindowAdapter() {
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