package com.se.jyh.view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.se.jyh.controller.Controller;
import com.se.jyh.view.menuitem.MenuCommandExit_impl;
import com.se.jyh.view.menuitem.MenuCommandNew_impl;
import com.se.jyh.view.menuitem.MenuCommandOpen_impl;
import com.se.jyh.view.menuitem.MenuCommandSaveAs_impl;
import com.se.jyh.view.menuitem.MenuCommandSave_impl;

/**
 * 
 * @author lgpc
 *
 * MenuBar
 * 
 */
public class MenuBar extends JMenuBar{
	
	private JMenu file;
	private JMenu help;
	private JMenu view;
	private Controller controller;
	private JFrame frame;
	
	public MenuBar(Controller controller, JFrame frame){
		
		this.controller = controller;
		this.frame=frame;	
		JMenu file = new JMenu("File");
		
		/**
		 * set its items
		 */
		
		JMenuItem newDsm = new MenuCommandNew_impl("New",frame);
		JMenuItem openDsm = new MenuCommandOpen_impl("Open",frame);
		JMenuItem saveDsm = new MenuCommandSave_impl("Save", frame);
		JMenuItem saveAsDsm = new MenuCommandSaveAs_impl("Save as", frame);
		JMenuItem exitDsm = new MenuCommandExit_impl("Exit", frame);
		
		
		file.add(newDsm);
		file.add(openDsm);
		file.add(saveDsm);
		file.add(saveAsDsm);
		file.add(exitDsm);
		
		controller.add(newDsm);
		controller.add(openDsm);
		controller.add(saveDsm);
		controller.add(saveAsDsm);
		controller.add(exitDsm);
		
		JMenu help = new JMenu("Help");
		JMenu view = new JMenu("view");
		
		this.add(file);
		this.add(view);
		this.add(help);
		
	}
}
