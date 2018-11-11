package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class NewPurchase
 */
@WebServlet("/NewPurchase")
public class NewPurchase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewPurchase() {
        super();
        // TODO Auto-generated constructor stub
    }
    Connection con;
 	PreparedStatement ps;
 	ResultSet rs=null;
 	RequestDispatcher rd;
 int n,num;
 String id=null, Dname=null;

	/**
	 * @throws SQLException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
 	  
 	  public int Buy(String name,int id) throws SQLException
 	  {
 		  try{  Class.forName("oracle.jdbc.driver.OracleDriver");
           con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
        }
     catch(Exception e){ e.printStackTrace();}

 		  String lastIP="";
 		 String query = "SELECT MAX(ip) from domain"; 
 	       ps = con.prepareStatement(query);
 	        
 	        rs = ps.executeQuery();
 	        rs.next();
 	        lastIP=rs.getString(1);
 	        String arr[]=lastIP.split("\\.");
 	        for (int i=3;i>=0;i--)
 	        {
 	        	num=Integer.valueOf(arr[i]);
 	        	if(num<255)
 	        	{
 	        		num++;
 	        		String s=String.valueOf(num);
 	        		arr[i]=s;
 	        		break;
 	        	}
 	        }
 	       
 	       lastIP=arr[0]+"."+arr[1]+"."+arr[2]+"."+arr[3];
 	        query="insert into domain values(?,?,?)";
 	        ps=con.prepareStatement(query);
 	        ps.setInt(1,id);
 	        ps.setString(2, name);
 	        ps.setString(3, lastIP);
 	    int s=ps.executeUpdate();
 	    return s;
 	  }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession(false);
		if(session!=null)
		{
		try{
			request.getRequestDispatcher("Logoutlink.html").include(request, response);
        response.setContentType("text/html");  
       
        Dname=request.getParameter("dname");
       id=request.getParameter("id");
       
       if(id!=null)
       			{ 
    	   Enc e=new Enc();
           n=e.decrypt(id);
    	 int s=Buy(Dname,n);
    	 if(s>0)
    	 	{
    		 out.print("Congratulations you have successfully registered for domain. you can find your domain name and ip details via profile section");
    	 	}
       		}
       else
       {
    	   out.print("you have get login for further process");
    	   response.sendRedirect("./Login.html");
    	   
       }
       
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