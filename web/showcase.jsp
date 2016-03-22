<%-- 
    Document   : showcase
    Created on : Nov 22, 2014, 6:24:12 PM
    Author     : skora
--%>

<%@page import="controller.ShowCaseController"%>
<%@page import="model.University"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
  <title>Welcome to Linked U</title>

  <meta http-equiv="content-language" content="en">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="description" content="This is University Search page for IT353 Project.">
  <meta name="keywords" content="IT353, ISU">
  <script src="resources/js/jquery-2.1.1.min.js"></script>
  <script src="resources/js/bootstrap.min.js"></script>
  <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="resources/css/project.css">
  <!-- <link rel="shortcut icon" href="favicon.ico"> -->
  <link rel="shortcut icon" href="http://www.iconj.com/ico/r/5/r5b17qv18e.ico" type="image/x-icon" />
</head>
<body>
  <!-- Paste navigation bar after body tag -start-  -->
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button class="navbar-toggle collapsed" data-target="#bs-example-navbar-collapse-6" data-toggle="collapse" type="button"></button>
                <a class="navbar-brand" href="#top">
                    <img src="resources/images/logo.png" alt="Linked U" title="Linked U" >
                </a>
            </div>
            <div id="bs-example-navbar-collapse-6" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <!-- Find an anchor element of your page, change its href attribute to "#top" o (not to redirect to your page on click) and add class="active" 
                       to li element so that your page's button is selected by default
                -->
                <!-- e.g. <li class="active"><a href="#top">University Search</a></li> -->
                <%
                    response.setHeader("header", "header");
                    String userEmail = request.getParameter("userEmail") + "";
                    String userType = request.getParameter("userType") + "";
                    if (userType.equalsIgnoreCase("S")){
                        %><li><a href=<%= "profile.jsp?userEmail=" + userEmail + "&userType=" + userType %>>Your Profile</a></li>
                        <li><a href=<%= "usearch.jsp?userEmail=" + userEmail + "&userType=" + userType %>>University Search</a></li></li><%                    
                    }else if (userType.equalsIgnoreCase("R")){
                        %>"<li><a href=<%= "stsearch.jsp?userEmail=" + userEmail + "&userType=" + userType %> >Student Search</a></li><%
                    }
                %>
                    
                    
                </ul>
                <ul class="nav navbar-nav navbar-right">
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="glyphicon glyphicon-cog"></i> Your Account <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                      <%
                    
                    if (userType.equalsIgnoreCase("S")){
                        %><li><a href=<%= "update.jsp?userEmail=" + userEmail + "&userType=" + userType %>>Edit Your Profile</a></li>
                      <li class="divider"></li><%
                      }
                      %>
                      <li><a href="index.jsp">Sign out</a></li>
                    </ul>
                  </li>
                </ul>
            </div>
            
        </div>
    </nav>
<!-- Paste navigation bar after body tag -end- -->
  <div class="container">
    <div class="page-header"  style="display:inline-block;">
      <h1>Welcome to Linked U!</h1>
    </div>

    <div class="jumbotron">
      <div class="label label-success">Featured University</div>
      <div class="row">
        <div class="col-lg-8">
          <%
            String currPath = getServletContext().getContextPath();
            
            ShowCaseController scController = new ShowCaseController(currPath);
            University aUniv = scController.pickOneFeaturedUniversity();
            
          %>
          <h1><%= aUniv.getUniversityName() %></h1>
          <p><%= aUniv.getDescription() %></p>
          <p><a class="btn btn-primary btn-lg" href=<%= aUniv.getUrl() %> target="_blank" role="button">Learn more</a></p>
        </div>
        <div class="col-lg-4">
          <a href="#" class="thumbnail">
              <img src=<%= aUniv.getPhotoPath1() %> alt="...">
          </a>
          <a href="#" class="thumbnail">
            <img src=<%= aUniv.getPhotoPath2() %> alt="...">
          </a>
        </div>
      </div>
    </div>
  </div>
</body>


</html>
