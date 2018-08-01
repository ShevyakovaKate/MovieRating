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
    <title>Movie list</title>
</head>
<body>
<jsp:include page="/jsp/admin/pagepart/header.jsp"/>
<main role="main" style="margin-top: 4em">

    <div class="row justify-content-center">
        <c:if test="${not empty sessionScope.operationStatusPositive}">
            <div class="alert alert-success" role="alert"><fmt:message key="${sessionScope.operationStatusPositive}" bundle="${rb}"/></div>
            <c:remove var="operationStatusPositive" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.operationStatusNegative}">
            <div class="alert alert-danger" role="alert"><fmt:message key="${sessionScope.operationStatusNegative}" bundle="${rb}"/></div>
            <c:remove var="operationStatusNegative" scope="session" />
        </c:if>
        <form  class="form-inline mt-2 mt-md-0" name="searchForm" method="POST" action="/controller">
            <input type="hidden" name="command" value="search_film" />
            <div class="btn-toolbar">
                <div class="input-group">
                    <select class="custom-select" name="genre_id" id="selectGenre">
                        <option selected value="any">Any</option>
                        <c:forEach items="${sessionScope.genre_list}" var="genre">
                            <option value="${genre.id}"><c:out value="${genre.name}"/></option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <input class="form-control mr-sm-2" type="text" name="searching_text" placeholder="Searching..." aria-label="Search">
                </div>
                <div>
                    <button type="submit" class="btn btn-default">
                        Search
                    </button>
                </div>

            </div>

        </form>
        <div>
            <a class="btn btn-success" href="/controller?command=get_page&jsp_page=ADD_FILM">Add new film</a>
        </div>




    </div>


    <div class="album py-5">
        <div class="container">
            <div class="row">
                <c:forEach items="${sessionScope.film_list}" var="film">
                    <div class="col-md-4">
                        <div class="card mb-4 box-shadow">
                            <img class="card-img-top" src="/img/film-element.jpg"  alt="Card image cap">
                            <div class="card-body">
                                <p class="card-text"><c:out value="${film.name}"/></p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <a class="btn btn-sm btn-outline-secondary" href="/controller?command=GET_FILM&film_id=${film.id}" role="button">Veiw</a>
                                        <a class="btn btn-sm btn-outline-secondary" href="/controller?command=DELETE_FILM&film_id=${film.id}" role="button">Delete</a>
                                    </div>
                                    <div>
                                        <img class="img-responsive" src="/img/star.png" alt="Star raiting">
                                        <h5 class="text-muted">${film.raiting}</h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <c:if test="${sessionScope.current_page > 1}">
                    <li class="page-item">
                        <a class="page-link" href="${sessionScope.urlForPagination}&current_page=${sessionScope.current_page - 1}">Prev</a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${sessionScope.noOfPages}" var="i">
                    <li class="page-item"><a class="page-link" href="${sessionScope.urlForPagination}&current_page=${i}">${i}</a></li>
                </c:forEach>

                <c:if test="${sessionScope.current_page < sessionScope.noOfPages}">
                    <li class="page-item">
                        <a class="page-link" href="${sessionScope.urlForPagination}&current_page=${sessionScope.current_page + 1}">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>
</main>
<jsp:include page="/jsp/pagepart/footer.jsp"/>
</body>
</html>
