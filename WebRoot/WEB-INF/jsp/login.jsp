<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统登录</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/login.action " method="post">
	    <table border="1">
	    	<tr><td>用户名</td><td><input type="text" name="username" /></td></tr>
	    	<tr><td>密　码</td><td><input type="text" name="password" /></td></tr>
	    	<tr><td><input type="submit" value="登录" /></td>
	    </table>
    </form>
</body>
</html>