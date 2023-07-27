package com.mydemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidateCodeBlocks {
	private static String[] input = {
			"{()[[[]]({{}[{}]}){((()))([[]]([]{}[]{{()({})[{(())[]}]([{[]}])}()[][[]]()[([{{{(){[[(()(([()]([][]()()){[{}][((()[()])[{(([([]{({})})](){[]{}[[[][{{{}{[()]{[]{}}}(())}}]()[{}{}{[[]][[]{{()}}]}{([[[]]({[]})]{}){}}][[{[()]}]][]{}]{}()]{}})([])){}}[{}[]]{}]){[{}]{{{}[()](){}}[[{}]]{[{}]}{}()([]){}[][]}([]((){[][]{[({{}}[[[][()]][[][]]{}]){[][[]()]{}}[]]({}[{(([[]()](){((({()(){([])()[{{[[{}][]]}(){}[][][[]{}[]{{[(([{}]))]()[]}(())}][([])[{(())}](())[]([[([])]])]}]}}({}{}))))}){{}}(({()}){})[](([]()()[{([]){}(){(({}[]))}}()](()){{}{}}){({})}[[([[]()][])()[]]()])(()))}])()}{({()})(([[]]{()}[]))}})()(){[]})()[{{{}}[]}{}{}[][(({}[[{}(){}]]){{[[]{[{(()[])((([[]])((())))){}[][]}]({})}(())]}{}((){{}((){[[][]]}([]){({}[[]({}{}){}])}(){})}(()))[]}({([][])})()){}]]}{(){}}()]{[]}{}[]{}{{}{}}[{}{{()}}]({(){[]}({}[]()[])({}{{{[]{{}}}{}[][{}[]][][]}[{{()}()[{}[]]{}([]())}]})[]({[]})})[]}{(())[[]]})[](){[[]{}]{}({{}(){[]}}[]{{}[[][]][]()}){{}}})[(()[])[()[[{}()]()]()[]]])][[]({([{{}}])})]]}}}}])]}))}]}",
			"({[]})", "({[})", "({[()]})", "({[]))", "()[]{}", "{[}]", "}{)(][", "})][({", "}{)(}{",
			"[{{()}}{[([])]}[]{}({}({}))]()({[[]{{([])}([{()[[[]][]]}(([[{{}{()[([[(()[]){}(({{}}[])(){})]])()(({[{{}}({})]}))]}}][{{}}]((){{}}[[]])]))])[]{()}}]()[[[]]()[[([])]]]{}}()[])[[{(){}}{}]](()[()(({}){([])}{}[(()[{}])])]{})(){}{}[(){}][[]]{()()[{}[[]]]{}}{()}[[][]][[]{[{[()]}({})]}()][{}]{}{}{}{{}{{}}[]}{{}()}()[][][(){}][][([][[]{}]{}{([])({}()()){}})[][]][][]{[[([()](()())[{{((({}){}[])([]([]{({()})}))(){}{}){({[()][]}[]{[][][]}[([()])])}[]{}{((()[][][[[({})]()][()([]()())]{{[[()()([{{}}([]){[{([{()}]{{}{}}[([{}[]])]{[]((({}({})({{}}[{[]{}[]}])[])([][][[[()]{([({}()(({{}()})([[{}[]]]{{()(({{}}))}{}[[]]()()}([{(({}()))[]}]()[()([])](){[]}){()(){}{[][(){}{}]}[][]{}}))()[][])])(({}){{}([[[]{}[]{}([[[]{}[[[][[]]]]{()}[]]()[{}()](){{{[]{(){}}}[]}([])}][[{}]]{}){}[]]])[{()}([()][])]}(){[]}[][]{}){}}](({[()]{}}{}))][[]][[][[]]]()[()([[[]][][]][{{}[[]][]}[[()]({})]{}{}])()[][(()())({}){}{{(())}}{({()[]([[]{([[]][])}]){}{{}}{}}{{{}}[()]})}]])[][([])][[][[[]]]{[]([]){[]}}]))})}]}])]]}}]))}}}])]]}}",
			"({[]})", "][(]}})("
	};
	
	static class StringIterator implements Iterator<Character>{
		String theString;
		boolean isStringNullOrEmpty = true;
		int index = 0;
		
		public StringIterator(String theString) {
			this.theString = theString;
			isStringNullOrEmpty = theString==null || theString.isEmpty(); 
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
	
	// Time complexity: O(n)
	static String isBalanced(String s) {
        boolean isValid = true;
		StringIterator iterator = new StringIterator(s);
		int i = 1;
		Stack<Character> stack = new Stack<Character>();
		String openingBlockSymbols = "{([";
		String closingBlockSymbols = "})]";
		String testStr = "";
		while(iterator.hasNext()){
			char ch = iterator.next();
			testStr += ch;
			if(openingBlockSymbols.indexOf(ch) != -1){
				stack.push(ch);
			}
			else if(closingBlockSymbols.indexOf(ch) != -1){
				if(stack.isEmpty()){
					isValid = false;
					break;
				}
				int pos = stack.size()-(i);
				char theChar = stack.get(pos);
				if(getMatchingTag(theChar)!=ch){
					isValid = false;
					break;
				}
				i++;
			}
		}
		stack.clear();
		String retVal = (isValid?"YES":"NO");
		System.out.println(retVal);
		return retVal;
    }
	
	private static void setInputTestCases_2(){
		InputStream stream = ValidateCodeBlocks.class.getClassLoader().getResourceAsStream("ValidateCodeBlocks_TCs.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		int totalLines = 0;
		try {
			totalLines = Integer.parseInt(reader.readLine());
			input = new String[totalLines];
			for(int i=0;i<input.length;i++){
				input[i]=reader.readLine();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Java 8 readlines from file
	private static void setInputTestCases(){
		String fileName = "C:/workspace/Standalone/bin/ValidateCodeBlocks_TCs.txt";
		List<String> list = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			// 1. filter line 3
			// 2. convert all content to upper case
			// 3. convert it into a List
			list = stream.filter(line -> !line.matches("0-9")).collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		input = list.toArray(input);
	}
	// Time complexity: O(n)
	public static void main(String[] args) {
		//setInputTestCases();
		//isBalanced(input[0]);
		for(String str:input){
			String s = isBalanced(str);
		}
	}
	
	private static char getMatchingTag(char ch){
		char retChar = ' ';
		switch(ch){
			case '{':	retChar = '}';
						break;
			case '(':	retChar = ')';
						break;
			case '[':	retChar = ']';
						break;
		}
		return retChar;
	}
}
