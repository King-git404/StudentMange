package cn.ancientsource.manage.center;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import cn.ancientsource.manage.database.Jdbc;

// 个人信息 -- 教师面板
public class TScoreCount extends JPanel{
	/**
	 * @author luo
	 */
	private static final long serialVersionUID = -8191693428130074545L;
	private String[] title = { "学号", "姓名", "成绩1", "成绩2", "成绩3", "总分" };
	private String[][] info;

	public static String account;

	private int count;
	private int s1;
	private int s2;
	private int s3;
	private int sc;
	private DefaultTableModel model;
	private JTableHeader JTH;
	private JTable JT;

	// 组件创建列表
	private ImageIcon icon; // 已实现

	private Image img; // 已实现

	private JScrollPane JSP;

	private JButton JB_add;
	private JButton JB_ser;
	private JButton JB_Dser;
	private JButton JB_RE;

	public TScoreCount() {
		GUI();
	}

	private void GUI() {
		JButtonSet();

		JScrollPaneSet();
		// 表格初始化
		JTableSet();
		// 次级容器初始化
		PanelSet();
	}

	private void JButtonSet() {
		JB_add = new JButton("高分排序");
		// 设置位置大小
		JB_add.setBounds(5 + 200, 910, 200, 50);
		// 设置字体
		JB_add.setFont(new Font("宋体", Font.CENTER_BASELINE, 30));
		// 设置控件透明
		JB_add.setOpaque(true);
		// 设置背景
		JB_add.setBackground(new Color(0, 103, 146));
		// 设置是否绘制边框
		JB_add.setBorderPainted(true);
		// 不绘制焦点
		JB_add.setFocusPainted(false);
		// 设置字体颜色
		JB_add.setForeground(Color.WHITE);
		
		JB_add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				up();
			}
		});

		JB_ser = new JButton("低分排序");
		// 设置位置大小
		JB_ser.setBounds(255 + 200, 910, 200, 50);
		// 设置字体
		JB_ser.setFont(new Font("宋体", Font.CENTER_BASELINE, 30));
		// 设置控件透明
		JB_ser.setOpaque(true);
		// 设置背景
		JB_ser.setBackground(new Color(0, 88, 126));
		// 设置是否绘制边框
		JB_ser.setBorderPainted(true);
		// 不绘制焦点
		JB_ser.setFocusPainted(false);
		// 设置字体颜色
		JB_ser.setForeground(Color.WHITE);

		JB_ser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				down();
			}
		});

		//JB_Dser = new JButton("默认排序");
		JB_Dser = new JButton("平均分");
		// 设置位置大小
		JB_Dser.setBounds(755 + 200, 910, 200, 50);
		// 设置字体
		JB_Dser.setFont(new Font("宋体", Font.CENTER_BASELINE, 30));
		// 设置控件透明
		JB_Dser.setOpaque(true);
		// 设置背景
		JB_Dser.setBackground(new Color(3, 101, 140));
		// 设置是否绘制边框
		JB_Dser.setBorderPainted(true);
		// 不绘制焦点
		JB_Dser.setFocusPainted(false);
		// 设置字体颜色
		JB_Dser.setForeground(Color.WHITE);

		JB_Dser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//reWrite();
				JOptionPane.showMessageDialog(null, "成绩1 ：" + s1 + "\n成绩2 ：" + s2 + "\n成绩3 ：" + s3 + "\n总分 ：" + sc, "平均分", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JB_RE = new JButton("默认排序");
		// 设置位置大小 
		JB_RE.setBounds(505 + 200, 910, 200, 50);
		// 设置字体
		JB_RE.setFont(new Font("宋体", Font.CENTER_BASELINE, 30));
		// 设置控件透明
		JB_RE.setOpaque(true);
		// 设置背景
		JB_RE.setBackground(new Color(20, 101, 140));
		// 设置是否绘制边框
		JB_RE.setBorderPainted(true);
		// 不绘制焦点
		JB_RE.setFocusPainted(false);
		// 设置字体颜色
		JB_RE.setForeground(Color.WHITE);

		JB_RE.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reWrite();
			}
		});
	}

	private void JScrollPaneSet() {
		JSP = new JScrollPane();
		JSP.setBounds(5, 5, 1280, 900);
		JSP.setOpaque(false);
		JSP.setBackground(null);
		JSP.getViewport().setOpaque(false);
		JSP.getViewport().setBorder(null);
	}

	private void JTableSet() {
		getInfo();
		model = new DefaultTableModel();
		model.setDataVector(info, title);
		JT = new JTable(model);
		JT.setOpaque(false);
		JT.setFont(new Font("幼圆", Font.PLAIN, 20));
		// JT.setBackground(null);
		JT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JT.setColumnSelectionAllowed(false);
		JT.setRowSelectionAllowed(true);

		JTH = JT.getTableHeader();
		JTH.setFont(new Font("幼圆", Font.PLAIN, 20));
		JSP.getViewport().add(JT);
		JT.setRowHeight(25);
	}

	@Override
	public void paint(Graphics g) {
		this.setBounds(0, 0, 1300, 1000);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		super.paint(g);
	}

	private void PanelSet() {
		// 设置背景
		// 获取图片路径
		icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\Main_4.png");
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
		this.add(JSP);
		this.add(JB_add);
		this.add(JB_Dser);
		this.add(JB_ser);
		this.add(JB_RE);
	}

	public void getInfo() {
		try {
			s1 = 0;
			s2 = 0;
			s3 = 0;
			sc = 0;
			ResultSet rs = Jdbc.SqlStatement("SELECT * FROM `学生成绩`");
			rs.last();
			count = rs.getRow();
			info = new String[rs.getRow()][6];
			rs.beforeFirst();
			for (int i = 0; i < count; i++) {
				if (rs.next()) {
					s1 += Integer.valueOf(rs.getString(3));
					s2 += Integer.valueOf(rs.getString(4));
					s3 += Integer.valueOf(rs.getString(5));
					sc += Integer.valueOf(rs.getString(6));
					for (int j = 0; j < 6; j++) {
						info[i][j] = rs.getString(j + 1);
					}
				}
			}
			if(count != 0){
				s1 /= count;
				s2 /= count;
				s3 /= count;
				sc /= count;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void reWrite(){
		getInfo();
		model.setDataVector(info, title);
	}
	
	public void up(){
		try {
			s1 = 0;
			s2 = 0;
			s3 = 0;
			sc = 0;
			ResultSet rs = Jdbc.SqlStatement("SELECT * FROM `学生成绩` order by `总分` desc");
			rs.last();
			count = rs.getRow();
			info = new String[rs.getRow()][6];
			rs.beforeFirst();
			for (int i = 0; i < count; i++) {
				if (rs.next()) {
					s1 += Integer.valueOf(rs.getString(3));
					s2 += Integer.valueOf(rs.getString(4));
					s3 += Integer.valueOf(rs.getString(5));
					sc += Integer.valueOf(rs.getString(6));
					for (int j = 0; j < 6; j++) {
						info[i][j] = rs.getString(j + 1);
					}
				}
			}
			s1 /= count;
			s2 /= count;
			s3 /= count;
			sc /= count;
			model.setDataVector(info, title);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void down(){
		try {
			s1 = 0;
			s2 = 0;
			s3 = 0;
			sc = 0;
			ResultSet rs = Jdbc.SqlStatement("SELECT * FROM `学生成绩` order by `总分` asc");
			rs.last();
			count = rs.getRow();
			info = new String[rs.getRow()][6];
			rs.beforeFirst();
			for (int i = 0; i < count; i++) {
				if (rs.next()) {
					s1 += Integer.valueOf(rs.getString(3));
					s2 += Integer.valueOf(rs.getString(4));
					s3 += Integer.valueOf(rs.getString(5));
					sc += Integer.valueOf(rs.getString(6));
					for (int j = 0; j < 6; j++) {
						info[i][j] = rs.getString(j + 1);
					}
				}
			}
			s1 /= count;
			s2 /= count;
			s3 /= count;
			sc /= count;
			model.setDataVector(info, title);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
