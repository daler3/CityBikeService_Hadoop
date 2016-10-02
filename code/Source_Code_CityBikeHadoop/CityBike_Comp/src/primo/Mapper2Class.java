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


public class Mapper2Class extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {

	private static IntWritable durata;

	
	private Text word = new Text();
	
	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
		String valStr0 = value.toString();
		String valStr=valStr0.replaceAll("\"", "");
		//System.err.println(valStr);
		String valStrSplitted[]=valStr.split(",");
		String birthYear = valStrSplitted[13];
		
		String tD="tripduration";
		if (valStrSplitted[0].equals(tD)){
			
		}
		else{
			if(birthYear.equals("")){
				
			}
			else{
				int intBirthYear = Integer.parseInt(birthYear);
				//ho l'age range
				String ageRange = toAgeRange(intBirthYear);
				//tiro fuori tripDuration
				String tripDuration = valStrSplitted[0];
				//int tripDurInt = Integer.parseInt(tripDuration);
				//durata = new IntWritable (tripDurInt);
				word.set(ageRange);
				output.collect(word, new Text(tripDuration));
			}
		}
		
		
	}
	
	private String toAgeRange(int year){
		Calendar now = Calendar.getInstance();
		int currentYear = now.get(Calendar.YEAR); 
		int diff = currentYear - year; 
		
		if(diff>=16 && diff<=19){
			return "16-19";
		}
		if(diff>=20 && diff<=29){
			return "20-29";
		}
		if(diff>=30 && diff<=39){
			return "30-39";
		}
		if(diff>=40 && diff<=49){
			return "40-49";
		}
		if(diff>=50 && diff<=59){
			return "50-59";
		}
		if(diff>=60 && diff<=69){
			return "60-69";
		}
		return "Over";
		
	}
	
	
}