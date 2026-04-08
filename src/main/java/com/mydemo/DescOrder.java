package com.mydemo;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;

public class DescOrder {
	public static void main(String[] args) {
		String str = "HELLO";
		String[] tokens = str.split("");
		
		//
//		Map<Character, Long> collect = str.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(c->c, Collectors.counting()));
		Map<String, Long> collect = Arrays.stream(str.split("")).collect(Collectors.groupingBy(c->c, Collectors.counting()));
		
		for(Map.Entry entry:collect.entrySet()) {
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		
		
		
		Map<String, Integer> map = new Hashtable<String, Integer>();
		for(String token:tokens) {
			int count=0;
			Integer countObj = map.get(token);
			if(countObj!=null)
				count=countObj;
			map.put(token, ++count);
		}
		for(String key: map.keySet()) {
			if(map.get(key)>1)
				System.out.println(key+":"+map.get(key));
		}
		
	}
}
