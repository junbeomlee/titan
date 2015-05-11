package com.se.jyh.viewComponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.se.jyh.controller.Controller;
import com.se.jyh.controller.demoController;
import com.se.jyh.viewComponent.MenuBarCommand.MenuCommand;
import com.se.jyh.viewComponent.MenuBarCommand.FileCommandExit_impl;
import com.se.jyh.viewComponent.MenuBarCommand.FileCommandNew_impl;
import com.se.jyh.viewComponent.MenuBarCommand.FileCommandOpen_impl;
import com.se.jyh.viewComponent.MenuBarCommand.FileCommandSaveAs_impl;
import com.se.jyh.viewComponent.MenuBarCommand.FileCommandSave_impl;

/**
 * 
 * @author lgpc
 *
 * MenuBar
 * 
 */
public class MenuBar extends JMenuBar implements ActionListener{
	
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
	
	
	
	/**
	 * MenuItem List
	 * -View
	 */

	
	public MenuBar(){
		System.out.println("menubar");	
		JMenu file = new JMenu("File");
		
		/**
		 * set its items
		 */
		exitDsm = new FileCommandExit_impl("Exit");
		
		/*
		newDsm = new MenuCommandNew_impl("New",frame);
		openDsm = new MenuCommandOpen_impl("Open",frame);
		saveDsm = new MenuCommandSave_impl("Save", frame);
		saveAsDsm = new MenuCommandSaveAs_impl("Save as", frame);
		
		
		
		file.add(newDsm);
		file.add(openDsm);
		file.add(saveDsm);
		file.add(saveAsDsm);
		file.add(exitDsm);
		
		controller.add(newDsm);
		controller.add(openDsm);
		controller.add(saveDsm);
		controller.add(saveAsDsm);
		controller.add(exitDsm);*/
		
		JMenu help = new JMenu("Help");
		JMenu view = new JMenu("view");
		
		this.add(file);
		this.add(view);
		this.add(help);
		
		addListener();
		
	}
	
	public void addListener(){
		System.out.println("Add listener");
		/*
		newDsm.addActionListener(this);
		openDsm.addActionListener(this);
		saveDsm.addActionListener(this);
		saveAsDsm.addActionListener(this);*/
		exitDsm.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		System.out.println("action");
		MenuCommand command = (MenuCommand) e.getSource();
		command.execute();
	}
}
