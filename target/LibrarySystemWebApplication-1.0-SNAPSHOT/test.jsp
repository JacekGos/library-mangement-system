<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    Testowa strona:
    <br>
    <c:out value="${libraryUser.getUserName()}"/>

</body>
</html>
