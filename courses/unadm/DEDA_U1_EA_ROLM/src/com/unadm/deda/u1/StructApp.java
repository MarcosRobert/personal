/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unadm.deda.u1;

import java.util.Scanner;

/**
 *
 * @author Cliente
 */
public class StructApp {
	private static final String structNames[] = {"", "Pila", "Cola", "Lista"};
	private static final String usagesStruts[] = {
		"",
		"Ingresar un elemento",
		"Eliminar un elemento",
		"Verificar si la estructura esta vacia",
		"Mostrar el contenido"
	};
	
	
	private Pila pila;
	private Cola cola;
	private Scanner sc;
	private Lista lista;

	
	public StructApp(){
		pila = new Pila();
		cola = new Cola();
		lista = new Lista();
		sc = new Scanner(System.in);
	}
	
	
	public static void main(String args[]){
		StructApp structApp = new StructApp();
		structApp.execute();
	}
	/**
	 * Imprime el menu principal de la aplicacion
	 */
	private void printMainMenu(){
		System.out.println("***** Aplicacion Para Estructuras de Datos");
		System.out.println("1)     " + structNames[1]);
		System.out.println("2)     " + structNames[2]);
		System.out.println("3)     " + structNames[3]);
		System.out.println("4)     Salir");
	}
	
	/**
	 * Imprime las operaciones disponibles para la estructura Pila
	 * @param struct 
	 */
	private void printPilaSubMenu(int struct){
		System.out.println("Bienvenido al evaluador de expresiones Aritmeticas");
		System.out.println("1)     Evaluar Expresion Aritmetica");
		System.out.println("2)     Regresar a Menu Principal");
	}
	
	/**
	 * Imprime las operaciones disponibles para la estructura Cola
	 * @param struct 
	 */
	private void printColaSubMenu(int struct){
		System.out.println("Bienvenidos a Farmacia *** FarmaRobert ***");
		System.out.println("1)     Solicitar Numero de Atencion");
		System.out.println("2)     Atender siguiente Numero");
		System.out.println("3)     Regresar a Menu Principal");
		
	}
	
		/**
	 * Imprime las operaciones disponibles para la estructura Lista
	 * @param struct 
	 */
	private void printListSubMenu(int struct){
		System.out.println("Bienvenidos a Consultorio Medico *** SuperMedico***");
		System.out.println("1)     Reservar Hora");
		System.out.println("2)     Cancelar Hora Reservada");
		System.out.println("3)     Imprimir Horas");
		System.out.println("4)     Regresar a Menu Principal");
	}
	
	/**
	 * Muestra las horas reservadas y las horas disponibles
	 * El horario de atencion es de 8:00 a 16:00
	 */
	private void reservarHora(){
		
		for(int i = 0; i <= 8; i++){
			System.out.println("[" + (i + 8) + ":00]: " + lista.getElement(i));
		}
		
		System.out.println("Seleccione Hora a Reservar [8-16]:");
		//for clean buffer
		sc.nextLine();
		//wait user entry string
		sc.hasNextLine();
		String shora = sc.nextLine();
		int ihora = Integer.parseInt(shora);
		if(ihora < 8 || ihora > 16){
			System.out.println("Debe elegir una hora entre [8 - 16]");
			return;
		}
		
		if(!("DISPONIBLE".equals(lista.getElement(ihora - 8)))){
			System.out.println("Hora no disponible. Seleccione otra.");
			return;
		}
		lista.removeElement(ihora - 8);
		lista.addElement(ihora - 8, "RESERVADA");
		System.out.println("Hora Reservada con Exito");
		
	}
	
	private void imprimirHoras() {
		System.out.println("*** HORAS ***");
		for (int i = 0; i <= 8; i++) {
			System.out.println("[" + (i + 8) + ":00]: " + lista.getElement(i));
		}
	}
	
