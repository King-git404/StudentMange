package cn.ancientsource.manage.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Jdbc {
	/**
	 * @author Qiangwei_Luo
	 */
	//private static String SQLlib = null;
	private static Connection conn = null;
	private static Statement st = null;
	private static ResultSet rs = null;
	private static int I_num = 0;
	
	// 建立与 MySql 的连接
	public static void linkSql(String url, String user, String password){
		try {
			// 建立连接
			conn = JdbcUtils.getConnextion(url, user, password);
			// 处理结果
			/*while(rs.next()) {
				System.out.println(rs.getObject(1));
			}*/
		} catch(Exception e) {
			JOptionPane.showMessageDialog( null,"连接数据库失败!");
			System.exit(1);
		}
	}
	
	// 调用 MySql 语句
	public static ResultSet SqlStatement (String SQLlib) throws SQLException {
		try {
			// 创建语句
			st = conn.createStatement();
			// 执行语句
			rs = st.executeQuery(SQLlib);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 返回结果以处理语句
		return rs;
	}
	
	// 执行 INSERT、UPDATE 或 DELETE 语句以及 SQL DDL（数据定义语言）语句
	public static int SqlStatementUpdate(String SQLlib) throws SQLException {
		// 创建语句
		st = conn.createStatement();
		// 执行语句
		I_num = st.executeUpdate(SQLlib);
		
		return I_num;
	}
	
	// 关闭数据库
	public static void closeSql() {
		JdbcUtils.free(rs, st, conn);
	}
}
