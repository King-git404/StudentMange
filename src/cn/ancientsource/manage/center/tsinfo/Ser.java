package cn.ancientsource.manage.center.tsinfo;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import cn.ancientsource.manage.center.BaseT;
import cn.ancientsource.manage.center.TSInfo;

public class Ser extends JFrame {

	/**
	 * @author luo
	 */
	private static final long serialVersionUID = -4665571384067887048L;
	private JTextField a;

	public Ser() {
		a = new JTextField("请输入要查找的信息");
		a.setFont(new Font("谐体", Font.BOLD, 25));
		a.setSize(250, 35);
		a.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) { // 聚焦中
				if ("请输入要查找的信息".equalsIgnoreCase(a.getText())) {
					a.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) { // 无聚集
				if ("".equals(a.getText().trim())) {
					a.setText("请输入要查找的信息");
				}
			}

		});
		a.addKeyListener(new KeyListener() {
			
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
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					if(!a.getText().equals("") && !a.getText().equals("请输入要查找的信息")){
						TSInfo.account = a.getText();
						Ser.this.setVisible(false);
						BaseT.tSInfo.search();
					}
				}
			}
		});

		this.setSize(250, 85);
		this.setLocationRelativeTo(null);
		// 得到一个 Toolkit 对象
		Toolkit tool = this.getToolkit();
		// 通过 tool 获取图像
		Image Ico = tool.getImage(System.getProperty("user.dir") + "\\images\\9.png");
		// 设置图标
		this.setIconImage(Ico);
		// 设置是否可见
		this.setVisible(true);
		// 设置点击关闭按钮的默认动作
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.add(a);
	}
}
