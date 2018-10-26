import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class SisDbUtil {
	private SisDbUtil() {}
	
    private static boolean isDriverLoaded =false;
	static {
    	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver loaded");
			isDriverLoaded =true;
		} catch (ClassNotFoundException e) {
			System.out.println("Driver NOT loaded");
			e.printStackTrace();
		}
		
	}
	
	private final static String URL ="jdbc:oracle:thin:@localhost:1521:XE";
	private final static String USER ="system";
	private final static String PASSWORD ="root";
	
	public static Connection getConnection() throws SQLException {
		Connection con  = null;
		if(isDriverLoaded) {
		con = DriverManager.getConnection(URL, USER,PASSWORD);
		}
		return con;
	}
	
	public static void closeConnection(Connection con) throws SQLException {
		if(con!=null) {
			con.close();
			System.out.println("con closed");
		}
	}

}
