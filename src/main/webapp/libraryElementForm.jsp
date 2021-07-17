<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
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
<c:import url="NavigationBar.jsp" />
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${libraryElement != null}">
                <form action="update" method="post" accept-charset="character_set">
            </c:if>
            <c:if test="${libraryElement == null}">
                <form action="insert" method="post" accept-charset="character_set">
            </c:if>
                    <caption>
                        <h2>
                            <c:if test="${libraryElement != null}">
                                Edytuj element
                            </c:if>
                            <c:if test="${libraryElement == null}">
                                Dodaj element
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${libraryElement != null}">
                        <input type="hidden" name="libraryElementId"
                               value="<c:out value='${libraryElement.getLibraryElementId()}' />"/>
                        <input type="hidden" name="statusId"
                               value="<c:out value='${libraryElement.getStatusId()}' />"/>
                        <input type="hidden" name="typeId"
                               value="<c:out value='${libraryElement.getTypeId()}' />"/>
                    </c:if>

                    <fieldset class="form-group" accept-charset="utf-8">
                        <label>Tytuł</label> <input type="text"
                                                    value="<c:out value='${libraryElement.getTitle()}' />"
                                                    class="form-control"
                                                    name="title" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label for="sortId">Rodzaj</label>
                        <br>
                        <select id="sortId" name="sortId" class="form-control">
                            <option value="1" selected>Historyczne</option>
                            <option value="2" >Fantastyka</option>
                            <option value="3" >Kryminał</option>
                            <option value="4" >Edukacja</option>
                            <option value="5" >Technologie</option>
                        </select>
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