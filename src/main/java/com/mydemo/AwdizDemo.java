package com.mydemo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Visitor implements Comparable {
	String name, address, phone;

	public Visitor(String name, String address, String phone) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	@Override
	public int compareTo(Object obj2) {
		Visitor visitor = (Visitor) obj2;
		int flag = name.compareTo(visitor.name);
		if (flag == 0) {
			flag = address.compareTo(visitor.address); // Sort by Visitor name, then address & phone
			if (flag == 0) {
				flag = phone.compareTo(visitor.phone);
			}
		}
		return flag;
	}

	public boolean equals(Object obj) {
		if (obj == null || !(this.getClass().getName().equals(obj.getClass().getName())))
			return false;
		Visitor p = (Visitor) obj;
		return p.name.equals(name)
				&& p.address.equals(address)
					&& p.phone.equals(phone);
	}
	
	@Override
	public int hashCode() {
		return name.hashCode()+address.hashCode()+phone.hashCode();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		System.out.println(name + " lives in " + address + "." + " Contact number: " + phone + " HashCode: " + hashCode());
		return name + " lives in " + address + "." + " Contact number: " + phone;
	}
}

public class AwdizDemo {
	public static void usingSet() {
		Set<Visitor> visitors = new HashSet<Visitor>();
		visitors.add(new Visitor("Mahesh", "Thane", "12346"));
		visitors.add(new Visitor("Shivali", "Jogeshwari", "78910"));
		visitors.add(new Visitor("Shivali", "Jogeshwari", "78910"));
		visitors.add(new Visitor("Shivali", "Ambarnath", "81910"));
		visitors.add(new Visitor("Mahesh", "Airoli", "111213"));
		visitors.add(new Visitor("Akhileshwar", "Airoli", "141516"));
		visitors.add(new Visitor("Nirav", "Ghatkopar", "171819"));
		
		System.out.println(visitors.size());

		// Count of Visitors named "Shivali"
		// Using Java Streams approach...
		Long count = visitors.stream()
				.filter(v->v.getName().equals("Shivali"))
				.collect(Collectors.counting());
		
		System.out.println(count);
	}
	
	
	public static void main(String[] args) {
		usingSet();
		List<Visitor> list = new ArrayList<Visitor>();
		list.add(new Visitor("Mahesh", "Thane", "12346"));
		list.add(new Visitor("Shivali", "Jogeshwari", "78910"));
		list.add(new Visitor("Shivali", "Jogeshwari", "78910"));
		list.add(new Visitor("Shivali", "Ambarnath", "81910"));
		list.add(new Visitor("Mahesh", "Airoli", "111213"));
		list.add(new Visitor("Akhileshwar", "Airoli", "141516"));
		list.add(new Visitor("Nirav", "Ghatkopar", "171819"));
		
		// Count of Visitors named "Shivali"
		// Using traditional approach...
		int count = 0;
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getName().equals("Shivali")){
				count++;
			}
		}
		System.out.println(count);
		
		// Count of Visitors named "Shivali"
		// Using Java Streams approach...
		Long collect = list.stream().filter(v->v.getName().equals("Shivali")).collect(Collectors.counting());
		System.out.println(count);
		
		
		Set<Visitor> set = new HashSet<Visitor>();
		set.addAll(list);
		set.stream().forEach(System.out::println);
	}
}
