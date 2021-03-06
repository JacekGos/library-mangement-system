<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>
<c:import url="../navigationBar.jsp" />

<html>
<head>
    <title>Rejestracja</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
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
                                               value="<c:out value='${userName}' />"
                                               class="form-control"
                                               name="userName" required="required"
                                               size="20">
                </fieldset>
                <fieldset class="form-group">
                    <label>Nazwisko</label> <input type="text"
                                                   value="<c:out value='${userSurname}' />"
                                                   class="form-control"
                                                   name="userSurname" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Hasło</label> <input type="password"
                                                value="<c:out value='${userPassword}' />"
                                                class="form-control"
                                                name="userPassword" required="required">
                </fieldset>

                <button type="submit" class="btn btn-success" style="background-color: #8edbf9; color: black">Zarejestruj</button>
            </form>
        </div>
        <c:if test="${isDataIncorrect}">
            <div class="card text-white bg-danger mb-3">
                <div class="card-body">
                    <c:forEach var="message" items="${errorMessageList}">
                        <c:out value="${message}"/><br>
                    </c:forEach>
                </div>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>