/*
解决IOSMsgbox对象title和btns变量污染
完善功能 可以在任何地方定义 并在dom树加载后调用alert方法
添加弹框位置和宽度自适应
解决手机旋转时resize事件频繁加载动画
弹框使用css3动画 添加缩放动画
移除背景启动动画
移除弹框关闭的动画
*/
var IOSMsgbox=function(title){
	var proto=IOSMsgbox.prototype;
	var that=this;
	that.title=(title==undefined?"":title);
	that.btns=[];

	var instanceBackground=function(){
		return $("<div>").addClass("imsgbox-background").hide();
	};

	proto.setTitle=function(title){
		that.title=title+"<br/>";
		return that;
	}

	proto.addTitle=function(title){
		that.title+=title+"<br/>";
		return that;
	}

	var instanceTitleRow=function(){
		return $("<tr>").append(
			$("<td>").attr({
				colspan:that.btns.length,
				colspan:that.btns.length})
			.addClass("imsgbox-inner imsgbox-title")
			.html(that.title));
	};

	var instanceMiddleRow=function(){
		return $("<tr>").append(
			$("<td>").attr({
				colspan:that.btns.length})
			.css("border-top","1px solid #ddd"))
	};

	var instanceBtnCell=function(value){
		return $("<td>").text(value)
		.addClass("imsgbox-inner imsgbox-button")
		.bind("mousedown touchstart",function(){
			return $(this).addClass("imsgbox-button-hover");
		});
	};

	var instanceBtnsRow=function(){
		var row=$("<tr>");
		$.each(that.btns,function(index,btn){
			btn.css("width",100/that.btns.length+"%");
			index==0?false:btn.css("border-left","1px solid #ddd");
			row.append(btn);
		})
		return row;
	};

	var instanceMsgbox=function(){
		return $("<table>").attr({
			cellspacing:"0px",
			cellpadding:"0px"
		}).addClass("imsgbox imsgbox-hide")
		.append(instanceTitleRow())
		.append(instanceMiddleRow())
		.append(instanceBtnsRow())

	};

	proto.addButton=function(value,fn){
		that.btns.push(
			instanceBtnCell()
			.text(value?value:"button")
			.bind("click",function(){
				removeAll(fn);
			}));
		return that;
	};

	var bindClickEvent=function(block){
		return that.btns.length>0?block:block.bind("click",function(){
			removeAll();
			return $(this);
		});
	};

	var setHalfTop=function(block){
		var half= ($(window).innerHeight()-block.innerHeight())/2;
		block.css("top",half+"px");
	};

	var showAll=function(){
		var back=bindClickEvent(instanceBackground());
		var msgbox=bindClickEvent(instanceMsgbox());
		var body=$("body");
		body.append(back);
		body.append(msgbox);
		setHalfTop(msgbox);
		back.fadeIn(100,function(){
			msgbox.addClass("imsgbox-transition imsgbox-show");
		});
	};

	that.alert=function(){
		setTimeout(showAll,200);
		$(window).onseries("resize",false,false,function(){
			var msgbox=$(".imsgbox");
			msgbox?setHalfTop(msgbox):false;
		},500);
	};

	var removeAll=function(fn){
		$(".imsgbox-button").removeClass("imsgbox-button-hover");
		$(".imsgbox").removeClass("imsgbox-show");
		$(".imsgbox-background").fadeOut(300,function(){
			$(".imsgbox").detach();
			$(".imsgbox-background").detach();
			fn?fn():false;
		})
	};

	return that;
}