package com.devexpert.hadoop.polindrome;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class PolindromeMapper extends MapReduceBase implements Mapper<LongWritable, Text, IntWritable,Text>{

	//private final static IntWritable one = new IntWritable(1);
	private Text text = new Text();
	
	private boolean simplePolindromeChecker(String word) {
			
		char[] chars = word.toCharArray();
		int left=0;
		int right=chars.length-1;
		for(;left<right;left++,right--) {
			if(chars[left]!=chars[right]) return false;
		}
		return true;
	}
	
	
	@Override
	public void map(LongWritable key, Text value, OutputCollector<IntWritable,Text> output, Reporter reporter)
			throws IOException {
		String line = value.toString();
		String[] words = line.split("\\W+");
		for(String word:words ) {
			if(simplePolindromeChecker(word)) {
				text.set(word);
				output.collect(new IntWritable(text.getLength()), text);	
			}
		}
		
	}



}
