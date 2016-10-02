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


public class Mapper3Class extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {

	private static IntWritable durata;
	//private static IntWritable weekNumber;
	
	//private final static IntWritable uno = new IntWritable(1);
	
	private Text word = new Text();
	
	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
		String valStr0 = value.toString();
		String valStr=valStr0.replaceAll("\"", "");
		String valStrSplitted[]=valStr.split(",");
		String tD="tripduration";
		
		if(valStrSplitted[0].equals(tD)){
			//do nothing 
		} else{
			String startStationId = valStrSplitted[3];
			String startStationName = valStrSplitted[4];
			String endStationId = valStrSplitted[7];
			String endStationName = valStrSplitted[8]; 
			String data[] = valStrSplitted[1].split(" ");
			String dataSplit[] = data[0].split("/");			
			String mese = dataSplit[0];
			String giorno = dataSplit[1];
			if (dateOfInterest(Integer.parseInt(giorno), Integer.parseInt(mese))){
				output.collect(new Text(data[0]+"-"+startStationId+"-"+startStationName), new Text("uno"));
				output.collect(new Text(data[0]+"-"+endStationId+"-"+endStationName), new Text("uno"));
			} else{
				//do nothing
			}
			
		}
	}
	
	private boolean dateOfInterest(Integer day, Integer month){
		if(month == 6 || month == 7 || month == 8){
			return true; 
		} else {
			return false;
		}
	}
}