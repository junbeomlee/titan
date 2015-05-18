package com.se.jyh.viewComponent.MenuBarCommand;

import javax.swing.JMenuItem;

import com.se.jyh.controller.demoController;

public class FileCommandLoadCluster_impl extends JMenuItem implements Command {

	private demoController democontroller;

	public FileCommandLoadCluster_impl(String name) {
		super(name);
		democontroller = demoController.getInstance();

	}

	@Override
	public void execute() {
		// TODO Auto-generated method stu
		System.out.println("execute loadcluster");
		democontroller.loadCluster();
	}

}
