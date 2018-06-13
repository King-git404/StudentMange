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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import cn.ancientsource.manage.center.tscore.*;
import cn.ancientsource.manage.database.Jdbc;

// 学生成绩 -- 教师面板
public class TScore extends JPanel {
	/**
	 * @author luo
	 */
	private static final long serialVersionUID = -2357455791079327751L;
	private String[] title = { "学号", "姓名", "成绩1", "成绩2", "成绩3", "总分" };
	private String[][] info;

	public static String account;

	private int count;
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
	private JButton JB_re;
	private JButton JB_del;

	public TScore() {
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
		JB_add = new JButton("添加");
		// 设置位置大小
		JB_add.setBounds(5 + 200, 910, 100, 50);
		// 设置字体
		JB_add.setFont(new Font("宋体", Font.CENTER_BASELINE, 30));
		// 设置控件透明
		JB_add.setOpaque(true);
		// 设置背景
		JB_add.setBackground(new Color(161, 133, 124));
		// 设置是否绘制边框
		JB_add.setBorderPainted(true);
		// 不绘制焦点
		JB_add.setFocusPainted(false);
		// 设置字体颜色
		JB_add.setForeground(Color.WHITE);
		
		JB_add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Add();
			}
		});

		JB_ser = new JButton("查找");
		// 设置位置大小
		JB_ser.setBounds(155 + 200, 910, 100, 50);
		// 设置字体
		JB_ser.setFont(new Font("宋体", Font.CENTER_BASELINE, 30));
		// 设置控件透明
		JB_ser.setOpaque(true);
		// 设置背景
		JB_ser.setBackground(new Color(178, 130, 79));
		// 设置是否绘制边框
		JB_ser.setBorderPainted(true);
		// 不绘制焦点
		JB_ser.setFocusPainted(false);
		// 设置字体颜色
		JB_ser.setForeground(Color.WHITE);

		JB_ser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Ser();
			}
		});

		JB_Dser = new JButton("取消查找");
		// 设置位置大小
		JB_Dser.setBounds(305 + 200, 910, 200, 50);
		// 设置字体
		JB_Dser.setFont(new Font("宋体", Font.CENTER_BASELINE, 30));
		// 设置控件透明
		JB_Dser.setOpaque(true);
		// 设置背景
		JB_Dser.setBackground(new Color(188, 110, 105));
		// 设置是否绘制边框
		JB_Dser.setBorderPainted(true);
		// 不绘制焦点
		JB_Dser.setFocusPainted(false);
		// 设置字体颜色
		JB_Dser.setForeground(Color.WHITE);

		JB_Dser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reWrite();
			}
		});

		JB_re = new JButton("修改");
		// 设置位置大小
		JB_re.setBounds(555 + 200, 910, 100, 50);
		// 设置字体
		JB_re.setFont(new Font("宋体", Font.CENTER_BASELINE, 30));
		// 设置控件透明
		JB_re.setOpaque(true);
		// 设置背景
		JB_re.setBackground(new Color(3, 141, 143));
		// 设置是否绘制边框
		JB_re.setBorderPainted(true);
		// 不绘制焦点
		JB_re.setFocusPainted(false);
		// 设置字体颜色
		JB_re.setForeground(Color.WHITE);

		JB_re.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// System.out.println(info[JT.getSelectedRow()][0]);
				if (JT.getSelectedRow() != -1) {
					new Update(info[JT.getSelectedRow()][0]);
				}
			}
		});

		JB_del = new JButton("删除");
		// 设置位置大小
		JB_del.setBounds(705 + 200, 910, 100, 50);
		// 设置字体
		JB_del.setFont(new Font("宋体", Font.CENTER_BASELINE, 30));
		// 设置控件透明
		JB_del.setOpaque(true);
		// 设置背景
		JB_del.setBackground(new Color(11, 82, 175));
		// 设置是否绘制边框
		JB_del.setBorderPainted(true);
		// 不绘制焦点
		JB_del.setFocusPainted(false);
		// 设置字体颜色
		JB_del.setForeground(Color.WHITE);

		JB_del.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (JT.getSelectedRow() != -1) {
					try {
						Jdbc.SqlStatementUpdate("DELETE FROM `学生成绩` WHERE `学号`=" + info[JT.getSelectedRow()][0]);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					reWrite();
					BaseT.tScoreCount.reWrite();
				}
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
		icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\Main_3.png");
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
		this.add(JB_del);
		this.add(JB_Dser);
		this.add(JB_re);
		this.add(JB_ser);
	}

	public void search() {
		try {
			if (account != null) {
				ResultSet rs = Jdbc.SqlStatement("SELECT * FROM `学生成绩` WHERE `学号` like '%" + account + "%'");
				rs.last();
				count = rs.getRow();
				rs.beforeFirst();
				if (count >= 0)
					info = new String[count][6];
				if (rs.next()) {
					rs.beforeFirst();
					for (int i = 0; i < count; i++) {
						if (rs.next()) {
							for (int j = 0; j < 6; j++) {
								info[i][j] = rs.getString(j + 1);
							}
						}
					}
					model.setDataVector(info, title);
				} else {
					rs = Jdbc.SqlStatement("SELECT * FROM `学生成绩` WHERE `姓名` like '%" + account + "%'");
					rs.last();
					count = rs.getRow();
					rs.beforeFirst();
					if (count >= 0)
						info = new String[count][6];
					if (rs.next()) {
						rs.beforeFirst();
						for (int i = 0; i < count; i++) {
							if (rs.next()) {
								for (int j = 0; j < 6; j++) {
									info[i][j] = rs.getString(j + 1);
								}
							}
						}
						model.setDataVector(info, title);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getInfo() {
		try {
			ResultSet rs = Jdbc.SqlStatement("SELECT * FROM `学生成绩`");
			rs.last();
			count = rs.getRow();
			info = new String[rs.getRow()][6];
			rs.beforeFirst();
			for (int i = 0; i < count; i++) {
				if (rs.next()) {
					for (int j = 0; j < 6; j++) {
						info[i][j] = rs.getString(j + 1);
					}
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void reWrite(){
		getInfo();
		model.setDataVector(info, title);
	}
}
