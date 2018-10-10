package com.webproject.dto;

public class RoutesInput {
	
	private String  startPlace;   //起始地
	private String  destinationPlace;  //目的地
	private int favourite;  //个人偏好
	private int travelLevel;  //游玩强度
	
	private String  startDate;  //起始日期
	private String  backDate;	//返回日期
	
	
	
	public String getStartName() {
		return startPlace;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getDestinationPlace() {
		return destinationPlace;
	}
	public String getBackDate() {
		return backDate;
	}
	public int getFavourite() {
		return favourite;
	}
	public int getTravelLevel() {
		return travelLevel;
	}
	
	public RoutesInput(String startPlace,String destinationPlace,int favourite,int travelLevel,String startDate,String backDate) {
		// TODO Auto-generated constructor stub
		this.backDate=backDate;
		this.travelLevel=travelLevel;
		this.destinationPlace=destinationPlace;
		this.favourite=favourite;
		this.startDate=startDate;
		this.startPlace=startPlace;
	}
	
	public void setStartName(String startPlace) {
		this.startPlace=startPlace;
	}
	public void setDestinationPlace(String destinationPlace) {
		this.destinationPlace=destinationPlace;
	}
	public void setStartDate(String startDate) {
		this.startDate=startDate;
	}
	public void setBackDate(String backDate) {
		this.backDate=backDate;
	}
	public void setFavourite(int favourite) {
		this.favourite=favourite;
	}
	public void setTravelLevel(int travelLevel) {
		this.travelLevel=travelLevel;
	}
	
	
	
	
	

}
