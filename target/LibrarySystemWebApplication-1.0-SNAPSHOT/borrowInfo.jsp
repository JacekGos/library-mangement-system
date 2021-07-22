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
    <c:if test="${borrowingResult == false}" >
        <h2> Pozycja nie jest dostępna do wypożyczenia </h2><br>
    </c:if>
    <c:if test="${borrowingResult == true}" >
        <div class="card">
            <div class="card-body" >
                <h2> Dane pozycji: </h2>
                <table class="table table-bordered">
                    <thead>
                    <tr bgcolor="#8edbf9">
                        <th>ID</th>
                        <th>Tytuł</th>
                        <th>Typ</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><c:out value="${libraryElement.getLibraryElementId()}" /></td>
                            <td><c:out value="${libraryElement.getTitle()}" /></td>
                            <td><c:out value="${libraryElement.getTypeName()}" /></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <h2> Udaj się do punktu wypożyczeń </h2>
    </c:if>

</div>
</body>