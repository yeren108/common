
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
	 //提交按钮,所有验证通过方可提交
    $("#register").click(function(){   		    		    	
		var flag = checkSubmitRegister();
		var username = $("#username").val();
		$.ajax({
			url:"/common-v1.0/user/exist",
			type : "get",
			data:{
				"username":username
			},
			success : function(data) {
				if (data.code=="200") {
					if(flag){
				    	disabledButton("#register");
				    	var username= $("#username").val();
				    	var mobile = $("#mobile").val();
				    	var password = $("#password").val();
				    	$.ajax({
							url:"/common-v1.0/user/register",
							type : "post",
							data:{
								"username":username,
								"mobile":mobile,
								"password":password
							},
							success : function(data) {
								if (data.code=="200") {
									removeButtonDisabled("#register");
									localStorage.setItem("sessionToken", data.data.sessionToken);
									localStorage.setItem("id", data.data.id);
										window.location.href="/common-v1.0/pages/regSuccess.html";
								}else{
									removeButtonDisabled("#register");
									var msgbox=new IOSMsgbox();
									msgbox.setTitle(data.msg);
									msgbox.addButton("确定");
									msgbox.alert();
								}
							},
							error : function() {
								removeButtonDisabled("#register");
							}
						
				    	}); 	
					 }	
				}else{
					var msgbox=new IOSMsgbox();
					msgbox.addTitle("该帐号已经注册!");
					msgbox.addButton("确定");
					msgbox.alert();
				}
			},
			error : function() {
				
			}
		});
		
    });
    
	//显示密码
	$('#password').togglePassword({
		el: '#pwdShow'
	});
	
	
	
	$("#register").attr("disabled",true).css("backgroundColor","#CECECE");
	$(function(){
		var regMobile=/^((13)|(14)|(15)|(18)|(17))\d{9}$/;
		var regPassword=/^[\w\W]{7,}$/;
		
		$("#inputDivRegister").bind('input propertychange', function(){
			 var username=$("#username").val();
			 var mobile = $("#mobile").val();
			 var password = $("#password").val();
			 if(regPassword.test(password)){
				 removeButtonDisabled("#register");
			 }else{
				 $("#register").attr("disabled",true).css("backgroundColor","#CECECE").val("注册");
		     }
		});
 	});
});


    

function requestUserExsit(){
	var username = $("#username").val();
	$.ajax({
		url:"/common-v1.0/user/exist",
		type : "get",
		data:{
			"username":username
		},
		success : function(data) {
			if (data.code=="400") {
				return true;
			}else{
				var msgbox=new IOSMsgbox();
				msgbox.addTitle("该帐号已经注册!");
				msgbox.addButton("确定");
				msgbox.alert();
			}
		},
		error : function() {
			
		}
	});
}



//禁止按钮功能，并灰显
function disabledButton(id){
	$(id).attr("disabled",true).css("backgroundColor","#CECECE").val("提交中....");
}

function removeButtonDisabled(id){
	$(id).removeAttr("disabled").css("backgroundColor","#39b8ef").val("注册");
}

//注册页面逻辑
function checkSubmitRegister() {
	if ($("#username").val() == "") { 
		msgBox({msg:"用户名不能为空！"});
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


//发送验证吗
/*$('#yan2').bind("click",sendCode);*/

function sendCode(){
	var check = checkMobile();
	if(check){
		var mobile = $("#mobile").val();
		$.ajax({
			url:"/portal/basic/code/send",
			type : "post",
			data:{
				"account":mobile,
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