package com.se.jyh.viewComponent.MenuBarCommand;

import javax.swing.JMenuItem;

import com.se.jyh.controller.demoController;

public class FileCommandSaveClusterAs_impl extends JMenuItem implements Command {

	private demoController democontroller;

	public FileCommandSaveClusterAs_impl(String name) {
		// TODO Auto-generated constructor stub
		super(name);
		democontroller = demoController.getInstance();
		System.out.println("exit");
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
