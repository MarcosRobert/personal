/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unadm.deda.u1;


/**
 *
 * @author Roberto Lopez
 */
public class ArimethicEvaluator {

	/**
	 * Evalua una expresion aritmetica usando dos pilas, la expresion aritmetica
	 * debe estar en forma postfija
	 * @param expr
	 * @return 
	 */
	public static String eval(String expr) {

		String[] post = expr.split("\\s");
		
		Pila numbers = new Pila(); 
		Pila operators = new Pila(); 


		for (int i = post.length - 1; i >= 0; i--) {
			numbers.push(post[i]);
		}

		//Algoritmo de Evaluación Postfija
		while (!numbers.isEmpty()) {
			if (isOperator(numbers.peek())) {
				operators.push(eval(numbers.pop(), operators.pop(), operators.pop()));
			} else {
				operators.push(numbers.pop());
			}
		}
		return operators.peek();
	}

	/**
	 * Realiza una operacion aritmetica de dos operandos
	 * @param op
	 * @param n2
	 * @param n1
	 * @return 
	 */
	private static String eval(String op, String n2, String n1) {
		int num1 = Integer.parseInt(n1);
		int num2 = Integer.parseInt(n2);
		if (op.equals("+")) {
			return String.valueOf(num1 + num2);
		}
		if (op.equals("-")) {
			return String.valueOf(num1 - num2);
		}
		if (op.equals("*")) {
			return String.valueOf(num1 * num2);
		}
		if (op.equals("/")) {
			return String.valueOf(num1 / num2);
		}
		return "0";
	}
	
	private static boolean isOperator(String opr){
		return "+".equals(opr)
				  || "-".equals(opr)
				  || "*".equals(opr)
				  || "/".equals(opr);
	}
}
