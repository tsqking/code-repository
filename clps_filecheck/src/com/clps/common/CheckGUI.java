/**
 * Project Name:clps_filecheck
 * File Name:GUI.java
 * Package Name:com.clps.common
 * Date:2016年12月13日上午9:22:50
 * Copyright (c) 2016, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.common;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

/**
 * ClassName:CheckGUI <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年12月13日 上午9:22:50 <br/>
 * 
 * @author Charles.Tan
 * @version
 * @since JDK 1.8
 * @see
 */
public class CheckGUI extends JFrame implements ActionListener {
	private Container container;// 容器
	private JPanel jPanel1, jPanel2;//
	private TextField textField;// 文本框
	private JButton okButton, startButton, resetButton;// 按钮
	private JTextArea textArea;// 文本域
	private JScrollPane jScrollPane;
	private static final long serialVersionUID = 7104081303914377794L;
	private JDialog jDialog;// 对话框
	private JLabel jLabel;
	private String fileType; // 记录文件类型
	Logger logger = Logger.getRootLogger();// 日志

	private void init() {
		// 对话框初始化
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		jDialog = new JDialog(this, "提示信息", true);
		jDialog.setBounds((width - 500) / 2 + 150, (height - 500) / 2 + 100, 200, 100);
		jDialog.setResizable(false);
		jDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		jDialog.setVisible(false);
		jDialog.setLayout(new BorderLayout());

		container = getContentPane();// 容器初始化
		jPanel1 = new JPanel();// panel初始化
		jPanel2 = new JPanel();
		textArea = new JTextArea();// 文本域初始化
		jScrollPane = new JScrollPane(textArea);// 滚动条初始化
		jLabel = new JLabel();// label初始化
		// 设置文本域不可编辑
		textArea.setEditable(false);
		// 设置文本域内自动换行
		textArea.setLineWrap(true);
		// 设置文本域背景颜色
		textArea.setBackground(Color.WHITE);
		textArea.setFont(new Font("宋体", 0, 15));
		okButton = new JButton("确定");// 按钮初始化
		startButton = new JButton("开始匹配");
		resetButton = new JButton("重置");
		textField = new TextField("请指定文件包含字符（如abc-abc-123)", 45);// 文本框初始化
		// 设置文本框可编辑
		textField.setEditable(true);
		// 设置布局,添加组件
		container.setLayout(new BorderLayout());
		jPanel1.setLayout(new FlowLayout());
		jPanel2.setLayout(new BorderLayout());

		jPanel1.add(textField);
		jPanel1.add(okButton);
		jPanel1.add(resetButton);

		jPanel2.add(jScrollPane);

		jDialog.add(jLabel, BorderLayout.NORTH);
		jDialog.add(startButton);

		container.add(jPanel1, BorderLayout.NORTH);
		container.add(jPanel2, BorderLayout.CENTER);

		// 添加事件监听
		okButton.addActionListener(this);
		startButton.addActionListener(this);
		resetButton.addActionListener(this);

	}

	/**
	 * Creates a new instance of CheckGUI.
	 *
	 */
	public CheckGUI(String title) {
		// 主窗体初始化

		setTitle(title);
		// 获得当前屏幕的宽和高
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		// 把窗口设置在屏幕中间
		setLocation((width - 500) / 2, (height - 500) / 2);
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();// 调用init方法让其组件初始化
		setVisible(true);

		// 文本域显示所有文件
		showDir();
	}

	/**
	 * TODO 事件监听器
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		// 如果事件源是确定按钮
		if (source == okButton) {
			if (textField.getText().equals("请指定文件包含字符（如abc-abc-123)")) {
				jLabel.setText("当前匹配字符:无");
				jDialog.setVisible(true);
			} else {
				jLabel.setText("当前匹配字符:" + textField.getText());
				jDialog.setVisible(true);
			}

		}
		// 如果事件源是开始匹配按钮
		if (source == startButton) {
			int allCount = 0;// 初始化记录
			jDialog.setVisible(false);
			File file = new File("");
			File dir = new File(file.getAbsolutePath());
			// 如果文件存在，而且是个目录执行下列操作
			File[] names = dir.listFiles();
			// 获得文件目录列表
			// String[] names = dir.list();
			textArea.setText(null);
			for (File name : names) {
				if (name.isDirectory()) {
					fileType = "文件夹  ";
				} else {
					fileType = "普通文件";
				}
				// 判断是否匹配
				if (name.getName().contains(this.textField.getText())) {
					allCount++;
					textArea.append(fileType + " " + name.getName() + "\n");
				}
			}
			textArea.append("\n一共有" + allCount + "个匹配结果");
			logger.info("匹配字符" + textField.getText() + "\n");
			logger.info(textArea.getText() + "\n");
		}
		// 如果事件源是重置按钮
		if (source == resetButton) {
			showDir();
		}

	}

	// 获得当前路径下所有文件并列出
	private void showDir() {
		int allCount = 0;// 记录初始化
		// 获取当前文件
		File file = new File("");
		File dir = new File(file.getAbsolutePath());
		// 清空当前文本域内容
		textArea.setText(null);
		// 获得文件目录列表
		File[] names = dir.listFiles();
		for (File name : names) {
			allCount++;
			if (name.isDirectory()) {
				fileType = "文件夹  ";
			} else {
				fileType = "普通文件";
			}
			// 追加文本内容并换行
			textArea.append(fileType + " " + name.getName() + "\n");
		}
		textArea.append("\n此目录下一共有" + allCount + "条记录");
		logger.info(textArea.getText() + "\n");
		logger.info("匹配字符" + textField.getText() + "\n");
	}
	public static void main(String[] args) {
		new CheckGUI("文件验证");
	}
	
}
