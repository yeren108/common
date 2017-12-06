/**
 * 通用的ajax获取数据方法
 * 
 * @param param
 */
function ajaxData(param){
	 if(typeof param.before=='function'){
		 param.before();
	 }
	 $.ajax({
			type : param.type,
			url :  param.url,
	   		data: param.data,
	   		error:param.error,
		   	success:param.success
		});
}
 
/**
 * 判断字符串不为空
 * 
 * @param str
 */
function trim(str){ // 删除左右两端的空格
	return str.replace(/(^\s*)|(\s*$)/g, ""); 
} 

function isNotBank(str){
	if(str!=null && str!=undefined && str!="" && str!="undefined"){
		return true;
	}
    return false;
}
	 
/**
 * 错误回调函数
 */
function errorback(XMLHttpRequest, textStatus, errorThrown) {
	closeLoadPanle();
	msgBox({msg:"网络出现异常!</br>可能原因:</br>1.网络不稳定</br>2.尚未接入互联网</br>3.安全软件禁止访问网络",textAlign:"left"});
}

/**
 * 弹出消息框,居中显示
 * 
 * @param msg
 *            显示的消息 必填
 */
function msgBox(parameter){

	var msg=parameter.msg;
	var msgbox=$("<div class='msgBox' id='msgBox'>"+msg+"</div>");
	
	//计算当前屏幕的高宽
	var height =  $(window).innerHeight();
	var width= $(window).innerWidth();
	
 	msgbox.click(function(){
 		closeMsgBox("msgBox")
 	});
 	
 	//如果当前页面存在加载面板，删除
 	$(".msgBox").each(function(){
 		$(this).remove();
 	});
 	
 	//设置消息框的样式
 	msgbox.hide();
	$("body").append(msgbox);
 	msgbox.css("top",(height-msgbox.innerHeight())/2+"px");
 	msgbox.css("left",(width-msgbox.innerWidth())/2+"px");
 	msgbox.show();

	setTimeout("closeMsgBox('msgBox');",3000); // 毫秒
}

/**
 * 显示加载面板
 * @param id 加载面板所属容器id 必填
 * @param url loading图片地址 必填
 * @returns
 */
function showLoadPanle(id,url){
	if($("#loadingPanle").length==0){
		 var panle=$("<div class='loadingPanle' id='loadingPanle'>数据加载中 <img src='"+url+"' align='absmiddle'/></div>");
	     var a =  document.documentElement.clientHeight;
	     var b =  document.documentElement.clientWidth;
	     panle.css("top",(a/2));
	     panle.css("left",((b-150)/2));
	     $("#"+id).append(panle);f
	     //setTimeout(function(){
	    	// $("#loadingPanle").remove();
	     //},2000); 
	}
   
}

/**
 * 关闭加载面板
 */
function closeLoadPanle(){
	 $("#loadingPanle").remove();
}

/**
 * 消息关闭
 * 
 * @param id
 *            消息框id 必填
 */
function closeMsgBox(id){
	$("#"+id).remove();
}

/**
 * 文本缩略显示
 * @param txt 文本 必填
 * @param maxLength 最大长度 必填
 */
function ellipsisText(txt,maxLength){
	 var newTxt=txt;
	// console.log("原始长度:"+newTxt.length);
	 if(newTxt.length>maxLength){
		 newTxt=newTxt.substr(0,maxLength);
		 newTxt+="...";
	 }
	// console.log("截取后的文本:"+newTxt);
	 //alert("长度:"+txt.length+"\r\n文字:"+txt+"\r\n截取后:"+newTxt);
	 return newTxt;
}

/**
 * 判断图片地址是否正常
 * @param url
 */
function checkImageURL(url){
	var flag=false;
	if(isNotBank(url)){
		var suffixs=".gif,.png,.jpeg,.bmp,.jpg";
		var startIndex=url.lastIndexOf(".");
		var suffix=url.substr(startIndex);
		var arry=suffixs.split(",");
		for(var i=0;i<arry.length;i++){
		   if(arry[i]==suffix){
		     flag=true;
			 break;
		   }else{
		       flag=false;
		   }
		}
	}
	return flag;
}