<%@ page import="logic.Recipe" %>
<%@ page import="java.util.List" %>
<%@ page import="jdbc.QueryFunctions" %>
<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <link href="css/search.css" rel="stylesheet" type="text/css" title="Hoja de estilo CSS">
    <link href="./bootstrap/bootstrap.min.css" rel=stylesheet>
    <script src="script.js" defer></script>
    <title>Wa2Eat</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<c:choose>
    <c:when test="${sessionScope.logged_user == null}">
        <c:redirect url="index.jsp"/>
    </c:when>
    <c:otherwise>

        <body id="cuerpo" class="fondosearch">
        <script src="./bootstrap/bootstrap.bundle.min.js"></script>

        <div>
            <nav class="navbar navbar-expand-lg navbar-light bg-light navbar fixed-top">
                <div class="container-fluid">
                    <a class="navbar-brand" href="./index.jsp">Wa2Eat</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarScroll"
                            aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarScroll2">
                        <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll"
                            style="--bs-scroll-height: 100px;">

                                <li class="nav-item">
                                    <a class="nav-link active" aria-current="page" href="./Search.jsp">Search here!</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link active" aria-current="page" href="./ingredients.jsp">Ingredients</a>
                                </li>
                                <a class="nav-link dropdown-toggle" href="#" id="navbarScrollingDropdown" role="button"
                                   data-bs-toggle="dropdown" aria-expanded="false">
                                    Media
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown2">
                                    <li><a class="dropdown-item" href="https://www.youtube.com/">YouTube</a></li>
                                    <li><a class="dropdown-item" href="https://www.instagram.com/">Instagram</a></li>
                                    <li>
                                        <hr class="dropdown-divider">
                                    </li>
                                    <li><a class="dropdown-item" href="https://twitter.com/home">Twitter</a></li>
                                </ul>


                        </ul>
                        <p>Welcome, ${sessionScope.logged_user}! </p>
                        <form class="d-flex" action="logout" method="post">
                            <button class="btn btn-outline-success" type="submit">Log out</button>
                        </form>

                    </div>
                </div>
            </nav>
        </div>

        <br>
        <br>
        <br>
        <br>


        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        <div class="container">
            <form class="searchform" action="search" method="post">
                <div class="row">
                    <!-- BEGIN SEARCH RESULT -->
                    <div class="col-md-12">
                        <div class="grid search">
                            <div class="grid-body">
                                <div class="row">
                                    <!-- BEGIN FILTERS -->
                                    <div class="col-md-3">
                                        <h2 class="grid-title"><i class="fa fa-filter"></i> Filters</h2>
                                        <hr>

                                        <!-- BEGIN FILTER BY CATEGORY -->
                                        <h4>By origin:</h4>
                                        <div class="checkbox">
                                            <label><input type="checkbox" class="icheck" name = "origin" value = "Mediterranean"> Mediterranean</label>
                                        </div>
                                        <div class="checkbox">
                                            <label><input type="checkbox" class="icheck" name = "origin" value = "Indian"> Indian</label>
                                        </div>
                                        <div class="checkbox">
                                            <label><input type="checkbox" class="icheck" name = "origin" value = "French"> French</label>
                                        </div>
                                        <div class="checkbox">
                                            <label><input type="checkbox" class="icheck" name = "origin" value = "English/American"> English/American</label>
                                        </div>
                                        <div class="checkbox">
                                            <label><input type="checkbox" class="icheck" name = "origin" value = "Mexican"> Mexican</label>
                                        </div>
                                        <!-- END FILTER BY CATEGORY -->

                                        <!-- BEGIN FILTER BY SEASON -->
                                        <h4>By season:</h4>
                                        <div class="checkbox">
                                            <label><input type="checkbox" class="icheck" name = "season" value = "4 seasons"> 4 seasons</label>
                                        </div>
                                        <div class="checkbox">
                                            <label><input type="checkbox" class="icheck" name = "season" value = "Spring"> Spring</label>
                                        </div>
                                        <div class="checkbox">
                                            <label><input type="checkbox" class="icheck" name = "season" value = "Summer"> Summer</label>
                                        </div>
                                        <div class="checkbox">
                                            <label><input type="checkbox" class="icheck" name = "season" value = "Autumn"> Autumn</label>
                                        </div>
                                        <div class="checkbox">
                                            <label><input type="checkbox" class="icheck" name = "season" value = "Winter"> Winter</label>
                                        </div>
                                        <!-- END FILTER BY SEASON -->

                                        <div class="padding"></div>

                                        <!-- BEGIN FILTER BY BOUGHT INGREDIENTS -->
                                        <h4>Show only recipes with already bought ingredients:</h4>
                                        <div class="checkbox">
                                            <label><input type="checkbox" class="icheck" name = "bought" value = "bought"> Available ingredients only</label>
                                        </div>
                                        <!-- END FILTER BY BOUGHT INGREDIENTS -->

                                        <div class="padding"></div>

                                        <!-- BEGIN FILTER BY CATEGORY -->
                                        <h4>Show vegan recipes only:</h4>
                                        <div class="checkbox">
                                            <label><input type="checkbox" class="icheck" name = "is_vegan" value = "True"> Vegan</label>
                                        </div>
                                        <!-- END FILTER BY CATEGORY -->

                                        <div class="padding"></div>

                                        <h4>By ingredients</h4>
                                        <div class="form-outline">
                                            <input type="search" id="form1" name = "ingredient" class="form-control" placeholder="Write an ingredient" aria-label="Search" />
                                        </div>
                                        <!-- BEGIN FILTER BY DATE -->
                                        <h4>By date:</h4>
                                        Before expiration date:
                                        <div class="input-group number" data-date="2014-06-14T05:25:07Z"
                                             data-date-format="dd-mm-yyyy" data-link-field="dtp_input1">
                                            <input type="date" name = "date" class="form-control">

                                        </div>
                                        <input type="hidden" id="dtp_input1" value="">

                                        <!-- END FILTER BY DATE -->

                                        <div class="padding"></div>


                                    </div>
                                    <!-- END FILTERS -->
                                    <!-- BEGIN RESULT -->
                                    <div class="col-md-9">
                                        <h2><i class="fa fa-file-o"></i> Result</h2>
                                        <hr>
                                        <!-- BEGIN SEARCH INPUT -->
                                        <div class="input-group">
                                            <input type="text" class="form-control" name = "recipe_name" placeholder="Search your recipe by name!">
                                            <span class="input-group-btn">
                                            <button class="btn btn-primary" type="submit"><i class="fa fa-search"></i></button>
                                            </span>
                                        </div>
                                        <!-- END SEARCH INPUT -->
                                        <p>Showing all results matching your search</p>

                                        <div class="padding"></div>



                                        <!-- BEGIN TABLE RESULT -->
                                        <div class="table-responsive">
                                            <table>
                                                <c:forEach items="${recipes}" var="recipe">
                                                    <tr>
                                                        <td><c:out value="${recipe.toString()}" />
                                                            <a href=${recipe.getLink()}>Link</a>
                                                        </td>

                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </div>
                                        <!-- END TABLE RESULT -->
                                    </div>

                                </div>
                                <!-- END RESULT -->
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END SEARCH RESULT -->

        </form>
        </div>

        </body>

    </c:otherwise>
</c:choose>

</html>