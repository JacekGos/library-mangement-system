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
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <form action="insertUser" method="post">

                <caption>
                    <h2>
                        Rejestracja
                    </h2>
                </caption>

                <fieldset class="form-group">
                    <label>Imię</label> <input type="text"
                                                    class="form-control"
                                                    name="userName" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Nazwisko</label> <input type="text"
                                                     class="form-control"
                                                     name="userSurname" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Hasło</label> <input type="password"
                                                       class="form-control"
                                                       name="userPassword" required="required">
                </fieldset>

                <button type="submit" class="btn btn-success">Zarejestruj</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>