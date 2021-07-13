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
    <style>
        .loggedUser {
            margin-left: auto;
            margin-right: 0;
        }
    </style>
    <header>
        <nav class="navbar navbar-expand-md navbar-dark"
             style="background-color: #47a0ff">
            <div>
                <a href="<%=request.getContextPath()%>/index.jsp" class="navbar-brand"> Strona główna </a>
            </div>
            <ul class="navbar-nav">
                <li><b><a href="<%=request.getContextPath()%>/libraryElementList"
                          class="nav-link">Wyszukaj w zbiorze</a></b></li>
            </ul>
            <div class="loggedUser">
                <ul class="navbar-nav">
                    <c:if test="${sessionScope.userName == null}">
                        <li><b><a href="<%=request.getContextPath()%>/login"
                                  class="nav-link">Zaloguj</a></b></li>
                    </c:if>
                    <c:if test="${sessionScope.userName != null}">
                <span class="navbar-text">
                    Użytkownik: <c:out value="${sessionScope.userName}"/>
                </span>
                        <li><b><a href="<%=request.getContextPath()%>/logoutProcess"
                                  class="nav-link">Wyloguj</a></b></li>
                        <%-- <li class="nav-item dropdown">
                             <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                                role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                 Użytkownik: <c:out value="${sessionScope.userName}"/>
                             </a>
                             <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                 <a class="dropdown-item" href="#">Action</a>
                                 <a class="dropdown-item" href="#">Another action</a>
                                 <div class="dropdown-divider"></div>
                                 <a class="dropdown-item" href="#">Logout</a>
                             </div>
                         </li>--%>
                    </c:if>
                </ul>
            </div>
        </nav>
    </header>

</body>