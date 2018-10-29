//shows all the previous transactions of a particular user


import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class HistoryServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		
		HttpSession session = request.getSession();
			
		String username = (String)session.getAttribute("user");
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		
		String user;
		String transaction;
		String date;
		
		try{
			
			con = SisDbUtil.getConnection();
			
			String query = "select * from History where username=?";
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			
			pw.print("<html>");
			pw.print("<head>");
			pw.print("<title>History</title>");
			pw.print("</head>");
			
			pw.print("<body style='text-align:center;background:#DAF7A6;'>");
			pw.print("<a href='logout' style='margin-left:70em;'>Logout</a>");
			pw.print("<hr>");
			pw.print("<hr>");
		
			
			pw.print("<h3> Previous Transactions of : "+username+"</h3><br><br>");
		
			pw.print("<table border=\"1px\">");
			pw.print("<tr>");
			pw.print("<th> User </th>");
			pw.print("<th> Bill Amount </th>");
			pw.print("<th> Transaction Occurred </th>");
			pw.print("</tr>");
			
			
			
			while(rs.next()){
				
				user= rs.getString(1);
				transaction = rs.getString(2);
				date = rs.getString(3);
				
				pw.print("<tr>");
				pw.print("<td>"+user+"</td>");
				pw.print("<td>"+transaction+"</td>");
				pw.print("<td>"+date+"</td>");
				pw.print("</tr>");
				
				
			}
			
		}catch (Exception e) {
			
		}finally{
			try {
				SisDbUtil.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		pw.print("</table>");
		pw.print("</body>");
		pw.print("</html>");
			
	}
}