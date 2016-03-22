<%-- 
    Document   : index
    Updated on : Nov 22, 2014, 5:50:18 PM
    Author     : skora
--%>

<%@page import="controller.LoginController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
  <!--<link rel="shortcut icon" href="http://www.iconj.com/ico/r/5/r5b17qv18e.ico" type="image/x-icon" />-->
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Welcome to LinkedU - Log In, Sign Up, or Learn More</title>
  <link rel="stylesheet" type="text/css" href="resources/css/login.css">
    <script src="resources/js/prefixfree.min.js"></script>
    <style>
        
@import url(http://fonts.googleapis.com/css?family=Exo:100,200,400);
@import url(http://fonts.googleapis.com/css?family=Source+Sans+Pro:700,400,300);

body{
	margin: 0;
	padding: 0;
	background: #fff;

	color: #fff;
	font-family: Arial;
	font-size: 12px;
	
	height: screen.height;
	width: screen.width;
}

.body{
	position: absolute;
	top: -20px;
	left: -20px;
	right: -40px;
	bottom: -40px;
	width: auto;
	height: auto;
	background-image: url("resources/images/isu2.jpg");
	background-size: cover;
	-webkit-filter: blur(8px);
	z-index: 0;
}

.grad{
	position: absolute;
	top: -20px;
	left: -20px;
	right: -40px;
	bottom: -40px;
	width: auto;
	height: auto;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(0,0,0,0)), color-stop(100%,rgba(0,0,0,0.65))); /* Chrome,Safari4+ */
	z-index: 1;
	opacity: 0.7;
}

.header{
	position: absolute;
	top: calc(50% - 60px);
	left: calc(50% - 255px);
	z-index: 2;
}

.header div{
	float: left;
	color: #fff;
	font-family: 'Exo', sans-serif;
	font-size: 35px;
	font-weight: 200;
}

.header div span{
	color: #5379fa !important;
}

.login{
	position: absolute;
	top: calc(50% - 75px);
	left: calc(50% - 50px);
	height: 150px;
	width: 350px;
	padding: 10px;
	z-index: 2;
}

.login input[type=email]{
	width: 250px;
	height: 30px;
	background: transparent;
	border: 1px solid rgba(255,255,255,0.6);
	border-radius: 2px;
	color: #fff;
	font-family: 'Exo', sans-serif;
	font-size: 16px;
	font-weight: 600;
	padding: 4px;
}

.login input[type=password]{
	width: 250px;
	height: 30px;
	background: transparent;
	border: 1px solid rgba(255,255,255,0.6);
	border-radius: 2px;
	color: #fff;
	font-family: 'Exo', sans-serif;
	font-size: 16px;
	font-weight: 400;
	padding: 4px;
	margin-top: 10px;
}

.login input[type=submit]{
	width: 260px;
	height: 35px;
	background: #fff;
	border: 1px solid #fff;
	cursor: pointer;
	border-radius: 2px;
	color: #a18d6c;
	font-family: 'Exo', sans-serif;
	font-size: 16px;
	font-weight: 400;
	padding: 6px;
	margin-top: 10px;
}

.login input[type=submit]:hover{
	opacity: 0.8;
}

.login input[type=submit]:active{
	opacity: 0.6;
}

.login input[type=email]:focus{
	outline: none;
	border: 1px solid rgba(255,255,255,0.9);
}

.login input[type=password]:focus{
	outline: none;
	border: 1px solid rgba(255,255,255,0.9);
}

.login input[type=submit]:focus{
	outline: none;
}



::-webkit-input-placeholder{
   color: rgba(255,255,255,0.6);
}

::-moz-input-placeholder{
   color: rgba(255,255,255,0.6);
}

    </style>
    <link rel="shortcut icon" href="http://www.iconj.com/ico/r/5/r5b17qv18e.ico" type="image/x-icon" />
</head>

<body>
	<div class="body">
	</div>
	
	<div class="grad">
	</div>
	
	<div class="header">
		<img src="resources/images/logo-whiteLinked.png" alt="LinkedU">
	</div>
	<br>
	
	<div class="login">
            <form method="post"  action="index.jsp">
		<input type="email" placeholder="email address" name="user"><br>
		<input type="password" placeholder="password" name="password"><br>
		<input type="submit" id="login" name="login" value="Login"><br>
		<input type="submit" id="signup" name="signUp" value="Sign Up"><br>
                  <%
      response.setHeader("header", "header");
      String submit = request.getParameter("login") + "";
      String signup = request.getParameter("signUp") + "";
           
      if(submit.equals("Login")){
         String user = request.getParameter("user") + "";
         String password = request.getParameter("password") + "";
         
         int result = LoginController.login(user, password);
         
         if (result > 0){
             
             %><jsp:forward page="showcase.jsp">
                 <jsp:param name="userEmail" value="<%= user %>" />
                 <jsp:param name="userType" value="<%= LoginController.getUser().getUserType() %>" />
                </jsp:forward><%
         }
         
         else
         {
             %><script>
                 alert("Could not connect to LinkedU with those credentials! Please try again!");
               </script>
                 <%
         }
        }
      
      if(signup.equals("Sign Up")){

             %><jsp:forward page="signup.jsp" /><%

        }
      %>
            </form>
	</div>
        

  <script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>


</body>

</html>
