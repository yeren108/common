var sessionToken = localStorage.getItem("sessionToken");
var userid = localStorage.getItem("userid");

function removeButtonDisabled(id){
	$(id).removeAttr("disabled").css("backgroundColor","#ff0000").val("退出登录");
}

function disabledButton(id){
	$(id).attr("disabled",true).css("backgroundColor","#CECECE").val("提交中....");
}


$(document).ready(function(){	
	$("#touch").click(function(){
		/*document.getElementById("firstBlock").style.display='none';
		document.getElementById("secondBlock").style.display='block';
		document.getElementById("thirdBlock").style.display='block';*/
	});
	
	$("#loginOut").click(function(){
		disabledButton("#loginOut")
		$.ajax({
			url:"/portal/account/logout",
			type:"get",
			data:{
				"source":"h5",
				"userid":userid
			},
			success:function(data){
				if ("200"==data.code) {
					removeButtonDisabled("#loginOut");
					localStorage.setItem("sessionToken", null);
					window.location.href="login.html";
				}else{
					var msgbox=new IOSMsgbox();
					msgbox.setTitle(data.msg);
					msgbox.addButton("确定");
					msgbox.alert();
				}
			}
		});
	});
});

function getSession(){
	if(sessionToken!=null&&sessionToken!="null"){				
		$.ajax({
			url:"/portal/account/info",
			type : "get",
			data:{
				"userid":userid,
				"sessionToken":sessionToken
			},
			success : function(data) {
				if ("200"==data.code) {
					var mobile = data.data.mobile;
					var username = data.data.username; 
					var userid = data.data.userid;
					var email = data.data.email;
					var pic = data.data.pic;
					var realValid=data.data.realValid;
					///////////////////////////////
					if(username==null||username==""||username=="null"){
						if(email==null||email==""||email=="null"){
							$("#username").text(function(){return mobile;});
							$("#realValidate").text(function(){return realValid==0?"未认证":"已认证";});
							$("#email").text(function(){return email==null?"暂无":emial;});
							$("#mobile").text(function(){return mobile==null?"暂无":mobile;});
							$("#userid").text(function(){return userid;});
							$("#uName").text(function(){return username==null?"暂无":username;});
							$("#rValidate").text(function(){return realValid==0?"未认证":"已认证";});
						}else {
							$("#username").text(function(){return email;});
							$("#realValidate").text(function(){return realValid==0?"未认证":"已认证";});
							$("#email").text(function(){return email==null?"暂无":emial;});
							$("#mobile").text(function(){return mobile==null?"暂无":mobile;});
							$("#userid").text(function(){return userid;});
							$("#uName").text(function(){return username==null?"暂无":username;});
							$("#rValidate").text(function(){return realValid==0?"未认证":"已认证";});
						}
					}else {
						$("#username").text(function(){return username;});
						$("#realValidate").text(function(){return realValid==0?"未认证":"已认证";});
						$("#email").text(function(){return email==null?"暂无":emial;});
						$("#mobile").text(function(){return mobile==null?"暂无":mobile;});
						$("#userid").text(function(){return userid;});
						$("#uName").text(function(){return username==null?"暂无":username;});
						$("#rValidate").text(function(){return realValid==0?"未认证":"已认证";});
					}
				}else{
					var msgbox=new IOSMsgbox();
					msgbox.setTitle(data.msg);
					msgbox.addButton("确定");
					msgbox.alert();
				}
			 }
		  });
	}else{
		window.location.href="login.html";
	}
	
}  





