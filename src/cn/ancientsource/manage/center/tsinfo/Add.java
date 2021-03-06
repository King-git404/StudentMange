package cn.ancientsource.manage.center.tsinfo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import cn.ancientsource.manage.center.BaseT;
import cn.ancientsource.manage.database.Jdbc;

public class Add extends JFrame {
	/**
	 * @author luo
	 */
	private static final long serialVersionUID = -404256303904204347L;

	private String[][] Speciality;

	// 组件创建列表
	private ImageIcon icon; // 已实现

	private Image img; // 已实现

	private JPanel base;

	private JLabel JL_Title;
	// 学号
	private JLabel JL_SID;
	// 密码
	private JLabel JL_PWD;
	// 姓名
	private JLabel JL_Name;
	// 性别
	private JLabel JL_Sex;
	// 院系
	private JLabel JL_Faculty;
	// 专业
	private JLabel JL_Speciality;

	private JTextField JT_SID;
	private JTextField JT_PWD;
	private JTextField JT_Name;

	private JComboBox<String> JCB_Sex;
	private JComboBox<String> JCB_Faculty;
	private JComboBox<String> JCB_Speciality;

	private JButton JB_Enter;

	public Add() {
		GUI();
	}

	public void GUI() {
		// 按钮设置初始化
		JButtonSet();
		// 文字框设置初始化
		JTextFieldSet();
		// 组合框设置
		JComboBoxSet();
		// 文本框设置初始化
		JLabelSet();
		// 次级容器初始化
		PanelSet();

		JFrameSet();
	}

	private void JFrameSet() {
		// 创建并设置窗体
		this.setTitle("学生信息添加");
		// 设置内容显示
		this.setContentPane(base);
		// 设置窗体大小
		this.setSize(500, 800);
		// 设置位置
		this.setLocation(800, 300);
		// 设置点击关闭按钮的默认动作
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// 锁定窗体
		this.setResizable(false);
		// 设置背景色（虽然没啥用）
		this.setBackground(Color.WHITE);
		// 得到一个 Toolkit 对象
		Toolkit tool = this.getToolkit();
		// 通过 tool 获取图像
		Image Ico = tool.getImage(System.getProperty("user.dir") + "\\images\\9.png");
		// 设置图标
		this.setIconImage(Ico);
		// 设置是否可见
		this.setVisible(true);
		// 设置居中
		this.setLocationRelativeTo(null);
	}

