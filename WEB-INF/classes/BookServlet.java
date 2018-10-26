//input from option2.html comes to book servlet

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class BookServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		
		HttpSession session = request.getSession();
			
		String username = session.getAttribute("user");
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		
		//====fetching data or options checked by checkboxes
		
		String []bookItemIds = request.getParameterValues("book_opts");//selected ids in form of strings
		// we are getting strings here corresponding to which, we need to find the values

		List<Long>selectedBookIDList = new ArrayList<Long>();
		
		for(String str1 : bookItemIds){
			
			selectedBookIDList.add(Long.parseLong(str1));
		
		}
		
		List<Item>listOfBooks = new ArrayList<Item>();
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
				StringBuilder  query1 = new StringBuilder("select * from UserData where itemId in(");
				
				for(int i =0; i<selectedBookIDList.size() ; i++){
					query1 = i< (selectedBookIDList.size() -1)? query1.append("?,"):query1.append("?");
				}
				query1.append(")");
				
				pstmt = con.prepareStatement(query1);
				
				int i=1;
				for(long LL : selectedBookIDList){
					pstmt.setLong(i++, LL );
				}
				
				rs = pstmt.executeQuery();
			
				while(rs.next()){
					
					objId = rs.getLong(1);
					objName = rs.getString(2);
					objType = rs.getString(3);
					objPrice = rs.getFloat(4);
					
					listOfBooks.add(new Item(objId,objName,objType,objPrice));
					
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
		pw.print("<title>Books</title>");
		pw.print("</head>");
		
		pw.print("<body style='text-align:center;background:#DAF7A6;'>");
		pw.print("<h1 style="background-color:black ; color:white">Cart holder's name : "+ username +" </h1>");
		pw.print("<hr>");
		pw.print("<hr>");
		pw.print("<h3>the selected Books by user : "+username+"</h3><br><br>");
		
		pw.print("<table border=\"1px\">");
		pw.print("<tr>");
		pw.print("<th>Item Name </th>");
		pw.print("<th>Item Price </th>");
		pw.print("</tr>");
		
		
		for(Item bks : listOfBooks){
			pw.print("<tr>");
			pw.print("<td>"+bks.getName()+"</td>");
			pw.print("<td>"+bks.getPrice()+"</td>");
			pw.print("</tr>");
			
		}
		pw.print("</table>");
	
	//link for further step
	
		pw.println("<a href=\"welcome\">Shop More</a>");
		pw.println("==============================================================");
		
		session.setAttribute("bookItemSelected", selectedBookIDList);
		
		pw.println("<a href=\"add\">Done Shopping</a>");
		
		pw.print("</body>");
		pw.print("</html>");
		
	}
	
	
}