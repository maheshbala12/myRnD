package com.mydemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/* Save this in a file called Main.java to compile and test it */

/* Do not add a package declaration */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/* DO NOT CHANGE ANYTHING ABOVE THIS LINE */
/* You may add any imports here, if you wish, but only from the
   standard library */

/* Do not add a namespace declaration */
public class Main {
    public static Map<String,Integer> processData(ArrayList<String> array) {
        /*
         * Modify this method to process `array` as indicated
         * in the question. At the end, return the appropriate value.
         *
         * Please create appropriate classes, and use appropriate
         * data structures as necessary.
         *
         * Do not print anything in this method.
         *
         * Submit this entire program (not just this method)
         * as your answer
         */
       Map<String,Integer> retVal = new HashMap<String,Integer>();

       Map<String, int[]> arr = new HashMap<String, int[]>();
       for(String str:array){
    	   String scoreArr [] = str.split("\\|");

    	   // Capture individual data
    	   int studentId = Integer.parseInt(scoreArr[0]);
    	   String sub = scoreArr[1];
    	   int marks = Integer.parseInt(scoreArr[2]);

    	   // Maintain map of subject to int[] - array of 2 elements - [0] containing studentId & [1] containing max marks
    	   int data[] = arr.get(sub);
    	   if(data==null){
    		   data = new int[2];
    		   data[0] = studentId;
        	   data[1] = marks;
    	   }
    	   else{
    		   // If new line has marks greater than existing for the subject, then replace the map with updated studentId & marks data.
    		   if(data[1]<marks){
    			   data[0] = studentId;
            	   data[1] = marks;
    		   }
    	   }
    	   arr.put(sub, data);
       }
       // Now just loop through map & copy result data to retVal map
       for(String subject:arr.keySet()){
    	   int [] topperData = arr.get(subject);
    	   retVal.put(subject, topperData[0]);
       }
       return retVal;
    }

    public static void main (String[] args) {
        ArrayList<String> inputData = new ArrayList<String>();
        String line;
        try {
        	System.out.println(new File("input.txt").getAbsolutePath());
            Scanner in = new Scanner(new BufferedReader(new FileReader("input.txt")));
            while(in.hasNextLine())
                inputData.add(in.nextLine());
            Map<String,Integer> retVal = processData(inputData);
            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
            for(Map.Entry<String,Integer> e: retVal.entrySet())
                output.println(e.getKey() + ": " + e.getValue());
            output.close();
        } catch (IOException e) {
            System.out.println("IO error in input.txt or output.txt");
        }
    }
}
