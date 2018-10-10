package com.webproject.dto;


public class hotelInfo {
	private String name;  
    private String address;  
    private String lat;//经度  
    private String lng;//纬度  
    
    public hotelInfo()
    {
    	
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

}
