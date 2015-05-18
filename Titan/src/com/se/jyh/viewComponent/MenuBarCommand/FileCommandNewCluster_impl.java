package com.se.jyh.viewComponent.MenuBarCommand;

import javax.swing.JMenuItem;

import com.se.jyh.controller.demoController;

public class FileCommandNewCluster_impl extends JMenuItem implements Command {

	private demoController democontroller;

	public FileCommandNewCluster_impl(String name) {
		super(name);
		democontroller = demoController.getInstance();

	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		//democontroller.newcluste();
		System.out.println("execute newcluster");
		democontroller.newCluster();
		
	}

}
