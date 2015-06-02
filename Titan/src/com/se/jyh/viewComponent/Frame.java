package com.se.jyh.viewComponent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import com.se.jyh.controller.demoController;
import com.se.jyh.viewComponent.MenuBarCommand.Command;
import com.se.jyh.viewComponent.ToolBarCommand.ButtonCommandLoadClustering_impl;
import com.se.jyh.viewComponent.ToolBarCommand.ButtonCommandNewClustering_impl;
import com.se.jyh.viewComponent.ToolBarCommand.ButtonCommandOpen_impl;
import com.se.jyh.viewComponent.ToolBarCommand.ButtonCommandRedraw_impl;
import com.se.jyh.viewComponent.ToolBarCommand.ButtonCommandSaveClusteringAs_impl;
import com.se.jyh.viewComponent.ToolBarCommand.ButtonCommandSaveClustering_impl;

/**
 * 
 * @author lgpc
 * 
 * Main view
 * 
 * is a parent of all other views and has a frame
 * 
 */
public class Frame implements ActionListener {
	
	
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
	
	
	private JButton openButton; 
	private JButton redrawButton;
	private JButton newClustering;
	private JButton loadClustering; 
	private JButton saveClustering;
	private JButton saveClusteringAs;
	
	
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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setPreferredSize(new Dimension(1000, 650));
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
	
	public void disableFileMenu(){
		
		this.frame.setJMenuBar(null);
		this.frame.remove(toolbar);
		
	}
	public void setToolBar(){
		
		toolbar = new JToolBar();
		
		toolbar.setFloatable(true);
		openButton = new ButtonCommandOpen_impl(this.democontroller);
		redrawButton = new ButtonCommandRedraw_impl(this.democontroller);
		newClustering = new ButtonCommandNewClustering_impl(this.democontroller);
		loadClustering = new ButtonCommandLoadClustering_impl(this.democontroller);
		saveClustering = new ButtonCommandSaveClustering_impl(this.democontroller);
		saveClusteringAs = new ButtonCommandSaveClusteringAs_impl(this.democontroller);
		
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
		
		/**
		 * 
		 */
		this.openButton.addActionListener(this);
		this.redrawButton.addActionListener(this);
		this.saveClustering.addActionListener(this);
		this.saveClusteringAs.addActionListener(this);
		this.loadClustering.addActionListener(this);
		this.newClustering.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Command command = (Command) e.getSource();
		command.execute();
		
	}
}
