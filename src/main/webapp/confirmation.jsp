<%@ page import="org.apache.logging.log4j.Logger" %>
<%@ page import="org.apache.logging.log4j.LogManager" %>
<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<%@include file="header.jsp"%>
<html>
<body>
<h2>Confirmation</h2>




<form name="userTokenForm" id="userTokenForm" action="loginAction" method="post">
    <input type="hidden" name="hash">
    <input type="hidden" name="uri">
    <input type="hidden"  name="idToken">
    <input type="hidden"  name="accessToken">
</form>

<script>
    let uri = window.location.href;
    let hash = window.location.hash;
    let params = hash.split("#")[1].split("&");
    let idToken = params[0].split("id_token=")[1];
    let accessToken = params[1].split("access_token=")[1];

    document.forms["userTokenForm"]["hash"].value = hash;
    document.forms["userTokenForm"]["uri"].value = uri;
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
</body>


</html>
