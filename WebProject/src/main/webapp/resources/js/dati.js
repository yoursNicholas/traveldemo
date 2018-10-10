function TiMu() {
	var str = '';
	for(var i in data1) {
		str += "<div class='entrance-bottom-frame-line'>"+
					"<div class='entrance-bottom-frame-beijing'></div>"+
					"<div class='entrance-bottom-frame-line-id'>"+data1[i].id+"</div>"+
					"<div class='entrance-bottom-frame-line-title'>"+data1[i].title+"</div>"+
					"<pre class='pre_message'><code>"+data1[i].code+"</code></pre>";
		for(var j in data1[i].xuanxiang){ 
			if(j == 0) {
				xuanXiangId = "A";
			} else if(j == 1) {
				xuanXiangId = "B";
			} else if(j == 2) {
				xuanXiangId = "C";
			} else {
				xuanXiangId = "D";
			}
			str += "<div class='entrance-bottom-frame-line-button'>"+
					"<div class='entrance-bottom-frame-line-button-id'>"+xuanXiangId+"</div>" +
					"<div class='entrance-bottom-frame-line-button-frame'>"+data1[i].xuanxiang[j]+"</div>" +
					"</div>"
		}
		str +="</div>";
		document.querySelector(".entrance-bottom-frame").innerHTML = str;
	};
};

function addClass(obj, cls) {
	var obj_class = obj.className, //获取 class 内容.
		blank = (obj_class != '') ? ' ' : ''; //判断获取到的 class 是否为空, 如果不为空在前面加个'空格'.
	added = obj_class + blank + cls; //组合原来的 class 和需要添加的 class.
	obj.className = added; //替换原来的 class.
}

function removeClass(obj, cls) {
	var obj_class = ' ' + obj.className + ' '; //获取 class 内容, 并在首尾各加一个空格. ex) 'abc    bcd' -> ' abc    bcd '
	obj_class = obj_class.replace(/(\s+)/gi, ' '), //将多余的空字符替换成一个空格. ex) ' abc    bcd ' -> ' abc bcd '
		removed = obj_class.replace(' ' + cls + ' ', ' '); //在原来的 class 替换掉首尾加了空格的 class. ex) ' abc bcd ' -> 'bcd '
	removed = removed.replace(/(^\s+)|(\s+$)/g, ''); //去掉首尾空格. ex) 'bcd ' -> 'bcd'
	obj.className = removed; //替换原来的 class.
}

function hasClass(obj, cls) {
	var obj_class = obj.className, //获取 class 内容.
		obj_class_lst = obj_class.split(/\s+/); //通过split空字符将cls转换成数组.
	x = 0;
	for(x in obj_class_lst) {
		if(obj_class_lst[x] == cls) { //循环数组, 判断是否包含cls
			return true;
		}
	}
	return false;
}

function getStyle(obj, attr) {
	if(obj.currentStyle) {
		return obj.currentStyle[attr];
	} else {
		return document.defaultView.getComputedStyle(obj, null)[attr];
	}
}

window.onload = function() {
	TiMu()
	mintime = 1;
	var dact = document.querySelector(".entrance-bottom-frame-line")
	var active = "active"
	var none = "none"
	addClass(dact, active)
	var timu_id = 0
	for(var i = 0; i < document.querySelectorAll(".entrance-bottom-frame-line-button").length; i++) {
		document.querySelectorAll(".entrance-bottom-frame-line-button")[i].onclick = function() {
			if(timu_id < document.querySelectorAll(".entrance-bottom-frame-line").length - 1) {
				var frame_left = getStyle(document.querySelector(".entrance-bottom-frame"), 'marginLeft')
				document.querySelector(".entrance-bottom-frame").style.marginLeft = parseInt(frame_left) - 1050 + "px"
				timu_id++;
				addClass(document.querySelectorAll(".entrance-bottom-frame-line")[timu_id], active)
				removeClass(document.querySelectorAll(".entrance-bottom-frame-line")[timu_id - 1], active) 
				addClass(document.querySelectorAll(".entrance-bottom-frame-beijing")[timu_id - 1], none)
			} else { 
				/*document.querySelector(".entrance-bottom-frame").style.marginLeft='-30px';
				str="<div>恭喜答完题</div>";
				document.querySelector(".entrance-bottom-frame").innerHTML = str;*/
				alert("测试结束，请查看结果");
				setTimeout(function(){
                    window.location.href = "content.jsp"
                }, 100);
			}
		}
	}
}

