/*------------------- SignUp -----------------*/

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SignUpServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		System.out.println("OK");
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		System.out.println("OK");
		
		String username2 = request.getParameter("siName");
		String password2 = request.getParameter("siPsswrd");
		// username and password obtained
		
		if(username2==null || username2.trim().isEmpty()){
			 response.sendRedirect("SignUp.html");
			 return;
		 }
		 
		 if(password2==null || password2.trim().isEmpty()){
			 response.sendRedirect("SignUp.html");
			 return;
		 }
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			con = SisDbUtil.getConnection();
			String query = "insert into LoginUser values (? , ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, username2);
			pstmt.setString(2,password2);
			
		   int val = pstmt.executeUpdate();
			
			/* check if theres any null value or not ************/
			
		}catch (Exception e) {
			
		}finally{
			try {
				SisDbUtil.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//-----------------------------------------------------------------------------------------
		
			HttpSession session = request.getSession();
			session.setAttribute("user", username2);
			
			String loc = "welcome";			//set in web.xml
			response.sendRedirect(loc);//new get request 
		
			
	}
	
	
}