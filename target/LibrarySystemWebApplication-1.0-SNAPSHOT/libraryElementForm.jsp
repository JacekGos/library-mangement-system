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
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${libraryElement != null}">
                <form action="update" method="post">
            </c:if>
            <c:if test="${libraryElement == null}">
                <form action="insert" method="post">
            </c:if>
                    <caption>
                        <h2>
                            <c:if test="${libraryElement != null}">
                                Edytuj element <c:out value='${libraryElement.getLibraryElementId()}'/>
                            </c:if>
                            <c:if test="${libraryElement == null}">
                                Dodaj element
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${libraryElement != null}">
                        <input type="hidden" name="libraryElementId"
                               value="<c:out value='${libraryElement.getLibraryElementId()}' />"/>
                    </c:if>

                    <fieldset class="form-group">
                        <label>Tytuł</label> <input type="text"
                                                    value="<c:out value='${libraryElement.getTitle()}' />"
                                                    class="form-control"
                                                    name="title" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Rodzaj</label> <input type="text"
                                                     value="<c:out value='${libraryElement.getSortName()}' />"
                                                     class="form-control" name="sort">
                    </fieldset>

                    <c:if test="${libraryElement.getTypeId() == 1}">
                        <fieldset class="form-group">
                            <label>Liczba stron</label> <input type="text"
                                                               value="<c:out value='${libraryElement.getPagesNumber()}' />"
                                                               class="form-control" name="pagesNumber">
                        </fieldset>
                    </c:if>
                    <c:if test="${libraryElement.getTypeId() == 2}">
                        <fieldset class="form-group">
                            <label>Czas trwania</label> <input type="text"
                                                               value="<c:out value='${libraryElement.getDurationTime()}' />"
                                                               class="form-control" name="durationTime">
                        </fieldset>
                    </c:if>
                    <button type="submit" class="btn btn-success">Zapisz</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>