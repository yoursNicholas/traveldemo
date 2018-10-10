package com.webproject.dto;

public class RouteSpot {
	
	private String scenicespotName; //景点名
	private String palytimeCost;    //景点游玩耗时
	private String latitude;		//纬度
	private String longitude;		//经度
	private String spotImagePath;	//景点图片存放路径
	
    public String getScenicespotName(){
    	return scenicespotName;
    }
    public String getpalytimeCost(){
    	return palytimeCost;
    }
    public String getLatitude(){
    	return latitude;
    }
    public String getLongitude(){
    	return longitude;
    }
    public String getspotImagePath(){
    	return spotImagePath;
    }
    
    
    public RouteSpot(String scenicespotName,String palytimeCost,String latitude,String longitude , String spotImagePath) {
		// TODO Auto-generated constructor stub
    	this.scenicespotName=scenicespotName;
    	this.palytimeCost=palytimeCost;
    	this.latitude=latitude;
    	this.longitude=longitude;
    	this.spotImagePath=spotImagePath;
	}    
    
    public void setScenicespotName(String scenicespotName){
    	this.scenicespotName=scenicespotName;
    }
    public void setpalytimeCost(String palytimeCost){
    	this.palytimeCost=palytimeCost;
    }
    public void setLatitude(String latitude){
    	this.latitude=latitude;
    }
    public void setLongitude(String longitude){
    	this.longitude=longitude;
    }
    
    public void setspotImagePath(String spotImagePath){
    	this.spotImagePath=spotImagePath;
    }
	

}
