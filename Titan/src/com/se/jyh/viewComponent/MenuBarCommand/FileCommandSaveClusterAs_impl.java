package com.se.jyh.viewComponent.MenuBarCommand;

import javax.swing.JMenuItem;

import com.se.jyh.controller.demoController;

public class FileCommandSaveClusterAs_impl extends JMenuItem implements Command {

	private demoController democontroller;

	public FileCommandSaveClusterAs_impl(demoController democontroller) {
		// TODO Auto-generated constructor stub
		super("Save Cluster As");
		this.democontroller=democontroller;
		//democontroller = demoController.getInstance();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("execute saveclusteras");
		democontroller.saveClusterAs();

	}

	

	@Override
	public void notifyAction(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
