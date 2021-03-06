<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>

<html>
<head>
    <title>Library System</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<style>
    .loggedUser {
        margin-left: auto;
        margin-right: 0;
    }
</style>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: #3364ff">
        <div>
            <a href="<c:out value="${pageContext.servletContext.contextPath}"/>/index.jsp" class="navbar-brand"> Strona główna </a>
        </div>
        <ul class="navbar-nav">
            <li><b><a href="<c:out value="${pageContext.servletContext.contextPath}"/>/libraryElementList"
                      class="nav-link">Wyszukaj w zbiorze</a></b></li>
            <c:if test="${sessionScope.userType == 1}">
                <li><b><a href="<c:out value="${pageContext.servletContext.contextPath}"/>/newLibraryElement"
                          class="nav-link">Dodaj pozycję zbioru</a></b></li>
                <li><b><a href="<c:out value="${pageContext.servletContext.contextPath}"/>/userList"
                          class="nav-link">Opcje użytkowników</a></b></li>
                <li><b><a href="<c:out value="${pageContext.servletContext.contextPath}"/>/requestList"
                          class="nav-link">Zapytania</a></b></li>
            </c:if>
            <c:if test="${sessionScope.userType == 2}">
                <li><b><a href="<c:out value="${pageContext.servletContext.contextPath}"/>/borrowingList"
                          class="nav-link">Wypożyczenia</a></b></li>
            </c:if>
        </ul>

        <div class="loggedUser">
            <ul class="navbar-nav">
                <c:if test="${sessionScope.userName == null}">
                    <li><b><a href="<%=request.getContextPath()%>/login"
                              class="nav-link">Zaloguj</a></b></li>
                </c:if>
                <c:if test="${sessionScope.userName != null}">
                    <span class="navbar-text">
                        Użytkownik: <c:out value="${sessionScope.userLogin}"/>
                    </span>
                        <li><b><a href="<%=request.getContextPath()%>/logoutProcess"
                                  class="nav-link">Wyloguj</a></b></li>
                </c:if>
            </ul>
        </div>
    </nav>
</header>
<br>

</body>
</html>
