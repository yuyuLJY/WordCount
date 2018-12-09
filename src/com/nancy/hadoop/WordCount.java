package com.nancy.hadoop;

import org.apache.hadoop.conf.Configuration; 
import org.apache.hadoop.fs.Path; 
import org.apache.hadoop.io.IntWritable; 
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.mapreduce.Job; 
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat; 
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat; 
import org.apache.hadoop.util.GenericOptionsParser;
public class WordCount {
	 public static void main(String[] args) throws Exception { 
		 Configuration conf = new Configuration();//ʵ��������Hadoop�������ļ����ȡ����
		 String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();//args�����в���
		 if (otherArgs.length != 2) {//�жϲ����ĸ����Ƿ���ȷ 
		   	System.err.println("Usage: wordcount <in> <out>");      
	    	System.exit(2);    
		 }
		 Job job = new Job(conf, "wordcount");//job_name = "wordcount"    
		 job.setJarByClass(WordCount.class);//����    
		 job.setMapperClass(TokenizerMapper.class);  //����  
		 job.setReducerClass(IntSumReducer.class);   //����
		 job.setOutputKeyClass(Text.class);    //���
		 job.setOutputValueClass(IntWritable.class); //���   
		 FileInputFormat.addInputPath(job, new Path(otherArgs[0]));//�����ļ�    
		 FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));//����ļ�    
		 System.exit(job.waitForCompletion(true) ? 0 : 1);//��ִ����ϣ��˳�
	 }
}
