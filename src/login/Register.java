package login;

import java.io.*;  

import java.sql.*;  

import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;  

@WebServlet("/Register")
public class Register extends HttpServlet {  
/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
response.setContentType("text/html");  
PrintWriter out = response.getWriter();  
          
String name=request.getParameter("CustomerName");  
String phone=request.getParameter("Phone");  
String email=request.getParameter("Email");  
//String username=request.getParameter("username"); 
String password=request.getParameter("password"); 
          
try{  
Class.forName("oracle.jdbc.driver.OracleDriver");  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:xe","hr","hr");  
  
PreparedStatement ps=con.prepareStatement("insert into customerdetails values(seq_id.nextval,?,?,?)");  
  
ps.setString(2,name);  
ps.setString(3,phone);  
ps.setString(4,email);  
//ps.setString(5,c);            
ps.executeUpdate();      
    ps=con.prepareStatement(  
    		"select MAX(id) from customerdetails"); 
    ResultSet rs=ps.executeQuery();
    rs.next();
    int id=rs.getInt(1);
    String uname=name+id;
    ps=con.prepareStatement(  
    		"insert into userlogin values(?,?,?)");
    ps.setInt(1,id);
    ps.setString(2,uname);
    ps.setString(3,password);
    int i= ps.executeUpdate();
    if(i>0){  
    	out.print("You are successfully registered...");
    	out.print("<a href=./Login.html>please login with your user name: "+uname+" and choosen password</a>");
    }
}catch (Exception e2) {System.out.println(e2);}  
          
out.close();  
}  
  
}  
