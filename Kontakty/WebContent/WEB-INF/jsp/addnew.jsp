<%@ page pageEncoding="utf-8"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
	<%@include file="css/style.css"%>
</style>
<title>Nowy kontakt</title>
</head>
<body>

<div id="container">

	<h2>Nowa osoba</h2>
	<p class="errorRed" align="center">${errorString}</p>
	
	<form title="Kontakt" method="POST" action="doAdd">
	
		<input type="hidden" name="operacja" value="${osoba.operacja }">
		<input type="hidden" name="idOsoby" value="${osoba.idOsoby }">
		
		<table border="0" align="center" cellpadding="4" width="440">
			<tr>
				<td align="right" width="160">Imię:</td>
				<td align="left" width="280"><input type="text" name="osobaImie" value="${osoba.osobaImie }" size="35"/>
					<font color="red">*</font>
				</td>		
			</tr>
			
			<tr>
				<td align="right">Nazwisko</td>
				<td align="left"><input type="text" name="osobaNazwisko" value="${osoba.osobaNazwisko }" size="35"/></td>		
			</tr>
			
			<tr>
				<td align="right">Adres email:</td>
				<td align="left"><input type="text" name="osobaEmail" value="${osoba.osobaEmail }" size="35"/>
					<font color="red">*</font>
				</td>		
			</tr>
			
			<tr>
				<td align="right">Telefon domowy:</td>
				<td align="left"><input type="text" name="osobaTelefonDom" value="${osoba.osobaTelefonDom }" size="35"/></td>		
			</tr>
			
			<tr>
				<td align="right">Telefon praca:</td>
				<td align="left"><input type="text" name="osobaTelefonPraca" value="${osoba.osobaTelefonPraca }" size="35"/></td>		
			</tr>
			
			<tr>
				<td align="right">Ulica:</td>
				<td align="left"><input type="text" name="osobaAdresUlica" value="${osoba.osobaAdresUlica }" size="35"/></td>		
			</tr>
			
			<tr>
				<td align="right">Numer domu:</td>
				<td align="left"><input type="text" name="osobaAdresNrDomu" value="${osoba.osobaAdresNrDomu }" size="35"/></td>		
			</tr>
			
			<tr>
				<td align="right">Numer mieszkania:</td>
				<td align="left"><input type="text" name="osobaAdresNrMieszkania" value="${osoba.osobaAdresNrMieszkania }" size="35"/></td>		
			</tr>
			
			<tr>
				<td align="right">Miasto:</td>
				<td align="left"><input type="text" name="osobaAdresMiasto" value="${osoba.osobaAdresMiasto }" size="35"/></td>		
			</tr>
			
			<tr>
				<td align="right">kod pocztowy:</td>
				<td align="left"><input type="text" name="osobaAdresKodPocztowy" value="${osoba.osobaAdresKodPocztowy }" size="35"/></td>		
			</tr>
			
			<tr>
				<td align="right">Urodzony:</td>
				<td align="left"><input type="text" name="osobaUrodziny" value="${osoba.osobaUrodziny }" size="35"/></td>		
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="Zapisz" class="formButton"/> &nbsp;
					<input type="button" onclick="window.location.href='${pageContext.request.contextPath}/contacts'" 
						value="Powrót" class="formButton"/>
				</td>
			</tr>
		
		</table>
	
	</form>
	
</div>

</body>
</html>