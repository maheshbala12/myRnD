package com.mydemo.headfirstoops.urbanrailroutemapper;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given an array of strings:
 * 		["Pune", "Delhi", "Deepak", "Pune", "Orange", "Orange", "Orange", "Deepak"]

 * The output should be:
		Word: Pune
		Frequency: 2
		Positions: 0, 3
		
		Word: Deepak
		Frequency : 2
		Positions: 2, 7
		
		Word: Orange
		Frequency : 3
		Positions: 4,5,6
		
		Word: Delhi
		Frequency : 1
		Positions: 1
 */

public class OccurancesCount {	
	public static void main(String[] args) {
		String []strings = {"Pune", "Delhi", "Deepak", "Pune", "Orange", "Orange", "Orange", "Deepak"};
		// Using OOP
		Map<String, OccuranceTracker> occurances = new Hashtable<String, OccuranceTracker>();
		for(int i=0;i<strings.length;i++) {
			String str = strings[i];
			OccuranceTracker tracker = occurances.get(str);
			boolean noTrackerForToken = tracker==null;
			if(noTrackerForToken) {
				tracker = new OccuranceTracker();
				tracker.setToken(str);
			}
			int count = tracker.getCount();
			tracker.setCount(count+1);
			String indices = tracker.getIndices();
			tracker.setIndices(indices + (noTrackerForToken?"":", ")+i);
			occurances.put(str, tracker);
		}
		for(String key: occurances.keySet()) {
			OccuranceTracker tracker = occurances.get(key);
			System.out.println("Word:"+ tracker.getToken());
			System.out.println("Frequency: "+tracker.getCount());
			System.out.println("Positions: "+ tracker.getIndices());
			System.out.println();
		}
		System.out.println();

		// Using streams
		
		Map<String, Long> map = Arrays.stream(strings).collect(Collectors.groupingBy(str->str, Collectors.counting()));
		for(Map.Entry entry:map.entrySet()) {
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		
	}
}

class OccuranceTracker{
	String token;
	int count=0;
	String indices="";

	public OccuranceTracker() {
	}

	public OccuranceTracker(String token, int count, String indices) {
		super();
		this.token = token;
		this.count = count;
		this.indices = indices;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getIndices() {
		return indices;
	}
	public void setIndices(String indices) {
		this.indices = indices;
	}

	@Override
	public String toString() {
		return "OccuranceTracker [token=" + token + ", count=" + count + ", indices=" + indices + "]";
	}
	
}




