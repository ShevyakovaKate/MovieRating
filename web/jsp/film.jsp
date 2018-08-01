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
    <title>Film</title>
    <link href="/css/jsp/film.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/jsp/pagepart/header.jsp"/>
<div class="container" style="margin-top: 4em">
    <div class="row">
        <div class="col-sm-4">

            <div class="fakeimg">Fake Image</div>
            <br>
            <h3>Raiting: <c:out value="${sessionScope.film.raiting}"/></h3>
            <h6>Director: <c:out value="${sessionScope.film.director}"/></h6>
            <h6>Genres:
                <c:forEach items="${sessionScope.film.genres}" var="genre">
                    ${genre.name}
                </c:forEach>
            </h6>
            <br>
            <hr class="d-sm-none">
        </div>
        <div class="col-sm-8">
            <h2><c:out value="${sessionScope.film.name}"/>, <c:out value="${sessionScope.film.yearOfIssue}"/></h2>
            <br>
            <h4><c:out value="${sessionScope.film.description}"/></h4>
            <br>
            <c:forEach items="${sessionScope.review_list}" var="review">
                <div class="card">
                    <div class="card-header">
                        <c:out value="${review.userName}"/>
                    </div>
                    <div class="card-body">
                        <blockquote class="blockquote mb-0">
                            <p><c:out value="${review.content}"/></p>
                            <footer class="blockquote-footer"><cite title="Source Title">Stars: <c:out value="${review.rating}"/></cite></footer>
                        </blockquote>
                    </div>
                </div>
                <br>
            </c:forEach>
           <%-- <div class="card">
                <div class="card-header">

                </div>
                <div class="card-body">
                    <blockquote class="blockquote mb-0">
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>
                        <footer class="blockquote-footer"><cite title="Source Title">Stars</cite></footer>
                    </blockquote>
                </div>
            </div>--%>

        </div>
    </div>
</div>

<jsp:include page="/jsp/pagepart/footer.jsp"/>
</body>
</html>
