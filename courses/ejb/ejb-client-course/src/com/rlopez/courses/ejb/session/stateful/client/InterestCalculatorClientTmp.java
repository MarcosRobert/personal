/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rlopez.courses.ejb.session.stateful.client;

import com.rlopez.courses.ejb.session.stateful.ejb.InterestCalculator;
import com.rlopez.courses.ejb.session.stateful.ejb.InterestCalculatorEJB;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
// Java core libraries
import java.awt.event.*;
import java.rmi.*;
import java.text.*;
import java.util.*;

// Java standard extensions
import javax.rmi.*;
import javax.naming.*;
import javax.ejb.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class InterestCalculatorClientTmp extends JFrame {
	// InterestCalculator remote reference

	private InterestCalculator calculator;

	private JTextField principalTextField;
	private JTextField rateTextField;
	private JTextField termTextField;
	private JTextField balanceTextField;
	private JTextField interestEarnedTextField;

	// InterestCalculatorClient constructor
	public InterestCalculatorClientTmp() {
		super("Stateful Session EJB Example");
		initComponents();
	} // end InterestCalculatorClient constructor

	private void initComponents() {
		// create InterestCalculator for calculating interest
		createInterestCalculator();

		// create JTextField for entering principal amount
		createPrincipalTextField();

		// create JTextField for entering interest rate
		createRateTextField();

		// create JTextField for entering loan term
		createTermTextField();

		// create uneditable JTextFields for displaying balance
		// and interest earned
		balanceTextField = new JTextField();
		balanceTextField.setEditable(false);

		interestEarnedTextField = new JTextField();
		interestEarnedTextField.setEditable(false);
		// layout components for GUI
		layoutGUI();

		// add WindowListener to remove EJB instances when user
		// closes window
		addWindowListener(getWindowListener());

		setSize(new Dimension(425, 200));
		pack();
	}
	// create InterestCalculator EJB instance
	public void createInterestCalculator() {
		// lookup InterestCalculatorHome and create
		// InterestCalculator EJB
		try {

			// create InterestCalculator EJB instance
			calculator = lookupRemoteStatefulInterestCalculator();

		} // end try
		// handle exception if InterestCalculator EJB not found
		catch (NamingException namingException) {
			namingException.printStackTrace();
		}
	} // end method createInterestCalculator

	// create JTextField for entering principal amount
	public void createPrincipalTextField() {
		principalTextField = new JTextField();

		principalTextField.addActionListener(
				  new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// set principal amount in InterestCalculator
				try {
					double principal = Double.parseDouble(
							  principalTextField.getText());

					calculator.setPrincipal(principal);
				} // handle exception setting principal amount
				catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				} // handle wrong number format
				catch (NumberFormatException numberFormatException) {
					numberFormatException.printStackTrace();
				}
			}
		}
		); // end addActionListener
	} // end method createPrincipalTextField

	// create JTextField for entering interest rate
	public void createRateTextField() {
		rateTextField = new JTextField();

		rateTextField.addActionListener(
				  new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				// set interest rate in InterestCalculator
				try {
					double rate = Double.parseDouble(
							  rateTextField.getText());

					// convert from percentage
					calculator.setInterestRate(rate / 100.0);
				} // handle exception when setting interest rate
				catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
			}
		}
		); // end addActionListener
	} // end method createRateTextField

	// create JTextField for entering loan term
	public void createTermTextField() {
		termTextField = new JTextField();

		termTextField.addActionListener(
				  new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// set loan term in InterestCalculator
				try {
					int term = Integer.parseInt(
							  termTextField.getText());

					calculator.setTerm(term);
				} // handle exception when setting loan term
				catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
			}
		}
		); // end addActionListener
	} // end method getTermTextField

	// get JButton for starting calculation
	public JButton getCalculateButton() {
		JButton calculateButton = new JButton("Calculate");

		calculateButton.addActionListener(
				  new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				// use InterestCalculator to calculate interest
				try {

					// get balance and interest earned
					double balance = calculator.getBalance();
					double interest
							  = calculator.getInterestEarned();

					NumberFormat dollarFormatter
							  = NumberFormat.getCurrencyInstance(
										 Locale.US);

					balanceTextField.setText(
							  dollarFormatter.format(balance));

					interestEarnedTextField.setText(
							  dollarFormatter.format(interest));
				} // handle exception when calculating interest
				catch (RemoteException remoteException) {
					remoteException.printStackTrace();
				}
			} // end method actionPerformed
		}
		); // end addActionListener

		return calculateButton;

	} // end method getCalculateButton

	// lay out GUI components in JFrame
	public void layoutGUI() {
		Container contentPane = getContentPane();

		// layout user interface components
		JPanel inputPanel = new JPanel(new GridLayout(5,2));
		inputPanel.add(new JLabel("Principal"));
		inputPanel.add(principalTextField);

		inputPanel.add(new JLabel("Interest Rate (%)"));
		inputPanel.add(rateTextField);

		inputPanel.add(new JLabel("Term (years)"));
		inputPanel.add(termTextField);

		inputPanel.add(new JLabel("Balance"));
		inputPanel.add(balanceTextField);

		inputPanel.add(new JLabel("Interest Earned"));
		inputPanel.add(interestEarnedTextField);

		// add inputPanel to contentPane
		contentPane.add(inputPanel, BorderLayout.CENTER);

		// create JPanel for calculateButton
		JPanel controlPanel = new JPanel(new FlowLayout());
		controlPanel.add(getCalculateButton());
		contentPane.add(controlPanel, BorderLayout.SOUTH);
	}

	// get WindowListener for exiting application
	public WindowListener getWindowListener() {
		return new WindowAdapter() {

			public void windowClosing(WindowEvent event) {
				// check to see if calculator is null
				if (calculator.equals(null)) {
					System.exit(-1);
				} else {
					// remove InterestCalculator instance
					System.exit(0);
				}
			}
		};
	} // end method getWindowListener

	// execute the application
	public static void main(String[] args) {
		System.out.println("hola");
		System.out.println("hola");
		
		try {
			System.out.println("hola");
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			
		} catch (InstantiationException ex) {
			
		} catch (IllegalAccessException ex) {
			
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new InterestCalculatorClientTmp().setVisible(true);
			}
		});
	}
	
	    /**
     * Looks up and returns the proxy to remote stateful counter bean
     *
     * @return
     * @throws NamingException
     */
    private static InterestCalculator lookupRemoteStatefulInterestCalculator() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);
        // The app name is the application name of the deployed EJBs. This is typically the ear name
        // without the .ear suffix. However, the application name could be overridden in the application.xml of the
        // EJB deployment on the server.
        // Since we haven't deployed the application as a .ear, the app name for us will be an empty string
        final String appName = "";
        // This is the module name of the deployed EJBs on the server. This is typically the jar name of the
        // EJB deployment, without the .jar suffix, but can be overridden via the ejb-jar.xml
        // In this example, we have deployed the EJBs in a jboss-as-ejb-remote-app.jar, so the module name is
        // jboss-as-ejb-remote-app
        final String moduleName = "ejb-course-1.0-SNAPSHOT";
        // AS7 allows each deployment to have an (optional) distinct name. We haven't specified a distinct name for
        // our EJB deployment, so this is an empty string
        final String distinctName = "";
        // The EJB name which by default is the simple class name of the bean implementation class
        final String beanName = InterestCalculatorEJB.class.getSimpleName();
        // the remote view fully qualified class name
        final String viewClassName = InterestCalculator.class.getName();
        // let's do the lookup (notice the ?stateful string as the last part of the jndi name for stateful bean lookup)
        return (InterestCalculator) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName + "?stateful");
    }
}
