package primo;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;


public class Mapper4Class extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
	
	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {

		String valStrSpl [] = value.toString().split("\\t");
		
		output.collect(new Text(valStrSpl[0]), new Text(valStrSpl[1]));
	}
}