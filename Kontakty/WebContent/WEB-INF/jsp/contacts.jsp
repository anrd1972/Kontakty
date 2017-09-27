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
	
	<table border="1" align="center" cellpadding="4" cellspacing="0">
	
	<tr>
			<td>ID</td>
			<td>Imię</td>
			<td>Nazwisko</td>
			<td>Email</td>
			<td>Telefon dom</td>
			<td>Telefon praca</td>
			<td>Ulica</td>
			<td>Nr domu</td>
			<td>Nr mieszkania</td>
			<td>Miasto</td>
			<td>Kod pocztowy</td>
			<td>Data urodzenia</td>
			<td colspan="2">Operacje</td>
		</tr>
		
		<c:forEach items="${listaOsob}" var="osoba" >
		
			<tr>
				<td>{$osoba.idOsoby}</td>
				<td>{$osoba.osobaImie}</td>
				<td>{$osoba.osobaNazwisko}</td>
				<td>{$osoba.osobaEmail}</td>
				<td>{$osoba.osobaTelefonDom}</td>
				<td>{$osoba.osobaTelefonPraca}</td>
				<td>{$osoba.osobaAdresUlica}</td>
				<td>{$osoba.osobaAdresNrDomu}</td>
				<td>{$osoba.osobaAdresNrMieszkania}</td>
				<td>{$osoba.osobaAdresMiasto}</td>
				<td>{$osoba.osobaAdresKodPocztowy</td>
				<td>{$osoba.osobaUrodziny}</td>
				<td>Edytuj</td>
				<td>Usuń</td>
			</tr>
		
		</c:forEach>
		
	</table>
	
	<p>Zarejestrowanych użytkowników: <%=ileOsob %></p>
	
	<hr style="height: 1px">
	<a href="<%=context%>/main">Strona główna</a> &nbsp;&nbsp;<a href="<%=context%>/login">Wyloguj</a>
	
	</div>

</body>
</html>