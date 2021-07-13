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
<br>
<div class="container col-md-5">
  <div class="card">
    <div class="card-body">
      <c:if test="${user != null}">
      <form action="update" method="post">
        </c:if>
        <c:if test="${user == null}">
        <form action="insert" method="post">
          </c:if>

          <caption>
            <h2>
              <c:if test="${user != null}">
                Edit User
              </c:if>
              <c:if test="${user == null}">
                Add New User
              </c:if>
            </h2>
          </caption>

          <c:if test="${user != null}">
            <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
          </c:if>

          <fieldset class="form-group">
            <label>User Name</label> <input type="text"
                                            value="<c:out value='${user.name}' />" class="form-control"
                                            name="name" required="required">
          </fieldset>

          <fieldset class="form-group">
            <label>User Email</label> <input type="text"
                                             value="<c:out value='${user.email}' />" class="form-control"
                                             name="email">
          </fieldset>

          <fieldset class="form-group">
            <label>User Country</label> <input type="text"
                                               value="<c:out value='${user.country}' />" class="form-control"
                                               name="country">
          </fieldset>

          <button type="submit" class="btn btn-success">Save</button>
        </form>
    </div>
  </div>
</div>
</body>
</html>