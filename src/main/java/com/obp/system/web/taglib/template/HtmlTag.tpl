#if(${doctypeEnable} == "true")
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
#end
<html>
<head>
<title>${title}</title>
<meta http-equiv="keywords" content="OBP,OBP平台">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="description" content="It's Based OBP！copyRight(c) OBPLab">
<meta http-equiv="pragma" content="no-cache">  
<meta http-equiv="cache-control" content="no-cache, must-revalidate">
<link rel="shortcut icon" href="${contextPath}/resource/image/${titleIcon}" />  
<link rel="stylesheet" type="text/css" href="${contextPath}/resource/ext-4.2/resources/css/ext-all-neptune.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/ext_icon.css"/>
<!--<link rel="stylesheet" type="text/css" href="${contextPath}/resource/theme/${theme}/resources/css/ext-all.css"/>-->
<script type="text/javascript" src="${contextPath}/resource/ext-4.2/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/ext_css_patch.css" />
<script type="text/javascript" src="${contextPath}/resource/ext-4.2/locale/ext-lang-zh_CN.js"></script>

<script type="text/javascript">
  var webContext = '${contextPath}';
  var runMode = '${runMode}';
  var ajaxErrCode = '${ajaxErrCode}';
  var micolor = 'color:${micolor};';
  Ext.QuickTips.init();
  Ext.form.Field.prototype.msgTarget = 'qtip';
  Ext.BLANK_IMAGE_URL = '${contextPath}/resource/image/ext/s.gif';
  
  Ext.Ajax.on('requestexception', function(conn, response, options) {
		if (response.status == ajaxErrCode) {
			setTimeout(function(){
				//延时避免被failure回调函数中的aler覆盖
				top.Ext.MessageBox.alert('提示', '由于长时间未操作,空闲会话已超时;您将被强制重定向到登录页面!', function() {
					parent.location.href = webContext + '/login.do?reqCode=init';
				});
			},200);
		}else if (response.status == '998') {
			setTimeout(function(){
				//延时避免被failure回调函数中的aler覆盖
				top.Ext.MessageBox.alert('提示', '您的会话连接由于在其它窗口上被注销而失效,系统将把您强制重定向到登录界面.', function() {
					parent.location.href = webContext + '/login.do?reqCode=init';
				});
			},200);
		}else if (response.status == -1) {
			setTimeout(function(){
				//延时避免被failure回调函数中的aler覆盖
				top.Ext.MessageBox.alert('提示', '请求失败,超时或服务器无响应.', function() {
				});
			},200);		
		}else{
		     parent.showException(response.responseText);
		}
  });
  
#if(${exportParams} == "true")
/* 全局参数表 */
#foreach($param in $paramList)var ${param.PARAM_KEY}='${param.PARAM_VALUE}';#end
#end

#if(${exportUserinfo} == "true")
/* 当前登录用户信息*/
var userid = '${userInfo.getUserid()}';var username = '${userInfo.getUsername()}';var account = '${userInfo.getAccount()}';
var deptid = '${userInfo.getDeptid()}';var customId = '${userInfo.getCustomId().trim()}';var theme = '${userInfo.getTheme()}';
var explorer = '${userInfo.getExplorer()}';
#end

#if(${isSubPage} == "true")
#if(${urlSecurity}=="1" && ${urlSecurity2}=="true")
window.onload=function(){
  if(parent.userid != '${userInfo.getUserid()}'){
     top.Ext.MessageBox.alert('提示', '这是一个非法请求或者您的会话连接由于在其它窗口上被注销而失效,系统将把您强制重定向到登录界面.', function() {
		parent.window.location.href = webContext + '/login.do?reqCode=init';
	 });
  }
};
#end
Ext.Ajax.on('beforerequest', function(conn, opts) {
  Ext.Ajax.extraParams={'loginuserid':parent.userid};
  });
#else
Ext.Ajax.on('beforerequest', function(conn, opts) {
  if(window.userid) Ext.Ajax.extraParams={'loginuserid':userid};
  });
#end

#if(${exportExceptionWindow} == "true")
//异常截获窗口
function showException(strMsg) {
	var arr = EXCEPTION_CLIENT_WIN_SIZE.split(',');
	var shortWindow = new Ext.Window({
		title : '<span class="commoncss">系统发生错误</span>', 
		layout : 'fit', 
		iconCls : 'exclamationIcon',
		width : arr[0], 
		height : arr[1], 
		animateTarget : Ext.getBody(),
		closable : true, 
		closeAction : 'close', 
		collapsible : false, 
		modal : true,
		maximizable : false, 
		border : false, 
		constrain : true, 
		items : [new Ext.Panel({
					html : EXCEPTION_CLIENT_MSG,
					style : "font-size: 13px;",
					autoScroll : true
				})],
		buttons : [{
					text : '更多信息',
					iconCls : 'previewIcon',
					handler : function() {
						var detailWindow = new Ext.Window({
									title : '<span class="commoncss">系统运行时异常堆栈详细信息</span>',
									layout : 'fit', 
									iconCls : 'previewIcon',
									width : document.body.clientWidth - 300, 
									height : document.body.clientHeight - 80, 
									animateTarget : Ext.getBody(),
									closable : true, 
									closeAction : 'close', 
									collapsible : false, 
									modal : true,
									maximizable : false, 
									border : false, 
									constrain : true, 
									buttons:[{
										text : '关闭',
										iconCls : 'deleteIcon',
										handler : function() {
										    detailWindow.hide();
										}
									}],
									items : [new Ext.Panel({
												html : strMsg,
												style : "font-size: 13px;",
												autoScroll : true
											})]
								}).show();
					}
				},{
					text : '关闭',
					iconCls : 'deleteIcon',
					handler : function() {
					    shortWindow.close();
					}
				}]
	}).show();
}
#end

</script>
</head>
#if(${showLoading} == "true")
<style type="text/css">
 #loading-mask {
	Z-INDEX: 20000;
	LEFT: 0px;
	WIDTH: 100%;
	POSITION: absolute;
	TOP: 0px;
	HEIGHT: 100%;
	BACKGROUND-COLOR: white
}
#loading {
	PADDING-RIGHT: 2px;
	PADDING-LEFT: 2px;
	Z-INDEX: 20001;
	LEFT: 32%;
	PADDING-BOTTOM: 2px;
	PADDING-TOP: 2px;
	POSITION: absolute;
	TOP: 40%;
	HEIGHT: auto
}
#loading IMG {
	MARGIN-BOTTOM: 5px
}
#loading .loading-indicator {
	PADDING-RIGHT: 10px;
	PADDING-LEFT: 10px;
	BACKGROUND: white;
	PADDING-BOTTOM: 10px;
	MARGIN: 0px;
	FONT: 12px 宋体, arial, helvetica;
	COLOR: #555;
	PADDING-TOP: 10px;
	HEIGHT: auto;
	TEXT-ALIGN: center
}
</style>
<script type="text/javascript">
Ext.EventManager.on(window, 'load', function(){
	 setTimeout(
		 function() {
			Ext.get('loading').remove();
			Ext.get('loading-mask').fadeOut({remove:true});
			}, 1); 
});
</script>
<DIV id=loading-mask></DIV>
<DIV id=loading>
<DIV class=loading-indicator><IMG style="MARGIN-RIGHT: 5px" 
	height=16
	src="${contextPath}/resource/image/ajax.gif"
	width=16 align=absMiddle>${pageLoadMsg}</DIV>
</DIV>
#end 