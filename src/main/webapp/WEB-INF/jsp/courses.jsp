<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TAW: Norbert Spyra - zaliczenie projektu</title>
</head>

<body style="background-color:AliceBlue;">

<i>Data ostatniej aktualizacji :</i>
<i><%= new java.util.Date() %></i>

<div align="center">

<h1>Projekt na zaliczenie przedmiotu Technologie i aplikacje webowe</h1>
<h2>Inzynieria Oprogramowania 2020/21</h2>
<h3><i>Norbert Spyra - Kalkulator walut</i></h3>


<table border="1" cellpadding="10">
<tr>
<th>Mnemonik</th>
<th>Kurs</th>
</tr>
<c:forEach items="${recordValues}" var="recordValue">
	<tr>
		<td>${recordValue.name}</td>
		<td>${recordValue.value}</td>
	</tr>
</c:forEach>
</table>

<i>Start</i>

<h2>Kalkulator - draft</h2>
<label for="currs">Prosze wybrac walute</label>
<c:forEach items="${recordValues}" var="recordValue">
	<select id="currs" name="currs">
	<option value="recordValues">${recordValues}</option>
	</select>
</c:forEach>

<i>Stop</i>

</div>
</body>

</html>