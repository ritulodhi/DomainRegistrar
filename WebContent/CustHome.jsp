<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.net.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Customer</title>
<meta name="keywords" content="free css templates, education, school, college, university, templatemo.com" />
<meta name="description" content="Education template is for academic related websites" />

<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="templatemo_header_wrapper">
    <div id="templatemo_header">
    	


    </div> <!-- end of templatemo_header -->

</div> <!-- end of templatemo_menu_wrapper -->
<div id="templatemo_menu_wrapper">
    <div id="templatemo_menu">
        
         <ul>
           <li><a href="Visitor.html">Home</a></li>
                    <li><a href="LoginAdmin.html">Adminstrator</a></li>
                    <li><a href="Registration.html">Registration</a></li>
                    <li><a href="Login.html">Login</a></li>
                    <li><a href="VisitorChecker.jsp">Available Domains</a></li>
                    <li><a href="contactus.jsp">Contact Us</a></li>
                </ul>    	
     
    </div> <!-- end of templatemo_menu -->
</div>
<div id="templatemo_content_wrapper" align="center">

	
    <div id="templatemo_content">
    
    	<div class="content_box">
            <h3>Customer</h3>
            <div align="right">
                <jsp:include page="Logoutlink.html" />
            </div>
            <div align="center">
            <%!HttpSession session;%>
            <% session=request.getSession(false); %>

            <% if(session!=null){
            %>
             <a href="./Profile?value=<%=session.getAttribute("id")%>" style="color: black">Profile</a>
             <br>

            <a href="./Dname?value=<%=session.getAttribute("id")%>" style="color: black">Registered Domains</a>
            <br>
            <a href="index1.jsp?value=<%=session.getAttribute("id")%>" style="color: black">New Purchase</a>

            <%
            }

            else {%>

            login first
            <%  } %>

                </div>	


        	<div class="cleaner"></div>
        </div><div class="content_box_bottom" align="center"></div>
        
        
    
    </div> <!-- end of content -->
    
    <div class="cleaner"></div>

</div>

<div id="templatemo_footer_wrapper">

    <div id="templatemo_footer">
    
        <ul class="footer_menu">
           <li><a href="Visitor.html">Home</a></li>
                    <li><a href="LoginAdmin.html">Adminstrator</a></li>
                    <li><a href="Registration.html">Registration</a></li>
                    <li><a href="Login.html">Login</a></li>
                    <li><a href="VisitorChecker.jsp">Available Domains</a></li>
                    <li><a href="contactus.jsp">Contact Us</a></li>
                </ul>    
    </div>
</div>
</body>
</html>
