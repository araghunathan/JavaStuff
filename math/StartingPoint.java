package code;

import test.MainTest;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
public class StartingPoint extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Main");
		createForm();
	}

	private static void createForm()
	{
		MainTest tester = new MainTest();
		tester.test();
		JTextField firstName = new JTextField("First Name");
		JTextField lastName = new JTextField("Last Name");
		firstName.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(firstName.getText());
				
			}
			
		});
		JPanel panel = new JPanel(new GridLayout(2,1));		
		panel.add(new JLabel("First Name:"));
		panel.add(firstName);
		panel.add(new JLabel("Last Name:"));
		panel.add(lastName);
		JOptionPane.showConfirmDialog(null, panel, "Test",
		        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		
	}
}