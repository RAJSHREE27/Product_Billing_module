import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class OptionSelectedServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		
		String selectedOption = request.getParameter("item_type");
		
		if("Clothes".equals(selectedOption)){
			RequestDispatcher rd = request.getRequestDispatcher("Option1.html");
			rd.forward(request,response);
			
		}else if("Books".equals(selectedOption)){
			RequestDispatcher rd = request.getRequestDispatcher("Option2.html");
			rd.forward(request,response);
			
		}else if("Electronics".equals(selectedOption)){
			RequestDispatcher rd = request.getRequestDispatcher("Option3.html");
			rd.forward(request,response);
			
		}
		
	}