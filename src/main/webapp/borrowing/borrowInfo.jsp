<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>
<c:import url="../navigationBar.jsp" />
<c:if test="${sessionScope.userType == null}">
    <c:redirect url = "/account/login.jsp"/>
</c:if>

<html>
<head>
    <title>Informacje o wypożyczeniu</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
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
        <div class="card text-white bg-primary mb-3">
            <div class="card-body">
                <h4> Udaj się do punktu wypożyczeń </h4>
            </div>
        </div>
    </c:if>

</div>
</body>