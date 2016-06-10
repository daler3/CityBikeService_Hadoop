package primo;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

import primo.MainClass;
import primo.Mapper1Class;
import primo.Reducer1Class;
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
import org.apache.hadoop.mapred.jobcontrol.Job;
import org.apache.hadoop.mapred.jobcontrol.JobControl;
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
		
        JobConf job1 = new JobConf(conf, MainClass.class);
        job1.setJobName("media");        
        job1.setMapperClass(Mapper1Class.class);
        //job1.setCombinerClass(Reducer1Class.class);
        job1.setReducerClass(Reducer1Class.class);     
        //conf.tblproperties("skip.header.line.count"="1");
        job1.setOutputKeyClass(IntWritable.class);
        job1.setOutputValueClass(Text.class);
        job1.setInputFormat(TextInputFormat.class);
        job1.setOutputFormat(TextOutputFormat.class);
		FileInputFormat.setInputPaths(job1, path1, path2, path3, path4, path5, path6);
		FileOutputFormat.setOutputPath(job1, new Path(args[1]));
        
        JobConf job2 = new JobConf(conf, MainClass.class);
        job2.setJobName("secondo");
        job2.setMapperClass(Mapper2Class.class);
        //job2.setCombinerClass(Reducer2Class.class);
        job2.setReducerClass(Reducer2Class.class);  
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(Text.class);
        job2.setInputFormat(TextInputFormat.class);
        job2.setOutputFormat(TextOutputFormat.class);
        FileInputFormat.setInputPaths(job2, path1, path2, path3, path4, path5, path6);
	    FileOutputFormat.setOutputPath(job2, new Path(args[2]));
	    
        JobConf job3 = new JobConf(conf, MainClass.class);
        job3.setJobName("terzo");
        job3.setMapperClass(Mapper3Class.class);
        //job3.setCombinerClass(Combiner3Class.class);
        job3.setReducerClass(Reducer3Class.class);  
        job3.setOutputKeyClass(Text.class);
        job3.setOutputValueClass(Text.class);
        job3.setInputFormat(TextInputFormat.class);
        job3.setOutputFormat(TextOutputFormat.class);
        FileInputFormat.setInputPaths(job3, path1, path2, path3, path4, path5, path6);
	    FileOutputFormat.setOutputPath(job3, new Path(args[3]+"/temporanea"));
	    
	    JobConf job4 = new JobConf(conf, MainClass.class);
        job4.setJobName("terzo due");
        job4.setMapperClass(Mapper4Class.class);
        //job3.setCombinerClass(Combiner3Class.class);
        job4.setReducerClass(Reducer4Class.class);  
        job4.setOutputKeyClass(Text.class);
        job4.setOutputValueClass(Text.class);
        job4.setInputFormat(TextInputFormat.class);
        job4.setOutputFormat(TextOutputFormat.class);
        FileInputFormat.setInputPaths(job4, args[3]+"/temporanea/part-00000");
	    FileOutputFormat.setOutputPath(job4, new Path(args[3]+"/finita"));
	    
	    	    
        Job actualJob1 = new Job(job1);
        Job actualJob2 = new Job(job2);	
        Job actualJob3 = new Job(job3);
        Job actualJob4 = new Job(job4);
        JobControl jobControl = new JobControl("chaining");
        jobControl.addJob(actualJob1);
        jobControl.addJob(actualJob2);
        //jobControl.addJob(actualJob3);
        //jobControl.addJob(actualJob4);
        //actualJob4.addDependingJob(actualJob3); 
        
        Thread t = new Thread(jobControl); 
        t.setDaemon(true);
        t.start(); 
                      
        while (!jobControl.allFinished()) { 
          try { 
            Thread.sleep(1000); 
          } catch (InterruptedException e) { 
            // Ignore. 
          } 
        } 
        		
		//JobClient.runJob(job4);

		return 0;
	}

	public static void main(String[] args) throws Exception {

		System.setProperty("hadoop.home.dir", "/Users/daniele/Desktop/hadoop-2.7.0/etc/hadoop");
		String home = System.getProperty("hadoop.home.dir");
		System.out.println(home);
		//System.setProperty("hadoop.home.dir", "/Users/daniele/Desktop/hadoop-2.7.0/etc/hadoop");
		Logger log= Logger.getLogger(MainClass.class);
		BasicConfigurator.configure();
		log.info("This is Logger Info");
		
		int res = ToolRunner.run(new Configuration(), new MainClass(), args);
		System.out.println("Finished");
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

