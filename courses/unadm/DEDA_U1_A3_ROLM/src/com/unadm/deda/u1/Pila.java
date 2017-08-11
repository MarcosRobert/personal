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
	public void addElement(Object toAdd) {
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
	public Object removeElement(){
		if(isEmpty()){
			throw new IllegalArgumentException("The Pila is empty");
		}
		Object toReturn = cache[--counter];
		cache[counter] = null;
		return toReturn;
	}
}
