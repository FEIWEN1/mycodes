<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录按钮</title>
</head>
<body>
	<form action="/login.action" method="post">
		姓名：<input type="text" name="username"><br>
		密码：<input type="text" name="password"> <br>
		验证码：<input id="randomcode" name="randomcode" size="8" /> <img id="randomcode_img" src="${pageContext.request.contextPath}/randomImage" alt=""
			width="56" height="20" align='absMiddle'/>
			 <!-- <a href=javascript:randomcode_refresh()>刷新</a> -->
			 <br> 
			 <input type="checkbox" name="rememberMe" />自动登陆<br>
			<input type="submit">
	</form>
</body>
</html>