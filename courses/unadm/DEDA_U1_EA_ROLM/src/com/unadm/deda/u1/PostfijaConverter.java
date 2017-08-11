/*
Algoritmo usado para convertir una expresion infija a postfija
tomado de: https://www.infor.uva.es/~cvaca/asigs/AlgInfPost.htm

  INICIO
     Crear pila y la lista de salida, inicialmente vacias.
     MIENTRAS lista de entrada no este vacia y
              no se ha encontrado ningun error HACER
       Extraer el primer termino de la lista (lo llamaremos E)
       SEGUN-SEA E
         CASO E es número :
           Insertar E al final de la lista de salida
         CASO E es la variable x :
           Insertar E al final de la lista de salida
         CASO E es un paréntesis izquierdo :
           Insertar E en la pila
         CASO E es un paréntesis derecho :
           MIENTRAS La pila no este vacía y
                    su cima no sea un paréntesis izquierdo HACER
             Extraer elemento de la pila
             Insertarlo al final de la lista de salida
           FIN-MIENTRAS
           SI Encontramos el parentesis izquierdo ENTONCES
             Extraerlo de la pila y destruirlo
           SINO
             Se ha detectado un ERROR 2
           FIN-SI
           Destruir E
         CASO E es un operador :
           MIENTRAS La pila no este vacía y
                    su cima sea un operador
                    de precedencia mayor o igual que la de E HACER
             Extraer elemento de la pila
             Insertarlo al final de la lista de salida
           FIN-MIENTRAS
           Insertar E en la pila
       FIN-SEGUN-SEA
    FIN-MIENTRAS
    MIENTRAS Pila no esté vacía HACER
      Extraer elemento de la pila
      Insertarlo al final de la lista de salida
    FIN-MIENTRAS
    Destruir pila
  FIN
 */
package com.unadm.deda.u1;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase implementa
 * @author Roberto Lopez (marcos.roberto.lopez@gmail.com)
 */
public class PostfijaConverter {

	private static final int DIGIT = 1;
	private static final int OPERATOR = 2;
	private static final int PARENTHESIS = 3;

	private PostfijaConverter() {
	}

	/**
	 * Este metodo aplica el algoritmo para convertir una expresion infija a una
	 * expresion postfija
	 * @param infija
	 * @return 
	 */
	public static String convert(String infija) {
		Pila operators = new Pila();
		List<String> postfija = new ArrayList<>();

		StringBuilder number = new StringBuilder();
		char inputArray[] = infija.toCharArray();
		for (int i = 0; i < inputArray.length; i++) {
			char character = inputArray[i];
			switch (getType(character)) {
				case DIGIT:
					number.append(character);
					if (i == inputArray.length - 1) {
						postfija.add(number.toString());
					}
					break;

				case OPERATOR:
					if (number.length() > 0) {
						postfija.add(number.toString());
						number = new StringBuilder();
					}

					while ((!operators.isEmpty()) && compare(operators.peek(), String.valueOf(character)) >= 0) {
						postfija.add(operators.pop().toString());
					}
					operators.push(String.valueOf(character));
					break;
				case PARENTHESIS:
					if (number.length() > 0) {
						postfija.add(number.toString());
						number = new StringBuilder();
					}

					if (isLeft(String.valueOf(character))) {
						operators.push(String.valueOf(character));
					} else {
						while ((!operators.isEmpty()) && (!isLeft(operators.peek()))) {
							postfija.add(operators.pop().toString());
						}
						if ((!operators.isEmpty()) && (isLeft(operators.peek()))) {
							operators.pop();
						}
					}
					break;
				default:

			}
		}

		StringBuilder strBuilder = new StringBuilder();

		for (String item : postfija) {
			strBuilder.append(item).append(" ");
		}

		while (!operators.isEmpty()) {
			strBuilder.append(operators.pop().toString());
			if (!operators.isEmpty()) {
				strBuilder.append(" ");
			}
		}
		return strBuilder.toString();
	}

	/**
	 * Determinar si un caracter es digito operador o parentesis
	 * @param item
	 * @return 
	 */
	private static int getType(char item) {
		if (Character.isDigit(item)) {
			return DIGIT;
		} else if (item == '-' || item == '+' || item == '*' || item == '/') {
			return OPERATOR;
		} else if (item == '(' || item == ')') {
			return PARENTHESIS;
		} else {
			return 0;
		}
	}

	/**
	 * Compara operadores de acuerdo a las reglas de precedencias
	 * de mayor a menor
	 *  * /
	 *  + -
	 *  ()
	 * @param operator1
	 * @param operator2
	 * @return 
	 */
	public static int compare(String operator1, String operator2) {
		if ("*".equals(operator1) || "/".equals(operator1)) {
			if ("+".equals(operator2) || "-".equals(operator2)) {
				return 1;
			} else if("*".equals(operator1) || "/".equals(operator1)){
				return 0;
			} else {
				return -1;
			}
		} else if ("+".equals(operator1) || "-".equals(operator1)) {
			if ("*".equals(operator2) || "/".equals(operator2)) {
				return -1;
			} else if("+".equals(operator1) || "-".equals(operator1)){
				return 0;
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}

	public static void main(String args[]) {
		PostfijaConverter converter = new PostfijaConverter();
		String test = "2+3*4";
		System.out.println(converter.convert(test));
	}

	public static boolean isLeft(String character) {
		return "(".equals(character);
	}
}
