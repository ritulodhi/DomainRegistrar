package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection con;
	PreparedStatement ps;
	ResultSet rs=null;
	RequestDispatcher rd;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{  Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
     }
  catch(Exception e){ e.printStackTrace();}
		PrintWriter out = response.getWriter();
		 response.setContentType("text/html; charset=UTF-8");
		//request.getRequestDispatcher("Login.html").include(request, response);
		try{
         
         
        String n=request.getParameter("username");  
        String p=request.getParameter("userpass"); 
        String query = "SELECT * from userlogin WHERE username = ? and password = ? "; 
       ps = con.prepareStatement(query);
        ps.setString(1,n);
        ps.setString(2,p);
        rs = ps.executeQuery();
       if(rs.next())
        {
        	 
        	  int id =rs.getInt(1);
        	      
        	  Enc e=new Enc();
        	String id1=e.encrypt(id);
        	HttpSession session = request.getSession(true);
        	session.setAttribute("id",id1);
        	request.setAttribute("id",id1);
        	rd = request.getRequestDispatcher("./CustHome.jsp");
            rd.forward(request,response); 
            
          
        }
      
        else
        {
        	out.print("invalid user");
        	rd = request.getRequestDispatcher("Login.html");
           rd.include(request, response);
           
        }
       out.close();
       con.close();
		}catch(Exception e){
			
			System.out.println(e);
		}
		  
	}
	}
