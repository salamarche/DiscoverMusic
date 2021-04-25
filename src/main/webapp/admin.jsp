<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 3/2/2021
  Time: 6:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp"%>
<%@include file="nav.jsp"%>


<div id="viewArtists">
  <h2>Artists</h2>
  <form action="edit-artist" method="post" id="editArtistForm">
    <table class="table">
      <thead>
      <tr>
        <th>ID</th>
        <th>Spotify ID</th>
        <th>Artist Name</th>
        <th>Avatar</th>
        <th>Description</th>
        <th>Select User</th>
      </tr>
      </thead>

      <tbody>
      <c:forEach var="artist" items="${artists}">
        <tr>
          <td>${artist.id}</td>
          <td>${artist.spotifyId}</td>
          <td>${artist.artistName}</td>
          <td>${artist.avatarUrl}</td>
          <td>${artist.description}</td>
          <td><input type="radio" value="${artist.id}" name="selectedArtist" /></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>

    <button class="btn btn-danger" type="submit" name="submit" value="delete">Delete Artist</button>
    <% //TODO add editArtist controller %>
  </form>
</div>

<%@include file="footer.jsp"%>

