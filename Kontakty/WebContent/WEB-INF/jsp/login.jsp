<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css">
	<%@include file="css/style.css"%>
</style>

<title>Logowanie</title>
</head>
<body>

	<div id="container">

	<p class="errorRed" align="center"> ${errorString}</p>

	<form method="POST" action="doLogin">
		<table border="0" align="center" cellpadding="4">
			<tr>
				<td align="right">Użytkownik</td>
				<td><input type="text" name="username" value="${user.username}" />
				</td>
			</tr>
			<tr>
				<td align="right">Hasło</td>
				<td><input type="password" name="password" value="${user.password}"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Zaloguj" class="formButton"/></td>
			</tr>
		</table>
	</form>
	
	</div>

</body>
</html>