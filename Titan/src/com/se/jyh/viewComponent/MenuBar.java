package com.se.jyh.viewComponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.se.jyh.controller.demoController;
import com.se.jyh.viewComponent.MenuBarCommand.Command;
import com.se.jyh.viewComponent.MenuBarCommand.FileCommandExit_impl;
import com.se.jyh.viewComponent.MenuBarCommand.FileCommandLoadCluster_impl;
import com.se.jyh.viewComponent.MenuBarCommand.FileCommandNewCluster_impl;
import com.se.jyh.viewComponent.MenuBarCommand.FileCommandNew_impl;
import com.se.jyh.viewComponent.MenuBarCommand.FileCommandOpen_impl;
import com.se.jyh.viewComponent.MenuBarCommand.FileCommandSaveAs_impl;
import com.se.jyh.viewComponent.MenuBarCommand.FileCommandSaveClusterAs_impl;
import com.se.jyh.viewComponent.MenuBarCommand.FileCommandSaveCluster_impl;
import com.se.jyh.viewComponent.MenuBarCommand.FileCommandSave_impl;
import com.se.jyh.viewComponent.MenuBarCommand.HelpCommand;
import com.se.jyh.viewComponent.MenuBarCommand.ViewCommandRedraw_impl;
import com.se.jyh.viewComponent.MenuBarCommand.ViewCommandShowRowL_impl;

/**
 * 
 * @author lgpc
 *
 * MenuBar
 * 
 */

public class MenuBar extends JMenuBar implements ActionListener, MouseListener{
	
	private demoController democontroller;
	/**
	 * Menu list 
	 */
	private JMenu file;
	private JMenu help;
	private JMenu view;
	
	/**
	 * Menuitem list
	 * -File
	 */
	private JMenuItem newDsm;
	private JMenuItem openDsm;
	private JMenuItem saveDsm;
	private JMenuItem saveAsDsm;
	private JMenuItem exitDsm;
	private JMenuItem newClustering;
	private JMenuItem loadClustering;
	private JMenuItem saveClustering;
	private JMenuItem saveClusteringAs;
	
	/**
	 * MenuItem List
	 * -Help
	 */
	
	private JMenuItem show; 
	
	/**
	 * MenuItem List
	 * -View
	 */
	private JMenuItem Redraw;
	private JMenuItem ShowRowL;
	
	public MenuBar(demoController democontroller){	
		this.democontroller= democontroller;
		/**
		 * set Jmenu
		 */
		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		JMenu view = new JMenu("view");
		
		/**
		 * set items
		 */
		
		exitDsm = new FileCommandExit_impl(this.democontroller);
		newDsm = new FileCommandNew_impl(this.democontroller);
		openDsm = new FileCommandOpen_impl(this.democontroller);
		saveDsm = new FileCommandSave_impl(this.democontroller);
		saveAsDsm = new FileCommandSaveAs_impl(this.democontroller);
		newClustering= new FileCommandNewCluster_impl(this.democontroller);
		loadClustering= new FileCommandLoadCluster_impl(this.democontroller);
		saveClustering= new FileCommandSaveCluster_impl(this.democontroller);
		saveClusteringAs= new FileCommandSaveClusterAs_impl(this.democontroller);
		
		Redraw = new ViewCommandRedraw_impl(this.democontroller);
		ShowRowL = new ViewCommandShowRowL_impl(this.democontroller);
		
		show= new HelpCommand(this.democontroller);
		/**
		 * add items to files
		 */
		file.add(newDsm);
		file.add(openDsm);
		file.add(saveDsm);
		file.add(saveAsDsm);
		file.add(exitDsm);
		file.add(newClustering);
		file.add(loadClustering);
		file.add(saveClustering);
		file.add(saveClusteringAs);
		
		/**
		 * add items to view
		 */
		view.add(Redraw);
		view.add(ShowRowL);
		
		/**
		 * add items to help
		 */
		help.add(show);
		
		this.add(file);
		this.add(view);
		this.add(help);
		
		addListener();
		
	}
	
	public void addListener(){
		
		newDsm.addActionListener(this);
		openDsm.addActionListener(this);
		saveDsm.addActionListener(this);
		saveAsDsm.addActionListener(this);
		exitDsm.addActionListener(this);
		newClustering.addActionListener(this);
		loadClustering.addActionListener(this);
		saveClustering.addActionListener(this);
		saveClusteringAs.addActionListener(this);
		Redraw.addActionListener(this);
		ShowRowL.addActionListener(this);
		show.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		
		Command command = (Command) e.getSource();
		command.execute();
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
