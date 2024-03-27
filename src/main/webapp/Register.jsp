<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Wa2Eat-Register</title>
    <link href="css/register.css" rel="stylesheet" type="text/css" title="Hoja de estilo CSS">
    <link href="./bootstrap/bootstrap.min.css" rel = stylesheet>
    <script src = "script.js" defer></script>

</head>
<body class="fondoR">
<script src="./bootstrap/bootstrap.bundle.min.js"></script>
<div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light navbar fixed-top">
        <div class="container-fluid">
            <a class="navbar-brand" href="./index.jsp">Wa2Eat</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarScroll">
                <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="./Login.jsp">Log In</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="./Register.jsp">Sign up</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarScrollingDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Media
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                            <li><a class="dropdown-item" href="https://www.youtube.com/">Youtube</a></li>
                            <li><a class="dropdown-item" href="https://www.instagram.com/">Instagram</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="https://twitter.com/home">Twitter</a></li>
                        </ul>
                    </li>

                </ul>

            </div>
        </div>
    </nav>
</div>

<form style="width: 400px; margin: auto; margin-top: 200px" class="regform" action="register" method="post">
    <div class="mb-3 us">
        <label for="exampleInputEmail1" class="form-label" style="margin-left: 110px">Username or Email</label>
        <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name = "email">
        <div id="emailHelp" class="form-text" style="margin-left: 65px">We'll never share your email with anyone else.</div>
    </div>
    <div class="mb-3 pas">
        <label for="exampleInputPassword1" class="form-label" style="margin-left: 140px">Password</label>
        <input type="password" class="form-control" id="exampleInputPassword1" name = "password">
    </div>
    <div class="mb-3 rep">
        <label for="exampleInputPassword2" class="form-label" style="margin-left: 114px">Repeat Password</label>
        <input type="password" class="form-control" id="exampleInputPassword2" name = "repeatedPassword">
    </div>

    <c:if test="${requestScope.err_msg != null}">
        <div id="err">${requestScope.err_msg}</div>
    </c:if>

    <input type="hidden" value="Register" id="register" name="register" placeholder="Register user"><br>
    <button class="btn btn-primary" id="myButton" type="submit" style="margin-left: 115px">Create Account</button>

</form>
<br/>

</body>
</html>