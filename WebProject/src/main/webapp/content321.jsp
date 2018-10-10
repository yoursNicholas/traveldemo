<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>旅游 &mdash;&mdash;一起探索未知旅程 </title>
    <link rel="stylesheet" type="text/css" href="./resources/css/stylecontent.css">
  </head>
<body>
  <div class="header" style="background-image: url('./resources/images/title.png');"></div>
  <div class="swiper-container">
    <ul class="swiper-container-ul">
      <li class="swiper-container-ul-li actives">具体行程</li>
      <li class="swiper-container-ul-li">地图行程</li>
    </ul>
    <div class="swiper-wrapper">
      <div class="swiper-slide">
        <div class="content">
          <div class="left" id="left" style="background-image: url('./resources/images/haiyang.png');">
            <ul>
              <li v-for="it in items">{{it.date}}</li>
            </ul>
          </div>
          <div class="right" id="right" >
            <ul>
              <li v-for="eachit in item2" >
                <div class="class-title">{{eachit.date}}</div>
                <div v-for="ite in eachit.routeList">
                  <div class="item">
                    <div class="item-left" style="width:100px">
						<div class="item-img" id="scenephoto" :style="{'background-image':'url('+ite.spotImagePath+')'}" style="height: 120px;width:100px "></div>
                        <div style = "text-align: center;height: 10px"><img src="./resources/images/arrow.png" style="height: 49px;margin-top:1px;"/></div>
                    </div>
					<div class="item-right">
                      <div class="title" style="width: 250px">{{ite.scenicespotName}}</div>
                      <div class="subtitle" style="width: 150px">游玩时长：{{ite.palytimeCost}}</div>
                      <div class="price" style="width: 300px">经度：{{ite.latitude}}；维度：{{ite.longitude}}</div>
                      <div style="margin-top: 60px;font-size:12px;color: #969696;">{{ite.routesTransport}}  {{ite.journeyTimeCost}}   {{ite.routesDistance}}</div>
                    </div>
                  </div>
                  <div>
                  
                  </div>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <div class="swiper-slide" style="width: 100%;height: 900px;display: none">
		<div class="content"  id="allmap" style="overflow: hidden;">
		</div>
      </div>
    </div>
  </div>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZkyTGui7w31dzjmCgslapjc2"></script>
    <script src="./resources/js/jquery.min.js"></script>
    <script src="./resources/js/vue.min.js"></script>
<!-- 		<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=0223e7da2fb2c5dd318e8cc31d58b6ab"></script>
	<script src="//webapi.amap.com/ui/1.0/main.js"></script>
	    <script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script> -->
	  <script type="text/javascript">
	  $(function(){
	    var returns ="";
	    var clickday = 0;
	    $.getJSON("<%=basePath%>getcontent" , function (result, status) {
	    	returns = result;
	  	  	var left = new Vue({
			    el: '#left',
			    data: {
			      items: result
			    }
			  });
			  var right = new Vue({
			    el: '#right',
			    data: {
			    	item2: result
			    },
			  });
			  $('.content').css('height',$('.right').height());
			    $('.left ul li').eq(0).addClass('active');
			    $(window).scroll(function(){
			      if($(window).scrollTop() >= 225){
			        $('.swiper-container-ul').css('position','fixed');
			        $('.left').css('position','fixed');
			        $('.right').css('margin-left',$('.left').width());
			        $('#allmap').css('position','fixed');
			        $('#allmap').css('top','40px');
			        $('#allmap').css('height','900px');
			      }else {
			        $('.swiper-container-ul').css('position','');
			        $('.left').css('position','');
			        $('.right').css('margin-left','');
			        $('#allmap').css('position','relative');
			        $('#allmap').css('top','0px');
			        $('#allmap').css('height','900px');
			      };
			      //滚动到标杆位置,左侧导航加active
			      $('.right ul li').each(function(){
			        var target = parseInt($(this).offset().top-$(window).scrollTop()-100);
			        var i = $(this).index();
			        if (target<=0) {
			          $('.left ul li').removeClass('active');
			          $('.left ul li').eq(i).addClass('active');
			        }
			      });
			    });
			    $('.left ul li').click(function(){
			      var i = $(this).index('.left ul li');
				  var clickday = i;
			      $('body, html').animate({scrollTop:$('.right ul li').eq(i).offset().top-40},900);
				    var js = result[clickday].mapjs;
					 // 百度地图API功能
				    var map = new BMap.Map("allmap");    // 创建Map实例
					eval(js);
			    });
			    $('.swiper-container-ul-li').click(function(){
			      var index = $(this).index();
			      if(index == 0){
			        $('.swiper-slide').eq(0).show();
			        $('.swiper-container-ul-li').eq(0).addClass('actives');
			        $('.swiper-slide').eq(1).hide();
			        $('.swiper-container-ul-li').eq(1).removeClass('actives');
			      }else {
			        $('.swiper-slide').eq(0).hide();
			        $('.swiper-container-ul-li').eq(0).removeClass('actives');
			        $('.swiper-slide').eq(1).show();
			        $('.swiper-container-ul-li').eq(1).addClass('actives');
			      }
			    });
			    var cnt = result.length;
			    var js = result[clickday].mapjs;
				 // 百度地图API功能
			    var map = new BMap.Map("allmap");    // 创建Map实例
				eval(js);
				
	    });
	  });
	  </script>
</body>
</html>
