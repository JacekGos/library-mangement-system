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

<c:import url="navigationBar.jsp" />

<div class="row">
  <div class="container">
    <h3 class="text-center">Opcje użytkowników</h3>
    <hr>
    <br>
    <table class="table table-bordered">
      <thead>
      <tr bgcolor="#68C967">
        <th>ID</th>
        <th>Imie</th>
        <th>Nazwisko</th>
        <th>Nazwa użytkownika</th>
        <th>Naliczone kary</th>
        <th>Opcje</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="object" items="${libraryUserList}">
        <tr>
          <td><c:out value="${object.getUserId()}"/></td>
          <td><c:out value="${object.getUserName()}"/></td>
          <td><c:out value="${object.getUserSurName()}"/></td>
          <td><c:out value="${object.getLogin()}"/></td>
          <td><c:out value="${object.getPenalty()}"/></td>

          <td><a href="borrow?libraryElementId=<c:out value='' />">Szczegóły</a>
            <a href="borrow?libraryElementId=<c:out value='' />">Usuń</a>
          </td>

        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>
<div class="row">
  <div class="container">
    <form action="searchUser" method="get">
      <fieldset class="form-group">
        <input type="text" class="form-control" name="searchedUserId">
      </fieldset>
      <div class="container text-center">
        <button type="submit" class="btn btn-success">Wyszukaj</button>
      </div>
    </form>
  </div>
</div>
</body>
</html>