<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String context = application.getContextPath();
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Użytkownicy</title>
</head>
<body>

	<p style="color: red;">${errorString}</p>

	<h3>Lista zarejestrowanych użytkowników</h3>

	<table border="1" cellpadding="4" cellspacing="0">
		<tr>
			<td>ID</td>
			<td>Nazwa użytkownika</td>
			<td>Imię</td>
			<td>Nazwisko</td>
			<td colspan="2">Operacje</td>
		</tr>
		
		<c:forEach items="${userList}" var="uzytkownik" >
			<tr>
				<td>${uzytkownik.idUser }</td>
				<td>${uzytkownik.username }</td>
				<td>${uzytkownik.userImie }</td>
				<td>${uzytkownik.userNazwisko }</td>
				<td><a href="<%=context %>/reg?mode=M&id=${uzytkownik.idUser }">Edytuj</a>
				<td>Usuń</td>
			</tr>
		</c:forEach>

	</table>
	
	<a href="<%=context%>/main">Strona główna</a> &nbsp;&nbsp;<a href="<%=context%>/login">Wyloguj</a>

</body>
</html>