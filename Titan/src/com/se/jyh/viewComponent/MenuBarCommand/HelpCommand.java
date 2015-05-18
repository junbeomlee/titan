package com.se.jyh.viewComponent.MenuBarCommand;

import javax.swing.JMenuItem;

import com.se.jyh.controller.demoController;

public class HelpCommand extends JMenuItem implements Command{
	
	private demoController democontroller;

	public HelpCommand(String name){
		super(name);
		democontroller = demoController.getInstance();
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("execute help");
		democontroller.help();
	}

}
