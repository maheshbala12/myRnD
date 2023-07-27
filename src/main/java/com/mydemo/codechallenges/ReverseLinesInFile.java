package com.mydemo.codechallenges;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReverseLinesInFile {
    public static void main(String[] args) {
    	File file = new File("resources/file_1.txt");
    	File file_2 = new File("resources/file_2.txt");
    	try(
        	FileReader fReader = new FileReader(file.getAbsolutePath());
        	BufferedReader bReader = new BufferedReader(fReader);
        	
        	FileWriter fWriter = new FileWriter(file_2);
        	BufferedWriter bWriter = new BufferedWriter(fWriter);
    	){
        	writeToFile(bReader, bWriter);
    	}
    	catch(Exception e) {}
    }
    
    static void writeToFile(BufferedReader bReader, BufferedWriter bWriter) throws IOException{
    	String str = null;
    	if((str=bReader.readLine())==null) {
    		return;
    	}
    	writeToFile(bReader, bWriter);
    	bWriter.write(str+"\n");
    }
    
    
}
