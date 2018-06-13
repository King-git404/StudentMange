package cn.ancientsource.manage.register;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import cn.ancientsource.manage.database.Jdbc;

public class RegisterWrite extends JFrame {
	/**
	 * @author luo
	 */
	private static final long serialVersionUID = -2547626758598568134L;
	// 学生的后续操作界面
	public static RegisterWriteS registerWriteS = new RegisterWriteS();
	// 教师的后续操作界面
	public static RegisterWriteT registerWriteT = new RegisterWriteT();

	private Toolkit tool;
	private Image Ico;

	public RegisterWrite() {
		// 创建并设置窗体
		this.setTitle("学生管理系统登入界面");
		/*
		 * // 设置内容显示 this.setContentPane(base);
		 */
		// 设置窗体大小
		this.setSize(500, 1000);
		// 设置位置
		this.setLocation(800, 50);
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
		// 默认关闭程序
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				
			}
		});
	}
	
	//　　转到教师面板
	public void switchPanelT(){
		this.remove(registerWriteS);
		this.setSize(500, 600);
		this.setLocationRelativeTo(null);
		this.add(registerWriteT);
		this.repaint();
		registerWriteT.repaint();
	}
	
	// 转到学生面板
	public void switchPanelS(){
		this.remove(registerWriteT);
		this.setSize(540, 800);
		this.setLocationRelativeTo(null);
		this.add(registerWriteS);
		this.repaint();
		registerWriteS.repaint();
	}

	// 将会判断调用哪个使用者来初始化相应数值
	public void setDeualt(String a) {
		if(a.length() == 4){
			registerWriteT.setDeualt(a);
		}
		if(a.length() == 8){
			registerWriteS.setDeualt(a);
		}
	}

}
