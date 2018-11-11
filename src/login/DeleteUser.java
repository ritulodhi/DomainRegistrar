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
 * Servlet implementation class DeleteUser
 */
@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUser() {
        super();
        // TODO Auto-generated constructor stub
    }
    Connection con;
	PreparedStatement ps;
	ResultSet rs=null;
	int id;

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
  String query1 = "delete from customerdetails WHERE id = ?";
  String query2 = "delete from domain WHERE id = ?";
  String query3= "delete from userlogin WHERE id=?";
  ps = con.prepareStatement(query1);
  ps.setInt(1,id);
  ps.executeQuery();
  
  ps = con.prepareStatement(query2);
  ps.setInt(1,id);
  ps.executeQuery();
  
  ps.executeQuery(query3);
  ps.setInt(1,id);
  ps.executeQuery();
  out.print("<h3 style=\"color:red\">succesfully delete record "+n);
  request.getRequestDispatcher("./AdminHome.jsp").include(request, response);
  
 
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