package com.se.jyh.viewComponent.MenuBarCommand;

import javax.swing.JMenuItem;

import com.se.jyh.controller.demoController;

public class FileCommandSaveCluster_impl extends JMenuItem implements Command {

	private demoController democontroller;

	public FileCommandSaveCluster_impl(demoController democontroller) {
		super("Save Cluster");
		this.democontroller=democontroller;
		//democontroller = demoController.getInstance();

	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("execute savecluster");
		democontroller.saveCluster();

	}

	

	@Override
	public void notifyAction(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
