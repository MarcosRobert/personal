/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rlopez.tutorials.string;

import java.util.Scanner;

/**
 *
 * @author Cliente
 */
public class ReverseString {
   public static void main(String args[]){
      System.out.println("Ingresa una palabra [presione enter para confirmar]");
      Scanner scanner = new Scanner(System.in);
      String world = scanner.nextLine();
      //first way create a string builder instance, reverse the same instance
      //of string builder
      System.out.println("Reverse using StringBuilder.reverse(): "
				  + (new StringBuilder(world).reverse()));
      //second way use my own method, reverse and return a new instance of
      //string
      System.out.println("Reverse using my owner reverse(): "
				  + (ReverseString.reverse(world)));
   }
   
   public static String reverse(String toReverse){
      char characters[] = new char[toReverse.length()];
      int j = 0;
      for(int i = toReverse.length() - 1; i >= 0; i--){
         characters[j++] = toReverse.charAt(i);
      }
      return new String(characters);
   }
}
