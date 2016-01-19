package com.walmart.retailtech.move.informationte.anchormanflint.interactors.implementations;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
/*
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import com.couchbase.spark.connection.*;
import com.couchbase.spark.internal.*;
import com.couchbase.spark.rdd.*;
import com.couchbase.spark.sql.*;
import com.couchbase.spark.streaming.*;
*/

public class StartingFlint 
{
	public StartingFlint() {}
	
	public static void main(String[] args) throws Exception
	{
		String logFile = "/Users/behowar/Desktop/Developer/SparkTest"; 
	    
	    SparkConf conf = new SparkConf()
	    						.setMaster( "local[*]" )
	    						.setAppName( "StartingFlint" );
	    
	    JavaSparkContext jsc = new JavaSparkContext( conf );
	    
		for(int i=0;i<10;i++)
		{
			go( logFile, jsc );
			Thread.sleep(5000);
		}
	}
	
	public static void go( String logFile, JavaSparkContext jsc ) 
	{
	    JavaRDD<String> logData = jsc.textFile( logFile ).cache();

	    long numAs = logData.filter( 
	    		new Function<String, Boolean>() 
	    		{
					private static final long serialVersionUID = 4909184422213181514L;

					public Boolean call(String s)
	    			{ 
	    				return s.contains("a");
	    			}
	    		}).count();

	    long numBs = logData.filter(
	    		new Function<String, Boolean>() 
	    		{
					private static final long serialVersionUID = -1705526448941350635L;

					public Boolean call(String s) 
	    			{ 
	    				return s.contains("b"); 
	    			}
	    		}).count();

	    System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
	  }
	}