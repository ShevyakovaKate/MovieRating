<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 24.06.2018
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie rating</title>

</head>
<body>
    <jsp:include page="/jsp/pagepart/header.jsp"/>
        <main role="main" class="container-fluid" style="margin-top: 4em">
            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselIndicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img class="d-block w-100" src="img/carousel-1.jpg" alt="First slide">
                        <div class="carousel-caption d-none d-md-block">
                            <h5>.lSDvnkjl;SK</h5>
                            <p>AWKGNBJLASKJ</p>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="img/carousel-1.jpg" alt="Second slide">
                        <div class="carousel-caption d-none d-md-block">
                            <h5>.lSDvnkjl;SK</h5>
                            <p>AWKGNBJLASKJ</p>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="img/carousel-1.jpg" alt="Third slide">
                        <div class="carousel-caption d-none d-md-block">
                            <h5>.lSDvnkjl;SK</h5>
                            <p>AWKGNBJLASKJ</p>
                        </div>
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </main><!-- /.container -->
    <jsp:include page="/jsp/pagepart/footer.jsp"/>
</body>
</html>
