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
    <body id="skin-blur-violate" style="background-image:url(./resources/img/bg.jpg);background-size:cover;">
		<section id="login">
            <div class="clearfix"></div>
            <div class="box tile animated active" id="box-login">
                <h3 class="m-t-0 m-b-20" style="text-align:center">同驻检测工具</h3>               
                <div class="lg_input">
                    <span class="lg_span">账号：</span>
                    <input type="text" id="lg_num" class="login-control m-b-10 m-t-15" placeholder="请输入账号">
                    <i id="lg_cross"></i>
                </div>                
                <span class="lg_hint lg_num_hint"></span>
                <div class="lg_input">
                    <span class="lg_span">密码：</span>
                    <input type="password" value name="testfild" id="lg_pass" class="keyboard login-control m-t-10 m-b-10" placeholder="请输入密码">   
                </div>
                <span class="lg_hint lg_pass_hint"></span>
                <div class="checkbox m-b-20 m-t-15">
                    <label>
                        <input type="checkbox" id="rmUser">记住账号
                    </label>                   
                </div>
                <button class="btn btn-sm lg_btn" id="sub_login">登录</button>
                
            </div>
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
            
            /* --------------------------------------------------------
        	Components
            -----------------------------------------------------------*/
            (function(){
                /* Textarea */
            	if($('.auto-size')[0]) {
            	    $('.auto-size').autosize();
            	}

                //Select
            	if($('.select')[0]) {
            	    $('.select').selectpicker();
            	}
            })();

            
            /* --------------------------------------------------------
             Login + exit
            -----------------------------------------------------------*/


            //获取cookie的值
        	var username = $.cookie('username');
        	var password = $.cookie('password');

        	//将获取的值填充入输入框中
			$('#lg_num').val(username);
        	$('#lg_pass').val(password); 
        	if(username != null && username != '' && password != null && password != ''){//选中保存秘密的复选框
        		$("#rmUser").attr('checked',true);
        	}


            $("#lg_num").focus(function(){
                $("#lg_cross").css("display","block");
            });
            $("#lg_num").blur(function(){
                var str = $("#lg_num").val();
                if(str == ""){
                    $("#lg_cross").css("display","none");
                }
            });
            $("#lg_cross").click(function(){
                $("#lg_num").val("");
                $("#lg_num").focus();
            });
            $("#sub_login").click(function(){    

                //console.log("login....");  
                var num = $("#lg_num").val();
                var pass = $("#lg_pass").val();
                if( num == ""){
                   $(".lg_num_hint").text("*账号不能为空！");
                   $("#lg_num").focus();
                }else if(pass == ""){
                   $(".lg_pass_hint").text("*请输入密码！");
                   $("#lg_pass").focus();
                }
                else{
                    if($("#rmUser").is(':checked')){
                        $.cookie("rmbUser", "true", { expires: 7 }); //存储一个带7天期限的cookie
                 		$.cookie("username", num, { expires: 7 });
                		$.cookie("password", pass, { expires: 7 });
                     }else {
               			$.cookie("rmbUser", "false", { expire: -1 });
                		$.cookie("username", "", { expires: -1 });
                		$.cookie("password", "", { expires: -1 });
                	}

                    $.ajax({
                    	type: "POST",
                    	url : "<%=basePath%>login",
                     	data: {"userName":num, "pass":pass},
                     	dataType: "json",
                     	success: function(data){
                     		console.log(data);
                     		if(data.result == "success"){
                     			alert("登录成功！");
                     			var d = new Date();
                     			d.setTime(d.getTime()+(365*24*60*60*1000));
                     			var expires = "expires="+d.toGMTString();
                				document.cookie = "username=" + num + "; " + expires;
                     		    location.href = "File.jsp";
                     		}
                     		else if(data.result == "fail"){
                     			$(".lg_pass_hint").text("*密码输入错误，请重新输入！");
                   				$("#lg_pass").focus();
                     		}
                    		else{
                    			$(".lg_num_hint").text("*用户不存在！");
                   				$("#lg_num").focus();  
                    		}             		                     
                        }
                    });
                }
            });

            
            /* --------------------------------------------------------
             Checkbox + Radio
             -----------------------------------------------------------*/
            if($('input:checkbox, input:radio')[0]) {
            	
        	//Checkbox + Radio skin
        	$('input:checkbox:not([data-toggle="buttons"] input, .make-switch input), input:radio:not([data-toggle="buttons"] input)').iCheck({
        		    checkboxClass: 'icheckbox_minimal',
        		    radioClass: 'iradio_minimal',
        		    increaseArea: '20%' // optional
        	});
            
        	   
            }
            
            /* --------------------------------------------------------
                MAC Hack 
            -----------------------------------------------------------*/
            (function(){
        	//Mac only
                if(navigator.userAgent.indexOf('Mac') > 0) {
                    $('body').addClass('mac-os');
                }
            })();

          /* --------------------------------------------------------
        	retrieve
        	-----------------------------------------------------------*/
            //paging(page_size, list_amount);
        });
        </script>
</body>
</html>