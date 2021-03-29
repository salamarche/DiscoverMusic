<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<%@include file="header.jsp"%>
<html>
<body>
<h2>Indie Project Links</h2>
    <ul>
        <li><a href="admin">Admin Page</a></li>
        <li><a href="discover">Discover Artists</a></li>
    </ul>

    <p>Just testing login... <%= session.getAttribute("username")%> </p>

</body>
</html>
