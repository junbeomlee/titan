package com.se.jyh.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.se.jyh.controller.Controller;
import com.se.jyh.controller.MainController;

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
	private Controller controller;
	private JPanel leftpanelmenu;
	
	public View(Controller controller){
		/**
		 * to call the functions in controller
		 */
		this.controller = controller;
		System.out.println(controller.toString());
		/** setting frame: size, name, visibility
		 */
		frame = new JFrame("Titan");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200,200);
		frame.setVisible(true);
		
		/** set menubar itmes is setting in the menubar class */
		menubar = new MenuBar(this.controller,frame);
		frame.setJMenuBar(menubar);
		
		/** set leftpanel menu*/
		leftpanelmenu = new LeftPanelMenu(this.controller);
		frame.add(leftpanelmenu);
		
	}
}
