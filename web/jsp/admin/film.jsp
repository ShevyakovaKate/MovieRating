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
    <link href="/css/jsp/user/film.css" rel="stylesheet">
    <script type="text/javascript">
        <%@include file="/js/new-review.js"%>
    </script>

    <%--<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">--%>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.1/js/bootstrap.min.js">
    <link href="//code.jquery.com/jquery-1.11.1.min.js">
    <!------ Include the above in your HEAD tag ---------->


</head>
<body>
<jsp:include page="/jsp/admin/pagepart/header.jsp"/>
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
            <div>
                <a class="btn btn-success" id="toggleState" href="/controller?command=EDIT_FILM" >Edit film</a>
            </div>
            <br>


            <br>
            <hr class="d-sm-none">
        </div>
        <div class="col-sm-8">
            <h2><c:out value="${sessionScope.film.name}"/>, <c:out value="${sessionScope.film.yearOfIssue}"/></h2>
            <br>
            <h4><c:out value="${sessionScope.film.description}"/></h4>
            <br>

            <%--Add review box--%>
            <div class="card my-4">
                <c:if test="${not empty sessionScope.user.email}">
                    <h5 class="card-header">
                        Leave Review <%--<span> ${sessionScope.commentSaved}</span>--%>
                        <%--<c:remove var="commentSaved" scope="session" />--%>
                    </h5>

                    <div class="card-body">
                        <form id="review_form" action="/controller" method="POST">
                            <input type="hidden" name="command" value="add_review"/>

                            <div class="form-group">
                                <textarea class="form-control" rows="3" name="content"></textarea>
                            </div>
                            <div class="row">
                                <div class="line">
                                    <button type="submit" class="btn btn-success">Submit</button>
                                </div>

                                <div class="row justify-content-end line">
                                        <ul class="rate-area">
                                            <input type="radio" id="10-star" name="rating" value="10" required/><label for="5-star" title="Amazing">10</label>
                                            <input type="radio" id="9-star" name="rating" value="9" required/><label for="4-star" title="Good">9</label>
                                            <input type="radio" id="8-star" name="rating" value="8" required/><label for="3-star" title="Average">8</label>
                                            <input type="radio" id="7-star" name="rating" value="7" required/><label for="2-star" title="Not Good">7</label>
                                            <input type="radio" id="6-star" name="rating" value="6" required/><label for="1-star" title="Bad">6</label>

                                            <input type="radio" id="5-star" name="rating" value="5" required/><label for="5-star" title="Amazing">5</label>
                                            <input type="radio" id="4-star" name="rating" value="4" required/><label for="4-star" title="Good">4</label>
                                            <input type="radio" id="3-star" name="rating" value="3" required/><label for="3-star" title="Average">3</label>
                                            <input type="radio" id="2-star" name="rating" value="2" required/><label for="2-star" title="Not Good">2</label>
                                            <input type="radio" id="1-star" name="rating" value="1" required/><label for="1-star" title="Bad">1</label>

                                        </ul>
                                </div>
                            </div>

                        </form>
                    </div>
                </c:if>
            </div>






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


        </div>
    </div>
</div>

<jsp:include page="/jsp/pagepart/footer.jsp"/>

</body>
</html>
