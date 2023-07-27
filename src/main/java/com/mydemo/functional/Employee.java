package com.mydemo.functional;

import com.mydemo.common.GenericConstants.Gender;

public class Employee{
	private String name, designation;
	private Gender gender;
	private int age, salary;
	
	
	public Employee(String name, Gender gender, int age, int salary, String designation) {
		super();
		this.gender = gender;
		this.name = name;
		this.age=age;
		this.designation=designation;
		this.salary=salary;
	}
	public String getName() {
		return name; 
	}
	public Gender getGender() {
		return gender; 
	}
	public int getAge() {
		return age;
	}
	public String getDesignation() {
		return designation;
	}
	public int getSalary() {
		return salary;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", designation=" + designation + ", gender=" + gender + ", age=" + age
				+ ", salary=" + salary + "]";
	}
}