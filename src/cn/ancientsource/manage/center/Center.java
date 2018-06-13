package cn.ancientsource.manage.center;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import cn.ancientsource.manage.database.Jdbc;

public class Center extends JFrame {
	/**
	 * @author luo
	 */
	private static final long serialVersionUID = 3089294126902933658L;
	public static BaseS baseS = new BaseS();
	public static BaseT baseT = new BaseT();

	// 组件列表
	private Image Ico;

	private Toolkit tool;

	public Center() {
		GUI();
	}

	public void GUI() {
		// 顶级容器初始化
		FrameSet();
	}

	private void FrameSet() {
		// 创建并设置窗体
		this.setTitle("学生管理系统");
		// 设置内容显示
		//this.setContentPane(baseT);
		// 设置窗体大小
		this.setSize(1500, 1000);
		// 设置位置
		this.setLocation(200, 40);
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
		// 设置居中
		this.setLocationRelativeTo(null);
		// 设置是否可见
		this.setVisible(false);
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {}
			
			@Override
			public void windowIconified(WindowEvent e) {}
			
			@Override
			public void windowDeiconified(WindowEvent e) {}
			
			@Override
			public void windowDeactivated(WindowEvent e) {}
			
			@Override
			public void windowClosing(WindowEvent e) {
				Jdbc.closeSql(); // 关闭程序前先关闭数据库
			}
			
			@Override
			public void windowClosed(WindowEvent e) {}
			
			@Override
			public void windowActivated(WindowEvent e) {
				
			}
		});
	}
	
	public void switchS(){
		baseT.initValue();
		this.setContentPane(baseS);
	}
	
	public void switchT(){
		baseS.initValue();
		this.setContentPane(baseT);
	}
}
