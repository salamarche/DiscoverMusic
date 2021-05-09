
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp"%>
<%@include file="nav.jsp"%>

<section class="bg-dark p-5"></section>

<section class="projects-section bg-light">
  <div id="viewArtists">
    <h2>Artists</h2>
    <form action="admin-action" method="post" id="selectArtistForm">
      <table id="adminTable" class="table table-bordered" cellspacing="0" width="100%">

        <thead>
        <tr>
          <th>Select User</th>
          <th>ID</th>
          <th>Spotify ID</th>
          <th>Artist Name</th>
          <th>Description</th>
          <th>Locations</th>
          <th>Avatar Url</th>
          <th>Spotify Href</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="artist" items="${artists}">
          <tr>
            <td><input type="radio" value="${artist.id}" name="selectedArtist" /></td>
            <td>${artist.id}</td>
            <td>${artist.spotifyId}</td>
            <td>${artist.artistName}</td>
            <td>${artist.description}</td>
            <td>
              <c:forEach var="city" items="${artist.cities}">
                <p>${city.toString()}</p>
              </c:forEach>
            </td>
            <td>${artist.avatarUrl}</td>
            <td>${artist.href}</td>
          </tr>
        </c:forEach>
        </tbody>
      </table>

      <button class="btn btn-danger" type="submit" name="submit" value="delete">Delete Artist</button>
      <button class="btn btn-primary" type="submit" name="submit" value="edit">Edit Artist Details</button>
      <button class="btn btn-secondary" type="submit" name="submit" value="location">Edit Artist Locations</button>

    </form>
  </div>
</section>

<script type="text/javascript" class="init">
  $(document).ready( function () {
    $('#adminTable').DataTable();
  } );
</script>



