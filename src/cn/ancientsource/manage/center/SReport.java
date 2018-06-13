package cn.ancientsource.manage.center;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.ancientsource.manage.database.Jdbc;
import cn.ancientsource.manage.login.Login;

// 成绩单 -- 学生面板
public class SReport extends JPanel {
	/**
	 * @author luo
	 */
	private static final long serialVersionUID = -4932163837656902554L;
	// 组件创建列表
	private ImageIcon icon; // 已实现

	private Image img; // 已实现

	private JLabel JL_Title;
	// 成绩1
	private JLabel JL_S1;
	// 成绩2
	private JLabel JL_S2;
	// 成绩3
	private JLabel JL_S3;
	// 总成绩
	private JLabel JL_SC;

	private JTextField JT_S1;
	private JTextField JT_S2;
	private JTextField JT_S3;
	private JTextField JT_SC;

	public SReport() {
		GUI();
	}

	private void GUI() {
		// 文字框设置初始化
		JTextFieldSet();
		// 文本框设置初始化
		JLabelSet();
		// 次级容器初始化
		PanelSet();
	}

	private void JLabelSet() {
		JL_Title = new JLabel();
		// 设置标题
		JL_Title.setText("个 人 成 绩");
		// 设置字体
		JL_Title.setFont(new Font("宋体", Font.PLAIN, 60));
		// 设置字体颜色
		JL_Title.setForeground(Color.WHITE);
		// 设置位置大小
		JL_Title.setBounds(75 + 350, 20, 400, 100);
		
		JL_S1 = new JLabel();
		// 设置标题
		JL_S1.setText("成绩1");
		// 设置字体
		JL_S1.setFont(new Font("宋体", Font.PLAIN, 25));
		// 设置字体颜色
		JL_S1.setForeground(Color.WHITE);
		// 设置位置大小
		JL_S1.setBounds(75 + 350, 200, 400, 100);
		
		JL_S2 = new JLabel();
		// 设置标题
		JL_S2.setText("成绩2");
		// 设置字体
		JL_S2.setFont(new Font("宋体", Font.PLAIN, 25));
		// 设置字体颜色
		JL_S2.setForeground(Color.WHITE);
		// 设置位置大小
		JL_S2.setBounds(75 + 350, 300, 400, 100);
		
		JL_S3 = new JLabel();
		// 设置标题
		JL_S3.setText("成绩3");
		// 设置字体
		JL_S3.setFont(new Font("宋体", Font.PLAIN, 25));
		// 设置字体颜色
		JL_S3.setForeground(Color.WHITE);
		// 设置位置大小
		JL_S3.setBounds(75 + 350, 400, 400, 100);
		
		JL_SC = new JLabel();
		// 设置标题
		JL_SC.setText("总成绩");
		// 设置字体
		JL_SC.setFont(new Font("宋体", Font.PLAIN, 25));
		// 设置字体颜色
		JL_SC.setForeground(Color.WHITE);
		// 设置位置大小
		JL_SC.setBounds(75 + 350, 500, 400, 100);
	}

	private void JTextFieldSet() {
		JT_S1 = new JTextField("成绩1");
		JT_S1.setBounds(170 + 450 - 10, 230, 100, 50);
		JT_S1.setFont(new Font("谐体", Font.BOLD, 30));
		JT_S1.setForeground(Color.WHITE);
		JT_S1.setEditable(false);
		JT_S1.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JT_S1.setHorizontalAlignment(JTextField.CENTER);
		JT_S1.setOpaque(false);
		
		JT_S2 = new JTextField("成绩2");
		JT_S2.setBounds(170 + 450 - 10, 330, 100, 50);
		JT_S2.setFont(new Font("谐体", Font.BOLD, 30));
		JT_S2.setForeground(Color.WHITE);
		JT_S2.setEditable(false);
		JT_S2.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JT_S2.setHorizontalAlignment(JTextField.CENTER);
		JT_S2.setOpaque(false);
		
		JT_S3 = new JTextField("成绩3");
		JT_S3.setBounds(170 + 450 - 10, 430, 100, 50);
		JT_S3.setFont(new Font("谐体", Font.BOLD, 30));
		JT_S3.setForeground(Color.WHITE);
		JT_S3.setEditable(false);
		JT_S3.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JT_S3.setHorizontalAlignment(JTextField.CENTER);
		JT_S3.setOpaque(false);
		
		JT_SC = new JTextField("总成绩");
		JT_SC.setBounds(170 + 450 - 10, 530, 100, 50);
		JT_SC.setFont(new Font("谐体", Font.BOLD, 30));
		JT_SC.setForeground(Color.WHITE);
		JT_SC.setEditable(false);
		JT_SC.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JT_SC.setHorizontalAlignment(JTextField.CENTER);
		JT_SC.setOpaque(false);
		
	}

	private void PanelSet() {
		// 设置背景
		// 获取图片路径
		icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\SS.png");
		img = icon.getImage();

		// 设置大小
		this.setBounds(0, 0, 1300, 1000);
		// 设置背景透明
		this.setBackground(null);
		// 设置控件透明
		this.setOpaque(false);
		// 不使用任何布局
		this.setLayout(null);

		// 加入组件
		this.add(JL_Title);
		this.add(JL_S1);
		this.add(JL_S2);
		this.add(JL_S3);
		this.add(JL_SC);
		
		this.add(JT_S1);
		this.add(JT_S2);
		this.add(JT_S3);
		this.add(JT_SC);
	}

	@Override
	public void paint(Graphics g) {
		this.setBounds(0, 0, 1300, 1000);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		super.paint(g);
	}

	public void initValue() {
		JT_S1.setText("成绩1");
		JT_S2.setText("成绩2");
		JT_S3.setText("成绩3");
		JT_SC.setText("总成绩");
	}
	
	public void getInfo(){
		try {
			ResultSet rs = Jdbc.SqlStatement("SELECT * FROM `学生成绩` WHERE `学号`=" + Login.SQL_Account);
			if(rs.next()){
				JT_S1.setText(rs.getString(3));
				JT_S2.setText(rs.getString(4));
				JT_S3.setText(rs.getString(5));
				JT_SC.setText(rs.getString(6));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
