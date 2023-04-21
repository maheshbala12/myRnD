package com.mydemo.designpatterns.observer;

public class SimulateLibrary {
	public static void main(String[] args) {
		Book fivePoint = new Book("Five point someone", "Chetan Bhagat");
		Book twoStates = new Book("2 States", "Chetan Bhagat");
		Book warAndPeace = new Book("War & peace", "Leo Tolstoy");
		
//		List<Book> books = Arrays.asList(fivePoint, twoStates, warAndPeace);
		
		BookReader user1 = new BookReader("Ravi");
		if(twoStates.rentOut(user1))
			System.out.println(user1.getUserName()+" got book:"+twoStates.getTitle());
		
		BookReader user2 = new BookReader("Mahesh");
		if(twoStates.rentOut(user2))
			System.out.println(user1.getUserName()+" got book:"+twoStates.getTitle());
		else
			System.out.println("Hi, "+user2.getUserName()+". The book:"+twoStates.getTitle()+" is already leased out.");
		
		user1.returnBook(twoStates);
		if(twoStates.rentOut(user2))
			System.out.println(user2.getUserName()+" got book:"+twoStates.getTitle());
		
	}
}
