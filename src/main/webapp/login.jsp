<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Panel logowania</title>
    <title>User Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

    <header>
        <nav class="navbar navbar-expand-md navbar-dark"
             style="background-color: #47a0ff">
            <div>
                <a href="<%=request.getContextPath()%>/index.jsp" class="navbar-brand"> Strona główna </a>
            </div>
            <ul class="navbar-nav">
                <li><b><a href="<%=request.getContextPath()%>/libraryElementList"
                          class="nav-link" >Wyszukaj w zbiorze</a></b></li>
                <li><a href="<%=request.getContextPath()%>/login"
                       class="nav-link">Login</a></li>
            </ul>
        </nav>
    </header>
    <br>
    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <form action="loginProcess" method="post">
                    <caption>
                        <h2> Panel Logowania </h2>
                    </caption>
                    <fieldset class="form-group">
                        <label>Login</label>
                        <input type="text" class="form-control" name="login" required="required">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Hasło</label>
                        <input type="password" class="form-control" name="password" required="required">
                    </fieldset>
                    <button type="submit" class="btn btn-success">Zaloguj</button>
                </form>
            </div>
        </div>
    </div>

</body>
</html>