var data1 = [{
	"id": "1",
	"title": "回答下面问题",
	"code": "夏天，如果有四种度假别墅，你最想住进哪一栋?",
	"xuanxiang": [
		"滨海别墅",
		"森林中的别墅",
		"草原别墅",
		"乡野别墅",
	]

}, {
	"id": "2",
	"title": "回答下面问题",
	"code": "你是怎样吃薯条的?",
	"xuanxiang": [
		"不沾酱，直接吃薯条",
		"将蕃茄酱挤在干净的容器上，然后用薯条沾着品尝",
		"将蕃茄酱沿线撕开，把薯条放入其中沾酱，然后品尝",
		"将蕃茄酱包开一个小口，把酱一点点的挤到薯条上，然后品尝",
	]
}, {
	"id": "3",
	"title": "回答下面问题",
	"code": "到理发店理发，你会如何与发型师沟通?",
	"xuanxiang": [
		"丢一堆杂志要他决定",
		"拿照片请他照着修剪",
		"任由理发师帮你设计",
		"口头说明大概要修剪的发型",
	]
}, {
	"id": "4",
	"title": "回答下面问题",
	"code": "如果有一天，可以让你看到地狱里的状况，那么你会想要看以下的哪一个部分呢?",
	"xuanxiang": [
		"受刑的过程",
		"投胎的过程",
		"阎罗王的审判过程",
		"地狱工作人员的休闲生活",
	]
}, {
	"id": "5",
	"title": "回答下面问题",
	"code": "目前的你认为相较五年前的你，最大的变化是",
	"xuanxiang": [
		"经济实力发生了改变",
		"相貌外表发生了改变",
		"思想境界发生了改变",
		"各方面能力发生了改变",
	]
}, {
	"id": "6",
	"title": "回答下面问题",
	"code": "吃牛排的时候，你的习惯是?",
	"xuanxiang": [
		"全部切完后，再一块一块吃",
		"从右端开始切，再一块一块吃",
		"由左端开始切，再一块一块吃",
		"从中间割一半，并从中间开始吃",
	]
}, {
	"id": "7",
	"title": "回答下面问题",
	"code": "下面哪种类型的小孩，你觉得未来最有可能取得事业上的成功?",
	"xuanxiang": [
		"很善于跟大人交流，又爱跟其他小孩玩的孩子",
		"喜欢玩智力游戏，学东西很快的孩子",
		"学习成绩非常好，非常专注的孩子",
		"有一两项特长，且表现很优秀，也很愿意坚持的孩子",
	]
}, {
	"id": "8",
	"title": "回答下面问题",
	"code": "在公园看到许多画家帮人画自画像，你心血来潮也想过过做模特儿的瘾，你会选择哪一类型的自画像？",
	"xuanxiang": [
		"水彩画或油画",
		"铅笔素描画",
		"俏皮逗趣的漫画",
		"毛笔水墨画",
	]
}, {
	"id": "9",
	"title": "回答下面问题",
	"code": "以下几种方便面你最喜欢哪种",
	"xuanxiang": [
		"鲜虾鱼板面",
		"红烧牛肉面",
		"香菇炖鸡面",
		"梅菜扣肉面",
	]
}, {
	"id": "10",
	"title": "回答下面问题",
	"code": "如果你的恋人告诉你，Ta不喜欢吃西瓜，你本能得觉得Ta的理由是什么呢？",
	"xuanxiang": [
		"觉得吃西瓜要吐子很麻烦",
		"切起来很麻烦",
		"不喜欢西瓜的味道",
		"西瓜水份很多一吃就觉得撑",
	]
}, {
	"id": "11",
	"title": "回答下面问题",
	"code": "情人节那天你与男(女)友在公园遇到了海洋动物义买.请你选择一个你喜欢的海洋动物",
	"xuanxiang": [
		"海星",
		"花斑鱼",
		"海龟",
		"海葵",
	]
}, {
	"id": "12",
	"title": "回答下面问题",
	"code": " 坐飞机时突然震动，你随着机身左右摇摆。这时您会怎样做呢",
	"xuanxiang": [
		"继续正在做的事情，不太在意骚乱",
		"注意事态的变化，并翻看紧急情况应付手册",
		"A和B都有一点",
		"不能确定--根本没注意到",
	]
}, {
	"id": "13",
	"title": "回答下面问题",
	"code": "一群4岁的孩子，其中一个孩子由于别人都不和他玩而大哭起来。您会怎么做呢",
	"xuanxiang": [
		"置身事外",
		"和这个孩子交谈，帮助她想办法",
		"温柔的告诉她不要哭",
		"给她一些玩具玩儿",
	]
}, {
	"id": "14",
	"title": "回答下面问题",
	"code": "你是大学生，想在某门课程上得优秀，却只得了及格。你会如何呢",
	"xuanxiang": [
		"制定详细的学习计划",
		"下决心以后好好学",
		"告诉自己没什么大不了，把精力集中在其他课程上",
		"试图让任课教授给您高一点的分数",
	]
}, {
	"id": "15",
	"title": "回答下面问题",
	"code": "您是保险推销员，去访问一些有希望成为您的顾客的人。前十五个人只是敷衍你，并不表态，您变得很失望。您会怎么做呢",
	"xuanxiang": [
		"这不过是今天的遭遇而已，明天会有好运气",
		"思考自己是否适合做推销员",
		"努力，保持勤勤恳恳工作的状态",
		"去争取其他的顾客",
	]
}];