<%-- 
    Document   : signup
    Created on : Nov 22, 2014, 8:55:14 PM
    Author     : maferre
--%>

<%@page import="controller.StudentInfoController"%>
<%@page import="controller.MailController"%>
<%@page import="controller.SignUpController"%>
<%@page import="controller.SignUpValidator"%> 
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Sign up</title>
          <meta http-equiv="content-language" content="en">
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <meta name="description" content="Upload Page">
          <meta name="keywords" content="IT353, ISU, Michael, Ferrer, maferre@ilstu.edu">
          <meta name="author" content="Michael Ferrer">
          <script src="resources/js/jquery-2.1.1.min.js"></script>
          <script src="resources/js/bootstrap.min.js"></script>
          <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
    </head>
    <body>
    <div class="container">

    <div class="page-header"  style="display:inline-block;">
      <nobr><a href=""><img src="resources/images/logo.png" alt="Linked U" title="Linked U" ></a>
      <h1>Signup <small> Get started on your road to success</small></h1></nobr>
    </div>
    <form method="post" action="signup.jsp">
      <div class="input-group">
        <span class="input-group-addon">First Name </span>
        <input type="text" name="firstName" class="form-control" placeholder="">
       </div>
       <div class="input-group">
        <span class="input-group-addon">Last Name </span>
        <input type="text" name="lastName" class="form-control" placeholder="">
       </div>
       <div class="input-group">
        <span class="input-group-addon">E-mail Address </span>
        <input type="email" name="emailAddress" class="form-control" placeholder="">
       </div>
       <div class="input-group">
        <span class="input-group-addon">Password </span>
        <input type="password" name="password" class="form-control" placeholder="">
       </div>
        <div class="form-group">
            <!-- <select class="form-control" name="user_type" style='width:200px'>
              <option value="D" selected="selected" >User Type</option> -->
			<span class="input-group-addon">User Type    </span>
            <select class="form-control" name="user_type" style='width:200px' required requiredMessage="Please select a user type">
              <option value="D" selected="selected" disabled > - Select One - </option>
              <option value="S" >Student</option>
              <option value="R" >Recruiter</option>
            </select>
        </div>
    <input name="goback" type="submit" class="btn btn-default" value="Submit">
    <input name="reset" type="reset" class="btn btn-default" value="Reset">
    </form>
    </div>
         
    </body>
    <%
        String button = request.getParameter("goback") + "";
        if(button.equals("Submit"))
        {
            String fName = request.getParameter("firstName");
            String lName = request.getParameter("lastName");
            String uType = request.getParameter("user_type");
            String email = request.getParameter("emailAddress");
            String pass = request.getParameter("password");
            
            //Creates the validation class.
            SignUpValidator test = new SignUpValidator(fName,lName,email,pass,uType);
            
            boolean nameTrue = test.checkName();
            boolean emailTrue = test.checkEmail();
            boolean passTrue = test.checkPassword();
            boolean userTrue = test.checkUserType();
            
            if(!nameTrue)
            {
                %><script>
                alert("Please make sure the name is valid!");
                </script><%
            }
            
            if(!emailTrue)
            {
                %><script>
                alert("Please make sure the email address is valid!");
                </script><%
            }
            
            if(!passTrue)
            {
                %><script>
                alert("Password needs to be atleast 8 characters!");
                </script><%
            }
            
            if(!userTrue)
            {
                %><script>
                alert("Please make sure a proper user type is selected!");
                </script><%
            }
            
            if(!nameTrue || !emailTrue || !passTrue || !userTrue)
            {
                %><script>
                alert("Please check the fields for proper information and try again!");
                </script><%
            }
            
            else
            {
                User newUser = new User(fName, lName, uType, email, pass);
                
                //Signs up a new user in the User Table.
                int result = SignUpController.signUp(newUser);
			
                //Uses the User object to also populate the StudentInfo table.
                result = StudentInfoController.createStudentInfoFromUser(newUser);
			
                if (result > 0)
                {
                    String type = "Recruiter";
                    if (request.getParameter("user_type").equalsIgnoreCase("S"))
                    {
                        type = "Student";
                    }
                        String messageContent = 
                        "<h1>Welcome to Linked U!</h1>"
                                + "<h2>The following is your account information.<br />"
                                + "Your name: " + request.getParameter("firstName") + " " + request.getParameter("lastName") + "<br />"
                                + "Email Address: " +  request.getParameter("emailAddress") + "<br />"
                                + "Your account type: " + type + "<br /><br />"
                                + "Let's get started!</h2>";
                                MailController.sendEmail(request.getParameter("emailAddress"), "Welcome to Linked U, " + request.getParameter("firstName")
                                + " " + request.getParameter("lastName") + "!", messageContent );
                %><script>alert("Confirmation email was sent to your email address!");
                </script>
                <jsp:forward page="index.jsp" /><%
                } 
            }
        }
     %>
</html>

