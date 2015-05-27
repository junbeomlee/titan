package com.se.jyh.viewComponent.MenuBarCommand;

import javax.swing.JMenuItem;

import com.se.jyh.controller.demoController;

public class ViewCommandShowRowL_impl extends JMenuItem implements Command {

	
	private demoController democontroller;
	
	public ViewCommandShowRowL_impl(demoController democontroller) {
		// TODO Auto-generated constructor stub
		super("ShowRowL");
		this.democontroller=democontroller;
		//democontroller= demoController.getInstance();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("execute showrowL");
		democontroller.showRowLabels();
		
	}

	@Override
	public void notifyAction(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
