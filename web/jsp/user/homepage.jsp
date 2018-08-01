<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:if test="${empty sessionScope.locale}">
    <c:set var="locale" value="en_US" scope="session"  />
</c:if>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb" />
<html>
<head>
    <title>Homepage</title>
</head>
<body>
<jsp:include page="/jsp/user/pagepart/header.jsp"/>
<main role="main" style="margin-top: 3em">
    <c:if test="${not empty requestScope.operationStatusPositive}">
        <div class="alert alert-success" role="alert"><fmt:message key="${requestScope.operationStatusPositive}" bundle="${rb}"/></div>
        <c:remove var="operationStatusPositive" scope="request" />
    </c:if>
    <c:if test="${not empty requestScope.operationStatusNegative}">
        <div class="alert alert-danger" role="alert"><fmt:message key="${requestScope.operationStatusNegative}" bundle="${rb}"/></div>
        <c:remove var="operationStatusNegative" scope="request" />
    </c:if>
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselIndicators" data-slide-to="1"></li>
            <li data-target="#carouselIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
            <div class="carousel-item active">
                <img class="d-block w-100" src="/img/drama.jpg" alt="First slide">
                <div class="carousel-caption d-none d-md-block text-left">
                    <%--<h1>Drama</h1>
                    <p>Life is a drama, boring excerpts were thrown out. Rate movies of this genre</p>--%>
                    <p><a class="btn btn-lg btn-primary" href="/controller?command=search_film&genre_id=3&searching_text=" role="button">View the drama films</a></p>
                </div>
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="/img/carousel-1.jpg" alt="Second slide">
                <div class="carousel-caption d-none d-md-block">
                    <%-- <h1>Best movies</h1>
                     <p> Films that have the highest rating on the site, recognized as the best critics. Rate the list.</p>--%>
                    <p><a class="btn btn-lg btn-primary" href="/controller?command=get_films_by_raiting" role="button">Highest ratings</a></p>
                </div>
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="/img/kino-news.jpg" alt="Third slide">
                <div class="carousel-caption d-none d-md-block text-right">
                    <%-- <h5>Novelties</h5>
                     <p>Novelties of the world of cinema. The latest movies, the hottest reviews.</p>--%>
                    <p><a class="btn btn-lg btn-primary" href="/controller?command=get_films_by_year&year=2018" role="button">Novelties</a></p>
                </div>
            </div>
        </div>
        <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>

    <main role="main" class="inner cover" style="margin: 4em;">
        <h1 class="cover-heading">WRITE HERE!!!</h1>
        <p class="lead">
            <a href="/controller?command=get_films_by_raiting" class="btn btn-lg btn-secondary">Go to searching</a>
        </p>
    </main>


</main><!-- /.container -->
<jsp:include page="/jsp/pagepart/footer.jsp"/>

</body>
</html>
