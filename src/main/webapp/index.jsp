<%@include file="taglib.jsp"%>
<%@include file="nav.jsp"%>



<header class="masthead">
    <div class="container d-flex h-100 align-items-center">
        <div class="mx-auto text-center">
            <h1 class="mx-auto my-0 text-uppercase"></h1>
            <h2 class="text-white mx-auto mt-2 mb-5">Find Artists In Your Backyard</h2>
            <a class="btn btn-primary js-scroll-trigger" href="#about">About</a>
        </div>
    </div>
</header>


<section class="projects-section bg-light" id="recentArtists">
    <div class="row" >
        <div class="mx-auto">
        <c:if test="${fn:length(discoveredArtists) > 0}">
            <h2>Recently Discovered Artists</h2>
            <c:forEach items="${discoveredArtists}" var="artist">
                <h3>${artist.artistName}</h3>
            </c:forEach>
        </c:if>

        </div>
    </div>

    <div class="row" >
        <div class="col-lg-8 mx-auto">
            <a class="btn btn-primary js-scroll-trigger" href="discover">Discover Artists</a>
            <a class="btn btn-primary js-scroll-trigger" href="spotifLokal">Contribute</a>
        </div>
    </div>

</section>

    </div>

<%@include file="footer.jsp"%>
