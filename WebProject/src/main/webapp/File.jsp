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
    <title>选取攻击实例</title>
	<link href="./resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="./resources/css/form.css" rel="stylesheet">
    <link href="./resources/css/style.css" rel="stylesheet">
    <link href="./resources/css/generics.css" rel="stylesheet">
</head>
 
<body style="background-image:url(./resources/img/bg.jpg);background-size:cover;">
  	<div class="container" >
	<div style="height: 100%;width: 100%;  position:relative; ">
		<div style="width: 80%;height: 50%;/*overflow: hidden;*/position: absolute; margin: auto; top: 0;left: 0;bottom: 0;right: 0;">
		<form method="post" action="" class="form-horizontal form-inline" role="form">
			<div id="showOption" style="width: 1350px;margin-top: 200px; display: inline-block; margin-left: 180px;">
<!-- 			  	<div class="form-group col-sm-3"> -->
					<div class="form-group col-sm-3">
				  	<label class="form-label">云平台:</label>
						    <select name="cloudPlatform" id="cloudPlatform" style="width: 240px;" class="form-control login-control m-b-10 m-t-15" onchange="addDataDistrict()">
						    	<option value="-1">--请选择--</option>
						    	<option value="0">Amazon</option>
						    	<option value="1">OpenShift</option>
						    	<option value="2">IBM Bluemix</option>
						    	<option value="3">阿里</option>
						    	<option value="4">百度</option>
						    	<option value="5">腾讯</option>
						    </select>
						    </div>
					    
					 		<div class="form-group col-sm-3">
							<label class="form-label">数据区域:</label>
					  		<select name="dataDistrict" id="dataDistrict" style="width: 240px;" class="form-control form-control-static login-control m-b-10 m-t-15">
					  			<option value="-1">--请选择--</option>
					  		</select>
					 		</div>

					   		<div class="form-group col-sm-3">
					  		<label class="form-label">实例类型:</label>
					  		<select name="instance" id="instance" style="width: 240px;" class="form-control login-control m-b-10 m-t-15" onchange="showOptions()">
					  			<option value="-1">--请选择--</option>
					  			<option value="0">虚拟机</option>
					  			<option value="1">容器</option>
					  		</select>
							</div>
<!-- 				</div> -->
			</div>
<!-- xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx -->
			 	<div id="hideOptions" style="width: 1350px;margin-bottom: 10px; margin-top: 30px;display: none; margin-left: 300px;">
					<div class="form-group col-sm-3">
					  	<label class="form-label">运行时:</label>
					    <select name="running" id="running" style="width: 240px;" class="form-control" onchange="">
					    	<option value="-1">--请选择--</option>
					    	<option value="0">Java</option>
					    	<option value="1">Python</option>
					    	<option value="2">Apache</option>
					    	<option value="3">nonede.js</option>
					    	<option value="4">C++</option>
					    </select>
					    </div>
					<div class="form-group col-sm-3">
					  	<label class="form-label">应用程序:</label>
						  <select name="application" id="application" style="width: 240px;" class="form-control" onchange="">
						  	<option value="-1">--请选择--</option>
						  	<option value="0">GPG</option>
						  	<option value="1">Web服务</option>
						  </select>
						  </div>				  
		  			<div class="form-group col-sm-3">
					  	<label class="form-label">加密算法:</label>
						  <select name="encryption" style="width: 240px;"class="form-control" id="encryption">
						  	<option value="-1">--请选择--</option>
						  	<option value="0">RSA</option>
						  	<option value="1">AES</option>
						  	<option value="2">DES</option>
						  </select>
					</div>
				</div>
		</form>
		</div>
		<button id="subButton" type="submit" class="btn btn-default" style="margin-top: 80px;width: 100px;position: absolute;left: 1310px;top: 300px;">下一步</button>
		</div>
	</div>
</body>
</body>
  <script src="./resources/js/jquery.min.js"></script>
  <script type="text/javascript">

var cloudPlatform = new Array();
cloudPlatform[-1] = new Array({name:'--请选择--',district:'-1'});
cloudPlatform[0] = new Array({name:'美国东部（俄亥俄州）',district:'us-east-2'},{name:'美国东部（弗吉尼亚北部）',district:'us-east-1'},{name:'美国西部（加利福尼亚北部）',district:'us-west-1'},{name:'美国西部（俄勒冈）',district:'us-west-2	'},{name:'亚太地区（孟买）',district:'ap-south-1'},{name:'亚太区域（首尔）',district:'ap-nonertheast-2'},{name:'亚太区域（新加坡）',district:'ap-southeast-1'},{name:'亚太区域（悉尼）',district:'ap-southeast-2'},{name:'亚太区域（东京）',district:'ap-nonertheast-1'},{name:'加拿大(中部)',district:'ca-central-1'},{name:'欧洲（法兰克福）',district:'eu-central-1'},{name:'欧洲（爱尔兰）',district:'eu-west-1'},{name:'欧洲(伦敦)',district:'eu-west-2'},{name:'南美洲（圣保罗）',district:'sa-east-1'});
cloudPlatform[1] = new Array({name:'暂时没有数据',district:'none'});
cloudPlatform[2] = new Array({name:'暂时没有数据',district:'none'});
cloudPlatform[3] = new Array({name:'暂时没有数据',district:'none'});
cloudPlatform[4] = new Array({name:'暂时没有数据',district:'none'});
cloudPlatform[5] = new Array({name:'暂时没有数据',district:'none'});


function addDataDistrict(){  
    $("#cloudPlatform").length=0;  
    var index = $("#cloudPlatform option:selected").val();  
	$("#dataDistrict").empty();
    for(var i=0;i<cloudPlatform[index].length;i++){
			var cp = new Option(cloudPlatform[index][i].name,cloudPlatform[index][i].district); 
			$("#dataDistrict").append(cp);
		} 
} 
/*function dataDistrict(){
	var index = $("#dataDistrict option:selected").val();
	if (index==-1){
		$("#instance").val("-1");
	}
}*/
function  showOptions(){
	//alert($("#dataDistrict option:selected").val());
	var index = $("#instance option:selected").val();
	if (index==1){
		$("#hideOptions").show();
		$("#hideOptions").css({"display": "inline-block",});
	}else{
		$("#hideOptions").hide();
	}
}
$("#subButton").click(function(){
	//alert("!");
    if ($("#cloudPlatform option:selected").val()==-1)  
    {  
        alert('请选择云平台 :)');  
        $("#cloudPlatform").focus();  
        return false;  
    } else if ($("#dataDistrict option:selected").val()==-1)  
    {  
        alert('请选择数据区域 :)');  
        $("#dataDistrict").focus();  
        return false;  
    } else if ($("#instance option:selected").val()==-1)  
    {  
        alert('请选择实例 :)');  
        $("#instance").focus();  
        return false;  
    } else {  
    	var dd = $("#dataDistrict option:selected").val();
        $.ajax({
            type:"POST",
            
            data: {"dataDistrict" : dd},
            dataType:"json",
            url : "<%=basePath%>FirstPage",
            success: function(data){
                var success = data.success;
                //alert(success);
                location.href = "File2.jsp";
                console.log(success);
            }
            });
        return true;  
    }  
});  

</script>

</html>
