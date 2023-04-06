package com.mydemo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mydemo.TestSomething.GenericArrayOps.Direction;
import com.mydemo.dummy.DummyOuterClass;

class Person {
	String name, address, phone;
	int salary;

	public Person(String name, String address, String phone, int salary) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.salary = salary;
	}

	public Person(String name, String address, String phone) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public boolean equals(Object obj) {
		if (obj == null || !(this.getClass().getName().equals(obj.getClass().getName())))
			return false;
		Person p = (Person) obj;
		return p.name.equals(name);// && p.address.equals(address) &&
									// p.phone.equals(phone);
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

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return name + " lives in " + address + "." + ((salary == 0) ? "" : name + " earns " + salary + " per month.");
	}

}

class Employee implements Comparable {
	String name, address, phone;
	int salary;

	public Employee(String name, String address, String phone, int salary) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.salary = salary;
	}

	@Override
	public int compareTo(Object obj2) {
		Employee otherEmp = (Employee) obj2;
		// return name.compareTo(otherEmp.name); // Sort by employee name
		// return address.compareTo(otherEmp.address); // Sort by employee
		// address
		// return salary-otherEmp.salary; // Sort by employee salary
		int flag = name.compareTo(otherEmp.name);
		if (flag == 0)
			flag = salary - otherEmp.salary; // Sort by employee name, then
												// salary
		return flag;
	}

	public boolean equals(Object obj) {
		if (obj == null || !(this.getClass().getName().equals(obj.getClass().getName())))
			return false;
		Person p = (Person) obj;
		return p.name.equals(name);// && p.address.equals(address) &&
									// p.phone.equals(phone);
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

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return name + " lives in " + address + "." + ((salary == 0) ? "" : name + " earns " + salary + " per month.");
	}
}

class EmployeeComparator implements Comparator {

	@Override
	public int compare(Object obj1, Object obj2) {
		Employee emp = (Employee) obj1;
		Employee otherEmp = (Employee) obj2;
		// return emp.name.compareTo(otherEmp.name); // Sort by employee name
		// return emp.address.compareTo(otherEmp.address); // Sort by employee
		// address
		// return emp.salary-otherEmp.salary; // Sort by employee salary
		int flag = emp.name.compareTo(otherEmp.name);
		if (flag == 0)
			flag = emp.salary - otherEmp.salary; // Sort by employee name, then
													// salary
		return flag;
	}
}

class OuterClass {
	public static class MyStaticClass {
		void sayHello() {
			System.out.println("hi");
		}
	}
}

class MySingleTonClass {
	private static MySingleTonClass singleTonObj;
	private static boolean isFirstThread = true;

	private MySingleTonClass() {
	}

	public static MySingleTonClass getInstance() {
		if (singleTonObj == null) {
			// Introduced intentional delay for the first thread, so second
			// thread is also allowed inside this block.
			// This is just to make sure getInstance() returns a new object 2nd
			// time around, thus defeating purpose of a singleton.
			// This drives home the concept why using synchronized is necessary
			// while implementing singleton design pattern.
			if (isFirstThread) {
				isFirstThread = false;
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
				}
			}
			singleTonObj = new MySingleTonClass();
		}
		return singleTonObj;
	}
}

class MySingletonManagerClass implements Runnable {

	private MySingleTonClass singletonObject = null;

	public MySingleTonClass getSingletonObject() {
		return singletonObject;
	}

	public void run() {
		singletonObject = MySingleTonClass.getInstance();
	}
}

class MySuperClass {
	protected String className = "super class";

	public MySuperClass() throws NullPointerException {
		System.out.println("Inside Super");
		// throw new NullPointerException("");
	}

}

class MySubClass extends MySuperClass {
	protected String className = "sub class";

	public MySubClass() {
		try {
			// super();
		} catch (Exception e) {
			System.out.println("Here");
		}
		System.out.println("Inside Subclass");
	}

}

class TestSuper {
	protected void throwsNPException() throws ArithmeticException {
		// /protected void throwsNPException() throws IOException{
	}

	protected MySuperClass returnCoVariant() {
		return new MySuperClass();
	}
}

interface ISomeInterface<T> {
	void set(T t);

	T get();
}

class SomeImpl implements ISomeInterface<String> {
	String str;

	public SomeImpl() {
		str = "Mahesh";
	}

	public void set(String t) {
		str = t;
	}

	public String get() {
		return str;
	}
}

class ClassWithProtectedMethod {
	protected void display() {
		System.out.println("Hi");
	}
}

class InheritedClassWithProtectedMethod extends ClassWithProtectedMethod {
}


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface FieldMapper {
	String SF_Field_Name();

	String SAP_Field_Name();
}

class FieldMapperExample {
	@FieldMapper(SF_Field_Name = "Chintu", SAP_Field_Name = "Pintu")
	private String accountName;

	@FieldMapper(SF_Field_Name = "Ship_Address", SAP_Field_Name = "shippingAddress")
	private String address;

	public FieldMapperExample(String accountName, String address) {
		super();
		this.accountName = accountName;
		this.address = address;
	}

}

public class TestSomething extends TestSuper {
	static Hashtable<String, City> cities = null;

	public enum LogType {
		DEBUG, INFO, WARN, ERROR, EXCEPTION;
	}

	private static void singleTon() throws InterruptedException {
		MySingletonManagerClass manager1 = new MySingletonManagerClass();
		MySingletonManagerClass manager2 = new MySingletonManagerClass();
		Thread singletonThreadManager1 = new Thread(manager1);
		Thread singletonThreadManager2 = new Thread(manager2);
		singletonThreadManager1.start();
		singletonThreadManager2.start();
		singletonThreadManager1.join();
		singletonThreadManager2.join();

		MySingleTonClass obj1 = manager1.getSingletonObject();
		MySingleTonClass obj2 = manager2.getSingletonObject();

		System.out.println("Objects equal?: " + (obj1 == obj2));
		System.out.println("Object 1: " + obj1);
		System.out.println("Object 2: " + obj2);
	}

	private static void generic() {
		SomeImpl impl = new SomeImpl();
		impl.set(impl.get());
	}

	private byte[] serializeOffers() throws IOException, ClassNotFoundException {
		Hashtable offers = new Hashtable();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream objOut = new ObjectOutputStream(out);
		objOut.writeObject(offers);
		objOut.close();
		return out.toByteArray();
	}

	private Hashtable deSerializeOffers(byte[] arr) throws IOException, ClassNotFoundException {
		ObjectInputStream objIn = new ObjectInputStream(new ByteArrayInputStream(arr));
		return (Hashtable) objIn.readObject();
	}

	@Override
	protected MySubClass returnCoVariant() {
		return new MySubClass();
	}

	private void coVariantReturn() {
		MySubClass object = returnCoVariant();
		System.out.println(object.className);
		System.out.println(returnCoVariant().className);
		System.out.println((super.returnCoVariant()).className);
	}

	private void coVariantOverride() {
		class Test1 {
			public double aMethod(float a, float b) throws IOException {
				return (float) 4.2;
			}
		}
		class Test2 extends Test1 {
			public float aMethod(double a, float b) throws IOException {
				return (float) 5.2;
			}
		}
	}

	static Vector<String> createVectorFromArray() {
		String[] strArr = { "Mahesh", "Akhileshwar", "Shivali", "Aditi" };
		// To get List out of array
		List<String> strList = Arrays.asList(strArr);
		//
		Vector<String> v = new Vector<String>();
		v.addAll(strList);
		return v;

	}

	static void stringBuilderPerformance() {
		int N = 77777777;
		long t;

		{
			StringBuffer sb = new StringBuffer();
			t = System.currentTimeMillis();
			for (int i = N; i-- > 0;) {
				sb.append("");
			}
			System.out.println("Time taken (StringBuffer):" + (System.currentTimeMillis() - t));
		}

		{
			StringBuilder sb = new StringBuilder();
			t = System.currentTimeMillis();
			for (int i = N; i-- > 0;) {
				sb.append("");
			}
			System.out.println("Time taken (StringBuilder):" + (System.currentTimeMillis() - t));
		}
	}

	// TODO: Difficult to simulate concept of volatile in multi-threaded env.
	class VolatileChanger extends Thread {
		// volatile boolean isDone = false; // Uncomment this
		boolean isDone = false; // Comment this
		boolean isFirstThread = true;

