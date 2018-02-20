<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script
        src="https://code.jquery.com/jquery-3.2.1.min.js"
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous">
</script>

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


<%--   NAV   --%>
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
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Wybierz quiz
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="/quiz1/Javaobjectoriented" onclick="window.location.href='/quiz1'" id="Java Object Oriented">Java Object Oriented</a>
                    <a class="dropdown-item" href="/quiz1/Collections" onclick="window.location.href='/quiz1'" id="Collections">Collections</a>
                    <a class="dropdown-item" href="/quiz1/Databases" onclick="window.location.href='/quiz1'" id="Databases">Databases</a>
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




<c:forEach var="questions" items="${questions}">
    <div class="container-fluid bg-info" >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3><span class="label label-warning" id="qid">${questions.question}</span> </h3>
                </div>
                <div class="modal-body">
                    <div class="col-xs-3 col-xs-offset-5">
                        <div id="loadbar" style="display: none;">
                            <div class="blockG" id="rotateG_01"></div>
                            <div class="blockG" id="rotateG_02"></div>
                            <div class="blockG" id="rotateG_03"></div>
                            <div class="blockG" id="rotateG_04"></div>
                            <div class="blockG" id="rotateG_05"></div>
                            <div class="blockG" id="rotateG_06"></div>
                            <div class="blockG" id="rotateG_07"></div>
                            <div class="blockG" id="rotateG_08"></div>
                        </div>
                    </div>

                    <div class="quiz" id="quiz" data-toggle="buttons">
                        <label id="answerA${questions.id}" onclick="findCorrectAnswer('${questions.id}', 'A')" class="element-animation1 btn btn-lg btn-primary btn-block"><span class="btn-label"><i class="glyphicon glyphicon-chevron-right"></i></span> <input type="radio" name="q_answer" value="1">A: ${questions.answer_a}</label>
                        <label id="answerB${questions.id}" onclick="findCorrectAnswer('${questions.id}', 'B')" class="element-animation2 btn btn-lg btn-primary btn-block"><span class="btn-label"><i class="glyphicon glyphicon-chevron-right"></i></span> <input type="radio" name="q_answer" value="2">B: ${questions.answer_b}</label>
                        <label id="answerC${questions.id}" onclick="findCorrectAnswer('${questions.id}', 'C')" class="element-animation3 btn btn-lg btn-primary btn-block"><span class="btn-label"><i class="glyphicon glyphicon-chevron-right"></i></span> <input type="radio" name="q_answer" value="3">C: ${questions.answer_c}</label>
                        <label id="answerD${questions.id}" onclick="findCorrectAnswer('${questions.id}', 'D')" class="element-animation4 btn btn-lg btn-primary btn-block"><span class="btn-label"><i class="glyphicon glyphicon-chevron-right"></i></span> <input type="radio" name="q_answer" value="4">D: ${questions.answer_d}</label>
                    </div>
                </div>
                <div class="modal-footer text-muted">
                    <span id="answer"></span>
                </div>
            </div>
        </div>
    </div>
</c:forEach>

<div align="center"><a href="/log">Powrot</a></div>

</body>
</html>


<script>
    var findCorrectAnswer = function (id, answer) {
        $.ajax({
            url: '/correct/answer',
            type: 'GET',
            data: {
                id: id,
                answer: answer
            },
            success: function (response) {
                if(response) {
                    $('#answer'+answer+id).css("background", "green");
                } else {
                    $('#answer'+answer+id).css("background", "red");
                }
            }
        });
    }
</script>




