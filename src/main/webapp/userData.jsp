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

<c:import url="navigationBar.jsp" />

<div class="row">
    <div class="container">
        <c:if test="${sessionScope.userType == 2}">
            <h3 class="text-center">Twoje wypożyczenia</h3>
        </c:if>
        <c:if test="${sessionScope.userType == 1}">
            <h3 class="text-center">Wypożyczenia użytkownika</h3>
        </c:if>
        <hr>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr bgcolor="#8edbf9">
                <th>ID</th>
                <th>Id Elementu</th>
                <th>Data</th>
                <th>Status</th>
                <c:if test="${sessionScope.userType == 1}">
                    <th>Opcje</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="object" items="${libraryUserBorrowingsList}">
                <tr>
                    <td><c:out value="${object.getBorrowingId()}"/></td>
                    <td><c:out value="${object.getLibraryElementId()}"/></td>
                    <td><c:out value="${object.getBorrowingDate()}"/></td>
                    <td><c:out value="${object.getStatusName()}"/></td>
                    <c:if test="${sessionScope.userType == 1}">
                        <c:if test="${object.getBorrowingStatusId() == 4}">
                            <td><a href="endBorrowing?borrowingId=<c:out value='${object.getBorrowingId()}' />">Zakończ wypożyczenie</a>
                        </c:if>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%--<div class="row">
    <div class="container">
        <form action="searchUser" method="get">
            <fieldset class="form-group">
                <input type="text" class="form-control" name="searchedUserId">
            </fieldset>
            <div class="container text-center">
                <button type="submit" class="btn btn-success">Wyszukaj</button>
            </div>
        </form>
    </div>
</div>--%>
</body>
</html>
