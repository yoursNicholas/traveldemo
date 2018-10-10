package com.webproject.dto;

import java.util.Comparator;


public class sortByRanking implements Comparator{
	//按等级逆序排列
	  public int compare(Object arg0, Object arg1) {  
		  scenicspot user0=(scenicspot)arg0;  
		  scenicspot user1=(scenicspot)arg1;
		  return user1.getRanking() - user0.getRanking();
	  }  
}