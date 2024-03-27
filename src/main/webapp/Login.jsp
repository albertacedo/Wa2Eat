<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
  <link href="css/Login.css" rel="stylesheet" type="text/css" title="Hoja de estilo CSS">
  <link href="./bootstrap/bootstrap.min.css" rel = stylesheet>
  <script src = "script.js" defer></script>
</head>

<body class="fondo">
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


<form style="width: 400px; margin: auto; margin-top: 200px" class="logform" action="login" method="post">
  <div class="mb-3 abc">
    <label for="exampleInputEmail1" class="form-label" style="margin-left: 110px">Username or Email</label>
    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name = "email">
    <div id="emailHelp" class="form-text" style="margin-left: 65px">We'll never share your email with anyone else.</div>
  </div>
  <div class="mb-3 def">
    <label for="exampleInputPassword1" class="form-label" style="margin-left: 140px">Password</label>
    <input type="password" class="form-control" id="exampleInputPassword1" name = "password">
  </div>

  <c:if test="${requestScope.err_msg != null}">
    <div id="err">${requestScope.err_msg}</div>
  </c:if>

  <input type="hidden" value="Login" id="login" name="login" placeholder="Check user"><br>
  <button type="submit" class="btn btn-primary" style="margin-left: 145px">Log In</button>
</form>
<br/>

<p style="margin-left: 640px">Click <a href="./Register.jsp">here</a> if you don't have an account</p>

</body>
</html>