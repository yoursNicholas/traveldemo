package com.webproject.dto;

import java.util.Comparator;

public class sortByDistance implements Comparator{
	  public int compare(Object arg0, Object arg1) {  
		  scenicspot user0=(scenicspot)arg0;  
		  scenicspot user1=(scenicspot)arg1;
		  if(user0.getDistances() > user1.getDistances())
			  return 1;
		  return -1;
	  }  
}
