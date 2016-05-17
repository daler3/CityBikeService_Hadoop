package primo;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import primo.MainClass;
import primo.MapperClass;
import primo.ReducerClass;
//import oracle.hadoop.loader.examples.CSVInputFormat;
import CsvInput.main.java.org.apache.hadoop.*;
import CsvInput.main.java.org.apache.hadoop.mapreduce.lib.input.CSVLineRecordReader;
import CsvInput.main.java.org.apache.hadoop.mapreduce.lib.input.CSVNLineInputFormat;
import CsvInput.main.java.org.apache.hadoop.mapreduce.lib.input.CSVTextInputFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.InputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MainClass extends Configured implements Tool {
	private static final String inizioName="2015";
	private static final String fineName="-citibike-tripdata.csv";
	
	@Override
	public int run(String[] args) throws Exception {
		
		String rootPath = args[0];
		Path path1 = new Path(rootPath+inizioName+"01"+fineName);
		Path path2 = new Path(rootPath+inizioName+"02"+fineName);
		Path path3 = new Path(rootPath+inizioName+"03"+fineName);
		Path path4 = new Path(rootPath+inizioName+"04"+fineName);
		Path path5 = new Path(rootPath+inizioName+"05"+fineName);
		Path path6 = new Path(rootPath+inizioName+"06"+fineName);

		Configuration conf = getConf();
		//Configuration conf = createConfig(args);
		
		conf.set("key.value.separator.in.input.line", ",");
		//conf.set(CSVLineRecordReader.FORMAT_SEPARATOR, ",");
        //conf.set(CSVLineRecordReader.FORMAT_DELIMITER, "\"");
		//conf.setInt(CSVNLineInputFormat.LINES_PER_MAP, 40000);
		//conf.setBoolean(CSVLineRecordReader.IS_ZIPFILE, false);
		
        JobConf job = new JobConf(conf, MainClass.class);
        job.setJobName("media");
        
        job.setMapperClass(MapperClass.class);
        job.setCombinerClass(ReducerClass.class);
        job.setReducerClass(ReducerClass.class);
        
        //conf.tblproperties("skip.header.line.count"="1");
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);
			

			
        //job.setInputFormat((Class<? extends InputFormat>) CSVTextInputFormat.class);
        
        //job.setInputFormatClass
        
        //job.setInputFormat(TextInputFormat.class);
        job.setInputFormat(TextInputFormat.class);
        job.setOutputFormat(TextOutputFormat.class);
		
        
        
		FileInputFormat.setInputPaths(job, path1, path2, path3, path4, path5, path6);
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		JobClient.runJob(job);

		return 0;
	}

	public static void main(String[] args) throws Exception {
		String cavolo = "tua mamma \"tiziana\" si sta \"drogando\"";
		System.err.println(cavolo);
		String cavoli = cavolo.replaceAll("\"", "");
		System.err.println(cavoli);
		
		System.setProperty("hadoop.home.dir", "/Users/daniele/Desktop/hadoop-2.7.0/etc/hadoop");
		String home = System.getProperty("hadoop.home.dir");
		System.out.println(home);
		//System.setProperty("hadoop.home.dir", "/Users/daniele/Desktop/hadoop-2.7.0/etc/hadoop");
		Logger log= Logger.getLogger(MainClass.class);
		BasicConfigurator.configure();
		log.info("This is Logger Info");
		
		int res = ToolRunner.run(new Configuration(), new MainClass(), args);
        System.exit(res);
		
		

	}
    private Configuration createConfig(String[]argu) {
        Configuration conf = new Configuration();
        
        conf.setStrings("mapred.input.dir", argu[0]);
        conf.set(CSVLineRecordReader.FORMAT_DELIMITER, "\"");
        conf.set(CSVLineRecordReader.FORMAT_SEPARATOR, ",");
        conf.setInt(CSVNLineInputFormat.LINES_PER_MAP, 40000);
        conf.setBoolean(CSVLineRecordReader.IS_ZIPFILE, false);

        return conf;
    }

}

