<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:if test="${empty sessionScope.locale}">
    <c:set var="locale" value="en_US" scope="session"  />
</c:if>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb" />

<c:set value="${sessionScope.user}" var="user" scope="session"/>

<html>
<head>
    <title>Change information</title>
</head>
<body>
<jsp:include page="/jsp/user/pagepart/header.jsp"/>
<div class="container theme-showcase" role="main">
    <div class="row">
        <div class="col-md-4">

        </div>
        <div class="col-md-4">
            <c:if test="${not empty requestScope.operationStatusPositive}">
                <div class="alert alert-success" role="alert"><fmt:message key="${requestScope.operationStatusPositive}" bundle="${rb}"/></div>
                <c:remove var="operationStatusPositive" scope="request" />
            </c:if>
            <c:if test="${not empty requestScope.operationStatusNegative}">
                <div class="alert alert-danger" role="alert"><fmt:message key="${requestScope.operationStatusNegative}" bundle="${rb}"/></div>
                <c:remove var="operationStatusNegative" scope="request" />
            </c:if>
            <br>
            <div>
                <h2>Edit user</h2>
            </div>
            <form class="form-horizontal" action='/controller' method="POST">
                <input type="hidden" name="command" value="change_personal_info" />
                <fieldset>

                    <div class="control-group">
                        <!-- E-mail -->
                        <label class="control-label" for="email"><fmt:message key="label.registration.email" bundle="${rb}"/></label>
                        <div class="controls">
                            <input type="email" id="email" name="email" required="" class="form-control" placeholder="email@email.com" value="${sessionScope.user.email}" />
                            <p class="help-block"><fmt:message key="label.registration.email.help" bundle="${rb}"/></p>
                        </div>
                    </div>

                    <div class="control-group">
                        <!-- Name -->
                        <label class="control-label" for="name"><fmt:message key="label.registration.name" bundle="${rb}"/></label>
                        <div class="controls">
                            <input type="text" id="name" name="name" required="" class="form-control" placeholder="Игорь" value="${sessionScope.user.name}" />
                            <p class="help-block"><fmt:message key="label.registration.name.help" bundle="${rb}"/></p>
                        </div>
                    </div>

                    <div class="control-group">
                        <!-- Birthday -->
                        <label class="control-label" for="birthday"><fmt:message key="label.registration.birthday" bundle="${rb}"/></label>
                        <div class="controls">
                            <input type="date" id="birthday" name="birthday" class="form-control"  value="${sessionScope.user.birthday}"/>
                            <p class="help-block"><fmt:message key="label.registration.surname.help" bundle="${rb}"/></p>
                        </div>
                    </div>

                    <div class="control-group">
                        <!-- City -->
                        <label class="control-label" for="city"><fmt:message key="label.registration.city" bundle="${rb}"/></label>
                        <div class="controls">
                            <input type="text" class="form-control" id="city" name="city"  value="${sessionScope.user.city}" placeholder="Минск" />
                            <p class="help-block"><fmt:message key="label.registration.city.help" bundle="${rb}"/></p>
                        </div>
                    </div>

                    <div class="control-group">
                        <!-- Info -->
                        <label class="control-label" for="info"><fmt:message key="label.registration.info" bundle="${rb}"/></label>
                        <div class="controls">
                            <input type="text" id="info" name="info"  value="${sessionScope.user.info}" class="form-control" placeholder="I'm a movie fan" />
                            <p class="help-block"><fmt:message key="label.registration.info.help" bundle="${rb}"/></p>
                        </div>
                    </div>


                    <br/>
                    <div class="control-group">
                        <!-- Button -->
                        <div class="controls ">
                            <button class="btn btn-success btn-space" type="submit"><fmt:message key="label.account.changepass.changebutton" bundle="${rb}"/></button>

                            <a class="btn btn-success" href="/controller?command=get_page&jsp_page=CHANGE_PASSWORD_JSP"><fmt:message key="label.account.changepass.changepass" bundle="${rb}"/></a>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
        <div class="col-md-4">

        </div>
    </div>
</div>
<jsp:include page="/jsp/pagepart/footer.jsp"/>

</body>
</html>
