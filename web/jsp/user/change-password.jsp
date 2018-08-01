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
    <title>Change password</title>
    <link href="/css/jsp/user/change-password.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/jsp/user/pagepart/header.jsp"/>
<div class="container theme-showcase" role="main">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <c:if test="${not empty requestScope.operationStatusPositive}">
                <div class="alert alert-success" role="alert"><fmt:message key="${requestScope.operationStatusPositive}" bundle="${rb}"/></div>
                <c:remove var="operationStatusPositive" scope="request" />
            </c:if>
            <c:if test="${not empty requestScope.operationStatusNegative}">
                <div class="alert alert-danger" role="alert"><fmt:message key="${requestScope.operationStatusNegative}" bundle="${rb}"/></div>
                <c:remove var="operationStatusNegative" scope="request" />
            </c:if>
            <form class="form-horizontal" action='/controller' method="POST">
                <input type="hidden" name="command" value="change_password" />
                <fieldset>
                    <br>
                    <div id="legend">
                        <h3>Change password</h3>
                    </div>

                    <div class="control-group">
                        <!-- New password-->
                        <label class="control-label" for="newpassword">New password</label>
                        <div class="controls">
                            <input type="password" id="newpassword" name="newPassword" placeholder="" class="form-control" required />
                            <p class="help-block">Some pattern</p>
                        </div>
                    </div>

                    <div class="control-group">
                        <!-- New password confirm-->
                        <label class="control-label" for="newpasswordconfirm">New password repeat</label>
                        <div class="controls">
                            <input type="password" id="newPasswordConfirm" name="newPasswordConfirm" placeholder="" class="form-control" required />
                            <p class="help-block">Some pattern</p>
                        </div>
                    </div>

                    <div class="control-group">
                        <!-- Password-->
                        <label class="control-label" for="password">Old password</label>
                        <div class="controls">
                            <input type="password" id="password" name="password" class="form-control" required />
                        </div>
                    </div>
                    <br/>

                    <div class="control-group">
                        <!-- Button -->
                        <div class="controls">
                            <button class="btn btn-success" type="submit">Change</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
<jsp:include page="/jsp/pagepart/footer.jsp"/>
</body>
</html>
