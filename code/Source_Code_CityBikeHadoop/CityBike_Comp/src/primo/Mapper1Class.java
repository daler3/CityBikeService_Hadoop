package primo;


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

public class Mapper1Class extends MapReduceBase implements Mapper<LongWritable, Text, IntWritable, Text> {

	private static IntWritable durata;
	private static IntWritable weekNumber;
	
	private Text word = new Text();
	
	@Override
	public void map(LongWritable key, Text value, OutputCollector<IntWritable, Text> output, Reporter reporter) throws IOException {
		
		Integer numeroAssegnamento;
		String valStr0 = value.toString();
		String valStr=valStr0.replaceAll("\"", "");

		String valStrSplitted[]=valStr.split(",");
		String userType = valStrSplitted[12];
		if(userType=="Subscriber"){
			numeroAssegnamento=0;
		}else{
			numeroAssegnamento=1;
		}
		
		String tD="tripduration";

		if(valStrSplitted[0].equals(tD)){
			//do nothing

		}else{
			String durationNcustomer = valStrSplitted[0]+"-"+numeroAssegnamento.toString();
			
			word.set(durationNcustomer);
			String settimana[] = valStrSplitted[1].split(" ");

			//splitto la settimana 
			String settimanaSplit[] = settimana[0].split("/");			
			String mese = settimanaSplit[0];
			String giorno = settimanaSplit[1];

			int nGiorno = Integer.parseInt(giorno);
			int nMese = Integer.parseInt(mese);
			int nSettimana = (int)getSettimana (nGiorno, nMese) +1;
			weekNumber = new IntWritable(nSettimana); 

			output.collect(weekNumber, word);
		}
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
