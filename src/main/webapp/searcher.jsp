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
    <header>
        <nav class="navbar navbar-expand-md navbar-dark"
             style="background-color: #47a0ff">
            <div>
                <a href="<%=request.getContextPath()%>/index.jsp" class="navbar-brand"> Strona główna </a>
            </div>
            <ul class="navbar-nav">
                <li><b><a href="<%=request.getContextPath()%>/libraryElementList"
                          class="nav-link">Wyszukaj w zbiorze</a></b></li>
                <c:if test="${sessionScope.userName == null}">
                    <li><b><a href="<%=request.getContextPath()%>/login"
                              class="nav-link">Zaloguj</a></b></li>
                </c:if>
                <c:if test="${sessionScope.userName != null}">
                    <li><b><a href="<%=request.getContextPath()%>/logoutProcess"
                              class="nav-link">Wyloguj</a></b></li>
                </c:if>
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
                        <td><c:out value="${object.getTypeName()}"/></td>
                        <td><c:out value="${object.getSortName()}"/></td>
                        <c:if test="${object.getTypeId() == 1}">
                            <td><c:out value="${object.getPagesNumber()}"/></td>
                            <td><c:out value="---"/></td>
                        </c:if>
                        <c:if test="${object.getTypeId() == 2}">
                            <td><c:out value="---"/></td>
                            <td><c:out value="${object.getDurationTime()}"/></td>
                        </c:if>
                        <td><c:out value="${object.getStatusName()}"/></td>
                        <c:if test="${sessionScope.userName == null}">
                            <td><a href="edit?id=<c:out value='${object.getLibraryElementId()}' />">Wypożycz</a></td>
                        </c:if>
                        <c:if test="${sessionScope.userType == 1}">
                            <td><a href="edit?id=<c:out value='${object.getLibraryElementId()}' />">Edytuj</a>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="edit?id=<c:out value='${object.getLibraryElementId()}' />">Usuń</a></td>
                        </c:if>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="container">
            <form action="search" method="get">
                <fieldset class="form-group">
                    <input type="text" class="form-control" name="searchedTitle">
                </fieldset>
                <div class="container text-center">
                    <button type="submit" class="btn btn-success">Wyszukaj</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
