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
          <div class="left" id="left">
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
                    <div class="item-left">
                      <div class="item-img" style="height: 160px"></div>
                    </div>
                    <div class="item-right">
                      <div class="title" style="width: 200px">{{ite.scenicespotName}}</div>
                      <div class="subtitle" style="width: 300px">游玩时长：{{ite.palytimeCost}}</div>
                      <div class="price" style="width: 400px">经度：{{ite.latitude}}；维度：{{ite.longitude}}</div>
                      <div class="price" style="width: 400px">经度：{{ite.latitude}}；维度：{{ite.longitude}}</div>
                    </div>
                  </div>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <div class="swiper-slide" style="width: 100%;height: 900px;display: none">
		<div class="content"  id="container">
			<!-- <div id="container"></div> -->
		</div>
      </div>
    </div>
  </div>

    <script src="./resources/js/jquery.min.js"></script>
    <script src="./resources/js/vue.min.js"></script>
  <script type="text/javascript">
  $(function(){
    $('.content').css('height',$('.right').height());
    $('.left ul li').eq(0).addClass('active');
    $(window).scroll(function(){
      if($(window).scrollTop() >= 225){
        $('.swiper-container-ul').css('position','fixed');
        $('.left').css('position','fixed');
        $('.right').css('margin-left',$('.left').width());
        $('#container').css('position','fixed');
        $('#container').css('top','40px');
      }else {
        $('.swiper-container-ul').css('position','');
        $('.left').css('position','');
        $('.right').css('margin-left','');
        $('#container').css('position','');
        $('#container').css('top','');
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
      $('body, html').animate({scrollTop:$('.right ul li').eq(i).offset().top-40},500);
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
    var returns ="";
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
    });

  });
  </script>
  <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=0223e7da2fb2c5dd318e8cc31d58b6ab"></script>
<script src="//webapi.amap.com/ui/1.0/main.js"></script>
<script type="text/javascript">
    //创建地图
    var map = new AMap.Map('container', {
        zoom: 4
    });

    AMapUI.load(['ui/misc/PathSimplifier', 'lib/$', 'lib/utils'], function(PathSimplifier, $, utils) {

        if (!PathSimplifier.supportCanvas) {
            alert('当前环境不支持 Canvas！');
            return;
        }

        var defaultRenderOptions = {
            pathNavigatorStyle: {
                initRotateDegree: 0,
                width: 10,
                height: 10,
                autoRotate: true,
                lineJoin: 'round',
                content: 'defaultPathNavigator',
                fillStyle: '#087EC4',
                strokeStyle: '#116394', //'#eeeeee',
                lineWidth: 1,
                pathLinePassedStyle: {
                    lineWidth: 2,
                    strokeStyle: 'rgba(8, 126, 196, 1)',
                    borderWidth: 1, 
                    borderStyle: '#eeeeee',
                    dirArrowStyle: false
                }
            }
        };

        var pathSimplifierIns = new PathSimplifier({
            zIndex: 100,
            map: map,
            getPath: function(pathData, pathIndex) {
                return pathData.path;
            },
            renderOptions: defaultRenderOptions
        });
        window.pathSimplifierIns = pathSimplifierIns;
        pathSimplifierIns.setData([{
            name: 'Test',
            path: PathSimplifier.getGeodesicPath([116.405289, 39.904987], [87.61792, 43.793308], 300)
        }]);
        pathSimplifierIns.setSelectedPathIndex(0);
        var customContainer = document.getElementById('my-gui-container');
        function createKeyNavigatorStyleGui(target) {
            var keyNavigatorStyleParams = utils.extend({}, defaultRenderOptions[target]);
            //形状类型
            keyNavigatorStyleGui.add(keyNavigatorStyleParams,
                'content', ['defaultPathNavigator', 'defaultArrow', 'plane_icon', 'circle', 'none']).onChange(render);
        }
    });
    </script>
</body>
</html>
