package com.mydemo.functional;

import java.util.Spliterator;
import java.util.function.Consumer;

import com.mydemo.common.GenericConstants.Gender;

public class MyFileReader implements Spliterator<Employee> {

	private String name, designation;
	private Gender gender;
	private int age, salary;
	
	Spliterator<String> baseSpliterator;
	int numFields;
	
	public MyFileReader(Spliterator<String> spliterator, int numFields) {
		super();
		baseSpliterator = spliterator;
		this.numFields=numFields;
	}

	@Override
	public int characteristics() {
		return baseSpliterator.characteristics();
	}

	@Override
	public long estimateSize() {
		return baseSpliterator.estimateSize()/numFields;
	}

	@Override
	public boolean tryAdvance(Consumer<? super Employee> action) {
		if(
			baseSpliterator.tryAdvance(str->name=str) &&
			baseSpliterator.tryAdvance(str->gender=(str.equalsIgnoreCase("Male")?Gender.MALE:Gender.FEMALE)) &&
			baseSpliterator.tryAdvance(str->age=Integer.parseInt(str)) &&
			baseSpliterator.tryAdvance(str->salary=Integer.parseInt(str)) &&
			baseSpliterator.tryAdvance(str->designation=str)){
			
			action.accept(new Employee(name, gender, age, salary, designation));
			return true;
		}
			
		return false;
	}

	@Override
	public Spliterator<Employee> trySplit() {
		return null;
	}

}
