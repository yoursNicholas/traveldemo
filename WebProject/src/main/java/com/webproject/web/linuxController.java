package com.webproject.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.swing.filechooser.FileSystemView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webproject.dto.RouteSpot;
import com.webproject.dto.Routes;
import com.webproject.dto.RoutesInput;
import com.webproject.dto.TravelPlanesDaily;
import com.webproject.dto.TxTsp;
import com.webproject.dto.scenicspot;
import com.webproject.dto.sortByDistance;
import com.webproject.dto.sortByRanking;
import com.webproject.entity.SourceData;

import net.sf.json.JSONArray;


@Controller
public class linuxController<E> {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	String sourceDatas = "";
	private ArrayList<TravelPlanesDaily> allPlenes;
	private RoutesInput routesInput;
	private Integer prefer;
	private Integer playrule;
	private String changeDate1;
	private String changeDate2;
	private String destinations;
	private String startPoint;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	private String login(HttpServletRequest request) throws IOException {
		String encode= "";
		String userName = request.getParameter("userName");
		String pass = request.getParameter("pass");
		if(userName.equals("admin")&&pass.equals("pass")){
			encode = "success";
		}else{
			encode = "fail";
		}
        Map<String, Object> mapp = new HashMap<String, Object>();
        mapp.put("result", encode);
        String res = net.sf.json.JSONObject.fromObject(mapp).toString();
		return res;
	}
	
	@RequestMapping(value = "/FirstPage", method = RequestMethod.POST)
	@ResponseBody
	private String FirstPage(HttpServletRequest request) throws IOException {
		String dataDistrict = request.getParameter("dataDistrict");
		System.out.println("dataDistrict"+dataDistrict);
		SourceData sourceData = new SourceData();
		sourceData.setDataArea(dataDistrict);
		sourceDatas = dataDistrict;
        Map<String, Object> mapp = new HashMap<String, Object>();
        String encode = dataDistrict;
        mapp.put("success", encode);
        String res = net.sf.json.JSONObject.fromObject(mapp).toString();
		return res;
	}

	@RequestMapping(value = "/Target_start", method = RequestMethod.POST)
	@ResponseBody
	private String TargetStart(HttpServletRequest request) throws IOException, InterruptedException {
		SourceData sourceData = new SourceData();
		String dataArea = sourceDatas;
		String visitId = request.getParameter("visitId");
		String vistPass = request.getParameter("vistPass");
		String instanceNum = request.getParameter("instanceNum");
		System.out.println(visitId+"----"+vistPass+"----"+instanceNum+"-----"+dataArea);
		String path = "//home//tjw//Desktop//awstools//resource//Access_Key_Victim.txt";
        File file = new File(path);
        if(!file.exists()){
            file.getParentFile().mkdirs();
        }
        file.createNewFile();

        // write
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(visitId+" "+vistPass+ " "+ dataArea+ " "+instanceNum);
        bw.flush();
        bw.close();
        fw.close();
        //String shpath="/home/tjw/Desktop/awstools/start_victim.sh";//程序路径
        String command1="/home/tjw/Desktop/awstools/start_victim.sh";//程序路径
        
		Process process = Runtime.getRuntime().exec("chmod 777 /home/tjw/Desktop/awstools/test.sh",null,new File("/home/tjw/Desktop/awstools/"));
		//process.waitFor();//阻塞，直到上述命令执行完
        process = Runtime.getRuntime().exec("/bin/bash -c /home/tjw/Desktop/awstools/test.sh",null,new File("/home/tjw/Desktop/awstools/"));
        process.waitFor();
        String ls_1;
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(process.getInputStream()));
        while ( (ls_1=bufferedReader.readLine()) != null);
        bufferedReader.close();
        
