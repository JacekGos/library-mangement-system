<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    Testowa strona:
    <br>
    <c:out value="${borrowing.getBorrowingId()}"/><br>
    <c:out value="${borrowing.getLibraryElementId()}"/><br>
    <c:out value="${borrowing.getBorrowingDate()}"/><br>
    <c:out value="${borrowing.getStatusName()}"/><br>


</body>
</html>
