<%-- 
    Document   : usearch
    Created on : Dec 3, 2014, 4:38:01 PM
    Author     : skora
--%>

<%@page import="model.RecruiterInfo"%>
<%@page import="controller.SearchController"%>
<%@page import="java.util.HashMap"%>
<%@page import="model.University"%>
<%@page import="controller.ShowCaseController"%>
<%@page import="model.State"%>
<%@page import="dao.SearchDAOImpl"%>
<%@page import="model.Major"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
  <title>University Search</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="content-language" content="en">
  <meta charset="UTF-8">
  <meta name="description" content="This is University Search page for IT353 Project.">
  <meta name="keywords" content="IT353, ISU">
  <script src="./resources/js/jquery-2.1.1.min.js"></script>
  <script src="./resources/js/bootstrap.min.js"></script>
  <link rel="stylesheet" type="text/css" href="./resources/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="./resources/css/project.css">
  <link rel="shortcut icon" href="favicon.ico">

</head>
<body>
<!-- Paste navigation bar after body tag -start-  -->
<%
    String userEmail = request.getParameter("userEmail");
    String userType = request.getParameter("userType") + "";
%>

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
<!-- Paste navigation bar after body tag -end- -->

  <div class="container">
<form>
    <div class="page-header"  style="display:inline-block;">
      <h1>University Search <small> Find the right school for you!</small></h1>
    </div>
    <div class="bs-example bs-navbar-top-example">



    <div class="panel panel-default">
      <div class="panel-heading" role="tab">
      <div class="row">
      <div class="col-lg-6">

        <div class="form-group">
          <label for="univ">College name</label>
          <input type="text" class="form-control" name="univ_name" placeholder="Name of Institution" >
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
          <div class="form-group" style="float:left;">
            <label for="stateList">State</label>
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
        <div class="form-group" style="clear:both;">
            <label for="grade">GPA</label>
            <input type="number" class="form-control" name="grade" min="0.00" max="4.00" step="0.01"  >
        </div>

      </div>
        <hr>
        <div class="form-group" style="float:left;margin-left:15px">
        <input name="search" type="submit" class="btn btn-primary btn-lg" value="Search for schools">
        <input type="reset" class="btn btn-primary btn-lg" value="Clear Criteria">
        <h4  style="float:right;margin-left:15px"><a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          Advanced Search
        </a></h4>
        </div>
        </div>
      </div>
      <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
        <div class="panel-body">
        
        
          <div class="form-group">
            <label for="keyword">Free Keyword</label>
            <input type="text" class="form-control" name="description" placeholder="small class..." >
          </div>
        </div>
      </div>
    </div>
            <input type="hidden" name="userEmail" value=<%= userEmail %>>
            <input type="hidden" name="userType" value=<%= userType %>>
  </form>


     
  </div>
  <%
    String currPath = getServletContext().getContextPath();                
    String button = request.getParameter("search") + "";
    if(button.equals("Search for schools")){
        HashMap keyset = new HashMap();
        ArrayList<String[]> tempKeySet = new ArrayList<String[]>();

        tempKeySet.add(new String[]{"major_id",request.getParameter("major_id") + ""});
        tempKeySet.add(new String[]{"univ_name",request.getParameter("univ_name") + ""});
        tempKeySet.add(new String[]{"state",request.getParameter("state") + ""});
        tempKeySet.add(new String[]{"city",request.getParameter("city") + ""});
        tempKeySet.add(new String[]{"grade",request.getParameter("grade") + ""});
        tempKeySet.add(new String[]{"description",request.getParameter("description") + ""});

        for (int i = 0; i < tempKeySet.size(); i++){
            if (!tempKeySet.get(i)[1].equalsIgnoreCase("")){
                keyset.put(tempKeySet.get(i)[0], tempKeySet.get(i)[1]);
            }
        }

        if (keyset.size() <=0){
            %><script>
                alert("At least one key entry is required.");
                return false;
            </script><%
        }

        SearchController sController = new SearchController();
        ArrayList<RecruiterInfo> resultset = sController.searchForSchools(keyset, currPath);
        
        if (resultset.size() > 0 ){
            %>
            <div class="results" style="display: inline;">
                <table class="table table-striped">
                <h2>Search Results</h2>
                <thead>
                  <tr>
                    <td></td>
                    <td>Institution</td>
                    <td>Official Page</td>
                    <td>Recruiter Name</td>
                    <td></td>
                  </tr>
                </thead>
                <tbody>
                      <%
                          for (int i =0;i<resultset.size();i++){
                              %>
                              <tr>
                                <td><img src=<%= resultset.get(i).getUniv().getPhotoPath1() %> alt="..."></td>
                                <td><%= resultset.get(i).getUniv().getUniversityName() %></td>
                                <td><a href=<%= resultset.get(i).getUniv().getUrl() %> class="btn btn-primary btn-lg active" role="button">Learn more</a></td>
                                <td><%= resultset.get(i).getFirstName() + " " + resultset.get(i).getLastName() %></td>
                                <td><a href=<%= "email.jsp?toEmail=" + resultset.get(i).getEmail() + "&userEmail=" + userEmail + "&userType=" + userType %> target="_blank" class="btn btn-primary btn-lg active" role="button">Contact</a></td>
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

 <%
            String currentPath = getServletContext().getContextPath();
            ShowCaseController scController = new ShowCaseController(currentPath);
            ArrayList<University> arrUniv = scController.getAllFeaturedUnivs();
            
            for (int i = 0; i<arrUniv.size();i++){
                %><div class="col-sm-6 col-md-4">
                    <span class="label label-success">Featured University</span>
                        <div class="thumbnail">
                            <img src=<%= arrUniv.get(i).getPhotoPath1() %> alt=<%= arrUniv.get(i).getUniversityName() %>>
                            <div class="caption">
                            <h3><%= arrUniv.get(i).getUniversityName() %></h3>
                            <p><%= arrUniv.get(i).getDescription() %></p>
                            <p><a href=<%= arrUniv.get(i).getUrl() %> class="btn btn-primary" target="_blank" role="button">Learn more</a></p>
                            </div>
                        </div>
                </div><%
            }
                    %>

  <div class="page-footer">
    <a href="#top"><i class="glyphicon glyphicon-hand-up"></i>  Back To Top</a>
  </div>
  </div>


 <!-- Main content ends here -->

</body>


</html>