        Map<String, Object> mapp = new HashMap<String, Object>();
        String encode = "目标实例启动成功";
        mapp.put("success", encode);
        String res = net.sf.json.JSONObject.fromObject(mapp).toString();
		return res;
	}
	
	@RequestMapping(value = "/QueryStatusT", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	private String QueryStatusT(HttpServletRequest request) throws IOException, InterruptedException {
		String res = "";
		Map<String, Object> mapp = new HashMap<String, Object>();
        Map<Integer,Map<String,String>> instances = new HashMap<Integer,Map<String,String>>();
        Map<String,String> instance = new HashMap<String,String>();
        //String path = "D:\\IIE\\victim_instances_info.txt";
        String path = "//home//tjw//Desktop//awstools//AccountInfo//victim_instances_info.txt";
        File fileText = new File(path);
        String textLine = null;
        int i = 1;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileText)));
            while((textLine = reader.readLine()) != null){
                Matcher m = Pattern.compile("^\\|{3}\\s{2}(Architecture|ImageId|InstanceId|LaunchTime|PrivateIpAddress|PublicIpAddress|VirtualizationType|Hypervisor)\\s*\\|\\s{2}(.*)\\s*\\|{3}$").matcher(textLine);
                if (m.find()){
                    instance.put(m.group(1).trim(),m.group(2).trim());
                    if ("VirtualizationType".equals(m.group(1).trim())){
                        instances.put(i++, instance);
                        instance = new HashMap<String, String>();
                    }
                    //System.out.println(index+".匹配结果="+m.group(1)+"::"+m.group(2));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("总实例："+instances);
        List<Map<String, String>> list2 = new ArrayList<Map<String,String>>();
       for (Integer in : instances.keySet()){
           System.out.println("实例"+in+":"+instances.get(in));
           Map<String,String> map = new HashMap<String,String>();
			map.put("Architecture", instances.get(in).get("Architecture"));
			map.put("Hypervisor", instances.get(in).get("Hypervisor"));
			map.put("ImageId", instances.get(in).get("ImageId"));
			map.put("InstanceId", instances.get(in).get("InstanceId"));
			map.put("LaunchTime", instances.get(in).get("LaunchTime"));
			map.put("PrivateIpAddress", instances.get(in).get("PrivateIpAddress"));
			map.put("PublicIpAddress", instances.get(in).get("PublicIpAddress"));
			map.put("VirtualizationType", instances.get(in).get("VirtualizationType"));
			list2.add(map);
       }
       System.out.println(list2);
		mapp.put("item", list2);
		mapp.put("cnt", list2.size());
		res = net.sf.json.JSONObject.fromObject(mapp).toString();
		return res;
	}
	
	@RequestMapping(value = "/attackStart", method = RequestMethod.POST)
	@ResponseBody
	private String attackStart(HttpServletRequest request) throws IOException, InterruptedException {
		SourceData sourceData = new SourceData();
		String dataArea = sourceDatas;
		String visitId = request.getParameter("visitId");
		String vistPass = request.getParameter("vistPass");
		String instanceNum = request.getParameter("instanceNum");
		System.out.println(visitId+"----"+vistPass+"----"+instanceNum+"-----"+dataArea);
		String path = "//home//tjw//Desktop//awstools//resource//Access_Key_Attacker.txt";
        File file = new File(path);
        if(!file.exists()){
            file.getParentFile().mkdirs();
        }
        file.createNewFile();

        // write
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(visitId+" "+vistPass+ " "+ dataArea+ " "+instanceNum);
        bw.flush();
        bw.close();
        fw.close();
        
		Process process = Runtime.getRuntime().exec("chmod 777 /home/tjw/Desktop/awstools/test.sh",null,new File("/home/tjw/Desktop/awstools/"));
		//process.waitFor();//阻塞，直到上述命令执行完
        process = Runtime.getRuntime().exec("/bin/bash -c /home/tjw/Desktop/awstools/test.sh",null,new File("/home/tjw/Desktop/awstools/"));
        process.waitFor();
        String ls_1;
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(process.getInputStream()));
        while ( (ls_1=bufferedReader.readLine()) != null);
        bufferedReader.close();
        
        Map<String, Object> mapp = new HashMap<String, Object>();
        String encode = "攻击实例启动成功";
        mapp.put("success", encode);
        String res = net.sf.json.JSONObject.fromObject(mapp).toString();
		return res;
	}
	
	@RequestMapping(value = "/QueryStatusA", method = RequestMethod.POST)
	@ResponseBody
	private String QueryStatusA(HttpServletRequest request) throws IOException, InterruptedException {
		String res = "";
		Map<String, Object> mapp = new HashMap<String, Object>();
        Map<Integer,Map<String,String>> instances = new HashMap<Integer,Map<String,String>>();
        Map<String,String> instance = new HashMap<String,String>();
        //String path = "C:\\Users\\Mr灬LandLord\\Desktop\\attacker_instances_info.txt";
        String p = System.getProperty("file.separator");//这便是读取桌面路径的方法了
        String p3 = System.getProperty("user.");
        URL p2 =linuxController.class.getResource("/");
        String path = "//home//tjw//Desktop//awstools//AccountInfo//attacker_instances_info.txt";
        File fileText = new File(path) ;
        String textLine = null;
        int i = 1;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileText)));
            while((textLine = reader.readLine()) != null){
                Matcher m = Pattern.compile("^\\|{3}\\s{2}(Architecture|ImageId|InstanceId|LaunchTime|PrivateIpAddress|PublicIpAddress|VirtualizationType|Hypervisor)\\s*\\|\\s{2}(.*)\\s*\\|{3}$").matcher(textLine);
                if (m.find()){
                    instance.put(m.group(1).trim(),m.group(2).trim());
                    if ("VirtualizationType".equals(m.group(1).trim())){
                        instances.put(i++, instance);
                        instance = new HashMap<String, String>();
                    }
                    //System.out.println(index+".匹配结果="+m.group(1)+"::"+m.group(2));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("总实例："+instances);
        List<Map<String, String>> list2 = new ArrayList<Map<String,String>>();
       for (Integer in : instances.keySet()){
           System.out.println("实例"+in+":"+instances.get(in));
           Map<String,String> map = new HashMap<String,String>();
			map.put("Architecture", instances.get(in).get("Architecture"));
			map.put("Hypervisor", instances.get(in).get("Hypervisor"));
			map.put("ImageId", instances.get(in).get("ImageId"));
			map.put("InstanceId", instances.get(in).get("InstanceId"));
			map.put("LaunchTime", instances.get(in).get("LaunchTime"));
			map.put("PrivateIpAddress", instances.get(in).get("PrivateIpAddress"));
			map.put("PublicIpAddress", instances.get(in).get("PublicIpAddress"));
			map.put("VirtualizationType", instances.get(in).get("VirtualizationType"));
			list2.add(map);
       }
       System.out.println(list2);
		mapp.put("item", list2);
		mapp.put("cnt", list2.size());
		mapp.put("path", p);
		mapp.put("path2", p2);
		
		res = net.sf.json.JSONObject.fromObject(mapp).toString();
		return res;
	}
	
	@RequestMapping(value = "/start_Match", method = RequestMethod.POST)
	@ResponseBody
	private String start_Match(HttpServletRequest request) throws IOException, InterruptedException {
		Map<String, Object> mapp = new HashMap<String, Object>();
		String res = "";
		Process process = Runtime.getRuntime().exec("chmod 777 /home/tjw/Desktop/awstools/test.sh",null,new File("/home/tjw/Desktop/awstools/"));
		//process.waitFor();//阻塞，直到上述命令执行完
        process = Runtime.getRuntime().exec("/bin/bash -c /home/tjw/Desktop/awstools/test.sh",null,new File("/home/tjw/Desktop/awstools/"));
        process.waitFor();
        String ls_1;
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(process.getInputStream()));
        while ( (ls_1=bufferedReader.readLine()) != null);
        bufferedReader.close();
		mapp.put("success", "同驻检测已完成");
		
		res = net.sf.json.JSONObject.fromObject(mapp).toString();
		return res;
	}
	
	
	@RequestMapping(value = "/QueryResult", method = RequestMethod.POST)
	@ResponseBody
	private String QueryResult(HttpServletRequest request) throws IOException, InterruptedException {
		String res = "";
		List<String> list = new ArrayList<String>();
		Map<String, Object> mapp = new HashMap<String, Object>();
		String path1 = "//home//tjw//Desktop//awstools//analysis_attack_results.txt";
		//String path1 = "D:\\IIE\\analysis_attack_results.txt";
		File fileText = new File(path1) ;
	    String textLine = null;
	    String next1 =null;
	    String next2 =null;
	    try {
	    	int i=1;
	        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileText)));
	        while((textLine = reader.readLine()) != null){
	            if ("Bingo!".equals(textLine.trim())) {
	                if ((next1 = reader.readLine()) != null) {
	                    System.out.println("第一行："+next1);
	                    list.add("---------------------------------------------------------------------------------------");
	                    list.add("第"+i+"组：");
	                    list.add(next1);
	                    i++;
	                } else {
	                    System.out.println("无数据！");
	                    return "";
	                }
	                if ((next2 = reader.readLine()) != null) {
	                    System.out.println("第二行："+next2);
	                    list.add(next2);
	                    
	                } else {
	                    System.out.println("无数据！");
	                    return "";
	                }

	            }
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    String result = "";
	    if(list!=null){
	    	result = "检测到同驻！";
	    }else{
	    	result = "未检测到同驻！";
	    }
		mapp.put("list", list);
		mapp.put("cnt", list.size());
		mapp.put("result",result);
		res = net.sf.json.JSONObject.fromObject(mapp).toString();
		return res;
	}
	
	/*@RequestMapping(value = "/dati",  method = { RequestMethod.POST,RequestMethod.GET })
	@ResponseBody
	public String dati(@RequestParam("startpoint")String startpoint,@RequestParam("destination")String destination,@RequestParam("preference")String preference,
			@RequestParam("playingrule")String playingrule,@RequestParam("datedeparture")String datedeparture,@RequestParam("datearrival")String datearrival) throws Exception{
		System.out.println("22222222222-------------"); 
		final Map<String, Object> map = new HashMap<String, Object>();

		prefer = Integer.valueOf(preference);
		playrule = Integer.valueOf(playingrule);
		changeDate1 = changeDate(datedeparture);
		changeDate2 = changeDate(datearrival);
		destinations = destination+"";
		startPoint = startpoint+"";
		System.out.println("....startpoint...."+startpoint);
		System.out.println("....preference...."+preference);
		System.out.println("....datedeparture...."+changeDate1);
		System.out.println("....datearrival...."+changeDate2);
		map.put("success", "success");
        String res = net.sf.json.JSONObject.fromObject(map).toString();
        System.out.println(res+"----------");
		return res;
		
	}*/
	@RequestMapping(value = "/getdata",  method = { RequestMethod.POST,RequestMethod.GET })
	@ResponseBody
	public String getdata(@RequestParam("startpoint")String startpoint,@RequestParam("destination")String destination,@RequestParam("preference")String preference,
			@RequestParam("playingrule")String playingrule,@RequestParam("datedeparture")String datedeparture,@RequestParam("datearrival")String datearrival) throws Exception{
		prefer = Integer.valueOf(preference);
		playrule = Integer.valueOf(playingrule);
		changeDate1 = changeDate(datedeparture);
		changeDate2 = changeDate(datearrival);
		destinations = destination+"";
		startPoint = startpoint+"";
		routesInput = new RoutesInput(startPoint,destinations,prefer,playrule,changeDate1,changeDate2);
		allPlenes = new ArrayList<TravelPlanesDaily>();

		
		final Map<String, Object> map = new HashMap<String, Object>();


		map.put("success", "success");
        String res = net.sf.json.JSONObject.fromObject(map).toString();
        System.out.println(res+"----------");
		return res;
	}
	
	public static String changeDate(String date2){
		String[] split = date2.split("/");
		String ss = split[2]+"-"+split[0]+"-"+split[1];
		return ss;
	}

	public static void main(String[] args) {
		RoutesInput routesInput=new RoutesInput("上海","北京",1,2,"2018-03-01","2018-03-04");
		ArrayList<TravelPlanesDaily> allPlenes=new ArrayList<TravelPlanesDaily>();
		//调用总入口//
		MakeTravelPlanes(routesInput,allPlenes);
		
		//打印输出每天的规划信息
		for (TravelPlanesDaily travelPlanesDaily : allPlenes) {
			System.out.println(travelPlanesDaily.getDate());
			//System.out.println(travelPlanesDaily.getRoutes());
			Routes routes=travelPlanesDaily.getRoutes();
			System.out.println("景点通勤时间："+routes.getRoutesTimeCostList());
			for(int i=0;1<routes.getRoutesTransportList().size()-1;i++){
				System.out.println("景点通勤出行方式："+routes.getRoutesTransportList().get(i));
			}
			System.out.println("景点间的通勤距离："+routes.getRoutesDistanceList());

			System.out.println(routes.getRouteList().size());
			for (RouteSpot spot : routes.getRouteList()) {
				System.out.println(spot.getScenicespotName());
				System.out.println(spot.getLatitude());
				System.out.println(spot.getLongitude());
				System.out.println(spot.getspotImagePath());

			}
		}
	}

	@RequestMapping(value = "/getcontent",  method = { RequestMethod.POST,RequestMethod.GET })
	@ResponseBody
	private String getcontent() throws Exception{ 
		String res = "";
		List<Map> listall = new ArrayList<Map>();
		allPlenes.clear();
		//调用总入口//
		MakeTravelPlanes(routesInput,allPlenes);
		//打印输出每天的规划信息
		for (TravelPlanesDaily travelPlanesDaily : allPlenes) {
			Map<String, Object> resultmap = new HashMap<String, Object>();
			resultmap.put("date", travelPlanesDaily.getDate());
			//System.out.println(travelPlanesDaily.getRoutes());
			Routes routes=travelPlanesDaily.getRoutes();
//			resultmap.put("journeyTimeCostList", routes.getRoutesTimeCostList());
//			System.out.println("景点通勤时间" + routes.getRoutesTimeCostList());
			if(routes.getBaiduMapJs()!=null){
				resultmap.put("mapjs", routes.getBaiduMapJs());
			}else{
				resultmap.put("mapjs", "");
			}
			ArrayList<String> routesTimeCostList = routes.getRoutesTimeCostList();
			ArrayList<String> routesTransportList = routes.getRoutesTransportList();
			ArrayList<String> routesDistanceList = routes.getRoutesDistanceList();
			System.out.println("总实例："+routes.getRouteList().size());
			List<Map<String, String>> spotlist = new ArrayList<Map<String,String>>();
			int i = 0;
			for (RouteSpot spot : routes.getRouteList()) {
				Map<String,String> spotinfo = new HashMap<String,String>();
				spotinfo.put("scenicespotName", spot.getScenicespotName());
				spotinfo.put("latitude", spot.getLatitude());
				spotinfo.put("longitude", spot.getLongitude());
				spotinfo.put("palytimeCost", spot.getpalytimeCost());
				spotinfo.put("spotImagePath", "./resources/images/"+spot.getspotImagePath());
				if(i < routes.getRouteList().size()-1){
					spotinfo.put("journeyTimeCost", "通勤时间："+routesTimeCostList.get(i)+"min    ");
					if(routesTransportList.get(i).length()>2){
						spotinfo.put("routesTransport", "乘车方式："+routesTransportList.get(i)+"    ");
					}else{
						spotinfo.put("routesTransport", "  ");
					}
					System.out.println("建议下一目的地交通方式："+routesTransportList.get(i));
					spotinfo.put("routesDistance", "距离："+routesDistanceList.get(i)+"m    ");
					System.out.println("步行".equals(routesTransportList.get(i).trim()));
					if("步行".equals(routesTransportList.get(i).trim())){
						System.out.println("----------------------");
						spotinfo.put("transImagePath", "./resources/images/walk.png");
					}else if(routesTransportList.get(i).length()>2){
						spotinfo.put("transImagePath", "./resources/images/bus.png");
					}else{
						spotinfo.put("transImagePath", "./resources/images/byckle.png");
					}
				}else{
					spotinfo.put("journeyTimeCost", "");
					spotinfo.put("routesTransport", "");
					spotinfo.put("routesDistance", "");
				}
				System.out.println("景点名称："+spot.getScenicespotName());
				System.out.println("纬度："+spot.getLatitude());
				System.out.println("经度："+spot.getLongitude());
				System.out.println("游玩时间："+spot.getpalytimeCost());
				spotlist.add(spotinfo);
				i++;
			}
			resultmap.put("routeList", spotlist);
			listall.add(resultmap);
		}
		System.out.println(listall);
		res = net.sf.json.JSONArray.fromObject(listall).toString();
		return res;
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
            byte tag_bytes[] = templateContent.getBytes();
            fileoutputstream.write(tag_bytes);
            fileoutputstream.close();
            
            String BaiduMapJs=templateContent.substring(templateContent.indexOf("var map = new BMap.Map(\"allmap\")"),
            		templateContent.indexOf("//增加折线")-1);
            return BaiduMapJs;

        } catch (Exception e) {
            System.out.print(e.toString());
            return "";
        }
    }

    /* MakeTravelPlanes 行程规划函数入口 
	 * RoutesInput 行程规划的输入参数
	 * allPlenes 列表中每一个元素代表当天的行程规划信息
	 */
	
	public static Boolean MakeTravelPlanes(RoutesInput routesInput,ArrayList<TravelPlanesDaily> allPlenes) {
		
		int favourite=routesInput.getFavourite();  //个人偏好 类型
		int travelLevel=routesInput.getTravelLevel();//游玩强度
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
			Routes route =RouteMaking(favourite,travelLevel,scenicspotinfos_routeList,scenicspotinfos_haveTraveled);	
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
	 * travelLevel 游玩强度
	 * scenicspotinfos_routeList 本次规划出的游玩景点
	 * scenicspotinfos_haveTraveled 之前已经游玩过的景点
	 * Routes 本次规划出的路径信息
	 */
	public static Routes RouteMaking(int favourite,int travelLevel,ArrayList<scenicspot>  scenicspotinfos_routeList,ArrayList<scenicspot>  scenicspotinfos_haveTraveled) {
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
            	sspot.setspotImagePath(rs.getString("imagePath").substring(rs.getString("imagePath").lastIndexOf("/")+1));
            	sspot.setRanking(Integer.valueOf(rs.getString("ranking")));
            	
            	scenicspotinfos_5A.add(sspot);
            	
            	//System.out.println(sspot.getVisitTime());
            	
            	
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
            	sspot.setspotImagePath(rs.getString("imagePath").substring(rs.getString("imagePath").lastIndexOf("/")+1));
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
            route=TspSearchForRoutes(travelLevel,scenicspotinfos_nearIn5km,scenicspotinfos_routeList);                      
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
		


		//Tsp 算法 安排路径最短旅行方案 //
	    //travelLevel 游玩强度
		public static Routes TspSearchForRoutes(int travelLevel ,ArrayList<scenicspot>  scenicspotinfos,ArrayList<scenicspot>  scenicspotinfos_routeList) {
		
			//	暂定一天出行的时间为10h 8:00 --- 18:00
			ArrayList<scenicspot>  scenicspotinfos_tmp =new ArrayList<scenicspot>();
			ArrayList<Integer> routeLines= new ArrayList<Integer>();//规划出的路径
			ArrayList<Integer> routeLinesTimeTraffic= new ArrayList<Integer>();//规划出的路径
			double TotalTimeConsume=0.0f;
			
			ArrayList <String> RoutesTimeCostList=null;//规划出的路径 景点间通勤的耗时
	        ArrayList <String> RoutesDistanceList=null;//规划出的路径 景点间通勤的距离
	        ArrayList <String> RouteLinesTrafficList=null;//景点间的换成方式
	        
	        for (scenicspot scenicspot : scenicspotinfos) {
	    		//System.out.println(scenicspot.getName()+"    "+scenicspot.getDistances());
	    		scenicspotinfos_tmp.add(scenicspot);
	    		TxTsp ts = new TxTsp(travelLevel,scenicspotinfos_tmp);
	    		if(ts.timeConsume > 600) { //(18-8)*60 == 600 minutes
	    			scenicspotinfos_tmp.remove(scenicspotinfos_tmp.size()-1);
	    			break;
	    		}
	    		routeLines=ts.routeLines;
	    		routeLinesTimeTraffic=ts.routeLinesTimeTraffic;
	    		
	    		RoutesTimeCostList =new ArrayList <String>();
	    		RoutesDistanceList =new ArrayList <String>();
	    		RouteLinesTrafficList =new ArrayList <String>();
	    		for (int i = 0; i < ts.routeLinesTimeTraffic.size(); i++) {
	    			RoutesTimeCostList.add(String.valueOf(ts.routeLinesTimeTraffic.get(i)));
	    			RoutesDistanceList.add(String.valueOf(ts.routeLinesDistanceTraffic.get(i)));
	    			RouteLinesTrafficList.add(ts.routeLinesTraffic.get(i));
	    			   			
				}
	    		
	    		TotalTimeConsume=ts.timeConsume;
	    		
		}
	        scenicspotinfos_routeList.addAll(scenicspotinfos_tmp);
	        
	        System.out.println("找到最佳出行路径");
	        String path="";
	        String Traffic="";
	        
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
	        		scenicspotinfos_tmp.get(routeLines.get(0)).getLng(),//经度
	        		scenicspotinfos_tmp.get(routeLines.get(0)).getspotImagePath()));  //图片路径名
	        
	        for(int j =1;j<routeLines.size();j++) {

	        	path+="-- "+ routeLinesTimeTraffic.get(j-1) +"min -->" + scenicspotinfos_tmp.get(routeLines.get(j)).getName();
	        	Traffic+="-- "+ RouteLinesTrafficList.get(j-1) +" -->" + scenicspotinfos_tmp.get(routeLines.get(j)).getName();
	        	Points+=",\r\n"+"new BMap.Point("+scenicspotinfos_tmp.get(routeLines.get(j)).getLng()+","+scenicspotinfos_tmp.get(routeLines.get(j)).getLat()+")";
	        	
	        	RouteList.add(new RouteSpot(scenicspotinfos_tmp.get(routeLines.get(j)).getName(),
	        			"1",
	        			scenicspotinfos_tmp.get(routeLines.get(j)).getLat(),
	        			scenicspotinfos_tmp.get(routeLines.get(j)).getLng(),
	        			scenicspotinfos_tmp.get(routeLines.get(j)).getspotImagePath())); 
	        	
	        	if(routeLines.size()==2 || j+1 < routeLines.size()) {
	        		Markers+="\r\n"+"addMarker(new BMap.Point("+scenicspotinfos_tmp.get(routeLines.get(j)).getLng()+","+scenicspotinfos_tmp.get(routeLines.get(j)).getLat()+"),\""+(j+1)+" ."+scenicspotinfos_tmp.get(routeLines.get(j)).getName()+"\");";	
	        		
	        	}
	        }
	        Points+="\r\n";
	        Markers+="\r\n";
	        
	        System.out.println("路径:" + path);
	        System.out.println("出行方式:" + Traffic);
	        System.out.println("总耗时为:" + TotalTimeConsume+" min");
	        
	        String filePath = "C:\\map\\tmp.html";
	        String disrPath = "C:\\map\\";
	        String fileName = "liuren";
	        String BaiduMapJs=MakeHtml(filePath,disrPath,fileName,centerZoom,Points,Markers);
	        
/*	        try  
	        {  
	            ProcessBuilder proc = new ProcessBuilder("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe", disrPath+fileName+".html");  
	            proc.start();  
	        }  
	        catch (Exception e)  
	        {  
	            System.out.println("Error executing progarm.");  
	        }
	       */
			
			//System.out.println(ts.timeConsume);
	        //System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	        //System.out.println(BaiduMapJs);
	        //当天的路径游玩信息//
	        Routes routes=new Routes(RouteList, RoutesTimeCostList, RoutesDistanceList,BaiduMapJs,RouteLinesTrafficList);

			return routes;
		}
}
