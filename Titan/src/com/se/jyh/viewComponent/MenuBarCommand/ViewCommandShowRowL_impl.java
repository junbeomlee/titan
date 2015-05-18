package com.se.jyh.viewComponent.MenuBarCommand;

import javax.swing.JMenuItem;

import com.se.jyh.controller.demoController;

public class ViewCommandShowRowL_impl extends JMenuItem implements Command {

	
	private demoController democontroller;
	
	public ViewCommandShowRowL_impl(String name) {
		// TODO Auto-generated constructor stub
		super(name);
		democontroller= demoController.getInstance();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("execute showrowL");
		democontroller.showRowLabels();
		
	}

}
