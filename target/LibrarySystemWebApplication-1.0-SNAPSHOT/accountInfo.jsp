<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>User Management Application</title>
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
    <h2> Konto zostało utworzone </h2><br>
    <div class="card">
        <div class="card-body" >
            <caption>
                <h3 align="left"> Imie: <c:out value="${libraryUser.getUserName()}" /></h3><br>
                <h3 align="left"> Nazwisko: <c:out value="${libraryUser.getUserSurName()}" /></h3><br>
                <h3 align="left"> Login: <c:out value="${libraryUser.getLogin()}" /></h3><br>
                <form action="login.jsp" method="get">
                    <button type="submit" class="btn btn-success">Zaloguj się</button>
                </form>
            </caption>
        </div>
    </div>

</div>
</body>