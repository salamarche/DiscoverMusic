<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 3/2/2021
  Time: 6:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp"%>
<html>
<%@include file="head.jsp"%>

  <body>
  <%@include file="header.jsp"%>
  <div id="viewUsers">
    <h2>Users</h2>
    <form action="editUser" method="post" id="editUserForm">
      <table class="table">
        <thead>
        <tr>
          <th>ID</th>
          <th>User Name</th>
          <th>Email</th>
          <th>Role</th>
          <th>Select User</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="user" items="${users}">
          <tr>
            <td>${user.id}</td>
            <td>${user.userName}</td>
            <td>${user.email}</td>
            <td>${user.role}</td>
            <td><input type="radio" value="${user.id}" name="selectUser" /></td>
          </tr>
        </c:forEach>
        </tbody>
      </table>

      <button type="submit" name="submit" value="delete">Delete User</button>
    </form>
  </div>

  <div id="viewArtists">
    <h2>Artists</h2>
    <form action="editArtist" method="post" id="editArtistForm">
      <table class="table">
        <thead>
        <tr>
          <th>ID</th>
          <th>Soundcloud ID</th>
          <th>Artist Name</th>
          <th>Location</th>
          <th>Avatar</th>
          <th>Description</th>
          <th>Select User</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="artist" items="${artists}">
          <tr>
            <td>${artist.id}</td>
            <td>${artist.soundcloudId}</td>
            <td>${artist.artistName}</td>
            <td>${artist.location}</td>
            <td>${artist.avatarUrl}</td>
            <td>${artist.description}</td>
            <td><input type="radio" value="${artist.id}" name="selectArtist" /></td>
          </tr>
        </c:forEach>
        </tbody>
      </table>

      <button type="submit" name="submit" value="delete">Delete User</button>
    </form>
  </div>

  </body>
</html>
