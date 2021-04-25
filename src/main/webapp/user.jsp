<%@include file="taglib.jsp"%>

<%@include file="nav.jsp"%>

    <h1>User history</h1>
    <c:forEach var="engagement" items="${engagementInfo}">
        <p>${engagement.key}</p>
        <p>${engagement.value['artist'].artistName}</p>
        <form action="remove-engagement" method="post">
            <input type="hidden" name="engagementId" value="${engagement.value['engagementId']}">
            <input class="btn btn-danger mb-2" type="submit" value="remove">
        </form>
    </c:forEach>

