package cn.ancientsource.manage.register;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import cn.ancientsource.manage.count.GUISystem;
import cn.ancientsource.manage.database.Jdbc;

public class Register extends JFrame {
	/**
	 * @author luo
	 */
	private static final long serialVersionUID = -6968091735063327215L;
	private String SQL_Account; // 待添加的新用户
	private String SQL_PWD; // 带添加的新用户密码

	private String SQLsm1; // SQL1 语句
	private String SQLsm2; // SQL2 语句
	private String SQLsm3; // SQL1 语句

	private boolean flag = false; // 判断是否成功注册

	// 组件创建列表
	private JLabel JL_Title; // 已实现
	private JLabel JL_Account; // 已实现
	private JLabel JL_Passwd; // 已实现

	private JTextField JT_Account; // 已实现

	private JPasswordField JP_Passwd; // 已实现
	private JPasswordField JP_RePasswd;

	private ImageIcon icon; // 已实现

	private Image img; // 已实现
	private Image Ico; // 已实现

	private JButton JB_login; // 已实现
	private JButton JB_Register; // 已实现

	private Toolkit tool;

	private JPanel base; // 已实现

	public Register() {
		GUI();
	}

	public void GUI() {
		// 按钮设置初始化
		JButtonSet();
		// 文字框设置初始化
		JTextFieldSet();
		// 密码框设置初始化
		JPasswordFieldSet();
		// 文本框设置初始化
		JLabelSet();
		// 次级容器初始化
		PanelSet();
		// 顶级容器初始化
		FrameSet();
	}

	public void JLabelSet() {
		JL_Title = new JLabel();
		// 设置标题
		JL_Title.setText("— 注册系统 —");
		// 设置字体
		JL_Title.setFont(new Font("宋体", Font.PLAIN, 50));
		// 设置字体颜色
		JL_Title.setForeground(Color.WHITE);
		// 设置位置大小 setBounds(int x, int y, int width, int height)
		JL_Title.setBounds(100, 20, 400, 100);

		// 初始化并设置显示文字
		JL_Account = new JLabel("账户");
		// 设置字体
		JL_Account.setFont(new Font("幼圆", Font.PLAIN, 25));
		// 设置字体颜色
		JL_Account.setForeground(Color.WHITE);
		// 设置位置大小
		JL_Account.setBounds(100, 200, 50, 25);

		// 初始化并设置显示文字
		JL_Passwd = new JLabel("密码");
		// 设置字体
		JL_Passwd.setFont(new Font("幼圆", Font.PLAIN, 25));
		// 设置字体颜色
		JL_Passwd.setForeground(Color.WHITE);
		// 设置位置大小
		JL_Passwd.setBounds(100, 270, 50, 25);

	}

