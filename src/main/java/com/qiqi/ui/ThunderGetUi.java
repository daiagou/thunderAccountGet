package com.qiqi.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.qiqi.handler.ThunderAccountGet;
import com.qiqi.handler.ThunderAccountGet2;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ThunderGetUi extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThunderGetUi frame = new ThunderGetUi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ThunderGetUi() {
		setResizable(false);
		setTitle("获取迅雷vip共享账号");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
//		textArea.setBounds(72, 37, 399, 154);
		JScrollPane scroll = new JScrollPane(textArea); 
		scroll.setBounds(72, 37, 399, 154);
		scroll.setHorizontalScrollBarPolicy( 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
				scroll.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
//		contentPane.add(textArea);
		contentPane.add(scroll);
		
		JButton button = new JButton("获取");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				List<String> list = new ArrayList<>();
				ThunderAccountGet get1 = new ThunderAccountGet();
				list=get1.getAccountByRange(3);
				for(String s:list){
					textArea.append(s);
					textArea.append("\r\n");
				}
				
				
				ThunderAccountGet2 get2 = new ThunderAccountGet2();
				list=get2.getAccounts();
				for(String s:list){
					textArea.append(s);
					textArea.append("\r\n");
				}
				
				
			}
		});
		button.setBounds(213, 214, 117, 44);
		contentPane.add(button);
	}
	
	
	
	
	
}
