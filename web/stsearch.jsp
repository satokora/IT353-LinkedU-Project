<%-- 
    Document   : stsearch
    Created on : Nov 26, 2014, 12:51:41 PM
    Author     : skora
--%>

<%@page import="controller.SearchController"%>
<%@page import="model.StudentInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="model.University"%>
<%@page import="controller.ShowCaseController"%>
<%@page import="model.State"%>
<%@page import="dao.SearchDAOImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Major"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Student Search</title>

  <meta http-equiv="content-language" content="en">
  <meta charset="UTF-8">
  <meta name="description" content="This is Student Search page for IT353 Project.">
  <meta name="keywords" content="IT353, ISU">
  <script src="./resources/js/jquery-2.1.1.min.js"></script>
  <script src="./resources/js/bootstrap.min.js"></script>
  <link rel="stylesheet" type="text/css" href="./resources/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="./resources/css/project.css">
  <link rel="shortcut icon" href="favicon.ico">


</head>
<body>
  <!-- Paste navigation bar after body tag -start-  -->
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button class="navbar-toggle collapsed" data-target="#bs-example-navbar-collapse-6" data-toggle="collapse" type="button"></button>
                <a class="navbar-brand" href=<%= "showcase.jsp?user=" + request.getParameter("user") + "&loginUser=" + request.getParameter("loginUser") %>>
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
                    String userType = request.getParameter("loginUser") + "";
                    if (userType.equalsIgnoreCase("S")){
                        %><li><a href=<%= "profile.jsp?user=" + request.getParameter("user") + "&loginUser=" + userType %>>Your Profile</a></li>
                        <li><a href=<%= "usearch.jsp?user=" + request.getParameter("user") + "&loginUser=" + userType %>>University Search</a></li></li><%                    
                    }else if (userType.equalsIgnoreCase("R")){
                        %>"<li><a href=<%= "stsearch.jsp?user="  + request.getParameter("user") + "&loginUser=" + userType %> >Student Search</a></li><%
                    }
                %>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="glyphicon glyphicon-cog"></i> Your Account <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                      <%
                    
                    if (userType.equalsIgnoreCase("S")){
                        %><li><a href=<%= "update.jsp?user="  + request.getParameter("user") + "&loginUser=" + userType %>>Edit Your Profile</a></li>
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
      <h1>Student Search <small> Prominent candidates are here!</small></h1>
    </div>

  <form method="post" action="stsearch.jsp">
    <div class="panel panel-default">
      <div class="panel-heading" role="tab">
      <div class="row">
      <div class="col-lg-6">



        <div class="form-group">
          <label for="nameSearch">Student Name</label>
          <div class="row">
              <div class="col-lg-6 col-md-6">
                    <input type="text" class="form-control" name="firstName" placeholder="First name">
              </div>
              <div class="col-lg-6 col-md-6">
                    <input type="text" class="form-control" name="lastName" placeholder="Last name">
               </div>
          </div>
        </div>
        <div class="form-group">
            <label for="school">Field of study</label>
            <select name="major_id" class="form-control">
                <option value="" selected="selected" >ALL</option>
                <%
                    ArrayList<Major> majors = SearchDAOImpl.getListOfMajors();
                    
                    if (majors !=null){
                        for (int i = 0;i<majors.size(); i++){
                            out.println("<option value=\"" + majors.get(i).getMajorId() + "\">");
                            out.println(majors.get(i).getMajorName());
                            out.println("</option>");  
                        }
                        
                    }
                    
                    %>
            </select>
        </div>
        
      </div>
      <div class="col-lg-6">
        <div class="form-group">
          <label for="univ">Name of Your Institution</label>
          <input type="text" class="form-control" name="univ_name" placeholder="" >
        </div>
        <div class="form-group">
          <label for="school">High School</label>
          <input type="text" class="form-control" name="highschool" placeholder="School name" >
        </div>
      </div>
        <hr>
        <div class="form-group" style="float:left;margin-left:15px">
        <button name="search" type="submit" class="btn btn-primary btn-lg" value="Search for students">Search for students</button>
        <button type="reset" class="btn btn-primary btn-lg">Clear Criteria</button>
        <h4  style="float:right;margin-left:15px"><a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          Advanced Search
        </a></h4>
        </div>
        </div>
      

      </div>
      <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
        <div class="panel-body">
        <div class="row">
        <div class="col-lg-6">
          
         
          <div class="form-group">
            <label for="school">Email address</label>
            <input type="email" class="form-control" name="email" placeholder="xx@xx.com" >
          </div>
          <div class="form-group" style="float:left;">
            <label for="state">State</label>
            <select name="state" class="form-control">
              <option value="" selected="selected" >ALL</option>
              <%
                    State stateList = new State();
                    
                    if (stateList !=null){
                        for (int i = 0;i< stateList.getListOfStates().size(); i++){
                            out.println("<option value=\"" + stateList.getListOfStates().get(i) + "\">");
                            out.println(stateList.getListOfStates().get(i));
                            out.println("</option>");  
                        }
                        
                    }
                    
                    %>
            </select>
          </div>
        <div class="form-group" style="float:left;margin-left:20px">
          <label for="city">City</label>
          <input type="text" class="form-control" name="city">
        </div>
        </div>
        <div class="col-lg-6">
          <div class="form-group">
            <label for="act">GPA</label>
            <input type="number" class="form-control" name="grade" min="0.00" max="4.00" step="0.01"  >
          </div>
          
        </div>
      </div>
          <div class="form-group">
            <label for="keyword">Free Keyword from Biography</label>
            <input type="text" class="form-control" name="biography" placeholder="Club activities, volunteer activities..." >
          </div>
        
      </div>
    </div>
    </div>
    <input type="hidden" name="user" value=<%= request.getParameter("user") %>>
    <input type="hidden" name="loginUser" value=<%= userType %>>
  </form>
  <%
    String currPath = getServletContext().getContextPath();                
    String button = request.getParameter("search") + "";
    if(button.equals("Search for students")){
        HashMap keyset = new HashMap();
        ArrayList<String[]> tempKeySet = new ArrayList<String[]>();
        tempKeySet.add(new String[]{"firstName",request.getParameter("firstName") + ""});
        tempKeySet.add(new String[]{"lastName",request.getParameter("lastName") + ""});
        tempKeySet.add(new String[]{"major_id",request.getParameter("major_id") + ""});
        tempKeySet.add(new String[]{"univ_name",request.getParameter("univ_name") + ""});
        tempKeySet.add(new String[]{"highschool",request.getParameter("highschool") + ""});
        tempKeySet.add(new String[]{"email",request.getParameter("email") + ""});
        tempKeySet.add(new String[]{"state",request.getParameter("state") + ""});
        tempKeySet.add(new String[]{"city",request.getParameter("city") + ""});
        tempKeySet.add(new String[]{"grade",request.getParameter("grade") + ""});
        tempKeySet.add(new String[]{"biography",request.getParameter("biography") + ""});

        for (int i = 0; i < tempKeySet.size(); i++){
            if (!tempKeySet.get(i)[1].equalsIgnoreCase("")){
                keyset.put(tempKeySet.get(i)[0], tempKeySet.get(i)[1]);
            }
        }

        if (keyset.size() <=0){
            %><script>
                alert("At least one key entry is required.")
                return false;
            </script><%
        }

        SearchController sController = new SearchController();
        ArrayList<StudentInfo> resultset = sController.searchForStudents(keyset, currPath);
        
        if (resultset.size() > 0 ){
            %>
            <div class="results" style="display:inline;">
              <table class="table table-striped">
                  <h2>Search Results</h2>
                  <thead>
                    <tr>
                      <td></td>
                      <td>Name</td>
                      <td>High school</td>
                      <td></td>
                    </tr>
                  </thead>
                  <tbody>
                      <%
                          for (int i =0;i<resultset.size();i++){
                              %>
                              <tr>
                                  <td><img src="resources/images/profileDefault.png" alt="..."></td>
                                <td><%= resultset.get(i).getFirstName() + " " + resultset.get(i).getLastName() %></td>
                                <td><%= resultset.get(i).getHighSchool() %></td>
                                <td><a href=<%= "profile.jsp?user="  + resultset.get(i).getEmail() + "&loginUser=" + userType %> class="btn btn-primary btn-lg active" role="button">Show Profile</a></td>
                              </tr>
                      <%
                          }
                          %>
                  </tbody>
              </table>
            </div><%
        }
    }
                    %>
    

  <div class="row">
      <%
            String currentPath = getServletContext().getContextPath();
            ShowCaseController scController = new ShowCaseController(currentPath);
            ArrayList<University> arrUniv = scController.getAllFeaturedUnivs();
            
            for (int i = 0; i<arrUniv.size();i++){
                %><div class="col-sm-6 col-md-4">
                    <span class="label label-success">Featured University</span>
                        <div class="thumbnail">
                            <img src=<%= arrUniv.get(i).getPhoto1().getName() %> alt=<%= arrUniv.get(i).getUniversityName() %>>
                            <div class="caption">
                            <h3><%= arrUniv.get(i).getUniversityName() %></h3>
                            <p><%= arrUniv.get(i).getDescription() %></p>
                            <p><a href=<%= arrUniv.get(i).getUrl() %> class="btn btn-primary" target="_blank" role="button">Learn more</a></p>
                            </div>
                        </div>
                </div><%
            }
                    %>

  </div>


  <div class="page-footer">
    <a href="#top"><i class="glyphicon glyphicon-hand-up"></i>  Back To Top</a>
  </div>


</div>

<!--  Main content ends here 
  <script>
    $("#search").click(function() {
      $(".results").css("display","inline");
      return false;
    });
  </script>-->
</body>


</html>