package com.mydemo.functional;

public class FunctionalList<T> {
	T head;
	FunctionalList<T> tail;
	
	public FunctionalList() {}

	public FunctionalList(T... elements) {
		FunctionalList<T> tmp = null;
		for(int i=elements.length-1;i>=0;i--) {
			tmp = add(elements[i]);
			tail=tmp.tail;
		}
		this.head=tmp.head;
		this.tail=tmp.tail;
	}
	
	public FunctionalList<T> add(T t) {
		FunctionalList<T> tmp = new FunctionalList<T>();
		tmp.head = t;
		tmp.tail=this.tail;
		return tmp;
	}
	
	public void show() {
		for(FunctionalList<T> temp = this;temp!=null;temp=temp.tail) {
			System.out.println(temp.head);
		}			
	}
	
	public FunctionalList remove(T t) {
		return null;
	}
}