	public void JTextFieldSet() {
		// 初始化
		JT_Account = new JTextField("请输入账户名");
		// 设置位置大小
		JT_Account.setBounds(170, 195, 250, 35);
		// 设置字体
		JT_Account.setFont(new Font("谐体", Font.BOLD, 25));
		// 设置禁止只读
		JT_Account.setEditable(true);
		// 设置控件透明
		// JT_Account.setOpaque(true);
		// 设置背景透明
		JT_Account.setOpaque(false);
		// 设置字体颜色
		JT_Account.setForeground(Color.WHITE);
		// 设置边框
		JT_Account.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		// 设置插入符（光标）的颜色
		JT_Account.setCaretColor(Color.WHITE);

		// 添加聚焦事件 对于这个事件不太还用因此去除
		JT_Account.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) { // 聚焦中
				if ("请输入账户名".equalsIgnoreCase(JT_Account.getText())) {
					JT_Account.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) { // 无聚集
				if ("".equals(JT_Account.getText().trim())) {
					JT_Account.setText("请输入账户名");
				}
			}

		});

		// 插入符变更事件
		JT_Account.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				// 把 JTextField 对象传给 JT_C
				JTextField JT_C = (JTextField) e.getSource();
				// 存储 JTextField 的字符
				String Text = JT_C.getText();
				// 判断，如果没有内容就退出侦听
				if (Text.length() == 0) {
					return;
				}
				// 获取末尾的元素
				char ch = Text.charAt(Text.length() - 1);
				// 留下提示要素,防止被一块删掉
				if (Text.equals("请输入账户名")) {
					return;
				}
				;
				// 判断，如果不是字母就删去
				if (!((ch >= '0' && ch <= '9') || (ch >= '0' && ch <= '9'))) {
					// 有写保护不能直接修改文字，开个新线程来改
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							// 去掉 JTextField 中的末尾字符
							JT_C.setText(Text.substring(0, Text.length() - 1));
						}
					});
				}
				if (Text.length() > 8) {
					// 有写保护不能直接修改文字，开个新线程来改
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							// 去掉 JTextField 中的末尾字符
							JT_C.setText(Text.substring(0, Text.length() - 1));
						}
					});
				}
			}
		});

		// 按键事件，设置成按下回车即可注册
		JT_Account.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						Register_try();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if (flag) {
						GUISystem.register.setVisible(false); // 先隐藏这个窗口，转到另一个窗口
						GUISystem.registerWrite.setVisible(true); // 转到登入窗体
						flag = false;
					}
				}
			}
		});
	}

	public void JPasswordFieldSet() {
		// 初始化
		JP_Passwd = new JPasswordField("请输入密码");
		// 设置位置大小
		JP_Passwd.setBounds(170, 265, 250, 35);
		// 设置字体
		JP_Passwd.setFont(new Font("谐体", Font.BOLD, 25));
		// 设置禁止只读
		JP_Passwd.setEditable(true);
		// 设置明文显示
		JP_Passwd.setEchoChar((char) 0);
		// 设置背景透明
		JP_Passwd.setOpaque(false);
		// 设置字体颜色
		JP_Passwd.setForeground(Color.WHITE);
		// 设置边框
		JP_Passwd.setBorder(BorderFactory.createLineBorder(new Color(73, 95, 219)));
		// 设置插入符（光标）的颜色
		JP_Passwd.setCaretColor(Color.WHITE);

		// 设置聚焦事件
		JP_Passwd.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) { // 非聚焦事件
				if ("".equals(String.valueOf(JP_Passwd.getPassword()).trim())) {
					JP_Passwd.setEchoChar((char) 0);
					JP_Passwd.setText("请输入密码");
				}
			}

			@Override
			public void focusGained(FocusEvent e) { // 聚焦事件
				// valueOf（）方法是通过new 一个String 对象来完成转化的
				if ("请输入密码".equalsIgnoreCase(String.valueOf(JP_Passwd.getPassword()))) {
					JP_Passwd.setText("");
					JP_Passwd.setEchoChar('I');
				}
			}
		});

		// 密码就不限制文字的使用了~

		// 按键事件，设置成按下回车即可注册
		JP_Passwd.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						Register_try();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if (flag) {
						GUISystem.register.setVisible(false); // 先隐藏这个窗口，转到另一个窗口
						GUISystem.registerWrite.setVisible(true); // 转到登入窗体
						flag = false;
					}
				}
			}
		});

		// 初始化
		JP_RePasswd = new JPasswordField("请再次输入密码");
		// 设置位置大小
		JP_RePasswd.setBounds(170, 335, 250, 35);
		// 设置字体
		JP_RePasswd.setFont(new Font("谐体", Font.BOLD, 25));
		// 设置禁止只读
		JP_RePasswd.setEditable(true);
		// 设置明文显示
		JP_RePasswd.setEchoChar((char) 0);
		// 设置背景透明
		JP_RePasswd.setOpaque(false);
		// 设置字体颜色
		JP_RePasswd.setForeground(Color.WHITE);
		// 设置边框
		JP_RePasswd.setBorder(BorderFactory.createLineBorder(new Color(73, 95, 219)));
		// 设置插入符（光标）的颜色
		JP_RePasswd.setCaretColor(Color.WHITE);

		// 设置聚焦事件
		JP_RePasswd.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) { // 非聚焦事件
				if ("".equals(String.valueOf(JP_RePasswd.getPassword()).trim())) {
					JP_RePasswd.setEchoChar((char) 0);
					JP_RePasswd.setText("请再次输入密码");
				}
			}

			@Override
			public void focusGained(FocusEvent e) { // 聚焦事件
				// valueOf（）方法是通过new 一个String 对象来完成转化的
				if ("请再次输入密码".equalsIgnoreCase(String.valueOf(JP_RePasswd.getPassword()))) {
					JP_RePasswd.setText("");
					JP_RePasswd.setEchoChar('I');
				}
			}
		});

		// 密码就不限制文字的使用了~

		// 按键事件，设置成按下回车即可注册
		JP_RePasswd.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						Register_try();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if (flag) {
						GUISystem.register.setVisible(false); // 先隐藏这个窗口，转到另一个窗口
						GUISystem.registerWrite.setVisible(true); // 转到登入窗体
						flag = false;
					}
				}
			}
		});
	}

	public void JButtonSet() {
		// 初始化
		JB_login = new JButton("登陆");
		// 设置位置大小
		JB_login.setBounds(320, 420, 100, 50);
		// 设置字体
		JB_login.setFont(new Font("宋体", Font.CENTER_BASELINE, 30));
		// 设置控件透明
		JB_login.setOpaque(true);
		// 设置背景
		JB_login.setBackground(new Color(11, 82, 175));
		// 设置是否绘制边框
		JB_login.setBorderPainted(true);
		// 不绘制焦点
		JB_login.setFocusPainted(false);
		// 设置字体颜色
		JB_login.setForeground(Color.WHITE);

		// 编写事件
		JB_login.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 设置边框
				JB_login.setBorder(null);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// 设置边框
				JB_login.setBorder(BorderFactory.createLineBorder(new Color(164, 167, 225)));
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				JB_Register.requestFocus(); // 初始化焦点
				Init_values(); // 初始化值
				Register.this.setVisible(false); // 先隐藏这个窗口，转到另一个窗口
				GUISystem.login.setVisible(true); // 显示登入窗体
				Register.this.setLocationRelativeTo(null); // 归位窗体位置
			}
		});

		// 聚焦事件
		JB_login.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				JB_login.setBackground(new Color(11, 82, 175));
			}

			@Override
			public void focusGained(FocusEvent e) {
				JB_login.setBackground(new Color(61, 132, 225));
			}
		});

		// 初始化
		JB_Register = new JButton("注册");
		// 设置位置大小
		JB_Register.setBounds(170, 420, 100, 50);
		// 设置字体
		JB_Register.setFont(new Font("宋体", Font.CENTER_BASELINE, 30));
		// 设置控件透明
		JB_Register.setOpaque(true);
		// 设置背景
		JB_Register.setBackground(new Color(21, 92, 195));
		// 设置是否绘制边框
		JB_Register.setBorderPainted(true);
		// 不绘制焦点
		JB_Register.setFocusPainted(false);
		// 设置字体颜色
		JB_Register.setForeground(Color.WHITE);

		// 编写事件
		JB_Register.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 设置边框
				JB_Register.setBorder(null);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// 设置边框
				JB_Register.setBorder(BorderFactory.createLineBorder(new Color(164, 167, 225)));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Register_try();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if (flag) {
					if (JT_Account.getText().length() == 8)
						GUISystem.registerWrite.switchPanelS();
					if (JT_Account.getText().length() == 4)
						GUISystem.registerWrite.switchPanelT();
					GUISystem.register.setVisible(false); // 先隐藏这个窗口，转到另一个窗口
					GUISystem.registerWrite.setVisible(true); // 转到登入窗体
					Init_values();
					Register.this.setLocationRelativeTo(null); // 归位窗体位置
					flag = false;
				}
			}
		});

		JB_Register.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						Register_try();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if (flag) {
						GUISystem.register.setVisible(false); // 先隐藏这个窗口，转到另一个窗口
						if (JT_Account.getText().length() == 8)
							GUISystem.registerWrite.switchPanelS();
						if (JT_Account.getText().length() == 4)
							GUISystem.registerWrite.switchPanelT();
						GUISystem.registerWrite.setVisible(true); // 转到登入窗体
						Init_values();
						Register.this.setLocationRelativeTo(null); // 归位窗体位置
						flag = false;
					}
				}
			}
		});

		JB_Register.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				JB_Register.setBackground(new Color(21, 92, 195));
			}

			@Override
			public void focusGained(FocusEvent e) {
				JB_Register.setBackground(new Color(77, 142, 245));
			}
		});
	}

	public void PanelSet() {
		// 设置背景
		// 获取图片路径
		icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\10.png");
		img = icon.getImage();
		// 创建个容器，并设置
		base = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 3576260302275511426L;

			@Override
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
				super.paint(g);
			}

		};

		// 设置背景透明
		base.setBackground(null);
		// 设置控件透明
		base.setOpaque(false);
		// 不使用任何布局
		base.setLayout(null);

		// 加入组件
		base.add(JL_Title);
		base.add(JL_Account);
		base.add(JL_Passwd);
		base.add(JT_Account);
		base.add(JP_Passwd);
		base.add(JB_login);
		base.add(JB_Register);
		base.add(JP_RePasswd);

	}

	public void FrameSet() {
		// 创建并设置窗体
		this.setTitle("学生管理系统注册界面");
		// 设置内容显示
		this.setContentPane(base);
		// 设置窗体大小
		this.setSize(500, 570);
		// 设置位置
		this.setLocation(800, 300);
		// 设置点击关闭按钮的默认动作
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 锁定窗体
		this.setResizable(false);
		// 设置背景色（虽然没啥用）
		this.setBackground(Color.WHITE);
		// 得到一个 Toolkit 对象
		tool = this.getToolkit();
		// 通过 tool 获取图像
		Ico = tool.getImage(System.getProperty("user.dir") + "\\images\\9.png");
		// 设置图标
		this.setIconImage(Ico);
		// 设置是否可见
		this.setVisible(false);
		// 设置居中
		this.setLocationRelativeTo(null);
		// 窗体事件
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				Jdbc.closeSql(); // 关闭程序前先关闭数据库
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
				JB_Register.requestFocus();
			}
		});
	}

	// 注册模块
	private void Register_try() throws SQLException {
		// 获取注册账户
		SQL_Account = JT_Account.getText();
		// 获取注册密码
		SQL_PWD = String.valueOf(JP_Passwd.getPassword());
		// 若密码不规范的处理
		if ("请输入账户名".equals(SQL_Account)) {
			JOptionPane.showMessageDialog(null, "请输入账户名！", "错误", JOptionPane.ERROR_MESSAGE);
		} else if ("请输入密码".equals(SQL_PWD)) {
			JOptionPane.showMessageDialog(null, "请输入密码！", "错误", JOptionPane.ERROR_MESSAGE);
		} else if (!SQL_PWD.equals(String.valueOf(JP_RePasswd.getPassword()))) {
			JOptionPane.showMessageDialog(null, "两次密码不一致！", "错误", JOptionPane.ERROR_MESSAGE);
		}
		// 判断密码和账户是否符合规范
		if (!"请输入账户名".equals(SQL_Account) && !"请输入密码".equals(SQL_PWD)
				&& SQL_PWD.equals(String.valueOf(JP_RePasswd.getPassword()))) {
			SQLsm2 = "select * from `教师信息` where `工号` = " + SQL_Account;
			SQLsm3 = "select * from `学生信息` where `学号` = " + SQL_Account;
			// 判断账号是否已经存在
			boolean flag1 = Jdbc.SqlStatement(SQLsm3).next();
			if (!flag1)
				flag1 = Jdbc.SqlStatement(SQLsm2).next();

			if (SQL_Account.length() == 8 && SQL_PWD.length() > 3 && !flag1) {
				// 插入到学生表
				SQLsm1 = "insert into `学生信息`(`学号`,`登入密码`) values(" + SQL_Account + "," + SQL_PWD + ")";
				Jdbc.SqlStatementUpdate(SQLsm1);
				flag = true;
				GUISystem.registerWrite.setDeualt(SQL_Account);
			} else if (SQL_Account.length() == 4 && SQL_PWD.length() > 3 && !flag1) {
				// 插入到教师表
				SQLsm1 = "insert into `教师信息`(`工号`,`登入密码`) values(" + SQL_Account + "," + SQL_PWD + ")";
				Jdbc.SqlStatementUpdate(SQLsm1);
				flag = true;
				GUISystem.registerWrite.setDeualt(SQL_Account);
			}
			// 如果账户存在或者是密码输入不合规范
			if (flag1) {
				JOptionPane.showMessageDialog(null, "该账户已存在！", "错误", JOptionPane.ERROR_MESSAGE);
				Init_values();
			} else if (SQL_Account.length() != 8 && SQL_Account.length() != 4) {
				JOptionPane.showMessageDialog(null, "该账户注册错误！\n请输入 8 位学号或 4 位工号！", "错误", JOptionPane.ERROR_MESSAGE);
			} else if (SQL_PWD.length() < 4) {
				JOptionPane.showMessageDialog(null, "该账户注册错误！\n请输入至少四位密码！", "错误", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void Init_values() {
		SQL_Account = ""; // 待添加的新用户
		SQL_PWD = ""; // 带添加的新用户密码

		flag = false;

		JT_Account.setText("请输入账户名");
		JP_Passwd.setText("请输入密码");
		JP_RePasswd.setText("请再次输入密码");
		JP_Passwd.setEchoChar((char) 0);
		JP_RePasswd.setEchoChar((char) 0);
	}
}
