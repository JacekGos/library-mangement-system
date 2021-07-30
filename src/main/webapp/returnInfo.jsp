<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Return</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>
<c:import url="navigationBar.jsp" />

<div class="container col-md-5" align="center">
    <div class="card">
        <div class="card-body" >
            <table class="table table-bordered">
                <thead>
                <tr bgcolor="#8edbf9">
                    <th>Data wypożyczenia</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><c:out value="${borrowingTime}"/></td>
                </tr>
                </tbody>
            </table>
            <table class="table table-bordered">
                <thead>
                <tr bgcolor="#8edbf9">
                    <th>Opóźnienie zwrotu</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <c:if test="${returningResult == false}" >
                        <td><c:out value="${minutes}"/> [minuty] <c:out value="${seconds}"/> [sekundy] </td>
                    </c:if>
                    <c:if test="${returningResult == true}" >
                        <td> Oddano w terminie </td>
                    </c:if>
                </tr>
                </tbody>
            </table>
            <table class="table table-bordered">
                <thead>
                <tr bgcolor="#8edbf9">
                    <th>Naliczona kara</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <c:if test="${returningResult == false}" >
                        <td> </td>
                    </c:if>
                    <c:if test="${returningResult == true}" >
                        <td> 0 [zł] </td>
                    </c:if>
                </tr>
                </tbody>
            </table>
            <form action="login.jsp" method="get">
                <button type="submit" class="btn btn-success" style="background-color: #8edbf9; color: black">Powrót</button>
            </form>
        </div>
    </div>
</div>
</body>
