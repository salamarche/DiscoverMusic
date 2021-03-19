<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 3/4/2021
  Time: 9:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="taglib.jsp"%>
<html>
<%@include file="head.jsp"%>

<body>
<%@include file="header.jsp"%>
    <div>
            <form class="form" id="loginForm" action="loginAction" method="POST">
            <div class="form-group">
                <label class="form-check-label" for="username">Email</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>

            <div class="form-group">
                <label class="form-check-label" for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password">
            </div>
            <!--<button type="submit" class="btn btn-primary">Submit</button>-->
            <input type="submit" value="Log In">
        </form>
        <div><a href="signup">Create a new account</a></div>
        <div><a href="">Password Recovery</a></div>
    </div>

</body>
</html>
