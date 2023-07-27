package com.mydemo.dummy;

public class DummyOuterClass {
    protected class ClassWithProtectedDefaultConstructor {
    	// A "protected" class inherits a "protected" default constructor, if there's no default constructor defined explicitly.
    	// So, any an instance of this/ sub classes have access to this class, but not access to its default constructor.
    	public void displayMessage(){
        	System.out.println("Welcome. If you are seeing this message, it means you instantiated this class as an anonymous class in a sub-class.");
        }
    }
}
