<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<c:import url="../navigationBar.jsp"/>

<div class="row">
    <div class="container">
        <h3 class="text-center">Bieżące zapytania</h3>
        <hr>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr bgcolor="#8edbf9">
                <th>ID</th>
                <th>ID Wypożyczenia</th>
                <th>Data zapytania</th>
                <th>Status zapytania</th>
                <th>Opcje</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="object" items="${requestsList}">
                <c:if test="${object.getStatusId() == 2}" >
                    <tr>
                        <td><c:out value="${object.getRequestId()}"/></td>
                        <td><c:out value="${object.getBorrowingId()}"/></td>
                        <td><c:out value="${object.getRequestDate()}"/></td>
                        <td><c:out value="${object.getStatusName()}"/></td>
                        <td><a href="requestApprove?requestId=<c:out value="${object.getRequestId()}" />">Opcje wyboru</a></td>
                    </tr>
                </c:if>

            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<div class="row">
    <div class="container">
        <form action="searchRequest" method="get">
            <fieldset class="form-group">
                <input type="text" class="form-control" name="searchedUserId" placeholder="ID Użytkownika"
                       required="required"/>
            </fieldset>
            <div class="container text-center">
                <button type="submit" class="btn btn-success" style="background-color: #8edbf9; color: black">Wyszukaj</button>
            </div>
        </form>
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
