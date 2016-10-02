package primo;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;


public class Reducer3Class extends MapReduceBase implements Reducer<Text, Text, Text, Text> {

	
	@Override
	public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
		Integer count=0; 

		while (values.hasNext()) {
			count ++;
			values.next();
		}
		//ora devo fare diventare la chiave il giorno
		String sumString = count.toString();

		String keySplitted[] = key.toString().split("-");
		String newKey = keySplitted[0];
		

		output.collect(new Text (newKey), new Text(keySplitted[1]+"-"+keySplitted[2]+"-"+sumString));

	}
}