package com.mydemo.designpatterns.observer;

public class SimulateLibrary {
	public static void main(String[] args) {
		Book fivePoint = new Book("Five point someone", "Chetan Bhagat");
		Book twoStates = new Book("2 States", "Chetan Bhagat");
		Book warAndPeace = new Book("War & peace", "Leo Tolstoy");
		
//		List<Book> books = Arrays.asList(fivePoint, twoStates, warAndPeace);
		
		BookReader ravi = new BookReader("Ravi");
		if(twoStates.rentOut(ravi))
			System.out.println(ravi.getUserName()+" got book:"+twoStates.getTitle());
		
		BookReader mahesh = new BookReader("Mahesh");
		if(twoStates.rentOut(mahesh))
			System.out.println(ravi.getUserName()+" got book:"+twoStates.getTitle());
		else
			System.out.println("Hi, "+mahesh.getUserName()+". The book:"+twoStates.getTitle()+" is already leased out.");
		
		ravi.returnBook(twoStates);
		if(twoStates.rentOut(mahesh))
			System.out.println(mahesh.getUserName()+" got book:"+twoStates.getTitle());
		
	}
}
