package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
/*	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
*/
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	  
	 
	  
	  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Connection con = null;
			PreparedStatement ps;
			ResultSet rs=null;
			
		int n;
		String id=null;
		
		try{  Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
     }
  catch(Exception e){ e.printStackTrace();}
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session=request.getSession(false);
		if(session!=null){
		try{
			request.getRequestDispatcher("Logoutlink.html").include(request, response);
     id=request.getParameter("value");
     Enc e=new Enc();
     n=e.decrypt(id);
        	 
        String query = "SELECT * from customerdetails WHERE id=?"; 
       ps = con.prepareStatement(query);
      ps.setInt(1,n);
        rs = ps.executeQuery();
        String fname,lname,phone,email;
        	rs.next();
    	  fname=rs.getString(2);
          lname=rs.getString(3);
    	  phone=rs.getString(4);
    	 email=rs.getString(5);
    	 out.print("<table border=\"1\" align=\"center\">");
     	out.print("<tr><th>First Name</th><th>Last Name</th><th>Phone</th><th>Email</th></tr>");
     	out.print("<tr><td>"+fname+"</td><td>"+lname+"</td><td>"+phone+"</td><td>"+email+"</td></tr>");
     	 out.print("</table>"); 
       con.close();
		}
       catch(Exception e){
			
			System.out.println(e);
		
		}
		}
		else
		{
			request.getRequestDispatcher("Login.html").include(request, response);
			out.print("<h3 style=\"color:red\">please login first");
			
		}
		out.close();
		}
		  
	}
		
	


