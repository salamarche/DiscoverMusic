<%@include file="taglib.jsp"%>
<%@include file="nav.jsp"%>



<header class="masthead">
    <div class="container d-flex h-100 align-items-center">
        <div class="mx-auto text-center">
            <h1 class="mx-auto my-0 text-uppercase"></h1>
            <h2 class="text-white mx-auto mt-2 mb-5">Find Artists In Your Backyard</h2>
            <a class="btn btn-primary" href="discover">Discover Artists</a>
            <a class="btn btn-primary" href="spotifLokal">Contribute</a>
        </div>
    </div>
</header>


<section class="projects-section bg-light" id="recentArtists">
    <div class="container">


    <c:if test="${fn:length(discoveredArtists) > 0}">
        <div class="row">
            <h2>Recently Discovered Artists</h2>
        </div>

        <div class="card-columns" >
            <c:forEach items="${discoveredArtists}" var="artist">
                <div class="card" style="width: 18rem;">
                    <img class="card-img-top" src="${artist.avatarUrl}" alt="Artist profile picture">
                    <div class="card-body">
                        <h3 class="card-title">${artist.artistName}</h3>
                        <c:forEach var="city" items="${artist.cities}">
                            <p>${city.toString()}</p>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
        </div>

    </c:if>

    </div>
</section>

<%@include file="footer.jsp"%>
