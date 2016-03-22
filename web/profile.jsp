<%-- 
    Document   : index
    Created on : Nov 29, 2014, 10:09:20 PM
    Author     : gmherr2
--%>
<%@page import="controller.ProfileController"%>
<%@page import="model.StudentInfo"%>

<html>
    <head>
        <link rel="shortcut icon" href="http://www.iconj.com/ico/r/5/r5b17qv18e.ico" type="image/x-icon" />
        <title>Profile</title>
        <meta charset="windows-1252">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="resources/js/jquery-2.1.1.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="resources/css/project.css">
        
        <link rel="stylesheet" type="text/css" href="resources/css/profile.css" />	
    </head>
   
    <body>

	 <%  String userEmail = request.getParameter("userEmail");
             String userType = request.getParameter("userType");
             controller.ProfileController.getStudentInfo(userEmail, getServletContext().getContextPath()); %>
	 
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button class="navbar-toggle collapsed" data-target="#bs-example-navbar-collapse-6" data-toggle="collapse" type="button"></button>
                <a class="navbar-brand" href=<%= "showcase.jsp?userEmail=" + userEmail + "&userType=" + userType %>>
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
	
        
		
        <div class="left">
			
            <div class="imgv">

		<div class="img-circular" style="background-image: url('resources/images/profileDefault.png');" alt="Avatar"></div>
                    <h2><%=controller.ProfileController.getInfo().getFirstName()%> <%=controller.ProfileController.getInfo().getLastName()%></h2>
                    </br>

                    <p class="side"><%=controller.ProfileController.getInfo().getEmail()%></p>
                    </br>
                    <p class="side"> Location: <%=controller.ProfileController.getInfo().getCity()%>, <%=controller.ProfileController.getInfo().getState()%></p>
				
                    <p class="side"> High School: <%=controller.ProfileController.getInfo().getHighSchool()%> </p>	
            </div>

        </div>
        
      
        
	<div class="tarea">
            <div class="row">
		<div class="col-md-6">
                    
                    <div class="cardleft">
			<p class="par">Biography:</p>
			<p class="par"><%=controller.ProfileController.getInfo().getBiography()%></p>
                    </div>
                    
                    </br>
                    
                    <div class="cardleft">
			<p class="par">University Interests:</p>
			<p class="par"><%=controller.ProfileController.getInfo().getUnivsInInterest()%></p>
                    </div>
                    
		</div>
			
		<div class="col-md-6">
                    
                    <div class="cardright">
			<p class="par">Primary Major Choice:</p>
                        <p class="par"><%=controller.ProfileController.getInfo().getMajor()%></p>
                    </div>
                    
                    <div class="cardright">	
			<p class="par">Video</p>
                        <p class="par"><video width="320" height="240" controls>
                            <source src="resources/videos/bench.mp4" type="video/mp4">
                           Your browser does not support the video tag.
                        </video></p>
                    </div>	
                    
		</div>		
            </div>
        </div>
    </body>
</html>