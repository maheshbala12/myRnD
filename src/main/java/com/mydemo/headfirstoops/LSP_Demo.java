package com.mydemo.headfirstoops;

import org.junit.Assert;

public class LSP_Demo {
	public static void main(String[] args) {
		Square square = new Square(12);
		square.setWidth(10); //Violation #1: Square should have equal sides unlike other rectangles.
		square.calculateArea();
		Assert.assertEquals(square.getLength(), square.getWidth()); // Fails
	}
}

class Rectangle{
	int length;
	int width;

	public Rectangle(int length, int width) {
		super();
		this.length = length;
		this.width = width;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}

	void calculateArea() {
		System.out.println(getLength() * getWidth());
	}
}

class Square extends Rectangle{

	public Square(int side) {
		super(side, side);
	}
	
	
}
