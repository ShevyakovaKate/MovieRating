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

    <title>Sign Up</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
</head>


<body class="text-center">
<div class="container-fluid bg" role="main" id="loginForm">
    <div class="row">
        <div class="col-md-4 col-ms-4 col-xs-12"></div>
        <div class="col-md-4 col-ms-4 col-xs-12">

            <!------ Include the above in your HEAD tag ---------->
            <form class="form-signin" name="registrationForm" method="POST" action="/controller">
                <input type="hidden" name="command" value="sign_up" />
                <fieldset>
                    <div id="legend">
                        <legend class=""><fmt:message key="label.registration.header" bundle="${rb}"/></legend>
                    </div>
                    <div class="control-group">
                        <!-- Username -->

                        <label class="control-label"  for="username"><fmt:message key="label.registration.name" bundle="${rb}"/></label>
                        <div class="controls">
                            <input type="text" id="username" name="name" placeholder="" class="input-xlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <!-- E-mail -->
                        <label class="control-label" for="email"><fmt:message key="label.registration.email" bundle="${rb}"/></label>
                        <div class="controls">
                            <input type="text" id="email" name="email" placeholder="" class="input-xlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <!-- Password-->
                        <label class="control-label" for="password"><fmt:message key="label.registration.password" bundle="${rb}"/></label>
                        <div class="controls">
                            <input type="password" id="password" name="password" placeholder="" class="input-xlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <!-- Button -->
                        <div class="controls">
                            <button class="btn btn-success"><fmt:message key="label.registration.registration_button" bundle="${rb}"/></button>
                        </div>
                    </div>
                </fieldset>
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
