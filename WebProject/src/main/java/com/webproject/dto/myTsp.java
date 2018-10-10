package com.webproject.dto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import com.webproject.dto.sortByDistance;
import com.webproject.dto.sortByRanking;
import com.webproject.dto.scenicspot;
import com.webproject.dto.TxTsp;
import com.webproject.dto.TravelPlanesDaily;
import com.webproject.dto.RoutesInput;



public class myTsp {
	
	
	//根据经纬度 计算两点之间的距离
	public static double algorithm(double longitude1, double latitude1, double longitude2, double latitude2) {
	              double Lat1 = rad(latitude1); // 纬度
	              double Lat2 = rad(latitude2);
	              double a = Lat1 - Lat2;//两点纬度之差
	              double b = rad(longitude1) - rad(longitude2); //经度之差
	              if(a==0 && b ==0) {
	            	  return 0.0f;
	              }
	              double s = 2 * Math.asin(Math
	                            .sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(Lat1) * Math.cos(Lat2) * Math.pow(Math.sin(b / 2), 2)));//计算两点距离的公式

	              s = s * 6378137.0;//弧长乘地球半径（半径为米）
	              s = Math.round(s * 10000d) / 10000d;//精确距离的数值
	              return s;
	}

	private static double rad(double d) {
		return d * Math.PI / 180.00; //角度转换成弧度
	}
	
	/**
     * @Title: MakeHtml 
     * @Description: 创建html
     * @param    filePath 设定模板文件
     * @param    disrPath  生成html的存放路径
     * @param    fileName  生成html名字 
     * @return void    返回类型 
     * @throws
     */
    public static String MakeHtml(String filePath,String disrPath,String fileName ,String centerZoom , String Points,String Markers){
        try {
            //String title = "var point = new BMap.Point(116.400244,39.92556);\r\nmap.centerAndZoom(point, 12);\r\nvar marker = new BMap.Marker(point);\r\n"
            //		+ "map.addOverlay(marker);\r\n";
            
            System.out.print(filePath);
            String templateContent = "";
            FileInputStream fileinputstream = new FileInputStream(filePath);// 读取模板文件
            int lenght = fileinputstream.available();
            byte bytes[] = new byte[lenght];
            fileinputstream.read(bytes);
            fileinputstream.close();
            templateContent = new String(bytes);
            //System.out.print(templateContent);
            templateContent = templateContent.replaceAll("###centerAndZoom###", centerZoom); //替换模板html中的聚焦点
            templateContent = templateContent.replaceAll("###Points###", Points);//替换折线点组
            templateContent = templateContent.replaceAll("###Markers###", Markers);//替换标注
            System.out.print(templateContent);
            
            String fileame = fileName + ".html";
            fileame = disrPath+"/" + fileame;// 生成的html文件保存路径。
            FileOutputStream fileoutputstream = new FileOutputStream(fileame);// 建立文件输出流
            System.out.print("文件输出路径:");
            System.out.print(fileame);
            byte tag_bytes[] = templateContent.getBytes();
            fileoutputstream.write(tag_bytes);
            fileoutputstream.close();
            
            String BaiduMapJs=templateContent.substring(templateContent.indexOf("var map = new BMap.Map(\"allmap\")"),
            		templateContent.indexOf("//增加折线")-1);
//            System.out.println("========================================================================"+BaiduMapJs+"========================================================================");
            return BaiduMapJs;

        } catch (Exception e) {
            System.out.print(e.toString());
            return "";
        }
    }
	
	
	//Tsp 算法 安排路径最短旅行方案 //
	public static Routes TspSearchForRoutes(ArrayList<scenicspot>  scenicspotinfos,ArrayList<scenicspot>  scenicspotinfos_routeList) {
	
		//	暂定一天出行的时间为10h 8:00 --- 18:00
		ArrayList<scenicspot>  scenicspotinfos_tmp =new ArrayList<scenicspot>();
		ArrayList<Integer> routeLines= new ArrayList<Integer>();//规划出的路径
		ArrayList<Integer> routeLinesTimeTraffic= new ArrayList<Integer>();//规划出的路径
		double TotalTimeConsume=0.0f;
		
		ArrayList <String> RoutesTimeCostList=null;//规划出的路径 景点间通勤的耗时
        ArrayList <String> RoutesDistanceList=null;//规划出的路径 景点间通勤的距离
        
        for (scenicspot scenicspot : scenicspotinfos) {
    		//System.out.println(scenicspot.getName()+"    "+scenicspot.getDistances());
    		scenicspotinfos_tmp.add(scenicspot);
    		TxTsp ts = new TxTsp(scenicspotinfos_tmp);
    		if(ts.timeConsume > 600) { //(18-8)*60 == 600 minutes
    			scenicspotinfos_tmp.remove(scenicspotinfos_tmp.size()-1);
    			break;
    		}
    		routeLines=ts.routeLines;
    		routeLinesTimeTraffic=ts.routeLinesTimeTraffic;
    		
    		RoutesTimeCostList =new ArrayList <String>();
    		RoutesDistanceList =new ArrayList <String>();
    		for (int i = 0; i < ts.routeLinesTimeTraffic.size(); i++) {
    			RoutesTimeCostList.add(String.valueOf(ts.routeLinesTimeTraffic.get(i)));
    			RoutesDistanceList.add(String.valueOf(ts.routeLinesDistanceTraffic.get(i)));
			}
    		
    		TotalTimeConsume=ts.timeConsume;
    		
	}
        scenicspotinfos_routeList.addAll(scenicspotinfos_tmp);
        
        System.out.println("找到最佳出行路径");
        String path="";
        
        //当天的景点游玩路径
        ArrayList <RouteSpot> RouteList =new ArrayList <RouteSpot>();
        
        
        path+=scenicspotinfos_tmp.get(routeLines.get(0)).getName();
        String centerZoom=scenicspotinfos_tmp.get(routeLines.get(0)).getLng()+","+scenicspotinfos_tmp.get(routeLines.get(0)).getLat()+"\r\n";  //替换地图聚焦点坐标
        String Points="new BMap.Point("+scenicspotinfos_tmp.get(routeLines.get(0)).getLng()+","+scenicspotinfos_tmp.get(routeLines.get(0)).getLat()+")";  ////替换地图折线点坐标
        String Markers="addMarker(new BMap.Point("+scenicspotinfos_tmp.get(routeLines.get(0)).getLng()+","+scenicspotinfos_tmp.get(routeLines.get(0)).getLat()+"),\"1. "+scenicspotinfos_tmp.get(routeLines.get(0)).getName()+"\");";
        
        
        //构建当天的景点路径规划列表//
        RouteList.add(new RouteSpot(scenicspotinfos_tmp.get(routeLines.get(0)).getName(), //景点名
        		"1",  //景点游玩耗时
        		scenicspotinfos_tmp.get(routeLines.get(0)).getLat(),   //纬度
        		scenicspotinfos_tmp.get(routeLines.get(0)).getLng()));  //经度
        
        for(int j =1;j<routeLines.size();j++) {

        	path+="-- "+ routeLinesTimeTraffic.get(j-1) +"min -->" + scenicspotinfos_tmp.get(routeLines.get(j)).getName();
        	Points+=",\r\n"+"new BMap.Point("+scenicspotinfos_tmp.get(routeLines.get(j)).getLng()+","+scenicspotinfos_tmp.get(routeLines.get(j)).getLat()+")";
        	
        	RouteList.add(new RouteSpot(scenicspotinfos_tmp.get(routeLines.get(j)).getName(),
        			"1",
        			scenicspotinfos_tmp.get(routeLines.get(j)).getLat(),
        			scenicspotinfos_tmp.get(routeLines.get(j)).getLng()));
        	
        	if(routeLines.size()==2 || j+1 < routeLines.size()) {
        		Markers+="\r\n"+"addMarker(new BMap.Point("+scenicspotinfos_tmp.get(routeLines.get(j)).getLng()+","+scenicspotinfos_tmp.get(routeLines.get(j)).getLat()+"),\""+(j+1)+" ."+scenicspotinfos_tmp.get(routeLines.get(j)).getName()+"\");";	
        		
        	}
        }
        Points+="\r\n";
        Markers+="\r\n";
        
        System.out.println("路径:" + path);
        System.out.println("总耗时为:" + TotalTimeConsume+" min");
        
        String filePath = "D:\\IIE\\zhaochongming\\TravelPlaneProject-03-05\\tmp.html";
        String disrPath = "D:\\IIE\\zhaochongming\\TravelPlaneProject-03-05\\";
        String fileName = "liuren";
        String BaiduMapJs=MakeHtml(filePath,disrPath,fileName,centerZoom,Points,Markers);
        
        try  
        {  
            ProcessBuilder proc = new ProcessBuilder("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe", disrPath+fileName+".html");  
            proc.start();  
        }  
        catch (Exception e)  
        {  
            System.out.println("Error executing progarm.");  
        }
       
		
		//System.out.println(ts.timeConsume);
        //System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        //System.out.println(BaiduMapJs);
        //当天的路径游玩信息//
        Routes routes=new Routes(RouteList, RoutesTimeCostList, RoutesDistanceList,BaiduMapJs);

		return routes;
	}
	
	
	public static void main(String[] args) {
		
		/*RoutesInput routesInput=new RoutesInput("上海","北京",1,2,"2018-03-01","2018-03-07");
		ArrayList<TravelPlanesDaily> allPlenes=new ArrayList<TravelPlanesDaily>();
		//调用总入口//
		MakeTravelPlanes(routesInput,allPlenes);
		
		//打印输出每天的规划信息
		for (TravelPlanesDaily travelPlanesDaily : allPlenes) {
			System.out.println(travelPlanesDaily.date);
			//System.out.println(travelPlanesDaily.getRoutes());
			Routes routes=travelPlanesDaily.getRoutes();
			System.out.println(travelPlanesDaily.getRoutes().getRoutesTimeCostList());
			System.out.println(travelPlanesDaily.getRoutes().getRoutesDistanceList());
			System.out.println(routes.getRouteList().size());
			for (RouteSpot spot : routes.getRouteList()) {
				System.out.println(spot.getScenicespotName());
			}
			
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}*/
		
		
		RoutesInput routesInput=new RoutesInput("上海","北京",1,2,"2018-03-01","2018-03-03");
		ArrayList<TravelPlanesDaily> allPlenes=new ArrayList<TravelPlanesDaily>();
		//调用总入口//
		MakeTravelPlanes(routesInput,allPlenes);
		
		//打印输出每天的规划信息
		for (TravelPlanesDaily travelPlanesDaily : allPlenes) {
			System.out.println(travelPlanesDaily.date);
			//System.out.println(travelPlanesDaily.getRoutes());
			Routes routes=travelPlanesDaily.getRoutes();
			System.out.println("景点通勤时间"+routes.getRoutesTimeCostList());
			System.out.println(routes.getRouteList().size());
			for (RouteSpot spot : routes.getRouteList()) {
				System.out.println(spot.getScenicespotName());
				System.out.println(spot.getLatitude());
				System.out.println(spot.getLongitude());
			}
			System.out.println("getBaiduMapJs=================================="+routes.getBaiduMapJs());
		}

			
		
	}
	
	/* MakeTravelPlanes 行程规划函数入口 
	 * RoutesInput 行程规划的输入参数
	 * allPlenes 列表中每一个元素代表当天的行程规划信息
	 */
	
	public static Boolean MakeTravelPlanes(RoutesInput routesInput,ArrayList<TravelPlanesDaily> allPlenes) {
		
		int favourite=routesInput.getFavourite();  //个人偏好 类型
		String destinationPlace=routesInput.getDestinationPlace();  //目的地
		String sDate=routesInput.getStartDate(); //出发日期
		String bDate=routesInput.getBackDate();  //返回日期
		
		
		
		ArrayList<scenicspot>  scenicspotinfos_haveTraveled =new ArrayList<scenicspot>();  //过去一天已经旅行过的景点
		ArrayList<scenicspot>  scenicspotinfos_routeList=new ArrayList<scenicspot>(); //产生的路径列表
		
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();

		Date date_s = null; //初始化date 
		Date date_b = null; //初始化date 

		try { 

		date_s = sdf1.parse(sDate); 
		date_b = sdf1.parse(bDate);
		
		cal.setTime(date_s);
		cal.add(Calendar.DATE, 1); // 日期加1天
		date_s=cal.getTime();

		} catch (ParseException e) { 

		e.printStackTrace(); 

		} 
		
		
		//遍历日期段 计算行程规划
		for(Date ss=date_s;ss.before(date_b);) {
			Routes route =RouteMaking(favourite,scenicspotinfos_routeList,scenicspotinfos_haveTraveled);	
			scenicspotinfos_haveTraveled.addAll(scenicspotinfos_routeList);//访问过的景点列表添加 上次产生路径规划列表
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			allPlenes.add(new TravelPlanesDaily(format.format(ss), route));
			
			cal.setTime(ss);
			cal.add(Calendar.DATE, 1); // 日期加1天
			ss=cal.getTime();
			
			System.out.println("####################################################");
		}
		
		return true;	
	}
	
	
	
	
	
	/* RouteMaking 根据用户的偏好，生成当天的路径游玩列表
	 * favourite 用户偏好
	 * scenicspotinfos_routeList 本次规划出的游玩景点
	 * scenicspotinfos_haveTraveled 之前已经游玩过的景点
	 * Routes 本次规划出的路径信息
	 */
	public static Routes RouteMaking(int favourite,ArrayList<scenicspot>  scenicspotinfos_routeList,ArrayList<scenicspot>  scenicspotinfos_haveTraveled) {
		// TODO Auto-generated method stub
		
		//声明Connection对象
        Connection con;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://101.201.152.221:3306/crawler";
        //MySQL配置时的用户名
        String user = "admin";
        //MySQL配置时的密码
        String password = "111111";
        FileOutputStream out = null;
        
        
        
        /*定义  个人偏好目的地 从 int---> String 的映射
         *  1  文化古迹
		 *	2    休闲娱乐
		 *	3    自然风光
         */
        
        Map<Integer, String> mapFavourite = new HashMap<Integer, String>(); 
        mapFavourite.put(1, "文化古迹");
        mapFavourite.put(2, "休闲娱乐");
        mapFavourite.put(3, "自然风光");
        
        Routes route=null;
   
        
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
         
            
            
            //按照个人的偏好，查询第一个初始景点作为当日的路径规划的种子点 ，并按数据库字段 raning 等级进行逆序排列  //
            ArrayList<scenicspot>  scenicspotinfos_5A =new ArrayList<scenicspot>();
            
            //2.创建statement类对象，用来执行SQL语句！！
            PreparedStatement psql;
            //要执行的SQL语句
            String sql = "select * from tb_scenicspotinfo_beijing where Type  like ? order by ranking desc";
            psql=con.prepareStatement(sql);
            //psql.setString(1, "%AAAAA%");
            //psql.setString(1, "5");
            psql.setString(1, mapFavourite.get(favourite));
            ResultSet rs=psql.executeQuery();  
            
            scenicspot sspot;
            int idx=0;             
            while(rs.next()){
            	
            	sspot =new scenicspot();
            	sspot.setName(rs.getString("scenicespotName"));
            	sspot.setLng(rs.getString("longitude"));
            	sspot.setLat(rs.getString("latitude"));
            	sspot.setRanking(Integer.valueOf(rs.getString("ranking")));
            	scenicspotinfos_5A.add(sspot);
            	
                //获取stuname这列数据
                //System.out.println();
            }
            
            //滤除访问过的景点//
    		for (scenicspot scenicspot : scenicspotinfos_haveTraveled) {
    			for(int i=0;i<scenicspotinfos_5A.size();i++) {
    				if(scenicspotinfos_5A.get(i).getName().equals(scenicspot.getName())) {
    					scenicspotinfos_5A.remove(i);
    					break;
    				}
    			}
    		}
            //scenicspotinfos_5A.removeAll(scenicspotinfos_haveTraveled);
            
    		System.out.println("================================================");
    		/*for (scenicspot scenicspot : scenicspotinfos_haveTraveled) {
    			System.out.println(scenicspot.getName());
    		}
    		System.out.println("(((((((((((((((((((((()))))))))))))))))))");
    		for (scenicspot scenicspot : scenicspotinfos_5A) {
    			System.out.println(scenicspot.getName());
    		}*/
            
            if(scenicspotinfos_5A.isEmpty()) {
            	System.out.println("未找到 个人偏好的景区!");
            	con.close();
            	
            	return route;
            }
            
            System.out.println(scenicspotinfos_5A.get(0).getName());
            
            //查询除去初始种子景点以外的景点 , 并计算与种子景点之间距离 //
            sql = "select * from tb_scenicspotinfo_beijing where scenicespotName not in(?) and Type like ? ORDER BY ranking DESC";
            psql=con.prepareStatement(sql);
            
            psql.setString(1, scenicspotinfos_5A.get(0).getName());
            psql.setString(2, mapFavourite.get(favourite));
            
            rs=psql.executeQuery();
            
            ArrayList<scenicspot>  scenicspotinfos_notIn5AFirst =new ArrayList<scenicspot>();
            scenicspotinfos_notIn5AFirst.add(scenicspotinfos_5A.get(0)); //加入 初始核心景点
            
            System.out.println("fffffffff");
            while(rs.next()){
            	//System.out.println(rs.getString("scenicespotName"));
            	if(rs.getString("longitude").equals("") || rs.getString("latitude").equals(""))
            		continue;
            	
            	sspot =new scenicspot();
            	sspot.setName(rs.getString("scenicespotName"));
            	sspot.setLng(rs.getString("longitude"));
            	sspot.setLat(rs.getString("latitude"));
            	double dis=algorithm(Double.parseDouble(sspot.getLng()), Double.parseDouble(sspot.getLat()), Double.parseDouble(scenicspotinfos_5A.get(0).getLng()), Double.parseDouble(scenicspotinfos_5A.get(0).getLat()));
            	sspot.setDistances(dis);
            	sspot.setRanking(Integer.valueOf(rs.getString("ranking")));
            	//System.out.println(dis);
            	scenicspotinfos_notIn5AFirst.add(sspot);               
            }
            
            
            
            //滤除访问过的景点//
    		for (scenicspot scenicspot : scenicspotinfos_haveTraveled) {
    			for(int i=0;i<scenicspotinfos_notIn5AFirst.size();i++) {
    				if(scenicspotinfos_notIn5AFirst.get(i).getName().equals(scenicspot.getName())) {
    					scenicspotinfos_notIn5AFirst.remove(i);
    					break;
    				}
    			}
    		}
    		
    		/*System.out.println("(((((((((((((((((((((()))))))))))))))))))");
    		for (scenicspot scenicspot : scenicspotinfos_notIn5AFirst) {
    			System.out.println(scenicspot.getName());
    		}*/
            //scenicspotinfos_notIn5AFirst.removeAll(scenicspotinfos_haveTraveled);
            
            System.out.println("aaaaaaa");
            //--	先按照与种子景点间的距离进行排序，再按等级进行排序	--//
            //Collections.sort(scenicspotinfos_notIn5AFirst);
            
            Collections.sort(scenicspotinfos_notIn5AFirst,new sortByRanking());
            Collections.sort(scenicspotinfos_notIn5AFirst,new sortByDistance());
            
            System.out.println("cccccccccc");
            ArrayList<scenicspot>  scenicspotinfos_nearIn5km =new ArrayList<scenicspot>();
            
            //输出距离排序结果 //
            /*for (scenicspot scenicspot : scenicspotinfos_notIn5AFirst) {
            	//距离 核心5A景点 半径5km以内的景点 
            	if(scenicspot.getDistances() <=5000) {
            		scenicspotinfos_nearIn5km.add(scenicspot);
            		//System.out.println(scenicspot.getName()+"    "+scenicspot.getDistances());
            	}
	
			}*/
            scenicspotinfos_nearIn5km.addAll(scenicspotinfos_notIn5AFirst);
            
            //ArrayList<scenicspot>  scenicspotinfos_routeList=new ArrayList<scenicspot>(); //产生的路径列表
            
            //进行路径规划操作
            //route 为Tsp算法规划出的路径信息  //
            route=TspSearchForRoutes(scenicspotinfos_nearIn5km,scenicspotinfos_routeList);                      
            con.close();        
        	}catch(ClassNotFoundException e){   
	            //数据库驱动类异常处理
	            System.out.println("Sorry,can`t find the Driver!");   
	            e.printStackTrace();   
	            } catch(SQLException e) {
	            //数据库连接失败异常处理
	            e.printStackTrace();  
	            }catch (Exception e) {
	            // TODO: handle exception
	            e.printStackTrace();
	        }finally{
	            System.out.println("数据库操作成功！！");
	            return route;
	        }

	}

}
