package com.se.jyh.viewComponent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

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
public class Frame{
	
	
	public MenuBar getMenubar() {
		return menubar;
	}
	public void setMenubar(MenuBar menubar) {
		this.menubar = menubar;
	}
	public LeftPanel getLeftpanel() {
		return leftpanel;
	}
	public void setLeftpanel(LeftPanel leftpanel) {
		this.leftpanel = leftpanel;
	}
	public RightPanel getRightpanel() {
		return rightpanel;
	}
	public void setRightpanel(RightPanel rightpanel) {
		this.rightpanel = rightpanel;
	}
	private demoController democontroller;
	private JFrame frame;
	private MenuBar menubar;
	private LeftPanel leftpanel;//= new LeftPanel();
	private RightPanel rightpanel;
	private JSplitPane splitpanel;
	private JToolBar toolbar;
	
	public Frame(demoController democontroller){
		
		this.democontroller=democontroller;
		
		this.setMenuBar();
		this.setToolBar();
		this.setLeftPanel();
		this.setRightPanel();
		this.setFrame();
		
		
	}
	public void setRightPanel(){
		this.rightpanel=new RightPanel(new BorderLayout(),this.democontroller);
	}
	public void setFrame(){
		
		/** setting frame: size, name, visibility */
		frame = new JFrame("Titan");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setPreferredSize(new Dimension(800, 550));
		frame.setVisible(true);
		frame.pack();
		
		/**
		 * add menubar
		 */
		frame.setJMenuBar(menubar);
		frame.add(toolbar,BorderLayout.PAGE_START);
		splitpanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,leftpanel,rightpanel);
		frame.getContentPane().add(splitpanel,BorderLayout.CENTER);	
		frame.pack();
	}
	
	public void setMenuBar(){
		
		menubar = new MenuBar(this.democontroller);
		
	}
	public void setLeftPanel(){
		
		leftpanel = new LeftPanel(new BorderLayout(),this.democontroller);
		
	}
	public void setToolBar(){
		
		toolbar = new JToolBar();
		
		toolbar.setFloatable(true);
		JButton openButton = new JButton(new ImageIcon("image/open-dsm.png"));
		openButton.setMargin(new Insets(0,0,0,0));
		JButton redrawButton = new JButton(new ImageIcon("image/redraw.png"));
		redrawButton.setMargin(new Insets(0,0,0,0));
		JButton newClustering = new JButton(new ImageIcon("image/new-clsx.png"));
		newClustering.setMargin(new Insets(0,0,0,0));
		JButton loadClustering = new JButton(new ImageIcon("image/open-clsx.png"));
		loadClustering.setMargin(new Insets(0,0,0,0));
		JButton saveClustering = new JButton(new ImageIcon("image/save-clsx.png"));
		saveClustering.setMargin(new Insets(0,0,0,0));
		JButton saveClusteringAs = new JButton(new ImageIcon("image/save-clsx-as.png"));
		saveClusteringAs.setMargin(new Insets(0,0,0,0));
		
		/**
		 * add buttons 
		 */
		
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
