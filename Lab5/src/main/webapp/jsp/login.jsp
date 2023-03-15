<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Login Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-5">
                <h3 class="text-center text-secondary mt-5 mb-3">User Login</h3>
                <form class="border rounded w-100 mb-5 mx-auto px-3 pt-3 bg-light" method="POST">
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input name="email" id="email" type="email" class="form-control" placeholder="Email">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input name="password" id="password" type="password" class="form-control"
                            placeholder="Password">
                    </div>
                    <div class="form-group">
                        <c:if test="${not empty requestScope.error}">
                            <div class="alert alert-danger alert-dismissible fade show">
                                <button type="button" class="close" data-dismiss="alert">&times;</button>
                                <c:out value='${requestScope.error}'/>
                            </div>
                        </c:if>
                    </div>
                    <div>
                        <label>
                            <input type="checkbox" name="rememberMe"> Remember username & password
                        </label>
                    </div>
                    <div class="form-group">
                        <button class="btn btn-success px-5">Login</button>
                    </div>

                </form>
            </div>

</body>

</html>