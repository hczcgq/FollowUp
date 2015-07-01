package com.shbestwin.followupmanager.common.util;

/*
 * sum 包含子字符串sun的个数
 * 
 * */
public class StringCounterUtil {
	private int counter = 0;
	public int stringNumbers(String sum,String sun)
	{
		if(sum != null && sun != null){
		    if (sum.indexOf(sun)==-1)
		    {
		        return 0;
		    }
		    else if(sum.indexOf(sun) != -1)
		    {
		        counter++;
		        stringNumbers(sum.substring(sum.indexOf(sun)+sun.length()),sun);
		        return counter;
		    }
		    return 0;
		}else{
			return -1;
		}

	}
}
