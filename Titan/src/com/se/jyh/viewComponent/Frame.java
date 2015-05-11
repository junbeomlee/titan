package com.se.jyh.viewComponent;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;

/**
 * 
 * @author lgpc
 * 
 * Main view
 * 
 * is a parent of all other views and has a frame
 * 
 */
public class Frame{
	
	private JFrame frame;
	private MenuBar menubar;
	private JScrollPane leftpanel;
	private JSplitPane splitpanel;
	
	public Frame(){
		
		this.setMenuBar();
		this.setLeftPanel();
		this.setRightPanel();
		this.setFrame();
		
		
		
		/** set leftpanel menu
		 * 
		 */
		
		//scrollPane.setSize(50, 50);
		
		
		
	}
	public void setFrame(){
		
		/** setting frame: size, name, visibility */
		
		frame = new JFrame("Titan");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000,600);
		frame.setVisible(true);
		
		/**
		 * add menubar
		 */
		frame.setJMenuBar(menubar);
		/**
		 * add leftpanel
		 */
		//frame.getContentPane().add(leftpanel,BorderLayout.WEST);
		/**
		 * 
		 */
		JPanel panel1 = new JPanel(new BorderLayout());
		JPanel panel2 = new JPanel();
		JToolBar toolbar = new JToolBar();
		JButton button = new JButton("asd"){
				public Dimension getPreferredSize(){
					return new Dimension(5,20);
				}
		};
		//button.setSize(new Dimension(5,5));
		//panel1.add(button);
		toolbar.setFloatable(false);
		toolbar.setRollover(true);
		
		panel1.add(toolbar,BorderLayout.PAGE_START);
		
	
		toolbar.add(button);
		splitpanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panel1,panel2);
		frame.getContentPane().add(splitpanel);
		
		//frame.pack();
	}
	
	public void setMenuBar(){
		/** set menubar itmes is setting in the menubar class
		 * 
		 * 	MenuBar
		 *  JMenu: file help view
		 *  JMenuItems in file: 
		 *  
		 *  
		 */
		menubar = new MenuBar();
	}
	public void setLeftPanel(){
		JTree tree = new JTree();
		JTextArea asd =new JTextArea();
		
		/*
		leftpanel = new LeftPanel(tree,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		toolbar.add(button);
		leftpanel.add(toolbar,BorderLayout.PAGE_START);
		/*leftpanel = new LeftPanel();
		leftpanel.setSize(500,100);*/
		//leftpanel.setVisible(true);
	}
	public void setRightPanel(){
		
	}
}
