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

    <header>
        <nav class="navbar navbar-expand-md navbar-dark"
             style="background-color: #47a0ff">
            <div>
                <a href="<%=request.getContextPath()%>/index.jsp" class="navbar-brand"> Strona główna </a>
            </div>
            <ul class="navbar-nav">
                <li><b><a href="<%=request.getContextPath()%>/list"
                          class="nav-link">Wyszukaj w zbiorze</a></b></li>
            </ul>
        </nav>
    </header>
<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">Katalog On-line</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
                New User</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tytuł</th>
                    <th>Typ</th>
                    <th>Rodzaj</th>
                    <th>Liczba stron</th>
                    <th>Czas trwania</th>
                    <th>Status</th>
                    <th>Opcje</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="libraryElement" items="${libraryElementsList}">
                <tr>
                    <td><c:out value="${libraryElement.getLibraryElementId()}" /></td>
                    <td><c:out value="${libraryElement.getTitle()}" /></td>
                    <td><c:out value="${libraryElement.getTypeId()}" /></td>
                    <td><c:out value="${libraryElement.getSortId()}" /></td>
                    <c:if test="${libraryElement.getTypeId() == 1}" >
                        <td><c:out value="${libraryElement.getPagesNumber()}" /></td>
                        <td><c:out value="---"/></td>
                    </c:if>
                    <c:if test="${libraryElement.getTypeId() == 2}">
                        <td><c:out value="---"/></td>
                        <td><c:out value="${libraryElement.getDurationTime()}"/></td>
                    </c:if>
                    <td><c:out value="${libraryElement.getStatusId()}" /></td>
                       <%-- <td><a href="edit?id=<c:out value='${1}' />">Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp; <a
                                    href="delete?id=<c:out value='${1}' />">Delete</a></td>--%>
                </tr>
            </c:forEach>
           <%-- <c:out value="${number}"/>
            <tr>
                <td><c:out value="${libraryElement.getLibraryElementId()}" /></td>
                <td><c:out value="${libraryElement.getTitle()}" /></td>
                <td><c:out value="${libraryElement.getTypeId()}" /></td>
                <td><c:out value="${libraryElement.getSortId()}" /></td>
                <c:if test="${libraryElement.getTypeId() == 1}" >
                    <td><c:out value="${libraryElement.getPagesNumber()}" /></td>
                    <td><c:out value="---"/></td>
                </c:if>
                <c:if test="${libraryElement.getTypeId() == 2}">
                    <td><c:out value="---"/></td>
                    <td><c:out value="${libraryElement.getDurationTime()}"/></td>
                </c:if>
                <td><c:out value="${libraryElement.getStatusId()}" /></td>
            </tr>--%>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>
</html>
