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
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User-Home</title>
    <link href="/css/jsp/user/profile.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/jsp/user/pagepart/header.jsp"/>
<main>
    <div class="col-md-4" >
    <c:if test="${not empty sessionScope.operationStatusPositive}">
        <div class="alert alert-success" role="alert"><fmt:message key="${sessionScope.operationStatusPositive}" bundle="${rb}"/></div>
        <c:remove var="operationStatusPositive" scope="session" />
    </c:if>
    <c:if test="${not empty sessionScope.operationStatusNegative}">
        <div class="alert alert-danger" role="alert"><fmt:message key="${sessionScope.operationStatusNegative}" bundle="${rb}"/></div>
        <c:remove var="operationStatusNegative" scope="session" />
    </c:if>
    </div>
    <div class="card col-md-3" id="line">
        <div class="card-body">

            <img class="card-img-top" src="/img/default-user-profile.png" alt="">

            <div class="profile-usertitle">
                <br>
                <div>
                    <h3>${sessionScope.user.name}</h3>
                </div>
                <br>
                <div>
                    <h4><fmt:message key="label.profile.raiting" bundle="${rb}"/> ${sessionScope.user.raiting}</h4>

                    <h4><fmt:message key="label.profile.reviews" bundle="${rb}"/> %%</h4>
                </div>
            </div>
        </div>
        <!-- END SIDEBAR USER TITLE -->
        <!-- SIDEBAR BUTTONS -->
        <div class="profile-userbuttons">

            <a class="btn btn-success" href="/controller?command=get_page&jsp_page=CHANGE_INFO_JSP" ><fmt:message key="label.account.edituser" bundle="${rb}"/></a>
        </div>
        <!-- END SIDEBAR BUTTONS -->
    </div>

    <div class="card col-md-6" id="line">
        <div class="card-body">
            <br>
            <h5><fmt:message key="label.registration.birthday" bundle="${rb}"/> ${sessionScope.user.birthday}</h5>
            <h5><fmt:message key="label.registration.email" bundle="${rb}"/> ${sessionScope.user.email}</h5>
            <h5><fmt:message key="label.registration.city" bundle="${rb}"/> ${sessionScope.user.city}</h5>
            <h5><fmt:message key="label.registration.info" bundle="${rb}"/> ${sessionScope.user.info}</h5>

        </div>
    </div>

</main>
<jsp:include page="/jsp/pagepart/footer.jsp"/>
</body>
</html>