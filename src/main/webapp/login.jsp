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
        <form class="form" id="loginForm" action="loginAction">
            <div class="form-group">
                <label class="form-check-label" for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>

            <div class="form-group">
                <label class="form-check-label" for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>

</body>
</html>
