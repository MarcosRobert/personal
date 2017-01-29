/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rlopez.courses.ejb.session.stateful.ejb;

import java.rmi.RemoteException;

/**
 *
 * @author CHICHO
 */
public interface InterestCalculator {
	// set principal amount

	public void setPrincipal(double amount)
			  throws RemoteException;

	// set interest rate
	public void setInterestRate(double rate)
			  throws RemoteException;

	// set loan length in years
	public void setTerm(int years)
			  throws RemoteException;

	// get loan balance
	public double getBalance() throws RemoteException;

	// get amount of interest earned
	public double getInterestEarned() throws RemoteException;
}
