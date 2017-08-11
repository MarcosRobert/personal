/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unadm.deda.u1;

/**
 *
 * @author Cliente
 */
public abstract class DataStruct {
	final int increaseFactor = 10;
	/**
	 * The cache for store the objects
	 */
	protected Object cache[];
	/**
	 * The counter of datas
	 */
	protected int counter;
	
	public DataStruct(){
		cache = new Object[increaseFactor];
		counter = 0;
	}
	
	/**
	 * Check if it is require increase the array
	 * @return 
	 */
	protected Object[] ensureCapacity() {
		Object newCache[] = new Object[cache.length + increaseFactor];
		System.arraycopy(cache, 0, newCache, 0, cache.length);
		return newCache;
	}

	/**
	 * Test if the struct is empty
	 * @return 
	 */
	public boolean isEmpty() {
		return counter < 1;
	}

	/**
	 * Print the elements that contains the struct
	 * @return 
	 */
	public String printElements() {
		StringBuilder sb = new StringBuilder();
		for (int position = 0; position < counter; position++) {
			sb.append("Position[ ").append(position).append(" ] = ").append(cache[position]).append("\n");
		}
		return sb.toString();
	}
	
	public int getSize(){
		return counter;
	}
}
