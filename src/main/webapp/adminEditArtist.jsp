
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp"%>
<%@include file="nav.jsp"%>

<section class="bg-dark p-5"></section>

<section class="p-5 bg-light text-center">
    <div class="container-fluid col-6">
        <h2>${artist.artistName}</h2>
        <form action="edit-artist" method="post" id="editArtistForm">
            <div class="form-group">
                <label for="artistDescription">Edit Description</label>
                <textarea class="form-control" id="artistDescription" name="artistDescription">
                    ${artist.description}
                </textarea>
            </div>
            <input class="hidden" name="artistId" id="artistId" value="${artist.id}">
            <button class="btn btn-primary" type="submit" name="submit" value="edit">Submit</button>

        </form>
    </div>
</section>

<script>
    /*
    let descriptionArea = document.querySelector("#artistDescription");

    descriptionArea.addEventListener("change", () => {
        let descriptionString = descriptionArea.textContent;
        descriptionArea.value = descriptionString;
    })
    */
</script>
