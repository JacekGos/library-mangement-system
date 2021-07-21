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

<div class="card-body">
    <caption>
        <h2> Dane Twojego Konta </h2><br>
        <h1> <c:out value="${libraryUser.getUserName()}" /></h1><br>
        <h1> <c:out value="${libraryUser.getUserSurName()}" /></h1><br>
        <h1> <c:out value="${libraryUser.getLogin()}" /></h1><br>
        <h1> <c:out value="${libraryUser.getPassword()}" /></h1><br>
        <h1> Dane Twojego Konta </h1><br>
    </caption>
</div>

</body>