<%@include file="taglib.jsp"%>
<html>
<body>
<h2>Testing my user class...</h2>
    <table>
        <thead>
            <tr>
                <th>User Name</th>
                <th>Email</th>
                <th>Delete</th>
            </tr>
        </thead>

        <tbody>
<c:forEach var="users" items="${users}">
        <tr>
            <td>${users.userName}</td>
            <td>${users.email}</td>
            <td><a href="editUser" value="${users.id}" class="delteUser" >delete user ${users.id}</a></td>
        </tr>

</c:forEach>
        </tbody>
    </table>

</body>
</html>
