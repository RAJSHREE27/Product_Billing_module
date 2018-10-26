import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class WelcomeServlet extends HttpServlet{
	
	
	private static List<Item> AllItemList = null;
	
	public static List<item> getAllItems(){
		//connection from database
		
		long objId;
		String objName;
		String objType;
		float objPrice;
		
		
		if(AllItemList == null){
			
			AllItemList = new ArrayList<item>();
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null ;
			
			try{
					con = SisDbUtil.getConnection();
					String query3 = "select * from UserData";
					
					pstmt= con.prepareStatement(query3);
					
					rs = pstmt.executeQuery();
					
					while(rs.next()){
						
						objId = rs.getLong(1);
						objName = rs.getString(2);
						objType = rs.getString(3);
						objPrice = rs.getFloat(4);
						
						AllItemList.add(new Item(objId,objName,objType,objPrice));
						
						//it contains all the info of every product
						
					}
				
			}catch (Exception e) {
					
			}finally{
					try {
						SisDbUtil.closeConnection(con);
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			
			
		}
		return AllItemList;
	}
	//---------------------------------------------------------------------------------------------
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		
		String userName = (String)session.getAttribute("user");
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		pw.print("<html>");
		pw.print("<head>");
		pw.print("<title>Welcome</title>");
		pw.print("</head>");
		pw.print("<body style='text-align:center;background:#DAF7A6;'>");
		
		pw.print("<nav>");
		pw.print("<a href='######' style='margin-left:80em;'>Logout</a>");
		pw.print("</nav>");
		
		pw.print("<h1 style="background-color:black ; color:white">Welcome user : "+ userName +" </h1>");
		pw.print("<hr>");
		
		pw.print("<h3 style='font-size: 30px;'>Items You Wanna Look For : </h3>");
		pw.print("<hr>");
		pw.print("<hr>");
		
		//-----------------------------------------------------------------------
		
		pw.print("<form action='selectrd' method='post'>");
		pw.print("<input type='radio' value='Clothes' name='item_type'>Wear Onns");
		pw.print("<input type='radio' value='Books'  name='item_type'>Books");
		pw.print("<input type='radio' value='Electronics' name='item_type'>Electronics");
		pw.print("<input value='choose' type='submit'>");
		  
		pw.print("</form>");
		
		pw.print("</body>");
		pw.print("</html>");
		
	}