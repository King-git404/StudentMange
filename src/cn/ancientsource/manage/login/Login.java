package cn.ancientsource.manage.login;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

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

public class Login extends JFrame {
	/**
	 * @author Qiangwei_Luo
	 */
	private static final long serialVersionUID = -6967500256047999437L;

	// private String SQLlib; // 存储 SQL 语句 ，然而并不需要
	boolean flag = false; // 判断是否成功登入

	protected static int SQL_CLP; // 存储类别
	public static String SQL_Account = ""; // 存储数据库中取出的账户
	protected static String SQL_PWD = ""; // 存储数据库中取出的密码

	private String Get_Account; // 存储用户输入的账户
	private String Get_PWD; // 存储用户输入的密码

	ResultSet rs; // 存储数据库返回的内容

	// 产生随机数，用于背景切换
	private Random rand = new Random();
	private int Set;

	// 组件创建列表
	private JLabel JL_Title; // 已实现
	private JLabel JL_Account; // 已实现
	private JLabel JL_Passwd; // 已实现

	private JTextField JT_Account; // 已实现

	private JPasswordField JP_Passwd; // 已实现

	private ImageIcon icon; // 已实现

	private Image img; // 已实现
	private Image Ico; // 已实现

	private JButton JB_login; // 已实现
	private JButton JB_Register; // 已实现

	private Toolkit tool;

	private JPanel base; // 已实现

	public Login() {
		GUI();

	}

