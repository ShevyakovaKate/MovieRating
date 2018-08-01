<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:if test="${empty sessionScope.locale}">
    <c:set var="locale" value="en_US" scope="session"  />
</c:if>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/css/jsp/pagepart/header.css" rel="stylesheet">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/SingUpValidation.js"></script>

</head>
<body>
    <!-- Fixed navbar -->
    <nav class="navbar navbar-expand-md fixed-top navbar-dark bg-dark">
        <a class="navbar-brand" href="/controller?command=get_page&jsp_page=HOMEPAGE_JSP">Movie raiting</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <%--<div class="nav-item dropdown">
            <a class="btn btn-secondary dropdown-toggle" href="#" id="dropdownGenreList" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Genres</a>
            <div class="dropdown-menu" aria-labelledby="dropdownGenreList">
                <c:forEach items="${sessionScope.genre_list}" var="genre">
                <a class="dropdown-item" href="/controller?command=get_films_by_genre&genre_id=${genre.id}">
                        <c:out value="${genre.name}"/>
                    </a>
                </c:forEach>
            </div>
        </div>--%>

        <div class="collapse navbar-collapse" id="navbarCollapse">
                    <ul class="nav navbar-nav mr-center ml-md-auto">
                        <li class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" id="dropdownLanguageMenuList" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" ><fmt:message key="label.header.language" bundle="${rb}"/></a>

                            <div class="dropdown-menu" aria-labelledby="dropdownLanguageMenuList">
                                <a class="dropdown-item" href="/controller?command=locale&locale=RU">
                                    <fmt:message key="label.language.ru"  bundle="${rb}"/>
                                </a>

                                <a class=dropdown-item href="/controller?command=locale&locale=EN">
                                    <fmt:message key="label.language.en"  bundle="${rb}"/>
                                </a>
                            </div>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="#" data-toggle="modal" data-target="#loginModal"><fmt:message key="label.login.login_button" bundle="${rb}" /></a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="#" data-toggle="modal" data-target="#regisrtModal"><fmt:message key="label.registration.registration_button" bundle="${rb}"/></a>
                        </li>

                    </ul>

            </div>

    </nav>

    <%--MODAL LOGIN--%>
    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
         aria-labelledby="loginModal" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="loginModalLabel"><fmt:message key="label.login.header" bundle="${rb}"/></h5>
                    <button class="close" type="button" data-disniss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <form class="form-signin" name="loginForm" method="POST" action="/controller">
                            <input type="hidden" name="command" value="login" />

                            <label for="inputEmail"><fmt:message key="label.login.email" bundle="${rb}"/></label>
                            <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus><br>


                            <label for="inputPassword"><fmt:message key="label.login.password" bundle="${rb}"/></label>
                            <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required><br>


                            <button class="btn btn-lg btn-primary btn-block" type="submit">
                                <fmt:message key="label.login.login_button" bundle="${rb}"/></button><br>
                            <c:if test="${not empty sessionScope.errorMessage}">
                                <p id="errorMessage">{sessionScope.errorMessage}</p>
                                <c:remove var="errorMessage" scope="session" />
                            </c:if>
                        </form>
                     </div>
                </div>
            </div>
        </div>
    </div>
    <%--END MODAL LOGIN--%>

    <%--MODAL REGISTRATION--%>
    <div class="modal fade" id="regisrtModal" tabindex="-1" role="dialog"
         aria-labelledby="regisrtModal" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="regisrtModalLabel">Сreate account</h5>
                    <button class="close" type="button" data-disniss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <form class="form-signin" onsubmit="return validate(this.form)" name="registrationForm" method="POST" action="/controller">
                            <input type="hidden" name="command" value="sign_up" />

                            <div>
                                <label for="email"><fmt:message key="label.registration.email" bundle="${rb}"/></label>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">@</span>
                                    </div>
                                    <input type="email" name="email" class="form-control" id="email" placeholder="you@example.com" required>
                                    <div class="invalid-feedback" style="width: 100%;">
                                        Your email is required.
                                    </div>
                                </div>
                            </div>

                            <div >
                                <label for="username"><fmt:message key="label.registration.username" bundle="${rb}"/></label>
                                <div class="input-group">
                                    <input type="text" name="name" class="form-control" id="username" placeholder="Username" required pattern="^[a-zA-Zа-яА-Я0-9_-]{3,16}$">
                                    <div class="invalid-feedback" style="width: 100%;">
                                        Your username is required.
                                    </div>
                                </div>
                            </div>

                            <div >
                                <label  for="password"><fmt:message key="label.login.password" bundle="${rb}"/></label>
                                <div class="input-group">
                                    <input type="password" name="password" class="form-control" id="password" placeholder="Password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*[\W|_]).{8,20}"/>
                                    <div class="invalid-feedback" style="width: 100%;">
                                        The password must consist of Latin letters and numbers and must contain at least one large letter, one small letter and one number. And should be longer than 8 characters and shorter than 20.
                                    </div>
                                </div>
                            </div>

                            <div class="control-group">
                                <label for="passwordConfirm"><fmt:message key="label.registration.password.repeat" bundle="${rb}"/></label>
                                <div class="controls">
                                    <input type="password" id="PasswordConfirm" name="passwordConfirm" placeholder="Password repeat" class="form-control" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*[\W|_]).{8,20}"/>
                                    <div class="invalid-feedback" style="width: 100%;">
                                        Password mismatch.
                                    </div>
                                </div>
                            </div><br>

                            <button class="btn btn-primary btn-lg btn-block" type="submit"><fmt:message key="label.registration.registration_button" bundle="${rb}"/></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>



    <%--MODAL REGISTRATION END--%>

    </body>
</html>

