<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<%@include file="header.jsp"%>
<html>
<body>
<h2>Indie Project Links</h2>
    <ul>
        <li><a href="admin">Admin Page</a></li>
        <li><a href="discover">Discover Artists</a></li>
        <li><a href="spotifLokal">Contribute</a></li>
        <li><a href="user">User</a></li>
    </ul>


    <div>
        <c:if test="${fn:length(discoveredArtists) > 0}">
            <h2>Recently Discovered Artists</h2>
            <c:forEach items="${discoveredArtists}" var="artist">
                <h3>${artist.artistName}</h3>
            </c:forEach>
        </c:if>

    </div>

</body>
</html>
