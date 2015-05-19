package com.se.jyh.viewComponent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

import com.se.jyh.model.Table;

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
	private LeftPanel leftpanel= new LeftPanel(new BorderLayout());
	private RightPanel rightpanel= new RightPanel(new BorderLayout());
	private JSplitPane splitpanel;
	private JToolBar toolbar;
	
	public Frame(){
		
		this.setMenuBar();
		this.setToolBar();
		this.setLeftPanel();
		this.setFrame();
		
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
		
		menubar = new MenuBar();
		
	}
	public void setLeftPanel(){
		
		leftpanel = new LeftPanel(new BorderLayout());
		
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
