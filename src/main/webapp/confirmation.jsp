<%@ page import="org.apache.logging.log4j.Logger" %>
<%@ page import="org.apache.logging.log4j.LogManager" %>
<%@include file="taglib.jsp"%>
<%@include file="nav.jsp"%>

<section class="projects-section text-center text-white" id="confirmation" style="background: url('images/sparks.jpg') ">
<h2>Logging you in ...</h2>

</section>






<form name="userTokenForm" id="userTokenForm" action="loginAction" method="post">
    <input type="hidden" name="hash">
    <input type="hidden"  name="idToken">
    <input type="hidden"  name="accessToken">
</form>

<script>

    let hash = window.location.hash;
    let params = hash.split("#")[1].split("&");
    let idToken = params[0].split("id_token=")[1];
    let accessToken = params[1].split("access_token=")[1];

    document.forms["userTokenForm"]["hash"].value = hash;
    document.forms["userTokenForm"]["idToken"].value = idToken;
    document.forms["userTokenForm"]["accessToken"].value = accessToken;

    document.forms["userTokenForm"].submit();

</script>

<%
    //This works too!
    /*
    final Logger logger = LogManager.getLogger(this.getClass());
    String input = request.getParameter("hash");
    logger.info(input);
     */

%>

