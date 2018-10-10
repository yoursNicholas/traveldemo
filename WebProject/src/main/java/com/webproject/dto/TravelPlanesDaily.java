package com.webproject.dto;


public class TravelPlanesDaily {
	String	date;   //游玩出行日期
	Routes  routes;  //当天规划的游玩路径信息
	
	public String getDate() {
		return date;
	}
	public Routes getRoutes() {
		return routes;
	}
	
	public TravelPlanesDaily(String date,Routes routes) {
		// TODO Auto-generated constructor stub
		this.date=date;
		this.routes=routes;
	}
	
	
	public void setDate(String date) {
		this.date=date;
	}
	public void setRoutes(Routes routes) {
		this.routes=routes;
	}
}
