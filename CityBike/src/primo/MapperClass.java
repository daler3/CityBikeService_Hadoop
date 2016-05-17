package primo;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import com.csvreader.CsvReader;

public class MapperClass extends MapReduceBase implements Mapper<LongWritable, Text, IntWritable, IntWritable> {

	private static IntWritable durata;
	private static IntWritable weekNumber;
	
	private Text word = new Text();
	
	@Override
	public void map(LongWritable key, Text value, OutputCollector<IntWritable, IntWritable> output, Reporter reporter) throws IOException {
		
		/*
		CsvReader dati = null;
		try {
			dati = new CsvReader("Dati_Csv/201501-citibike-tripdata.csv");
			dati.readHeaders();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		   */
		
		String valStr0 = value.toString();
		String valStr=valStr0.replaceAll("\"", "");
		//System.err.println(valStr);
		String valStrSplitted[]=valStr.split(",");
		
		//System.out.println(valStr);
		//String keyStr = key.toString();
		//System.out.println(val);
		String tD="tripduration";
		//String tD2="\"tripduration\"";
		if(valStrSplitted[0].equals(tD)){
			//do nothing
			//System.out.println("ma zio");
		}else{
			//String splitted[]=valStr.split(",");
			int duration = Integer.parseInt(valStrSplitted[0]);
			durata = new IntWritable(duration);
			String settimana[] = valStrSplitted[1].split(" ");
			
			//splitto la settimana 
			String settimanaSplit[] = settimana[0].split("/");
			
			String mese = settimanaSplit[0];
			String giorno = settimanaSplit[1];
			String anno = settimanaSplit[2];
			
			int nGiorno = Integer.parseInt(giorno);
			int nMese = Integer.parseInt(mese);
			
			int nSettimana = (int)getSettimana (nGiorno, nMese) +1;
			
			weekNumber = new IntWritable(nSettimana); 
			
			//word.set(settimana[0]); 
			
			output.collect(weekNumber, durata);
		}
	
	/*	
		while (dati.readRecord())
        {
			String tripduration = dati.get("tripduration");
			int duration = Integer.parseInt(tripduration);
			durata = new IntWritable(duration);
			String starttime = dati.get("starttime");
			String[] splitted1 = starttime.split("\n");
			//tring[] splitted2 = splitted1
			word.set(splitted1[0]);
			output.collect(word, durata);
			
        }
		dati.close();
		
		*/
		/*
		String line = value.toString();
		StringTokenizer tokenizer = new StringTokenizer(line);
		while (tokenizer.hasMoreTokens()) {
			word.set(tokenizer.nextToken());
			output.collect(word, one);
		}
		*/
	}
	
	private double getSettimana (int giorno, int mese){
		double settimana = 0;
		int giorniPassati; 
		
		if(mese==1){
			giorniPassati=giorno; 
		}
		else {
			giorniPassati=getGiorniDaInizioAnno(mese)+giorno; 
		}	
		settimana = Math.ceil(giorniPassati/7);
		
		return settimana;		
	}
	
	
	private int getGiorniDaInizioAnno (int mese){
		switch(mese){
			case 1: return 0;
			case 2: return 31;
			case 3: return 59; //31+28
	 		case 4: return 90; //59+31
			case 5: return 120; //90+30
			case 6: return 151; //120+31 
			case 7: return 181; 
			case 8: return 211;
			case 9: return 242; //211+31 
			case 10: return 272; //242+30
			case 11: return 303; //272+31
			case 12: return 333;
		}
		return -1;
	
	}

}
