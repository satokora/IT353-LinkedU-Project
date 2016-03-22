<%-- 
    Document   : email
    Created on : Dec 3, 2014, 8:55:30 PM
    Author     : skora
--%>

<%@page import="model.User"%>
<%@page import="dao.LoginDAOImpl"%>
<%@page import="dao.LoginDAO"%>
<%@page import="controller.MailController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Email</title>
          <meta http-equiv="content-language" content="en">
          <meta charset="UTF-8">
          <meta name="description" content="Upload Page">
          <meta name="keywords" content="IT353, ISU, Michael, Ferrer, maferre@ilstu.edu">
          <meta name="author" content="Michael Ferrer">
          <script src="./resources/js/jquery-2.1.1.min.js"></script>
          <script src="./resources/js/bootstrap.min.js"></script>

          <link rel="stylesheet" type="text/css" href="./resources/css/bootstrap.min.css">
    </head>
    <body>
    <div class="container">
        
        <%
            String toEmail = request.getParameter("toEmail");
            String userEmail = request.getParameter("userEmail");
            String userType = request.getParameter("userType");
        %>

        <div class="container">
        
    <div class="page-header"  style="display:inline-block;">
      <nobr><a href=""><img src="./resources/images/logo.png" alt="Linked U" title="Linked U" ></a>
      <h1>Contact Recruiter <small> Get started on your road to success</small></h1></nobr>
    </div>
        <form method="post" action="email.jsp">
            <div class="input-group">

             </div>
             <div class="input-group">
              <span class="input-group-addon">To </span>
              <input type="email" name="to" class="form-control" placeholder="" value=<%= toEmail + "" %> >
             </div>
                 <input type="text" name="title" class="form-control" placeholder="Title">
             <textarea class="form-control" rows="10" name="content" placeholder="Content"></textarea>
             <input type="hidden" name="from" value=<%= userEmail %>>

             <input name="send" type="submit" class="btn btn-default" value="Send" >
             <input name="reset" type="reset" class="btn btn-default" >
        </form>
        <%
            String userFromEmail = request.getParameter("from") + "";
            String userToEmail = request.getParameter("to") + "";
            String title = request.getParameter("title") + "";
            
            String button = request.getParameter("send") + "";
            if (button.contentEquals("Send")){
                LoginDAO dao = new LoginDAOImpl();
                User fromUser = dao.getUser(userFromEmail);
                String content = "Message From: " + fromUser.getFirstName() + " " + fromUser.getLastName() + "<br />Reply to: <a>" + fromUser.getEmailAddress() + "</a><br />"  + request.getParameter("content") + "<br />";
                System.out.println(content);
                
                %>
                <script>
                    window.alert("Email sent to " + <%= userToEmail %> + "! You will be contacted shortly!");
                </script>
                    
                
                <%
                
                MailController.sendEmail(userFromEmail,userToEmail, title, content);
                
                %>
                
                <script>
                    window.close();
                </script>
                    
                
                <%
                
            }
            
        %>
    </div>
         
    </body>

</html>

