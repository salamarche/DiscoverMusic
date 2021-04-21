<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<%@include file="header.jsp"%>
<html>
<body>
    <h1>User history</h1>
    <c:forEach var="artist" items="${artists}">
        <p>${artist.value.artistName}</p>
        <form method="get" action="remove-engagement"></form>
        <input class="hidden" name="artistId" value="${artist.value.id}">
        <button class="btn btn-danger mb-2" type="submit">Remove from collection</button>
    </c:forEach>
</body>
</html>
