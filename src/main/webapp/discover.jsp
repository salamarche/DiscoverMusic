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

    <div id="resultsContainer">
        <%//TODO make this pretty %>
        <ul>
        <c:forEach items="${artists}" var="artist">
            <li>${artist.artistName}</li>
        </c:forEach>
        </ul>

    </div>

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
