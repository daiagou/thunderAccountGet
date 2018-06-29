package com.qiqi;

import java.awt.EventQueue;

import com.qiqi.ui.ThunderGetUi;

/**
 * Created by abner.zhang on 18/6/29.
 */
public class App {


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


}
