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
 * Servlet implementation class Dname
 */
@WebServlet("/Dname")
public class Dname extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dname() {
        super();
        // TODO Auto-generated constructor stub
    }
    Connection con;
 	PreparedStatement ps;
 	ResultSet rs=null;
 	RequestDispatcher rd;
 int n;
 String id;

 	 
 	              

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	     try{  Class.forName("oracle.jdbc.driver.OracleDriver");
          con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
       }
    catch(Exception e){ e.printStackTrace();}
	     response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession(false);
		if(session!=null){
		try{
			request.getRequestDispatcher("Logoutlink.html").include(request, response);
       id=request.getParameter("value");
       Enc e=new Enc();
        n=e.decrypt(id);
       
        	 
        String query = "SELECT * from domain WHERE id=? "; 
       ps = con.prepareStatement(query);
        ps.setInt(1,n);
        //ps.setString(2,p);
        rs = ps.executeQuery();
        String dname,ip;
        out.print("<table border=\"1\" align=\"center\">");
    	out.print("<tr><td>domain name</td><td>IP Address</td></tr>");
    	
      while(rs.next())
        {     
    	  dname=rs.getString(2);
    	  ip=rs.getString(3);
    	  
    	  out.print("<tr><td>"+dname+"</td><td>"+ip+"</td></tr>");
          
        }
      out.print("</table>");
       con.close();
		}catch(Exception e){
			
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