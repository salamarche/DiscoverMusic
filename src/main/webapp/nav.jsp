<%@include file="taglib.jsp"%>
<%@ page import="java.util.Properties" %>
<%@ page import="java.io.IOException" %>

<%@include file="head.jsp"%>

<body>
    <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
        <div class="container">
            <a class="navbar-brand" href="">Backyard Music</a>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                Menu
                <i class="fas fa-bars"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav">
                    <li class="nav-item"><a class="nav-link" href="discover">Discover Artists</a></li>
                    <li class="nav-item"><a class="nav-link" href="spotifLokal">Contribute</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">About</a></li>

                    <c:if test="${username == null}">
                        <li class="nav-item"><a class="nav-link" href="login">Sign In</a></li>
                    </c:if>

                    <c:if test="${username != null}">
                        <li class="nav-item"><a class="nav-link" href="user">My Collection</a></li>
                        <li class="nav-item"><a class="nav-link" href="logout">Log Out</a></li>
                    </c:if>

                </ul>
            </div>

        </div>
    </nav>







