//input from option1.html comes to cloth servlet
//handle nullpointerexception as no items selected

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ClothServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		
		HttpSession session = request.getSession();
			
		String username = (String)session.getAttribute("user");
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		
		//====fetching data or options checked by checkboxes
		
		String []clothItemIds = request.getParameterValues("cloth_opts");	//selected ids in form of strings
		// we are getting strings here corresponding to which, we need to find the values

		List<Long>selectedItemIDList = new ArrayList<Long>();
		
		for(String str : clothItemIds){
			
			selectedItemIDList.add(Long.parseLong(str));
		
		}
		
		List<Item>listOfItems = new ArrayList<Item>();
		long objId;
		String objName;
		String objType;
		float objPrice;
		
		
		//-------------------------------------------------------------
		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null ;
			
		try{
				con = SisDbUtil.getConnection();
				
				//String query= "select * from UserData where itemId in "+ selectedItemIDList ;
				StringBuilder  query = new StringBuilder("select * from UserData where itemId in(");
				
				for(int i =0; i<selectedItemIDList.size() ; i++){
					query = i< (selectedItemIDList.size() -1)? query.append("?,"):query.append("?");
				}
				query.append(")");
				
				String sttt = query.toString();
				pstmt = con.prepareStatement(sttt);
				
				int i=1;
				for(long L : selectedItemIDList){
					pstmt.setLong(i++, L);
				}
				
				rs = pstmt.executeQuery();
			
				while(rs.next()){
					
					objId = rs.getLong(1);
					objName = rs.getString(2);
					objType = rs.getString(3);
					objPrice = rs.getFloat(4);
					
					listOfItems.add(new Item(objId,objName,objType,objPrice));
					
				}
			}catch (Exception e) {
				
			}finally{
				try {
					SisDbUtil.closeConnection(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		pw.print("<html>");
		pw.print("<head>");
		pw.print("<title>Wear onns</title>");
		pw.print("</head>");
		
		pw.print("<body style='text-align:center;background:#DAF7A6;'>");
		pw.print("<a href='logout' style='margin-left:70em;'>Logout</a>");
		pw.print("<h1 style='background-color:black ; color:white;'>Cart holder's name : "+ username +" </h1>");
		pw.print("<hr>");
		pw.print("<hr>");
		
		pw.print("<h3>the selected wear-onns by user : "+username+"</h3><br><br>");
		
		pw.print("<table border=\"1px\">");
		pw.print("<tr>");
		pw.print("<th>Item Name </th>");
		pw.print("<th>Item Price </th>");
		pw.print("</tr>");
		
		
		for(Item things : listOfItems){
			pw.print("<tr>");
			pw.print("<td>"+things.getName()+"</td>");
			pw.print("<td>"+things.getPrice()+"</td>");
			pw.print("</tr>");
			
		}
		session.setAttribute("clothItemSelected", selectedItemIDList);
		pw.print("</table>");
	
	//link for further step
	
		pw.println("<a href='welcome'>Shop More</a>");
		pw.println("<br>==============================================================");
		
		
		pw.println("<a href='add'>Done Shopping</a>");
		
		pw.print("</body>");
		pw.print("</html>");
		
	}
	
	
}