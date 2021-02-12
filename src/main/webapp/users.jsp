<%@include file="taglib.jsp"%>
<html>
<body>
<h2>Testing my user class...</h2>
  <form action="editUser" method="post" id="editUserForm">
    <table>
        <thead>
            <tr>
                <th>User Name</th>
                <th>Email</th>
                <th>Select User</th>
            </tr>
        </thead>

        <tbody>
<c:forEach var="user" items="${users}">
        <tr>
            <td>${user.userName}</td>
            <td>${user.email}</td>
            <td><input type="radio" value="${user.id}" name="selectUser" /></td>
        </tr>
</c:forEach>
        </tbody>
    </table>

      <button type="submit" name="submit" value="delete">Delete User</button>
  </form>
</body>
</html>
