<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logowanie</title>
</head>
<body>

	<p style="color: red;">${errorString}</p>

	<form method="POST" action="doLogin">
		<table border="0">
			<tr>
				<td>Użytkownik</td>
				<td><input type="text" name="username" value="${user.username}" />
				</td>
			</tr>
			<tr>
				<td>Hasło</td>
				<td><input type="password" name="password" value="${user.password}" />
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Zaloguj" /> &nbsp;<a
					href="${pageContext.request.contextPath}/">Powrót</a></td>
			</tr>
		</table>
	</form>

</body>
</html>