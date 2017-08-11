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
	private void printMainMenu(){
		System.out.println("***** Menu Principal");
		System.out.println("1)     " + structNames[1]);
		System.out.println("2)     " + structNames[2]);
		System.out.println("3)     " + structNames[3]);
		System.out.println("4)     Salir");
	}
	
	private void printSubMenu(int struct){
				System.out.println("Operaciones disponibles para: " + structNames[struct]);
		System.out.println("1)     " + usagesStruts[1]);
		System.out.println("2)     " + usagesStruts[2]);
		System.out.println("3)     " + usagesStruts[3]);
		System.out.println("4)     " + usagesStruts[4]);
		System.out.println("5)     Regresar a Menu Principal");
	}
	
	
	/**
	 * Execute the app
	 */
	public void execute(){

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
			
			do {
				printSubMenu(selectedMenu);
				System.out.println("Selecciona una Opcion:");
				if (!sc.hasNextInt()) {
					System.out.println("Opcion Invalida!!");
				}
				int selectedSubMenu = sc.nextInt();
				if(selectedSubMenu == 5) {
					//return to Main Menu
					break;
				}
				switch (selectedMenu) {
					case 1:
						usePila(selectedSubMenu);
						break;
					case 2:
						useCola(selectedSubMenu);
						break;
					case 3:
						useList(selectedSubMenu);
						break;
				}
			} while (true);

		} while (true);

	}
	
	private void useList(int usage) {
		switch (usage) {
			case 1:
				System.out.println("Ingrese elemento a añadir:");
				//for clean buffer
				sc.nextLine();
				//wait user entry string
				sc.hasNextLine();
				String toAdd = sc.nextLine();
				lista.addElement(toAdd);
				break;
			case 2:
				String removed = (String) lista.removeElement();
				System.out.println("Elemento eliminado: " + removed);
				break;
			case 3:
				System.out.println("Lista vacia:" + (lista.isEmpty() ? "SI" : "NO"));
				break;
			case 4:
				System.out.println("Elementos en la Lista");
				System.out.println(lista.printElements());
				break;
		}
	}

	private void usePila(int usage) {
		switch (usage) {
			case 1:
				System.out.println("Ingrese elemento a añadir:");
				//for clean buffer
				sc.nextLine();
				//wait user entry string
				sc.hasNextLine();
				String toAdd = sc.nextLine();
				pila.addElement(toAdd);
				break;
			case 2:
				String removed = (String) pila.removeElement();
				System.out.println("Elemento eliminado: " + removed);
				break;
			case 3:
				System.out.println("Pila vacia:" + (pila.isEmpty() ? "SI" : "NO"));
				break;
			case 4:
				System.out.println("Elementos en la Pila");
				System.out.println(pila.printElements());
				break;
		}
	}
			
	private void useCola(int usage) {
		switch (usage) {
			case 1:
				System.out.println("Ingrese elemento a añadir:");
					//for clean buffer
				sc.nextLine();
				//wait user entry string
				sc.hasNextLine();
				String toAdd = sc.nextLine();
				cola.addElement(toAdd);
				break;
			case 2:
				String removed = (String) cola.removeElement();
				System.out.println("Elemento eliminado: " + removed);
				break;
			case 3:
				System.out.println("COla vacia:" + (cola.isEmpty() ? "SI" : "NO"));
				break;
			case 4:
				System.out.println("Elementos en la Cola");
				System.out.println(cola.printElements());
				break;
		}
	}
}