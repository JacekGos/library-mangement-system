<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>New Account Data</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">

    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    %>
</head>
<body>

<c:import url="../navigationBar.jsp" />

<div class="container col-md-5" align="center">
    <h2> Konto zostało utworzone </h2><br>
    <div class="card">
        <div class="card-body" >
            <h2> Dane pozycji: </h2>
            <table class="table table-bordered">
                <thead>
                <tr bgcolor="#8edbf9">
                    <th>Imię</th>
                    <th>Nazwisko</th>
                    <th>Login</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><c:out value="${libraryUser.getUserName()}" /></td>
                    <td><c:out value="${libraryUser.getUserSurName()}" /></td>
                    <td><c:out value="${libraryUser.getLogin()}" /></td>
                </tr>
                </tbody>
            </table>
            <form action="account/login.jsp" method="get">
                <button type="submit" class="btn btn-success" style="background-color: #8edbf9; color: black">Zaloguj się</button>
            </form>
        </div>
    </div>
</div>
</body>