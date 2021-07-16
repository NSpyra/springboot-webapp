<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TAW: Norbert Spyra - zaliczenie projektu</title>
</head>

<body style="background-color: AliceBlue;">

	<i>Data ostatniej aktualizacji :</i>
	<i><%=new java.util.Date()%></i>

	<div align="center">

		<h1>Projekt na zaliczenie przedmiotu Technologie i aplikacje
			webowe</h1>
		<h2>Inzynieria Oprogramowania 2020/21</h2>
		<h3>
			<i>Norbert Spyra - Kursy walut</i>
		</h3>


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



		<h2>Kalkulator walut</h2>

		<form th:action="@{/}" th:object="${Calculate}" method="post">
			<p>
				<label for="value1">Prosze podac kwote: </label> <input type="text"
					id="value1" name="value1" /></input>
			</p>
			<label for="currs">Prosze wybrac walute pierwotna</label> <select
				id="currency1" name="currency1">
				<c:forEach items="${recordValues}" var="recordValue">
					<option value=${recordValue.name}>${recordValue.name}</option>
				</c:forEach>
			</select>
			</p>
			<p>
				<label for="currs">Prosze wybrac walute docelowa</label> <select
					id="currency2" name="currency2">
					<c:forEach items="${recordValues}" var="recordValue">
						<option value=${recordValue.name}>${recordValue.name}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<button type="submit">Przelicz!</button>
			</p>
		</form>


	</div>
</body>

</html>