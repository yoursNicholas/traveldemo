package com.webproject.dto;

import java.util.ArrayList;


public class Routes {
	//当天的景点游玩路径规划类
	
	private ArrayList <RouteSpot> RouteList; //路径列表
	private ArrayList <String> RoutesTimeCostList ; //景点间的通勤时间
	private ArrayList <String> RoutesDistanceList ;  //景点间的通勤距离
	private ArrayList <String> RoutesTransportList ;  //景点间通勤的交通方式
	
	private String BaiduMapJs ;  // 生成的百度地图api js 元素 
	
	
	public ArrayList<RouteSpot> getRouteList() {
		
		return RouteList;	
	}
	public ArrayList<String> getRoutesTimeCostList() {
		
		return RoutesTimeCostList;	
	}
	public ArrayList<String> getRoutesDistanceList() {
		
		return RoutesDistanceList;	
	}
	public ArrayList<String> getRoutesTransportList() {
		
		return RoutesTransportList;	
	}

	public String getBaiduMapJs() {
		
		return BaiduMapJs;	
	}
	
	public Routes(ArrayList <RouteSpot> RouteList,ArrayList <String> RoutesTimeCostList,ArrayList <String> RoutesDistanceList,String BaiduMapJs,ArrayList<String> RoutesTransportList) {
		// TODO Auto-generated constructor stub		
		this.RouteList=RouteList;
		this.RoutesTimeCostList=RoutesTimeCostList;
		this.RoutesDistanceList=RoutesDistanceList;
		this.BaiduMapJs=BaiduMapJs;
		this.RoutesTransportList=RoutesTransportList;
	}
	
	public void setRouteList(ArrayList<RouteSpot> RouteList) {
		
		this.RouteList=RouteList;	
	}
	public void setRoutesTimeCostList(ArrayList<String> RoutesTimeCostList) {
		
		this.RoutesTimeCostList=RoutesTimeCostList;	
	}
	public void setRoutesDistanceList(ArrayList<String> RoutesDistanceList) {
		
		this.RoutesDistanceList=RoutesDistanceList;	
	}
	public void setRoutesTransportList(ArrayList<String> RoutesTransportList) {
		
		this.RoutesTransportList=RoutesTransportList;	
	}
	
	public void setBaiduMapJsList(String BaiduMapJs) {
		
		this.BaiduMapJs=BaiduMapJs;	
	}

}
