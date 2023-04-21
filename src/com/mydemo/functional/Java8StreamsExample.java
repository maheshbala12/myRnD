package com.mydemo.functional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.mydemo.common.GenericConstants;

public class Java8StreamsExample {
	static Stream<Employee> getStreamOfemployees() throws IOException{
		Stream<Employee> stream = null;
//		Approaches:
//		1) Populate list & derive stream
		/*List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee("Shivali", Gender.FEMALE, 31, 30000, "Jr. Soft. Engr"));
		employees.add(new Employee("Mahesh", Gender.MALE, 41, 130000, "Team Lead"));
		employees.add(new Employee("Akhileshwar", Gender.MALE, 36, 90000, "Sr. Soft. Engr"));
		employees.add(new Employee("Aditi", Gender.FEMALE, 30, 30000, "Jr. Soft. Engr"));
		employees.add(new Employee("Nirav", Gender.MALE, 35, 150000, "Team Lead"));
		employees.add(new Employee("Akshay", Gender.MALE, 41, 100000, "Sr. Soft. Engr"));
		employees.add(new Employee("Kaveri", Gender.FEMALE, 37, 80000, "Sr. Soft. Engr"));
		employees.add(new Employee("Srinivas", Gender.MALE, 42, 100000, "Team Lead"));
		employees.add(new Employee("Ashwini", Gender.FEMALE, 33, 20000, "Jr. Soft. Engr"));
		stream = employees.stream();*/
		
//		2) Alternatively, use Stream class to create and get hold of the stream directly
		/*stream = Stream.of(new Employee("Shivali", Gender.FEMALE, 31, 30000, "Jr. Soft. Engr"),
				new Employee("Mahesh", Gender.MALE, 41, 130000, "Team Lead"),
				new Employee("Akhileshwar", Gender.MALE, 36, 90000, "Sr. Soft. Engr"),
				new Employee("Aditi", Gender.FEMALE, 30, 30000, "Jr. Soft. Engr"),
				new Employee("Nirav", Gender.MALE, 35, 150000, "Team Lead"),
				new Employee("Akshay", Gender.MALE, 41, 100000, "Sr. Soft. Engr"),
				new Employee("Kaveri", Gender.FEMALE, 37, 80000, "Sr. Soft. Engr"),
				new Employee("Srinivas", Gender.MALE, 42, 100000, "Team Lead"),
				new Employee("Ashwini", Gender.FEMALE, 33, 20000, "Jr. Soft. Engr"));*/
		
//		3) Create custom spliterator to create Stream and read Employee objects from a file
		Path filePath = Paths.get("D:\\Mahesh\\Learning\\POCs\\myRnD\\src\\com\\mydemo\\employees.txt");
//		InputStream inputStream = ValidateCodeBlocks.class.getClassLoader().getResourceAsStream("employees.txt");
		
		Stream<String> streamOfLines = Files.lines(filePath);
		Spliterator<String> sp = streamOfLines.spliterator();
		
		Spliterator<Employee> employeeSpliterator = new MyFileReader(sp, 3);
		stream = StreamSupport.stream(employeeSpliterator, false);
		
		return stream;
	}
	
	public static void main(String[] args) throws Exception {
		
		Stream<Employee> employeestream = getStreamOfemployees();
		
		// (1) Derive list of female employees
//		getFemaleemployeesCommaSeparated(employeestream);

		// (2) Get a list of males with age > 25
//		getMalesWithAgeRestriction(employeestream);
		
		// (3) Create a map of <employeename, Employee>
//		createemployeesMap(employeestream);
		
//		 (4) Grouping and aggregation examples
		groupByDesignation(employeestream);
//		getCountByDesignation(employeestream);
		//getMaxSalByDesignation(employeestream);
//		getEmpsWithMaxSalByDesignation(employeestream); // TODO: Needs to be fixed
		
//		(5) Mimic reduce ops using BinaryOperator
		//new IntReduceOps().getSum(Arrays.asList(23,11, 9));
		
//		(6) TODO: Functional data structure (Immutable) - List. Map, etc
//		FunctionalList class is broken, needs fixed
//		(Ref: E:\Learning\Functional Programming\Functional & Reactive Programming in Java\source files)
		FunctionalList<Integer> list = new FunctionalList<Integer>(37,46,72,10,5);
		//list.show();
//		(7) 
		
	}

	
	
