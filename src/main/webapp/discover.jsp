<%@include file="taglib.jsp"%>
<%@include file="nav.jsp"%>

<c:if test="${formSubmitted == 'true'}">
<section class="projects-section bg-dark">
    <div id="resultsContainer" class="container-fluid">
        <c:if test="${fn:length(artists) > 0}">
            <div class="row justify-content-center text-light"> <h2>Showing artists in ${cityLocation}</h2></div>
            <div class="card-columns">

                <c:forEach items="${artists}" var="artist">

                    <div class="card m-2" style="width: 20rem;">
                        <img class="card-img-top" src="${artist.avatarUrl}" id="artistImage" alt="Artist profile picture">
                        <div class="card-body">
                            <h3 class="card-title">${artist.artistName}</h3>
                            <p class="card-text">${artist.spotifyId}<p>
                            <p>${artist.description}</p>
                            <div>
                                <form action="favorite" method="post">
                                    <input style="width: 3rem;" type="image" id="add" src="images/icons/add.png">
                                    <label for="add" >add artist to your collection</label>
                                    <input type="hidden" name="artistId" value="${artist.id}">
                                     <input type="hidden" name="cityId" value="${cityId}">
                                </form>
                            </div>
                        </div>
                    </div>

                </c:forEach>
            </div>
        </c:if>

        <c:if test="${fn:length(artists) == 0}">
            <div class="text-center text-light">
                <h3>No artists found in this city</h3>
                <a href="spotifLokal"><h4>Contribute to our Collection</h4></a>
            </div>
        </c:if>
    </div>
</section>
</c:if>

<section class="projects-section bg-dark text-white">
    <div class="container col-lg-8">
        <form action="discoverAction" method="get" id="locationForm">
            <%@include file="locationFormElements.jsp"%>
            <input class="btn btn-primary mb-2" type="submit" value="enter location">
        </form>
    </div>
</section>


    <script>
        //This removes fields from location form that are not needed by the servelt
        //However, there fields are needed for user and for the functionality of the form
        let form = document.querySelector("#locationForm");
        form.addEventListener("submit", () => {
            form.country.remove();
            form.region.remove();
            form.city.remove();
            form.selectedCountryId.remove();
            form.selectedRegionId.remove();
        });

        /*
        locateUser = document.querySelector("#locateUser");
        locateUser.addEventListener('click', () => {
           navigator.geolocation.getCurrentPosition(function(position) {
               console.log(position);
           });
        });
         */
    </script>
<%@include file="footer.jsp"%>
