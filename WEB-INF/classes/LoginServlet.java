/*------------------- Login -----------------*/

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		
		
		String username1 = request.getParameter("name");
		String password1 = request.getParameter("psswrd");
		// username and password obtained
		boolean successful= false;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		
		
		try{
			
			con = SisDbUtil.getConnection();
			String query = "select * from LoginUser where username = ? and password = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, username1);
			pstmt.setString(2,password1);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				successful = true;
				
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				SisDbUtil.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(successful == false){
			
			response.getWriter().print("<h1 style='color:red'>INVALID USERNAME OR PASSWORD</h1>");
			RequestDispatcher rd = request.getRequestDispatcher("Login.html");
			rd.include(request , response);
			
		}else if(successful == true){
			HttpSession session = request.getSession();
			session.setAttribute("user", username1);
			
			String loc = "welcome";			//set in web.xml
			response.sendRedirect(loc);
			
		}
		
		
	}
	
	
}