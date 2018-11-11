<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Domain Checker</title>
<meta name="keywords" content="free css templates, education, school, college, university, templatemo.com" />
<meta name="description" content="Education template is for academic related websites" />

<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function loadXMLDoc()
{
var xmlhttp;
var k=document.getElementById("id2").value;
var urls="pass.jsp?ver="+k;

if (window.XMLHttpRequest)
  {
  xmlhttp=new XMLHttpRequest();
  }
else
  {
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4)
    {
        //document.getElementById("err").style.color="red";
        document.getElementById("err").innerHTML=xmlhttp.responseText;
    }
  };
xmlhttp.open("GET",urls,true);
xmlhttp.send();
}
</script>
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
            <div align="center">
            <h4>You Have To Register Yourself For Purchasing The Domain</h4>
            Please enter full qualified domain name: <input type="text" name="dname" id="id2" onkeyup="loadXMLDoc()">
            <span id="err"> </span>
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
