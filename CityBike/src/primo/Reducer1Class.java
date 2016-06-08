package primo;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;


public class Reducer1Class extends MapReduceBase implements Reducer<IntWritable, Text, IntWritable, Text> {

	
	@Override
	public void reduce(IntWritable key, Iterator<Text> values, OutputCollector<IntWritable, Text> output, Reporter reporter) throws IOException {
		Integer sumDuration = 0;
		Integer sumCustomer = 0;
		int count = 0; 
		String temp1;
		while (values.hasNext()) {
			temp1 = values.next().toString();
			String temp2[] = temp1.split("-");
			sumDuration = sumDuration + Integer.parseInt(temp2[0]);
			count ++;		
			sumCustomer = sumCustomer + Integer.parseInt(temp2[1]);
		}
		Integer avg = sumDuration/count; 
		String avgString = avg.toString();
		String sumCustomerString = sumCustomer.toString();

		output.collect(key, new Text(avgString+"-"+sumCustomerString));
	}

}
