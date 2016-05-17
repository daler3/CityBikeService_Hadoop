package primo;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;


public class ReducerClass extends MapReduceBase implements Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {

	@Override
	public void reduce(IntWritable key, Iterator<IntWritable> values, OutputCollector<IntWritable, IntWritable> output, Reporter reporter) throws IOException {
		int sum = 0;
		int count = 0; 
		while (values.hasNext()) {
			sum += values.next().get();
			count++;
		}
		int avg = sum/count; 
		output.collect(key, new IntWritable(avg));
	}

}
