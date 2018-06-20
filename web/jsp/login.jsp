
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty sessionScope.locale}">
    <c:set var="locale" value="en_US" scope="session"  />
</c:if>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb" />


<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Login</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">


</head>

<body class="text-center">
<ul class="navbar-nav right">
    <li class="nav-item">
        <form action="/controller">
            <input type="hidden" name="command" value="locale">
            <button type="submit" class="btn btn-default navbar-btn" name="locale" value="RU" >RU</button>
        </form>
    </li>
    <li class="nav-item">
        <form action="/controller">
            <input type="hidden" name="command" value="locale">
            <button type="submit" class="btn btn-default navbar-btn" name="locale" value="EN" >EN</button>
        </form>
    </li>
</ul>
<div class="container-fluid bg" role="main" id="loginForm">
    <div class="row">
        <div class="col-md-4 col-ms-4 col-xs-12"></div>
        <div class="col-md-4 col-ms-4 col-xs-12">

            <form class="form-signin" name="loginForm" method="POST" action="/controller">
                <input type="hidden" name="command" value="login" />
                <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="label.login.header" bundle="${rb}"/></h1>
                <label for="inputEmail" class="sr-only"><fmt:message key="label.login.email" bundle="${rb}"/></label>
                <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
                <label for="inputPassword" class="sr-only"><fmt:message key="label.login.password" bundle="${rb}"/></label>
                <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>

                <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="label.login.login_button" bundle="${rb}" /></button>
                <br>
                <h2>
                    Go to
                    <a href="registration.jsp"> registration </a>
                </h2>

                <c:if test="${not empty sessionScope.errorMessage}">
                    <p id="errorMessage">{sessionScope.errorMessage}</p>
                    <c:remove var="errorMessage" scope="session" />
                </c:if>
            </form>

        </div>>
        <div class="col-md-4 col-ms-4 col-xs-12"></div>
    </div>
</div>
<div class="col-md-4 col-ms-4 col-xs-12"></div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
</body>
</html>
