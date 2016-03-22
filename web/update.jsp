<%@page import="dao.StudentInfoDAO"%>
<%@page import="dao.StudentInfoDAOImpl"%>
<%@page import="model.StudentInfo"%>
<%@page import="upload.FileUploadHandler"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@page import="controller.StudentInfoController"%>

     <%
         if(session.getAttribute("authenticated")!=null && session.getAttribute("authenticated").equals(true))
        {
            response.sendRedirect("index.jsp");
        }
        
         //Loads the user information.
        String userEmail = request.getParameter("userEmail");
        String userType = request.getParameter("userType");
        
        controller.StudentInfoController.getStudentInfo(userEmail);
     %>

<html>
    <head>
        <title>Update Content</title>
          <meta http-equiv="content-language" content="en">
          <meta charset="UTF-8">
          <meta name="description" content="Upload Page">
          <meta name="keywords" content="IT353, ISU, Michael, Ferrer, maferre@ilstu.edu">
          <meta name="author" content="Michael Ferrer">
          <script src="./JQ/jquery-2.1.1.min.js"></script>
          <script src="./bootstrap/js/bootstrap.min.js"></script>
          <script type="text/javascript" src="./assign2.js"></script>
          <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
          <link rel="stylesheet" type="text/css" href="resources/css/project.css">
    <link rel="shortcut icon" href="http://www.iconj.com/ico/r/5/r5b17qv18e.ico" type="image/x-icon" />
    </head>
    <body>
        
        
  <div class="container">
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
                <%
                    response.setHeader("header", "header");
                    if (userType.equalsIgnoreCase("S")){
                        %><li><a href=<%= "profile.jsp?userEmail=" + userEmail + "&userType=" + userType  %>>Your Profile</a></li>
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
                        <li><a href=<%= "update.jsp?userEmail=" + userEmail + "&userType=" + userType %>>Edit Your Profile</a></li>
                      <li class="divider"></li>
                      <li><a href="index.jsp">Sign out</a></li>
                    </ul>
                  </li>
                </ul>
            </div>
            
        </div>
    </nav>

    <div class="page-header"  style="display:inline-block;">
      <h1>Update Content <small> Manage pictures, videos and more</small></h1></nobr>
    </div>
      
    <div class="panel panel-primary">
        <div class="panel-heading"><h2>Profile Picture</h2></div>
        <div class="panel-body">
            <p><strong>Current Profile Picture:</strong></p>
            <img src="resources/images/profileDefault.png" alt="Profile Photo" title="Profile Photo">
        </div>
        <div class="panel-body">
            <p>
                <strong>Click below to upload a profile picture:</strong>
                <br>
                
            <form method="post" action="upload" enctype="multipart/form-data">
                <input type="file" name="photoFile" id="photoFile" required="true" /> <br/>
                <button type="submit" name="upload" id="upload" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> Upload Photo</button>
            </form>
                
            </p>
        </div>
    </div>
      <div class="panel panel-primary">
        <div class="panel-heading"><h2>Profile Information</h2></div>
        <div class="panel-body">
            <form autocomplete="off" method="post" onsubmit="updateMethod()">
            <div class="form-group">
                <label for="firstName">First Name</label>
                <input type="text" name="fName" class="form-control" placeholder="John" value="<%= controller.StudentInfoController.getInfo().getFirstName() %>">
            </div>
            <div class="form-group">
                <label for="lastName">Last Name</label>
                <input type="text" name="lName" class="form-control" placeholder="Smith" value="<%= controller.StudentInfoController.getInfo().getLastName() %>">
            </div>
            <div class="form-group">
                <label for="email">E-mail Address</label>
                <input type="email" name="email" class="form-control" placeholder="name@mail.com" value="<%= controller.StudentInfoController.getInfo().getEmail() %>">
            </div>
            <div class="form-group">
                <label for="address">Street Address</label>
                <input type="text" name="street" class="form-control" placeholder="123 Cherry St." value="<%= controller.StudentInfoController.getInfo().getAddress() %>">
            </div>
            <div class="form-group">
                <label for="city">City</label>
                <input type="text" name="city" class="form-control" placeholder="Woodridge" value="<%= controller.StudentInfoController.getInfo().getCity() %>">
            </div>
            <div class="form-group">
                <label for="state">State</label>
                <input type="text" name="state" class="form-control" placeholder="IL" value="<%= controller.StudentInfoController.getInfo().getState() %>">
            </div>
            <div class="form-group">
                <label for="phone">Phone Number</label>
                <input type="text" name="phone" class="form-control" placeholder="(630) 999 - 9999" value="<%= controller.StudentInfoController.getInfo().getPhone() %>">
            </div>
            <div class="form-group">
                <label for="grade">Grade</label>
                <input type="text" name="grade" class="form-control" placeholder="11" value="<%= controller.StudentInfoController.getInfo().getGrade() %>">
            </div>
            <div class="form-group">
                <label for="major">Primary Major</label>
                <input type="text" name="major" class="form-control" placeholder="Information Technology" value="<%= controller.StudentInfoController.getInfo().getMajor() %>">
            </div>
            <div class="form-group">
                <label for="highSchool">High School</label>
                <input type="text" name="hs" class="form-control" placeholder="County High School" value="<%= controller.StudentInfoController.getInfo().getHighSchool() %>">
            </div>
            <div class="form-group">
                <label for="comment">Biography:</label>
                <textarea name="bio" class="form-control" rows="5" id="biography" ><%= controller.StudentInfoController.getInfo().getBiography() %></textarea>
            </div>
            <div class="form-group">
                <input name="updateProfile" type="submit" class="btn btn-default" value="Update">
                <input name="reset" type="reset" class="btn btn-default" value="Reset">
            </div>
            <script>
                function updateMethod()
                {
                    <%
                    System.out.println("It should be here when submitted");
                    String submit = request.getParameter("updateProfile") + "";
           
                    if(submit.equals("Update"))
                    {
                        //Gets all of the strings for updates.
                        
                        String fName = request.getParameter("fName");
                        String lName = request.getParameter("lName");
                        String email = request.getParameter("email");
                        String street = request.getParameter("street");
                        String city = request.getParameter("city");
                        String state = request.getParameter("state");
                        String phone = request.getParameter("phone");
                        String grade = request.getParameter("grade");
                        String major = request.getParameter("major");
                        String hs = request.getParameter("hs");
                        String bio = request.getParameter("bio");
                        
                        //Creates a StudentInfo object to be passed.
                        StudentInfo updatedStudent = new StudentInfo(email,fName,lName,street,city,state,phone,hs,bio,grade,major);
          
                        int returnCode = StudentInfoController.updateStudentInfo(updatedStudent);
                        System.out.println("Code is " + returnCode); 
                        if(returnCode == 1)
                        {
                             %> <alert("Your information was updated!");
                            <jsp:forward page="showcase.jsp">
                                <jsp:param name="userEmail" value="<%= userEmail %>" />
                                <jsp:param name="userType" value="<%= userType %>" />
                            </jsp:forward><%
                        }
          
                        else
                        {
                             %> alert("Please check the fields for errors!"); <% 
                        }
                    }%>
                }   
            </script>
        </form>
        </div>
    </div>
          
      <div class="panel panel-primary">
        <div class="panel-heading"><h2>Highlight Video</h2></div>
        <div class="panel-body">
            <p><strong>Primary Highlight Video:</strong></p>
            <video width="320" height="240" controls>
                <source src="resources/videos/bench.mp4" type="video/mp4">
                Your browser does not support the video tag.
            </video>
        </div>
        <div class="panel-body">
            <p>
                <strong>Click below to choose a video highlighting yourself and your accomplishments:</strong> <br/>
                <br>
                
                <input type="file"> <br/>
                <button class="btn btn-primary"><span class="glyphicon glyphicon-film"></span> Add Video</button>
            </p>
        </div>
    </div>
            
  </div>
    </body>
</html>
