<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String context = application.getContextPath();
	Integer ileUserow = (Integer) request.getAttribute("ile");
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
				
				<td>
					<c:set var="ilosc" value="<%=ileUserow %>"/>
					
					<c:choose>
					
						<c:when test="${ilosc > 1}">
							<a href="<%=context %>/del?id=${uzytkownik.idUser }">Usuń</a>
						</c:when>
						
						<c:otherwise>
						 &nbsp;
						</c:otherwise>
					</c:choose>
				</td>
				
				
			</tr>
		</c:forEach>

	</table>
	
	<p>
		<a href="<%=context %>/reg?mode=D&id=">Dodaj nowego użytkownika</a>
	</p>
	
	<p/>
	<hr style="height: 1px">
	<a href="<%=context%>/main">Strona główna</a> &nbsp;&nbsp;<a href="<%=context%>/login">Wyloguj</a>
	
	<p>Zarejestrowanych użytkowników: <%=ileUserow %></p>

</body>
</html>