		@Override
		public void run() {
			if (isFirstThread) {
				isFirstThread = false;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
				System.out.println("Thread1:" + isDone);
			} else {
				synchronized (VolatileChanger.class) {
					isDone = true;
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
					}
				}
				System.out.println("Thread2:" + isDone);
			}
		}
	}

	private void volatileTest() {
		VolatileChanger obj = new VolatileChanger();
		Thread t1 = new Thread(obj);
		Thread t2 = new Thread(obj);
		t1.start();
		t2.start();
	}

	class HashCodeTest {
		private String name;
		String address;

		public HashCodeTest(String name, String address) {
			super();
			this.name = name;
			this.address = address;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public int hashCode() {
			return 31 * name.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null || !(this.getClass().getName().equals(obj.getClass().getName())))
				return false;
			HashCodeTest p = (HashCodeTest) obj;
			return (name == null ? p.name == null
					: name.equals(p.name) && (address == null ? p.address == null : address.equals(p.address)));
		}
	}

	private static void mapWithNoEqualsOrHashCode() {
		MySuperClass cls = new MySuperClass();
		Map map = new HashMap();
		map.put(cls, "TestString");

		MySuperClass cls2 = new MySuperClass();
		System.out.println(map.get(cls2));
	}

	private void hashmap() {
		HashMap<HashCodeTest, String> map = new HashMap<HashCodeTest, String>();

		// Theory: Unequal objects may have same hash codes
		// For example, String's impl of hashCode() gets us same hashcode for
		// "Aa" & "BB"
		System.out.println("Aa".hashCode());
		System.out.println("BB".hashCode());
		// kTheory ends

		HashCodeTest person1 = new HashCodeTest("Mahesh", "Thane");
		System.out.println(
				"person1:(" + person1 + "):" + person1.hashCode() + ":" + Integer.toHexString(person1.hashCode()));
		map.put(person1, "A value object");

		HashCodeTest person2 = new HashCodeTest("Mahesh", "Mumbai");
		map.put(person2, "A different object");
		HashCodeTest searchPerson = new HashCodeTest("Mahesh", "Thane");
		System.out.println("The value mapped to key is: " + map.get(searchPerson));

		System.out.println(
				"person2:(" + person2 + "):" + person2.hashCode() + ":" + Integer.toHexString(person2.hashCode()));
		System.out.println("original hashcodes: person1 -(" + System.identityHashCode(person1) + ") person2 -("
				+ System.identityHashCode(person2) + ")");
		System.out.println("Are objects equal? " + (person1 == person2));
	}

	static TestSomething ts = new TestSomething();

	public static void main(String[] args) throws Exception {
		// byPassIteratorOnCollectionAdd();
		// ts.graphImpl();
		// runtimeException();
		// printClassNameFromStackTrace();
		// new CannotInstantiate();
		// parseXmlFile();
		// printClassName(new SomeClass());
		// printClassName(TestSomeThing.class);
		// System.out.println("DEBUG is:: "+LogType.DEBUG+">>"+LogType.INFO);
		// enumToMap();
		// nullInteger();
		// listContainment();
		// classSynchronized();
		// OuterClass.MyStaticClass cls = new OuterClass.MyStaticClass();
		// cls.sayHello();
		// singleTon();
		// ts.coVariantReturn();
		// ts.coVariantOverride();
		// generic();
		// createVectorFromArray();
		// stringBuilderPerformance();
		// ts.volatileTest();
		// printF();
		// passByRef();

		//---Tests around maps & hashcode()
		// setMethodInaccessible();
		// ts.hashmap();
		// mapWithNoEqualsOrHashCode();
		// calculateHashCode();
		// equals();
		// equalsWithNoHashCodeImpl();
		// equalsWithSubClassImpl();
		   retrieveMapEntryWithKeyHavingChangedMutableAttribute();
		
		// varArgs();
		// generateRandomString(9);
		// constructorThrowsException();
		// regEx();
		// cyclicEnum();
		// invokeThreadTwice();
		// callWeatherCloudService();
		// failObjectOrientation();
		// dateWithinRange();
		// enumTest();
		// cloneTest();
		// multipleInterfaceResolution();
		// covariance();
		// rotateArrayElements();
		// genericsSuper();
		// defaultImplementation();
		// conflictWithDefaultImplementation();
		// overrideAbstractMethodAndMakeDefault();
		// mongoDBAccess();
		// dBConnection();
		// stdIn();
		// stream();
		// annotation();
		// notIf();
		// optional();
		// concurrentHashMap();
		// classNotFoundException();

		// classWithNoAccessibleDefaultConstructor();
		// breakSingletonByCloning();
		// reverseStackUsingRecursion();
		// implementArrayAsStack();
		// reverseStringByRecursion();
		// implementStackUsingQueue();
		// implementLinkedListUsingArray();
		// implementQueueUsingStack();
		// subvertStack();

		// sortedMap();
		// stringIterator();
		// treeSet();
		// treeMap();
		// diamondProbUsingMultipleInheritance();

		// --- Functional programming---
		// createThreadUsingLambdaExpr();
		// inheritanceInFunctionalInterface();
		// ts.recursiveUsingLambda();
		// ts.sAMWithDefaultFunction();
		// functionalInterface();
		// functionalInterface2();
		// higherOrderFunction();
		
		// dOSWithThreadSync();
		// Simulation of why we shouldn't use object lock String for
		// synchronization...
		// dOSWithThreadSyncUsingStringObject();

		// interruptThread();
		// defaultUncaughtExceptionHandler();
		// deadLock();
		// liveLock();
		// getAllThreadNames();
		// reEntrantLock();
		// getLockFromWaitingThread();
		// cloneCollection();
		// predicates();
		// findNumbersGreaterThanAverage();
		// convert12HourTime();
		// concurrentAPI();
		// latchCounterExample();
		// semaphore();
		// lambdaForNumericOps();
		// generateRandomNames();

		/*
		 * String names[] = generateRandomNames();
		 * SortingAlgos.stringInsertSort(names); ts.binarySearchName(names,
		 * "Edward");
		 */
		// sortingAlgos();
		// removeReference();
		// System.out.println(new int[2].getClass().getName());
		//escapeRefFromConstructor();
		//testHashtableLoadFactorByExtendingMap();
		//testHashtableLoadFactorByUsingConsumerPredicate();
		//calculateSumOfNumbersDivisibleBy3And5();
		// formatMessage();
	}

	static void retrieveMapEntryWithKeyHavingChangedMutableAttribute() {

		class EmployeeKey{
			private String id, name, address;

			public EmployeeKey(String id, String name, String address) {
				super();
				this.id=id; 
				this.name = name;
				this.address = address;
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

			@Override
			public String toString() {
				return "EmployeeKey [id=" + id + ", name=" + name + ", address=" + address + "]";
			}

			@Override
			public int hashCode() {
				return 31 * id.hashCode();
			}

			@Override
			public boolean equals(Object obj) {
				if (obj == null || !(this.getClass().getName().equals(obj.getClass().getName())))
					return false;
				EmployeeKey p = (EmployeeKey) obj;
				return (id == null ? p.id == null : id.equals(p.id));
			}
		}
		
		EmployeeKey keyObj = new EmployeeKey("M1491", "Mahesh", "Thane");
		HashMap<EmployeeKey, String> empMap = new HashMap<EmployeeKey, String>();
		empMap.put(keyObj, "Mahesh Balasubramanian");
		
		System.out.println(keyObj.hashCode()+":"+keyObj);
		
		// Update map with a new key object having some different value for mutable attribute of the key object.
		keyObj = new EmployeeKey("M1491", "Rakesh", "Thane");
		empMap.put(keyObj, "Rakesh Jadhav");
		for(EmployeeKey key : empMap.keySet()) {
			System.out.println(key.hashCode()+":"+key);
		}
	}

	// Format a string just like they do in C language.
	private static void formatMessage(){
		int num = 786;
		System.out.println(String.format("Number :%22d", num));
		System.out.println(String.format("Number :%22.2f", 123.45));
		System.out.println(String.format("Padded :%22s", "Right aligned"));
		System.out.println(String.format("Boolean:%22b", true));
	}

	public static void calculateSumOfNumbersDivisibleBy3And5() {
			// Sum of numbers between 1 & 5678 that are divisible by both 3 & 5
			int MAXNUMBER = 123456;
			int sum=0;
			int divisor = 3 * 5;

			//Approach #1
			long startTime = System.currentTimeMillis();
			for(int i=0; i<MAXNUMBER; i+=divisor) {
				sum += i;
			}
			long endTime = System.currentTimeMillis();
			System.out.println("Total:" + sum);
			double totalTimeTaken = (endTime-startTime);
			System.out.println("Total time taken (ms):"+ totalTimeTaken);

			// Approach #2
			sum=0;
			startTime=endTime;
			for(int i=0; i<MAXNUMBER; i++) {
				if(i%3==0 && i%5==0)
					sum += i;
			}
			System.out.println("Total:" + sum);
			endTime = System.currentTimeMillis();
			totalTimeTaken = (endTime-startTime);
			System.out.println("Total time taken (ms):"+ totalTimeTaken);
	}

	private static void getHashMapCapacity(Map m) {
		try {
			Field tableField = Hashtable.class.getDeclaredField("table");
			tableField.setAccessible(true);
			Object[] table = (Object[]) tableField.get(m);
			int length = table == null ? 0 : table.length;
			//System.out.println("Length: "+table == null ? 0 : table.length); // TODO: Why this gives diff output??
			System.out.println("Size: " + m.size() + ", Capacity: " + length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testHashtableLoadFactorByExtendingMap(){
		Map<String, Employee> empTable = new Hashtable<String, Employee>(4, (float)0.5){
			Field tableField = null;
			{
				try {
					tableField = Hashtable.class.getDeclaredField("table");
					tableField.setAccessible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public synchronized Employee put(String key, Employee value) {
				try {
					Object[] table = (Object[]) tableField.get(this);
					int length = table == null ? 0 : table.length;
					System.out.println("Size: " + size() + ", Capacity: " + length);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return super.put(key, value);
			}
		};

		empTable.put("Mahesh", new Employee("Mahesh", "Thane", "12346", 70000));
		empTable.put("Shivali", new Employee("Shivali", "Ambarnath", "81910", 20000));
		empTable.put("Mahendra", new Employee("Mahendra", "Bhivandi", "111213", 10000));
		empTable.put("Akhil", new Employee("Akhileshwar", "Airoli", "141516", 45000));
		empTable.put("Nirav", new Employee("Nirav", "Ghatkopar", "171819", 65000));
	}


	private static void testHashtableLoadFactorByUsingConsumerPredicate(){
		Hashtable<String, Employee> empTable = new Hashtable<String, Employee>(4, (float)0.5);

		Consumer<Map<String, Employee>> showMapCapacity = (Map<String, Employee> m)->{
			try {
				Field tableField = Hashtable.class.getDeclaredField("table");
				tableField.setAccessible(true);
				Object[] table = (Object[]) tableField.get(m);
				int length = table == null ? 0 : table.length;
				//System.out.println("Length: "+table == null ? 0 : table.length); // TODO: Why this gives diff output??
				System.out.println("Size: " + m.size() + ", Capacity: " + length);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};

		showMapCapacity.accept(empTable); //1 (size:0, capacity:4)
		empTable.put("Mahesh", new Employee("Mahesh", "Thane", "12346", 70000));
		empTable.put("Shivali", new Employee("Shivali", "Ambarnath", "81910", 20000));
		showMapCapacity.accept(empTable); //2 (size:2, capacity:4)
		empTable.put("Mahendra", new Employee("Mahendra", "Bhivandi", "111213", 10000));
		showMapCapacity.accept(empTable); //3 (size:3, capacity:9)
		empTable.put("Akhil", new Employee("Akhileshwar", "Airoli", "141516", 45000));
		showMapCapacity.accept(empTable); //4 (size:4, capacity:9)
		empTable.put("Nirav", new Employee("Nirav", "Ghatkopar", "171819", 65000));
		showMapCapacity.accept(empTable); //5 (size:5, capacity:19)
	}

	class RefEscapeOtherClass{
		public void doSomething(RefEscapeDemo demoObj){
			System.out.println("Other class: " + demoObj.getSomeStr());
			// Allow object construction to complete
			try {
				Thread.sleep(2000);
			}
			catch (InterruptedException e) {}
			System.out.println("Other class: " + demoObj.getSomeStr());
		}
	}

	class RefEscapeDemo{
		private String someStr;

		// A class might assume that some methods will only be called
		// after the instance is fully initialized, but the external code is likely
		// to break those assumptions.

		public RefEscapeDemo(String someStr) {
			// The string someStr is yet to be initialized...
			// Before it is, I call some method on an object outside of this class, passing this instance.

			RefEscapeOtherClass otherObj = new RefEscapeOtherClass();
			//otherObj.doSomething(this);
			// Intentional delay to simulate "this" escaping
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
			}

			// Now set the string..
			this.someStr = someStr;
			otherObj.doSomething(this);
		}

		public String getSomeStr() {
			return someStr;
		}
	}

	static void escapeRefFromConstructor(){
		ts.new RefEscapeDemo("Some String");
	}

	static class MyLinkedList<T>{
		T data;
		MyLinkedList<T> nextNode;

		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}

		void add(T t){
			if(data==null)
				setData(t);

			else while(nextNode!=null){
				nextNode = nextNode.nextNode;
			}
			nextNode = new MyLinkedList<T>();
			nextNode.setData(t);
		}
		@Override
		public String toString() {
			StringBuffer str = new StringBuffer();
			while(nextNode!=null){
				str.append(getData());
				if(nextNode!=null)
					str.append(" -> ");
				nextNode = nextNode.nextNode;
			}
			return str.toString();
		}

	}

	// TODO: Fix this. Doesn't work.
	public static void implementLinkedListUsingArray(){
		MyLinkedList<String> object = new MyLinkedList<String>();

		// Adding elements to the linked list
		object.add("A");
		object.add("B");
		object.add("F");
		object.add("G");
		System.out.println("Linked list : " + object);
		// Removing elements from the linked list
		//object.remove("B");
	}

	public static void removeReference(){
		Employee emp1 = new Employee("Mahesh", "Thane", "12346", 70000);
		Employee emp2 = emp1;
		System.out.println(emp1);
		System.out.println(emp2);
		emp1 = new Employee("Shivali", "Ambarnath", "81910", 20000);
		Employee emp3 = emp2;
		emp2 = null;
		System.out.println(emp1);
		System.out.println(emp2);
		System.out.println(emp3);
	}

	//TODO: Given a sorted set of names of people, search a name using binary search algorithm.
	boolean binarySearchName(String[] names, String name){
		GenericArrayOps<String> stringArrOps = new GenericArrayOps<String>(names);
		stringArrOps.displayArray();
		// Take one element as root
		int midElementIndex = names.length/2;
		// Now start searching
		String names2[] = null;
		if(midElementIndex%2==0)
			names2 = new String[midElementIndex];
		else
			names2 = new String[midElementIndex+1];
		Arrays.fill(names2,"ZZZZZ");
		if(names[midElementIndex].equals(name)){
			System.out.println("\nName: "+name+" found at index: "+midElementIndex);
			return true;
		}
		else if(names.length==1){
			return false;
		}
		if(name.compareTo(names[midElementIndex])<0){
			//Arrays.binarySearch(a, key)
			System.arraycopy(names, 0, names2, 0, midElementIndex);
			return binarySearchName(names2, name);
		}
		else if(name.compareTo(names[midElementIndex])>0){
			System.arraycopy(names, midElementIndex, names2, 0, names.length - midElementIndex);
			return binarySearchName(names2, name);
		}
		System.out.println("\nCould not find name: "+name);
		return false;
	}

	// Given a sorted list of people names, generate random array (unsorted)
	private static String[] generateRandomNames(){
		String names[] = { "Ashley", "Ashton", "Bailey", "Braxton", "Edward", "Emerson", "Emery", "Everett", "Everly",
				"Faith", "Hadley", "Hailey", "Harrison", "Hayden", "Ivy", "Jade", "Jameson", "King", "Kingston",
				"Kinsley", "Maxwell", "Piper", "River", "Ruby", "Ryder", "Steven", "Sydney", "Wesley", "Weston",
				"Willow" };
		Random random = new Random();
		String settledIndexes = "";

		String []names2 = new String[names.length];
		for(int count = 0; count<names.length;){
			int index = random.nextInt(names.length);
			String tempStr = "#"+index+"#";
			if(!settledIndexes.contains(tempStr)){
				settledIndexes += tempStr;
				names2[count] = names[index];
				count++;
			}
		}
		/*for(String name:names2)
			System.out.println(name);*/

		return names2;
	}

	static class SortingAlgos {

		static private void showArray(int[] arr) {
			System.out.println();
			for (int val : arr)
				System.out.print(val + " ");
		}

		/*
		 Quick sort example - taking leftmost element (26) as the pivot:

			Unsorted list:

			26, 33, 42, 10, 14, 19, 27
			pivot = 26
			j	i
			1	1
			2	1
			3

			10<26=true
			swap(1, 3)
			26, 10, 42, 33, 14, 19, 27
				2
			3	2
			4
			swap(2, 4)
			26, 10, 14, 33, 42, 19, 27
				3
			5
			swap(3, 5)
			26, 10, 14, 19, 42, 33, 27
				4
			6	4

			Now swap(pivot, i-1)
			=swap(pivot, 3)

			19, 10, 14, 26, 42, 33, 27
		 */

		// Input:
				// 26, 33, 42, 10, 14, 19, 27, 44, 35, 31

		// Expected output:
				// 26, 33, 42, 10, 14, 19, 27, 44, 35, 31
		static private int partition(int[] arr, int startIndex, int endIndex){
			int pivot = arr[endIndex];
			int temp = 0;
			int i = j = endIndex-1;
			for(; i>=startIndex; i--){
				/*if(pivot<0)
					break;*/
				if(arr[i]>pivot){
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
				j--;
			}
			temp = arr[j+1];
			arr[j+1] = arr[endIndex];
			arr[endIndex] = temp;
			return j+1;
		}

		// Time complexity:

		// Input:
		// 26, 33, 42, 10, 14, 19, 27, 44, 35, 31

		// Expected output:
		// 26, 33, 42, 10, 14, 19, 27, 31, 44, 35
		static void quickSort(int[] arr) {
			showArray(arr);
			applyQuickSort(arr, 0, arr.length-1);
			showArray(arr);
		}

		//TODO: Fix this quick sort
		static void applyQuickSort(int[] arr, int startIndex, int endIndex) {
			if(startIndex < endIndex){
				int pivot = partition(arr, startIndex, endIndex);
				applyQuickSort(arr, 0, pivot-1);
				//26, 33, 42, 10, 14, 19, 27
				applyQuickSort(arr,pivot+1, arr.length-1);
			}
		}

		static void stringInsertSort(String[] arr) {
			GenericArrayOps<String> stringArrOps = new GenericArrayOps<String>(arr);
			stringArrOps.displayArray();
			int totalInversions = 0;
			for (int i = 1; i < arr.length; i++) {
				int hole = i;
				String value = arr[i];
				while (hole > 0 && arr[hole - 1].compareTo(arr[hole])>0) {
					arr[hole] = arr[hole - 1];
					arr[hole - 1] = value;
					hole--;
					totalInversions++;
				}
				// showArray(arr);
			}
			System.out.println("\nTotal inversions:" + totalInversions);
			//stringArrOps.displayArray();
		}

		// Time complexity: O(n^2)
		static void selectionSort(int arr[]){
			showArray(arr);
			//n=10, Time complexity=? 40
			// Time complexity: O(n)
			// n-1* ((n-1)/2)
			// 9 * 9-1/2 = 36
			// 10 *
			int iterationCount = 0;
			for (int i=0;i<arr.length-1;i++){
				int lowestNumIndex = i;
				// Time complexity: (n-2), (n-3)...2,1
				// 9, 8, 7, 6, 5, 4, 3, 2, 1
				for (int j=i+1;j<arr.length;j++){
					if(arr[j] < arr[lowestNumIndex]){
						lowestNumIndex = j;
					}
					iterationCount++;
				}
				if(lowestNumIndex!=i){
					int temp = arr[lowestNumIndex];
					arr[lowestNumIndex] = arr[i];
					arr[i] = temp;
				}
			}
			System.out.println("Iterations: " + iterationCount);
			showArray(arr);
		}

		// Time complexity:
		static void insertSort(int[] arr) {
			showArray(arr);
			int totalInversions = 0;
			for (int i = 1; i < arr.length; i++) {
				int hole = i;
				int value = arr[i];
				while (hole > 0 && arr[hole - 1] > arr[hole]) {
					arr[hole] = arr[hole - 1];
					arr[hole - 1] = value;
					hole--;
					totalInversions++;
				}
				// showArray(arr);
			}
			System.out.println("\nTotal inversions:" + totalInversions);
			showArray(arr);
		}
	}

	static class LoginQueueUsingSemaphore {
		private Semaphore semaphore;
		public LoginQueueUsingSemaphore(int slotLimit) {
			semaphore = new Semaphore(slotLimit);
		}
		boolean tryLogin() {
			return semaphore.tryAcquire();
		}
		void logout() {
			semaphore.release();
		}
		int availableSlots() {
			return semaphore.availablePermits();
		}
	}

	private static void lambdaForNumericOps() throws IOException {
		MyMath ob = new MyMath();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		PerformOperation op;
		boolean ret = false;
		String ans = null;
		while (T-- > 0) {
			String s = br.readLine().trim();
			StringTokenizer st = new StringTokenizer(s);
			int ch = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			if (ch == 1) {
				op = ob.isOdd();
				ret = ob.checker(op, num);
				ans = (ret) ? "ODD" : "EVEN";
			} else if (ch == 2) {
				op = ob.isPrime();
				ret = ob.checker(op, num);
				ans = (ret) ? "PRIME" : "COMPOSITE";
			} else if (ch == 3) {
				op = ob.isPalindrome();
				ret = ob.checker(op, num);
				ans = (ret) ? "PALINDROME" : "NOT PALINDROME";
			}
			System.out.println(ans);
		}
	}

	static interface PerformOperation {
		boolean check(int a);
	}

	static class MyMath {
		public static boolean checker(PerformOperation p, int num) {
			return p.check(num);
		}

		PerformOperation isOdd(){
			return (n)->{return n%2==1;};
		}

		PerformOperation isPrime() {
			return (n) -> {
				if (n <= 1)
					return false;
				for (int i = 2; i < n; i++)
					if (n % i == 0)
						return false;

				return true;
			};
		}

		PerformOperation isPalindrome(){
			return (n)->{
				String numStr = ""+n;
				int leftIndex = 0;
				int rightIndex = numStr.length()-1;
				while(leftIndex < rightIndex){
					if(numStr.charAt(leftIndex++) != numStr.charAt(rightIndex--))
						return false;
				}
				return true;
			};
		}
	}

	static void semaphore() {
		int slots = 10;
		Semaphore semaphore = new Semaphore(slots);
		System.out.println(semaphore.availablePermits());
		IntStream.range(1, slots+1).forEach((num) -> {
			boolean permitAvailable = true;
			//boolean permitAvailable = semaphore.tryAcquire();
			//System.out.println(num+":"+permitAvailable);
			if (permitAvailable){
				try {
					//semaphore.acquire();
					System.out.println(semaphore.tryAcquire() + ":" + semaphore.availablePermits());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		try {
			System.out.println(semaphore.tryAcquire() + ":" + semaphore.availablePermits());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class MyRunnable implements Runnable {
		CountDownLatch latch;

		public MyRunnable(CountDownLatch latch) {
			super();
			this.latch = latch;
		}

		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			latch.countDown();
			System.out.println(latch.getCount());
		}
	}

	private static void latchCounterExample() {
		CountDownLatch latch = new CountDownLatch(4);
		MyRunnable runnableObj = new MyRunnable(latch);

		new Thread(runnableObj).start();
		new Thread(runnableObj).start();
		new Thread(runnableObj).start();
		new Thread(runnableObj).start();
		System.out.println(latch.getCount());

		new Thread(runnableObj).start();
		System.out.println(latch.getCount());
	}

	private static void concurrentAPI() throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newFixedThreadPool(2);
		Future result = service.submit(() -> {
			// return "Hi";
		});
		service.shutdown();
		service.awaitTermination(1, TimeUnit.SECONDS);
		if (result.isDone())
			System.out.println(result.get());
	}

	private static void convert12HourTime() {
		String inputs[] = { "07:05:45PM", "11:59:59PM", "12:00:00AM", "01:30:00AM", "12:45:54PM" };

		Arrays.asList(inputs).stream().forEach((input) -> {
			System.out.println();
			String meridiem = input.substring(input.length() - 2);

			String hourStr = input.substring(0, input.indexOf(":"));
			int hour = Integer.parseInt(hourStr);
			if (hour == 12 && meridiem.equals("AM"))
				hour = 0;
			else if (hour != 12 && meridiem.equals("PM"))
				hour += 12;
			String padding = hour > 9 ? "" : "0";
			String output = padding + hour + input.substring(input.indexOf(":"), input.length() - 2);
			System.out.println("Input:  " + input + "\nOutput: " + output);
		});
	}

	// Class defined to easily measure time complexity
	static class MonitorTimeComplexity{
		long startTime = 0;

		public MonitorTimeComplexity() {
			startTime = System.nanoTime();
		}

		long getTimeElapsed(){
			long newStartTime = System.nanoTime();
			double retVal = (newStartTime - startTime)/1000000;
			startTime = newStartTime;
			return (long)retVal;
		}

		@Override
		public String toString() {
			return "\nTime elapsed: "+getTimeElapsed()+" milliseconds";
		}
	}

	// Generate an int array with random numbers
	private static int[] generateRandomArray(int totalNumbers){
		int max_range = 20;
		int [] arr = new Random().ints(totalNumbers, 1, max_range+1).toArray();
		/*for(Integer i:arr)
			System.out.println(i);*/
		return arr;
	}

	private static void sortingAlgos() {
		//int[] arr = { 5, 8, 2, 9, 10, 7, 4, 5, 6, 1, 3};
		int[] arr = generateRandomArray(10);
		MonitorTimeComplexity monitor = new MonitorTimeComplexity();

		SortingAlgos.insertSort(arr);
		System.out.println(monitor);

		SortingAlgos.selectionSort(arr);
		System.out.println(monitor);

		arr = new int[]{26, 33, 42, 10, 14, 19, 27, 44, 35, 31};
		SortingAlgos.quickSort(arr);
		System.out.println(monitor);

		/*String[] names = generateRandomNames();
		SortingAlgos.stringInsertSort(names);
		System.out.println(monitor);*/
	}

	// Given an array of numbers, find numbers that are greater than average
	private static void findNumbersGreaterThanAverage() {
		int[] numbers = { 1, 2, 4, 0, 5, 4, 6, 9, 10 };
		// Average: (3.44). Output should be 4,5,4,6,9,10

		// Find average & then sort array; get max index containing the numbers
		// lower than the average. Beyond this index lies all greater numbers.
		double averageVal = Arrays.stream(numbers).average().getAsDouble();
		System.out.println("Average value:" + averageVal);

		Arrays.stream(numbers).filter((num) -> num > averageVal).forEach(System.out::println);
	}

	private static void predicates() {
		Supplier<Integer> producer = () -> new Random().nextInt();
		Consumer<Integer> cons = (Integer i) -> System.out.println("Number: " + i);
		for (int i = 0; i < 5; i++) {
			cons.accept(producer.get());
		}
	}

	private static void cloneCollection() {
		ArrayList<String> names = new ArrayList<String>() {
			{
				add("Mahesh");
				add("Suresh");
			}
		};
		ArrayList<String> names2 = (ArrayList<String>) names.clone();
		System.out.println(names2);
	}

	static Object obj = new Object();
	static Object obj2 = new Object();

	// Try getting lock on an object held by another thread that is in
	// timed-wait state
	private static void getLockFromWaitingThread() throws InterruptedException {
		// Both main thread & new thread lock on the same object.
		Runnable runnable = () -> {
			synchronized (obj) {
				System.out.println("Starting new thread"); // (1)
				try {
					obj.wait(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("New thread finished execution"); // (3)
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
		Thread.sleep(500); // intentional delay to let new thread created above
							// get the lock & go into timed wait

		synchronized (obj) {
			System.out.println("Main thread got hold of obj lock"); // (2)
			// obj.wait(5000); // Relinquishes the execution
			Thread.sleep(5000); // Keeps the lock & waits
			System.out.println("Main thread finished"); // (4)
		}
	}

	private static void reEntrantLock() {
		Runnable runnable = () -> {
			String threadName = Thread.currentThread().getName();
			dummyMethod1(threadName);
		};
		Thread t1 = new Thread(runnable, "Thread#1");
		Thread t2 = new Thread(runnable, "Thread#2");

		t1.start();
		t2.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Consumer<Thread> showThreadStat = (thread) -> System.out.println(thread.getName() + ":" + thread.getState());

		showThreadStat.accept(t1);
		showThreadStat.accept(t2);
		//

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		showThreadStat.accept(t1);
		showThreadStat.accept(t2);
	}

	// Dummy method to  re-entrant lock
	private synchronized static void dummyMethod1(String threadName) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(threadName + " : executing method1");
		dummyMethod2(threadName);
	}

	// Dummy method to  re-entrant lock
	private synchronized static void dummyMethod2(String threadName) {
		System.out.println(threadName + " : executing method2");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void getAllThreadNames() {
		// Creating 2 dummy threads
		Runnable runnable = () -> {
			String threadName = Thread.currentThread().getName();
			// System.out.println(threadName + " Started.");

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// System.out.println(threadName + " Ended.");
		};

		new Thread(runnable, "Thread#1").start();
		new Thread(runnable, "Thread#2").start();

		// Following line gets a list of all the running Threads in the JVM
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		for (Thread thread : threadSet) {
			System.out.println(thread.getName() + ":" + thread.getPriority());
		}
	}

    private static void higherOrderFunction(){
    	// Higher order function - a function that returns another function.
        Function<Integer, Function<Integer,Integer>> makeAdder = x -> y -> x + y;
        System.out.println(makeAdder.apply(18).apply(12));
    }
	
	private static void functionalInterface2() {
		MySubDefault mySubClass = () -> {
			System.out.println("Inside display()...");
		};
		mySubClass.display();
	}

	interface MySuperAbstract {
		void aMethod();
	}

	interface MySubDefault extends MySuperAbstract {
		@Override
		default void aMethod() {
			System.out.println("Message from method overridden as a default method..");
			display();
		}

		void display();
	}

	static int i = 1;
	static int j = 1;

	@FunctionalInterface
	interface SAMInterface {
		void someMethod();

		default void someOtherMethod() {
			System.out.println("someOtherMethod():" + j++);
			someMethod();
		}
	}

	private void sAMWithDefaultFunction() {
		SAMInterface intf = () -> {
			System.out.println("someMethod():" + (i++));
		};
		intf.someOtherMethod();
	}

	private static void treeMap() {
		SortedMap<String, Employee> map = new TreeMap<String, Employee>();
		map.put("M1491", new Employee("Mahesh", "Thane", "12346", 70000));
		map.put("S2457", new Employee("Shivali", "Jogeshwari", "78910", 35000));
		map.put("S1456", new Employee("Shivali", "Ambarnath", "81910", 20000));
		map.put("M3145", new Employee("Mahesh", "Airoli", "111213", 60000));
		map.put("A1564", new Employee("Akhileshwar", "Airoli", "141516", 45000));
		map.put("N1389", new Employee("Nirav", "Ghatkopar", "171819", 65000));

		map.keySet().stream().forEach((String k) -> {
			Employee emp = map.get(k);
			System.out.println(k + ": " + emp.getName() + " - " + emp.getAddress());
		});

		final SortedMap<String, Employee> map2;
		// Take a subset of the map (Returns a portion from this map whose keys
		// are strictly LESS than the specified key).
		map2 = map.headMap("N1389");

		// Take a subset of the map (Returns a portion from this map whose keys
		// are greater than or equal to the specified key).
		// map2 = map.tailMap("N1389");

		// Take a subset of the map (Returns a portion whose keys are in range
		// between - the inclusive "from" key to the exclusive "to" key).
		// map2 = map.subMap("M3145", "S2457");

		map2.keySet().stream().forEach((String k) -> {
			Employee emp = map2.get(k);
			// System.out.println(k + ": " + emp.getName() + " - " +
			// emp.getAddress());
		});
	}

	private static void treeSet() {
		SortedSet<Employee> set = new TreeSet();
		// SortedSet<Employee> set = new TreeSet(new EmployeeComparator());
		List<Employee> list = new ArrayList<Employee>();
		list.add(new Employee("Mahesh", "Thane", "12346", 70000));
		list.add(new Employee("Shivali", "Jogeshwari", "78910", 35000));
		list.add(new Employee("Shivali", "Ambarnath", "81910", 20000));
		list.add(new Employee("Mahesh", "Airoli", "111213", 60000));
		list.add(new Employee("Akhileshwar", "Airoli", "141516", 45000));
		list.add(new Employee("Nirav", "Ghatkopar", "171819", 65000));
		set.addAll(list);
		set.stream().forEach(System.out::println);
	}

	static class StringIterator implements Iterator<Character> {
		String theString;
		boolean isStringNullOrEmpty = true;
		int index = 0;

		public StringIterator(String theString) {
			this.theString = theString;
			isStringNullOrEmpty = theString == null || theString.isEmpty();
		}

		@Override
		public boolean hasNext() {
			return !isStringNullOrEmpty && index < theString.length();
		}

		@Override
		public Character next() {
			return theString.charAt(index++);
		}

	}

	private static void stringIterator() {
		StringIterator iterator = new StringIterator("Mahesh");
		while (iterator.hasNext())
			System.out.println(iterator.next() + ", ");
	}

	private static void sortedMap() {
		SortedMap<String, Integer> map = new TreeMap<String, Integer>();
		map.put("Mahesh", 40);
		map.put("Varun", 8);
		map.put("Mukul", 38);
		map.put("Avi", 42);

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

	private static void reverseStackUsingRecursion() {
		Stack stack = new Stack();
		stack.push(12);
		stack.push(54);
		stack.push(36);
		stack.push(61);
		stack.push(28);

		System.out.println(stack);
		// Collections.reverse(stack);

		// stack = reverseStack(stack);
		stack = reverseStack(stack);
		System.out.println(stack);
	}

	private static Stack reverseStackUsingLambda(FunctionalInterface fi) {
		return null;
	}

	static Stack newStack = new Stack();

	private static Stack reverseStack(Stack stack) {
		if (stack.isEmpty()) {
			return newStack;
		}
		newStack.push(stack.pop());
		return reverseStack(stack);
	}

	private static void subvertStack() {
		Stack stack = new Stack() {

			@Override
			public synchronized Object pop() {
				if (size() > 0) {
					return super.pop();
				}
				return null;
			}

			@SuppressWarnings("unchecked")
			@Override
			public Object push(Object item) {
				add(0, item);
				return item;
			}

		};

		stack.push(12);
		stack.push(54);
		stack.push(36);
		stack.push(61);
		stack.push(28);

		// stack.printStack();
		System.out.println(stack);
		// 28 61 36 54 12

		System.out.println();
		System.out.println(stack.pop());
		System.out.println(stack.pop());

		stack.push(121);
		stack.push(148);

		// stack.printStack();
		System.out.println(stack);
		// 148 121 28 61 36

		System.out.println();
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}

	private static void reverseStringByRecursion() {
		String origStr = "Mahesh Balasubramanian";
		String reverseStr = reverseStringRecursively(origStr);
		System.out.println(reverseStr);
	}

	private static String reverseStringRecursively(String str) {
		if (str.equals(""))
			return "";
		return str.charAt(str.length() - 1) + reverseStringRecursively(str.substring(0, str.length() - 1));
	}

	static class BreakSingletonByCloning implements Cloneable {
		private static BreakSingletonByCloning instance = new BreakSingletonByCloning();

		public static BreakSingletonByCloning getInstance() {
			return instance;
		}

		// clone() breaks the singleton. To fix this, either return the
		// singleton instance
		// or just throw CloneNotSupportedException
		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
			// return instance;
			// throw new CloneNotSupportedException();
		}

	}

	private static void breakSingletonByCloning() throws CloneNotSupportedException {
		BreakSingletonByCloning instance1 = BreakSingletonByCloning.getInstance();
		BreakSingletonByCloning instance2;

		// instance2 = BreakSingletonByCloning.getInstance();
		instance2 = (BreakSingletonByCloning) instance1.clone();

		System.out.println(instance1.hashCode());
		System.out.println(instance2.hashCode());
	}

	static class SubClassWithInheritedDefaultConstructor extends DummyOuterClass {
		void displayInnerObjectMessage() {
			// Class is accesible,...
			ClassWithProtectedDefaultConstructor inner;

			// ... but constructor is not! ...
			// inner = new ClassWithProtectedDefaultConstructor(); //
			// compile-time access error ("The ... constructor is not visible")

			// ... instead, provide an anonymous, dummy implementation...
			inner = new ClassWithProtectedDefaultConstructor() {
			};
			inner.displayMessage();
		}
	}

	private static void classWithNoAccessibleDefaultConstructor() {
		// #1: This class has a package visible constructor, hence an instance
		// can't be created outside its package.
		// ClassInstantiableFromWithinPackageOnly obj = new
		// ClassInstantiableFromWithinPackageOnly(); // Compile error

		SubClassWithInheritedDefaultConstructor obj = new SubClassWithInheritedDefaultConstructor();
		obj.displayInnerObjectMessage();
	}

	class HomeBuyer {
		boolean loanDisbursed = false;

		void handoverCheque(HomeOwner owner) throws InterruptedException {
			while (!owner.ownershipChanged) {
				System.out.println("Waiting for home ownership to be changed");
				Thread.sleep(5000);
			}
			loanDisbursed = true;
		}

	}

	class HomeOwner {
		boolean ownershipChanged = false;

		void handoverTitle(HomeBuyer buyer) throws InterruptedException {
			while (!buyer.loanDisbursed) {
				System.out.println("Waiting for cheque to be disbursed");
				Thread.sleep(5000);
			}
			ownershipChanged = true;
		}
	}

	static private class ClassWithNoExplicitDefaultConstructor {
		void displayMessage() {
			System.out.println("Hi");
		}
	}

	private static void liveLock() {
		HomeOwner owner = ts.new HomeOwner();
		HomeBuyer buyer = ts.new HomeBuyer();

		Thread buyerThread = new Thread() {
			public void run() {
				try {
					owner.handoverTitle(buyer);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		Thread ownerThread = new Thread() {
			public void run() {
				try {
					buyer.handoverCheque(owner);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		buyerThread.start();
		ownerThread.start();
	}

	private static void deadLock() {
		class DeadlockExample {
			Object lock1 = new Object();
			Object lock2 = new Object();

			void doSomething() throws InterruptedException {
				System.out.println("Thread1 trying to get lock1");
				synchronized (lock1) {
					Thread.sleep(500);
					System.out.println("Thread1 trying to get lock2");
					synchronized (lock2) {
						System.out.println("Its an achievement for a thread to have got till this line");
					}
				}
			}

			void doSomethingElse() throws InterruptedException {
				System.out.println("Thread2 trying to get lock2");
				synchronized (lock2) {
					Thread.sleep(500);
					System.out.println("Thread2 trying to get lock1");
					synchronized (lock1) {
						System.out.println(
								"A thread would be called courageous to have broken the shackles & get till this point");
					}
				}
			}
		}

		DeadlockExample dle = new DeadlockExample();
		new Thread() {
			public void run() {
				try {
					dle.doSomething();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();

		new Thread() {
			public void run() {
				try {
					dle.doSomethingElse();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();

	}

	private static void defaultUncaughtExceptionHandler() {
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("Default exception handler");
				t.dumpStack();
			}
		});

		new Thread() {
			public void run() {
				int i = 12 / 0;
				System.out.println(i);
			}
		}.start();
	}

	private static void interruptThread() throws InterruptedException {
		Thread t1 = new Thread() {
			boolean running = true;

			public void run() {
				while (running) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace(System.out);
						running = false;
					}
				}
			}
		};

		t1.start();
		Thread.sleep(2000);
		t1.interrupt();
	}

	private static void dOSWithThreadSyncUsingStringObject() {

		class Vendor {
			String vendorName = "Mahesh";

			void displayVendorInfo() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace(System.out);
				}
				// This synchronized block tries to get lock on vendor Name
				// object...
				// However this never happens if name property of Vendor matches
				// with that of the Employee object.
				// So the current thread does not get object lock & waits
				// forever to execute this synchronized code
				synchronized (vendorName) {
					System.out.println(Thread.currentThread().getName() + ":" + this);
				}
			}

			@Override
			public String toString() {
				return "Vendor [Hash=" + vendorName.hashCode() + "]";
			}
		}

		class Employee {
			String empName = "Mahesh";

			void displayEmpInfo() {
				int count = 0;
				synchronized (empName) {
					// The current thread (which owns up the lock on the String
					// object - referenced in this object as "empName") locks
					// out
					// any other thread that synchronizes on the same string
					// object- waiting on the String object forever

					while (true) {
						// System.out.println("Thread (" +
						// Thread.currentThread().getName() + ") has locked up
						// the object forever. Retry count:"+count++);
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace(System.out);
						}
						System.out.println(Thread.currentThread().getName() + ":" + this);
					}
				}
			}

			@Override
			public String toString() {
				return "Employee [Hash=" + empName.hashCode() + "]";
			}
		}

		new Thread() {
			Employee employee = new Employee();

			@Override
			public void run() {
				employee.displayEmpInfo();
			}
		}.start();

		new Thread() {
			Vendor vendor = new Vendor();

			@Override
			public void run() {
				vendor.displayVendorInfo();
			}
		}.start();
	}

	// DOS (Denial Of Service) simulation
	private static void dOSWithThreadSync() {
		class SomeSyncronizedClass {
			int count = 0;

			synchronized void doSomething() {
				System.out.println("Inside do something:" + count++);
			}
		}

		SomeSyncronizedClass obj = new SomeSyncronizedClass();
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace(System.out);
				}
				System.out.println("Now calling doSomething()...");
				// doSomething() has no chance of getting called, since main
				// thread has held up the object lock on dOSWithThreadSync
				// object
				obj.doSomething();
			}
		}.start();

		synchronized (obj) {
			int count = 0;
			while (true) {
				System.out.println("This (the main thread) has locked up the object forever. Count:" + count++);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace(System.out);
				}
			}
		}
	}

	private static void equalsWithSubClassImpl() {
		// This returns true since both are identical (both are empty)
		// System.out.println(new ArrayList().equals(new LinkedList()));

		// SuperClass
	}

	private static void equalsWithNoHashCodeImpl() {

		// Test #1: Two identical strings have same hashCode?
		String str1 = "Mahesh";
		String str2 = new String("Mahesh");
		System.out.println("Equals:" + str1.equals(str2) + " : str1 HashCode=" + str1.hashCode() + ", str2 HashCode="
				+ str2.hashCode());

		System.out.println();

		class EmployeeKey {
			String empCode, dob;

			public EmployeeKey(String empCode, String dob) {
				super();
				this.empCode = empCode;
				this.dob = dob;
			}

			// This hashCode() returns same hashCode for all the EmployeeKey
			// objects.
			// Hence this is still valid, however, this reduces the performance
			// of a map where objects of this class are used as keys.
			// In such a case, consider such a map to be like a large
			// LinkedList, making searches slower,
			// as if, there did not exist a thing such as "Map" in the Java
			// world!
			// This behavior can be verified by inspecting the hashcode for all
			// the keys in the keySet (as the below code does).

			@Override
			public int hashCode() {
				return 0;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;

				EmployeeKey other = (EmployeeKey) obj;

				if (empCode != null ? !empCode.equals(other.empCode) : other.empCode != null)
					return false;
				if (dob != null ? !dob.equals(other.dob) : other.dob != null)
					return false;

				return true;
			}

			@Override
			public String toString() {
				return "EmployeeKey (Hash:" + hashCode() + ") [empCode=" + empCode + ", dob=" + dob + "]";
			}
		}

		// Test #2: Object without overridden hashCode() used as a key in
		// hashmap.
		EmployeeKey mahesh = new EmployeeKey("M1491", "12091977");
		EmployeeKey shivali = new EmployeeKey("S1473", "04061987");
		EmployeeKey nirav = new EmployeeKey("N1231", "13091983");
		EmployeeKey akhilesh = new EmployeeKey("A1231", "20111982");

		Map<EmployeeKey, String> employees = new HashMap<EmployeeKey, String>() {
			{
				super.put(mahesh, "mahesh");
				super.put(shivali, "Shivali");
				super.put(nirav, "Nirav");
				super.put(akhilesh, "Akhilesh");
			}
		};

		EmployeeKey lookupEmployee = new EmployeeKey("S1473", "04061987");
		System.out.println("Lookup Employee hash:" + lookupEmployee.hashCode());

		System.out.println(employees.get(lookupEmployee) + "\n");

		// Verify by inspecting the hashcode for all the keys in the keySet
		// There's only a single bucket in the map!
		for (EmployeeKey key : employees.keySet()) {
			System.out.println(employees.get(key) + ":" + key.hashCode());
		}

		System.out.println();

		// Test #3: two duplicate Objects (equal as per equals() definition),
		// but without overridden hashCode() being added in a set.

		EmployeeKey emp1 = new EmployeeKey("M1491", "12091977");
		EmployeeKey emp2 = new EmployeeKey("N1231", "13091983");
		EmployeeKey emp2Dup = new EmployeeKey("N1231", "13091983"); // Duplicate
		EmployeeKey emp3 = new EmployeeKey("A1231", "20111982");
		EmployeeKey emp3Dup = new EmployeeKey("A1231", "20111982"); // Duplicate

		Set<EmployeeKey> workers = new HashSet<EmployeeKey>() {
			{
				super.add(emp1);
				super.add(emp2);
				super.add(emp2Dup);
				super.add(emp3);
				super.add(emp3Dup);
			}
		};

		workers.forEach(System.out::println);
	}

	private static void classNotFoundException() {
		class NoClass {
		}
		// Locate this class com.mydemo.TestSomething$1NoClass in classpath &
		// delete it
		// to simulate NoClassDefFoundError/ ClassNotFoundException
		NoClass noClass = new NoClass();
	}

	private static void implementQueueUsingStack() {
		class MyQueue {
			Stack queue;

			public MyQueue() {
				super();
				queue = new Stack();
			}

			public Object poll() {
				if (queue.size() > 0) {
					return queue.remove(0);
				}
				return "";
			}

			public void add(Object obj) {
				queue.add(queue.size(), obj);
			}

			public void printQueue() {
				System.out.println("\nQueue Contents:");
				Iterator iter = queue.iterator();
				while (iter.hasNext()) {
					System.out.print(iter.next() + " ");
				}
			}
		}

		MyQueue queue = new MyQueue();
		queue.add(12);
		queue.add(54);
		queue.add(36);
		queue.add(61);
		queue.add(28);

		queue.printQueue();
		// 12 54 36 61 28

		System.out.println();
		System.out.println(queue.poll());
		System.out.println(queue.poll());

		queue.add(121);
		queue.add(148);

		queue.printQueue();
		// 36 61 28 121 148

		System.out.println();
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());

		queue.printQueue();
		// 148
	}

	private static void implementStackUsingQueue() {
		class MyStack {
			Queue stack;

			public MyStack() {
				super();
				stack = new LinkedList();
			}

			public void push(Object obj) {
				Queue tempQueue = new LinkedList();
				tempQueue.addAll(stack);
				stack = new LinkedList();

				stack.add(obj);
				stack.addAll(tempQueue);
			}

			public Object pop() {
				if (stack.size() > 0) {
					return stack.poll();
				}
				return ":";
			}

			public void printStack() {
				Queue tempQueue = new LinkedList();
				tempQueue.addAll(stack);

				System.out.println("\nStack Contents:");

				int len = stack.size();
				for (int i = 0; i < len; i++) {
					System.out.print(stack.poll() + " ");
				}
				stack = tempQueue;
			}
		}

		MyStack stack = new MyStack();
		stack.push(12);
		stack.push(54);
		stack.push(36);
		stack.push(61);
		stack.push(28);

		stack.printStack();
		// 28 61 36 54 12

		System.out.println();
		System.out.println(stack.pop());
		System.out.println(stack.pop());

		stack.push(121);
		stack.push(148);

		stack.printStack();
		// 148 121 36 54 12

		System.out.println();
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());

	}

	private static void implementArrayAsStack() {

		class Stack {
			int position;
			Object arr[] = new Object[20];

			Object pop() {
				Object retNum = 0;
				if (position > 0) {
					retNum = arr[position - 1];
					arr[position - 1] = null;
					position--;
				}
				return retNum;
			}

			void push(Object num) {
				if (position < arr.length) {
					arr[position++] = num;
				}
			}

			@Override
			public String toString() {
				String arrStr = "";
				for (int i = 0; i < position; i++) {
					Object num = arr[i];
					if (num != null)
						arrStr += num + ", ";
				}
				if (arrStr.length() > 0)
					arrStr = arrStr.substring(0, arrStr.length() - 2);
				return arrStr;
			}

		}

		Stack stack = new Stack();

		stack.push(10);
		stack.push(20);
		stack.push(30);
		stack.push(40);
		System.out.println(stack);

		System.out.println("Top: " + stack.pop());
		System.out.println(stack);
		System.out.println("Top: " + stack.pop());
		System.out.println(stack);
		stack.push(50);
		System.out.println(stack);
		stack.push(70);
		System.out.println(stack);

		// Test with Strings
		/*
		 * stack.push("Mahesh"); stack.push("Nirav"); stack.push("Rakesh");
		 * stack.push("Sandesh"); System.out.println(stack);
		 *
		 * System.out.println("Top: " + stack.pop()); System.out.println(stack);
		 * System.out.println("Top: " + stack.pop()); System.out.println(stack);
		 * stack.push("Vishnu"); System.out.println(stack);
		 * stack.push("Vibhor"); System.out.println(stack);
		 */
	}

	private static void concurrentHashMap() {
		// Following line throws ConcurrentModificationException
		Map<String, String> map = new Hashtable<String, String>() {
			// This line doesn't:
			// Map map = new ConcurrentHashMap<String, String>(){
			{
				super.put("name", "mahesh");
				super.put("occupation", "pvt service");
				super.put("city", "thane");
				super.put("birthplace", "Nagpur");
			}
		};
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			if (key.equals("name")) {
				map.put("name2", "Avi");
			}
			System.out.println(key + ":" + map.get(key));
		}
		System.out.println();
		for (Object key : map.keySet()) {
			System.out.println(key + ":" + map.get(key));
		}
	}

	private static void optional() {
		String name = "Mahesh";
		Optional<String> nameObj = Optional.ofNullable(name);
		if (nameObj.isPresent())
			System.out.println("Present: " + nameObj.get());
		else
			System.out.println("Absent");

		System.out.println(nameObj.orElse("Rakesh"));
	}

	/*
	 * Try to code without if statements
	 */
	private static void notIf() {
		int[] arrayOfIntegers = { 1, 4, 5, 9, 0, -1, 5, 24, 59, 98, 100 };
		int counter = 0;
		for (int integer : arrayOfIntegers) {
			int remainder = Math.abs(integer % 2);
			// Count total odd numbers
			/*
			 * if (remainder == 1) { counter++; }
			 */
			// Replace above if with this:
			counter += remainder;

			// Count total even numbers
			// counter += (remainder^1);
		}
		System.out.println(counter);
	}

	// Example of a generic solution for integrating two different objects. This
	// can be used for mapping and injecting values at object level.
	public static void getFieldMappings(FieldMapperExample mapperEx, Class<FieldMapper> annotationType) {

		Class<? extends FieldMapperExample> classType = mapperEx.getClass();
		Annotation annotation = null;
		for (Field field : classType.getDeclaredFields()) {
			try {
				field.setAccessible(true);
				System.out.println(field.getName() + " --> " + field.get(mapperEx));
				annotation = field.getAnnotation(annotationType);
				if (annotation != null) {
					String value = null;
					Method method = annotation.annotationType().getMethod("SF_Field_Name");
					value = (String) method.invoke(annotation);
					System.out.println(method.getName() + ":" + value);

					method = annotation.annotationType().getMethod("SAP_Field_Name");
					value = (String) method.invoke(annotation);
					System.out.println(method.getName() + ":" + value + "\n");
				}
			} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}

	private static void annotation() {
		FieldMapperExample mapperEx = new FieldMapperExample("Sunil Bidwalkar", "Nalasopara");
		getFieldMappings(mapperEx, FieldMapper.class);
	}

	class SuperClassWithGenericEquals {
		String superVal;

		public SuperClassWithGenericEquals(String superVal) {
			this.superVal = superVal;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null || this == obj)
				return false;
			/*
			 * System.out.println("Inside Super class Equals");
			 * System.out.println("This class name:" +
			 * this.getClass().getName()); System.out.println(
			 * "Class name for passed object:" + obj.getClass().getName());
			 *
			 *
			 * System.out. println(
			 * "this is instanceof SuperClassWithGenericEquals:" + (this
			 * instanceof SuperClassWithGenericEquals)); System.out.println(
			 * "obj instanceof SuperClassWithGenericEquals:" + (obj instanceof
			 * SuperClassWithGenericEquals));
			 */

			// if(obj==null || this.getClass() != obj.getClass())
			// Below code applies to sub classes as well
			if (!(obj instanceof SuperClassWithGenericEquals))
				return false;
			SuperClassWithGenericEquals instance = (SuperClassWithGenericEquals) obj;
			if (!superVal.equals(instance.superVal))
				return false;
			return true;
		}
	}

	class Subclass1WithEquals extends SuperClassWithGenericEquals {
		String subVal;

		public Subclass1WithEquals(String superVal, String subVal) {
			super(superVal);
			this.subVal = subVal;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!super.equals(obj))
				return false;
			if (!(obj instanceof Subclass1WithEquals))
				return false;
			Subclass1WithEquals other = (Subclass1WithEquals) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (subVal == null) {
				if (other.subVal != null)
					return false;
			} else if (!subVal.equals(other.subVal)) {
				return false;
			}
			return true;
		}

		private TestSomething getOuterType() {
			return TestSomething.this;
		}
	}

	class Subclass2WithoutEquals extends SuperClassWithGenericEquals {
		public Subclass2WithoutEquals(String superVal) {
			super(superVal);
		}
		// No equals() override, still the common implementation is inherited
		// from super class
	}

	private static void equals() {
		SuperClassWithGenericEquals obj1 = ts.new Subclass1WithEquals("N.Balasubramanian", "Mahesh");
		SuperClassWithGenericEquals obj2 = ts.new Subclass2WithoutEquals("N.Balasubramanian");
		// SuperClassWithGenericEquals obj2 = ts.new
		// Subclass1WithoutEquals("N.Balasubramanian", "Mahesh");
		System.out.println(obj1.equals(obj2));
	}

	private static void stdIn() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		try {
			int numRows = Integer.parseInt(br.readLine());

			int centreCell = (numRows % 2 != 0) ? (numRows / 2) + 1 : (numRows / 2);
			System.out.println("Rows: " + numRows + ", Center: " + centreCell);

			int princessPosRow = 0;
			int princessPosCol = 0;

			for (int i = 0; i < numRows; i++) {
				str = br.readLine();
				for (int j = 0; j < str.length(); j++) {
					if (str.charAt(j) == 'P' || str.charAt(j) == 'p') {
						// Note the position of princess
						princessPosRow = i;
						princessPosCol = j;
					}
				}
			}

			// Calculate direction to take
			String steps[] = new String[2];
			if (princessPosRow == 0)
				steps[0] = "UP";
			else if (princessPosRow == numRows - 1)
				steps[0] = "DOWN";
			if (princessPosCol == 0)
				steps[1] = "LEFT";
			if (princessPosCol == numRows - 1)
				steps[1] = "RIGHT";

			// Calculate number of moves in a particular direction
			int moves = centreCell / 2;
			for (int i = 0; i <= moves; i++) {
				System.out.println(steps[0]);
				System.out.println(steps[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void dBConnection() {
		List<Resource> resources = ts.getEntityRecords();
		for (Resource resource : resources) {
			System.out.println(resource.getName() + ", aged " + resource.getAge() + " years, lives in "
					+ resource.getAddress() + " and works as a " + resource.getDesignation());
		}
	}

	private List<Resource> getEntityRecords() {
		Connection conn = getDBConnection();
		// convert to json
		List<Resource> resources = new ArrayList<Resource>();
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("select * from resources");
			ResultSet rs = stmt.executeQuery();
			Resource resource = null;
			while (rs.next()) {
				resource = new Resource();
				resource.setName(rs.getString("name"));
				resource.setAddress(rs.getString("address"));
				resource.setAge(rs.getInt("age"));
				resource.setProject(rs.getString("project"));
				resource.setDesignation(rs.getString("designation"));
				resource.setContact(rs.getLong("contact"));
				resources.add(resource);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return resources;
	}

	private Connection getDBConnection() {
		@SuppressWarnings("unused")
		Connection connDatabase = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://mahesh-bala:3306/mysql?autoReconnect=true&zeroDateTimeBehavior=convertToNull";
			String dbUserName = "root";
			String dbPassword = "ahika";
			/*
			 * String dbURL =
			 * "jdbc:mysql://my-mysql-db.cxtz0qxz2pbi.us-east-1.rds.amazonaws.com:3306/my_aws_db?autoReconnect=true&zeroDateTimeBehavior=convertToNull";
			 * String dbUserName = "mahesh"; String dbPassword = "punno_20apr";
			 */
			connDatabase = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
		} catch (SQLException eSQLExcp) {
			System.out.println(eSQLExcp);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		return connDatabase;
	}

	class Resource {
		int resource_id;
		String name;
		String designation;
		String project;
		int age;
		String address;
		long contact;

		public int getResource_id() {
			return resource_id;
		}

		public void setResource_id(int resource_id) {
			this.resource_id = resource_id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDesignation() {
			return designation;
		}

		public void setDesignation(String designation) {
			this.designation = designation;
		}

		public String getProject() {
			return project;
		}

		public void setProject(String project) {
			this.project = project;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public long getContact() {
			return contact;
		}

		public void setContact(long contact) {
			this.contact = contact;
		}
	}

	static void mongoDBAccess() {
		Mongo mongo = new Mongo();

		DB db = mongo.getDB("test");
		DBCollection personColl = db.getCollection("person");
		// Insert a new person document
		// personColl.insert(new BasicDBObject().append("name",
		// "Aditi").append("address", "powai").append("married",
		// "no").append("age", 33));

		// Find persons below a certain age
		BasicDBObject dbFilterObj = new BasicDBObject();
		dbFilterObj.put("age", new BasicDBObject("$lt", 36));

		// Show only select fields
		BasicDBObject dbProjObj = new BasicDBObject();
		dbProjObj.put("name", "1");
		dbProjObj.put("married", "1");
		dbProjObj.put("age", "1");

		DBCursor cursor = personColl.find(dbFilterObj, dbProjObj);
		// Appr #1: Using old looping approach
		/*
		 * System.out.println("\nNow showing using the old looping approach\n");
		 * while(cursor.hasNext()){ System.out.println(cursor.next()); }
		 */

		// Appr #2: Using Java 8 method reference
		cursor.forEach(System.out::println);
	}

	static void stream() {
		StreamsJava8 streamsDemoObj = new StreamsJava8();
		// streamsDemoObj.sortByName();
		// streamsDemoObj.filterByPlace();
		streamsDemoObj.incrementSalary();
	}

	UnaryOperator<Integer> factorial = null;

	// Instance initializer
	{
		// factorial = (Integer i) -> (i == 1) ? 1 : i * factorial.apply( i - 1
		// );

		factorial = (Integer i) -> {
			int temp = 0;
			if (i == 1)
				temp = 1;
			else
				temp = i * factorial.apply(i - 1);
			// System.out.println(temp);
			return temp;
		};
	}

	void recursiveUsingLambda() {
		int seed = 5;
		System.out.println("Factorial of " + seed + " is " + ts.factorial.apply(seed));
	}

	static void inheritanceInFunctionalInterface() {
		// Valid, since Father is a Functional interface
		Father f = () -> {
		};
		// Invalid expression, since class Son inherits a method from Father
		// already, making it a non-Functional interface
		// Son s = ()->{};
	}

	static void createThreadUsingLambdaExpr() {
		Runnable r = () -> System.out.println("Created a thread using lambda expression...");
		Thread t = new Thread(r);
		t.start();
	}

	static void overrideAbstractMethodAndMakeDefault() {
		SuperAbstract sa = new SubDefault() {
		};
		sa.aMethod();
	}

	static void conflictWithDefaultImplementation() {
		// Test #1
		C.showMessageFromSuperIntf();

		// Test #2
		CC cc = new DD();
		cc.hello(); /*
					 * Will print hello from BB. The static type of cc is
					 * unimportant; what counts is that it is an instance of DD,
					 * whose most specific version of hello() is inherited from
					 * BB.
					 */
	}

	interface InterfaceWithDefaultImplementation {
		// existing method declarations
		default void describe(Integer i) {
			System.out.println("Empty default implementation: " + i);
		}
	}

	interface AnotherInterfaceWithDefaultImplementation {
		// existing method declarations
		default void describe(Integer i) {
			System.out.println("Another default implementation: " + i);
		}
	}

	static void defaultImplementation() {
		InterfaceWithDefaultImplementation interImpl = new InterfaceWithDefaultImplementation() {
		};
		interImpl.describe(123);
		List<String> list = new ArrayList<>();
		list.forEach(System.out::println);
	}

	interface MyFunctionalInterface {
		void displayMsg();
	}

	static void functionalInterface() {
		MyFunctionalInterface interImpl = () -> System.out.println("Hi");
		interImpl.displayMsg();
	}

	static void diamondProbUsingMultipleInheritance() {
		class MultiInherit implements InterfaceWithDefaultImplementation, AnotherInterfaceWithDefaultImplementation {

			// Compiler complains if we fail to override this method
			@Override
			public void describe(Integer i) {
				// Resolve method call with super implementation of a particular
				// super interface...
				InterfaceWithDefaultImplementation.super.describe(i);
				AnotherInterfaceWithDefaultImplementation.super.describe(i);
			}

		}
	}

	static void genericsSuper() {
		new GenericSuper().genericsSuper();
	}

	static void rotateArrayElements() {
		String[] arr = { "Mahesh", "Varun", "Poornima", "Meera" };
		GenericArrayOps<String> arrOps = new GenericArrayOps<String>(arr);

		// Integer[] arr = {1,3,2,4};
		// GenericArrayOps<Integer> arrOps = new GenericArrayOps<Integer>(arr);

		arrOps.displayArray();
		arrOps.rotateArrElements(Direction.LEFT);
		arrOps.displayArray();

	}

	static class GenericArrayOps<T> {
		T[] t;

		enum Direction {
			RIGHT("RIGHT", true), LEFT("LEFT", false);

			Direction(String desc, boolean val) {
				this.desc = desc;
				this.val = val;
			}

			String desc;
			boolean val;

			public String getDesc() {
				return desc;
			}

			public void setDesc(String desc) {
				this.desc = desc;
			}

			public boolean getVal() {
				return val;
			}

			public void setVal(boolean val) {
				this.val = val;
			}

			static Map<String, Boolean> map = new HashMap<String, Boolean>();

			static {
				for (Direction val : values()) {
					map.put(val.getDesc(), val.getVal());
				}
			}

			@Override
			public String toString() {
				return getDesc();
			}
		};

		public GenericArrayOps(T[] t) {
			this.t = t;
		}

		void displayArray() {
			System.out.println();
			StringBuffer buf = new StringBuffer();
			for (int i=0;i<t.length;i++) {
				if(i==t.length-1)
					System.out.print(t[i]);
				else
					System.out.print(t[i] + ", ");
			}
		}

		// By default, rotates elements to the right (towards 0 index), so, the
		// rightmost element becomes leftmost.
		void rotateArrElements(Direction direction) {
			if (direction.equals(Direction.RIGHT)) {
				T rootElem = t[t.length - 1];
				for (int i = t.length - 1; i > 0; i--) {
					t[i] = t[i - 1];
				}
				t[0] = rootElem;
			} else {
				T rootElem = t[0];
				for (int i = 0; i < t.length - 1; i++) {
					t[i] = t[i + 1];
				}
				t[t.length - 1] = rootElem;
			}
		}
	}

	static void covariance() {
		Integer[] iArr = { 12, 23, 34 };
		// Arrays are co-variant, so below assignment is perfectly fine.
		Object[] oArr = iArr;
		// Even the below assignment doesn't flag any compile time error. But
		// throws ArrayStoreException in run time.
		// oArr[0] = "Test";

		List<Integer> intList = Arrays.asList(iArr);
		// Collections are invariant, hence below line flags compile error even
		// if we try to force casting.
		// List<Object> objList = (List<Integer>) intList;
	}

	interface Interface1 {
		int someConstant = 1234;

		void display();
	}

	interface Interface2 {
		int someConstant = 5678;

		// Invert comments on both lines. How's the conflict resolved between
		// display() returning int in Interface2 Vs the void version in
		// Interface1?
		void display();
		// int display();
	}

	class MupltipleIntf implements Interface1, Interface2 {
		/*
		 * @Override public void display() { System.out.println("Number is: "
		 * +Interface2.someConstant); }
		 *
		 * @Override public int display() { return 0; }
		 */
		public void display() {
			System.out.println("Number is: " + Interface2.someConstant);
		}
	}

	static void multipleInterfaceResolution() {
		ts.new MupltipleIntf().display();
	}

	class CloneNotSupporting implements Cloneable {

		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}

		void someMethod() throws CloneNotSupportedException {
			// clone(); // clone() method is accessible even if it were not
			// implemented here (its defined as protected access in Object
			// class)
		}

	}

	static void cloneTest() {
		CloneNotSupporting obj1 = ts.new CloneNotSupporting();
		try {
			CloneNotSupporting obj2 = (CloneNotSupporting) obj1.clone(); // If
																			// CloneNotSupporting
																			// class
																			// chooses
																			// not
																			// to
																			// override
																			// clone()
																			// method,
																			// the
																			// compiler
																			// flags
																			// error.
																			// Why?
			System.out.println("Equals: " + obj1.equals(obj2));
			System.out.println("Obj1 hashcode: " + obj1.hashCode());
			System.out.println("Obj2 hashcode: " + obj2.hashCode());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	enum BalaceValidValues {
		BALANCE_0("BALANCE_0", 0), BALANCE_5("BALANCE_5", 5), BALANCE_10("BALANCE_10", 10), BALANCE_15("BALANCE_15",
				15);

		BalaceValidValues(String desc, int val) {
			this.desc = desc;
			this.val = val;
		}

		String desc;
		int val;

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public int getVal() {
			return val;
		}

		public void setVal(int val) {
			this.val = val;
		}

		static Map<String, Integer> map = new HashMap<String, Integer>();

		static {
			for (BalaceValidValues val : values()) {
				map.put(val.getDesc(), val.getVal());
			}
		}

		@Override
		public String toString() {
			return getDesc();
		}

		public static boolean contains(int val) {
			return map.containsValue(val);
		}
	};

	private static void enumTest() {
		BalaceValidValues balVal = BalaceValidValues.BALANCE_15;
		System.out.println("Constant: " + balVal + " Value: " + balVal.getVal());
		balVal = BalaceValidValues.BALANCE_5;
		System.out.println("Constant: " + balVal + " Value: " + balVal.getVal());
	}

	private static void dateWithinRange() {
		Date from, to;
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, 6, 14);
		from = calendar.getTime();
		calendar.set(2015, 6, 18);
		to = calendar.getTime();
		Date date = new Date();
		System.out.println("Check 1:" + !(from.after(date) || to.before(date)));
		System.out.println(
				"Check 2:" + (((from.before(date) && to.after(date))) || (from.equals(date)) || (to.equals(date))));
	}

	private static void failObjectOrientation() {
		class PersonName {
			private String name;

			public PersonName(String name) {
				this.name = name;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}
		}

		class ABean {
			private PersonName persName = null;

			public ABean(PersonName persName) {
				this.persName = persName;
			}

			public PersonName getPersName() {
				return persName;
			}
		}
		PersonName pName = new PersonName("mahesh");
		ABean bean = new ABean(pName);
		System.out.println("Before: " + bean.getPersName().getName());

		pName.setName("Sandeep");

		System.out.println("After:" + bean.getPersName().getName());
	}

	private static void callWeatherCloudService() throws MalformedURLException, IOException {
		String url = "http://api.openweathermap.org/data/2.5/forecast?q=London,uk&mode=json&units=metric";
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		InputStream is = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String str = null;
		while ((str = br.readLine()) != null)
			System.out.println(str);
	}

	private static void invokeThreadTwice() throws InterruptedException {
		Thread t = new Thread() {
			int count = 1;

			@Override
			public void run() {
				System.out.println((count++) + ") Done with execution\n");
			}
		};
		t.setDaemon(true);
		t.start();
		Thread.sleep(2000);
		t.start();// Throws IllegalThreadStateException
	}

	public enum State {
		SUCCESS, FAILURE
	}

	static void cyclicEnum() {
		System.out.println("Success.failure:" + State.SUCCESS.FAILURE);
		System.out.println("Failure.Success:" + State.FAILURE.SUCCESS);
	}

	static void regEx() {
		String tweet = "SDN as a Business Case is not quite there yet, according to Analysys Mason http://bit.ly/1lQJazb  - #telecom software";
		// Case insensitive search
		String patternStr = "[([^A-Za-z_0-9]*)]Telecom Software[([^A-Za-z_0-9]*)]";
		Pattern pattern = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(tweet);
		while (matcher.find()) {
			System.out.print("Start index: " + matcher.start());
			System.out.print(" End index: " + matcher.end() + " ");
			System.out.println(matcher.group());
		}
	}

	static void constructorThrowsException() {
		try {
			MySubClass sObj = new MySubClass();
		} catch (Exception e) {
			System.out.println("Caught Exception");
		}
	}

	/**
	 * Return a random String with all upper case alphabetic characters
	 *
	 * @param strLength
	 * @return random String
	 */
	static String generateRandomString(int strLength) {
		StringBuffer buf = new StringBuffer(strLength);
		Random r = new Random();
		for (int i = 1; i <= strLength; i++) {
			buf.append((char) (65 + r.nextInt(26)));
		}
		System.out.print(buf.toString());
		return buf.toString();
	}

	private static void varArgs() {
		methodWithVarArgs(123, "String1", 321, new Date());
	}

	private static void methodWithVarArgs(int dummyVal, Object... args) {
		int i = 0;
		for (Object obj : args) {
			switch (i) {
			case 0: // treat as number
				System.out.println("String:" + obj);
				break;
			case 1:// treat as String
				System.out.println("Integer:" + obj);
				break;
			case 2:// treat as date
				System.out.println("Date:" + obj);
				break;
			default:
				break;
			}
			i++;
		}
	}

	private static void calculateHashCode() {
		String noStr = "3530111333300000";
		int no = noStr.hashCode();
		System.out.println(no);
		System.out.println();
	}

	private static void setMethodInaccessible() {

		// Shows error message: The method display() from the type YetAnotherInheritedClassWithProtectedMethod is not visible
		/*YetAnotherInheritedClassWithProtectedMethod obj = new YetAnotherInheritedClassWithProtectedMethod();
		obj.display();*/


		try {
			Class<InheritedClassWithProtectedMethod> cls = InheritedClassWithProtectedMethod.class;
			Method m = cls.getMethod("display");
			m.setAccessible(false);
			new InheritedClassWithProtectedMethod().display();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	private static void passByRef() {
		SomeImpl impl = new SomeImpl();
		System.out.println("Value before calling method:" + impl.get());
		modifyParamObject(impl);
		System.out.println("Value after method returns:" + impl.get());
	}

	private static void modifyParamObject(SomeImpl person) {
		person.set("Alvito");
	}

	private static void printF() {
		System.out.printf("The developer %1s is from the %2s project.", "Mahesh", "Billeo");
	}

	protected void throwsNPException() throws RuntimeException {
		// protected void throwsNPException() throws Exception{// Results in
		// error "Exception ... is not compatible with throws clause in
		// <super>.<method>..."
		super.throwsNPException();
		// throw new RuntimeException();
	}

	static void listContainment() {
		List<Person> list = new ArrayList<Person>();
		list.add(new Person("Mahesh", "Thane", "12346"));
		list.add(new Person("Shivali", "Jogeshwari", "12346"));
		list.add(new Person("Akhileshwar", "Airoli", "12346"));

		Person newPerson = new Person("Akhileshwar", "Goregaon", "12346"); // TRUE
		// Person newPerson = new Person("Aditi", "Powai", "12346"); // FALSE

		if (list.contains(newPerson)) {
			System.out.println("Contains?: " + list.contains(newPerson));
			int indx = list.indexOf(newPerson);
			Person p = list.get(indx);
			System.out.println("Index is:" + indx + ".. Address is: " + p.address);
		}

	}

	static void classSynchronized() {
		synchronized (TestSomething.class) {

		}
	}

	static private void nullInteger() {
		Integer i = new Integer("");
		int val = i;
		System.out.println(val);
	}

	public enum ErrorCode {

		ERROR_INVALID_EMAIL(101, "ERROR_INVALID_EMAIL"), ERROR_INVALID_SHIPPINGINFO(102,
				"ERROR_INVALID_SHIPPINGINFO"), ERROR_INVALID_CHARACTER(103,
						"ERROR_INVALID_CHARACTER"), ERROR_INVALID_SHIPPING_STATE(104, "ERROR_INVALID_SHIPPING_STATE");

		static Map<String, ErrorCode> map = new HashMap<String, ErrorCode>();

		static {
			for (ErrorCode code : values()) {
				map.put(code.getDescription(), code);
			}
		}

		final int code;
		private final String description;

		private ErrorCode(int code, String description) {
			this.code = code;
			this.description = description;
		}

		public String getDescription() {
			return description;
		}

		public int getCode() {
			return code;
		}

		public String toString() {
			return code + ": " + description;
		}

		/*
		 * public static Map<String, ErrorCode> valueSet() { Map<String,
		 * ErrorCode> map = new HashMap<String, ErrorCode>(); for(ErrorCode
		 * code:values()) { map.put(code.getDescription(), code); } return map;
		 * }
		 */
		public static ErrorCode getErrorCode(String errorDesc) {
			return map.get(errorDesc);
		}
	}

	private static void enumToMap() {
		ErrorCode code = null;
		// Map<String, ErrorCode> map = ErrorCode.valueSet();
		// code = map.get("ERROR_INVALID_EMAIL");
		code = ErrorCode.getErrorCode("ERROR_INVALID_EMAIL");
		if (code != null)
			System.out.println(code.getDescription());
	}

	private static void printClassName(Object obj) {
		System.out.println(obj.getClass().getName());
	}

	public static DocumentBuilderFactory dbFactory;
	public static DocumentBuilder dBuilder;
	public static Document doc;

	private static void parseXmlFile() {
		String fileName = "Rei_VAR.xml";
		File f = new File(fileName);
		System.out.println(f.exists() + ":" + f.getAbsolutePath());
		dbFactory = DocumentBuilderFactory.newInstance();
		try {
			dBuilder = dbFactory.newDocumentBuilder();

			// doc = dBuilder.parse(f);
			InputSource is = new InputSource(
					new FileReader(new File(new File("").getAbsolutePath() + File.separator + fileName)));
			// InputStream is = new
			// TestSomeThing().getClass().getClassLoader().getResourceAsStream(new
			// File("").getAbsolutePath()+File.separator+fileName);
			doc = dBuilder.parse(is);
			System.out.println(doc.getChildNodes().item(0).getNodeName());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void printClassNameFromStackTrace() {
		try {
			throw new Exception("Some exception");
		} catch (Exception e) {
			System.out.println("Class::" + e.getStackTrace()[0].getClassName());
		}
	}

	// **********************************************************************
	static void runtimeException() {
		String str = "AbCd";
		boolean bVal = str.equalsIgnoreCase("ABCD");
		System.out.println("bVal" + bVal);
		/*
		 * String numStr = "as"; try { BigDecimal bd = new BigDecimal(numStr);
		 * System.out.println(bd.toString()); } catch (RuntimeException e) {
		 * e.printStackTrace(); }
		 */
	}

	// **********************************************************************

	void graphImpl() {
		City seed = initSeed();
		System.out.println("END");
	}

	City initSeed() {

		City pune = new City(City.CITY_PUNE, "Pune");
		City mumbai = new City(City.CITY_MUMBAI, "Mumbai");
		City nashik = new City(City.CITY_NASHIK, "Nashik");
		City satara = new City(City.CITY_SATARA, "Satara");
		City kolhapur = new City(City.CITY_KOLHAPUR, "Kolhapur");

		// Init connections
		pune.addConnection(City.CITY_MUMBAI, 415);
		pune.addConnection(City.CITY_NASHIK, 700);
		pune.addConnection(City.CITY_SATARA, 75);

		mumbai.addConnection(City.CITY_NASHIK, 170);
		mumbai.addConnection(City.CITY_KOLHAPUR, 800);

		nashik.addConnection(City.CITY_SATARA, 500);
		nashik.addConnection(City.CITY_KOLHAPUR, 650);

		satara.addConnection(City.CITY_KOLHAPUR, 60);

		kolhapur.addConnection(City.CITY_SATARA, 60);

		return kolhapur;
	}

	class City {
		public static final String CITY_NASHIK = "NAS";
		public static final String CITY_MUMBAI = "MUM";
		public static final String CITY_KOLHAPUR = "KOL";
		public static final String CITY_PUNE = "PUN";
		public static final String CITY_SATARA = "SAT";

		String code, name;
		Hashtable<String, Integer> connections = new Hashtable<String, Integer>();

		public City(String code, String name) {
			if (cities == null)
				cities = new Hashtable<String, City>();
			this.code = code;
			this.name = name;
			cities.put(code, this);
		}

		// To maintain bi-directional weight info...
		void addConnection(String cityCode, int weight) {
			if (!connections.containsKey(cityCode)) {
				connections.put(cityCode, weight);
				City tempCity = cities.get(cityCode);
				if (tempCity != null && tempCity.connections != null && !tempCity.connections.containsKey(this.code))
					tempCity.addConnection(this.code, weight);
			}
		}

		boolean isConnected(String cityCode) {
			return connections.contains(cityCode);
		}
	}

	// **********************************************************************
	static void byPassIteratorOnCollectionAdd() {
		final List<String> list = new ArrayList() {
			{
				add("Hello");
			}
		};
		final Iterator<String> iterator = list.iterator();
		System.out.println(iterator.next());
		list.add("World");
		list.add("Mahesh");
		// FIXME : work here while I'm sunbathing
		for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE - 1; i++) {
			// ((ArrayList) list).trimToSize();
			((ArrayList<String>) list).ensureCapacity(1);
		}
		System.out.println(iterator.next());
		System.out.println(iterator.next());
	}

	public TestSomething() {
		super();
	}
}

class GenericSuper {
	static class Organism {
	}

	static class HumanBeing extends Organism {
	}

	static class Animal extends Organism {
	}

	static class Mammal extends Animal {
	}

	static class Fish extends Animal {
	}

	static class Bird extends Animal {
	}

	static class Reptile extends Animal {
	}

	static class Amphibian extends Animal {
	}

	static class Anthropod extends Animal {
	}

	<T extends Organism> void doNothing(T t) {
	}

	void genericsSuper() {
		HumanBeing human = new HumanBeing();
		doNothing(human);
	}
}

final class Algorithm {
	public static <T> void swap(T[] a, int i, int j) {
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}

class SomeClass {

}

class CannotInstantiate {
	private CannotInstantiate() {

	}

	public void instanceMethod() {

	}
}

// Classes/ interfaces to demonstrate how conflict is handled with multiple
// interface inheritance,
// each interface having same signature for the default method

// Test #1

interface A {
	default void hello() {
		System.out.println("Hello World from A");
	}
}

interface B {
	default void hello() {
		System.out.println("Hello World from B");
	}
}

class C implements B, A {
	static void showMessageFromSuperIntf() {
		C c = new C();
		c.hello();// Conflict - which version of hello() would be called - A or
					// B?
					// The conflict must be resolved only by overriding, where,
					// if desired,
					// one of the inherited methods can be selected using the
					// new syntax X.super.m(...)
					// See the below overridden hello() method
	}

	public void hello() {
		A.super.hello();
		B.super.hello();
	}
}

// Test #2:

interface AA {
	default void hello() {
		System.out.println("Hello World from A");
	}
}

interface BB extends AA {
	default void hello() {
		System.out.println("Hello World from B");
	}
}

interface CC extends AA {
}

class DD implements BB, CC {
}

// Test #3: Can interface override an abstract method from super interface as
// default method?
// Yes, it can be.
interface SuperAbstract {
	void aMethod();
}

interface SubDefault extends SuperAbstract {
	@Override
	default void aMethod() {
		System.out.println("Message from method overridden as a default method..");
	}
}

@FunctionalInterface
interface Father {
	void attendOffice();// { System.out.println("My office is calling me"); }
}

// @FunctionalInterface // Invalid, since Son inherits a method from Father
// already, so not a functional interface really.
interface Son extends Father {
	void attendSchool();// { System.out.println("Off I am going to school"); }
}

//
class StreamsJava8 {
	List<Person> list = null;

	public StreamsJava8() {
		list = new ArrayList<Person>();
		list.add(new Person("Mahesh", "Thane", "12346", 70000));
		list.add(new Person("Shivali", "Jogeshwari", "78910", 35000));
		// list.add(new Person("Srinivas", "Airoli", "111213", 60000));
		list.add(new Person("Akhileshwar", "Airoli", "141516", 45000));
		list.add(new Person("Nirav", "Ghatkopar", "171819", 65000));
	}

	public void displayAll() {
		list.forEach(System.out::println);
	}

	public void sortByName() {
		list.stream().sorted((Person p1, Person p2) -> p1.getName().compareTo(p2.getName()))
				.forEach(System.out::println);
	}

	public void filterByPlace() {
		// Show persons living in places other than Airoli
		list.stream().filter((Person p) -> !p.address.equals("Airoli")).forEach(System.out::println);
	}

	public void incrementSalary() {
		System.out.println("Before salary increment::");
		displayAll();

		System.out.println();

		list = list.stream().map(p -> {
			int salBe4 = p.getSalary();
			int inc10Percent = salBe4 * 10 / 100;
			p.setSalary(salBe4 + inc10Percent);
			return p;
		}).collect(Collectors.toList());

		System.out.println("After the salary increment::");
		displayAll();
	}

	/*
	 * public void incrementSalary(){ displayAll(); System.out.println();
	 * list.stream().map(p->{ p.setSalary(p.getSalary()+1000); return
	 * p.getSalary(); }) .forEach(System.out::println) ; displayAll(); }
	 */
}
