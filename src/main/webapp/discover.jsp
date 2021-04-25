<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 3/9/2021
  Time: 1:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp"%>

<%@include file="nav.jsp"%>

    <form action="discoverAction" method="get" id="locationForm">
        <%@include file="locationFormElements.jsp"%>
        <input class="btn btn-primary mb-2" type="submit" value="enter location">
    </form>
    <c:if test="${formSubmitted == 'true'}">
        <c:if test="${fn:length(artists) > 0}">
        <div id="resultsContainer">
            <h3>Showing artists in ${cityLocation}</h3>
            <div>
            <c:forEach items="${artists}" var="artist">
                <form action="favorite" method="post">
                    <h4>${artist.artistName}</h4><input type="image" src="images/icons/white-heart.png">
                    <p>${artist.spotifyId}</p>
                    <p>${artist.description}</p>
                    <img src="${artist.avatarUrl}" id="artistImage">
                    <input type="hidden" name="artistId" value="${artist.id}">
                    <input type="hidden" name="from" value="${pageContext.request.requestURI}">
                    <input type="hidden" name="cityId" value="${cityId}">
                </form>

            </c:forEach>
            </div><br>

        </c:if>

        <c:if test="${fn:length(artists) == 0}">
            <h3>No artists found in this region</h3>
            <a href="spotifLokal"><h4>Contribute to our Collection</h4></a>
        </c:if>


        </div>
    </c:if>


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

