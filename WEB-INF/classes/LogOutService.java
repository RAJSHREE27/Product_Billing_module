import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LogOutService extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		
		PrintWriter pw  = response.getWriter();
		response.setContentType("text/html");
		pw.print("<html>");
		pw.print("<head>");
		pw.print("<title>Logged</title>");
		pw.print("</head>");
		pw.print("<body>");
		
		HttpSession session = request.getSession(false);
		
		if(session != null){
			//if session is existing;
			session.invalidate();
			pw.print("<h1>Successfully logged out!</h1>");
			
		}else{
			pw.print("<h1>Session already logged out !</h1>");
			
		}
		
		pw.print("<a href='Index.html'>re-login</a>");
		pw.print("</body>");
		pw.print("</html>");
		
	}
	
	
}//end class