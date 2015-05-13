package com.se.jyh.viewComponent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

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
	private LeftPanel leftpanel;
	private JPanel rightpanel;
	private JSplitPane splitpanel;
	private JToolBar toolbar;
	
	public Frame(){
		
		this.setMenuBar();
		this.setToolBar();
		
		this.setLeftPanel();
		this.setRightPanel();
		
		this.setFrame();
		
	}
	
	public void setFrame(){
		
		/** setting frame: size, name, visibility */
		frame = new JFrame("Titan");
		//frame.setLayout(new BorderLayout());
		//frame.getContentPane().setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setSize(1000,600);
		frame.getContentPane().setPreferredSize(new Dimension(800, 550));
		//frame.setSize(1000, 600);
		frame.setVisible(true);
		frame.pack();
		
		/**
		 * add menubar
		 */
		frame.setJMenuBar(menubar);
		frame.add(toolbar,BorderLayout.PAGE_START);
		/**
		 * add leftpanel
		 */
		JPanel panel2 = new JPanel();
		
		splitpanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,leftpanel,panel2);
		//splitpanel.setPreferredSize(new Dimension(800,550));

		//frame.add(splitpanel);
		frame.getContentPane().add(splitpanel,BorderLayout.CENTER);
		
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
		
		leftpanel = new LeftPanel(new BorderLayout());
		leftpanel.setToolBar();
		
		//JTree tree = new JTree();
		//JTextArea asd =new JTextArea();
	
	}
	public void setRightPanel(){

		
	}
	public void setToolBar(){
		System.out.println("toolbar");
		toolbar = new JToolBar();
		
		//toolbar.setLayout(null);
		toolbar.setFloatable(true);
		
		JButton openButton = new JButton(new ImageIcon("image/open-dsm.png"));
		JButton redrawButton = new JButton(new ImageIcon("image/redraw.png"));
		JButton newClustering = new JButton(new ImageIcon("image/new-clsx.png"));
		JButton loadClustering = new JButton(new ImageIcon("image/open-clsx.png"));
		JButton saveClustering = new JButton(new ImageIcon("image/save-clsx.png"));
		JButton saveClusteringAs = new JButton(new ImageIcon("image/save-clsx-as.png"));
		
		/**
		 * set buttons 
		 */
		//openButton.setBounds(100, 20, 16, 16);
		openButton.setPreferredSize(new Dimension(16,16));
		
		toolbar.add(openButton);
		toolbar.add(redrawButton);
		toolbar.addSeparator();
		toolbar.add(newClustering);
		toolbar.add(loadClustering);
		toolbar.addSeparator();
		toolbar.add(saveClustering);
		toolbar.add(saveClusteringAs);
		
	}
}
