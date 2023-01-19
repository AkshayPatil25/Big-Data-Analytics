import java.util.*;
import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;


public class Forest{
	// MApper class -> output -> string, int
	public static class ForestMapper extends MapReduceBase implements 
	Mapper<Object, /*Input Key Type */
	Text, /*Input value Type */
	Text, /* Output Key Type */
	FloatWritable> /* Output value Type */
	{
	
	boolean flag=false;
	public void map(Object key, Text value,OutputCollector<Text ,FloatWritable > output, Reporter reporter) throws IOException
	{
         String line[]= value.toString().split(",",13);
         if(flag){
        
     	 
     	 String date =line[2];
   
     	 if(date.equals("aug")){
	  Float area = Float.parseFloat(line[12]);     	     
     	   
     	   output.collect(new Text("aug"),new FloatWritable(area));
     	   }
     	 }
     	   flag = true;
    } 
    }
       // Reducer class 
       public static class ForestReducer extends MapReduceBase implements           Reducer<Text,FloatWritable,Text,FloatWritable>{
       public void reduce(Text key,
       Iterator<FloatWritable>values,
       OutputCollector<Text ,FloatWritable> output,
       Reporter reporter)throws IOException
       {
       	float add =0;
                while(values.hasNext())
                 {
                  add =add + values.next().get();
                   }
                output.collect(key,new FloatWritable(add));
       	
       
   } 
  }
   public static void main(String args[]) throws Exception
	{
	JobConf conf = new JobConf(Forest.class);
	conf.setJobName("Facebook shares");
	
	conf.setOutputKeyClass(Text.class);
	conf.setOutputValueClass(FloatWritable.class);
	 	
	conf.setMapperClass(ForestMapper.class);
	conf.setReducerClass(ForestReducer.class);
	conf.setInputFormat(TextInputFormat.class);
	conf.setOutputFormat(TextOutputFormat.class);
	FileInputFormat.setInputPaths(conf,new Path(args[0]));
	FileOutputFormat.setOutputPath(conf,new Path(args[1]));
	JobClient.runJob(conf);
  }
}
