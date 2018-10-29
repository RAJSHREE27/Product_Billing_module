// shows all the products selected along with their prices

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		
		HttpSession session = request.getSession();
			
		String username = (String)session.getAttribute("user");
		
		List<Long> Clothes = (ArrayList)session.getAttribute("clothItemSelected");
		List<Long> Books = (ArrayList)session.getAttribute("bookItemSelected");
		List<Long> Electronics = (ArrayList)session.getAttribute("electronicItemSelected");
		
		System.out.println(Clothes);
		System.out.println(Books);
		System.out.println(Electronics);
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		float count=0;
		// it takes all the session attributes and shows them as a list
		
		
		pw.print("<html>");
		pw.print("<head>");
		pw.print("<title>ALL_Items</title>");
		pw.print("</head>");
		
		pw.print("<body style='text-align:center;background:#DAF7A6;'>");
		pw.print("<a href='logout' style='margin-left:70em;'>Logout</a>");
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
				count += item1.getPrice();
			}
			
		}//for
		
		for(Item item2 : WelcomeServlet.getAllItems()){
			if(Books.contains(item2.getId())){
				
				pw.print("<tr>");
				pw.print("<td>"+ item2.getId() +"</td>");
				pw.print("<td>"+ item2.getName()+"</td>");
				pw.print("<td>"+ item2.getType()+"</td>");
				pw.print("<td>"+ item2.getPrice()+"</td>");
				pw.print("</tr>");
				count += item2.getPrice();
			}
			
		}
		
		//for
		
		for(Item item3 : WelcomeServlet.getAllItems()){
			if(Electronics.contains(item3.getId())){
				
				pw.print("<tr>");
				pw.print("<td>"+ item3.getId() +"</td>");
				pw.print("<td>"+ item3.getName()+"</td>");
				pw.print("<td>"+ item3.getType()+"</td>");
				pw.print("<td>"+ item3.getPrice()+"</td>");
				pw.print("</tr>");
				
				count += item3.getPrice();
			}
			
		}//for
		
		
		pw.print("</table>");
		
		pw.print("<h3> Bill : Rs. "+count+"/- </h3>");
		
		//---------------------------------------------------------------------------------'
		//---------------------------------------------------------------
		
		pw.print("</body>");
		pw.print("</html>");
		
		
		
	}
}
