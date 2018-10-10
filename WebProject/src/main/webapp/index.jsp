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
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<title>旅游 &mdash;&mdash;一起探索未知旅程 </title>
		<meta name="description" content="Free Bootstrap 4 Theme by ProBootstrap.com">
		<meta name="keywords" content="free website templates, free bootstrap themes, free template, free bootstrap, free website template">
    
    


		<link rel="stylesheet" href="./resources/css/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="./resources/css/animate.css">
    <link rel="stylesheet" href="./resources/fonts/ionicons/css/ionicons.min.css">
    
    <link rel="stylesheet" href="./resources/css/owl.carousel.min.css">
    
    <link rel="stylesheet" href="./resources/fonts/flaticon/font/flaticon.css">

    <link rel="stylesheet" href="./resources/fonts/fontawesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="./resources/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="./resources/css/select2.css">
    

    <link rel="stylesheet" href="./resources/css/helpers.css">
    <link rel="stylesheet" href="./resources/css/style.css">

	</head>
	<body>
    

    <section class="probootstrap-cover overflow-hidden relative"  style="background-image: url('./resources/images/bg_1.jpg');" data-stellar-background-ratio="0.5"  id="section-home">
      <div class="overlay"></div>
      <div class="container">
        <div class="row align-items-center">
          <div class="col-md">
            <h2 class="heading mb-2 display-4 font-light probootstrap-animate">一起探索未知旅程</h2>
            <p class="lead mb-5 probootstrap-animate">
              

            </p>
              <a href="" role="button" class="btn btn-primary p-3 mr-3 pl-5 pr-5 text-uppercase d-lg-inline d-md-inline d-sm-block d-block mb-3" id="target_start">开始探索</a>
            </p>
          </div> 
          <div class="col-md probootstrap-animate">
            <form action="#" class="probootstrap-form">
              <div class="form-group">
                <div class="row mb-3">
                  <div class="col-md">
                    <div class="form-group">
                      <label for="id_label_single">起始地</label>

                      <label for="id_label_single" style="width: 100%;">
                        <select class="js-example-basic-single js-states form-control" id="startpoint" style="width: 100%;">
                          <option value="Beijing" selected="selected">北京</option>
                          <option value="Shanghai">上海</option>
                          <option value="Guangzhou">广州</option>
                          <option value="Shenzhen">深圳</option>
                          <option value="Lijiang">丽江</option>
                          <option value="Sanyang">三亚</option>
                          <option value="Dali">大理</option>
                          <option value="Kunming">昆明</option>
                          <option value="xiamen">厦门</option>
                          <option value="Guilin">桂林</option>
                          <option value="Qingdao">青岛</option>
                        </select>
                      </label>


                    </div>
                  </div>
                  <div class="col-md">
                    <div class="form-group">
                      <label for="id_label_single2">目的地</label>
                      <div class="probootstrap_select-wrap">
                        <label for="id_label_single2" style="width: 100%;">
                        <select class="js-example-basic-single js-states form-control" id="destination" style="width: 100%;">
                          <option value="Beijing">北京</option>
                          <option value="Shanghai" selected="selected">上海</option>
                          <option value="Guangzhou">广州</option>
                          <option value="Shenzhen">深圳</option>
                          <option value="Lijiang">丽江</option>
                          <option value="Sanyang">三亚</option>
                          <option value="Dali">大理</option>
                          <option value="Kunming">昆明</option>
                          <option value="xiamen">厦门</option>
                          <option value="Guilin">桂林</option>
                          <option value="Qingdao">青岛</option>
                        </select>
                      </label>
                      </div>
                    </div>
                  </div>
                </div>
                                <div class="row mb-3">
                  <div class="col-md">
                    <div class="form-group">
                      <label for="id_label_single">个人偏好</label>

                      <label for="id_label_single" style="width: 100%;">
                        <select class="js-example-basic-single js-states form-control" id="preference" style="width: 100%;">
                          <option value="1" selected="selected">文化古迹</option>
                          <option value="2">休闲娱乐</option>
                          <option value="3">自然风光</option>
                        </select>
                      </label>


                    </div>
                  </div>
                  <div class="col-md">
                    <div class="form-group">
                      <label for="id_label_single2">游玩规划</label>
                      <div class="probootstrap_select-wrap">
                        <label for="id_label_single2" style="width: 100%;">
                        <select class="js-example-basic-single js-states form-control" id="playingrule" style="width: 100%;">
                          <option value="1">宽松安排</option>
                          <option value="2" selected="selected">适中安排</option>
                          <option value="3">紧凑安排</option>
                        </select>
                      </label>
                      </div>
                    </div>
                  </div>
                </div>
                <!-- END row -->
                <div class="row mb-5">
                  <div class="col-md">
                    <div class="form-group">
                      <label for="probootstrap-date-departure">起始日期</label>
                      <div class="probootstrap-date-wrap">
                        <span class="icon ion-calendar"></span>
                        <input type="text" id="probootstrap-date-departure" class="form-control" placeholder="">
                      </div>
                    </div>
                  </div>
                  <div class="col-md">
                    <div class="form-group">
                      <label for="probootstrap-date-arrival">返回日期</label>
                      <div class="probootstrap-date-wrap">
                        <span class="icon ion-calendar"></span> 
                        <input type="text" id="probootstrap-date-arrival" class="form-control" placeholder="">
                      </div>
                    </div>
                  </div>
                </div>
                <!-- END row -->

              </div>
            </form>
          </div>
        </div>
      </div>
    
    </section>
    <!-- END section -->
    

  



    <section class="probootstrap_section">
      <div class="container">
        <div class="row text-center mb-5 probootstrap-animate">
          <div class="col-md-12">
            <h2 class="display-4 border-bottom probootstrap-section-heading">热门景点</h2>
          </div>
        </div>
        
        <div class="row probootstrap-animate">
          <div class="col-md-12">
            <div class="owl-carousel js-owl-carousel-2">
              <div>
                <div class="media probootstrap-media d-block align-items-stretch mb-4 probootstrap-animate">
                  <img src="./resources/images/sq_img_2.jpg" alt="Free Template by ProBootstrap" class="img-fluid">
                  <div class="media-body">
                    <h5 class="mb-3">佛罗伦萨</h5>
                    <p>佛罗伦萨是意大利中部的一个城市，托斯卡纳区首府，意大利语原意为“花之都”。佛罗伦萨是意大利文艺复兴的摇篮，是意大利和世界上最美丽的城市之一。 </p>
                  </div>
                </div>
              </div>
              <!-- END slide item -->

              <div>
                <div class="media probootstrap-media d-block align-items-stretch mb-4 probootstrap-animate">
                  <img src="./resources/images/lijiang.jpg" alt="Free Template by ProBootstrap" class="img-fluid">
                  <div class="media-body">
                    <h5 class="mb-3">丽江</h5>
                    <p>我们来丽江，遇见自己，遗忘过往，漫步古城感受闲适光阴里的一花一木，让午后的一米阳光一下子照进心中最柔软的地方。 </p>
                  </div>
                </div>
              </div>
              <!-- END slide item -->

              <div>
                <div class="media probootstrap-media d-block align-items-stretch mb-4 probootstrap-animate">
                  <img src="./resources/images/sq_img_3.jpg" alt="Free Template by ProBootstrap" class="img-fluid">
                  <div class="media-body">
                    <h5 class="mb-3">悉尼</h5>
                    <p>澳大利亚的发源地悉尼是如今大洋洲最大的城市。这里有绝佳的水景可供欣赏，只需短短的车程就可以从城市的喧嚣中逃脱，海滩及森林使每个人保持清醒。</p>
                  </div>
                </div>
              </div>
              <!-- END slide item -->

              <div>
                <div class="media probootstrap-media d-block align-items-stretch mb-4 probootstrap-animate">
                  <img src="./resources/images/sq_img_4.jpg" alt="Free Template by ProBootstrap" class="img-fluid">
                  <div class="media-body">
                    <h5 class="mb-3">京都</h5>
                    <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                  </div>
                </div>
              </div>
              <!-- END slide item -->

              <div>
                <div class="media probootstrap-media d-block align-items-stretch mb-4 probootstrap-animate">
                  <img src="./resources/images/sq_img_5.jpg" alt="Free Template by ProBootstrap" class="img-fluid">
                  <div class="media-body">
                    <h5 class="mb-3">02. Service Title Here</h5>
                    <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                  </div>
                </div>
              </div>
              <!-- END slide item -->


              <div>
                <div class="media probootstrap-media d-block align-items-stretch mb-4 probootstrap-animate">
                  <img src="./resources/images/sq_img_2.jpg" alt="Free Template by ProBootstrap" class="img-fluid">
                  <div class="media-body">
                    <h5 class="mb-3">02. Service Title Here</h5>
                    <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                  </div>
                </div>
              </div>
              <!-- END slide item -->

              <div>
                <div class="media probootstrap-media d-block align-items-stretch mb-4 probootstrap-animate">
                  <img src="./resources/images/sq_img_1.jpg" alt="Free Template by ProBootstrap" class="img-fluid">
                  <div class="media-body">
                    <h5 class="mb-3">02. Service Title Here</h5>
                    <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                  </div>
                </div>
              </div>
              <!-- END slide item -->

              <div>
                <div class="media probootstrap-media d-block align-items-stretch mb-4 probootstrap-animate">
                  <img src="./resources/images/sq_img_3.jpg" alt="Free Template by ProBootstrap" class="img-fluid">
                  <div class="media-body">
                    <h5 class="mb-3">02. Service Title Here</h5>
                    <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                  </div>
                </div>
              </div>
              <!-- END slide item -->

              <div>
                <div class="media probootstrap-media d-block align-items-stretch mb-4 probootstrap-animate">
                  <img src="./resources/images/sq_img_4.jpg" alt="Free Template by ProBootstrap" class="img-fluid">
                  <div class="media-body">
                    <h5 class="mb-3">02. Service Title Here</h5>
                    <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                  </div>
                </div>
              </div>
              <!-- END slide item -->

              <div>
                <div class="media probootstrap-media d-block align-items-stretch mb-4 probootstrap-animate">
                  <img src="./resources/images/sq_img_5.jpg" alt="Free Template by ProBootstrap" class="img-fluid">
                  <div class="media-body">
                    <h5 class="mb-3">02. Service Title Here</h5>
                    <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                  </div>
                </div>
              </div>
              <!-- END slide item -->
              
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- END section -->

    
    <script src="./resources/js/jquery.min.js"></script>
    
    <script src="./resources/js/popper.min.js"></script>
    <script src="./resources/js/bootstrap.min.js"></script>
    <script src="./resources/js/owl.carousel.min.js"></script>

    <script src="./resources/js/bootstrap-datepicker.js"></script>
    <script src="./resources/js/jquery.waypoints.min.js"></script>
    <script src="./resources/js/jquery.easing.1.3.js"></script>

    <script src="./resources/js/select2.min.js"></script>

    <script src="./resources/js/main.js"></script>

    <script type="text/javascript">
        
    $(document).ready(function(){
          $("#target_start").click(function(){
              var startpoint = $("#startpoint").val();

              var destination = $("#destination").val();
              var preference = $("#preference").val();
              var playingrule = $("#playingrule").val();
              var datedeparture = $("#probootstrap-date-departure").val();
              var datearrival = $("#probootstrap-date-arrival").val();
              console.log(startpoint);
              if (datedeparture=="")
              {
                  alert('请选择出发时间 :)');  
                  $("#probootstrap-date-departure").focus();
                  return false;
              }else if (datearrival=="")
              {  
                  alert('请选择到达时间 :)');  
                  $("#probootstrap-date-arrival").focus();
                  return false;
              }else{
                  $.ajax({
                  type: "POST",
                  url : "<%=basePath%>getdata",
                  data: {"startpoint":startpoint,
                    "destination":destination,
                    "preference":preference,
                    "playingrule":playingrule,
                    "datedeparture":datedeparture,
                    "datearrival":datearrival
                    },
                  dataType: "json",
                  success: function(data){
                  /* var success = data.success; */
                  setTimeout(function(){
                      window.location.href = "dati.html"
                  }, 100);
                  /* console.log(success); */
                  }
                });
                return false;
              }
          });
        });

    </script>
	</body>
</html>