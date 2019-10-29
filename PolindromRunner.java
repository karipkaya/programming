package com.devexpert.hadoop.polindrome;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

public class PolindromRunner {
	public static void main(String[] args) throws IOException {
		// create JobConf
		JobConf conf = new JobConf(PolindromRunner.class);
		// set job name
		conf.setJobName("PolindromeFinder");
		// set output key value types
		conf.setOutputKeyClass(IntWritable.class);
		conf.setOutputValueClass(Text.class);
		//set mapper, reducer and combiner
		conf.setMapperClass(PolindromeMapper.class);
		conf.setCombinerClass(PolindromeReducer.class);
		conf.setReducerClass(PolindromeReducer.class);
		
		// set input output file formats
		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);
		
		//set file paths
		TextInputFormat.setInputPaths(conf, new Path(args[0]));
		TextOutputFormat.setOutputPath(conf, new Path(args[1]));
		
		JobClient.runJob(conf);
			
	}

}
