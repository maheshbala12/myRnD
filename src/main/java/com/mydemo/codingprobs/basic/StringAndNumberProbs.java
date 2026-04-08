package com.mydemo.codingprobs.basic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class StringAndNumberProbs {
	public static void main(String[] args) {
//		countDuplicates();
//		findFirstNonRepeatedCharInString();
//		countVowelsAndConsonants();
//		generatePermutations();
//		removeDuplicateChars();
		sortByElemLength();
	}

	private static void sortByElemLength() {
		String names[] = { "Ashley", "Hailey", "Harrison", "Jameson", "Kingston",
				"Kinsley", "Maxwell", "Amy", "Piper", "River", "Ruby" };
		List<String> res = Arrays.asList(names).stream().sorted((s1,s2)->s1.length()>s2.length()?1:-1).toList();
		System.out.println();
		// Intent is to sort by length of the string, if length is same then sort by natural order
	}
	

	private static void removeDuplicateChars() {
		String str="this is going somewhere";
		String res = "";
		for(int i=0;i<str.length();i++) {
			char ch = str.charAt(i);
			String temp = str.substring(0, i) + str.substring(i+1);
			res += temp.indexOf(ch+"")==-1 ? ch : "";
		}
		System.out.println(res);
	}

	private static void generatePermutations() {
		Set<String> permuteAndStore = permuteAndStore("", "TEST");
		System.out.println(permuteAndStore.size());
		System.out.println(permuteAndStore);
	}
	private static Set<String> permuteAndStore(String prefix, String str) {
		Set<String> permutations = new HashSet<>();
		if(str.equals("")) {
			permutations.add(prefix);
		}
		else {
			for(int i=0;i<str.length();i++) {
				permutations.addAll(permuteAndStore(prefix+str.charAt(i), str.substring(0,i)+str.substring(i+1)));
			}
		}
		return permutations;
	}
	private static void countVowelsAndConsonants() {
		String str="this is going somewhere";
		
		
		// occurances of a certain char
		char ch='w';
		long count = str.chars().filter(c->c==ch).count();
		
		
		Set<Character> set = new HashSet<Character>(
				Arrays.asList('a','e','i','o','u'));
		Long vowels = str.chars().
				filter(c->set.contains((char)c)).
				filter(c-> (c>='a' && c<='z')).count();
		Long consonents = str.chars().
				filter(c->!set.contains((char)c)).
				filter(c-> (c>='a' && c<='z')).count();
		System.out.println(vowels+":"+consonents);
	}

	private static void findFirstNonRepeatedCharInString() {
		String str="this is going somewhere";
		Map<Character, Integer> map = new LinkedHashMap<Character, Integer>();
		for(int i=0;i<str.length();i++) {
			char ch = str.charAt(i);
			map.compute(ch, (k,v)->(v==null)?1:++v);
		}
		for(Entry<Character, Integer> entry:map.entrySet()) {
			if(entry.getValue()==1) {
				System.out.println(entry.getKey());
				break;
			}
		}
	}

	private static void countDuplicates() {
		String str="this is going somewhere";
		Map<Object, Long> map = str.chars().mapToObj(c->(char)c).
				collect(Collectors.groupingBy(c->c, Collectors.counting()));
		System.out.println();
	}
}
