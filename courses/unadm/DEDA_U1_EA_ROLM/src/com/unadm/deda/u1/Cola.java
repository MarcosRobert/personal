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
public class Cola extends DataStruct {

	
	public Cola(){
		super();
	}
	
	/**
	 * Add a element to end of Cola
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
	 * Always remove the frond element. It is that was first added. After remove, move to forwad the
	 * rest of elements
	 * @return 
	 */
	public Object removeElement(){
		if(isEmpty()){
			throw new IllegalArgumentException("The Cola is empty");
		}
		//the cola first element add it is the first element to go out
		Object toReturn = cache[0];
		
		//move elements to forwad
		System.arraycopy(cache, 1, cache, 0, cache.length - 1);
		cache[--counter] = null;
		return toReturn;
	}
	
}
 