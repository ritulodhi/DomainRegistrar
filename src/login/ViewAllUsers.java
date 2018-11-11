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
 * Servlet implementation class ViewAllUsers
 */
@WebServlet("/ViewAllUsers")
public class ViewAllUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllUsers() {
        super();
        // TODO Auto-generated constructor stub
    }
    Connection con;
	PreparedStatement ps;
	ResultSet rs=null,rs1;
    int id;
	String lname,fname,phone,email,dname,ip;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
         
        String q="SELECT fname,lname,phone,email, dname, ip FROM customerdetails, domain WHERE  customerdetails.id = domain.id ORDER BY fname";
       ps = con.prepareStatement(q);
       rs = ps.executeQuery();	
        out.print("<table border=\"1\" align=\"center\">");
    	out.print("<tr><td>First Name</td><td>Last Name</td><td>Phone</td><td>Email</td><td>domain name</td><td>IP Address</td></tr>");
    	
while(rs.next())
	{
	
	fname=rs.getString(1);
        lname=rs.getString(2);
	phone=rs.getString(3);
	email=rs.getString(4);
	dname=rs.getString(5);
	ip=rs.getString(6);
	
	out.print("<tr><td>"+fname+"</td><td>"+lname+"</td><td>"+phone+"</td><td>"+email+"</td><td>"+dname+"</td><td>"+ip+"</td></tr>");
	
	}
out.print("</table>");
      
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

	

	