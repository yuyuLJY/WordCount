package com.nancy.hadoop;
import java.io.IOException; 
import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable; 
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.mapreduce.Mapper;
public class TokenizerMapper  extends Mapper<Object, Text, Text, IntWritable>{  
	IntWritable one = new IntWritable(1);  //�������ֵʼ����1
	Text word = new Text();   //���������ʽkey����ʽ
	public void map(Object key, Text value, Context context)throws IOException, InterruptedException {
		StringTokenizer itr = new StringTokenizer(value.toString());//����ֵ��Text����Ҫת��
		while (itr.hasMoreTokens()){      
			word.set(itr.nextToken()); //������Ŀ��Ա�������
			context.write(word, one); //����ʽ<word,1>����  
		}  
    }
}