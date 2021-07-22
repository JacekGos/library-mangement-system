<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Panel logowania</title>
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
                <form action="loginProcess" method="post">
                    <caption>
                        <h2> Panel Logowania </h2>
                    </caption>
                    <fieldset class="form-group">
                        <label>Login</label>
                        <input type="text" class="form-control" name="login" required="required">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Hasło</label>
                        <input type="password" class="form-control" name="password" required="required">
                    </fieldset>
                    <button type="submit" class="btn btn-success" style="background-color: #8edbf9; color: black">Zaloguj</button>
                </form>
                <form action="newUser" method="get">
                    <button type="submit" class="btn btn-success" style="background-color: #8edbf9; color: black">Załóż konto</button>
                </form>
            </div>
        </div>
    </div>

</body>
</html>
