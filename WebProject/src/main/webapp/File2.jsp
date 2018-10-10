<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
        <meta name="format-detection" content="telephone=no">
        <title>实例</title>
        <meta charset="UTF-8" />
        <!-- CSS -->
        <link href="./resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="./resources/css/form.css" rel="stylesheet">
        <link href="./resources/css/style.css" rel="stylesheet">
        <!-- <link href="css/animate.css" rel="stylesheet"> -->
        <link href="./resources/css/generics.css" rel="stylesheet">
    </head>
    <body id="skin-blur-violate" style="background-image:url(./resources/img/bg.jpg);background-size:cover;">
        <section id="login">
            
            <!-- Login -->
            <div class="box tile animated active" id="box-login" style="margin:0 auto;float:left;margin-left: 40px;width: 670px">
                <h3 class="m-t-0 m-b-20">目标实例</h3>
                <div class="lg_input">
                    访 问 I D： <input type="text" id="vist_id" class="login-control m-b-10 m-t-15" placeholder="请输入访问ID">
                    <i id="lg_cross"></i>
                </div>
                <span class="lg_hint lg_num_hint"></span>
                <div class="lg_input">
                    访问密钥：<input type="text" value name="testfild" id="vist_pass" class="login-control m-b-10 m-t-15"placeholder="请输入访问密钥">
                </div>
                <span class="lg_hint lg_pass_hint"></span>
                <div class="lg_input">
                    实例数量：<input value name="testfild" id="instance_num" class="login-control m-b-10 m-t-15" placeholder="请输入实例数量">
                </div>
                <span class="lg_hint lg_pass_hint"></span>
                <button class="btn btn-sm lg_btn" id="target_start" style="width:30%;margin-bottom: 40px;font-size:140%;">开始启动</button>
                <span class="lg_hint lg_pass_hint"></span>
                <button class="btn btn-sm lg_btn" id="query_status_t" style="width:53%;text-align:center;font-size:140%;">查询启动状况</button>
                <span class="lg_hint lg_pass_hint"></span>
                <div id="totalnum" style="margin-top: 20px"></div>
                <div id="" style="margin-top: 10px;height:280px;overflow-y:auto;">
	                <table id="startstatus_t" style="height:100%; width: 100%; overflow:auto;margin: 0 auto;border:1px solid #0094ff;overflow-y:auto;">
	                </table>
                </div>
            </div>


            <div class="box tile animated active" id="box-login" style="margin:0 auto;float:right;margin-right: 80px;width: 670px">
                <h3 class="m-t-0 m-b-20">攻击实例</h3>
                <div class="lg_input">
                    访 问 I D： <input type="text" id="vist_id_a" class="login-control m-b-10 m-t-15" placeholder="请输入访问ID">
                    <i id="lg_cross"></i>
                </div>
                <span class="lg_hint lg_num_hint"></span>
                <div class="lg_input">
                    访问密钥：<input type="text" value name="testfild" id="vist_pass_a" class="login-control m-b-10 m-t-15"placeholder="请输入访问密钥">
                </div>
                <span class="lg_hint lg_pass_hint"></span>
                <div class="lg_input">
                    实例数量：<input value name="testfild" id="instance_num_a" class="login-control m-b-10 m-t-15" placeholder="请输入实例数量">
                </div>
                <span class="lg_hint lg_pass_hint"></span>
                <button class="btn btn-sm lg_btn" id="target_start_a" style="width:30%;margin-bottom: 40px;font-size:140%;">开始启动</button>
                <span class="lg_hint lg_pass_hint"></span>
                <div id="lg_input" style="height: 200px">
                <button class="btn btn-sm lg_btn" id="query_status_a" style="width:53%;text-align:center;font-size:140%;">查询启动状况</button>
                <div id="totalnum_a" style="margin-top: 20px"></div>
                <div id="" style="margin-top: 10px;height:280px;overflow:auto;">
	                <table id="startstatus_a" style="height:100%; width: 100%; overflow:auto;margin: 0 auto; border:1px solid #0094ff; overflow:scroll;">
	                </table>
                </div>
            </div>
            <button class="btn btn-sm " id="start_mat" style="width:100%;height: 7%;margin-right: 80px;margin-top: 180px;font-size:140%;">开始检测</button>
            <button class="btn btn-sm " id="query_res" style="width:100%;height: 7%;margin-right: 80px;margin-top: 10px;font-size:140%;">查询检测结果</button>
            <div id="returnnum" style="margin-top: 20px;font-size:140%;"></div>
            <div id="startstatus_r" style="margin-top: 20px;font-size:140%;"></div>
        </section>
        
        <!-- Javascript Libraries -->
        <!-- jQuery -->
        <script src="./resources/js/jquery.min.js"></script> <!-- jQuery Library -->
        
        <!-- Bootstrap -->
        <script src="./resources/js/bootstrap.min.js"></script>
        
        <!--  Form Related -->
        <script src="./resources/js/icheck.js"></script> <!-- Custom Checkbox + Radio -->
        
        <!-- All JS functions -->
        <script src="./resources/js/functions.js"></script>
        <script src="./resources/js/jquery.cookie.js"></script>
        <script type="text/javascript">
        $(document).ready(function(){
	        $("#target_start").click(function(){
         		alert("目标实例正在启动...");
	            var visitId = $("#vist_id").val();
	            var vistPass = $("#vist_pass").val();
	            var instanceNum = $("#instance_num").val();
	                    $.ajax({
	                    	type: "POST",
	                    	url : "<%=basePath%>Target_start",
	                     	data: {"visitId":visitId,
	                     		"vistPass":vistPass,
	                     		"instanceNum":instanceNum
	                     		},
	                     	dataType: "json",
	                     	success: function(data){
	                     		var success = data.success;
	                     		alert(success);
	                     		console.log(success);
	                        }
	                    });
	        	});
		        $("#query_status_t").click(function(){
                    $.ajax({
                    	type: "POST",
                    	url : "<%=basePath%>QueryStatusT",
                     	dataType: "json",
                     	success: function(data){
                     		console.log(data);
                     		var cnt = data.cnt;
                     		$("#totalnum").html("一共收到"+cnt+"个实例的数据");
                     		var info = "";
                     		info += "<tr >"+"<th >Num </th>"+"<th>Arch </th>"+"<th>Hyper </th>"+"<th>ImgId </th>"+"<th>InsId </th>"+"<th>LTime </th>"+"<th>PriIpA </th>"+"<th>PubIpA </th>"+"<th>VirTyp </th>"+"</tr>";
                     		for(var i = 0;i<cnt;i++){
                     			info += '<tr valign="middle" style="border:1px solid #0094ff;">'+
                     			'<th style="font-weight:bold;WORD-WRAP: break-word;height:25px;width:80px;border:1px solid #0094ff; ">'+ (parseInt("1")+i) +'</th>'+
                     			'<th style="font-weight:bold;WORD-WRAP: break-word;width:80px;word-break:break-all;border:1px solid #0094ff; ">'+data.item[i].Architecture+'</th>'+
                     			'<th style="font-weight:bold;WORD-WRAP: break-word;width:80px;word-break:break-all;border:1px solid #0094ff;">'+data.item[i].Hypervisor+'</th>'+
                     			'<th style="font-weight:bold;WORD-WRAP: break-word;width:100px;word-break:break-all;border:1px solid #0094ff;">'+data.item[i].ImageId+'</th>'+
                     			'<th style="font-weight:bold;WORD-WRAP: break-word;width:110px;word-break:break-all;border:1px solid #0094ff;">'+data.item[i].InstanceId+'</th>'+
                     			'<th style="font-weight:bold;WORD-WRAP: break-word;width:130px;word-break:break-all;border:1px solid #0094ff;">'+data.item[i].LaunchTime+'</th>'+
                     			'<th style="font-weight:bold;WORD-WRAP: break-word;width:80px;word-break:break-all;border:1px solid #0094ff;">'+data.item[i].PrivateIpAddress+'</th>'+
                     			'<th style="font-weight:bold;WORD-WRAP: break-word;width:80px;word-break:break-all;border:1px solid #0094ff;">'+data.item[i].PublicIpAddress+'</th>'+
                     			'<th style="font-weight:bold;WORD-WRAP: break-word;width:70px;word-break:break-all;border:1px solid #0094ff;">'+data.item[i].VirtualizationType+'</th></tr>';
                     		}
                     		
                     		$("#startstatus_t").html(info);
                        }
                    });
		        	});
        });
        
        
        $("#target_start_a").click(function(){
     		alert("攻击实例正在启动...");
            var visitId = $("#vist_id_a").val();
            var vistPass = $("#vist_pass_a").val();
            var instanceNum = $("#instance_num_a").val();
                    $.ajax({         
                    	type: "POST",
                    	url : "<%=basePath%>attackStart",
                     	data: {"visitId":visitId,
                     		"vistPass":vistPass,
                     		"instanceNum":instanceNum
                     		},
                     	dataType: "json",
                     	success: function(data){
                     		var success = data.success;
                     		alert(success);
                     		console.log(success);
                        }
                    });
        	});
	        $("#query_status_a").click(function(){
                   $.ajax({
                   	type: "POST",
                   	url : "<%=basePath%>QueryStatusA",
                    	dataType: "json",
                    	success: function(data){
                    		console.log(data);
                    		var cnt = data.cnt;
                    		console.log(data.path);
                    		console.log(data.path2);
                    		$("#totalnum_a").html("一共收到"+cnt+"个实例的数据");
                    		var info = "";
                    		info += "<tr >"+"<th >Num </th>"+"<th>Arch </th>"+"<th>Hyper </th>"+"<th>ImgId </th>"+"<th>InsId </th>"+"<th>LTime </th>"+"<th>PriIpA </th>"+"<th>PubIpA </th>"+"<th>VirTyp </th>"+"</tr>";
                    		for(var i = 0;i<cnt;i++){
                    			info += '<tr valign="middle" style="border:1px solid #0094ff;">'+
                     			'<th style="font-weight:bold;WORD-WRAP: break-word;height:25px;width:80px;border:1px solid #0094ff; ">'+ (parseInt("1")+i) +'</th>'+
                     			'<th style="font-weight:bold;WORD-WRAP: break-word;width:80px;word-break:break-all;border:1px solid #0094ff; ">'+data.item[i].Architecture+'</th>'+
                     			'<th style="font-weight:bold;WORD-WRAP: break-word;width:80px;word-break:break-all;border:1px solid #0094ff;">'+data.item[i].Hypervisor+'</th>'+
                     			'<th style="font-weight:bold;WORD-WRAP: break-word;width:100px;word-break:break-all;border:1px solid #0094ff;">'+data.item[i].ImageId+'</th>'+
                     			'<th style="font-weight:bold;WORD-WRAP: break-word;width:110px;word-break:break-all;border:1px solid #0094ff;">'+data.item[i].InstanceId+'</th>'+
                     			'<th style="font-weight:bold;WORD-WRAP: break-word;width:130px;word-break:break-all;border:1px solid #0094ff;">'+data.item[i].LaunchTime+'</th>'+
                     			'<th style="font-weight:bold;WORD-WRAP: break-word;width:80px;word-break:break-all;border:1px solid #0094ff;">'+data.item[i].PrivateIpAddress+'</th>'+
                     			'<th style="font-weight:bold;WORD-WRAP: break-word;width:80px;word-break:break-all;border:1px solid #0094ff;">'+data.item[i].PublicIpAddress+'</th>'+
                     			'<th style="font-weight:bold;WORD-WRAP: break-word;width:70px;word-break:break-all;border:1px solid #0094ff;">'+data.item[i].VirtualizationType+'</th></tr>';
                     		}
                    		
                    		$("#startstatus_a").html(info);
                       }
                   });
        	});
	        $("#start_mat").click(function(){
         		alert("正在进行同驻检测...");
	                    $.ajax({              	
	                    	type: "POST",
	                    	url : "<%=basePath%>start_Match",
	                     	data: {},
	                     	dataType: "json",
	                     	success: function(data){
	                     		var success = data.success;
	                     		alert(success);
	                     		console.log(success);
	                        }
	                    });
	        	});
	        
	        $("#query_res").click(function(){
                $.ajax({
                	type: "POST",
                	url : "<%=basePath%>QueryResult",
                 	dataType: "json",
                 	success: function(data){
                 		var list = data.list;
                 		var cnt = data.cnt;
                 		var info = "";
                 		for(var i = 0;i<cnt;i++){
                 			info +='<span style="font-weight:bold;display:block;">'+data.list[i]+'</span>';
                 		}
                 		$("#startstatus_r").html(info);
                 		$("#returnnum").html(data.result);
                    }
                });
     	});
        </script>
    </body>
</html>
