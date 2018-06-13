package cn.ancientsource.manage.register;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.ancientsource.manage.count.GUISystem;
import cn.ancientsource.manage.database.Jdbc;

public class RegisterWriteT extends JPanel {
	/**
	 * @author QiangweiLuo 500 x 600
	 */
	private static final long serialVersionUID = -8919713134133080798L;
	// SQL 语句
	private String sqlUpdate;
	private String sqlSelect;

	// 组件创建列表
	private ImageIcon icon; // 已实现

	private Image img; // 已实现
	private JLabel JL_Title;
	private JLabel JL_SID;
	private JLabel JL_PWD;
	private JLabel JL_Name;

	private JTextField JT_SID;
	private JTextField JT_PWD;
	private JTextField JT_Name;

	private JButton JB_Enter;

	public RegisterWriteT() {
		GUI();
	}

	public void GUI() {
		// 按钮设置初始化
		JButtonSet();
		// 文字框设置初始化
		JTextFieldSet();
		// 文本框设置初始化
		JLabelSet();
		// 次级容器初始化
		PanelSet();
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		super.paint(g);
	}

	private void PanelSet() {
		// 设置背景
		// 获取图片路径
		icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\T.png");
		img = icon.getImage();

		// 设置背景透明
		this.setBackground(null);
		// 设置控件透明
		this.setOpaque(false);
		// 不使用任何布局
		this.setLayout(null);

		// 加入组件
		this.add(JL_Title);
		this.add(JL_SID);
		this.add(JL_PWD);
		this.add(JL_Name);

		this.add(JT_SID);
		this.add(JT_PWD);
		this.add(JT_Name);

		this.add(JB_Enter);
	}

	private void JButtonSet() {
		JB_Enter = new JButton("确定");
		JB_Enter.setBounds(160, 420, 200, 100);
		JB_Enter.setFont(new Font("宋体", Font.CENTER_BASELINE, 50));
		// JB_Enter.setOpaque(false);
		JB_Enter.setBackground(new Color(115, 140, 138));
		JB_Enter.setBorderPainted(true);
		JB_Enter.setFocusPainted(false);
		JB_Enter.setForeground(Color.WHITE);
		JB_Enter.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					upDate();
					GUISystem.registerWrite.setVisible(false);
					init_Value();
					GUISystem.register.Init_values();
					GUISystem.login.Init_values();
					GUISystem.login.setVisible(true);
				}
			}
		});
		JB_Enter.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				upDate();
				GUISystem.registerWrite.setVisible(false);
				init_Value();
				GUISystem.register.Init_values();
				GUISystem.login.Init_values();
				GUISystem.login.setVisible(true);
			}
		});
	}

	private void JTextFieldSet() {
		JT_SID = new JTextField("工号");
		JT_SID.setBounds(170, 195, 250, 35);
		JT_SID.setFont(new Font("谐体", Font.BOLD, 25));
		JT_SID.setEditable(false);
		JT_SID.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JT_SID.setHorizontalAlignment(JTextField.CENTER);
		JT_SID.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					upDate();
					GUISystem.registerWrite.setVisible(false);
					init_Value();
					GUISystem.register.Init_values();
					GUISystem.login.Init_values();
					GUISystem.login.setVisible(true);
				}
			}
		});

		JT_PWD = new JTextField("密码");
		JT_PWD.setBounds(170, 265, 250, 35);
		JT_PWD.setFont(new Font("谐体", Font.BOLD, 25));
		JT_PWD.setEditable(false);
		JT_PWD.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JT_PWD.setHorizontalAlignment(JTextField.CENTER);
		JT_PWD.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					upDate();
					GUISystem.registerWrite.setVisible(false);
					init_Value();
					GUISystem.register.Init_values();
					GUISystem.login.Init_values();
					GUISystem.login.setVisible(true);
				}
			}
		});

		JT_Name = new JTextField("请输入姓名");
		JT_Name.setBounds(170, 335, 250, 35);
		JT_Name.setFont(new Font("谐体", Font.BOLD, 25));
		JT_Name.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JT_Name.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					upDate();
					GUISystem.registerWrite.setVisible(false);
					init_Value();
					GUISystem.register.Init_values();
					GUISystem.login.Init_values();
					GUISystem.login.setVisible(true);
				}
			}
		});
		JT_Name.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if ("".equals(JT_Name.getText())) {
					JT_Name.setText("请输入姓名");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if ("请输入姓名".equals(JT_Name.getText())) {
					JT_Name.setText("");
				}
			}
		});
	}

	private void JLabelSet() {
		// 初始化并设置显示文字
		JL_Title = new JLabel();
		// 设置标题
		JL_Title.setText("您的详细信息");
		// 设置字体
		JL_Title.setFont(new Font("宋体", Font.PLAIN, 60));
		// 设置字体颜色
		JL_Title.setForeground(Color.BLACK);
		// 设置位置大小
		JL_Title.setBounds(75, 20, 400, 100);

		// 初始化并设置显示文字
		JL_SID = new JLabel();
		// 设置标题
		JL_SID.setText("账户");
		// 设置字体
		JL_SID.setFont(new Font("幼圆", Font.PLAIN, 25));
		// 设置字体颜色
		JL_SID.setForeground(Color.BLACK);
		// 设置位置大小
		JL_SID.setBounds(50, 200, 50, 25);

		// 初始化并设置显示文字
		JL_PWD = new JLabel();
		// 设置标题
		JL_PWD.setText("密码");
		// 设置字体
		JL_PWD.setFont(new Font("幼圆", Font.PLAIN, 25));
		// 设置字体颜色
		JL_PWD.setForeground(Color.BLACK);
		// 设置位置大小
		JL_PWD.setBounds(50, 270, 50, 25);

		// 初始化并设置显示文字
		JL_Name = new JLabel();
		// 设置标题
		JL_Name.setText("姓名");
		// 设置字体
		JL_Name.setFont(new Font("幼圆", Font.PLAIN, 25));
		// 设置字体颜色
		JL_Name.setForeground(Color.BLACK);
		// 设置位置大小
		JL_Name.setBounds(50, 340, 50, 25);
	}

	public void init_Value() {
		JT_SID.setText("学号");
		JT_PWD.setText("密码");
		JT_Name.setText("请输入姓名");
	}

	public void upDate() {
		try {
			if (!JT_Name.getText().equals("请输入姓名")) {
				sqlUpdate = "UPDATE `教师信息` SET `姓名` = '" + JT_Name.getText() + "' WHERE  `工号` = " + JT_SID.getText();
				Jdbc.SqlStatementUpdate(sqlUpdate);
			} else {
				sqlUpdate = "UPDATE `教师信息` SET `姓名` = " + null + " WHERE  `工号` = " + JT_SID.getText();
				Jdbc.SqlStatementUpdate(sqlUpdate);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "输入信息有误！", "错误", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setDeualt(String a) {
		try {
			sqlSelect = "select * from `教师信息` where `工号` = " + a;
			ResultSet rs = Jdbc.SqlStatement(sqlSelect);
			rs.next();
			JT_SID.setText(rs.getInt(1) + "");
			if (rs.getString(2) != null)
				JT_Name.setText(rs.getString(2));
			if (rs.getString(3) != null)
				JT_PWD.setText(rs.getString(3) + "");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
