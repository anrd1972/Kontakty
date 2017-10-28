<%@ page pageEncoding="utf-8"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
	<%@include file="css/style.css"%>
</style>
<title>Osoba kontaktowa - szczegóły</title>
</head>
<body>

<div id="container">

	<h2>Osoba kontaktowa - szczegóły</h2>
	<p class="errorRed" align="center">${errorString}</p>
	
	<table border="1" align="center" cellpadding="4" cellspacing="0">
		<tr>
			<td align="right"><b>Imię:</b></td>
			<td align="left">${osoba.osobaImie}</td>
		</tr>
		<tr>
			<td align="right"><b>Nazwisko:</b></td>
			<td align="left">${osoba.osobaNazwisko}</td>
		</tr>
		<tr>
			<td align="right"><b>Adres email:</b></td>
			<td align="left">${osoba.osobaEmail}</td>
		</tr>
		<tr>
			<td align="right"><b>Telefon domowy:</b></td>
			<td align="left">${osoba.osobaTelefonDom}</td>
		</tr>
		<tr>
			<td align="right"><b>Telefon do pracy:</b></td>
			<td align="left">${osoba.osobaTelefonPraca}</td>
		</tr>
		<tr>
			<td align="right" valign="top"><b>Adres:</b></td>
			<td align="left">${osoba.osobaAdresUlica}<br/>
				${osoba.osobaAdresNrDomu}&nbsp;/
				${osoba.osobaAdresNrMieszkania}<br/>
				${osoba.osobaAdresKodPocztowy}&nbsp;${osoba.osobaAdresMiasto}
			</td>
		</tr>
		<tr>
			<td align="right"><b>Urodziny:</b></td>
			<td align="left">${osoba.osobaUrodziny}</td>
		</tr>
	
		<tr>
		<td colspan="2" align="center">
			<input type="button" onclick="window.location.href='${pageContext.request.contextPath}/contacts'" 
						value="Powrót"/>
		</td>
		</tr>
	</table>
	
</div>

</body>
</html>