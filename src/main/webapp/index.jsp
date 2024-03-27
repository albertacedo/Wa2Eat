<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <link href="css/test.css" rel="stylesheet" type="text/css" title="Hoja de estilo CSS">
    <link href="./bootstrap/bootstrap.min.css" rel = stylesheet>
    <script src = "script.js" defer></script>
    <title>Wa2Eat</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body id="cuerpoPrincipal" class="fondoini">
<script src="./bootstrap/bootstrap.bundle.min.js"></script>

<c:choose>
    <c:when test="${sessionScope.logged_user == null}">

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
                        <a class="nav-link dropdown-toggle" href="#" id="navbarScrollingDropdown2" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Media
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                            <li><a class="dropdown-item" href="https://www.youtube.com/">YouTube</a></li>
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
<main role="main">
    <br>
    <br>


    <div class="container">
        <div class="card bg-light mb-3" style="width: 50rem; height: 38.5rem" >
            <img class="card-img-top" src="media/wa2eat%20jc.jpg" alt="Card image cap">
            <div class="card-body">
                <a href="Register.jsp" class="btn btn-primary"> New in Wa2Eat? Get started!</a>
            </div>
        </div>
    </div>
</main>

    </c:when>
    <c:otherwise>

<div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light navbar fixed-top">
        <div class="container-fluid">
            <a class="navbar-brand" href="./index.jsp">Wa2Eat</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarScroll2">
                <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="./Search.jsp">Search here!</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="./ingredients.jsp">Ingredients</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarScrollingDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Media
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                            <li><a class="dropdown-item" href="https://www.youtube.com/">YouTube</a></li>
                            <li><a class="dropdown-item" href="https://www.instagram.com/">Instagram</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="https://twitter.com/home">Twitter</a></li>
                        </ul>
                    </li>

                </ul>
                <p>Welcome, ${sessionScope.logged_user}!      </p>
                <form class="d-flex" action="logout" method="post">
                    <button class="btn btn-outline-success" type="submit">Log out</button>
                </form>

            </div>
        </div>
    </nav>
</div>
<main role="main">
    <br>
    <br>



    <div class="container">
        <div class="card bg-light mb-3" style="width: 50rem; height: 38.5rem" >
            <img class="card-img-top" src="media/wa2eat%20jc.jpg" alt="Card image cap">
            <div class="card-body">
                <a href="Search.jsp" class="btn btn-primary"> Go and discover!</a>

            </div>
        </div>
    </div>
</main>

    </c:otherwise>
</c:choose>

</body>

</html>