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
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        
  }
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
    		try{
            response.setContentType("text/html; charset=UTF-8");  
             
            String n=request.getParameter("username");  
            String p=request.getParameter("userpass"); 
            String query = "SELECT * from admin WHERE username = ? and password = ? "; 
           ps = con.prepareStatement(query);
            ps.setString(1,n);
            ps.setString(2,p);
            rs = ps.executeQuery();
           if(rs.next())
            {
            	 String uname=rs.getString(1);
            	HttpSession session = request.getSession(true);
            	session.setAttribute("id",uname);
            	rd = request.getRequestDispatcher("./AdminHome.jsp");
                rd.forward(request,response); 
                
              
            }
          
            else
            {
            	out.print("Invalid Admin");
            	rd = request.getRequestDispatcher("LoginAdmin.html");
               rd.include(request, response);
               
            }
           con.close();
    		}catch(Exception e){
    			
    			System.out.println(e);
    		}
    		  
    	}
    	}
