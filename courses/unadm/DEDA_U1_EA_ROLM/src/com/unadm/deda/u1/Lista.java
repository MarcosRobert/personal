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
public class Lista extends DataStruct {
	
	public Lista(){
		super();
	}
	
	public void addElement(Object element){
		addElement(counter, element);
	}
	
	public void addElement(int index, Object element){
		if (index > cache.length - 1) {
			//increase the cache is required
			cache = ensureCapacity();
		}
		cache[index] = element;
		counter++;
	}
	
	public Object removeElement(){
		return removeElement(counter);
	}
	
	public Object getElement(int index){
		return cache[index];
	}
	public Object removeElement(int index){
		Object toReturn = cache[index];
		cache[index] = null;
		counter--;
		return toReturn;
	}
	
}
