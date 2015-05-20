package com.se.jyh.viewComponent.MenuBarCommand;

import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import com.se.jyh.controller.demoController;

public class FileCommandNew_impl extends JMenuItem implements Command {

	
	private demoController democontroller;
	
	public FileCommandNew_impl(String name){
		super(name);
		democontroller= demoController.getInstance();
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("execute new");
		democontroller.newDsm();
	}
	@Override
	public String toString(){
		String a = "New";
		return a;
		
	}
	@Override
	public void notifyAction(boolean b) {
		// TODO Auto-generated method stub
		
	}
	


}
