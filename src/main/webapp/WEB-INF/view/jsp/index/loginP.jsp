<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>

</head>
<body>
<div class="container" align="center">
    <div class="row">
        <div class="col-lg-12 text-center" style="margin-top: 15%">
            <h1>Login</h1>
            <c:if test="${param.error == 'true'}">
                <h6>Login Failed! ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</h6>
            </c:if>
        </div>
    </div>
    <!-- Login error -->
    <div class="row">
                <form action="${pageContext.request.contextPath}/j_spring_security_check" method='POST'>
                    <div class="form-group text-center">
                        <legend>Email</legend>
                        <input type="text" name="username" class="form-control">
                    </div>
                    <div class="form-group text-center">
                        <legend>Password</legend>
                        <input type="password" name="password" class="form-control">
                    </div>
                    <div class="text-center">
                        <input class="btn btn-info" name="submit" type="submit" value="Log in" style="width: 50%">
                    </div>
                </form>

</div>
    </div>
</body>
</html>