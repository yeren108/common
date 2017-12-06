var objyan = document.getElementById("yan2");

//验证码重发
var yanTimeoutStart=function(){
	if (countdown <= 0) { 
		clearTimeout(yanTimeoutId);
		countdown = 5;
	    objyan.value="获取验证码"; 
	    objyan.removeAttribute("disabled");
	} else { 
		objyan.value="重发(" + countdown + ")"; 
	    countdown--;
	}
}
var countdown=60;
var yanTimeoutId;
var yanTimeout=function() {
	objyan.setAttribute("disabled", true);
	yanTimeoutId?clearTimeout(yanTimeoutId):false;
	yanTimeoutStart();
	yanTimeoutId=setInterval("yanTimeoutStart()" ,1000);
}



////////////////////////////////////////////////////////////////////////////


$(document).ready(function(){
	var sessionToken = localStorage.getItem("sessionToken");
	
	/*if(sessionToken!=null&&sessionToken!="null"){
		window.location.href="/common/pages/online.html";
	}*/
	
	$('#fastLogin').click(function(){
		document.getElementById("fast").style.display='block';
		document.getElementById("normal").style.display='none';   	 		
	});
	
	$('#normalLogin').click(function(){
		document.getElementById("fast").style.display='none';
		document.getElementById("normal").style.display='block';		
	});
	
	function removeButtonDisabled(id){
		$(id).removeAttr("disabled").css("backgroundColor","#39b8ef").val("登录");
	}
	
	function disabledButton(id){
		$(id).attr("disabled",true).css("backgroundColor","#CECECE").val("提交中....");
	}
	
	
	$("#BtnFast").attr("disabled",true).css("backgroundColor","#CECECE");
	$("#BtnNomal").attr("disabled",true).css("backgroundColor","#CECECE");
	$(function(){
		
		var regMobile=/^((13)|(14)|(15)|(18)|(17))\d{9}$/;
		var regCode=/^[0-9]+$/;
		var regUsername=/^[A-Za-z0-9]+$/;
		var regPassword=/^[\w\W]{7,}$/;
		
		$("#inputDivNormal").bind('input propertychange', function(){
			 var username = $("#username").val();
			 var password = $("#password").val();
			 if(regUsername.test(username)&&regPassword.test(password)){
				 removeButtonDisabled("#BtnNomal");
			 }else{
				 $("#BtnNomal").attr("disabled",true).css("backgroundColor","#CECECE").val("登录");
		     }
		});
		
		$("#inputDivFast").bind('input propertychange', function(){
			 var mobile = $("#mobile").val();
			 var yan1 = $("#yan1").val();
			 if(regMobile.test(mobile)&&regCode.test(yan1)){
				 removeButtonDisabled("#BtnFast");
			 }else{
				 $("#BtnFast").attr("disabled",true).css("backgroundColor","#CECECE").val("登录");
		     }
		});
 	});

	//发送验证吗
 	$('#yan2').bind("click",sendCode);
 	
 	
	//普通登录
    $('#BtnNomal').click(function(){
   	 disabledButton("#BtnNomal")
     var username = $("#username").val();
 	 var password = $("#password").val();
	 if(checkBindSubmit()){
		 $.ajax({
			url:"/common/user/login",
			type : "post",
			data:{
				"username":username,
				"password":password
			},
			success : function(data) {
				if (data.code=="200") {
					removeButtonDisabled("#BtnNomal");
					localStorage.setItem("sessionToken", data.data.sessionToken);
					localStorage.setItem("userid", data.data.userid);
					window.location.href="/common/pages/online.html";
				}else{
					removeButtonDisabled("#BtnNomal");
					var msgbox=new IOSMsgbox();
					msgbox.setTitle(data.msg);
					msgbox.addButton("确定");
					msgbox.alert();
				}
			},
			error : function() {
				 removeButtonDisabled("#BtnNomal");
			}
			
		});
     }else{
    	 removeButtonDisabled("#BtnNomal");
     }	 
		
    });
    
    
    ////////////////////////////////////////////////////////////////////////////////////////
    
    
    //快速登录
    $('#BtnFast').click(function(){
   	 disabledButton("#BtnFast");
     var mobile = $("#mobile").val();
 	 var code = $("#yan1").val();
 	 var check = checkFastSubmit();
     
	 if(check){
		 $.ajax({
			url:"/portal/account/fastLogin",
			type : "post",
			data:{
				"mobile":mobile,
				"code":code,
				"source":"h5",
			},
			success : function(data) {
				if (data.code="200") {
					removeButtonDisabled("#BtnNomal");
					localStorage.setItem("sessionToken", data.data.sessionToken);
					localStorage.setItem("id", data.data.id);
					window.location.href="/common/pages/online.html";
				}else{
					removeButtonDisabled("#BtnNomal");
					var msgbox=new IOSMsgbox();
					msgbox.setTitle(data.msg);
					msgbox.addButton("确定");
					msgbox.alert();
				}
			},
			error : function() {
				 removeButtonDisabled("#BtnNomal");
			}
			
		});
     }else{
    	 removeButtonDisabled("#BtnNomal");
     }	 
   	 	
		
    });
});


////////////////////////////////////////////////////////////////////////////////////////////


function checkBindSubmit() { 
	if ($("#username").val() == "") { 
		msgBox({msg:"用户名不能为空！"});
		return false; 
	} 
	
	if ((!$("#username").val().match((/^[a-zA-Z]\w{5,39}$/)))) { 
		 msgBox({msg:"用户名格式不正确！"});
		 return false; 
	}
	
	if ($("#password").val() == "") { 
		msgBox({msg:"密码不能为空！"});
		return false; 
	} 
	
	if (!$("#password").val().match((/^[\w\W]{7,}$/))) { 
		msgBox({msg:"密码长度大于6位"});
		return false; 
	} 
	
	return true;
}


function checkFastSubmit() { 
	if ($("#mobile").val() == "") { 
		msgBox({msg:"手机号不能为空！"});
		return false; 
	} 
	
	if(!$("#mobile").val().match((/^((13)|(14)|(15)|(18)|(17))\d{9}$/))){
		 msgBox({msg:"手机号格式不正确！"});
		 return false; 
	}
	
	if ($("#yan1").val() == "") { 
		msgBox({msg:"验证码不能为空！"});
		return false; 
	} 
	return true;
}







function checkMobile(){
	if ($("#mobile").val() == "") { 
		msgBox({msg:"手机号不能为空！"});
		return false; 
	} 
	
	if(!$("#mobile").val().match((/^((13)|(14)|(15)|(18)|(17))\d{9}$/))){
		 msgBox({msg:"手机号格式不正确！"});
		 return false; 
	}
	
	return true;
}


function sendCode(){
	var check = checkMobile();
	if(check){
		var username = $("#mobile").val();
		$.ajax({
			url:"/portal/basic/code/send",
			type : "post",
			data:{
				"username":username,
				"type":"1"
			},
			success : function(data) {
				if(data.code="200"){
					
				}else {
					var msgbox=new IOSMsgbox();
					msgbox.addTitle(data.msg);
					msgbox.addButton("确定");
					msgbox.alert();
				}
			},
			error : function() {
			}
		});
		yanTimeout();
	}
}


function checkForm(){
	$("#BtnFast").attr("disabled",true).css("backgroundColor","#CECECE");
	$("#BtnNomal").removeAttr("disabled").css("backgroundColor","#39b8ef");
}



