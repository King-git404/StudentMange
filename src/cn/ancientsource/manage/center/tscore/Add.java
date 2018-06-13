package cn.ancientsource.manage.center.tscore;

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
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
	private static final long serialVersionUID = -2589296397466456224L;

	// 组件创建列表
	private ImageIcon icon; // 已实现

	private Image img; // 已实现

	private JPanel base;

	private JLabel JL_Title;
	// 学号
	private JLabel JL_SID;
	private JLabel JL_S1;
	private JLabel JL_S2;
	private JLabel JL_S3;

	private JTextField JT_SID;
	private JTextField JT_S1;
	private JTextField JT_S2;
	private JTextField JT_S3;
	
	private int sum;

	private JButton JB_Enter;

	public Add() {
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

		JFrameSet();
	}

	private void JFrameSet() {
		// 创建并设置窗体
		this.setTitle("学生信息添加");
		// 设置内容显示
		this.setContentPane(base);
		// 设置窗体大小
		this.setSize(500, 800 - 100);
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

		JT_S1 = new JTextField("请输入成绩1");
		JT_S1.setBounds(170, 265, 280, 35);
		JT_S1.setFont(new Font("谐体", Font.BOLD, 25));
		JT_S1.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JT_S1.addKeyListener(new KeyListener() {

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
		JT_S1.addCaretListener(new CaretListener() {

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
				if (Text.equals("请输入成绩1")) {
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
				if (Text.length() > 3) {
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
		JT_S1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if ("".equals(JT_S1.getText())) {
					JT_S1.setText("请输入成绩1");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if ("请输入成绩1".equals(JT_S1.getText())) {
					JT_S1.setText("");
				}
			}
		});
		
		JT_S2 = new JTextField("请输入成绩2");
		JT_S2.setBounds(170, 335, 280, 35);
		JT_S2.setFont(new Font("谐体", Font.BOLD, 25));
		JT_S2.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JT_S2.addKeyListener(new KeyListener() {

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
		JT_S2.addCaretListener(new CaretListener() {

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
				if (Text.equals("请输入成绩2")) {
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
				if (Text.length() > 3) {
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
		JT_S2.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if ("".equals(JT_S2.getText())) {
					JT_S2.setText("请输入成绩2");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if ("请输入成绩2".equals(JT_S2.getText())) {
					JT_S2.setText("");
				}
			}
		});
		
		JT_S3 = new JTextField("请输入成绩3");
		JT_S3.setBounds(170, 410, 280, 35);
		JT_S3.setFont(new Font("谐体", Font.BOLD, 25));
		JT_S3.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JT_S3.addCaretListener(new CaretListener() {

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
				if (Text.equals("请输入成绩3")) {
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
				if (Text.length() > 3) {
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
		JT_S3.addKeyListener(new KeyListener() {

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
		JT_S3.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if ("".equals(JT_S3.getText())) {
					JT_S3.setText("请输入成绩3");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if ("请输入成绩3".equals(JT_S3.getText())) {
					JT_S3.setText("");
				}
			}
		});
	}

	private void JButtonSet() {
		JB_Enter = new JButton("确定");
		JB_Enter.setBounds(160, 620 - 100, 200, 100);
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
		icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\Sore.png");
		img = icon.getImage();

		base = new JPanel(){
			/**
			 * @author luo
			 */
			private static final long serialVersionUID = -2848313275721002488L;

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
		base.add(JL_S1);
		base.add(JL_S2);
		base.add(JL_S3);

		base.add(JT_SID);
		base.add(JT_S1);
		base.add(JT_S2);
		base.add(JT_S3);

		base.add(JB_Enter);
	}

	private void JLabelSet() {
		// 初始化并设置显示文字
		JL_Title = new JLabel();
		// 设置标题
		JL_Title.setText("添 加 成 绩");
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
		
		JL_S1 = new JLabel("成绩1");
		JL_S1.setFont(new Font("幼圆", Font.PLAIN, 25));
		JL_S1.setForeground(Color.BLACK);
		JL_S1.setBounds(50, 270, 100, 25);
		
		JL_S2 = new JLabel("成绩2");
		JL_S2.setFont(new Font("幼圆", Font.PLAIN, 25));
		JL_S2.setForeground(Color.BLACK);
		JL_S2.setBounds(50, 340, 100, 25);
		
		JL_S3 = new JLabel("成绩3");
		JL_S3.setFont(new Font("幼圆", Font.PLAIN, 25));
		JL_S3.setForeground(Color.BLACK);
		JL_S3.setBounds(50, 410, 100, 25);
	}

	public void upDate() {
		if(JT_SID.getText().equals("") || JT_SID.getText().equals("请输入学号")){
			JOptionPane.showMessageDialog(null, "学号未输入！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(JT_SID.getText().length() != 8){
			JOptionPane.showMessageDialog(null, "请输入8位学号！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			sum = 0;
			ResultSet rs = Jdbc.SqlStatement("SELECT * FROM `学生成绩` WHERE `学号`=" + JT_SID.getText());
			if(rs.next()){
				JOptionPane.showMessageDialog(null, "该学生成绩已录入！", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			rs = Jdbc.SqlStatement("SELECT * FROM `学生信息` WHERE `学号`=" + JT_SID.getText());
			if(!rs.next()){
				JOptionPane.showMessageDialog(null, "该学生不存在！", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			//rs.beforeFirst();
			String sql = "insert into `学生成绩` values ('" + JT_SID.getText() + "','" + rs.getString(2) + "',";
			
			if(!JT_S1.getText().equals("") && !JT_S1.getText().equals("请输入成绩1")){
				sum += Integer.valueOf(JT_S1.getText());
				sql += "'" + JT_S1.getText() + "',";
			}else{
				sql += "'0',";
			}
			if(!JT_S2.getText().equals("") && !JT_S2.getText().equals("请输入成绩2")){
				sum += Integer.valueOf(JT_S2.getText());
				sql += "'" + JT_S2.getText() + "',";
			}else{
				sql += "'0',";
			}
			if(!JT_S3.getText().equals("") && !JT_S3.getText().equals("请输入成绩3")){
				sum += Integer.valueOf(JT_S3.getText());
				sql += "'" + JT_S3.getText() + "','" + sum + "')";
			}else{
				sql += "'0','" + sum + "')";
			}
			Jdbc.SqlStatementUpdate(sql);
			BaseT.tScore.reWrite();
			BaseT.tScoreCount.reWrite();
			this.setVisible(false);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
