
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp"%>
<%@include file="nav.jsp"%>

<section class="bg-dark p-5"></section>

<section class="p-5 bg-light text-center">
    <div class="container-fluid col-6">
        <h2>${artist.artistName}</h2>
        <form action="edit-artist" method="post" id="editArtistForm">

            <textarea class="form-control" id="artistDescription" name="artistDescription">
                ${artist.description}
            </textarea>

            <button class="btn btn-primary" type="submit" name="submit" value="edit">Edit</button>

        </form>
    </div>
</section>
