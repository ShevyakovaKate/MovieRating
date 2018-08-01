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
    <title>Add film</title>
</head>
<body>
<jsp:include page="/jsp/admin/pagepart/header.jsp"/>

<div class="container theme-showcase" role="main" style="margin-top: 4em">
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
                <h2>Add film</h2>
            </div>
            <form class="form-horizontal" action='/controller' method="POST">
                <input type="hidden" name="command" value="add_film" />
                <%--<input type="hidden"  name="rating" value="0" />--%>
                <fieldset>

                    <div class="control-group">
                        <!-- Film name -->
                        <label class="control-label" for="name">Film name: </label>
                        <div class="controls">
                            <input type="name" id="name" name="name" class="form-control" placeholder="Name of film" required />
                        </div>
                    </div>

                    <div class="control-group">
                        <!-- Year of issue -->
                        <label class="control-label" for="year">Year of issue:</label>
                        <div class="controls">
                            <input type="text" id="year" name="year" class="form-control" placeholder="YYYY" required pattern="^(19|20)\d{2}$"/>
                            <p>From 1900 to 2099</p>
                        </div>
                    </div>

                    <div class="control-group">
                        <!-- Director -->
                        <label class="control-label" for="director">Director:</label>
                        <div class="controls">
                            <input type="text" id="director" class="form-control" name="director"  required/>
                        </div>
                    </div>

                    <div class="control-group">
                        <!-- Description -->
                        <label class="control-label" for="description">Description:</label>
                        <div class="controls">
                            <textarea class="form-control" id="description" rows="3" name="description" required></textarea>
                        </div>
                    </div>

                    <br/>
                    <div class="control-group">
                        <!-- Button -->
                        <div class="controls ">
                            <button class="btn btn-success btn-space" type="submit">Add</button>
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
