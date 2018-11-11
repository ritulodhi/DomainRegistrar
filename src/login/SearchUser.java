package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SearchUser
 */
@WebServlet("/SearchUser")
public class SearchUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUser() {
        super();
        // TODO Auto-generated constructor stub
    }

    Connection con;
	PreparedStatement ps;
	ResultSet rs=null,rs1;
	int id;
	String name,phone,email,dname,ip;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{  Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
     }
  catch(Exception e){ e.printStackTrace();}
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession(false);
		if(session!=null){
		try{
        response.setContentType("text/html; charset=UTF-8");  
         
        String n=request.getParameter("username");  
        
        String query = "SELECT id from userlogin WHERE username = ?"; 
       ps = con.prepareStatement(query);
        ps.setString(1,n);
        rs = ps.executeQuery();
        rs.next();
        id=rs.getInt(1);
       if(rs!=null)
        {
    	   
  rs=null;
  String query1 = "SELECT * from customerdetails WHERE id = ?";
  String query2 = "SELECT * from domain WHERE id = ?";
  ps = con.prepareStatement(query1);
  ps.setInt(1,id);
  rs = ps.executeQuery();
  ps = con.prepareStatement(query2);
  ps.setInt(1,id);
  rs1 = ps.executeQuery();
  //rs.next();
  out.print("<table border=\"1\">");
	out.print("<tr><td>First Name</td><td>Last Name</td><td>Phone</td><td>domain name</td><td>IP Address</td></tr>");
	while( rs1.next())
	{ 
		if(rs.next())
		{
	 name=rs.getString(2);
	 phone=rs.getString(3);
	 email=rs.getString(4);
		}
	 dname=rs1.getString(2);
	 ip=rs1.getString(3);
	 out.print("<tr><td>"+name+"</td><td>"+phone+"</td><td>"+email+"</td><td>"+dname+"</td><td>"+ip+"</td></tr>");
		
	}
	out.print("</table>");
	 
 	
        }
      
        else
        {
        	out.print("invalid username");
        	
           
        }
       con.close();
		}catch(Exception e){
			
			System.out.println(e);
		}
		}
		else
		{
			request.getRequestDispatcher("LoginAdmin.html").include(request, response);
			out.print("<h3 style=\"color:red\">please login first");
		}
		 out.close(); 
	}
}
	