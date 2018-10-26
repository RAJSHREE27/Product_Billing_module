// shows all the products selected along with their prices


import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		
		HttpSession session = request.getSession();
			
		String username = session.getAttribute("user");
		List<Long> Clothes = session.getAttribute("clothItemSelected");
		List<Long> Books = session.getAttribute("bookItemSelected");
		List<Long> Electronics = session.getAttribute("electronicItemSelected");
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		// it takes all the session attributes and shows them as a list
		
		
		pw.print("<html>");
		pw.print("<head>");
		pw.print("<title>ALL_Items</title>");
		pw.print("</head>");
		
		pw.print("<body style='text-align:center;background:#DAF7A6;'>");
		pw.print("<h1 style='background-color:black ; color:white;'>Cart holder's name : "+ username +" </h1>");
		pw.print("<hr>");
		pw.print("<hr>");
		
		
		pw.print("<h3> total items selected : </h3><br>");
		
		pw.print("<table border=\"1px\">");
		pw.print("<tr>");
		pw.print("<th>Item ID </th>");
		pw.print("<th>Item Name</th>");
		pw.print("<th>Item Type</th>");
		pw.print("<th>Price</th>");
		pw.print("</tr>");
		
		for(Item item1 : WelcomeServlet.getAllItems()){
			if(Clothes.contains(item1.getId())){
				
				pw.print("<tr>");
				pw.print("<td>"+ item1.getId() +"</td>");
				pw.print("<td>"+ item1.getName()+"</td>");
				pw.print("<td>"+ item1.getType()+"</td>");
				pw.print("<td>"+ item1.getPrice()+"</td>");
				pw.print("</tr>");
			}
			
		}
		for(Item item2 : WelcomeServlet.getAllItems()){
			if(Books.contains(item2.getId())){
				
				pw.print("<tr>");
				pw.print("<td>"+ item2.getId() +"</td>");
				pw.print("<td>"+ item2.getName()+"</td>");
				pw.print("<td>"+ item2.getType()+"</td>");
				pw.print("<td>"+ item2.getPrice()+"</td>");
				pw.print("</tr>");
			}
			
		}
		for(Item item3 : WelcomeServlet.getAllItems()){
			if(Electronics.contains(item3.getId())){
				
				pw.print("<tr>");
				pw.print("<td>"+ item3.getId() +"</td>");
				pw.print("<td>"+ item3.getName()+"</td>");
				pw.print("<td>"+ item3.getType()+"</td>");
				pw.print("<td>"+ item3.getPrice()+"</td>");
				pw.print("</tr>");
			}
			
		}
		pw.print("</table>");
		pw.print("</body>");
		pw.print("</html>");
		
		
		
	}
}