	// 初始化控件并显示
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
		// 设置默认焦点 现已加入窗体事件豪华午餐中
		// JB_login.requestFocus();
	}

	public void JLabelSet() {
		JL_Title = new JLabel();
		// 设置标题
		JL_Title.setText("学生管理系统");
		// 设置字体
		JL_Title.setFont(new Font("宋体", Font.PLAIN, 50));
		// 设置字体颜色
		JL_Title.setForeground(Color.BLACK);
		// 设置位置大小 setBounds(int x, int y, int width, int height)
		JL_Title.setBounds(110, 20, 400, 100);

		// 初始化并设置显示文字
		JL_Account = new JLabel("账户");
		// 设置字体
		JL_Account.setFont(new Font("幼圆", Font.PLAIN, 25));
		// 设置字体颜色
		JL_Account.setForeground(Color.BLACK);
		// 设置位置大小
		JL_Account.setBounds(100, 200, 50, 25);

		// 初始化并设置显示文字
		JL_Passwd = new JLabel("密码");
		// 设置字体
		JL_Passwd.setFont(new Font("幼圆", Font.PLAIN, 25));
		// 设置字体颜色
		JL_Passwd.setForeground(Color.BLACK);
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
		JT_Account.setOpaque(true);
		// 设置背景透明
		JT_Account.setBackground(new Color(235, 238, 245));

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

		// 按键事件，设置成按下回车即可登入
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
					JB_login.setBackground(new Color(190, 190, 190)); // 设置按钮背景
					Login_try();
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
		// 设置控件透明
		JP_Passwd.setOpaque(true);
		// 设置背景透明
		JP_Passwd.setBackground(new Color(225, 228, 235));

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

		// 按键事件，设置成按下回车即可登入
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
					JB_login.setBackground(new Color(190, 190, 190)); // 设置按钮背景
					Login_try();
				}
			}
		});
	}

	public void JButtonSet() {
		// 初始化
		JB_login = new JButton("登陆");
		// 设置位置大小
		JB_login.setBounds(170, 350, 100, 50);
		// 设置字体
		JB_login.setFont(new Font("宋体", Font.CENTER_BASELINE, 30));
		// 设置控件透明
		JB_login.setOpaque(true);
		// 设置背景透明
		JB_login.setBackground(new Color(147, 136, 147));
		// 设置是否绘制边框
		JB_login.setBorderPainted(true);
		// 不绘制焦点
		JB_login.setFocusPainted(false);

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
				JB_login.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				Login_try();
			}
		});

		JB_login.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Login_try();
				}
			}
		});

		// 聚焦事件
		JB_login.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				JB_login.setBackground(new Color(147, 136, 147));
			}

			@Override
			public void focusGained(FocusEvent e) {
				JB_login.setBackground(new Color(190, 190, 190));
			}
		});

		// 初始化
		JB_Register = new JButton("注册");
		// 设置位置大小
		JB_Register.setBounds(320, 350, 100, 50);
		// 设置字体
		JB_Register.setFont(new Font("宋体", Font.CENTER_BASELINE, 30));
		// 设置控件透明
		JB_Register.setOpaque(true);
		// 设置背景透明
		JB_Register.setBackground(new Color(145, 133, 157));
		// 设置是否绘制边框
		JB_Register.setBorderPainted(true);
		// 不绘制焦点
		JB_Register.setFocusPainted(false);

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
				JB_Register.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				JB_login.requestFocus(); // 初始化焦点
				Init_values(); // 清空当前程序的值
				Login.this.setVisible(false); // 先隐藏这个窗口，转到另一个窗口
				GUISystem.register.setVisible(true); // 显示注册窗体
				Login.this.setLocationRelativeTo(null); // 归位窗体位置
			}
		});

		JB_Register.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				JB_Register.setBackground(new Color(145, 133, 157));
			}

			@Override
			public void focusGained(FocusEvent e) {
				JB_Register.setBackground(new Color(190, 190, 190));
			}
		});
	}

	public void PanelSet() {
		// 设置背景(获取图片地址)
		SetBackGround();
		// 创建个容器，并设置
		base = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 2329755506587564311L;

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
	}

	public void FrameSet() {
		// 创建并设置窗体
		this.setTitle("学生管理系统登入界面");
		// 设置内容显示
		this.setContentPane(base);
		// 设置窗体大小
		this.setSize(500, 500);
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
		this.setVisible(true);
		// 设置居中
		this.setLocationRelativeTo(null);
		// 窗体事件
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				JB_login.requestFocus();
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
				// 设置默认焦点
				JB_login.requestFocus();
			}
		});
	}

	// 登入事件的处理方法
	private void Login_try() {
		// 获取用户和密码，保密措施暂时不做
		Get_Account = JT_Account.getText();
		Get_PWD = String.valueOf(JP_Passwd.getPassword()); // 不安全的做法
		try {
			// 调用执行 SQL 语句
			rs = Jdbc.SqlStatement("select * from `教师信息` where `工号` = '" + Get_Account + "'");
		} catch (SQLException e) {
			// 异常处理
			e.printStackTrace();
		}
		// 老师
		try {
			if (rs.next()) {
				try {
					// 老师的标识
					SQL_CLP = 1;
					// 取账户
					SQL_Account = rs.getInt(1) + "";
					// 取密码
					SQL_PWD = rs.getString(3) + "";
				} catch (SQLException e) {
					// 异常处理
					e.printStackTrace();
				}
			} else {
				try {
					rs = Jdbc.SqlStatement("select * from `学生信息` where `学号` = '" + Get_Account +"'");

					if (rs.next()) {
						// 学生的标识
						SQL_CLP = 2;
						// 取账户
						SQL_Account = rs.getInt(1) + "";
						// 取密码
						SQL_PWD = rs.getString(6) + "";
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 如果账户、密码正确且存活，则允许登入
		if ((Get_PWD.equals(SQL_PWD)) && (SQL_Account.equals(Get_Account))) {
			flag = true;
		}
		if (!flag) {
			if ((!(Get_PWD.equals(SQL_PWD))) && (SQL_Account.equals(Get_Account))) {
				JOptionPane.showMessageDialog(null, "密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
			} else if(Get_Account.length() != 4 && Get_Account.length() != 8){
				JOptionPane.showMessageDialog(null, "请输入 4 位或者 8 位账号！", "错误", JOptionPane.ERROR_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, "该账户不存在！", "错误", JOptionPane.ERROR_MESSAGE);
			}
		}

		// 跳转
		if (flag) {
			if (SQL_CLP == 1) {
				this.setVisible(false);
				GUISystem.center.switchT();
				GUISystem.center.setVisible(true);
				Login.this.setLocationRelativeTo(null); // 归位窗体位置
			} else {
				this.setVisible(false);
				GUISystem.center.switchS();
				GUISystem.center.setVisible(true);
				Login.this.setLocationRelativeTo(null); // 归位窗体位置
			}
		}
	}

	public void Init_values() {
		// 初始化值
		flag = false; // 判断是否成功登入

		SQL_CLP = 0; // 存储权限等级
		SQL_Account = ""; // 存储数据库中取出的账户
		SQL_PWD = ""; // 存储数据库中取出的密码

		Get_Account = ""; // 存储用户输入的账户
		Get_PWD = ""; // 存储用户输入的密码

		rs = null; // 存储数据库返回的内容

		JT_Account.setText("请输入账户名");

		JP_Passwd.setText("请输入密码");
		JP_Passwd.setEchoChar((char) 0);
	}

	public void SetBackGround() {
		/*
		 * JL_Title.setForeground(Color.WHITE);
		 * JL_Account.setForeground(Color.WHITE);
		 * JL_Passwd.setForeground(Color.WHITE);
		 */
		// 获取随机数随机选择背景
		// 当背景干扰时，重新设置字体颜色
		Set = rand.nextInt(50);
		switch (Set) {
		case 0:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\0.png");
			break;
		case 1:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\1.png");
			break;
		case 2:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\2.png");
			break;
		case 3:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\3.png");
			break;
		case 4:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\4.png");
			break;
		case 5:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\5.png");
			break;
		case 6:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\6.png");
			break;
		case 7:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\7.png");
			break;
		case 8:
			// 获取当前项目路径
			// String t = System.getProperty("user.dir");
			// 获取图片路径... MMP 不知道为什么，相对路径就是打印不出来...
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\8.png");
			break;
		case 9:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\9.png");
			break;
		case 10:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\10.png");
			break;
		case 11:
			JL_Passwd.setForeground(Color.WHITE);
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\11.png");
			break;
		case 12:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\12.png");
			break;
		case 13:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\13.png");
			break;
		case 14:
			JL_Passwd.setForeground(new Color(170, 170, 10));
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\14.png");
			break;
		case 15:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\15.png");
			break;
		case 16:
			JL_Title.setForeground(Color.WHITE);
			JL_Account.setForeground(Color.WHITE);
			JL_Passwd.setForeground(Color.WHITE);
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\16.png");
			break;
		case 17:
			JL_Passwd.setForeground(Color.WHITE);
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\17.png");
			break;
		case 18:
			JL_Passwd.setForeground(Color.WHITE);
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\18.png");
			break;
		case 19:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\19.png");
			break;
		case 20:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\20.png");
			break;
		case 21:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\21.png");
			break;
		case 22:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\22.png");
			break;
		case 23:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\23.png");
			break;
		case 24:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\24.png");
			break;
		case 25:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\25.png");
			break;
		case 26:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\26.png");
			break;
		case 27:
			JL_Title.setForeground(Color.WHITE);
			JL_Account.setForeground(Color.WHITE);
			JL_Passwd.setForeground(Color.WHITE);
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\27.png");
			break;
		case 28:
			JL_Title.setForeground(Color.WHITE);
			JL_Account.setForeground(Color.WHITE);
			JL_Passwd.setForeground(Color.WHITE);
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\28.png");
			break;
		case 29:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\29.png");
			break;
		case 30:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\30.png");
			break;
		case 31:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\31.png");
			break;
		case 32:
			JL_Title.setForeground(Color.WHITE);
			JL_Account.setForeground(Color.WHITE);
			JL_Passwd.setForeground(Color.WHITE);
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\32.png");
			break;
		case 33:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\33.png");
			break;
		case 34:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\34.png");
			break;
		case 35:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\35.png");
			break;
		case 36:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\36.png");
			break;
		case 37:
			JL_Title.setForeground(Color.WHITE);
			JL_Account.setForeground(Color.WHITE);
			JL_Passwd.setForeground(Color.WHITE);
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\37.png");
			break;
		case 38:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\38.png");
			break;
		case 39:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\39.png");
			break;
		case 40:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\40.png");
			break;
		case 41:
			JL_Title.setForeground(Color.WHITE);
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\41.png");
			break;
		case 42:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\42.png");
			break;
		case 43:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\43.png");
			break;
		case 44:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\44.png");
			break;
		case 45:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\45.png");
			break;
		case 46:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\46.png");
			break;
		case 47:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\47.png");
			break;
		case 48:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\48.png");
			break;
		case 49:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\49.png");
			break;
		case 50:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\50.png");
			break;

		}
		img = icon.getImage();
	}
}
