<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 3/9/2021
  Time: 1:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp"%>
<html>
<%@include file="head.jsp"%>
<body>
<%@include file="header.jsp"%>

    <form action="discoverAction" method="post" id="locationForm">
        <%@include file="locationFormElements.jsp"%>
        <input class="btn btn-primary mb-2" type="submit" value="enter location">
    </form>
    <c:if test="${formSubmitted == 'true'}">
        <c:if test="${fn:length(artists) > 0}">
        <div id="resultsContainer">
            <h3>Showing artists in ${cityLocation}</h3>
            <div>
            <c:forEach items="${artists}" var="artist">
                <h4>${artist.artistName}</h4>
                <p>${artist.spotifyId}</p>
                <p>${artist.description}</p>
                <img src="${artist.avatarUrl}" id="artistImage">

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

        /*
        locateUser = document.querySelector("#locateUser");
        locateUser.addEventListener('click', () => {
           navigator.geolocation.getCurrentPosition(function(position) {
               console.log(position);
           });
        });
         */
    </script>


</body>
</html>
