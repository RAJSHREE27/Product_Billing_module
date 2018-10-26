/*------------------- SignUp -----------------*/

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SignUpServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		
		
		String username2 = request.getParameter("siName");
		String password2 = request.getParameter("siPsswrd");
		// username and password obtained
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try{
			
			con = SisDbUtil.getConnection();
			String query = "insert into LoginUser values (? , ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, username2);
			pstmt.setString(2,password2);
			
			rs = pstmt.executeUpdate();
			
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
			response.sendRedirect(loc);
			
	}
	
	
}