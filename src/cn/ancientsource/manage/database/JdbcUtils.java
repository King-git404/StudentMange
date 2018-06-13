package cn.ancientsource.manage.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
	/**
	 * @author Qiangwei_Luo
	 */
	//private static String url = "jdbc:mysql://localhost:3306/blue?useSSL=false";
	//private static String user;
	//private static String password;
	
	private JdbcUtils() {}
	
	static {
		// 注册驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	// 建立连接
	public static Connection getConnextion(String url, String user, String password) throws SQLException {
		return DriverManager.getConnection(url,user,password);
	}
	
	// 关闭连接    使用 finally 确保关闭在出错时可以正常执行
	public static void free(ResultSet rs, Statement st, Connection conn) {
		try {
			if(rs != null) {
				rs.close();
				rs = null;
			}
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			try {
				if(st != null) {
					st.close();
					st = null;
				}
			} catch(SQLException e){
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
						conn = null;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
