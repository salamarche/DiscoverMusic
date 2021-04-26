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
    <div class="container">


    <c:if test="${fn:length(discoveredArtists) > 0}">
    <div class="row">
        <h2>Recently Discovered Artists</h2>
    </div>

    <div class="row" >
        <c:forEach items="${discoveredArtists}" var="artist">
        <div class="col-md-4">
            <div class="card" style="width: 18rem;">
                <img class="card-img-top" src="${artist.avatarUrl}" alt="Artist profile picture">
                <div class="card-body">
                    <h3 class="card-title">${artist.artistName}</h3>
                    <p class="card-text"></p>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>

    </c:if>



        <div class="row m-2" >
            <div class="col-lg-8 mx-auto text-center">
                <a class="btn btn-primary" href="discover">Discover Artists</a>
                <a class="btn btn-primary" href="spotifLokal">Contribute</a>
            </div>
        </div>
    </div>
</section>

<%@include file="footer.jsp"%>
