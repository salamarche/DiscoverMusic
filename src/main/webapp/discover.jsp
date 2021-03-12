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
    <div id="searchContainer">
        <form class="form" id="searchArtistsForm" action="discoverAction" method="GET" >
            <input class="form-control" type="text" id="city" name="city" placeholder="city">
            <input class="form-control" type="text" id="country" name="country" placeholder="country">
            <button class="btn btn-info" id="locateUser">Get Current Location</button>
            <input class="btn btn-primary" type="submit" value="Search for Artists">
        </form>
    </div>

    <div id="resultsContainer">

    </div>

    <script>
        locateUser = document.querySelector("#locateUser");
        locateUser.addEventListener('click', () => {
           navigator.geolocation.getCurrentPosition(function(position) {
               console.log(position);
           });
        });
    </script>


</body>
</html>