	/**
	 *Cancela una reserva de hora, esta queda disponible para ser reservada
	 */
	private void cancelarReservaHora(){
		System.out.println("Cancelar Hora Reservada");
		for(int i = 0; i <= 8; i++){
			System.out.println("[" + (i + 8) + ":00]: " + lista.getElement(i));
		}
		
		System.out.println("Seleccione Hora a Cancelar [8-16]:");
		//for clean buffer
		sc.nextLine();
		//wait user entry string
		sc.hasNextLine();
		String shora = sc.nextLine();
		int ihora = Integer.parseInt(shora);
		if(ihora < 8 || ihora > 16){
			System.out.println("Debe elegir una hora entre [8 - 16]");
			return;
		}
		
		if(!("RESERVADA".equals(lista.getElement(ihora - 8)))){
			System.out.println("Hora no se encuentra Reservada");
			return;
		}
		lista.removeElement(ihora - 8);
		lista.addElement(ihora - 8, "DISPONIBLE");
		System.out.println("Hora cancelada con Exito");
		
	}
	
	/**
	 * Metodo principal de la aplicacion muestra el menu y espera a recibir
	 * solicitud de parte del usuario hasta que el decida terminar el programa
	 * Execute the app
	 */
	public void execute(){

		//iniciar la lista en horas disponibles
		for(int i = 0; i <= 8; i++){
			lista.addElement("DISPONIBLE");
		}
		
		do {
			printMainMenu();
			System.out.println("Selecciona una Opcion:");

			if (!sc.hasNextInt()) {
				System.out.println("Opcion Invalida!!");
			}

			int selectedMenu = sc.nextInt();
			if(selectedMenu == 4){
				//exit with return success
				System.exit(0);
			}
			
			int selectedSubMenu;
			do {
				switch (selectedMenu) {
					case 1:
						printPilaSubMenu(selectedMenu);
						System.out.println("Selecciona una Opcion:");
						if (!sc.hasNextInt()) {
							System.out.println("Opcion Invalida!!");
						}
						selectedSubMenu = sc.nextInt();
						if (selectedSubMenu == 2) {
							//return to Main Menu
							break;
						}
						usePila(selectedSubMenu);
						break;
					case 2:
						printColaSubMenu(selectedMenu);
						System.out.println("Sele cciona una Opcion:");
						if (!sc.hasNextInt()) {
							System.out.println("Opcion Invalida!!");
						}
						selectedSubMenu = sc.nextInt();
						if (selectedSubMenu == 3) {
							//return to Main Menu
							break;
						}
						useCola(selectedSubMenu);
						break;
					case 3:
						printListSubMenu(selectedMenu);
						System.out.println("Selecciona una Opcion:");
						if (!sc.hasNextInt()) {
							System.out.println("Opcion Invalida!!");
						}
						selectedSubMenu = sc.nextInt();
						if (selectedSubMenu == 4) {
							//return to Main Menu
							break;
						}
						useList(selectedSubMenu);
						
						break;
				}
			} while (true);

		} while (true);

	}
	
	private void useList(int usage) {
		switch (usage) {
			case 1:
				reservarHora();
				break;
			case 2:
				cancelarReservaHora();
				break;
			case 3:
				imprimirHoras();
				break;
			case 4:
				break;
		}
	}

	private void usePila(int usage) {
		switch (usage) {
			case 1:
				System.out.println("Ingrese una expresion aritmetica (Ejem:2+3*4):");
				//for clean buffer
				sc.nextLine();
				//wait user entry string
				sc.hasNextLine();
				String arimethicExpression  = sc.nextLine();
				String postfija = PostfijaConverter.convert(arimethicExpression);
				ArimethicEvaluator evaluator = new ArimethicEvaluator();
				String result = evaluator.eval(postfija);
				System.out.println("Resultado: " + result);
				break;
		}
	}
			
	private void useCola(int usage) {
		switch (usage) {
			case 1:
				String nextNumber = Integer.toString(cola.getSize() + 1);
				System.out.println("Su numero de Atencion es: " + nextNumber);
				cola.addElement(nextNumber);
				break;
			case 2:
				String removed = (String) cola.removeElement();
				System.out.println("Pasar a modulo de Atencion el numero: " + removed);
				break;
		}
	}
}