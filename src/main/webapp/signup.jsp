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
        <form class="form" id="signupForm" action="j_security_check" method="POST">
            <div class="form-group">
                <label class="form-check-label" for="username">Username</label>
                <input type="text" class="form-control" id="username" name="j_username" required>
            </div>

            <div class="form-group">
                <label class="form-check-label" for="password">Password</label>
                <input type="password" class="form-control" id="password" name="j_password">
            </div>
            <div class="form-group">
                <label class="form-check-label" for="confirmPassword">Confirm Password</label>
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword">
            </div>
            <!--<button type="submit" class="btn btn-primary">Submit</button>-->
            <input type="submit" value="Log In">
        </form>
        <div>Already have an account? <a href="/login">Login Here</a> </div>
    </div>

</body>
</html>
