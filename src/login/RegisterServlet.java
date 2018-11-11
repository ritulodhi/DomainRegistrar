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

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
response.setContentType("text/html");  
PrintWriter out = response.getWriter();  
          
String fname=request.getParameter("FirstName"); 
String lname=request.getParameter("LastName");
String phone=request.getParameter("Phone");  
String email=request.getParameter("Email");  
//String username=request.getParameter("username"); 
String password=request.getParameter("Password"); 
          
try{  
Class.forName("oracle.jdbc.driver.OracleDriver");  
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");  
  
PreparedStatement ps=con.prepareStatement("insert into customerdetails values(seq_id.nextval,?,?,?,?)");  
  
ps.setString(1,fname);  
ps.setString(2,lname);
ps.setString(3,phone);  
ps.setString(4,email);  
//ps.setString(5,c);            
ps.executeUpdate(); 
  
      
    ps=con.prepareStatement("select MAX(id) from customerdetails"); 
    ResultSet rs=ps.executeQuery();
    rs.next();
    int id=rs.getInt(1);
    String uname=fname+id;
    ps=con.prepareStatement("insert into userlogin values(?,?,?)");
    ps.setInt(1,id);
    ps.setString(2,uname);
    ps.setString(3,password);
    int i= ps.executeUpdate();
    if(i>0){  
        out.print("</br></br></br><div align=\"center\">");
    	out.print("<h4>You are successfully registered...</h4>");
    	out.print("<a href=./Login.html>please login with your user name:</a> <p style=\"color:red\">"+uname+" </p>and choosen password");
        out.print("</div>");
    }
}catch (Exception e2) {System.out.println(e2);}  
          
out.close();  
} 

}
