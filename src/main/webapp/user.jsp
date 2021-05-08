<%@include file="taglib.jsp"%>
<%@include file="nav.jsp"%>
    <section class="projects-section bg-dark">
        <div class="container">

            <h1 class="text-light">Hello, ${username}</h1>
            <div class="card-columns">
            <c:forEach var="engagement" items="${engagementInfo}">

                <div class="card m-2" style="width: 18rem;">
                    <img class="card-img-top" src="${engagement.value['artist'].avatarUrl}" alt="Artist profile picture">
                    <div class="card-body">
                        <h3 class="card-title">${engagement.value['artist'].artistName}</h3>
                        <c:forEach var="city" items="${engagement.value['artist'].cities}">
                            <p>${city.toString()}</p>
                        </c:forEach>
                        <p class="card-text"><a href="${artist.href}">Listen on Spotify</a></p>
                        <form action="remove-engagement" method="post">
                            <input type="hidden" name="engagementId" value="${engagement.value['engagementId']}">
                            <input class="btn btn-danger mb-2" type="submit" value="remove">
                        </form>

                    </div>
                </div>


            </c:forEach>
            </div>
        </div>
    </section>

<%@include file="footer.jsp"%>