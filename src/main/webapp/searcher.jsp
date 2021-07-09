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
    <div class="container">
        <h3 class="text-center">Katalog On-line</h3>
        <hr>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr bgcolor="#68C967">
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
            <c:forEach var="object" items="${libraryElementList}">
                <tr>
                    <td><c:out value="${object.getLibraryElementId()}"/></td>
                    <td><c:out value="${object.getTitle()}"/></td>
                    <td><c:out value="${object.getTypeId()}"/></td>
                    <td><c:out value="${object.getSortId()}"/></td>
                    <c:if test="${object.getTypeId() == 1}">
                        <td><c:out value="${object.getPagesNumber()}"/></td>
                        <td><c:out value="---"/></td>
                    </c:if>
                    <c:if test="${object.getTypeId() == 2}">
                        <td><c:out value="---"/></td>
                        <td><c:out value="${object.getDurationTime()}"/></td>
                    </c:if>
                    <td><c:out value="${object.getStatusId()}"/></td>
                    <td><a href="edit?id=<c:out value='${object.getLibraryElementId()}' />">Wypożycz</a>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <hr>
    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <form action="search" method="post">
                    <fieldset class="form-group">
                        <input type="text" class="form-control" name="name"
                               required="required">
                    </fieldset>
                    <button type="submit" class="btn btn-success">Wyszukaj</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
