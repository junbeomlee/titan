package com.se.jyh.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.se.jyh.controller.Controller;
import com.se.jyh.controller.demoController;

/**
 * 
 * @author lgpc
 * 
 * Main view
 * 
 * is a parent of all other views and has a frame
 * 
 */
public class View{
	
	private JFrame frame;
	private MenuBar menubar;
	private demoController democontroller;
	private JPanel leftpanelmenu;
	
	public View(demoController democontroller){
		/**
		 * to call the functions in controller
		 */
		System.out.println("view");
		this.democontroller = democontroller;
		//System.out.println(controller.toString());
		/** setting frame: size, name, visibility
		 */
		frame = new JFrame("Titan");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200,200);
		frame.setVisible(true);
		
		/** set menubar itmes is setting in the menubar class */
		menubar = new MenuBar(this.democontroller,frame);
		frame.setJMenuBar(menubar);
		
		/** set leftpanel menu*/
		leftpanelmenu = new LeftPanelMenu(this.democontroller);
		frame.add(leftpanelmenu);
		
	}
}
