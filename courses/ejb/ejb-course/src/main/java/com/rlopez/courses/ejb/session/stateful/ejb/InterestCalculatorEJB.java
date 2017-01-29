/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rlopez.courses.ejb.session.stateful.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateful;
/**
 *
 * @author CHICHO
 */
@Stateful
@Remote(InterestCalculator.class)
public class InterestCalculatorEJB implements InterestCalculator{

	// state variables
	private double principal;
	private double interestRate;
	private int term;

	// set principal amount
	public void setPrincipal(double amount) {
		principal = amount;
	}

	// set interest rate
	public void setInterestRate(double rate) {
		interestRate = rate;
	}

	// set loan length in years
	public void setTerm(int years) {
		term = years;
	}
	// get loan balance

	public double getBalance() {
		// calculate simple interest
		return principal * Math.pow(1.0 + interestRate, term);
	}

	// get amount of interest earned
	public double getInterestEarned() {
		return getBalance() - principal;
	}
}
