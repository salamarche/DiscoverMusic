<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="taglib.jsp"%>

<%@include file="nav.jsp"%>
    <section class="projects-section bg-dark">
        <div class="container-fluid col-md-6">
            <form class="form" id="confirmId" action="confirm-artist" method="get">
                <input class="form-control" id="spotifyId" name="spotifyId" placeholder="Spotify Artist Id" >
                <input class="btn btn-primary my-4" type="submit" value="confirm artist" >
            </form>
        </div>
    </section>

    <c:if test="${isFound == 'true'}">
        <section class="py-4">
            <div class="container-fluid">
                <div class="row justify-content-center mb-4 text-light"><h2>Artist Found</h2></div>
                <div class="row justify-content-center">

                    <div class="col-md-4">
                        <div class="card m-auto" style="width: 20rem;">
                            <img class="card-img-top" src="${artist.avatarUrl}" id="artistImage" alt="Artist profile picture">
                            <div class="card-body">
                                <h3 class="card-title">${artist.artistName}</h3>
                                <p class="card-text">${artist.spotifyId}<p>
                                <p>${artist.description}</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4" >
                        <div class="card m-auto">
                            <h2 class="card-header">Locations</h2>
                            <div class="card-body">
                                <c:if test="${fn:length(artist.cities) > 0}">
                                    <form id="removeLocationForm" action="remove-location" method="post" >
                                        <c:forEach items="${artistLocations}" var="location">
                                            <ul class="list-group list-group-flush">
                                                <li class="list-group-item from-check">
                                                    <input class="form-check-input" type="radio" id="${location.key}" name="location" value="${location.key}">
                                                    <label class="form-check-label" for="${location.key}">${location.value}</label>
                                                </li>
                                            </ul>
                                        </c:forEach>
                                        <input type="hidden" name="artistId" value="${artist.id}">
                                        <input class="btn btn-danger" type="submit" value="remove location" name="removeLocation">
                                    </form>
                                </c:if>

                                <c:if test="${fn:length(artist.cities) == 0}">
                                   <p>No Locations Found</p>
                                </c:if>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="row justify-content-center text-light"><h2>Add New Location</h2></div>
                <div class="row justify-content-center my-4 text-light">
                    <div class="col-lg-8">
                        <form class="form" id="addLocationForm" action="add-location" method="post">
                            <%@include file="locationFormElements.jsp"%>
                            <input type="hidden" name="artistId" value="${artist.id}">
                            <input class="btn btn-primary mb-2" type="submit" value="add location">
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </c:if>

    <c:if test="${isFound == 'false'}">
        <section class="bg-dark p-4">
            <div class = "text-light text-center">
                <h2>Artist could not be located</h2>
            </div>
        </section>
    </c:if>
<%@include file="footer.jsp"%>