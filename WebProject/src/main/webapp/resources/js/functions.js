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
	Time
	-----------------------------------------------------------*/
	(function(){

	    setInterval( function() {

	        // Create a newDate() object and extract the seconds of the current time on the visitor's
	        var seconds = new Date().getSeconds();

	        // Add a leading zero to seconds value
	        $("#sec").html(( seconds < 10 ? "0":"" ) + seconds);
	    },1000);

	    setInterval( function() {

	        // Create a newDate() object and extract the minutes of the current time on the visitor's
	        var minutes = new Date().getMinutes();

	        // Add a leading zero to the minutes value
	        $("#min").html(( minutes < 10 ? "0":"" ) + minutes);
	    },1000);

	    setInterval( function() {

	        // Create a newDate() object and extract the hours of the current time on the visitor's
	        var hours = new Date().getHours();

	        // Add a leading zero to the hours value
	        $("#hours").html(( hours < 10 ? "0" : "" ) + hours);
	    }, 1000);
	    
	})();

    /* --------------------------------------------------------
     Login + Sign up
    -----------------------------------------------------------*/
    /*$("#lg_num").focus(function(){
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
            alert("login...");
        }
    });*/
    
    $("#exit").click(function(){
        var statu = confirm("确认退出？");
        if( statu == false){
            return false;
        }else{
            location.href = "login.html"; 
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

        //Checkbox listing
        var parentCheck = $('.list-parent-check');
        var listCheck = $('.list-check');
        
        parentCheck.on('ifChecked', function(){
            $(this).closest('.list-container').find('.list-check').iCheck('check');
        });
        
        parentCheck.on('ifClicked', function(){
            $(this).closest('.list-container').find('.list-check').iCheck('uncheck');
        });
        
        listCheck.on('ifChecked', function(){
                var parent = $(this).closest('.list-container').find('.list-parent-check');
                var thisCheck = $(this).closest('.list-container').find('.list-check');
                var thisChecked = $(this).closest('.list-container').find('.list-check:checked');
            
                if(thisCheck.length == thisChecked.length) {
                parent.iCheck('check');
                }
        });
        
        listCheck.on('ifUnchecked', function(){
                var parent = $(this).closest('.list-container').find('.list-parent-check');
                parent.iCheck('uncheck');
        });
        
        listCheck.on('ifChanged', function(){
                var thisChecked = $(this).closest('.list-container').find('.list-check:checked');
                var showon = $(this).closest('.list-container').find('.show-on');
                if(thisChecked.length > 0 ) {
                showon.show();
                }
                else {
                showon.hide();
                }
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
    Calendar
    -----------------------------------------------------------*/
    (function(){
    
        //Sidebar
        if ($('#sidebar-calendar')[0]) {
            var date = new Date();
            var d = date.getDate();
            var m = date.getMonth();
            var y = date.getFullYear();
            $('#sidebar-calendar').fullCalendar({
                editable: false,
                events: [],
                header: {
                    left: 'title'
                }
            });
        }

        //Content widget
        if ($('#calendar-widget')[0]) {
            $('#calendar-widget').fullCalendar({
                header: {
                    left: 'title',
                    right: 'prev, next',
                    //right: 'month,basicWeek,basicDay'
                },
                editable: true,
                events: [
                    {
                        title: 'All Day Event',
                        start: new Date(y, m, 1)
                    },
                    {
                        title: 'Long Event',
                        start: new Date(y, m, d-5),
                        end: new Date(y, m, d-2)
                    },
                    {
                        title: 'Repeat Event',
                        start: new Date(y, m, 3),
                        allDay: false
                    },
                    {
                        title: 'Repeat Event',
                        start: new Date(y, m, 4),
                        allDay: false
                    }
                ]
            });
        }

    })();
    
});

function checksum(chars)
{
    var sum = 0; 
    for (var i=0; i<chars.length; i++)
    { 
        var c = chars.charCodeAt(i); 
        if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f))
        { 
            sum++; 
        }
        else 
        {     
            sum+=2; 
        } 
    }
    return sum;
}




