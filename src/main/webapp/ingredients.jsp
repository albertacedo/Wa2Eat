<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                <li class="nav-item dropdown">
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
                  <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                    <li><a class="dropdown-item" href="https://www.youtube.com/">YouTube</a></li>
                    <li><a class="dropdown-item" href="https://www.instagram.com/">Instagram</a></li>
                    <li>
                      <hr class="dropdown-divider">
                    </li>
                    <li><a class="dropdown-item" href="https://twitter.com/home">Twitter</a></li>
                  </ul>
                </li>

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
          <div class="row">
            <!-- BEGIN SEARCH RESULT -->
            <div class="col-md-12">
              <div class="grid search">
                <div class="grid-body">
                  <div class="row">

                    <div class="col-md-3">
                      <form class="searchform" action="add_ingredient" method="post">
                        <h2 class="grid-title">Ingredient data:</h2>
                        <hr>
                        <h4>Ingredient name</h4>
                        <div class="form-outline">
                          <input type="search" id="form1" name = "ingredient" class="form-control" placeholder="Write an ingredient" aria-label="Search" required >
                        </div>
                        <p>Don't forget starting with capital letter!</p>
                        <h4>Expiration date:</h4>
                        <div class="input-group number" data-date="2014-06-14T05:25:07Z"
                             data-date-format="dd-mm-yyyy" data-link-field="dtp_input1">
                          <input type="date" name = "date" class="form-control" required>
                        </div>
                        <div class="padding"></div>
                        <br>
                        <button class="btn btn-primary" type="submit">Add Ingredient</button>
                      </form>
                      <br>
                      <c:if test="${requestScope.err_msg != null}">
                        <div id="err">${requestScope.err_msg}</div>
                      </c:if>
                      <c:if test="${requestScope.success_msg != null}">
                        <div id="success">${requestScope.success_msg}</div>
                      </c:if>

                    </div>
                    <!-- BEGIN RESULT -->
                    <div class="col-md-9">
                      <h2><i class="fa fa-file-o"></i> Your Ingredients</h2>
                      <hr>

                      <!-- BEGIN TABLE RESULT -->
                      <div class="table-responsive">
                        <table>
                          <c:set var="req" value="${pageContext.request}" />
                          <c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />
                          <c:forEach items="${bought_Ingredient}" var="BI">
                            <div>
                              <tr>
                                <td name>
                                  <form action = "delete_ingredient?name=${BI.getName()}&date=${BI.expiration_date()}" method = "POST" enctype="multipart/form-data">
                                    <c:out value=" ${BI.getName()} , expiration date on ${BI.expiration_date()} :"/>
                                    <input type="submit" value="Delete"><br>
                                  </form>
                                </td>
                              </tr>
                            </div>
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

      </div>

      </body>

  </c:otherwise>
</c:choose>

</html>







