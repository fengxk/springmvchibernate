<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>塞弗司导系统-登陆</title>
<%@ include file="head.jsp"%>
<link type="text/css" rel="stylesheet" href="css/index.css" />
</head>
<script type="text/javascript">
	$(function() {
		$("#invisibility").click(function() {
			var url = "manager/verifyCode";
			var timetamp = (new Date()).valueOf();
			url = url + "?t=" + timetamp;
			$("#verification_code").attr('src',url);
		});
		
		$("#entry_btn").click(function() {
			para="verifyCode="+$("#verifyCode").val();
			xmlHttp = $.ajax({
				url : 'login/validateVerifyCode',
		        data : para,  
		        dataType : 'json',  
		        type : 'POST', 
		        success : function(data) {  
	            	if(data.status == 0){
	            		alert("验证码不正确");
	            	}else if(data.status == 1){
	            		$("#login_form").submit();
	            	}
		        },error : function(data) {
		              alert("服务器异常")  ;
		        }  
			});
		});
	});
	
</script>
<body>
	<!--内容开始-->
	<div class="login">
	<p class="error">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</p>
		<form action="j_spring_security_check" method="post" id="login_form">
			<h2>登 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 录</h2>
			<ul class="login_in">
				<li><span>用户名</span><input type="text" name="username" value="admin" />
				</li>
				<li><span>密&nbsp;&nbsp;&nbsp;码</span><input type="password"
					name="password" value="123456"/>
				</li>
				<li class="height_36"><span>验证码</span><input type="text" name="verifyCode" id="verifyCode"/>
				</li>
			</ul>
			<div class="verification">
				<div class="verification_code">
					<span><img id="verification_code" src="manager/verifyCode"
						width="70" height="26" />
					</span><span class="invisibility" id="invisibility">看不清楚，换张图片</span>
				</div>
			</div>
			<div class="entry_btn" id="entry_btn">登录</div>
		</form>
	</div>
	<!--内容结束-->
</body>
</html>
