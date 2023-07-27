package com.mydemo.dsa;

// Client program to take care of maintaining head/tail.
public class DoublyLinkedList{
	public static void main(String[] args) {
		testWithStrings();
		testWithIntegers();
	}
	
	private static void testWithStrings() {
		Node<String> node1 = new Node<String>("India");
		Node<String> node2 = new Node<String>("China");
		Node<String> node3 = new Node<String>("Pakistan");
		Node<String> node4 = new Node<String>("USA");
		
		node1.setNext(node2);
		node2.setNext(node3);
		node3.setNext(node4);
		displayListContent(node1, false);
		
		System.out.println("After delete (Reverse)...");
		deleteNode(node3);
		displayListContent(node4, true);
	}

	private static void testWithIntegers() {
		Node<Integer> node1 = new Node<Integer>(12);
		Node<Integer> node2 = new Node<Integer>(23);
		Node<Integer> node3 = new Node<Integer>(34);
		Node<Integer> node4 = new Node<Integer>(45);
		
		node1.setNext(node2);
		node2.setNext(node3);
		node3.setNext(node4);
		displayListContent(node4, true);
		
		System.out.println("After delete...");
		deleteNode(node3);
		displayListContent(node1, false);
		displayListContent(node4, true);
	}

	static void deleteNode(Node<? extends Object> node) {
		if(node.previous!=null && node.next!=null) {
			node.previous.setNext(node.getNext());
		}
	}

	private static void displayListContent(Node<?> node, boolean reverse) {
		while(node!=null) {
			System.out.print("\t"+ node.getObj());
			node = reverse?node.getPrevious():node.getNext();
		}
		System.out.println();
	}
}

class Node<T extends Object>{
	Node<T> previous;
	T obj;
	Node<T> next;

	public Node(T o) {
		super();
		this.obj = o;
	}
	
	public Node<T> getPrevious() {
		return previous;
	}
	public void setPrevious(Node<T> previous) {
		this.previous = previous;
		previous.next = this;
	}
	public T getObj() {
		return obj;
	}
	public void setObj(T o) {
		this.obj = o;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
		next.previous=this;
	}
	
}

