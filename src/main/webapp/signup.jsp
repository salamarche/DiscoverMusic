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
        <form class="form" id="signupForm" action="signupAction" method="POST">
            <div class="form-group">
                <label class="form-check-label" for="username">Email</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>

            <div class="form-group">
                <label class="form-check-label" for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password">
            </div>
            <div class="form-group">
                <label class="form-check-label" for="confirmPassword">Confirm Password</label>
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword">
            </div>
            <!--<button type="submit" class="btn btn-primary">Submit</button>-->
            <input type="submit" value="Sign Up">
        </form>
        <div>Already have an account? <a href="login">Login Here</a> </div>
    </div>

</body>
</html>