	private void JComboBoxSet() {
		String[] Sex = new String[] { "女", "男" };
		JCB_Sex = new JComboBox<String>(Sex);
		JCB_Sex.setFont(new Font("谐体", Font.BOLD, 25));
		JCB_Sex.setEditable(false);
		JCB_Sex.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JCB_Sex.setBounds(170, 410, 280, 35);
		JCB_Sex.addKeyListener(new KeyListener() {
			
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
				}
			}
		});

		String[] Faculty = new String[] { "农学院", "园林与艺术学院", "动物科学技术学院", "工学院", "软件学院", "计算机与信息工程学院", "经济管理学院", "理学院",
				"人文与公共管理学院", "国土资源与环境学院", "军体部", "生物科学与工程学院", "外国语学院", "食品科学与工程学院", "职业师范(技术)学院", "图书馆", "马克思主义学院",
				"商学院", "继续教育学院", "婺源茶校" };
		JCB_Faculty = new JComboBox<String>(Faculty);
		JCB_Faculty.setFont(new Font("谐体", Font.BOLD, 25));
		JCB_Faculty.setEditable(false);
		JCB_Faculty.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JCB_Faculty.setBounds(170, 480, 280, 35);
		JCB_Faculty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 根据选择的学院更改专业
				JCB_Speciality.removeAllItems();
				for (int i = 0; i < Speciality[JCB_Faculty.getSelectedIndex()].length; i++) {
					JCB_Speciality.addItem(Speciality[JCB_Faculty.getSelectedIndex()][i]);
				}
			}
		});
		JCB_Faculty.addKeyListener(new KeyListener() {
			
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
				}
			}
		});

		Speciality = new String[20][];
		Speciality[0] = new String[] { "农学", "园艺", "植物保护", "农村区域发展", "蚕学", "动植物检疫", "种子科学与工程", "茶学", "惟一农学" };
		Speciality[1] = new String[] { "林学", "园林", "城市规划", "艺术设计", "中药资源开发", "林产化工", "城乡规划", "风景园林", "环境设计", "视觉传达设计",
				"林学实验班" };
		Speciality[2] = new String[] { "动物科学", "动物医学", "水产养殖学", "动物药学" };
		Speciality[3] = new String[] { "电子信息工程", "机械制造与自动化", "模具设计与制造", "汽车运输工程", "汽车检测与维修技术", "农业机械化及其自动化",
				"机械设计制造及自动化", "交通运输", "农业建筑环境与能源工程", "土木工程", "电子信息工程", "工程管理", "车辆工程" };
		Speciality[4] = new String[] { "软件工程", "物联网工程" };
		Speciality[5] = new String[] { "计算机科学与技术", "信息管理与信息系统", "电子商务", "网络工程", "计算机科学与技术" };
		Speciality[6] = new String[] { "工商管理双学位", "国际经济与贸易双学位", "会计学双学位", "经济学双学位", "金融学双学位", "财务管理双学位", "市场营销双学位",
				"农林经济管理", "经济学", "国际经济与贸易", "工商管理", "金融学", "劳动与社会保障", "财务管理", "市场营销" };
		Speciality[7] = new String[] { "应用化学", "信息与计算科学" };
		Speciality[8] = new String[] { "公共事业管理", "新闻学", "汉语学", "汉语言文学", "法学", "法学双学位", "管理科学", "音乐学", "音乐表演" };
		Speciality[9] = new String[] { "农业水利工程", "农业资源与环境", "土地资源管理", "地理信息科学", "地理信息系统", "旅游管理", "环境工程", "环境科学" };
		Speciality[10] = new String[] { "无" };
		Speciality[11] = new String[] { "制药工程", "生物工程", "生物技术", "生物科学" };
		Speciality[12] = new String[] { "商务英语", "日语", "英语", "英语双学位" };
		Speciality[13] = new String[] { "轻化工程", "食品科学与工程", "食品质量与安全" };
		Speciality[14] = new String[] { "中英文秘书", "会计电算化", "农艺教育", "动物营养与饲料科学", "可视化程序设计", "商务英语", "园林技术", "应用化工技术",
				"应用电子技术", "教育技术", "教育技术学", "数字媒体艺术", "文秘教育", "旅游与酒店管理", "旅游管理", "现代城镇园林绿化", "电脑艺术设计", "畜禽生产教育",
				"网络系统管理", "营销与策划", "计算机图形图像制作", "计算机应用技术", "计算机科学与技术", "饲料与动物营养" };
		Speciality[15] = new String[] { "无" };
		Speciality[16] = new String[] { "无" };
		Speciality[17] = new String[] { "无" };
		Speciality[18] = new String[] { "无" };
		Speciality[19] = new String[] { "无" };

		JCB_Speciality = new JComboBox<String>(Speciality[0]);
		JCB_Speciality.setFont(new Font("谐体", Font.BOLD, 25));
		JCB_Speciality.setEditable(false);
		JCB_Speciality.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JCB_Speciality.setBounds(170, 550, 280, 35);
		JCB_Speciality.addKeyListener(new KeyListener() {
			
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
				}
			}
		});
	}

	private void JTextFieldSet() {
		JT_SID = new JTextField("请输入学号");
		JT_SID.setBounds(170, 195, 280, 35);
		JT_SID.setFont(new Font("谐体", Font.BOLD, 25));
		JT_SID.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JT_SID.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if ("".equals(JT_SID.getText())) {
					JT_SID.setText("请输入学号");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if ("请输入学号".equals(JT_SID.getText())) {
					JT_SID.setText("");
				}
			}
		});
		JT_SID.addCaretListener(new CaretListener() {

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
				if (Text.equals("请输入学号")) {
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
		JT_SID.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					upDate();
				}
			}
		});

		JT_PWD = new JTextField("请输入密码");
		JT_PWD.setBounds(170, 265, 280, 35);
		JT_PWD.setFont(new Font("谐体", Font.BOLD, 25));
		JT_PWD.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JT_PWD.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					upDate();
				}
			}
		});
		JT_PWD.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if ("".equals(JT_PWD.getText())) {
					JT_PWD.setText("请输入密码");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if ("请输入密码".equals(JT_PWD.getText())) {
					JT_PWD.setText("");
				}
			}
		});

		JT_Name = new JTextField("请输入姓名");
		JT_Name.setBounds(170, 335, 280, 35);
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
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					upDate();
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

	private void JButtonSet() {
		JB_Enter = new JButton("确定");
		JB_Enter.setBounds(160, 620, 200, 100);
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
			}
		});
	}

	private void PanelSet() {
		// 设置背景
		// 获取图片路径
		icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\S.png");
		img = icon.getImage();

		base = new JPanel(){
			/**
			 * @author luo
			 */
			private static final long serialVersionUID = 1247415254217551987L;

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
		base.add(JL_SID);
		base.add(JL_PWD);
		base.add(JL_Name);
		base.add(JL_Sex);
		base.add(JL_Faculty);
		base.add(JL_Speciality);

		base.add(JT_SID);
		base.add(JT_PWD);
		base.add(JT_Name);

		base.add(JCB_Sex);
		base.add(JCB_Faculty);
		base.add(JCB_Speciality);

		base.add(JB_Enter);
	}

	private void JLabelSet() {
		// 初始化并设置显示文字
		JL_Title = new JLabel();
		// 设置标题
		JL_Title.setText("添 加 信 息");
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

		JL_Sex = new JLabel();
		JL_Sex.setText("性别");
		JL_Sex.setFont(new Font("幼圆", Font.PLAIN, 25));
		JL_Sex.setForeground(Color.BLACK);
		JL_Sex.setBounds(50, 410, 100, 25);

		JL_Faculty = new JLabel();
		JL_Faculty.setText("院系");
		JL_Faculty.setFont(new Font("幼圆", Font.PLAIN, 25));
		JL_Faculty.setForeground(Color.BLACK);
		JL_Faculty.setBounds(50, 480, 50, 25);

		JL_Speciality = new JLabel();
		JL_Speciality.setText("专业");
		JL_Speciality.setFont(new Font("幼圆", Font.PLAIN, 25));
		JL_Speciality.setForeground(Color.BLACK);
		JL_Speciality.setBounds(50, 550, 100, 25);

	}

	public void upDate() {
		if(JT_SID.getText().equals("") || JT_SID.getText().equals("请输入学号") || JT_PWD.getText().equals("") || JT_PWD.getText().equals("请输入密码")){
			JOptionPane.showMessageDialog(null, "学号或密码未输入！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(JT_SID.getText().length() != 8){
			JOptionPane.showMessageDialog(null, "请输入8位学号！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(JT_PWD.getText().length() < 4){
			JOptionPane.showMessageDialog(null, "密码至少要四位！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			ResultSet rs = Jdbc.SqlStatement("SELECT * FROM `学生信息` WHERE `学号`=" + JT_SID.getText());
			if(rs.next()){
				JOptionPane.showMessageDialog(null, "该用户已存在！", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String sql = "insert into `学生信息` values ('" + JT_SID.getText() + "',";
			if(!JT_Name.getText().equals("") && !JT_Name.getText().equals("请输入姓名")){
				sql += "'" + JT_Name.getText() + "','" + JCB_Sex.getSelectedItem() +"','" + JCB_Faculty.getSelectedItem() + "','" + JCB_Speciality.getSelectedItem() + "','" + JT_PWD.getText() + "')";
			}else{
				sql += "null,'" + JCB_Sex.getSelectedItem() +"','" + JCB_Faculty.getSelectedItem() + "','" + JCB_Speciality.getSelectedItem() + "','" + JT_PWD.getText() + "')";
			}
			Jdbc.SqlStatementUpdate(sql);
			BaseT.tSInfo.reWrite();
			this.setVisible(false);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
