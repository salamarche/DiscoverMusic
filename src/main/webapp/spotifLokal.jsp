<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="taglib.jsp"%>

<html>
<%@include file="head.jsp"%>
<body>
<%@include file="header.jsp"%>

    <form class="form" id="confirmId" action="confirm-artist" method="get">
        <label for="spotifyId">
            <input class="form-control" id="spotifyId" name="spotifyId" placeholder="Spotify Artist Id" >
        </label>

        <input class="btn btn-primary" type="submit" value="confirm artist" >
    </form>


    <div>
    <c:if test="${isFound == 'true'}">
        <h3>${artist.artistName}</h3><br>
        <p>${artist.spotifyId}</p><br>
        <p>${artist.description}</p><br>
        <img src="${artist.avatarUrl}" id="artistImage">

        <c:if test="${fn:length(artist.cities) > 0}">
            <h4>Locations</h4>
            <form id="removeLocationForm" action="remove-location" method="post" >
                <ul>
                    <c:forEach items="${artistLocations}" var="location">
                        <li>
                            <label for="${location.key}">${location.value}</label>
                            <input class="form-control" type="radio" id="${location.key}" name="location" value="${location.key}">
                        </li>
                    </c:forEach>
                </ul>
                <input type="hidden" name="artistId" value="${artist.id}">
                <input type="submit" value="remove location" name="removeLocation">
            </form>
        </c:if>

        <form class="form" id="addLocationForm" action="add-location" method="post">
            <%@include file="locationFormElements.jsp"%>
            <input type="hidden" name="artistId" value="${artist.id}">
            <input class="btn btn-primary mb-2" type="submit" value="add location">
        </form>
    </c:if>

    <c:if test="${isFound == 'false'}">
        <div>
            <h2>Artist could not be located</h2>
        </div>

    </c:if>


    </div>
</body>
</html>
