
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bookmarks</title>
</head>
<body>
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
<jsp:include page="/jsp/user/pagepart/header.jsp"/>
<main role="main" style="margin-top: 4em">

    <div class="row justify-content-center">
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


    </div>


    <div class="album py-5">
        <div class="container">
            <div class="row">
                <c:forEach items="${requestScope.bookmark_list}" var="film">
                    <div class="col-md-4">
                        <div class="card mb-4 box-shadow">
                            <img class="card-img-top" src="/img/film-element.jpg"  alt="Card image cap">
                            <div class="card-body">
                                <p class="card-text"><c:out value="${film.name}"/></p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <a class="btn btn-sm btn-outline-secondary" href="/controller?command=GET_FILM&film_id=${film.id}" role="button">Veiw</a>
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
            <c:if test="${requestScope.current_page > 1}">
                <li class="page-item">
                    <a class="page-link" href="${requestScope.urlForPagination}&current_page=${requestScope.current_page - 1}">Prev</a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                <li class="page-item"><a class="page-link" href="${requestScope.urlForPagination}&current_page=${i}">${i}</a></li>
            </c:forEach>

            <c:if test="${requestScope.current_page < requestScope.noOfPages}">
                <li class="page-item">
                    <a class="page-link" href="${requestScope.urlForPagination}&current_page=${requestScope.current_page + 1}">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
</main>
<jsp:include page="/jsp/pagepart/footer.jsp"/>
</body>
</html>

</body>
</html>
