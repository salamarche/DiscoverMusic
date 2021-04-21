<%@include file="taglib.jsp"%>
<%@ page import="java.util.Properties" %>
<%@ page import="java.io.IOException" %>
<header>
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Nebula Loci</a>
            <div>
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


</header>


