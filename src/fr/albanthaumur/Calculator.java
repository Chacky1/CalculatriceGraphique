package fr.albanthaumur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculator extends JFrame{
	
	// This is the main container that takes the contentPane place.
	private JPanel container = new JPanel();
	
	// Here are all the buttons we need for the calculator to work
	String[] tabString = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "=", "C", "+", "-", "*", "/"};
	JButton[] tabButton = new JButton[tabString.length];
	
	
	private JLabel screen = new JLabel();
	// Dimensions of the buttons on the calculator
	private Dimension dim1 = new Dimension(100, 80), dim2 = new Dimension(100, 70);
	// Boolean used as memory of previous actions that can change the screen
	private boolean update = false, operatorSelected = false;
	// Number remembered to do calculations
	private double number = 0;
	// Operator used for a calculation
	private String operator = "";
	
	public Calculator() {
		// Configuration of a few parameters of the program
		this.setTitle("Calculator");
		this.setSize(500, 600);
		this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    // Initialization of the components
	    initialization();
		
	    // Definition of the content pane
		this.setContentPane(container);
		this.setVisible(true);
	}
	
	/**
	 * This function initializes all the components required for the calculator
	 */
	public void initialization() {
		// Initialization of the screen
		Font style = new Font("Arial", Font.BOLD, 20);
		screen = new JLabel("0");
		screen.setFont(style);
		screen.setHorizontalAlignment(JLabel.RIGHT);
		screen.setPreferredSize(new Dimension(450, 100));
		// The JLabel is wrapped in a JPanel
		JPanel panScreen = new JPanel();
		panScreen.setPreferredSize(new Dimension(460, 110));
		panScreen.add(screen);
		panScreen.setBorder(BorderFactory.createLineBorder(Color.black));
		
		// Initialization of the two JPanels, one for numbers, one for operators
		JPanel panNumbers = new JPanel();
		panNumbers.setPreferredSize(new Dimension(330, 450));
		JPanel panOperators = new JPanel();
		panOperators.setPreferredSize(new Dimension(130, 450));
		
		// Initialization of all the buttons
		for (int i = 0; i < tabString.length; i++) {
			tabButton[i] = new JButton(tabString[i]);
			tabButton[i].setPreferredSize(dim1);
			
			switch(i) {
			// Listeners listen to the button "=", "C", "+", "-", "*", "/" 
				case 11:
					tabButton[i].addActionListener(new EqualityListener());
					panNumbers.add(tabButton[i]);
					break;
				case 12:
					tabButton[i].setForeground(Color.red);
					tabButton[i].addActionListener(new ResetListener());
					panOperators.add(tabButton[i]);
					break;
				case 13:
					tabButton[i].addActionListener(new PlusListener());
					tabButton[i].setPreferredSize(dim2);
					panOperators.add(tabButton[i]);
					break;
				case 14:
					tabButton[i].addActionListener(new MinusListener());
					tabButton[i].setPreferredSize(dim2);
					panOperators.add(tabButton[i]);
					break;
				case 15:
					tabButton[i].addActionListener(new MultiplyListener());
					tabButton[i].setPreferredSize(dim2);
					panOperators.add(tabButton[i]);
					break;
				case 16:
					tabButton[i].addActionListener(new DivideListener());
					tabButton[i].setPreferredSize(dim2);
					panOperators.add(tabButton[i]);
					break;
				// Numbers have a own Listener
				default:
					tabButton[i].addActionListener(new NumberListener());
					panNumbers.add(tabButton[i]);
					break;
			}
			
			container.add(panScreen, BorderLayout.NORTH);
			container.add(panNumbers, BorderLayout.CENTER);
			container.add(panOperators, BorderLayout.EAST);
		}
	}
	
	public void calculation() {
		if (operator.equals("+")) {
			number = number + Double.valueOf(screen.getText()).doubleValue();
			screen.setText(String.valueOf(number));
		}
		if (operator.equals("-")) {
			number = number - Double.valueOf(screen.getText()).doubleValue();
			screen.setText(String.valueOf(number));
		}
		if (operator.equals("*")) {
			number = number * Double.valueOf(screen.getText()).doubleValue();
			screen.setText(String.valueOf(number));
		}
		if (operator.equals("/")) {
			try {
				number = number / Double.valueOf(screen.getText()).doubleValue();
				screen.setText(String.valueOf(number));
			} catch (ArithmeticException e) {
				screen.setText("0");
			}
		}
	}
	
	class NumberListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String str = ((JButton)e.getSource()).getText();
			if (update) {
				update = false;
			}
			else {
				if (!screen.getText().equals("0")) {
					str = screen.getText() + str;
				}
			}
			screen.setText(str);
		}
		
	}
	
	class EqualityListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			calculation();
			update = true;
			operatorSelected = false;
		}
		
	}
	
	class ResetListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			update = false;
			operator = "";
			operatorSelected = false;
			number = 0;
			screen.setText("0");
		}
		
	}
	
	class PlusListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			operator = "+";
			if (operatorSelected) {
				calculation();
				screen.setText(String.valueOf(number));
			}
			else {
				number = Double.valueOf(screen.getText()).doubleValue();
				operatorSelected = true;
			}
			operator = "+";
			update = true;
		}
		
	}
	
	class MinusListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			operator = "-";
			if (operatorSelected) {
				calculation();
				screen.setText(String.valueOf(number));
			}
			else {
				number = Double.valueOf(screen.getText()).doubleValue();
				operatorSelected = true;
			}
			operator = "-";
			update = true;
		}
		
	}
	
	class MultiplyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			operator = "*";
			if (operatorSelected) {
				calculation();
				screen.setText(String.valueOf(number));
			}
			else {
				number = Double.valueOf(screen.getText()).doubleValue();
				operatorSelected = true;
			}
			operator = "*";
			update = true;
		}
		
	}
	
	class DivideListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			operator = "/";
			if (operatorSelected) {
				calculation();
				screen.setText(String.valueOf(number));
			}
			else {
				number = Double.valueOf(screen.getText()).doubleValue();
				operatorSelected = true;
			}
			operator = "/";
			update = true;
		}
		
	}
}
