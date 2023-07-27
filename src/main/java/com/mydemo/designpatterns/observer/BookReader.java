package com.mydemo.designpatterns.observer;

public class BookReader implements BookObserver {

	String userName;
	
	public BookReader(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	void returnBook(Book book) {
		System.out.println(userName+" returned the book: "+book.getTitle());
		book.deregisterObserver(this);
		book.setStatus(Book.AVAILABILITY_STATUS.Available);
	}
	
	@Override
	public void update(Book book) {
		System.out.println("Hello, "+userName+", the book "+book.getTitle()+" is now available.");
		// This wrongly placed call results in StackOverflowError on Book.setStatus().
//		book.setStatus(Book.AVAILABILITY_STATUS.Available);
	}

}
