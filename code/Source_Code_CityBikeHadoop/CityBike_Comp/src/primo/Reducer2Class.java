package primo;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;


public class Reducer2Class extends MapReduceBase implements Reducer<Text, Text, Text, Text> {

	
	@Override
	public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
		
		Integer count = 0;
		Integer sum = 0; 
		Integer avg;
		Text temp0;
		String temp;
		while (values.hasNext()) {
			count ++;
			temp0 = values.next();
			temp = temp0.toString();
			

		
			Integer tempInt = Integer.parseInt(temp);
			sum = tempInt + sum; 

			
		}
		avg = sum/count; 
		
		
		String avgString = avg.toString();

			
		
		String countString = count.toString();
		if(avgString.startsWith("-")){
			avgString = avgString.replace("-", "");
		}
		
		output.collect(key, new Text(avgString+"-"+countString));
	}
	
}
