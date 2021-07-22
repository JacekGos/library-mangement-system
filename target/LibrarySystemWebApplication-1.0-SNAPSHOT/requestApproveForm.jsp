<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Request</title>
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
        <h3 class="text-center">Dane użytkownika</h3>
        <hr>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr bgcolor="#8edbf9">
                <th>ID</th>
                <th>Imie</th>
                <th>Nazwisko</th>
                <th>Nazwa użytkownika</th>
                <th>Naliczone kary</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td><c:out value="${libraryUser.getUserId()}"/></td>
                    <td><c:out value="${libraryUser.getUserName()}"/></td>
                    <td><c:out value="${libraryUser.getUserSurName()}"/></td>
                    <td><c:out value="${libraryUser.getLogin()}"/></td>
                    <td><c:out value="${libraryUser.getPenalty()}"/></td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<br>
<div class="row">
    <div class="container">
        <h3 class="text-center">Dane pozycji</h3>
        <hr>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr bgcolor="#8edbf9">
                <th>ID</th>
                <th>Tytuł</th>
                <th>Typ</th>
                <th>Rodzaj</th>
                <th>Liczba stron</th>
                <th>Czas trwania</th>
                <th>Status</th>
            </tr>
            </thead>
                <tbody>
                    <tr>
                        <td><c:out value="${libraryElement.getLibraryElementId()}"/></td>
                        <td><c:out value="${libraryElement.getTitle()}"/></td>
                        <td><c:out value="${libraryElement.getTypeName()}"/></td>
                        <td><c:out value="${libraryElement.getSortName()}"/></td>
                        <c:if test="${libraryElement.getTypeId() == 1}">
                            <td><c:out value="${libraryElement.getPagesNumber()}"/></td>
                            <td><c:out value="---"/></td>
                        </c:if>
                        <c:if test="${libraryElement.getTypeId() == 2}">
                            <td><c:out value="---"/></td>
                            <td><c:out value="${libraryElement.getDurationTime()}"/></td>
                        </c:if>
                        <td><c:out value="${libraryElement.getStatusName()}"/></td>
                    </tr>
                </tbody>
        </table>
    </div>
</div>
<br>
<div class="container text-center">
    <form action="acceptRequest" method="post">
        <input type="hidden" name="libraryElementId"
               value="<c:out value='${libraryElement.getLibraryElementId()}' />"/>
        <input type="hidden" name="requestId"
               value="<c:out value='${requestId}' />"/>
        <input type="hidden" name="borrowingId"
               value="<c:out value='${borrowingId}' />"/>
        <button type="submit" class="btn btn-success" style="background-color: #8edbf9; color: black">Akceptuj</button>
    </form>
</div>
<div class="container text-center">
    <form action="rejectRequest" method="post">
        <input type="hidden" name="libraryElementId"
               value="<c:out value='${libraryElement.getLibraryElementId()}' />"/>
        <input type="hidden" name="requestId"
               value="<c:out value='${requestId}' />"/>
        <input type="hidden" name="borrowingId"
               value="<c:out value='${borrowingId}' />"/>
        <button type="submit" class="btn btn-success" style="background-color: #8edbf9; color: black"> Odrzuć </button>
    </form>
</div>
</body>
</html>
