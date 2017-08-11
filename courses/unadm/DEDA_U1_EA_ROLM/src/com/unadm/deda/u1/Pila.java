/**
 * Implements a basic Pila
 * @author Roberto Lopez (marcos.roberto.lopez@gmail.com)
 * 
 */
package com.unadm.deda.u1;

public class Pila extends DataStruct{

	
	public Pila(){
		super();
	}
	
	/**
	 * Add a element to the top of Pila
	 * @param toAdd 
	 */
	public void push(String toAdd) {
		if (counter + 1 > cache.length - 1) {
			//increase the cache is required
			cache = ensureCapacity();
		}
		cache[counter++] = toAdd;
	}
	
	/**
	 * Remove the las element that was added. It is the most top element.
	 * @return 
	 */
	public String pop(){
		if(isEmpty()){
			throw new IllegalArgumentException("The Pila is empty");
		}
		Object toReturn = cache[--counter];
		cache[counter] = null;
		return (String) toReturn;
	}
	
	public String peek(){
				if(isEmpty()){
			throw new IllegalArgumentException("The Pila is empty");
		}
		Object toReturn = cache[counter-1];
		cache[counter] = null;
		return (String) toReturn;
	}
}
