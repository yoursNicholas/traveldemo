package com.webproject.dto;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class TxTsp {
	
	public double timeConsume;//所选路径游玩的耗时
	private int cityNum; // 城市数量
	private int[][] distance; // 距离矩阵
	private double[] x;//lng
	private double[] y;//lat

	private int[] colable;//代表列，也表示是否走过，走过置0
	private int[] row;//代表行，选过置0
	public ArrayList<Integer> routeLines;//规划出的路径
	public ArrayList<Integer> routeLinesTimeTraffic;//规划出的路径节点之间的通勤时间
	public ArrayList<Integer> routeLinesDistanceTraffic;//规划出的路径节点之间的通勤距离
	public ArrayList<String> routeLinesTraffic;//景点间的出行工具
	private int travelLevel;
	
	private ArrayList<scenicspot>  scenicspotinfos;
	
	
	/*
	 *getLatAndLngByAddress 函数重写 
	 */
	public static Map<String, String> getLatAndLngByAddress(scenicspot orig , scenicspot dest ,int Transportint){
		
		String origStr=orig.getLat()+","+orig.getLng();
		String destStr=dest.getLat()+","+dest.getLng();
		
		return getLatAndLngByAddress(origStr, destStr, Transportint);
	}
	
	
	/*
	 * 输入起止地址坐标字符串 ，得到路程的耗时和距离
	 * 输入形式：String orig= "40.45,116.34"   
	 * String dest= "40.34,116.45"
	 * Transportint 为出行交通工具方式           1--》 驾车     、   2---》 骑行  、 3----》 步行  、 4----》 公交
	 * 返回Map集合 包含  distance（以m 为单位） 和  duration（以min 为单位），以及出行换乘信息（此数据仅对 公交路线请求有效）
	 */
	public static Map<String, String> getLatAndLngByAddress(String orig , String dest ,int Transportint){
		String origins="";
		String destinations="";
        String distance = "0";
        String duration = "0";
        String Path="";//换乘信息（此数据仅对 公交路线请求有效）
        Map<Integer, String> transportintMap =new HashMap<Integer,String>();
        transportintMap.put(1, "driving");
        transportintMap.put(2, "riding");
        transportintMap.put(3, "walking");
        transportintMap.put(4, "bus");
        
        if (Transportint<1 || Transportint >4) {
			Transportint=1;
		}
        String transportintStr=transportintMap.get(Transportint);//获取映射后的出行方式字符串
        
        try {  
            origins = java.net.URLEncoder.encode(orig,"UTF-8");  
            destinations = java.net.URLEncoder.encode(dest,"UTF-8"); 
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } 
        
        //origins=40.45,116.34|40.54,116.35&destinations=40.34,116.45|40.35,116.46
        //String url = String.format("http://api.map.baidu.com/geocoder/v2/?"
        //+"ak=r3SrPvXNDL1X3oV9hOnAMI3BvFC4OHcS&output=json&address=%s",address);
        String url = "";
        if (4 == Transportint) {
        	url = String.format("http://api.map.baidu.com/direction/v2/transit?origin=%s&destination=%s"
        			+"&ak=nDYQBgDkpqhHI5YC0Y6gCPMotNf2MxgQ",origins,destinations);//公交路线请求结构
		}else {
	        url= String.format("http://api.map.baidu.com/routematrix/v2/%s?output=json"
	        		+"&origins=%s&destinations=%s&ak=nDYQBgDkpqhHI5YC0Y6gCPMotNf2MxgQ",transportintStr,origins,destinations);
		}

        URL myURL = null;
        URLConnection httpsConn = null; 
        //System.out.println(url);
        //进行转码
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {

        }
        try {
            httpsConn = (URLConnection) myURL.openConnection();
            if (httpsConn != null) {
                InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(insr);
                String data = null;
                if ((data = br.readLine()) != null) {
                	//System.out.println(data);
                	
                	JSONObject json = JSONObject.fromObject(data);
                	//System.out.println(json.get("result"));
                	if(4 == Transportint) {//公交路线规划专用json解析结构     
                		JSONObject jsonResult = JSONObject.fromObject(json.get("result"));
                		JSONArray jsonArrayRoutes = JSONArray.fromObject(jsonResult.get("routes")); 
                        	
                        for (int i = 0; i < jsonArrayRoutes.size(); i++) { 
                        	//System.out.println(jsonArrayRoutes.getJSONObject(i));
                        	distance=jsonArrayRoutes.getJSONObject(i).getString("distance");
                        	duration=jsonArrayRoutes.getJSONObject(i).getString("duration");
                        	//System.out.println(distance);//距离
                        	//System.out.println(duration);//时间
                        	JSONArray jsonArraySteps = JSONArray.fromObject(jsonArrayRoutes.getJSONObject(i).get("steps"));
                        	for (int j = 0; j < jsonArraySteps.size(); j++) {
                        		Path+="|";//每条换成路线以 | 为分隔符
                        		//System.out.println("++++++++++++++++++++++");
                        		//System.out.println(jsonArraySteps.get(j));
                        		JSONArray jsonArrayArraySteps = JSONArray.fromObject(jsonArraySteps.get(j));
                        		for (int k = 0; k < jsonArrayArraySteps.size(); k++) {
                        			JSONObject jsonInstructions = JSONObject.fromObject(jsonArrayArraySteps.getJSONObject(k));
                        			//System.out.println("----------------------");
                        			//System.out.println(jsonInstructions.get("instructions"));
                        			Path+=jsonInstructions.get("instructions");                      			
                        		}
                        	}
                        	
                        	break;
                        }
                	}else {
                        JSONArray jsonArray = JSONArray.fromObject(json.get("result")); 
	                    for (int i = 0; i < jsonArray.size(); i++) {  
	                    	distance=JSONObject.fromObject(jsonArray.getJSONObject(i).getString("distance")).get("value").toString();
	                    	duration=JSONObject.fromObject(jsonArray.getJSONObject(i).getString("duration")).get("value").toString();
	                    	break;
	                    }
                	}
                }               	
                insr.close();
            }       
        } catch (IOException e) {

        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("distance", distance);
        map.put("duration", duration);
        map.put("path", Path);
        return map;
	}
	
	
	

	public TxTsp(int travelLevel,ArrayList<scenicspot>  scenicspotinfos) {
		this.scenicspotinfos=scenicspotinfos;
		this.travelLevel=travelLevel;
		cityNum = scenicspotinfos.size();
		try {
			init(scenicspotinfos);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("TxTsp init exception!");
		}
		//printinit();
		solve();
		
	}

	private void init(ArrayList<scenicspot>  scenicspotinfos) throws IOException {
		// 读取数据


		distance = new int[cityNum][cityNum];
		x = new double[cityNum]; //lng
		y = new double[cityNum];  //lat
		for (int i = 0; i < cityNum; i++) {
			// 读取一行数据，数据格式1 6734 1453

			x[i] = Double.valueOf(scenicspotinfos.get(i).getLng());// x坐标
			y[i] = Double.valueOf(scenicspotinfos.get(i).getLat());// y坐标
		}


		// 计算距离矩阵
		// ，针对具体问题，距离计算方法也不一样，此处用的是att48作为案例，它有48个城市，距离计算方法为伪欧氏距离，最优值为10628
		for (int i = 0; i < cityNum - 1; i++) {
			distance[i][i] = 0; // 对角线为0
			for (int j = i + 1; j < cityNum; j++) {
				double rij = algorithm(x[i], y[i], x[j], y[j]);
				// 四舍五入，取整
				int tij = (int) Math.round(rij);
				distance[i][j] = tij;
				distance[j][i] = distance[i][j];
				
				/*if (tij < rij) {
					distance[i][j] = tij + 1;
					distance[j][i] = distance[i][j];
				} else {
					distance[i][j] = tij;
					distance[j][i] = distance[i][j];
				}*/
			}
		}

		distance[cityNum - 1][cityNum - 1] = 0;

		colable = new int[cityNum];
		colable[0] = 0;
		for (int i = 1; i < cityNum; i++) {
			colable[i] = 1;
		}

		row = new int[cityNum];
		for (int i = 0; i < cityNum; i++) {
			row[i] = 1;
		}

	}
	
	//根据经纬度 计算两点之间的距离
	public  double algorithm(double longitude1, double latitude1, double longitude2, double latitude2) {
	              double Lat1 = rad(latitude1); // 纬度
	              double Lat2 = rad(latitude2);
	              double a = Lat1 - Lat2;//两点纬度之差
	              double b = rad(longitude1) - rad(longitude2); //经度之差
	              double s = 2 * Math.asin(Math
	                            .sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(Lat1) * Math.cos(Lat2) * Math.pow(Math.sin(b / 2), 2)));//计算两点距离的公式

	              s = s * 6378137.0;//弧长乘地球半径（半径为米）
	              s = Math.round(s * 10000d) / 10000d;//精确距离的数值
	              return s;
	}

	private  double rad(double d) {
		return d * Math.PI / 180.00; //角度转换成弧度
	}
	
	public void solve(){
		
		int[] temp = new int[cityNum];
		String path="0";
		timeConsume=0.0;
		routeLines=new ArrayList<Integer>();
		routeLinesTimeTraffic=new ArrayList<Integer>();
		routeLinesDistanceTraffic=new ArrayList<Integer>();
		routeLinesTraffic=new ArrayList<String>();
		routeLines.add(0);
		
		int s=0;//计算距离
		int i=0;//当前节点
		int j=0;//下一个节点
		//默认从0开始
		while(row[i]==1){
			//复制一行
			for (int k = 0; k < cityNum; k++) {
				temp[k] = distance[i][k];
				//System.out.print(temp[k]+" ");
			}
			//System.out.println();
			//选择下一个节点，要求不是已经走过，并且与i不同
			j = selectmin(temp);
			//找出下一节点
			row[i] = 0;//行置0，表示已经选过
			colable[j] = 0;//列0，表示已经走过
			
			path+="-->" + j;
			routeLines.add(j);
			//System.out.println(i + "-->" + j);
			//System.out.println(distance[i][j]);
			s = s + distance[i][j];
			i = j;//当前节点指向下一节点
		}
		timeConsume+=60.0; //minutes  暂时按每个景点游玩一小时计算
		//timeConsume+=scenicspotinfos.get(routeLines.get(0)).getVisitTime(); //minutes 获取每个景点的游玩时间
		//double speed=25000.0/60.0; //  m/min  定义市内汽车平均通勤速度
		//System.out.println("--- "+timeConsume+" "+scenicspotinfos.get(routeLines.get(0)).getName());
		


		for(int idx=1; idx< routeLines.size();idx++) {
			int Transportint=1;//默认出行方式 驾车
			if (distance[routeLines.get(idx-1)][routeLines.get(idx)]<=500) {
				//里程小于0.5km 步行
				Transportint=3;
				routeLinesTraffic.add("步行");
			}else if (distance[routeLines.get(idx-1)][routeLines.get(idx)]<= 2000) {
				//里程小于0.5-2km 骑行
				Transportint=2;
				routeLinesTraffic.add("骑行");
			}else {
				//里程大于2km 
				if (1 == travelLevel ) {
					//如果行程强度 轻松 则以驾车为主//
					Transportint=1;
					routeLinesTraffic.add("驾车");
				}else {
					//其他强度 则以公交为主//
					Transportint=4;
					
				}
				
			}
			//int ttime=(int)(distance[routeLines.get(idx-1)][routeLines.get(idx)]/speed);
			//int ttime=(int)getLatAndLngByAddress(String.valueOf(y[routeLines.get(idx-1)])+","+String.valueOf(x[routeLines.get(idx-1)]),
			//		String.valueOf(y[routeLines.get(idx)])+","+String.valueOf(x[routeLines.get(idx)]), 
			//		Transportint).get("duration")/60;//以分钟为单位
			Map<String, String> resultMap = getLatAndLngByAddress(String.valueOf(y[routeLines.get(idx-1)])+","+String.valueOf(x[routeLines.get(idx-1)]),
					String.valueOf(y[routeLines.get(idx)])+","+String.valueOf(x[routeLines.get(idx)]), Transportint);
			
			int ttime=(int)Integer.valueOf(resultMap.get("duration"))/60;//以分钟为单位
			ttime=ttime>0?ttime:1;
			timeConsume+= ttime;
			routeLinesTimeTraffic.add(ttime);
			routeLinesDistanceTraffic.add(distance[routeLines.get(idx-1)][routeLines.get(idx)]);
			timeConsume+=60.0;
			
			if (4 == Transportint) {
				routeLinesTraffic.add("公交"+resultMap.get("path"));
			}
			
			
			//System.out.println("+++ "+scenicspotinfos.get(routeLines.get(idx)).getVisitTime()+" "+scenicspotinfos.get(routeLines.get(idx)).getName());
		}
		timeConsume-=60.0;

		
		//System.out.println("*** "+timeConsume);
		
		
		//System.out.println("路径:" + path);
		//System.out.println("总距离为:" + s);
		//System.out.println("总耗时为:" + timeConsume+" min");
		
	}
	
	public int selectmin(int[] p){
		int j = 0, m = p[0], k = 0;
		//寻找第一个可用节点，注意最后一次寻找，没有可用节点
		while (colable[j] == 0) {
			j++;
			//System.out.print(j+" ");
			if(j>=cityNum){
				//没有可用节点，说明已结束，最后一次为 *-->0
				m = p[0];
				break;
				//或者直接return 0;
			}
			else{
				m = p[j];
			}
		}
		//从可用节点J开始往后扫描，找出距离最小节点
		for (; j < cityNum; j++) {
			if (colable[j] == 1) {
				if (m >= p[j]) {
					m = p[j];
					k = j;
				}
			}
		}
		return k;
	}


	public void printinit() {
		System.out.println("print begin....");
		for (int i = 0; i < cityNum; i++) {
			for (int j = 0; j < cityNum; j++) {
				System.out.print(distance[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("print end....");
	}



}
