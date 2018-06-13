import cn.ancientsource.manage.count.GUISystem;
import cn.ancientsource.manage.database.Jdbc;

public class Main {
	public static void main(String[] args) {
		Jdbc.linkSql("jdbc:mysql://115.159.146.57:3306/学生管理系统?useSSL=false", "xsglxt", "xsglxt");
		new GUISystem();
	}
}
