<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
    <title>Quiz</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="/log" onclick="window.location.href='/log'">HOME</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/userdetail" onclick="window.location.href='/userdetail'">Twoje dane<span class="sr-only">(current)</span></a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="/quiz1" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Wybierz quiz
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="/quiz1/Javaobjectoriented" onclick="window.location.href='/quiz1/Javaobjectoriented'" id="Java Object Oriented">Java Object Oriented</a>
                    <a class="dropdown-item" href="/quiz1/Collections" onclick="window.location.href='/quiz1/Collections'" id="Collections">Collections</a>
                    <a class="dropdown-item" href="/quiz1/Databases" onclick="window.location.href='/quiz1/Databases'" id="Databases">Databases</a>
                </div>
            </li>
            <li>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <h2>Welcome : ${pageContext.request.userPrincipal.name}</h2>
                </c:if>
            </li>
        </ul>
    </div>

    <!-- naval with buttons -->
    <div class="btn-group" role="group" aria-label="Basic example">
        <button type="button" class="btn btn-secondary" onclick="window.location.href='/logout'">Wyloguj się</button>
    </div>
</nav>



<div align="center">
    <h1>USER DETAILS</h1>
</div>

<div align="center">
    <table border="1">
        <thead>
        <th>email</th>
        <th>gender</th>
        <th>country</th>
        </thead>
        <tbody>

            <tr bgcolor="#ffffff">
                <td>${response.email}</td>
                <td>${response.gender}</td>
                <td>${response.country}</td>
            </tr>

        </tbody>
    </table>
    <br>
    <br><br>
</div>
<div align="center">
    <form action="/adduserdetails" method="post">
        Email <input type="text" name="email"><br><br>
        Gender <input type="text" name="gender"><br><br>
        Country <input type="text" name="country"><br><br>
        <input type="submit" value="add">
    </form>
    <br><br>
    <a href="/log">home page</a>

</div>

</body>
</html>