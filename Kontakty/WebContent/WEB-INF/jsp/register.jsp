<%@ page pageEncoding="utf-8"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
	<%@include file="css/style.css"%>
</style>

<title>Rejestracja użytkownika</title>
</head>
<body>

<div id="container">

	<p class="errorRed">${errorString}</p>

	<p>Aby korzystać z programu KONAKTY musisz być zarejestrowany w
		bazie jako użytkownik</p>

	<form title="Rejestracja" method="POST" action="doRegister">

		<input type="hidden" name="operacja" value="${user.operacja }">
		<input type="hidden" name="idUser" value="${user.idUser }">

		<table border="0" align="center" cellpadding="4">
			<tr>
				<td>Użytkownik</td>
				<td><input type="text" name="username" value="${user.username}" /><font
					color="red">*</font></td>
			</tr>
			<tr>
				<td>Hasło</td>
				<td><input type="password" name="password"
					value="${user.password}" /><font color="red">*</font></td>
			</tr>
			<tr>
				<td>Imię</td>
				<td><input type="text" name="userImie" value="${user.userImie}" /><font
					color="red">*</font></td>
			</tr>
			<tr>
				<td>Nazwisko</td>
				<td><input type="text" name="userNazwisko"
					value="${user.userNazwisko}" /> <font color="red">*</font></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Zapisz" class="formButton"/> &nbsp;<a
					href="${pageContext.request.contextPath}/">Powrót</a></td>
			</tr>
		</table>
	</form>
	<p>
		<font color="red">*</font> - pola wymagane
	</p>

</div>
</body>
</html>