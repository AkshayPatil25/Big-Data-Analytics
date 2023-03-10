import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.fs.Path; 
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.StringTokenizer;
import java.io.IOException;

public class marks{
	
	public static class marksMapper extends Mapper<Object, Text, Text, IntWritable>
	{	
	public void map (Object key, Text value, Context context) throws IOException,InterruptedException
	{
		String line = value.toString();
		StringTokenizer st = new StringTokenizer(line,",");
		String name = st.nextToken();
		int marks = Integer.parseInt(st.nextToken());
		String cls = st.nextToken();
		//if(marks >= 60)
		if(cls.equals("TY"))
		   context.write(new Text("marks"),new IntWritable(marks));
		}				
	} 
	
	// Reducer class -> string,int
	public static class marksReducer extends Reducer<Text,IntWritable,Text,FloatWritable>
	{
	public void reduce(Text key,Iterable<IntWritable> values,Context context) throws IOException,
											InterruptedException
		{
			FloatWritable average = new FloatWritable();

			int sum = 0;
			int total = 0;
			for(IntWritable num : values)
			{
				sum = sum + num.get();
				total++;
			}
			average.set((float)sum/total);
	               context.write(new Text("Average is: "),average);
		}
	}
	public static void main(String args[]) throws Exception
	{
		//create the object of Configuration class
		Configuration conf=new Configuration();
		
		//create the object of Job class
		Job job=new Job(conf,"marks");
		
		//set the data type of output key
		job.setMapOutputKeyClass(Text.class);
		
		//set the data type of output value
		job.setMapOutputValueClass(IntWritable.class);
		
		//set the data type of output key
		job.setOutputKeyClass(Text.class);
		
		//set the data type of output value
		job.setOutputValueClass(FloatWritable.class);
		
		//set the data type of output
		job.setOutputFormatClass(TextOutputFormat.class);
		
		//set the data type of input
		job.setInputFormatClass(TextInputFormat.class);
			
		//set the name of Mapper class
		job.setMapperClass(marksMapper.class);
		
		//set the name of Reducer class
		job.setReducerClass(marksReducer.class);
		
		//set the input files path from 0th argument
		FileInputFormat.addInputPath(job,new Path(args[0]));
		
		//set the output files path from 1th argument
		FileOutputFormat.setOutputPath(job,new Path(args[1]));
		
		//execute the job and wait for completion
		job.waitForCompletion(true);
	}
}
