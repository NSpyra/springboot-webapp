<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TAW: Norbert Spyra - zaliczenie projektu</title>
</head>

<body style="background-color:rgba(0, 0, 0, 0.1);">

	<i>Data ostatniej aktualizacji :</i>
	<i><%=new java.util.Date()%></i>

	<div align="center">

		<h1>Projekt na zaliczenie przedmiotu Technologie i aplikacje
			webowe</h1>
		<h2>Inzynieria Oprogramowania 2020/21</h2>
		<h3>
			<i>Norbert Spyra - Kalkulator walut</i>
		</h3>


		<h2>Kalkulator walut</h2>

		<h3>Rezultat obliczen:</h3>
		<h3>${value} ${course1} = ${result} ${course2}</h3>
		<p>
			<i>Prosze nacisnac 'Wstecz', aby powrocic do strony glownej</i>
		</p>
		
	</div>
</body>

</html>