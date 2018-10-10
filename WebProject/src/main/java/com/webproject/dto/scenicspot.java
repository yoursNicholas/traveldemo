package com.webproject.dto;



public class scenicspot implements Comparable {
	private String name;  
    private String address;  
    private String lat;//经度  
    private String lng;//纬度
    private String spotImagePath;//景点图片存放路径 
    private double distances;
    private  int ranking;
    private int visitTime;//参观景点的平均用时 分钟 计算
    
    public scenicspot()
    {
    	distances=0.0f;
    }
    
    public String getName(){
    	return name;
    }
    
    public String getAddress(){
    	return address;
    }
    
    public String getLat(){
    	return lat;
    }    
    public String getLng(){
    	return lng;
    }
    public String getspotImagePath(){
    	return spotImagePath;
    }
    
    public int getVisitTime(){
    	return visitTime;
    }
    public double getDistances(){
    	return distances;
    }
    public int getRanking(){
    	return ranking;
    }   
    
    public void setName(String name) {
		this.name=name;
	}
    public void setAddress(String address) {
		this.address=address;
	}
    public void setLat(String lat) {
		this.lat=lat;
	}
    public void setLng(String lng) {
		this.lng=lng;
	}
    public void setDistances(double distances){
    	this.distances=distances;
    }
    
    public void setRanking(int ranking){
    	this.ranking=ranking;
    }
    
    public void setspotImagePath(String spotImagePath){
    	this.spotImagePath=spotImagePath;
    }
    
    
    public void setVisitTime(int visitTime){
    	this.visitTime=visitTime;
    }
   
    
    public int compareTo(Object o) {
    	scenicspot s = (scenicspot) o;
        if (this.getDistances() > s.getDistances())
        	return 1;
        return -1;
       }

}


