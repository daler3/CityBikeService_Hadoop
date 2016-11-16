package primo;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;


public class Reducer4Class extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
	
	@Override
	public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
		//come chiave ora ho il giorno
		
		Integer tempMax = -1;
		String temporaryStationId = "nullo";
		String temporaryStationName = "nullo";
		while (values.hasNext()) {
			Text tempo = values.next();
			String valString = tempo.toString();
			String splitString []= valString.split("-");
			Integer tempSum = Integer.parseInt(splitString[2]);
			if(tempSum > tempMax){
				tempMax = tempSum; 
				temporaryStationId = splitString[0];
				temporaryStationName = splitString[1];
			}
			
		}
		
		output.collect(key, new Text("Station ID: "+temporaryStationId+" - Station Name: "+temporaryStationName+" - Amount of traffic:"+tempMax.toString()));
		
	}
}
