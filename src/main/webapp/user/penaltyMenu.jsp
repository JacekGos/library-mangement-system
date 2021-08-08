<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Kary</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">

</head>
<body>


<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>
<c:import url="../navigationBar.jsp" />

<div class="container col-md-5" align="center">
    <div class="card">
        <div class="card-body" >
            <table class="table table-bordered">
                <thead>
                <tr bgcolor="#8edbf9">
                    <th>Kara za przetrzymanie</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><c:out value="${userPenalty}"/> [zł]</td>
                </tr>
                </tbody>
            </table>
            <form action="regulatePenalty" method="post">
                <input type="hidden" name="libraryUserId"
                       value="<c:out value='${libraryUserId}' />"/>
                <fieldset class="form-group">
                    <label>Kwota oddana</label> <input type="text"
                                               class="form-control"
                                               name="returnedAmount" required="required">
                </fieldset>
                <div class="container text-center">
                    <button type="submit" class="btn btn-success" style="background-color: #8edbf9; color: black">Potwierdź</button>
                </div>
            </form>
            <c:if test="${isDataIncorrect}">
                <div class="card text-white bg-danger mb-3">
                    <div class="card-body">
                        <c:forEach var="message" items="${errorMessageList}">
                            <c:out value="${message}"/><br>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
