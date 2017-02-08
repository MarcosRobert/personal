/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rlopez.tutorials.load.resource;

import java.io.InputStream;
import java.net.URL;

/**
 *
 * @author Roberto Lopez
 */
public class ResourceLocator {
	
	public static void main(String args[]){
		System.out.println("" + System.getProperty("java.class.path"));
		Class clazz = ResourceLocator.class;
		InputStream url = clazz.getResourceAsStream("/duke_wave.png");
		System.err.println(url);
		System.err.println(url);
	}
}
