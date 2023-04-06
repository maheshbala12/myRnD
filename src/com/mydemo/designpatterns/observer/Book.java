package com.mydemo.designpatterns.observer;

import java.util.ArrayList;
import java.util.List;

public class Book {
	public static enum AVAILABILITY_STATUS{Taken, Available};
	String title, author;
	AVAILABILITY_STATUS status;

	List<BookObserver> readers;
	
	public Book(String title, String author) {
		super();
		this.title = title;
		this.author = author;
		this.status = AVAILABILITY_STATUS.Available;
		this.readers = new ArrayList<BookObserver>();
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public AVAILABILITY_STATUS getStatus() {
		return status;
	}

	public void setStatus(AVAILABILITY_STATUS status) {
		if(this.status==AVAILABILITY_STATUS.Taken && status==AVAILABILITY_STATUS.Available)
			notifyReaders();
		this.status = status;
	}

	@Override
	public String toString() {
		return title + " (" + author + ") :" + status;
	}
	
	boolean rentOut(BookReader reader){
		if(status==AVAILABILITY_STATUS.Taken) {
			registerObserver(reader);
			return false;
		}
		status=AVAILABILITY_STATUS.Taken;
		return true;
	}
	
	
	void registerObserver(BookObserver reader){
		readers.add(reader);
	}

	void deregisterObserver(BookObserver reader){
		readers.remove(reader);
	}
	
	void notifyReaders(){
		readers.stream().forEach(reader->reader.update(this));
	}
	
}
