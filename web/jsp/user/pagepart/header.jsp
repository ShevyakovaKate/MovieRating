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
    <link href="/css/jsp/header.css" rel="stylesheet">
</head>
<body>
<!-- Fixed navbar -->
<nav class="navbar navbar-expand-md fixed-top navbar-dark bg-dark">
    <a class="navbar-brand" href="/controller?command=get_page&jsp_page=USER_HOMEPAGE_JSP">Movie raiting</a>
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
                <a class="nav-link" href="/controller?command=GET_PAGE&jsp_page=USER_PROFILE_JSP">My profile</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/controller?command=GET_BOOKMARKS&user_id=${sessionScope.user.id}">Bookmarks</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/controller?command=LOGOUT">Logout</a>
            </li>
        </ul>

    </div>

</nav>

</body>
</html>


