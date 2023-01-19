
import java.util.*;
import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;

public class fb
{
	public static class fbMapper extends MapReduceBase implements
	Mapper <Object ,/*Input Key Type */
	Text, /*input value type*/
	Text, /*Output key type*/
	IntWritable> /*Output value Type*/
	{
	 //Map Function
	// Count the sum of likes
	boolean flag = false;
	public void map(Object key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws    													IOException 
	{ 
	if(flag){
		String line = value.toString();
		StringTokenizer s =new StringTokenizer (line, ",");
		String id = s.nextToken();
		String type = s.nextToken();
		String date = s.nextToken();
		int likes = 0;
		int count = 0;
		while(count < 4)
		{	
			likes = Integer.parseInt(s.nextToken());
			count++;
		}
		if(date.startsWith("2") && date.contains("2018") && type.equals("video"))
	         output.collect(new Text("Likes"), new IntWritable(likes));     
	}
	 flag = true;
	}
	}
	
	//Reducer class
	public static class fbReducer extends MapReduceBase implements 
	Reducer< Text, IntWritable, Text, IntWritable > 
	{
		//Reduce function 
		public void reduce(
		Text key, 
		Iterator <IntWritable> values,
		OutputCollector<Text, IntWritable> output,Reporter reporter) throws IOException
		{
			int add = 0;
			while(values.hasNext())
			{
				add = add + values.next().get();	
			}
			output.collect(key, new IntWritable(add));
		}
	
	}


//Main function 
public static void main(String args[]) throws Exception
{
	JobConf conf = new JobConf(fb.class);
	conf.setJobName("Facebook Likes");
	conf.setOutputKeyClass(Text.class);
	conf.setOutputValueClass(IntWritable.class);
	conf.setMapperClass(fbMapper.class);
	conf.setReducerClass(fbReducer.class);
	conf.setInputFormat(TextInputFormat.class);
	conf.setOutputFormat(TextOutputFormat.class);
	FileInputFormat.setInputPaths(conf, new Path(args[0]));
	FileOutputFormat.setOutputPath(conf, new Path(args[1]));
	JobClient.runJob(conf);
}

}