	static class IntReduceOps<Integer>{
		int getSum(List<Integer> numbers) {
			int result=0;
			// TODO: Needs to be fixed
			// intValue() here works fine
			System.out.println(new java.lang.Integer(123).intValue());
			numbers.get(1);
			// This doesn't work.
	        /*BinaryOperator<Integer> biop = (Integer i, Integer j)->(i.intValue()+j.intValue());
	        for(Integer iObj:numbers){
	        	result = biop.apply(result, iObj.intValue());
	        }*/
			return result;
		}
		
	}
	
//	TODO: Fix this. Transform Map<String, Optional<Employee>> 
//	to 
//	Map<String, Employee>
//	Explore using some downstream Collector for this.
	/*static void getEmpsWithMaxSalByDesignation(Stream<Employee> employeestream){
		Map<String, Optional<Employee>> employeesMap = employeestream
				.collect(Collectors.groupingBy(employee->employee.getDesignation(),
								Collectors.maxBy(Comparator.comparing(emp->emp.getSalary()))
						));
		System.out.println(employeesMap);
	}*/
	
	static void getMaxSalByDesignation(Stream<Employee> employeestream){
		Map<String, Optional<Integer>> employeesMap = employeestream
				.collect(Collectors.groupingBy(employee->employee.getDesignation(),
						Collectors.mapping(emp->((Employee)emp).getSalary(), Collectors.maxBy(Comparator.comparing(Function.identity()))
						)));
		System.out.println(employeesMap);
	}
	
	static void groupByDesignation(Stream<Employee> employeestream){
		Map<Object, List<Employee>> employeesMap = employeestream
				.collect(Collectors.groupingBy(employee->employee.getDesignation()));
		System.out.println("Keys:::");
		employeesMap.keySet().forEach(System.out::println);
		
		System.out.println("\nValues:::");
		employeesMap.values().forEach(System.out::println);
	}
	
	static void getCountByDesignation(Stream<Employee> employeestream){
		Map<String, Long> collect = employeestream
				.collect(Collectors.groupingBy(employee->employee.getDesignation(), Collectors.counting()));
		System.out.println(collect);
	}

	static void getCommaSeparatedFemaleEmployeeNames(Stream<Employee> employeestream) {
		// Collectors.joining()
		String femaleemployees = employeestream.filter(employee->employee.getGender().equals(GenericConstants.Gender.FEMALE)).map(employee->employee.getName()).collect(Collectors.joining(", "));
		System.out.println("female employees:"+femaleemployees);
	}
	static void getMalesWithAgeRestriction(Stream<Employee> employeestream){
		Predicate<Employee> pred = employee -> employee.getAge()>35; 
		List<String> maleemployees = 
				employeestream.filter(employee -> employee.getGender().equals(GenericConstants.Gender.MALE))
				.filter(pred)
				.map(employee -> employee.getName())
				.sorted()
				.collect(Collectors.toList());
		System.out.println("Male employees with Age>35:");
		maleemployees.forEach(System.out::println);		
	}
	
	static void createemployeesMap(Stream<Employee> employeestream){
		Map<String, Employee> employeesMap = employeestream
				.collect(Collectors.toMap((Employee employee)-> employee.getName(), (Employee employee)-> employee));
		
		System.out.println("Keys:::");
		employeesMap.keySet().forEach(System.out::println);
		
		System.out.println("\nValues:::");
		employeesMap.values().forEach(System.out::println);		
	}
}

