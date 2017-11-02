<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String context = application.getContextPath();
	Integer ileOsob = (Integer) request.getAttribute("ile");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css">

<%@ include file="css/style.css"%>

</style>

<title>Moje kontakty</title>
</head>
<body>

	<div id="container">
	
	<p class="errorRed" align="center">${errorString}</p>

	<h2>Lista zarejestrowanych kontaktów</h2>
	
	<p>
		<a href="<%=context%>/new?mode=D&id=">Dodaj</a>
	</p>
	
	<div align="right">
	<form title="szukaj" method="POST" action="contacts" >
		<input type="text" name="find" />
		<input type="submit" value="Szukaj" />
	</form>
	</div>
	
	<table border="1" align="center" cellpadding="4" cellspacing="0">
	
	<tr>
			<td>ID</td>
			<td>Imię</td>
			<td>Nazwisko</td>
			<td>Email</td>
			<td colspan="2" align="center">Operacje</td>
		</tr>
		
		<c:forEach items="${listaOsob}" var="osoba" >
		
			<tr>
				<td><input type="button"
					onclick="window.location.href='${pageContext.request.contextPath}/detail?id=${osoba.idOsoby}'"
					value="${osoba.idOsoby}"/></td>
				<td>${osoba.osobaImie}</td>
				<td>${osoba.osobaNazwisko}</td>
				<td>${osoba.osobaEmail}</td>
				<td>
					<input type="button"
						onclick="window.location.href='${pageContext.request.contextPath}/new?mode=M&id=${osoba.idOsoby}'" 
						value="Edytuj"/>
				</td>
				<td><input type="button" onclick="window.location.href='${pageContext.request.contextPath}/rem?id=${osoba.idOsoby}'"
						value="Usuń"/></td>
			</tr>
		
		</c:forEach>
		
	</table>
	
	<p>Zarejestrowanych użytkowników: <%=ileOsob %></p>
	
	<hr style="height: 1px">
	<a href="<%=context%>/main">Strona główna</a> &nbsp;&nbsp;<a href="<%=context%>/login">Wyloguj</a>
	
	</div>

</body>
</html>